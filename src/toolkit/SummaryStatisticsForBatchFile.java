package toolkit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import business.BiologicalOutcome;
import business.FinalResult;
import main.KaryotypeRunner;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class SummaryStatisticsForBatchFile {
	
	private Map<String, List<List<Integer>>> chrLGFOccurrenceCountMap;
	private Map<String, List<List<Double>>> summaryStatisticsMap;
	private int karyotypeCloneCount = 0;
	
	{
		chrLGFOccurrenceCountMap = getInitializedChrLGFOccurrenceCountMap();
		summaryStatisticsMap = getInitializedSummaryStatisticsMap();
	}
	
	private Map<String, List<List<Integer>>> getInitializedChrLGFOccurrenceCountMap() {
		Map<String, List<List<Integer>>> chrLGFOccurrenceCountMap = new HashMap<>();
		List<String> chrList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "X", "Y");
		chrList.forEach(chr -> {
									List<List<Integer>> chrLGFOccurrenceCount = new ArrayList<>();
									int chrBandsSize = ISCN2016.getChrBandsList(chr).size();
									chrLGFOccurrenceCount.add(new ArrayList<>(Collections.nCopies(chrBandsSize, 0)));
									chrLGFOccurrenceCount.add(new ArrayList<>(Collections.nCopies(chrBandsSize, 0)));
									chrLGFOccurrenceCount.add(new ArrayList<>(Collections.nCopies(chrBandsSize, 0)));
									chrLGFOccurrenceCountMap.put(chr, chrLGFOccurrenceCount);			
								});
		return chrLGFOccurrenceCountMap;
	}
	
	private Map<String, List<List<Double>>> getInitializedSummaryStatisticsMap() {
		Map<String, List<List<Double>>> summaryStatisticsMap = new HashMap<>();
		List<String> chrList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "X", "Y");
		chrList.forEach(chr -> {
									List<List<Double>> chrSummaryStatistics = new ArrayList<>();
									int chrBandsSize = ISCN2016.getChrBandsList(chr).size();
									chrSummaryStatistics.add(new ArrayList<>(Collections.nCopies(chrBandsSize, 0.0)));
							  		chrSummaryStatistics.add(new ArrayList<>(Collections.nCopies(chrBandsSize, 0.0)));
							  		chrSummaryStatistics.add(new ArrayList<>(Collections.nCopies(chrBandsSize, 0.0)));
							  		summaryStatisticsMap.put(chr, chrSummaryStatistics);			
								});
		return summaryStatisticsMap;
	}
	
	private Map<String, List<List<Double>>> updateChrLGFOccurrenceCountMap(Path filePath) {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath.toFile()))) {
			String line;
			while((line = bufferedReader.readLine()) != null) {
                if (line.trim().length() > 0) {
                	updateChrLGFOccurrenceCountMap(line);
                }
            }	
			return getSummaryStatistics();
		} catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	private void updateChrLGFOccurrenceCountMap(String input) {
		FinalResult finalResult = KaryotypeRunner.getFinalResult(input);
		if (finalResult.isContainingLexerParserError()) {
			String revisedKaryotype = finalResult.getRevisedKaryotype();
			if (!revisedKaryotype.isEmpty()) {
				FinalResult newFinalResult = KaryotypeRunner.getFinalResult(revisedKaryotype);
				if (!newFinalResult.isContainingValidationError()) {
					List<BiologicalOutcome> biologicalOutcomeList = newFinalResult.getBiologicalOutcomeList();
					updateChrLGFOccurrenceCountMap(biologicalOutcomeList);
				}
			}
		} else if (finalResult.isContainingValidationError()) {
			
		} else {
			List<BiologicalOutcome> biologicalOutcomeList = finalResult.getBiologicalOutcomeList();
			updateChrLGFOccurrenceCountMap(biologicalOutcomeList);
		}
	}
	
	private void updateChrLGFOccurrenceCountMap(List<BiologicalOutcome> biologicalOutcomeList) {
		karyotypeCloneCount += biologicalOutcomeList.size();
		biologicalOutcomeList.forEach(
			biologicalOutcome -> { 
									Map<String, List<List<Integer>>> aberrantChrLGFMap = BiologicalOutcome.getAberrantChrLGF(biologicalOutcome);
									aberrantChrLGFMap
									  	.keySet()
									  	.forEach(chr -> {
									  		int chrBandsSize = ISCN2016.getChrBandsList(chr).size();
									  		IntStream.rangeClosed(0, 2).forEach(i -> {
									  			IntStream.range(0, chrBandsSize).forEach(j -> {
									  				if (aberrantChrLGFMap.get(chr).get(i).get(j) > 0) {
									  					chrLGFOccurrenceCountMap.get(chr).get(i).set(j, chrLGFOccurrenceCountMap.get(chr).get(i).get(j) + 1); 
										  			}
									  			});								  			
									  		});
									  	});
								 }
		);
	}
	
	private Map<String, List<List<Double>>> getSummaryStatistics() {
		chrLGFOccurrenceCountMap
		  	.keySet()
		  	.forEach(chr -> {
		  		List<List<Double>> chrSummaryStatistics = summaryStatisticsMap.get(chr);
		  		int chrBandsSize = ISCN2016.getChrBandsList(chr).size();
		  		IntStream.rangeClosed(0, 2).forEach(i -> {
		  			IntStream.range(0, chrBandsSize).forEach(j -> {
		  				int count = chrLGFOccurrenceCountMap.get(chr).get(i).get(j);
						if (count > 0) {
							double fraction = (double)Math.round(((double)count/karyotypeCloneCount) * 10000) / 10000;
							chrSummaryStatistics.get(i).set(j, fraction);
						}
		  			});								  			
		  		});
		  		summaryStatisticsMap.put(chr, chrSummaryStatistics);
		  	});
		return summaryStatisticsMap;
	} 	
	
	public static void writeSummaryStatistics(Path fileReadPath, Path fileWritePath) throws Exception {

        Map<String, List<List<Double>>> summaryStatisticsMap = new SummaryStatisticsForBatchFile().updateChrLGFOccurrenceCountMap(fileReadPath);
        List<List<Double>> overallSummaryStatistics = new ArrayList<>();
        overallSummaryStatistics.add(new ArrayList<Double>());
        overallSummaryStatistics.add(new ArrayList<Double>());
        overallSummaryStatistics.add(new ArrayList<Double>());
        List<String> chrList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "X", "Y");
        chrList.forEach(chr -> {
            List<List<Double>> chrSummaryStatistics = summaryStatisticsMap.get(chr);
            IntStream.rangeClosed(0, 2).forEach(i -> overallSummaryStatistics.get(i).addAll(chrSummaryStatistics.get(i)));
        });
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileReadPath.toFile())); BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWritePath.toFile()))) {
        	bufferedWriter.write("G850-Bands,Loss,Gain,Fusion");
        	bufferedWriter.write("\n");
        	List<String> allChrBandsList = BiologicalOutcome.getChrBandsList();
            int allChrBandsSize = allChrBandsList.size();
            for (int rowNum = 0; rowNum < allChrBandsSize; rowNum++) {
            	bufferedWriter.write(allChrBandsList.get(rowNum) + "," + formatPercentage(overallSummaryStatistics.get(0).get(rowNum)) + "," + formatPercentage(overallSummaryStatistics.get(1).get(rowNum)) + "," + formatPercentage(overallSummaryStatistics.get(2).get(rowNum)));
            	bufferedWriter.write("\n");
            }
        	
        } catch (Exception e) {
			throw e;
        }
        
        
    }
	
	private static String formatPercentage(double d) {
        try {
            NumberFormat defaultFormat = NumberFormat.getPercentInstance();
            defaultFormat.setMinimumFractionDigits(2);
            return defaultFormat.format(d);
        } catch (Exception e) {
            return "";
        }
    }

}
