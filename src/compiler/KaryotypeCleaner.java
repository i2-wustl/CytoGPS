package compiler;

import java.util.List;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class KaryotypeCleaner extends KaryotypeBaseListener {
	
	BufferedTokenStream tokens;
	TokenStreamRewriter rewriter;
	String chrListElements;
	String breakpointsListElements;
	String derChrListElements;
	String derBreakpointsListElements;
	String cellNumContent;
	String idemEvent;
	String slEvent;
	String sdlEvent;

	public TokenStreamRewriter getRewriter() {
		return rewriter;
	}

    public KaryotypeCleaner(BufferedTokenStream tokens) {
    	this.tokens = tokens;
    	rewriter = new TokenStreamRewriter(tokens);
    }
    
    @Override
    public void exitClone(KaryotypeParser.CloneContext ctx) {    	
    	int remove = 0;
    	if (ctx.incorrectSlant() != null) {
    		remove = ctx.incorrectSlant.getText().length();
    	}
    	if (ctx.tooManySlant() != null) {
    		remove = ctx.tooManySlant.getText().length();
    	} 
    	if (remove > 0) {
    		Token startToken = ctx.start;
            int start = startToken.getTokenIndex();
            int stop = start + remove - 1;
    		List<Token> cloneTokens = tokens.getTokens(start, stop);
    		for (Token token: cloneTokens) {
    			rewriter.replace(token, "");
    		}
    		rewriter.insertBefore(startToken, "/");    		
    	} else {
    		if (!ctx.getText().startsWith("/")) {
    			rewriter.insertBefore(ctx.start, "/");
    		}
    	}    	
    }
    
    @Override
    public void exitNonclonalClone(KaryotypeParser.NonclonalCloneContext ctx) {
    	int remove = 0;
    	if (ctx.incorrectSlant() != null) {
    		remove = ctx.incorrectSlant.getText().length();
    	}
    	if (ctx.tooManySlant() != null) {
    		remove = ctx.tooManySlant.getText().length();
    	} 
    	if (remove > 0) {
    		Token startToken = ctx.start;
            int start = startToken.getTokenIndex();
            int stop = start + remove - 1;
    		List<Token> cloneTokens = tokens.getTokens(start, stop);
    		for (Token token: cloneTokens) {
    			rewriter.replace(token, "");
    		}
    		rewriter.insertBefore(startToken, "/");    		
    	} else {
    		if (!ctx.getText().startsWith("/")) {
    			rewriter.insertBefore(ctx.start, "/");
    		}
    	}
    }
    
    @Override 
    public void exitAdditionalStemlineClone(KaryotypeParser.AdditionalStemlineCloneContext ctx) { 
    	int remove = 0;
    	if (ctx.incorrectSlant() != null) {
    		remove = ctx.incorrectSlant.getText().length();
    	}
    	if (ctx.tooManySlant() != null) {
    		remove = ctx.tooManySlant.getText().length();
    	} 
    	if (remove > 0) {
    		Token startToken = ctx.start;
            int start = startToken.getTokenIndex();
            int stop = start + remove - 1;
    		List<Token> cloneTokens = tokens.getTokens(start, stop);
    		for (Token token: cloneTokens) {
    			rewriter.replace(token, "");
    		}
    		rewriter.insertBefore(startToken, "/");    		
    	} else {
    		if (!ctx.getText().startsWith("/")) {
    			rewriter.insertBefore(ctx.start, "/");
    		}
    	}
    }
    
    @Override
    public void exitSidelineCloneTypeI(KaryotypeParser.SidelineCloneTypeIContext ctx) {
    	int remove = 0;
    	if (ctx.incorrectSlant() != null) {
    		remove = ctx.incorrectSlant.getText().length();
    	}
    	if (ctx.tooManySlant() != null) {
    		remove = ctx.tooManySlant.getText().length();
    	} 
    	if (remove > 0) {
    		Token startToken = ctx.start;
            int start = startToken.getTokenIndex();
            int stop = start + remove - 1;
    		List<Token> cloneTokens = tokens.getTokens(start, stop);
    		for (Token token: cloneTokens) {
    			rewriter.replace(token, "");
    		}
    		rewriter.insertBefore(startToken, "/");    		
    	} else {
    		if (!ctx.getText().startsWith("/")) {
    			rewriter.insertBefore(ctx.start, "/");
    		}
    	}
    }
    
    @Override
    public void exitSidelineCloneTypeII(KaryotypeParser.SidelineCloneTypeIIContext ctx) {
    	int remove = 0;
    	if (ctx.incorrectSlant() != null) {
    		remove = ctx.incorrectSlant.getText().length();
    	}
    	if (ctx.tooManySlant() != null) {
    		remove = ctx.tooManySlant.getText().length();
    	} 
    	if (remove > 0) {
    		Token startToken = ctx.start;
            int start = startToken.getTokenIndex();
            int stop = start + remove - 1;
    		List<Token> cloneTokens = tokens.getTokens(start, stop);
    		for (Token token: cloneTokens) {
    			rewriter.replace(token, "");
    		}
    		rewriter.insertBefore(startToken, "/");   		
    	} else {
    		if (!ctx.getText().startsWith("/")) {
    			rewriter.insertBefore(ctx.start, "/");
    		}
    	}
    }
    
    @Override 
    public void exitAdditionalClone(KaryotypeParser.AdditionalCloneContext ctx) {
    	int remove = 0;
    	if (ctx.incorrectSlant() != null) {
    		remove = ctx.incorrectSlant.getText().length();
    	}
    	if (ctx.tooManySlant() != null) {
    		remove = ctx.tooManySlant.getText().length();
    	} 
    	if (remove > 0) {
    		Token startToken = ctx.start;
            int start = startToken.getTokenIndex();
            int stop = start + remove - 1;
    		List<Token> cloneTokens = tokens.getTokens(start, stop);
    		for (Token token: cloneTokens) {
    			rewriter.replace(token, "");
    		}
    		rewriter.insertBefore(startToken, "/");   		
    	} else {
    		if (!ctx.getText().startsWith("/")) {
    			rewriter.insertBefore(ctx.start, "/");
    		}
    	}
    }
    
    @Override
    public void exitIncorrectModalNum(KaryotypeParser.IncorrectModalNumContext ctx) {
    	String modalNumRevised = "<" + ctx.modalNumContent.getText() + ">";
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> rBreakpointsListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: rBreakpointsListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, modalNumRevised);
    }
    
    @Override
    public void exitGender(KaryotypeParser.GenderContext ctx) { 
    	if (!ctx.getText().startsWith(",")) {
    		rewriter.insertBefore(ctx.start, ",");
    	}
    	if (ctx.getText().matches(",{2,}.+")) {
    		Token startToken = ctx.start;
    		Token stopToken = ctx.stop;
    		List<Token> commaTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex(), startToken.getType());
    		rewriter.insertBefore(startToken, ",");
    		for (Token comma: commaTokens) {
    			rewriter.replace(comma, "");
    		}
    		
    	}
    }
    
    @Override
    public void exitRegularEvent(KaryotypeParser.RegularEventContext ctx) {
    	if (!ctx.getText().startsWith(",")) {
    		rewriter.insertBefore(ctx.start, ",");
    	}
    	if (ctx.getText().matches(",{2,}.+")) {
    		Token startToken = ctx.start;
    		Token stopToken = ctx.stop;
    		List<Token> commaTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex(), startToken.getType());
    		rewriter.insertBefore(startToken, ",");
    		for (Token comma: commaTokens) {
    			rewriter.replace(comma, "");
    		}    		
    	}
    }
    
    @Override
    public void exitIdemSpecial(KaryotypeParser.IdemSpecialContext ctx) {
    	String idemEventRevised = "," + idemEvent;    	
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> chrListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: chrListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, idemEventRevised);	
    }
    
    @Override
    public void enterIdemEvent(KaryotypeParser.IdemEventContext ctx) {
    	idemEvent = "";
    }
    
    @Override
    public void exitIdemEvent(KaryotypeParser.IdemEventContext ctx) {
    	idemEvent = ctx.getText().replace(",", "");
    }
    
    @Override 
    public void exitSlSpecial(KaryotypeParser.SlSpecialContext ctx) {
    	String slEventRevised = "," + slEvent;    	
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> chrListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: chrListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, slEventRevised);	
    }
    
    @Override
    public void enterSlEvent(KaryotypeParser.SlEventContext ctx) {
    	slEvent = "";
    }
    
    @Override
    public void exitSlEvent(KaryotypeParser.SlEventContext ctx) {
    	slEvent = ctx.getText().replace(",", "");
    }
    
    @Override 
    public void exitSdlSpecial(KaryotypeParser.SdlSpecialContext ctx) {
    	String sdlEventRevised = "," + sdlEvent;    	
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> chrListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: chrListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, sdlEventRevised);
    }
    
    @Override
    public void enterSdlEvent(KaryotypeParser.SdlEventContext ctx) {
    	sdlEvent = "";
    }
    
    @Override
    public void exitSdlEvent(KaryotypeParser.SdlEventContext ctx) {
    	sdlEvent = ctx.getText().replace(",", "");
    }
    
    @Override
    public void enterChrListElements(KaryotypeParser.ChrListElementsContext ctx) {
    	chrListElements = "";
    }
    
    @Override
    public void exitChrListElements(KaryotypeParser.ChrListElementsContext ctx) {
    	chrListElements = ctx.getText().replace(',', ';')
    			                       .replace("100", "10")
    			                       .replace("200", "20")
    			                       .replace("30", "3")
    			                       .replace("40", "4")
    			                       .replace("50", "5")
    			                       .replace("60", "6")
    			                       .replace("70", "7")
    			                       .replace("80", "8")
    			                       .replace("90", "9")
    			                       ;  
    	if (chrListElements.matches("9[\\d]+.*")) {
    		chrListElements = chrListElements.substring(1);
    	}
    }
    
    @Override
    public void exitCorrectChrList(KaryotypeParser.CorrectChrListContext ctx) { 
    	String chrListRevised = "(" + chrListElements + ")";    	
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> chrListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: chrListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, chrListRevised);	
    }
    
    @Override
    public void exitIncorrectChrList(KaryotypeParser.IncorrectChrListContext ctx) {
    	String chrListRevised = "(" + chrListElements + ")";    	
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> chrListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: chrListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, chrListRevised);		
    }
    
    @Override
    public void enterBreakpointsListElements(KaryotypeParser.BreakpointsListElementsContext ctx) {
    	breakpointsListElements = "";
    }
    
    @Override
    public void exitBreakpointsListElements(KaryotypeParser.BreakpointsListElementsContext ctx) {
    	breakpointsListElements = ctx.getText();
    	if (breakpointsListElements.contains(",")) {
    		breakpointsListElements = breakpointsListElements.replace(',', ';');
    	}
    }
    
    @Override
    public void exitCorrectBreakpointsList(KaryotypeParser.CorrectBreakpointsListContext ctx) {
    	String breakpointsListRevised = "(" + breakpointsListElements + ")";
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> breakpointsListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: breakpointsListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, breakpointsListRevised);
    }
    
    @Override
    public void exitIncorrectBreakpointsList(KaryotypeParser.IncorrectBreakpointsListContext ctx) {
    	String breakpointsListRevised = "(" + breakpointsListElements + ")";
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> breakpointsListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: breakpointsListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, breakpointsListRevised);
    }
    
    @Override
    public void enterDerChrListElements(KaryotypeParser.DerChrListElementsContext ctx) {
    	derChrListElements = "";
    }
    
    @Override
    public void exitDerChrListElements(KaryotypeParser.DerChrListElementsContext ctx) { 
    	derChrListElements = ctx.getText().replace(',', ';')
							    		  .replace("100", "10")
							              .replace("200", "20")
							              .replace("30", "3")
							              .replace("40", "4")
							              .replace("50", "5")
							              .replace("60", "6")
							              .replace("70", "7")
							              .replace("80", "8")
							              .replace("90", "9")
							              ;
    	if (derChrListElements.matches("9[\\d]+.*")) {    		
    		derChrListElements = derChrListElements.substring(1);
    	}
    }
    
    @Override
    public void exitCorrectDerChrList(KaryotypeParser.CorrectDerChrListContext ctx) { 
    	String derChrListRevised = "(" + derChrListElements + ")";    	
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> derChrListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: derChrListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, derChrListRevised);
    }

    @Override
    public void exitIncorrectDerChrList(KaryotypeParser.IncorrectDerChrListContext ctx) {
    	String derChrListRevised = "(" + derChrListElements + ")";    	
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> derChrListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: derChrListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, derChrListRevised);
    }
    
    @Override
    public void enterDerBreakpointsListElements(KaryotypeParser.DerBreakpointsListElementsContext ctx) {
    	derBreakpointsListElements = "";
    }
    
    @Override
    public void exitDerBreakpointsListElements(KaryotypeParser.DerBreakpointsListElementsContext ctx) {
    	derBreakpointsListElements = ctx.getText();
    	if (derBreakpointsListElements.contains(",")) {    		
    		derBreakpointsListElements = derBreakpointsListElements.replace(',', ';');
    	}
    }
    
    @Override
    public void exitCorrectDerBreakpointsList(KaryotypeParser.CorrectDerBreakpointsListContext ctx) {
    	String derBreakpointsListRevised = "(" + derBreakpointsListElements + ")";
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> derBreakpointsListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: derBreakpointsListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, derBreakpointsListRevised);
    }
    
    @Override
    public void exitIncorrectDerBreakpointsList(KaryotypeParser.IncorrectDerBreakpointsListContext ctx) {
    	String derBreakpointsListRevised = "(" + derBreakpointsListElements + ")";
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> derBreakpointsListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: derBreakpointsListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, derBreakpointsListRevised);    	
    }
    
    @Override
    public void exitCorrectRChrList(KaryotypeParser.CorrectRChrListContext ctx) {
    	String rChrListRevised = "(" + chrListElements + ")";
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> rChrListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: rChrListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, rChrListRevised);
    }
    
    @Override
    public void exitIncorrectRChrList(KaryotypeParser.IncorrectRChrListContext ctx) { 
    	String rChrListRevised = "(" + chrListElements + ")";    	
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> rChrListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: rChrListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, rChrListRevised);
    }
    
    @Override
    public void exitCorrectRBreakpointsList(KaryotypeParser.CorrectRBreakpointsListContext ctx) {
    	String rBreakpointsListRevised = "(" + breakpointsListElements + ")";
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> rBreakpointsListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: rBreakpointsListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, rBreakpointsListRevised);
    }
    
    @Override
    public void exitIncorrectRBreakpointsList(KaryotypeParser.IncorrectRBreakpointsListContext ctx) {
    	String rBreakpointsListRevised = "(" + breakpointsListElements + ")";
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> rBreakpointsListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: rBreakpointsListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, rBreakpointsListRevised);
    }
    
    @Override
    public void exitIncorrectUncertainChr(KaryotypeParser.IncorrectUncertainChrContext ctx) {
    	String uncertainChrRevised = "(" + ctx.uncertainChrContent.getText() + ")";
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> rBreakpointsListTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: rBreakpointsListTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, uncertainChrRevised);
    }
    
    @Override
    public void exitBasicAberrationError(KaryotypeParser.BasicAberrationErrorContext ctx) { 
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> allTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: allTokens) {
			if (token.getText().equals(",")) {
				rewriter.replace(token, "");
			}			
		}
    }
    
    @Override
    public void exitDerAberrationError(KaryotypeParser.DerAberrationErrorContext ctx) {
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> allTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: allTokens) {
			if (token.getText().equals(",")) {
				rewriter.replace(token, "");
			}
		}
    }
    
    @Override
    public void exitRearrangement(KaryotypeParser.RearrangementContext ctx) {
    	String input = ctx.getText();
    	if (input.contains(",")) {
    		Token startToken = ctx.start;
    		Token stopToken = ctx.stop;
    		List<Token> allTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
    		for (Token token: allTokens) {
    			if (token.getText().equals(",")) {
    				rewriter.replace(token, "");
    			}
    		}
    	}
    }
    
    @Override
    public void enterCellNumContent(KaryotypeParser.CellNumContentContext ctx) {
    	cellNumContent = "";
    }
    
    @Override
    public void exitCellNumContent(KaryotypeParser.CellNumContentContext ctx) {
    	cellNumContent = ctx.getText().replaceAll("[cC][eE][lL][lL][sS]?", "");   
    	if (ctx.getText().toLowerCase().contains("cp")) {
    		cellNumContent = "cp" + cellNumContent.replaceAll("[cC][pP]", ""); 
    	}
    }
    
    @Override
    public void exitCorrectCellNum(KaryotypeParser.CorrectCellNumContext ctx) {
    	String cellNumRevised = "[" + cellNumContent + "]";
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> cellNumTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: cellNumTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, cellNumRevised);
    }
    
    @Override
    public void exitIncorrectCellNum(KaryotypeParser.IncorrectCellNumContext ctx) {
    	String cellNumRevised;
    	if (ctx.getText().toLowerCase().startsWith("cp") && !cellNumContent.toLowerCase().contains("cp")) {
    		cellNumRevised = "[cp" + cellNumContent + "]";
    	} else {
    		cellNumRevised = "[" + cellNumContent + "]";
    	}
    	Token startToken = ctx.start;
		Token stopToken = ctx.stop;
		List<Token> cellNumTokens = tokens.getTokens(startToken.getTokenIndex(), stopToken.getTokenIndex());
		for (Token token: cellNumTokens) {
			rewriter.replace(token, "");
		}
		rewriter.insertBefore(startToken, cellNumRevised);
    }
    
}
