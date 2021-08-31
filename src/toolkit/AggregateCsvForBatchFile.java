package toolkit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.List;

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
public class AggregateCsvForBatchFile {
	
	public static void generateAggregateLGF(Path fileReadPath, Path fileWritePath) throws Exception {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileReadPath.toFile())); BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWritePath.toFile()))) {
			bufferedWriter.write("Line_Number,Karyotype_Revised,Clone_Code");
			for (String[] chrArm: ISCN2016.chrArmArrays) {
				for (String chrBand: chrArm) {
					bufferedWriter.write("," + chrBand + "_Loss");
					bufferedWriter.write("," + chrBand + "_Gain");
					bufferedWriter.write("," + chrBand + "_Fusion");
				}				
			}
			bufferedWriter.write("\n");
			int lineNum = 1;
			String line;			
			while((line = bufferedReader.readLine()) != null) {
                if (line.trim().length() > 0) {
                	FinalResult finalResult = KaryotypeRunner.getFinalResult(line);
                	if (finalResult.isContainingLexerParserError()) {
                		String revisedKaryotype = finalResult.getRevisedKaryotype();
                		if (!revisedKaryotype.isEmpty()) {
                			FinalResult newFinalResult = KaryotypeRunner.getFinalResult(revisedKaryotype);
                			if (!newFinalResult.isContainingValidationError()) {
                				writeCloneLGF(bufferedWriter, lineNum, true, newFinalResult);
                			}
                		}
                	} else if (finalResult.isContainingValidationError()) {
                		
                	} else {
            			writeCloneLGF(bufferedWriter, lineNum, false, finalResult);
            		}
                }
                lineNum++;
            }
		} catch (Exception e) {
			throw e;
        }
	}
	
	private static void writeCloneLGF(BufferedWriter bufferedWriter, int lineNum, boolean revisedKaryotype, FinalResult finalResult) throws Exception  {
		try {
			List<String> cloneCodeList = finalResult.getCloneCodeList();
			List<BiologicalOutcome> biologicalOutcomeList = finalResult.getBiologicalOutcomeList();
			for (int i = 0; i < cloneCodeList.size(); i++) {
				if (cloneCodeList.get(i).contains(",")) {
					bufferedWriter.write(lineNum + "," + revisedKaryotype + ",\"" + cloneCodeList.get(i) + "\"");
				} else {
					bufferedWriter.write(lineNum + "," + revisedKaryotype + "," + cloneCodeList.get(i));
				}				
				List<List<Integer>> cloneLGF = BiologicalOutcome.getAdjustedLGF(biologicalOutcomeList.get(i));
				int chrBandsSize = cloneLGF.get(0).size();
				for (int k = 0; k < chrBandsSize; k++) {
					for (int j = 0; j <= 2; j++) {
						bufferedWriter.write("," + cloneLGF.get(j).get(k));
					}
				}
				bufferedWriter.write("\n");
			}
		} catch (Exception e) {
			throw e;
        }
	}
}