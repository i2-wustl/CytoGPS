package business;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import validation.DerivativeValidation;
import validation.DerivativeValidationOutcome;
import validation.DicDerivativeValidationDifferentDerChrNoBreakpoints;
import validation.DicDerivativeValidationDifferentDerChrWithBreakpoints;
import validation.DicDerivativeValidationSameDerChrSameArmWithBreakpoints;
import validation.DicDerivativeValidationSameDerChrNoBreakpoints;
import validation.IderValidation;
import validation.ValidationError;
import validation.Validator;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class ParseEvent {
	
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
	
	private static final String CHR1PTER = "1p36.33";
	private static final String CHR1QTER = "1q44";
	private static final String CHR2PTER = "2p25.3";
	private static final String CHR2QTER = "2q37.3";
	private static final String CHR3PTER = "3p26.3";
	private static final String CHR3QTER = "3q29";
	private static final String CHR4PTER = "4p16.3";
	private static final String CHR4QTER = "4q35.2";
	private static final String CHR5PTER = "5p15.33";
	private static final String CHR5QTER = "5q35.3";
	private static final String CHR6PTER = "6p25.3";
	private static final String CHR6QTER = "6q27";
	private static final String CHR7PTER = "7p22.3";
	private static final String CHR7QTER = "7q36.3";
	private static final String CHR8PTER = "8p23.3";
	private static final String CHR8QTER = "8q24.3";
	private static final String CHR9PTER = "9p24.3";
	private static final String CHR9QTER = "9q34.3";
	private static final String CHR10PTER = "10p15.3";
	private static final String CHR10QTER = "10q26.3";
	private static final String CHR11PTER = "11p15.5";
	private static final String CHR11QTER = "11q25";
	private static final String CHR12PTER = "12p13.33";
	private static final String CHR12QTER = "12q24.33";
	private static final String CHR13PTER = "13p13";
	private static final String CHR13QTER = "13q34";
	private static final String CHR14PTER = "14p13";
	private static final String CHR14QTER = "14q32.33";
	private static final String CHR15PTER = "15p13";
	private static final String CHR15QTER = "15q26.3";
	private static final String CHR16PTER = "16p13.3";
	private static final String CHR16QTER = "16q24.3";
	private static final String CHR17PTER = "17p13.3";
	private static final String CHR17QTER = "17q25.3";
	private static final String CHR18PTER = "18p11.32";
	private static final String CHR18QTER = "18q23";
	private static final String CHR19PTER = "19p13.3";
	private static final String CHR19QTER = "19q13.43";
	private static final String CHR20PTER = "20p13";
	private static final String CHR20QTER = "20q13.33";
	private static final String CHR21PTER = "21p13";
	private static final String CHR21QTER = "21q22.3";
	private static final String CHR22PTER = "22p13";
	private static final String CHR22QTER = "22q13.33";
	private static final String CHRXPTER = "Xp22.33";
	private static final String CHRXQTER = "Xq28";
	private static final String CHRYPTER = "Yp11.32";
	private static final String CHRYQTER = "Yq12";
	
	private Map<Integer, String> indexToChrMap = new HashMap<>();
	private Map<String, Integer> chrToIndexMap = new HashMap<>();
    private List<Integer> karyotypeLossOutcome;
    private List<Integer> karyotypeGainOutcome;
    private List<Integer> karyotypeFusionOutcome;
	private List<List<Integer>> initKaryotypeLGF = new ArrayList<>();

    private String[][] chrArmArrays = {CHR1P, CHR1Q, CHR2P, CHR2Q, CHR3P, CHR3Q, CHR4P, CHR4Q, CHR5P, CHR5Q, 
    		                           CHR6P, CHR6Q, CHR7P, CHR7Q, CHR8P, CHR8Q, CHR9P, CHR9Q, CHR10P, CHR10Q, 
    		                           CHR11P, CHR11Q, CHR12P, CHR12Q, CHR13P, CHR13Q, CHR14P, CHR14Q, CHR15P, CHR15Q, 
    		                           CHR16P, CHR16Q, CHR17P, CHR17Q, CHR18P, CHR18Q, CHR19P, CHR19Q, CHR20P, CHR20Q, 
    		                           CHR21P, CHR21Q, CHR22P, CHR22Q, CHRXP, CHRXQ, CHRYP, CHRYQ};
    public ParseEvent() {
    	int i = 0;
    	for (String[] chrArmArray: chrArmArrays) {
        	for(String chrBand: chrArmArray) {
        		indexToChrMap.put(i, chrBand);
        		chrToIndexMap.put(chrBand, i);
        		i++;        		        		
        	}
        }
    	karyotypeLossOutcome = new ArrayList<>(Collections.nCopies(indexToChrMap.size(), 0));
    	karyotypeGainOutcome = new ArrayList<>(karyotypeLossOutcome);
    	karyotypeFusionOutcome = new ArrayList<>(karyotypeLossOutcome);   	
    	initKaryotypeLGF.add(karyotypeLossOutcome);
    	initKaryotypeLGF.add(karyotypeGainOutcome);
    	initKaryotypeLGF.add(karyotypeFusionOutcome);
    }    
    
    public BiologicalOutcome getEventOutcome(Event e, BiologicalOutcome b) { 
    	
    	List<List<Integer>> karyotypeLGF = b.getKaryotypeLGF();
    	List<String> uncertainEventsList = b.getUncertainEventsList();
    	List<Integer> karyotypeLossOutcome = karyotypeLGF.get(0);
        List<Integer> karyotypeGainOutcome = karyotypeLGF.get(1);
        List<Integer> karyotypeFusionOutcome = karyotypeLGF.get(2);
    	List<List<String>> breakpointsFullName = new ArrayList<>();
    	
    	if (e.isUncertainEvent()) {
			uncertainEventsList.add(e.getEventCode());
		} else {
			
			// Process -
	    	// Using reflection to improve performance
	    	for (String lossChr: e.getLossChrs()) {
	    		String[] chrPArmBreakpoints = doReflection(lossChr, "P");
	    		String[] chrQArmBreakpoints = doReflection(lossChr, "Q");
	    		Stream.of(chrPArmBreakpoints)
  		      		  .forEach(breakpoint -> {
							    		  		int index = chrToIndexMap.get(breakpoint); 
							    		  		karyotypeLossOutcome.set(index, karyotypeLossOutcome.get(index) + 1);
						    			 	 });
	    		Stream.of(chrQArmBreakpoints)
	      		  	  .forEach(breakpoint -> {
		    		  							int index = chrToIndexMap.get(breakpoint); 
		    		  							karyotypeLossOutcome.set(index, karyotypeLossOutcome.get(index) + 1);
					    			     	 });
	    	}
	    	
	    	// Process +
	    	// Using reflection to improve performance
	    	for (String gainChr: e.getGainChrs()) {
	    		String[] chrPArmBreakpoints = doReflection(gainChr, "P");
	    		String[] chrQArmBreakpoints = doReflection(gainChr, "Q");
	    		Stream.of(chrPArmBreakpoints)
	    		      .forEach(breakpoint -> {
								    		  	int index = chrToIndexMap.get(breakpoint); 
								    			karyotypeGainOutcome.set(index, karyotypeGainOutcome.get(index) + 1);
							    			 });
	    		Stream.of(chrQArmBreakpoints)
  		      		  .forEach(breakpoint -> {
			    		  						int index = chrToIndexMap.get(breakpoint); 
			    		  						karyotypeGainOutcome.set(index, karyotypeGainOutcome.get(index) + 1);
						    			     });
	    	}
    	
			if (!e.getNature().isEmpty()) {
				
    			if (!(e instanceof DerEvent)) {
                	breakpointsFullName = e.getBreakpointsFullName(e.getChrList(), e.getBreakpoints());  		
            	} else {
            		breakpointsFullName = null;
            	}     			
    	
            	switch (e.getNature()) {
            	
	            	case "add": {
	            		processLossFusionEvent(karyotypeLossOutcome, karyotypeFusionOutcome, breakpointsFullName);
	            		break;
	            	}
	            	case "del": {
	            		processDel(karyotypeLossOutcome, karyotypeFusionOutcome, breakpointsFullName);
	            		break;
	            	}
	            	case "dic": {
	            		processLossFusionEvent(karyotypeLossOutcome, karyotypeFusionOutcome, breakpointsFullName);
	            		break;
	            	}
	            	case "idic": {
	            		processIdic(karyotypeLossOutcome, karyotypeGainOutcome, karyotypeFusionOutcome, breakpointsFullName);
	            		break;
	            	}
	            	case "dup": {
	            		processDup(karyotypeGainOutcome, karyotypeFusionOutcome, breakpointsFullName);
	            		break;
	            	}
	            	case "trp": {
	            		processTrp(karyotypeGainOutcome, karyotypeFusionOutcome, breakpointsFullName);
	            		break;
	            	}
	            	case "qdp": {
	            		processQdp(karyotypeGainOutcome, karyotypeFusionOutcome, breakpointsFullName);
	            		break;
	            	}
	            	case "hsr": {
	            		processFusionEvent(karyotypeFusionOutcome, breakpointsFullName);
	            		processFusionEvent(karyotypeFusionOutcome, breakpointsFullName);
	            		break;
	            	}
	            	case "ins": {
	            		processFusionEvent(karyotypeFusionOutcome, breakpointsFullName);
	                	if (breakpointsFullName.size() == 1 && breakpointsFullName.get(0).get(1).equals(breakpointsFullName.get(0).get(2))) {
	                		markBreakpointToFusionOutcome(karyotypeFusionOutcome, breakpointsFullName.get(0).get(1));
	                		markBreakpointToFusionOutcome(karyotypeFusionOutcome, breakpointsFullName.get(0).get(0));
	                	} else if (breakpointsFullName.size() == 2 && breakpointsFullName.get(1).get(0).equals(breakpointsFullName.get(1).get(1))) {
	                		markBreakpointToFusionOutcome(karyotypeFusionOutcome, breakpointsFullName.get(0).get(0));
	                		markBreakpointToFusionOutcome(karyotypeFusionOutcome, breakpointsFullName.get(1).get(0));
	                	} else {
	                		processFusionEvent(karyotypeFusionOutcome, breakpointsFullName);
	                	}
	            		break;
	            	}
	            	case "inv": {
	            		processFusionEvent(karyotypeFusionOutcome, breakpointsFullName);
	            		processFusionEvent(karyotypeFusionOutcome, breakpointsFullName);
	            		break;
	            	}
	            	case "i": {
	            		processI(karyotypeLossOutcome, karyotypeGainOutcome, karyotypeFusionOutcome, breakpointsFullName);
	            		break;
	            	}
	            	case "r": {
	            		processLossFusionEvent(karyotypeLossOutcome, karyotypeFusionOutcome, breakpointsFullName);
	            		break;
	            	}
	            	case "tas": {
	            		processFusionEvent(karyotypeFusionOutcome, breakpointsFullName);
	            		break;
	            	}
	            	case "t": {
	            		processT(karyotypeFusionOutcome, breakpointsFullName); 
	            		break;
	            	}
	            	case "rob": {
	            		processFusionOppositeArmLossEvent(karyotypeLossOutcome, karyotypeFusionOutcome, breakpointsFullName); 
	            		break;
	            	}
	            	case "trc": {
	            		processLossFusionEvent(karyotypeLossOutcome, karyotypeFusionOutcome, breakpointsFullName);
	            		break;
	            	}
	            	case "ider": {
    					DerivativeValidationOutcome d = new IderValidation((DerEvent)e).getIderValidationOutcome();
	            		List<List<Integer>> derKaryotypeLGF = d.getDerKaryotypeLGF();
    					updateLoss(karyotypeLossOutcome, derKaryotypeLGF);
    					updateGain(karyotypeGainOutcome, derKaryotypeLGF);
    					updateFusion(karyotypeFusionOutcome, derKaryotypeLGF);
    					b.getDetailedSystem().add(d.getDetailedSystem());            		
	            		break;
	            	}
        			case "der": {
        				
        				List<String> chrList = e.getChrList();
    					if (chrList.size() == 1) {
    						
        					DerivativeValidationOutcome d = new DerivativeValidation((DerEvent)e).getDerivativeValidationOutcome();	
        					List<List<Integer>> derKaryotypeLGF = d.getDerKaryotypeLGF();
        					updateLoss(karyotypeLossOutcome, derKaryotypeLGF);
        					updateGain(karyotypeGainOutcome, derKaryotypeLGF);
        					updateFusion(karyotypeFusionOutcome, derKaryotypeLGF);
        					b.getDetailedSystem().add(d.getDetailedSystem());                            
        					
        				} else {		
        					
        					DerivativeValidationOutcome d;
        					if (e.getBreakpoints().size() == 0) {
        						if (!chrList.get(0).equals(chrList.get(1))) {
        							d = new DicDerivativeValidationDifferentDerChrNoBreakpoints((DerEvent)e).getDicDerivativeValidationOutcome();	
        						} else {
        							d = new DicDerivativeValidationSameDerChrNoBreakpoints((DerEvent)e).getDicDerivativeValidationOutcome();
        						}        						
        					} else {
        						if (!chrList.get(0).equals(chrList.get(1))) {
        							d = new DicDerivativeValidationDifferentDerChrWithBreakpoints((DerEvent)e).getDicDerivativeValidationOutcome();
        						} else {
        							List<List<String>> derCens = e.getBreakpointsFullName(chrList, e.getBreakpoints());
        							if (!getChrArm(derCens.get(0).get(0)).equals(getChrArm(derCens.get(1).get(0)))) {
        								d = new DicDerivativeValidationDifferentDerChrWithBreakpoints((DerEvent)e).getDicDerivativeValidationOutcome();
        							} else {
        								d = new DicDerivativeValidationSameDerChrSameArmWithBreakpoints((DerEvent)e).getDicDerivativeValidationOutcome();
        							}
        						}    							        						
        					}
        					List<List<Integer>> derKaryotypeLGF = d.getDerKaryotypeLGF();
        					updateLoss(karyotypeLossOutcome, derKaryotypeLGF);
        					updateGain(karyotypeGainOutcome, derKaryotypeLGF);
        					updateFusion(karyotypeFusionOutcome, derKaryotypeLGF);
        					b.getDetailedSystem().add(d.getDetailedSystem());                                    
        					
        				} // End of else (e.getChrList().size() == 1), which implies e.getChrList().size() == 2     				        				         				
        				// Due to our validation, we don't consider tricentric derived chromosomes. In general, is it possible to have a derivative chromosome with more than two derived chromosomes? 
        				break;
        			} // End of case "der"
            	} // End of big switch
            	
//            	cancelOutLossGain(karyotypeLGF);
    			
    		} // End of if (!e.getNature().isEmpty())
        	
    	} // End of else (e.isUncertainEvent())
    	
    	return b;
        	
    }
    
    private void recordLoss(List<Integer> karyotypeLossOutcome, int index) {
		karyotypeLossOutcome.set(index, karyotypeLossOutcome.get(index) + 1);
    }
    
    private void recordGain(List<Integer> karyotypeGainOutcome, int index) {
    	karyotypeGainOutcome.set(index, karyotypeGainOutcome.get(index) + 1);
    }
    
    private void recordGain2(List<Integer> karyotypeGainOutcome, int index) {
    	karyotypeGainOutcome.set(index, karyotypeGainOutcome.get(index) + 2);
    }
    
    private void recordGain3(List<Integer> karyotypeGainOutcome, int index) {
    	karyotypeGainOutcome.set(index, karyotypeGainOutcome.get(index) + 3);
    }
    
    private void recordFusion(List<Integer> karyotypeFusionOutcome, int index) {
		karyotypeFusionOutcome.set(index, karyotypeFusionOutcome.get(index) + 1);
    }
    
    private void processLossFusionEvent(List<Integer> karyotypeLossOutcome, List<Integer> karyotypeFusionOutcome, List<List<String>> breakpointsFullName) {    	
		int beginLossIndex, endLossIndex;		
		for (List<String> chrBreakpoints: breakpointsFullName) {
			for (String chrBreakpoint: chrBreakpoints) {
				markBreakpointToFusionOutcome(karyotypeFusionOutcome, chrBreakpoint);	
				String ter = getChrTer(getChrArm(chrBreakpoint));
				beginLossIndex = getBeginIndex(chrBreakpoint);				
				endLossIndex = chrToIndexMap.get(ter);
				for (int i = beginLossIndex; i <= endLossIndex; i++) {
					recordLoss(karyotypeLossOutcome, i);
				}
			}
		}
    } 
    
    private void markBreakpointToFusionOutcome(List<Integer> karyotypeFusionOutcome, String chrBreakpoint) {
    	int index;
		if (chrToIndexMap.get(chrBreakpoint) == null) {
			for (String subband: getAllKeys(chrBreakpoint)) {
				index = chrToIndexMap.get(subband);
				recordFusion(karyotypeFusionOutcome, index);								
			}					
		} else {
			index = chrToIndexMap.get(chrBreakpoint);
			recordFusion(karyotypeFusionOutcome, index);
		}
    }
    
    private void processDel(List<Integer> karyotypeLossOutcome, List<Integer> karyotypeFusionOutcome, List<List<String>> breakpointsFullName) {
    	List<String> chrBreakpoints = breakpointsFullName.get(0); 
		if (chrBreakpoints.size() == 2) {
			processFusionEvent(karyotypeFusionOutcome, breakpointsFullName);
			String beginBand = chrBreakpoints.get(0);
			String endBand = chrBreakpoints.get(1);
			int beginLossIndex = getBeginIndex(beginBand);
			int endLossIndex = getEndIndex(endBand);
			for (int i = beginLossIndex; i <= endLossIndex; i++) {
				recordLoss(karyotypeLossOutcome, i);
			}
		} else {
			String chrBreakpoint = chrBreakpoints.get(0);
			String ter = getChrTer(getChrArm(chrBreakpoint));	
			int beginIndex = getBeginIndex(chrBreakpoint);			
			int endIndex = chrToIndexMap.get(ter);
			for (int i = beginIndex; i <= endIndex; i++) {
				recordLoss(karyotypeLossOutcome, i);
			}
		}
    }   
    
    private void processFusionEvent(List<Integer> karyotypeFusionOutcome, List<List<String>> breakpointsFullName) {
    	for (List<String> chrBreakpoints: breakpointsFullName) {
			for (String chrBreakpoint: chrBreakpoints) {
				markBreakpointToFusionOutcome(karyotypeFusionOutcome, chrBreakpoint);
			}
		}
    }
    
    private void processIdic(List<Integer> karyotypeLossOutcome, List<Integer> karyotypeGainOutcome, List<Integer> karyotypeFusionOutcome, List<List<String>> breakpointsFullName) {    	
		String chrBreakpoint = breakpointsFullName.get(0).get(0);
		markBreakpointToFusionOutcome(karyotypeFusionOutcome, chrBreakpoint);
		markBreakpointToFusionOutcome(karyotypeFusionOutcome, chrBreakpoint);
		String ter = getChrTer(getChrArm(chrBreakpoint));
		int endLossIndex = chrToIndexMap.get(ter);
		int beginLossIndex = getBeginIndex(chrBreakpoint);		
		for (int i = beginLossIndex; i <= endLossIndex; i++) {
			recordLoss(karyotypeLossOutcome, i);
		}	
		String cen = getChrArm(chrBreakpoint) + "10";
		int beginGainIndex = chrToIndexMap.get(cen);
		int endGainIndex = getEndIndex(chrBreakpoint);
		for (int i = beginGainIndex; i <= endGainIndex; i++) {
			recordGain(karyotypeGainOutcome, i);
		}
		String oppChrArm = getOppositeChrArm(getChrArm(chrBreakpoint));
		String oppCen = oppChrArm + "10";
		String oppTer = getChrTer(oppChrArm);
		for (int i = chrToIndexMap.get(oppCen); i <= chrToIndexMap.get(oppTer); i++) {
			recordGain(karyotypeGainOutcome, i);
		}
    }
    
    private void processDup(List<Integer> karyotypeGainOutcome, List<Integer> karyotypeFusionOutcome, List<List<String>> breakpointsFullName) {
    	processFusionEvent(karyotypeFusionOutcome, breakpointsFullName);
    	List<String> chrBreakpoints = breakpointsFullName.get(0);	
    	String chrBreakpoint0 = chrBreakpoints.get(0);
    	String chrBreakpoint1 = chrBreakpoints.get(1);
    	if (chrBreakpoint0.equals(chrBreakpoint1)) {
    		markBreakpointToFusionOutcome(karyotypeFusionOutcome, chrBreakpoint0);
    	}
    	if (chrBreakpoint0.compareTo(chrBreakpoint1) > 0) {
    		markBreakpointToFusionOutcome(karyotypeFusionOutcome, chrBreakpoint0);
    		markBreakpointToFusionOutcome(karyotypeFusionOutcome, chrBreakpoint0);
    	}
    	
		String beginBand = getMin(chrBreakpoints);
		String endBand = getMax(chrBreakpoints);							
		int beginGainIndex = getBeginIndex(beginBand);			
		int	endGainIndex = getEndIndex(endBand);		
		for (int i = beginGainIndex; i <= endGainIndex; i++) {
			recordGain(karyotypeGainOutcome, i);
		}
    }
    
    private void processTrp(List<Integer> karyotypeGainOutcome, List<Integer> karyotypeFusionOutcome, List<List<String>> breakpointsFullName) {
    	processFusionEvent(karyotypeFusionOutcome, breakpointsFullName);
    	processFusionEvent(karyotypeFusionOutcome, breakpointsFullName);
    	List<String> chrBreakpoints = breakpointsFullName.get(0);	
    	
		String beginBand = getMin(chrBreakpoints);
		String endBand = getMax(chrBreakpoints);							
		int beginGainIndex = getBeginIndex(beginBand);			
		int	endGainIndex = getEndIndex(endBand);		
		for (int i = beginGainIndex; i <= endGainIndex; i++) {
			recordGain2(karyotypeGainOutcome, i);
		}
    }
    
    private void processQdp(List<Integer> karyotypeGainOutcome, List<Integer> karyotypeFusionOutcome, List<List<String>> breakpointsFullName) {
    	processFusionEvent(karyotypeFusionOutcome, breakpointsFullName);
    	processFusionEvent(karyotypeFusionOutcome, breakpointsFullName);
    	List<String> chrBreakpoints = breakpointsFullName.get(0);	
    	if (chrBreakpoints.get(0).equals(chrBreakpoints.get(1))) {
    		markBreakpointToFusionOutcome(karyotypeFusionOutcome, chrBreakpoints.get(0));
    	} else {
    		processFusionEvent(karyotypeFusionOutcome, breakpointsFullName);
    	}
    	
		String beginBand = getMin(chrBreakpoints);
		String endBand = getMax(chrBreakpoints);							
		int beginGainIndex = getBeginIndex(beginBand);			
		int	endGainIndex = getEndIndex(endBand);		
		for (int i = beginGainIndex; i <= endGainIndex; i++) {
			recordGain3(karyotypeGainOutcome, i);
		}
    }
    
    private void processI(List<Integer> karyotypeLossOutcome, List<Integer> karyotypeGainOutcome, List<Integer> karyotypeFusionOutcome, List<List<String>> breakpointsFullName) {
    	String cen = breakpointsFullName.get(0).get(0);
		String ter = getChrTer(getChrArm(cen));	
		for (int i = chrToIndexMap.get(cen); i <= chrToIndexMap.get(ter); i++) {
			recordGain(karyotypeGainOutcome, i);
		}
		processOppositeArmLoss(karyotypeLossOutcome, cen);
		recordFusion(karyotypeFusionOutcome, chrToIndexMap.get(cen));
		recordFusion(karyotypeFusionOutcome, chrToIndexMap.get(cen));
    }
    
    private void processOppositeArmLoss(List<Integer> karyotypeLossOutcome, String cen) {
    	String oppChrArm = getOppositeChrArm(getChrArm(cen));
		String oppCen = oppChrArm + "10";
		String oppTer = getChrTer(oppChrArm);
		for (int i = chrToIndexMap.get(oppCen); i <= chrToIndexMap.get(oppTer); i++) {
			recordLoss(karyotypeLossOutcome, i);
		}
    }
    
    private void processT(List<Integer> karyotypeFusionOutcome, List<List<String>> breakpointsFullName) {
    	for (List<String> chrBreakpoints: breakpointsFullName) {
			for (String chrBreakpoint: chrBreakpoints) {
				markBreakpointToFusionOutcome(karyotypeFusionOutcome, chrBreakpoint);
				if (chrBreakpoint.endsWith("10")) {
					String oppChrArm = getOppositeChrArm(getChrArm(chrBreakpoint));
					String oppCen = oppChrArm + "10";
					int extraIndex = chrToIndexMap.get(oppCen);
					recordFusion(karyotypeFusionOutcome, extraIndex);
				} else {
					markBreakpointToFusionOutcome(karyotypeFusionOutcome, chrBreakpoint);
				}				
			}
		}
    } 
    
    private void processFusionOppositeArmLossEvent(List<Integer> karyotypeLossOutcome, List<Integer> karyotypeFusionOutcome, List<List<String>> breakpointsFullName) {
    	for (List<String> chrBreakpoints: breakpointsFullName) {
			String cen = chrBreakpoints.get(0);
			processOppositeArmLoss(karyotypeLossOutcome, cen);
			recordFusion(karyotypeFusionOutcome, chrToIndexMap.get(cen));				
		}
    }
    
    private void updateLoss(List<Integer> karyotypeLossOutcome, List<List<Integer>> derKaryotypeLGF) {
    	IntStream.range(0, derKaryotypeLGF.get(0).size())
    	         .filter(i->derKaryotypeLGF.get(0).get(i)>0)
    	         .forEach(i->karyotypeLossOutcome.set(i, derKaryotypeLGF.get(0).get(i) + karyotypeLossOutcome.get(i)));
    }
    
    private void updateGain(List<Integer> karyotypeGainOutcome, List<List<Integer>> derKaryotypeLGF) {
    	IntStream.range(0, derKaryotypeLGF.get(1).size())
    	         .filter(i -> derKaryotypeLGF.get(1).get(i)>0)
    	         .forEach(i -> karyotypeGainOutcome.set(i, derKaryotypeLGF.get(1).get(i) + karyotypeGainOutcome.get(i)));
    }
    
    private void updateFusion(List<Integer> karyotypeFusionOutcome, List<List<Integer>> derKaryotypeLGF) {
    	IntStream.range(0, derKaryotypeLGF.get(2).size())
    	         .filter(i -> derKaryotypeLGF.get(2).get(i)>0)
    	         .forEach(i -> karyotypeFusionOutcome.set(i, derKaryotypeLGF.get(2).get(i) + karyotypeFusionOutcome.get(i)));
    }

	public Map<Integer, String> getIndexToChrMap() {
		return indexToChrMap;
	}

	public Map<String, Integer> getChrToIndexMap() {
		return chrToIndexMap;
	}

	public static String getChrTer(String chrArm) {
		String ter = "";
		switch(chrArm) {
			case "1p": ter = CHR1PTER; break;
			case "1q": ter = CHR1QTER; break;
			case "2p": ter = CHR2PTER; break;
			case "2q": ter = CHR2QTER; break;
			case "3p": ter = CHR3PTER; break;
			case "3q": ter = CHR3QTER; break;
			case "4p": ter = CHR4PTER; break;
			case "4q": ter = CHR4QTER; break;
			case "5p": ter = CHR5PTER; break;
			case "5q": ter = CHR5QTER; break;
			case "6p": ter = CHR6PTER; break;
			case "6q": ter = CHR6QTER; break;
			case "7p": ter = CHR7PTER; break;
			case "7q": ter = CHR7QTER; break;
			case "8p": ter = CHR8PTER; break;
			case "8q": ter = CHR8QTER; break;
			case "9p": ter = CHR9PTER; break;
			case "9q": ter = CHR9QTER; break;
			case "10p":ter = CHR10PTER;break;
			case "10q":ter = CHR10QTER;break;
			case "11p":ter = CHR11PTER;break;
			case "11q":ter = CHR11QTER;break;
			case "12p":ter = CHR12PTER;break;
			case "12q":ter = CHR12QTER;break;
			case "13p":ter = CHR13PTER;break;
			case "13q":ter = CHR13QTER;break;
			case "14p":ter = CHR14PTER;break;
			case "14q":ter = CHR14QTER;break;
			case "15p":ter = CHR15PTER;break;
			case "15q":ter = CHR15QTER;break;
			case "16p":ter = CHR16PTER;break;
			case "16q":ter = CHR16QTER;break;
			case "17p":ter = CHR17PTER;break;
			case "17q":ter = CHR17QTER;break;
			case "18p":ter = CHR18PTER;break;
			case "18q":ter = CHR18QTER;break;
			case "19p":ter = CHR19PTER;break;
			case "19q":ter = CHR19QTER;break;
			case "20p":ter = CHR20PTER;break;
			case "20q":ter = CHR20QTER;break;
			case "21p":ter = CHR21PTER;break;
			case "21q":ter = CHR21QTER;break;
			case "22p":ter = CHR22PTER;break;
			case "22q":ter = CHR22QTER;break;
			case "Xp": ter = CHRXPTER; break;
			case "Xq": ter = CHRXQTER; break;
			case "Yp": ter = CHRYPTER; break;
			case "Yq": ter = CHRYQTER; break;
		}
		return ter;
	}
	
	public List<String> getAllKeys(String particalKey) {
		Set<String> allKeys = chrToIndexMap.keySet();
		List<String> targetedKeys = new ArrayList<>();
		for (String s: allKeys) {
			if (s.startsWith(particalKey)) {
				targetedKeys.add(s);
			}
		}
		Collections.sort(targetedKeys);
		return targetedKeys;
	}
	
	public String getChr(String chrBreakpoint) {
		String chr;
		if (chrBreakpoint.contains("p")) {
			chr = chrBreakpoint.substring(0, chrBreakpoint.indexOf('p'));				
		} else {
			chr = chrBreakpoint.substring(0, chrBreakpoint.indexOf('q'));							
		}
		return chr;
	}
	
	public String getChrArm(String chrBreakpoint) {
		String chrArm;
		if (chrBreakpoint.contains("p")) {
			chrArm = chrBreakpoint.substring(0, chrBreakpoint.indexOf('p') + 1);				
		} else {
			chrArm = chrBreakpoint.substring(0, chrBreakpoint.indexOf('q') + 1);							
		}
		return chrArm;
	}
	
	public String getBreakpoint(String chrBreakpoint) {
		String breakpoint;
		if (chrBreakpoint.contains("p")) {
			breakpoint = chrBreakpoint.substring(chrBreakpoint.indexOf('p'));				
		} else {
			breakpoint = chrBreakpoint.substring(chrBreakpoint.indexOf('q'));							
		}
		return breakpoint;
	}

	public String getOppositeChrArm(String chrArm) {
		String oppositeChrArm;
		if (chrArm.contains("p")) {
			oppositeChrArm = chrArm.substring(0, chrArm.indexOf('p')) + "q";
		} else {
			oppositeChrArm = chrArm.substring(0, chrArm.indexOf('q')) + "p";
		}
		return oppositeChrArm;
	}
	
	public String getLastElement(List<String> list) {
		return list.get(list.size() - 1);
	}
	
	public boolean distinctValues(List<String> list) {
		Set<String> foundStrings = new HashSet<>();
		for (String s : list) {
	        if(foundStrings.contains(s)){
	            return false;
	        }
	        foundStrings.add(s);
	    }              
	    return true;
	}
	
	public int getIndex(List<String> list, String s) {
		return list.indexOf(s);
	}

    public String[] doReflection(String chr, String arm) {
    	ParseEvent parseEvent = new ParseEvent();
		try {
			Field privateArrayField = parseEvent.getClass().getDeclaredField("CHR" + chr + arm);
			privateArrayField.setAccessible(true);
			String[] chrBreakpoints = (String[])privateArrayField.get(parseEvent);
			return chrBreakpoints;
		} catch (Exception ex) {
			return null;
		}
    }
    
    private int getBeginIndex(String chrBreakpoint) {
    	int beginIndex;
    	if (chrToIndexMap.get(chrBreakpoint) == null) {					
    		beginIndex = chrToIndexMap.get(getAllKeys(chrBreakpoint).get(0));							
		} else {
			beginIndex = chrToIndexMap.get(chrBreakpoint);					
		}
    	return beginIndex;
    }
    
    private int getEndIndex(String chrBreakpoint) {
    	int endIndex;
    	if (chrToIndexMap.get(chrBreakpoint) == null) {					
    		endIndex = chrToIndexMap.get(getLastElement(getAllKeys(chrBreakpoint)));							
		} else {
			endIndex = chrToIndexMap.get(chrBreakpoint);					
		}
    	return endIndex;
    }
    
    public String getMin(List<String> twoStrings) {
    	if (twoStrings.get(0).compareTo(twoStrings.get(1)) <= 0) {
    		return twoStrings.get(0);
    	} else {
    		return twoStrings.get(1);
    	}
    }
    
    public String getMax(List<String> twoStrings) {
    	if (twoStrings.get(0).compareTo(twoStrings.get(1)) <= 0) {
    		return twoStrings.get(1);
    	} else {
    		return twoStrings.get(0);
    	}
    }
    

    


    


    

    

    


    

    

 
    

    

    
    private void processEventWithoutBreakpoints(Event event, List<Event> accumulatedCompleteEventsList) {
    	if (event.getBreakpoints().size() > 0) {
			accumulatedCompleteEventsList.add(event);
		} else if (accumulatedCompleteEventsList.contains(event)) {
			int index = accumulatedCompleteEventsList.indexOf(event);
			Event sameEvent = accumulatedCompleteEventsList.get(index);
			event.setBreakpoints(sameEvent.getBreakpoints());
			event.setEventCode(event.getCompleteEventCode(event.getBreakpoints())); 
			// Update Uncertainty if necessary
			String eventCode = event.getEventCode();
			int breakpointsLeftParenthesis = eventCode.indexOf(")") + 1;
			int breakpointsRightParenthesis = eventCode.indexOf(")", breakpointsLeftParenthesis);
			String breakpointsString = eventCode.substring(breakpointsLeftParenthesis + 1, breakpointsRightParenthesis);
			if (!event.isUncertainEvent() && (breakpointsString.contains("?") || breakpointsString.contains("-") || breakpointsString.contains("~") || breakpointsString.contains("or"))) {
				event.setUncertainEvent(true);
			}					
		} else if (event.getNature().equals("r") && !event.isUncertainEvent() && ValidationError.isValidChrList(event.getChrList())) {
			for (String chr: event.getChrList()) {
    			List<String> breakpoints = new ArrayList<>();
    			breakpoints.add(getBreakpoint(getChrTer(chr + "p")));
    			breakpoints.add(getBreakpoint(getChrTer(chr + "q")));
    			event.getBreakpoints().add(breakpoints);
    		}
		} else {
			event.setUncertainEvent(true);
		}
    }
    
    private void processSubeventWithoutBreakpoints(Event subevent, List<Event> accumulatedCompleteEventsList) {
    	if (subevent.getBreakpoints().size() > 0) {
			accumulatedCompleteEventsList.add(subevent);
		} else if (accumulatedCompleteEventsList.contains(subevent)) {
			int index = accumulatedCompleteEventsList.indexOf(subevent);
			Event sameEvent = accumulatedCompleteEventsList.get(index);
			subevent.setBreakpoints(sameEvent.getBreakpoints());
			subevent.setEventCode(subevent.getCompleteEventCode(subevent.getBreakpoints()));
			// Update Uncertainty if necessary
			String subeventCode = subevent.getEventCode();
			int breakpointsLeftParenthesis = subeventCode.indexOf(")") + 1;
			int breakpointsRightParenthesis = subeventCode.indexOf(")", breakpointsLeftParenthesis);
			String breakpointsString = subeventCode.substring(breakpointsLeftParenthesis + 1, breakpointsRightParenthesis);
			if (!subevent.isUncertainEvent() && (breakpointsString.contains("?") || breakpointsString.contains("-") || breakpointsString.contains("~") || breakpointsString.contains("or"))) {
				subevent.setUncertainEvent(true);
			}  					
		} else if (subevent.getNature().equals("r") && subevent.getChrList().size() == 1 && ValidationError.isValidChr(subevent.getChrList().get(0)) && !subevent.isUncertainEvent()) {
			for (String chr: subevent.getChrList()) {
    			List<String> breakpoints = new ArrayList<>();
    			breakpoints.add(getBreakpoint(getChrTer(chr + "p")));
    			breakpoints.add(getBreakpoint(getChrTer(chr + "q")));
    			subevent.getBreakpoints().add(breakpoints);
    		}
		} else {
			subevent.setUncertainEvent(true);
		}
    }
    
    
    private boolean isUncerntainDer(DerEvent d) {
    	for (Event e: d.getSubevents()) {
    		if (e.isUncertainEvent()) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public void processMissingBreakpoints(List<Clone> rowClones) {
    	List<Event> accumulatedCompleteEventsList = new ArrayList<>();
    	for (Clone clone: rowClones) { 
//    		List<Integer> indexListForEventsWaitingForChangeUncertainty = new ArrayList<>();
    		for (Event e: clone.getCloneInput()) {
//    			Event e = karyotype.get(index);
    			if (e instanceof DerEvent) {    			
        			for (Event subevent: ((DerEvent)e).getSubevents()) {
        				processSubeventWithoutBreakpoints(subevent, accumulatedCompleteEventsList);
        			}
//        			markDerEventWithIncompleteInfo(karyotype, (DerEvent)e, index, indexListForEventsWaitingForChangeUncertainty);
        			// The order matters here: For example, 85,XXYY,-1,t(1;19),-2,-3,-4,del(4)(q23),-5,del(5)(p13),del(6)(q15),-7,+8,+8,+del(8)(p21),-9,-10,-12,dup(14)(q13q32)x2,-16,-17,-18,der(19)t(1;19),+20,+21,+21,-22,-22,+mar
        			// In order to put der(19)t(1;19) to the undecoded event list, which is what we want, we need to use this order. O/w, the code will only throw a second t(1;19) to the undecoded event list, if we flip the order.
        		} else if (!e.getNature().isEmpty()) {
        			processEventWithoutBreakpoints(e, accumulatedCompleteEventsList);
        		} else {
        			// do nothing
        		} 
    		}
//    		for (int index: indexListForEventsWaitingForChangeUncertainty) {
//    			karyotype.get(index).setUncertainEvent(true);
//    		}
    	}    	
    }
    
    public void markUncertainDerEvent(List<Clone> rowClones) {
    	for (Clone clone: rowClones) {
    		List<Integer> indexListForEventsWaitingForChangeUncertainty = new ArrayList<>();
    		List<Event> karyotype = clone.getCloneInput();
    		for (int index = 0; index < karyotype.size(); index++) {
    			Event e = karyotype.get(index);
    			if (e instanceof DerEvent) {
    				markDerEventWithIncompleteInfo(karyotype, (DerEvent)e, index, indexListForEventsWaitingForChangeUncertainty);
    			}
    		}
    		for (int index: indexListForEventsWaitingForChangeUncertainty) {
    			karyotype.get(index).setUncertainEvent(true);
    		}
    	}
    }
    
    private void markDerEventWithIncompleteInfo(List<Event> eList, DerEvent event, int index, List<Integer> indexListForEventsWaitingForChangeUncertainty) {   
    	
    	boolean waitingForChangeUncertainty = false;
    	
    	if (!event.isUncertainEvent()) {
    		
    		if (isUncerntainDer(event)) {
    			waitingForChangeUncertainty = true;
    		} 
    		
    		// For those events which are waiting for change uncertainty, we need to process their lossChr or gainChr events, if any.
        	// For example, 46,XX,+der(1)r(1;?)(p36.1q23;?). We cannot not decode der(1)r(1;?)(p36.1q23;?), but at least we can parse +1. 
        	// Note that since we have processed the lossChr or gainChr events of these derivative chromosomes, we should remove the "+" or "-" sign from the eventcode when lisiting the undecoded events.
    		if (waitingForChangeUncertainty) {
    			
    			indexListForEventsWaitingForChangeUncertainty.add(index);
    			// We will take out chrList when "+" or "-" is before a der event, only if this der passes validation test.
//    			if (Validator.isValidEvent(event)) {
    				
    				if (event.getLossChrs().size() > 0) {
            			// Add a new lossChr event    	    	    			
            			Event e = new Event();
            			e.getLossChrs().addAll(event.getLossChrs());        	    	
            	    	eList.add(e);    	    	
            	    	// Delete the minus sign from the event code
            	    	event.setEventCode(event.getEventCode().substring(1));    	    	
            		}        		
            		if (event.getGainChrs().size() > 0) {
            			//  Add a new gainChr event
            			Event e = new Event();
            			e.getGainChrs().addAll(event.getGainChrs());        	    	
            	    	eList.add(e);    	    	
            	    	// Delete the plus sign from the event code
            	    	event.setEventCode(event.getEventCode().substring(1)); 
            		}
    				
//    			}
    			
    		} // End of if (waitingForChangeUncertainty)   		
    		
    	} // End of big if
    }
    
    public BiologicalOutcome getKaryotypeOutcome(List<Event> eList) {
    	List<String> uncertainEventsList = new ArrayList<>();
    	List<String> derDetailedSystem = new ArrayList<>();
    	BiologicalOutcome b = new BiologicalOutcome(initKaryotypeLGF, uncertainEventsList, derDetailedSystem);
        for (Event e: eList) {
        	for (int i = 1; i <= e.getCopies(); i++) {
        		b = getEventOutcome(e, b);
        		if (e.isUncertainEvent()) {
            		break; // If e is uncertain, I don't want to print undecoded event multiple times.
            	} 
    		}
        }        
        return b;
    }
    
    public List<BiologicalOutcome> getMultipleCloneRowOutcome(List<Clone> rowClones) {
    	List<BiologicalOutcome> multipleCloneRowOutcome = new ArrayList<>();
    	for (Clone clone: rowClones) {    		
    		BiologicalOutcome b = new ParseEvent().getKaryotypeOutcome(clone.getCloneInput());
    		multipleCloneRowOutcome.add(b);
    	}
    	return multipleCloneRowOutcome;
    }

    
}
