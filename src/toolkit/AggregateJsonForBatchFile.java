package toolkit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.List;

import javax.json.*;

import business.BiologicalOutcome;
import business.FinalResult;
import main.KaryotypeRunner;
import main.TokenError;
import toolkit.ISCN2016;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class AggregateJsonForBatchFile {
	
	public static void generateJson(Path fileReadPath, Path fileWritePath) throws Exception {
    	FileReader fr = null;
        BufferedReader br = null;
        try { 	
        	fr = new FileReader(fileReadPath.toFile()); 
            br = new BufferedReader(fr);
            
    		JsonArrayBuilder cytogpsOutputArrayBuilder = Json.createArrayBuilder();
            
        	String s;
        	while ((s = br.readLine()) != null) {	
        		String karyotype = s;
    			FinalResult finalResult = KaryotypeRunner.getFinalResult(karyotype);
    			JsonObjectBuilder cytogpsOutputBuilder = Json.createObjectBuilder().add("karyotype", karyotype);
    			if (finalResult.isContainingLexerParserError()) {
    				String revisedKaryotype = finalResult.getRevisedKaryotype();
    				if (!revisedKaryotype.isEmpty()) {
    					FinalResult newFinalResult = KaryotypeRunner.getFinalResult(revisedKaryotype);
    					if (newFinalResult.isContainingValidationError()) {
    						cytogpsOutputBuilder.add("status", "Fixable grammar error but containing validation error");
    						JsonArrayBuilder grammarErrorBuilder = Json.createArrayBuilder();
            				for (TokenError e:  finalResult.getTokenErrorList()) {
            					grammarErrorBuilder.add(e.toString(karyotype.replaceAll("\\s","")));
            				}
            				cytogpsOutputBuilder.add("grammar_error", grammarErrorBuilder);
    						cytogpsOutputBuilder.add("revised_karyotype", revisedKaryotype);
    						JsonArrayBuilder validationErrorBuilder = Json.createArrayBuilder();
    						for (String v: newFinalResult.getValidationMessage()) {
    							validationErrorBuilder.add(v);
    						}
    						cytogpsOutputBuilder.add("validation_error", validationErrorBuilder);
    					} else {
    						cytogpsOutputBuilder.add("status", "Fixable grammar error and success");
    						JsonArrayBuilder grammarErrorBuilder = Json.createArrayBuilder();
            				for (TokenError e:  finalResult.getTokenErrorList()) {
            					grammarErrorBuilder.add(e.toString(karyotype.replaceAll("\\s","")));
            				}
            				cytogpsOutputBuilder.add("grammar_error", grammarErrorBuilder);        						
    						cytogpsOutputBuilder.add("revised_karyotype", revisedKaryotype);        						
    						JsonArrayBuilder revisedParsingResultBuilder = Json.createArrayBuilder();
    						int cloneNum = newFinalResult.getCloneCodeList().size();
    						for (int j = 0; j < cloneNum; j++) {
    							JsonObjectBuilder cloneParsingResultBuilder = Json.createObjectBuilder();
    							if (newFinalResult.getCellNumList().get(j) != 0) {
    								cloneParsingResultBuilder.add("cell_number", newFinalResult.getCellNumList().get(j));
    							}
    							if (newFinalResult.getRelationshipList().get(j) != null) {
    								cloneParsingResultBuilder.add("relationship", newFinalResult.getRelationshipList().get(j));
    							} 
    							BiologicalOutcome b = newFinalResult.getBiologicalOutcomeList().get(j);
    							if (b.getUncertainEventsList().size() > 0) {
    								JsonArrayBuilder uncertainEventsBuilder = Json.createArrayBuilder();
    								for (String u: b.getUncertainEventsList()) {
    									uncertainEventsBuilder.add(u);
    								}
    								cloneParsingResultBuilder.add("uncertain_events", uncertainEventsBuilder);
    							}
    							if (b.getDetailedSystem().size() > 0) {
    								JsonArrayBuilder detailedSystemsBuilder = Json.createArrayBuilder();
    								for (String d: b.getDetailedSystem()) {
    									detailedSystemsBuilder.add(d);
    								}
    								cloneParsingResultBuilder.add("derivative_chromosome_detailed_systems", detailedSystemsBuilder);
    							}
    							List<List<Integer>> cloneLGF = BiologicalOutcome.getAdjustedLGF(b);
    							JsonObjectBuilder lgfOutputBuilder = Json.createObjectBuilder();  											
    							JsonArrayBuilder lossOutputBuilder = Json.createArrayBuilder();
    							JsonArrayBuilder gainOutputBuilder = Json.createArrayBuilder();
    							JsonArrayBuilder fusionOutputBuilder = Json.createArrayBuilder();
    							cloneLGF.get(0).stream().forEach(l -> lossOutputBuilder.add(l));
    							cloneLGF.get(1).stream().forEach(g -> gainOutputBuilder.add(g));
    							cloneLGF.get(2).stream().forEach(f -> fusionOutputBuilder.add(f));							
                                lgfOutputBuilder.add("loss", lossOutputBuilder);
                                lgfOutputBuilder.add("gain", gainOutputBuilder);
                                lgfOutputBuilder.add("fusion", fusionOutputBuilder);
                                cloneParsingResultBuilder.add("loss_gain_fusion_computing", lgfOutputBuilder);
    							
                                revisedParsingResultBuilder.add(cloneParsingResultBuilder);
    						}
    						
    						cytogpsOutputBuilder.add("parsing_result", revisedParsingResultBuilder);
    					}
    				} else {
    					cytogpsOutputBuilder.add("status", "Nonfixable grammar error");
    					JsonArrayBuilder grammarErrorBuilder = Json.createArrayBuilder();
        				for (TokenError e:  finalResult.getTokenErrorList()) {
        					grammarErrorBuilder.add(e.toString(karyotype.replaceAll("\\s","")));
        				}
        				cytogpsOutputBuilder.add("grammar_error", grammarErrorBuilder);
    				}
    			} else if (finalResult.isContainingValidationError()) {
    				cytogpsOutputBuilder.add("status", "Validation error");
    				JsonArrayBuilder validationErrorBuilder = Json.createArrayBuilder();
    				for (String v: finalResult.getValidationMessage()) {
    					validationErrorBuilder.add(v);
    				}
    				cytogpsOutputBuilder.add("validation_error", validationErrorBuilder);
    			} else {
    				cytogpsOutputBuilder.add("status", "Success");
    				JsonArrayBuilder parsingResultBuilder = Json.createArrayBuilder();
    				int cloneNum = finalResult.getCloneCodeList().size();
    				for (int j = 0; j < cloneNum; j++) {
    					JsonObjectBuilder cloneParsingResultBuilder = Json.createObjectBuilder();
    					if (finalResult.getCellNumList().get(j) != 0) {
    						cloneParsingResultBuilder.add("cell_number", finalResult.getCellNumList().get(j));
    					}
    					if (finalResult.getRelationshipList().get(j) != null) {
    						cloneParsingResultBuilder.add("relationship", finalResult.getRelationshipList().get(j));
    					} 
    					BiologicalOutcome b = finalResult.getBiologicalOutcomeList().get(j);
    					if (b.getUncertainEventsList().size() > 0) {
    						JsonArrayBuilder uncertainEventsBuilder = Json.createArrayBuilder();
    						for (String u: b.getUncertainEventsList()) {
    							uncertainEventsBuilder.add(u);
    						}
    						cloneParsingResultBuilder.add("uncertain_events", uncertainEventsBuilder);
    					}
    					if (b.getDetailedSystem().size() > 0) {
    						JsonArrayBuilder detailedSystemsBuilder = Json.createArrayBuilder();
    						for (String d: b.getDetailedSystem()) {
    							detailedSystemsBuilder.add(d);
    						}
    						cloneParsingResultBuilder.add("derivative_chromosome_detailed_systems", detailedSystemsBuilder);
    					}
    					List<List<Integer>> cloneLGF = BiologicalOutcome.getAdjustedLGF(b);
    					JsonObjectBuilder lgfOutputBuilder = Json.createObjectBuilder(); 											
    					JsonArrayBuilder lossOutputBuilder = Json.createArrayBuilder();
    					JsonArrayBuilder gainOutputBuilder = Json.createArrayBuilder();
    					JsonArrayBuilder fusionOutputBuilder = Json.createArrayBuilder();
    					cloneLGF.get(0).stream().forEach(l -> lossOutputBuilder.add(l));
    					cloneLGF.get(1).stream().forEach(g -> gainOutputBuilder.add(g));
    					cloneLGF.get(2).stream().forEach(f -> fusionOutputBuilder.add(f));							
                        lgfOutputBuilder.add("loss", lossOutputBuilder);
                        lgfOutputBuilder.add("gain", gainOutputBuilder);
                        lgfOutputBuilder.add("fusion", fusionOutputBuilder);
                        cloneParsingResultBuilder.add("loss_gain_fusion_computing", lgfOutputBuilder);
    					
                        parsingResultBuilder.add(cloneParsingResultBuilder);
    				}
    				
    				cytogpsOutputBuilder.add("parsing_result", parsingResultBuilder);
    			}
    			
    			cytogpsOutputArrayBuilder.add(cytogpsOutputBuilder);
    			
    		}
			
		    JsonArrayBuilder iscn2016BandsBuilder = Json.createArrayBuilder();
		    for (String[] chrArmArray: ISCN2016.chrArmArrays) {
		     	for(String chrBand: chrArmArray) {
		     		iscn2016BandsBuilder.add(chrBand);        		        		
		     	}
		    }	        	
        	
        	JsonObject cytogps = Json.createObjectBuilder()
				    				 .add("producer", "CytoGPS")
				    				 .add("date", java.time.LocalDate.now().toString())
				    				 .add("iscn2016_bands", iscn2016BandsBuilder)
				    				 .add("output", cytogpsOutputArrayBuilder)
				    				 .build();
        	
    		try (JsonWriter writer = Json.createWriter(new FileWriter(fileWritePath.toFile()))) {	    			
    			writer.writeObject(cytogps);	    		
    		} catch (Exception e) {
    			throw e;
    		} 
        	
            	
        } catch (Exception e) {
			throw e;
        } finally {
            try {
                br.close();
                fr.close();
            } catch (Exception e) {
            	throw e; 
            }
        }	        		
	}

}
