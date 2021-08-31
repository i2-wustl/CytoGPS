package main;

import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import business.BiologicalOutcome;
import business.Clone;
import business.FinalResult;
import business.ParseEvent;
import compiler.KaryotypeCleaner;
import compiler.KaryotypeLexer;
import compiler.KaryotypeLoader;
import compiler.KaryotypeParser;
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
public class KaryotypeRunner {
	
	public static FinalResult getFinalResult(String input){
		FinalResult finalResult = new FinalResult();
		String inputNoSpace = input.replaceAll("\\s","");
		try {
			KaryotypeLexer lexer = new KaryotypeLexer(CharStreams.fromString(inputNoSpace));
			lexer.removeErrorListeners();
			lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			KaryotypeParser parser = new KaryotypeParser(tokens);
			parser.removeErrorListeners();
			parser.addErrorListener(ThrowingErrorListener.INSTANCE);
			ParseTree tree = parser.row();
			ParseTreeWalker walker = new ParseTreeWalker();
			KaryotypeLoader loader = new KaryotypeLoader();
			walker.walk(loader, tree);
			
			List<String> multiCloneErrorMsgList = loader.getErrorMsgList();
			if (multiCloneErrorMsgList.size() > 0) {
				finalResult.setContainingValidationError(true);
				finalResult.setValidationMessage(multiCloneErrorMsgList);
			} else {
				List<Clone> rowClones = loader.getRowClones();	
				finalResult.setCloneCodeList(finalResult.getCloneCodeList(rowClones));
				new ParseEvent().processMissingBreakpoints(rowClones);
				if (Validator.isValidRowClones(rowClones)) {
					new ParseEvent().markUncertainDerEvent(rowClones);
					for (BiologicalOutcome b: new ParseEvent().getMultipleCloneRowOutcome(rowClones)) {		
						finalResult.getBiologicalOutcomeList().add(b);
						finalResult.getBiologicalInterpretationList().add(BiologicalOutcome.getBiologicalInterpretation(b));
					}
					finalResult.setCellNumList(finalResult.getCellNumList(rowClones));
					finalResult.setRelationshipList(finalResult.getRelationshipList(rowClones));
				} else {
					finalResult.setContainingValidationError(true);
					finalResult.setValidationMessage(ValidationError.getRowClonesError(rowClones));
				}
			}		
			
		} catch (ParseCancellationException ex) {
			finalResult.setContainingLexerParserError(true);
			KaryotypeLexer lexer = new KaryotypeLexer(CharStreams.fromString(inputNoSpace));
			ListErrorListener listErrorListener = new ListErrorListener();
			lexer.removeErrorListeners();
			lexer.addErrorListener(listErrorListener);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			KaryotypeParser parser = new KaryotypeParser(tokens);
			parser.removeErrorListeners();
			parser.addErrorListener(listErrorListener);
			parser.row();
			
			listErrorListener.getErrorList(inputNoSpace, listErrorListener.getErrorStrings())
			                 .forEach(e-> finalResult.getTokenErrorList().add(e));
				
			KaryotypeLexer lexer1 = new KaryotypeLexer(CharStreams.fromString(inputNoSpace));
			lexer1.removeErrorListeners();
			CommonTokenStream tokens1 = new CommonTokenStream(lexer1);
			KaryotypeParser parser1 = new KaryotypeParser(tokens1);
			parser1.removeErrorListeners();
			RuleContext tree1 = parser1.row();
			ParseTreeWalker walker1 = new ParseTreeWalker();
			KaryotypeCleaner cleaner = new KaryotypeCleaner(tokens1);
			walker1.walk(cleaner, tree1);
			String inputRevised = cleaner.getRewriter().getText();
//			System.out.println(inputRevised);
			try {
				KaryotypeLexer lexer2 = new KaryotypeLexer(CharStreams.fromString(inputRevised));
				lexer2.removeErrorListeners();
				lexer2.addErrorListener(ThrowingErrorListener.INSTANCE);
				CommonTokenStream tokens2 = new CommonTokenStream(lexer2);
				KaryotypeParser parser2 = new KaryotypeParser(tokens2);
				parser2.removeErrorListeners();
				parser2.addErrorListener(ThrowingErrorListener.INSTANCE);
				parser2.row();
//				String errorMessage = "Maybe you mean the following karyotype:\r\n" + inputRevised + "\r\n";	
				finalResult.setRevisedKaryotype(inputRevised);
//				finalResult.setErrorMessage(errorMessage); // I don't need this, as I have already created a list of TokenError
			} catch (ParseCancellationException e) {				
			}	
			
		}
		
		return finalResult;
	}

}
