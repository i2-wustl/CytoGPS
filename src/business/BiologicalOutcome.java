package business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

import toolkit.ChrBreakpointComparator;
import toolkit.ISCN2016;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class BiologicalOutcome {
	
	private List<List<Integer>> karyotypeLGF;
	private List<String> uncertainEventsList;
	private List<String> derDetailedSystem;

	
	public BiologicalOutcome(List<List<Integer>> karyotypeLGF, List<String> uncertainEventsList, List<String> derDetailedSystem) {		
		this.karyotypeLGF = karyotypeLGF;
		this.uncertainEventsList = uncertainEventsList;
		this.derDetailedSystem = derDetailedSystem;
	}

	public List<List<Integer>> getKaryotypeLGF() {
		return karyotypeLGF;
	}

	public void setKaryotypeLGF(List<List<Integer>> karyotypeLGF) {
		this.karyotypeLGF = karyotypeLGF;
	}	
	
	public List<String> getUncertainEventsList() {
		return uncertainEventsList;
	}

	public void setUncertainEventsList(List<String> uncertainEventsList) {
		this.uncertainEventsList = uncertainEventsList;
	}

	public List<String> getDetailedSystem() {
		return derDetailedSystem;
	}

	public void setDetailedSystem(List<String> derDetailedSystem) {
		this.derDetailedSystem = derDetailedSystem;
	}
	
	
	
	public static String getBiologicalInterpretation(BiologicalOutcome b) {
		String output = "";
		List<String> uncertainEventsList = b.getUncertainEventsList();
		if (uncertainEventsList.size() > 0) {
			output += "Undecoded Events: " + uncertainEventsList + "\r\n";			
		}
		List<String> derDetailedSystem = b.getDetailedSystem();
		if (derDetailedSystem.size() > 0) {
			output += "The detailed formats of all the derivative chromosomes in this clone are as follows: \r\n";
		}
		for (String s: derDetailedSystem) {
			output += s + "\r\n";
		}
		Map<Integer, String> indexToChrMap = new ParseEvent().getIndexToChrMap();
		ChrBreakpointComparator chrBreakpointComparator = new ChrBreakpointComparator();
		Map<String, Integer> lossMap = new TreeMap<>(chrBreakpointComparator);
		Map<String, Integer> gainMap = new TreeMap<>(chrBreakpointComparator);
		Map<String, Integer> fusionMap = new TreeMap<>(chrBreakpointComparator);
		List<List<Integer>> karyotypeLGF = b.getKaryotypeLGF();
		for (int i = 0; i < indexToChrMap.size(); i++) {
			if (karyotypeLGF.get(0).get(i) > 0) {
				lossMap.put(indexToChrMap.get(i), karyotypeLGF.get(0).get(i));
			}
			if (karyotypeLGF.get(1).get(i) > 0) {
				gainMap.put(indexToChrMap.get(i), karyotypeLGF.get(1).get(i));
			}
			if (karyotypeLGF.get(2).get(i) > 0) {
				fusionMap.put(indexToChrMap.get(i), karyotypeLGF.get(2).get(i));
			}
		}
		if (lossMap.size() > 0) {
			output += "Loss Outcome: " + lossMap + "\r\n";
		}
		if (gainMap.size() > 0) {
			output += "Gain Outcome: " + gainMap + "\r\n";
		}
		if (fusionMap.size() > 0) {
			output += "Fusion Outcome: " + fusionMap + "\r\n";
		}
		if (output.isEmpty()) {
			output += "This is a normal karyotype.\r\n";
		}
		return output;
	}
	
	
	
     
	
	
	
	
	
	private static final String[] CHR1P = {"1p10", "1p11.1", "1p11.2", "1p12", "1p13.1", "1p13.2", "1p13.3", 
								           "1p21.1", "1p21.2", "1p21.3", "1p22.1", "1p22.2", "1p22.3", 
								           "1p31.1", "1p31.2", "1p31.3", "1p32.1", "1p32.2", "1p32.3", "1p33", 
								           "1p34.1", "1p34.2", "1p34.3", "1p35.1", "1p35.2", "1p35.3", 
								           "1p36.11", "1p36.12", "1p36.13", "1p36.21", "1p36.22", "1p36.23", "1p36.31", "1p36.32", "1p36.33"};
	private static final String[] CHR1Q = {"1q10", "1q11", "1q12", "1q21.1", "1q21.2", "1q21.3", "1q22", "1q23.1", "1q23.2", "1q23.3", 
								           "1q24.1", "1q24.2", "1q24.3", "1q25.1", "1q25.2", "1q25.3", 
								           "1q31.1", "1q31.2", "1q31.3", "1q32.11", "1q32.12", "1q32.13", "1q32.2", "1q32.3",
								           "1q41", "1q42.11", "1q42.12", "1q42.13", "1q42.2", "1q42.3", "1q43", "1q44"};
	private static final String[] CHR2P = {"2p10", "2p11.1", "2p11.2", "2p12", "2p13.1", "2p13.2", "2p13.3", "2p14", "2p15", 
								           "2p16.1", "2p16.2", "2p16.3", "2p21.1", "2p21.2", "2p21.3", "2p22.1", "2p22.2", "2p22.3", 
								           "2p23.1", "2p23.2", "2p23.3", "2p24.1", "2p24.2", "2p24.3", "2p25.1", "2p25.2", "2p25.3"};
	private static final String[] CHR2Q = {"2q10", "2q11.1", "2q11.2", "2q12.1", "2q12.2", "2q12.3", "2q13", 
								           "2q14.1", "2q14.2", "2q14.3", "2q21.1", "2q21.2", "2q21.3", 
								           "2q22.1", "2q22.2", "2q22.3", "2q23.1", "2q23.2", "2q23.3", "2q24.1", "2q24.2", "2q24.3", 
								           "2q31.1", "2q31.2", "2q31.3", "2q32.1", "2q32.2", "2q32.3", "2q33.1", "2q33.2", "2q33.3", 
								           "2q34", "2q35", "2q36.1", "2q36.2", "2q36.3", "2q37.1", "2q37.2", "2q37.3"};
	private static final String[] CHR3P = {"3p10", "3p11.1", "3p11.2", "3p12.1", "3p12.2", "3p12.3", "3p13", "3p14.1", "3p14.2", "3p14.3", 
								           "3p21.1", "3p21.2", "3p21.31", "3p21.32", "3p21.33", "3p22.1", "3p22.2", "3p22.3", "3p23", 
								           "3p24.1", "3p24.2", "3p24.3", "3p25.1", "3p25.2", "3p25.3", "3p26.1", "3p26.2", "3p26.3"};
	private static final String[] CHR3Q = {"3q10", "3q11.1", "3q11.2", "3q12.1", "3q12.2", "3q12.3", "3q13.11", "3q13.12", "3q13.13",
								           "3q13.2", "3q13.31", "3q13.32", "3q13.33", "3q21.1", "3q21.2", "3q21.3", "3q22.1", "3q22.2", "3q22.3", 
								           "3q23", "3q24", "3q25.1", "3q25.2", "3q25.31", "3q25.32", "3q25.33", "3q26.1", "3q26.2", 
								           "3q26.31", "3q26.32", "3q26.33", "3q27.1", "3q27.2", "3q27.3", "3q28", "3q29"};
	private static final String[] CHR4P = {"4p10", "4p11", "4p12", "4p13", "4p14", "4p15.1", "4p15.2", 
								           "4p15.31", "4p15.32", "4p15.33", "4p16.1", "4p16.2", "4p16.3"};
	private static final String[] CHR4Q = {"4q10", "4q11", "4q12", "4q13.1", "4q13.2", "4q13.3", 
								           "4q21.1", "4q21.21", "4q21.22", "4q21.23", "4q21.3", "4q22.1", "4q22.2", "4q22.3", 
								           "4q23", "4q24", "4q25", "4q26", "4q27", "4q28.1", "4q28.2", "4q28.3", 
								           "4q31.1", "4q31.21", "4q31.22", "4q31.23", "4q31.3", "4q32.1", "4q32.2", "4q32.3", 
								           "4q33", "4q34.1", "4q34.2", "4q34.3", "4q35.1", "4q35.2"};
	private static final String[] CHR5P = {"5p10", "5p11", "5p12", "5p13.1", "5p13.2", "5p13.3", 
								           "5p14.1", "5p14.2", "5p14.3", "5p15.1", "5p15.2", "5p15.31", "5p15.32", "5p15.33"};
	private static final String[] CHR5Q = {"5q10", "5q11.1", "5q11.2", "5q12.1", "5q12.2", "5q12.3", "5q13.1", 
								           "5q13.21", "5q13.22", "5q13.23", "5q13.3", "5q14.1", "5q14.2", "5q14.3", "5q15", 
								           "5q21.1", "5q21.2", "5q21.3", "5q22.1", "5q22.2", "5q22.3", "5q23.1", "5q23.2", "5q23.3",
								           "5q31.1", "5q31.2", "5q31.3", "5q32", "5q33.1", "5q33.2", "5q33.3", "5q34", "5q35.1", "5q35.2", "5q35.3"};
	private static final String[] CHR6P = {"6p10", "6p11.1", "6p11.2", "6p12.1", "6p12.2", "6p12.3", 
								           "6p21.1", "6p21.2", "6p21.31", "6p21.32", "6p21.33", "6p22.1", "6p22.2", 
								           "6p22.31", "6p22.32", "6p22.33", "6p23", "6p24", "6p25.1", "6p25.2", "6p25.3"};
	private static final String[] CHR6Q = {"6q10", "6q11.1", "6q11.2", "6q12", "6q13", "6q14.1", "6q14.2", "6q14.3", "6q15", "6q16.1", "6q16.2", "6q16.3", 
								           "6q21.1", "6q21.2", "6q21.3", "6q22.1", "6q22.2", "6q22.31", "6q22.32", "6q22.33", 
								           "6q23.1", "6q23.2", "6q23.3", "6q24.1", "6q24.2", "6q24.3", "6q25.1", "6q25.2", "6q25.3", "6q26", "6q27"};
	private static final String[] CHR7P = {"7p10", "7p11.1", "7p11.2", "7p12.1", "7p12.2", "7p12.3", "7p13", "7p14.1", "7p14.2", "7p14.3", 
								           "7p15.1", "7p15.2", "7p15.3", "7p21.1", "7p21.2", "7p21.3", "7p22.1", "7p22.2", "7p22.3"};
	private static final String[] CHR7Q = {"7q10", "7q11.1", "7q11.21", "7q11.22", "7q11.23", "7q21.11", "7q21.12", "7q21.13", 
								           "7q21.2", "7q21.3", "7q22.1", "7q22.2", "7q22.3", "7q31.1", "7q31.2", "7q31.31", "7q31.32", "7q31.33", 
								           "7q32.1", "7q32.2", "7q32.3", "7q33", "7q34", "7q35", "7q36.1", "7q36.2", "7q36.3"};
	private static final String[] CHR8P = {"8p10", "8p11.1", "8p11.21", "8p11.22", "8p11.23", "8p12", 
								           "8p21.1", "8p21.2", "8p21.3", "8p22", "8p23.1", "8p23.2", "8p23.3"};
	private static final String[] CHR8Q = {"8q10", "8q11.1", "8q11.21", "8q11.22", "8q11.23", "8q12.1", "8q12.2", "8q12.3",
								           "8q13.1", "8q13.2", "8q13.3", "8q21.11", "8q21.12", "8q21.13", "8q21.2", "8q21.3", 
								           "8q22.1", "8q22.2", "8q22.3", "8q23.1", "8q23.2", "8q23.3", "8q24.11", "8q24.12", "8q24.13", 
								           "8q24.21", "8q24.22", "8q24.23", "8q24.3"};
	private static final String[] CHR9P = {"9p10", "9p11.1", "9p11.2", "9p12", "9p13.1", "9p13.2", "9p13.3", 
								           "9p21.1", "9p21.2", "9p21.3", "9p22.1", "9p22.2", "9p22.3", "9p23", "9p24.1", "9p24.2", "9p24.3"};
	private static final String[] CHR9Q = {"9q10", "9q11", "9q12", "9q13", "9q21.11", "9q21.12", "9q21.13", "9q21.2", "9q21.31", "9q21.32", "9q21.33", 
								           "9q22.1", "9q22.2", "9q22.31", "9q22.32", "9q22.33", "9q31.1", "9q31.2", "9q31.3", 
								           "9q32", "9q33.1", "9q33.2", "9q33.3", "9q34.1", "9q34.2", "9q34.3"};
	private static final String[] CHR10P= {"10p10", "10p11.1", "10p11.21", "10p11.22", "10p11.23", "10p12.1", "10p12.2", 
								           "10p12.31", "10p12.32", "10p12.33", "10p13", "10p14", "10p15.1", "10p15.2", "10p15.3"};
	private static final String[] CHR10Q= {"10q10", "10q11.1", "10q11.21", "10q11.22", "10q11.23", "10q21.1", "10q21.2", "10q21.3", 
								           "10q22.1", "10q22.2", "10q22.3", "10q23.1", "10q23.2", "10q23.31", "10q23.32", "10q23.33", 
								           "10q24.1", "10q24.2", "10q24.31", "10q24.32", "10q24.33", "10q25.1", "10q25.2", "10q25.3", 
								           "10q26.11", "10q26.12", "10q26.13", "10q26.2", "10q26.3"};
	private static final String[] CHR11P= {"11p10", "11p11.11", "11p11.12", "11p11.2", "11p12", "11p13", "11p14.1", "11p14.2", "11p14.3", 
								           "11p15.1", "11p15.2", "11p15.3", "11p15.4", "11p15.5"};
	private static final String[] CHR11Q= {"11q10", "11q11", "11q12.1", "11q12.2", "11q12.3", "11q13.1", "11q13.2", "11q13.3", "11q13.4", "11q13.5", 
								           "11q14.1", "11q14.2", "11q14.3", "11q21", "11q22.1", "11q22.2", "11q22.3", "11q23.1", "11q23.2", "11q23.3", 
								           "11q24.1", "11q24.2", "11q24.3", "11q25"};
	private static final String[] CHR12P= {"12p10", "12p11.1", "12p11.21", "12p11.22", "12p11.23", "12p12.1", "12p12.2", "12p12.3", 
								           "12p13.1", "12p13.2", "12p13.31", "12p13.32", "12p13.33"};
	private static final String[] CHR12Q= {"12q10", "12q11", "12q12", "12q13.11", "12q13.12", "12q13.13", "12q13.2", "12q13.3", 
								           "12q14.1", "12q14.2", "12q14.3", "12q15", 
								           "12q21.1", "12q21.2", "12q21.31", "12q21.32", "12q21.33", "12q22", "12q23.1", "12q23.2", "12q23.3", 
								           "12q24.11", "12q24.12", "12q24.13", "12q24.21", "12q24.22", "12q24.23", "12q24.31", "12q24.32", "12q24.33"};
	private static final String[] CHR13P= {"13p10", "13p11.1", "13p11.2", "13p12", "13p13"};
	private static final String[] CHR13Q= {"13q10", "13q11", "13q12.11", "13q12.12", "13q12.13", "13q12.2", "13q12.3", 
								           "13q13.1", "13q13.2", "13q13.3", "13q14.11", "13q14.12", "13q14.13", "13q14.2", "13q14.3", 
								           "13q21.1", "13q21.2", "13q21.31", "13q21.32", "13q21.33", "13q22.1", "13q22.2", "13q22.3", 
								           "13q31.1", "13q31.2", "13q31.3", "13q32.1", "13q32.2", "13q32.3", "13q33.1", "13q33.2", "13q33.3", "13q34"};
	private static final String[] CHR14P= {"14p10", "14p11.1", "14p11.2", "14p12", "14p13"};
	private static final String[] CHR14Q= {"14q10", "14q11.1", "14q11.2", "14q12", "14q13.1", "14q13.2", "14q13.3", 
								           "14q21.1", "14q21.2", "14q21.3", "14q22.1", "14q22.2", "14q22.3", "14q23.1", "14q23.2", "14q23.3", 
								           "14q24.1", "14q24.2", "14q24.3", "14q31.1", "14q31.2", "14q31.3", 
								           "14q32.11", "14q32.12", "14q32.13", "14q32.2", "14q32.31", "14q32.32", "14q32.33"};
	private static final String[] CHR15P= {"15p10", "15p11.1", "15p11.2", "15p12", "15p13"};
	private static final String[] CHR15Q= {"15q10", "15q11.1", "15q11.2", "15q12", "15q13.1", "15q13.2", "15q13.3", "15q14", 
								           "15q15.1", "15q15.2", "15q15.3", "15q21.1", "15q21.2", "15q21.3", "15q22.1", "15q22.2", 
								           "15q22.31", "15q22.32", "15q22.33", "15q23", "15q24.1", "15q24.2", "15q24.3", 
								           "15q25.1", "15q25.2", "15q25.3", "15q26.1", "15q26.2", "15q26.3"};
	private static final String[] CHR16P= {"16p10", "16p11.1", "16p11.2", "16p12.1", "16p12.2", "16p12.3", 
								           "16p13.11", "16p13.12", "16p13.13", "16p13.2", "16p13.3"};
	private static final String[] CHR16Q= {"16q10", "16q11.1", "16q11.2", "16q12.1", "16q12.2", "16q13", 
								           "16q21", "16q22.1", "16q22.2", "16q22.3", "16q23.1", "16q23.2", "16q23.3", "16q24.1", "16q24.2", "16q24.3"};
	private static final String[] CHR17P= {"17p10", "17p11.1", "17p11.2", "17p12", "17p13.1", "17p13.2", "17p13.3"};
	private static final String[] CHR17Q= {"17q10", "17q11.1", "17q11.2", "17q12", "17q21.1", "17q21.2", "17q21.31", "17q21.32", "17q21.33", 
								           "17q22", "17q23.1", "17q23.2", "17q23.3", "17q24.1", "17q24.2", "17q24.3", "17q25.1", "17q25.2", "17q25.3"};
	private static final String[] CHR18P= {"18p10", "18p11.1", "18p11.21", "18p11.22", "18p11.23", "18p11.31", "18p11.32"};
	private static final String[] CHR18Q= {"18q10", "18q11.1", "18q11.2", "18q12.1", "18q12.2", "18q12.3", "18q21.1", "18q21.2", 
								           "18q21.31", "18q21.32", "18q21.33", "18q22.1", "18q22.2", "18q22.3", "18q23"};
	private static final String[] CHR19P= {"19p10", "19p11", "19p12", "19p13.11", "19p13.12", "19p13.13", "19p13.2", "19p13.3"};
	private static final String[] CHR19Q= {"19q10", "19q11", "19q12", "19q13.11", "19q13.12", "19q13.13", "19q13.2", 
								           "19q13.31", "19q13.32", "19q13.33", "19q13.41", "19q13.42", "19q13.43"};
	private static final String[] CHR20P= {"20p10", "20p11.1", "20p11.21", "20p11.22", "20p11.23", "20p12.1", "20p12.2", "20p12.3", "20p13"};
	private static final String[] CHR20Q= {"20q10", "20q11.1", "20q11.21", "20q11.22", "20q11.23", "20q12", 
								           "20q13.11", "20q13.12", "20q13.13", "20q13.2", "20q13.31", "20q13.32", "20q13.33"};
	private static final String[] CHR21P= {"21p10", "21p11.1", "21p11.2", "21p12", "21p13"};
	private static final String[] CHR21Q= {"21q10", "21q11.1", "21q11.2", "21q21.1", "21q21.2", "21q21.3", 
								           "21q22.11", "21q22.12", "21q22.13", "21q22.2", "21q22.3"};
	private static final String[] CHR22P= {"22p10", "22p11.1", "22p11.2", "22p12", "22p13"};
	private static final String[] CHR22Q= {"22q10", "22q11.1", "22q11.21", "22q11.22", "22q11.23", "22q12.1", "22q12.2", "22q12.3", 
								           "22q13.1", "22q13.2", "22q13.31", "22q13.32", "22q13.33"};
	private static final String[] CHRXP = {"Xp10", "Xp11.1", "Xp11.21", "Xp11.22", "Xp11.23", "Xp11.3", "Xp11.4", 
								           "Xp21.1", "Xp21.2", "Xp21.3", "Xp22.11", "Xp22.12", "Xp22.13", "Xp22.2", 
								           "Xp22.31", "Xp22.32", "Xp22.33"};
	private static final String[] CHRXQ = {"Xq10", "Xq11.1", "Xq11.2", "Xq12", "Xq13.1", "Xq13.2", "Xq13.3", 
								           "Xq21.1", "Xq21.2", "Xq21.31", "Xq21.32", "Xq21.33", "Xq22.1", "Xq22.2", "Xq22.3", "Xq23", 
								           "Xq24", "Xq25", "Xq26.1", "Xq26.2", "Xq26.3", "Xq27.1", "Xq27.2", "Xq27.3", "Xq28"};
	private static final String[] CHRYP = {"Yp10", "Yp11.1", "Yp11.2", "Yp11.31", "Yp11.32"};
	private static final String[] CHRYQ = {"Yq10", "Yq11.1", "Yq11.21", "Yq11.221", "Yq11.222", "Yq11.223", "Yq11.23", "Yq12"};

    public static String[][] chrArmArrays = {CHR1P, CHR1Q, CHR2P, CHR2Q, CHR3P, CHR3Q, CHR4P, CHR4Q, CHR5P, CHR5Q, 
											  CHR6P, CHR6Q, CHR7P, CHR7Q, CHR8P, CHR8Q, CHR9P, CHR9Q, CHR10P, CHR10Q, 
											  CHR11P, CHR11Q, CHR12P, CHR12Q, CHR13P, CHR13Q, CHR14P, CHR14Q, CHR15P, CHR15Q, 
											  CHR16P, CHR16Q, CHR17P, CHR17Q, CHR18P, CHR18Q, CHR19P, CHR19Q, CHR20P, CHR20Q, 
											  CHR21P, CHR21Q, CHR22P, CHR22Q, CHRXP, CHRXQ, CHRYP, CHRYQ};	
    
    private static int[] getChrsOffset(String[][] chrArmArrays) {
    	int[] chBandsLength = new int[chrArmArrays.length / 2];
    	for (int i = 0; i < chrArmArrays.length / 2; i++) {
    		chBandsLength[i] = chrArmArrays[2*i].length + chrArmArrays[2*i + 1].length;    		
    	}
    	int[] chrsOffset = new int[chBandsLength.length];
    	chrsOffset[0]= 0;
        for (int i = 1; i < chrsOffset.length; i++) {
        	chrsOffset[i] = chrsOffset[i-1] + chBandsLength[i-1];    		
    	}
    	return chrsOffset;
    }
    
    private static int[] getChrPArmsLength(String[][] chrArmArrays) {
    	int[] chrPArmsLength = new int[chrArmArrays.length / 2];
    	for (int i = 0; i < chrArmArrays.length / 2; i++) {
    		chrPArmsLength[i] = chrArmArrays[2*i].length;    		
    	}
    	return chrPArmsLength;
    } 
    
    public static Map<String, List<List<Integer>>> getDecomposedLGFList(List<List<Integer>> karyotypeLGF) {
    	Map<String, List<List<Integer>>> chrLGFMap = new HashMap<>();
    	int[] chrsOffset = getChrsOffset(chrArmArrays);   	    	
    	for (int i = 1; i <= 22; i++) {
    		chrLGFMap.put(String.valueOf(i), new ArrayList<>());
    		chrLGFMap.get(String.valueOf(i)).add(karyotypeLGF.get(0).subList(chrsOffset[i-1], chrsOffset[i]));
    		chrLGFMap.get(String.valueOf(i)).add(karyotypeLGF.get(1).subList(chrsOffset[i-1], chrsOffset[i]));
    		chrLGFMap.get(String.valueOf(i)).add(karyotypeLGF.get(2).subList(chrsOffset[i-1], chrsOffset[i]));
    	}
    	chrLGFMap.put("X", new ArrayList<>());
    	chrLGFMap.get("X").add(karyotypeLGF.get(0).subList(chrsOffset[22], chrsOffset[23]));
		chrLGFMap.get("X").add(karyotypeLGF.get(1).subList(chrsOffset[22], chrsOffset[23]));
		chrLGFMap.get("X").add(karyotypeLGF.get(2).subList(chrsOffset[22], chrsOffset[23]));
    	chrLGFMap.put("Y", new ArrayList<>());
    	chrLGFMap.get("Y").add(karyotypeLGF.get(0).subList(chrsOffset[23], karyotypeLGF.get(0).size()));
		chrLGFMap.get("Y").add(karyotypeLGF.get(1).subList(chrsOffset[23], karyotypeLGF.get(1).size()));
		chrLGFMap.get("Y").add(karyotypeLGF.get(2).subList(chrsOffset[23], karyotypeLGF.get(2).size()));
		
		int[] chrPArmsLength = getChrPArmsLength(chrArmArrays);
		for (int j = 0; j < 3; j++) {
			for (int i = 1; i <= 22; i++)  {
				Collections.reverse(chrLGFMap.get(String.valueOf(i)).get(j).subList(0, chrPArmsLength[i - 1]));
			}
			Collections.reverse(chrLGFMap.get("X").get(j).subList(0, chrPArmsLength[22]));
			Collections.reverse(chrLGFMap.get("Y").get(j).subList(0, chrPArmsLength[23]));
		}		
		return chrLGFMap;
    }
    
	public static List<String> getAberrantChrs(Map<String, List<List<Integer>>> chrLGFMap) {
		String[] chrArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "X", "Y"};
		List<String> aberrantChrList = new ArrayList<>();
		for (String chr: chrArray) {
			List<List<Integer>> chrLGF = chrLGFMap.get(chr);
			inChr:
			for (int i = 0; i < chrLGF.get(0).size(); i++) {
				for (int j = 0; j < 3; j++) {
					if (chrLGF.get(j).get(i) > 0) {
						aberrantChrList.add(chr);
						break inChr;
					}
				}				
			}
		}
		return aberrantChrList;
	}   
	
	public static Map<String, List<List<Integer>>> getAberrantChrLGF(BiologicalOutcome b) {
		Map<String, List<List<Integer>>> chrLGFMap = getDecomposedLGFList(b.getKaryotypeLGF());
		List<String> aberrantChrList = getAberrantChrs(chrLGFMap);
		Map<String, List<List<Integer>>> aberrantChrLGFMap = new HashMap<>();
		for (String aberrantChr: aberrantChrList) {
			aberrantChrLGFMap.put(aberrantChr, chrLGFMap.get(aberrantChr));
		}
		return aberrantChrLGFMap;
	}
	
	// This gives p arm reversed from pter to p10
	public static List<List<Integer>> getAdjustedLGF(BiologicalOutcome b) {
		Map<String, List<List<Integer>>> chrLGFMap = getDecomposedLGFList(b.getKaryotypeLGF());
		List<List<Integer>> adjustedLGF = new ArrayList<>();
		adjustedLGF.add(new ArrayList<>());
		adjustedLGF.add(new ArrayList<>());
		adjustedLGF.add(new ArrayList<>());
		String[] chrArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "X", "Y"};
		for (String chr: chrArray) {
			List<List<Integer>> chrLGF = chrLGFMap.get(chr);
			IntStream.rangeClosed(0, 2).forEach(i -> adjustedLGF.get(i).addAll(chrLGF.get(i)));	
		}
		return adjustedLGF;
	}
	
	public static List<String> getChrBandsList() {
		List<String> chrBandsList = new ArrayList<>();
		for (String[] chrArm: ISCN2016.chrArmArrays) {
			chrBandsList.addAll(Arrays.asList(chrArm));			
		}
		return chrBandsList;
	}
	
	
}
