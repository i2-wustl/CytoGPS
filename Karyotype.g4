/**
 * Define a grammar used to parse karyotypes
 * 
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021
 */
grammar Karyotype;

@header {
package compiler; 
import java.util.*; 
}

@parser::members{
	int i = 0;
	List<String> sexChrList = new ArrayList<>();
}

row: rowTypeI 
   | rowTypeII
   | (LETTER | .)+ {notifyErrorListeners("-1|This is an incorrect input for karyotype parsing.");}
   ;
rowTypeI: firstClone (nonclonalClone | clone)* '.'? ;  
firstClone 
@init {i = i + 1;}
@after {sexChrList = new ArrayList<>();}
: ploidy? karyotypeI 
;
clone locals [String s = "", int l = 0]
@init {i = i + 1;}
@after {sexChrList = new ArrayList<>();}
:  '/' ploidy? karyotypeII
| ploidy? karyotypeII 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeII.text;
	} else {
		$s = $karyotypeII.text;
	}
	$l = $s.length();
	notifyErrorListeners($l + "|Missing a slant line before '" + $s + "'");
  } 
| tooManySlant ploidy? karyotypeII 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeII.text;
	} else {
		$s = $karyotypeII.text;
	}
	$l = $s.length() + $tooManySlant.text.length();
	notifyErrorListeners($l + "|Too many slant lines before '" + $s + "'");
  } 
| incorrectSlant ploidy? karyotypeII 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeII.text;
	} else {
		$s = $karyotypeII.text;
	}
	$l = $s.length() + $incorrectSlant.text.length();
	notifyErrorListeners($l + "|Incorrect clone separation before '" + $s + "'");
  } 
;
nonclonalClone locals [String s = "", int l = 0]
@init {i = i + 1;}
: '/' ploidy? karyotypeV
| ploidy? karyotypeV 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeV.text;
	} else {
		$s = $karyotypeV.text;
	}
	$l = $s.length();
	notifyErrorListeners($l + "|Missing a slant line before '" + $s + "'");
  } 
| tooManySlant ploidy? karyotypeV 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeV.text;
	} else {
		$s = $karyotypeV.text;
	}
	$l = $s.length() + $tooManySlant.text.length();
	notifyErrorListeners($l + "|Too many slant lines before '" + $s + "'");
  } 
| incorrectSlant ploidy? karyotypeV 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeV.text;
	} else {
		$s = $karyotypeV.text;
	}
	$l = $s.length() + $incorrectSlant.text.length();
	notifyErrorListeners($l + "|Incorrect clone separation before '" + $s + "'");
  } 
;

rowTypeII: firstStemlineGroup otherStemlineGroup* additionalClone* '.'? ;
firstStemlineGroup: stemlineClone sidelineCloneTypeI sidelineCloneTypeII* sidelineCloneTypeI* 
                  | stemlineClone sidelineCloneTypeI+ sidelineCloneTypeI sidelineCloneTypeII+ sidelineCloneTypeI*
                  ;
otherStemlineGroup: additionalStemlineClone sidelineCloneTypeI+ ;
stemlineClone
@init {i = i + 1;}
@after {sexChrList = new ArrayList<>();}
: ploidy? karyotypeI 
;
additionalStemlineClone locals [String s = "", int l = 0]
@init {i = i + 1;}
@after {sexChrList = new ArrayList<>();}
: '/' ploidy? karyotypeI 
| ploidy? karyotypeI 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeI.text;
	} else {
		$s = $karyotypeI.text;
	}
	$l = $s.length();
	notifyErrorListeners($l + "|Missing a slant line before '" + $s + "'");
  }
| tooManySlant ploidy? karyotypeI 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeI.text;
	} else {
		$s = $karyotypeI.text;
	}
	$l = $s.length() + $tooManySlant.text.length();
	notifyErrorListeners($l + "|Too many slant lines before '" + $s + "'");
  } 
| incorrectSlant ploidy? karyotypeI 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeI.text;
	} else {
		$s = $karyotypeI.text;
	}
	$l = $s.length() + $incorrectSlant.text.length();
	notifyErrorListeners($l + "|Incorrect clone separation before '" + $s + "'");
  } 
; 
sidelineCloneTypeI locals [String s = "", int l = 0]
@init {i = i + 1;}
@after {sexChrList = new ArrayList<>();}
: '/' ploidy? karyotypeIII
| ploidy? karyotypeIII 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeIII.text;
	} else {
		$s = $karyotypeIII.text;
	}
	$l = $s.length();
	notifyErrorListeners($l + "|Missing a slant line before '" + $s + "'");	
  }
| tooManySlant ploidy? karyotypeIII 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeIII.text;
	} else {
		$s = $karyotypeIII.text;
	}
	$l = $s.length() + $tooManySlant.text.length();
	notifyErrorListeners($l + "|Too many slant lines before '" + $s + "'");	
  }
| incorrectSlant ploidy? karyotypeIII 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeIII.text;
	} else {
		$s = $karyotypeIII.text;
	}
	$l = $s.length() + $incorrectSlant.text.length();
	notifyErrorListeners($l + "|Incorrect clone separation before '" + $s + "'");
  } 
;
sidelineCloneTypeII locals [String s = "", int l = 0] 
@init {i = i + 1;}
@after {sexChrList = new ArrayList<>();}
: '/' ploidy? karyotypeIV 
| ploidy? karyotypeIV 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeIV.text;
	} else {
		$s = $karyotypeIV.text;
	}
	$l = $s.length();
	notifyErrorListeners($l + "|Missing a slant line before '" + $s + "'");
  }
| tooManySlant ploidy? karyotypeIV 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeIV.text;
	} else {
		$s = $karyotypeIV.text;
	}
	$l = $s.length() + $tooManySlant.text.length();
	notifyErrorListeners($l + "|Too many slant lines before '" + $s + "'");	
  }
| incorrectSlant ploidy? karyotypeIV 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeIV.text;
	} else {
		$s = $karyotypeIV.text;
	}
	$l = $s.length() + $incorrectSlant.text.length();
	notifyErrorListeners($l + "|Incorrect clone separation before '" + $s + "'");	
  } 
;
additionalClone locals [String s = "", int l = 0]
@init {i = i + 1;}
@after {sexChrList = new ArrayList<>();}
: '/' ploidy? karyotypeI
| ploidy? karyotypeI 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeI.text;
	} else {
		$s = $karyotypeI.text;
	}
	$l = $s.length();
	notifyErrorListeners($l + "|Missing a slant line before '" + $s + "'");	
  }
| tooManySlant ploidy? karyotypeI 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeI.text;
	} else {
		$s = $karyotypeI.text;
	}
	$l = $s.length() + $tooManySlant.text.length();
	notifyErrorListeners($l + "|Too many slant lines before '" + $s + "'");
  }
| incorrectSlant ploidy? karyotypeI 
  {
	if ($ploidy.text != null) {
		$s = $ploidy.text + $karyotypeI.text;
	} else {
		$s = $karyotypeI.text;
	}
	$l = $s.length() + $incorrectSlant.text.length();
	notifyErrorListeners($l + "|Incorrect clone separation before '" + $s + "'");	
  } 
;

/** I have seen a karyotype 46,t(X;18)(p11.1;q11.2),t(Y;1)(q11.2;p13) on p.50 of ISCN 2016.
 *  From Mitleman database, we have seen 46,t(X;14)(p22;q32) or t(Y;14)(p11;q32).
 *  We may need to allow gender to be missing? (Complicated, but will do.)
 */
karyotypeI locals [int l = 0] 
: mosChi? chrNum modalNum? gender regularEvent* cellNum?
| mosChi? chrNum modalNum? r=regularEvent* cellNum? 
  {
	if (sexChrList.size() == 0) {	
		if ($mosChi.text != null) {
			$l = $l + $mosChi.text.length();
		}
		$l = $l + $chrNum.text.length();
		if ($modalNum.text != null) {
			$l = $l + $modalNum.text.length();
		}
		if ($r.text != null) {
			$l = $l + $r.text.length();
		}
		if ($cellNum.text != null) {
			$l = $l + $cellNum.text.length();
		}
		notifyErrorListeners($l + "|Missing gender in clone # " + i);
	}
  }
| mosChi? r=regularEvent+ cellNum? 
  {
	if ($mosChi.text != null) {
		$l = $l + $mosChi.text.length();
	}
	$l = $l + $r.text.length();
	if ($cellNum.text != null) {
		$l = $l + $cellNum.text.length();
	}	
	notifyErrorListeners($l + "|Missing chromosome numbers and gender");
  }
; 

karyotypeII locals [int l = 0] 
: mosChi? chrNum modalNum? gender regularEvent* cellNum?
| mosChi? chrNum modalNum? idemSpecial regularEvent* cellNum?
| mosChi? idemSpecial r=regularEvent* cellNum? 
  {
	if ($mosChi.text != null) {
		$l = $l + $mosChi.text.length();
	}
	$l = $l + $idemSpecial.text.length();
	if ($r.text != null) {
		$l = $l + $r.text.length();
	}
	if ($cellNum.text != null) {
		$l = $l + $cellNum.text.length();
	}
	notifyErrorListeners($l + "|Missing chromosome numbers");
  }
| mosChi? chrNum modalNum? r=regularEvent* cellNum? 
  {
	if (sexChrList.size() == 0) {
		if ($mosChi.text != null) {
			$l = $l + $mosChi.text.length();
		}
		$l = $l + $chrNum.text.length();
		if ($modalNum.text != null) {
			$l = $l + $modalNum.text.length();
		}
		if ($r.text != null) {
			$l = $l + $r.text.length();
		}
		if ($cellNum.text != null) {
			$l = $l + $cellNum.text.length();
		}
		notifyErrorListeners($l + "|Missing gender in clone # " + i);
	}
  }
| mosChi? r=regularEvent+ cellNum? 
  {
	if ($mosChi.text != null) {
		$l = $l + $mosChi.text.length();
	}
	$l = $l + $r.text.length();
	if ($cellNum.text != null) {
		$l = $l + $cellNum.text.length();
	}	
	notifyErrorListeners($l + "|Missing chromosome numbers and gender");
  }
;    

karyotypeIII locals [int l = 0]
: mosChi? chrNum modalNum? slSpecial regularEvent* cellNum?
| mosChi? slSpecial r=regularEvent* cellNum? 
  {
	if ($mosChi.text != null) {
		$l = $l + $mosChi.text.length();
	}
	$l = $l + $slSpecial.text.length();
	if ($r.text != null) {
		$l = $l + $r.text.length();
	}
	if ($cellNum.text != null) {
		$l = $l + $cellNum.text.length();
	}
	notifyErrorListeners($l + "|Missing chromosome numbers");
  }
;
karyotypeIV locals [int l = 0]
: mosChi? chrNum modalNum? sdlSpecial regularEvent* cellNum?
| mosChi? sdlSpecial r=regularEvent* cellNum? 
  {
	if ($mosChi.text != null) {
		$l = $l + $mosChi.text.length();
	}
	$l = $l + $sdlSpecial.text.length();
	if ($r.text != null) {
		$l = $l + $r.text.length();
	}
	if ($cellNum.text != null) {
		$l = $l + $cellNum.text.length();
	}	
	notifyErrorListeners($l + "|Missing chromosome numbers");
  }
;  
karyotypeV: mosChi? (chrNum modalNum? gender ',')? 
            ('n' | 'N') ('o' | 'O') ('n' | 'N') '-'? ('c' | 'C') ('l' | 'L') ('o' | 'O') ('n' | 'N') ('a' | 'A') ('l' | 'L') 
            (('a' | 'A') ('b' | 'B') ('n' | 'N') (('o' | 'O') ('r' | 'R') ('m' | 'M') ('a' | 'A') ('l' | 'L'))? )? 
            cellNum? ;           
                           
ploidy: modalDesc (('c' | 'C') ('l' | 'L') ('o' | 'O') ('n' | 'N') ('e' | 'E') ':'?)? ;
tooManySlant: '/' '/'+;
incorrectSlant: ('.' | ',' | ';' | '/')+;

modalNum locals [String s = "", int l = 0]
: '<' modalNumContent '>'   # correctModalNum
| modalNumContent '>' 
  {
  	$s = $modalNumContent.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing '<' for a modal number '" + $s + "'");
  }
  # incorrectModalNum
| '<' modalNumContent
  {
  	$s = $modalNumContent.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing '>' for a modal number '" + $s + "'");
  }
  # incorrectModalNum
| modalNumContent 
  {
  	$s = $modalNumContent.text;
  	$l = $s.length();
  	notifyErrorListeners($l + "|Missing '<>' for a modal number '" + $s + "'");
  }
  # incorrectModalNum  
| incorrectLeftParenthesis modalNumContent incorrectRightParenthesis  
  {
  	$s = $modalNumContent.text;
  	$l = $s.length();
  	if ($incorrectLeftParenthesis.text != null) {
  		$l = $l + $incorrectLeftParenthesis.text.length();
  	}
  	if ($incorrectRightParenthesis.text != null) {
  		$l = $l + $incorrectRightParenthesis.text.length();
  	}
  	notifyErrorListeners($l + "|Wrong bracket format for a modal number '" + $s + "', expecting '<>'");
  }
  # incorrectModalNum   	
;
modalNumContent: POSITIVEDIGIT ('n' | 'N') ;
                               
gender locals [String s = "", int l = 0]
: ',' sexChr orSex c?
| sexChr orSex c? 
  {
	$s = $sexChr.text;
	if ($orSex.text != null) {
		$s = $s + $orSex.text;
	}
	if ($c.text != null) {
		$s = $s + $c.text;
	}
	$l = $s.length();
	notifyErrorListeners($l + "|Missing a comma before '" + $s + "'");
  }
| tooManyComma sexChr orSex c? 
  {
	$s = $sexChr.text;
	if ($orSex.text != null) {
		$s = $s + $orSex.text;
	}
	if ($c.text != null) {
		$s = $s + $c.text;
	}
	$l = $s.length() + $tooManyComma.text.length();
	notifyErrorListeners($l + "|Too many commas before '" + $s + "'");
  }            
;

tooManyComma: ',' ','+;
orSex: (('o' | 'O') ('r' | 'R') sexChr)*;
      
idemSpecial locals [String s = "", int l = 0]
: ',' idemEvent
| idemEvent  
  {
	$s = $idemEvent.text;
	$l = $s.length();
	notifyErrorListeners($l + "|Missing a comma before '" + $s + "'");
  }   
| tooManyComma idemEvent 
  {
	$s = $idemEvent.text;
	$l = $s.length() + $tooManyComma.text.length();
	notifyErrorListeners($l + "|Too many commas before '" + $s + "'");
  }   
; 
slSpecial locals [String s = "", int l = 0]
: ',' slEvent
| slEvent 
  {
	$s = $slEvent.text;
 	$l = $s.length();
	notifyErrorListeners($l + "|Missing a comma before '" + $s + "'");
  }  
| tooManyComma slEvent 
  {
	$s = $slEvent.text;
	$l = $s.length() + $tooManyComma.text.length();
	notifyErrorListeners($l + "|Too many commas before '" + $s + "'");
  }
;                     
sdlSpecial locals [String s = "", int l = 0]
: ',' sdlEvent
| sdlEvent 
  {
	$s = $sdlEvent.text;
 	$l = $s.length();
	notifyErrorListeners($l + "|Missing a comma before '" + $s + "'");
  }   
| tooManyComma sdlEvent 
  {
	$s = $sdlEvent.text;
	$l = $s.length() + $tooManyComma.text.length();
	notifyErrorListeners($l + "|Too many commas before '" + $s + "'");
  }  
; 
regularEvent locals [String s = "", int l = 0]
: ',' regEvent
| regEvent 
  {
	$s = $regEvent.text;
 	$l = $s.length();
	notifyErrorListeners($l + "|Missing a comma before '" + $s + "'");
  }
| tooManyComma regEvent 
  {
	$s = $regEvent.text;
	$l = $s.length() + $tooManyComma.text.length();
	notifyErrorListeners($l + "|Too many commas before '" + $s + "'");
  }   
;  

incorrectComma: ','+ ;   
     
idemEvent locals [String s = "", int l = 0]
: ('i' | 'I') ('d' | 'D') ('e' | 'E') ('m' | 'M') multiplication? 
| ('i' | 'I') ('d' | 'D') ('e' | 'E') ('m' | 'M') incorrectComma multiplication
  {
  	$s = $multiplication.text;
  	$l = $s.length() + 4 + $incorrectComma.text.length();
  	notifyErrorListeners($l + "|No comma should be used between 'idem' and '" + $s + "'");
  }
; 
slEvent locals [String s1 = "", String s2 = "", int l = 0]
: slMark multiplication?
| slMark incorrectComma multiplication
  {
  	$s1 = $slMark.text;
  	$s2 = $multiplication.text;
  	$l = $s1.length() + $s2.length() + $incorrectComma.text.length();
  	notifyErrorListeners($l + "|No comma should be used between '" + $s1 + "' and '" + $s2 + "'");
  }
;
slMark: ('s' | 'S') ('l' | 'L') integer? ;
sdlEvent locals [String s1 = "", String s2 = "", int l = 0]
: sdlMark multiplication? 
| sdlMark incorrectComma multiplication
  {
  	$s1 = $sdlMark.text;
  	$s2 = $multiplication.text;
  	$l = $s1.length() + $s2.length() + $incorrectComma.text.length();
  	notifyErrorListeners($l + "|No comma should be used between '" + $s1 + "' and '" + $s2 + "'");
  }
;
sdlMark: ('s' | 'S') ('d' | 'D') ('l' | 'L') integer? ;
regEvent: regEventType (('o' | 'O') ('r' | 'R') regEventType)+      # multipleInterpretationRegularEvent 
        | regEventType                                              # simpleRegularEvent 
        ;
regEventType: prefixPlus? ('r' | 'R') suffix? ',' QUES? dminNum? ('d' | 'D') ('m' | 'M') ('i' | 'I') ('n' | 'N') suffix? # undecodedSpecialEvent
            | aberration       # aberrationEvent
            | undecoded        # undecodedEvent 
            | gainLossChr      # gainLossChrEvent
            ;          

/** Note: The multiplication sign should not be used to denote multiple copies of normal chromosomes.
 *  For example, we have 48,XX,del(6)(q13q23)x2,+7,+7 on p.80 of ISCN 2016.
 */
gainLossChr locals [String s = "", int l = 0]
: prefixPlus gainChr (c | inh)? 
  { 
  	$s = $gainChr.text.toUpperCase(); 
  	if ($s.contains("X")) {sexChrList.add("X");}
  	if ($s.contains("Y")) {sexChrList.add("Y");}
  }  
  # gainChrEvent
| prefixMinus lossChr (c | inh)?     
  { 
  	$s = $lossChr.text.toUpperCase(); 
  	if ($s.contains("X")) {sexChrList.add("X");}
  	if ($s.contains("Y")) {sexChrList.add("Y");}
  } 
  # lossChrEvent
| undeterminedPrefix gainChr? (c | inh)?
  {
  	$s = $gainChr.text;
  	$l = $undeterminedPrefix.text.length(); 
  	if ($c.text != null) {
  		$l = $l + $c.text.length(); 
  	} 	
  	if ($inh.text != null) {
  		$l = $l + $inh.text.length(); 
  	}
 	if ($s != null) {
 		$l = $l + $s.length();
 		notifyErrorListeners($l + "|Cannot use both '-' and '+' for the gain/loss aberration of chromosome '" + $gainChr.text + "'");
 	} else {
 		notifyErrorListeners($l + "|Cannot use both '-' and '+' for the gain/loss aberration and missing chromosome information"); 
 	} 		          	            	
  } 
  # gainLossChrError
| (prefixPlus | prefixMinus) 
  {
  	if ($prefixPlus.text != null) {
  		$l = $prefixPlus.text.length();
  	}
  	if ($prefixMinus.text != null) {
  		$l = $prefixMinus.text.length();
  	}
  	notifyErrorListeners($l + "|Missing chromosome information of the gain/loss aberration");
  } 
  # gainLossChrError  
;
           
undecoded: prefixPlus? ('r' | 'R') suffix? 
         | prefixPlus ('m' | 'M') ('a' | 'A') ('r' | 'R') integer suffix?  
         | prefixPlus marNum? ('m' | 'M') ('a' | 'A') ('r' | 'R') suffix?  
         | QUES? dminNum? ('d' | 'D') ('m' | 'M') ('i' | 'I') ('n' | 'N') suffix?  
         | ('i' | 'I') ('n' | 'N') ('c' | 'C')  
         ;  

aberration locals [String s = "", int l = 0]
: prefix? mosChi? derId derChrList derBreakpointsList? rearrangementElement* suffix? 
  {
  	$s = $derChrList.text.toUpperCase();
  	if ($s.contains("X")) {sexChrList.add("X");}
  	if ($s.contains("Y")) {sexChrList.add("Y");}
  }
  # derAberration
| prefix? mosChi? id chrList breakpointsList? suffix?
  {
  	$s = $chrList.text.toUpperCase();
  	if ($s.contains("X")) {sexChrList.add("X");}
  	if ($s.contains("Y")) {sexChrList.add("Y");}
  }
  # basicAberration
| prefix? mosChi? idUncertainChr uncertainChr suffix?
  {
  	$s = $uncertainChr.text.toUpperCase();
  	if ($s.contains("X")) {sexChrList.add("X");}
  	if ($s.contains("Y")) {sexChrList.add("Y");}
  }  
  # uncertainBasicAberration  
| prefix? mosChi? derId comma=','+ derChrList derBreakpointsList? r=rearrangementElement* suffix? 
  {
  	if ($prefix.text != null) {
  		$l = $l + $prefix.text.length(); 
  	}
  	if ($mosChi.text != null) {
  		$l = $l + $mosChi.text.length(); 
  	}
  	$l = $l + $derId.text.length(); 
  	$l = $l + $comma.text.length(); 
  	$l = $l + $derChrList.text.length(); 
  	if ($r.text != null) {
  		$l = $l + $r.text.length(); 
  	}
  	if ($suffix.text != null) {
  		$l = $l + $suffix.text.length(); 
  	}  	
 	if ($derBreakpointsList.text != null) {
 		$l = $l + $derBreakpointsList.text.length();
 		notifyErrorListeners($l + "|Incorrect comma before '" + $derChrList.text + $derBreakpointsList.text + "'");
 	} else {
 		notifyErrorListeners($l + "|Incorrect comma before '" + $derChrList.text + "'");
 	} 	    
  } 
  # derAberrationError
| prefix? mosChi? id comma=','+ chrList breakpointsList? suffix?  
  {
  	if ($prefix.text != null) {
  		$l = $l + $prefix.text.length(); 
  	}
  	if ($mosChi.text != null) {
  		$l = $l + $mosChi.text.length(); 
  	}
  	$l = $l + $id.text.length(); 
  	$l = $l + $comma.text.length(); 
  	$l = $l + $chrList.text.length(); 
  	if ($suffix.text != null) {
  		$l = $l + $suffix.text.length(); 
  	}
 	if ($breakpointsList.text != null) {
 		$l = $l + $breakpointsList.text.length();
 		notifyErrorListeners($l + "|Incorrect comma before '" + $chrList.text + $breakpointsList.text + "'");
 	} else {
 		notifyErrorListeners($l + "|Incorrect comma before '" + $chrList.text + "'"); 
 	} 	       
  } 
  # basicAberrationError      
;
rearrangementElement: rearrangement                                              # normalRearrangement
                    | rearrangement (('o' | 'O') ('r' | 'R') rearrangement)+     # multipleInterpretationRearrangement
                    ;            
rearrangement locals [int l = 0]
: QUES? rId rChrList rBreakpointsList? 
| QUES? rId comma=','+ rChrList rBreakpointsList? 
  {
  	if ($QUES.text != null) {
  		$l = $l + $QUES.text.length(); 
  	}
  	$l = $l + $rId.text.length(); 
  	$l = $l + $comma.text.length();
  	$l = $l + $rChrList.text.length();  	
	if ($rBreakpointsList.text != null) {
		$l = $l + $rBreakpointsList.text.length();
		notifyErrorListeners($l + "|Incorrect comma before '" + $rChrList.text + $rBreakpointsList.text + "'");
	} else {
		notifyErrorListeners($l + "|Incorrect comma before '" + $rChrList.text + "'");
	}	       
  }
;         
derChrList locals [String s = "", int l = 0]
: '(' derChrListElements ')'   # correctDerChrList 
| derChrListElements ')' 
  {
  	$s = $derChrListElements.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing '(' for derivative chromosome list '" + $s + "'");
  } 
  # incorrectDerChrList 	
| '(' derChrListElements 
  {
  	$s = $derChrListElements.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing ')' for derivative chromosome list '" + $s + "'");
  }
  # incorrectDerChrList  
| derChrListElements 
  {
  	$s = $derChrListElements.text;
  	$l = $s.length();
  	notifyErrorListeners($l + "|Missing '()' for derivative chromosome list '" + $s + "'");
  }
  # incorrectDerChrList 
| incorrectLeftParenthesis derChrListElements incorrectRightParenthesis
  {
  	$s = $derChrListElements.text;
  	$l = $s.length();
  	if ($incorrectLeftParenthesis.text != null) {
  		$l = $l + $incorrectLeftParenthesis.text.length();
  	}
  	if ($incorrectRightParenthesis.text != null) {
  		$l = $l + $incorrectRightParenthesis.text.length();
  	}
  	notifyErrorListeners($l + "|Wrong bracket format for derivative chromosome list '" + $s + "', expecting '()'");
  }
  # incorrectDerChrList 
; 
derChrListElements
: derChr (';' derChr)* 
| incorrectDerChrListElements
  {
  	notifyErrorListeners($incorrectDerChrListElements.text.length() + "|Incorrect use of separators inside derivative chromosome list '" + $incorrectDerChrListElements.text + "', expecting ';'");
  } 
;   
incorrectDerChrListElements: derChr ((';'|',') derChr)+ ;          
incorrectLeftParenthesis: ('['|'{'|'<'|'(')*;
incorrectRightParenthesis: (']'|'}'|'>'|')')*;               
derBreakpointsList locals [String s = "", int l = 0]
: '(' derBreakpointsListElements ')'   # correctDerBreakpointsList
| derBreakpointsListElements ')' 
  {
  	$s = $derBreakpointsListElements.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing '(' for derivative breakpoints list '" + $s + "'");
  }
  # incorrectDerBreakpointsList	
| '(' derBreakpointsListElements 
  {
  	$s = $derBreakpointsListElements.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing ')' for derivative breakpoints list '" + $s + "'");
  }
  # incorrectDerBreakpointsList
| derBreakpointsListElements 
  {
  	$s = $derBreakpointsListElements.text;
  	$l = $s.length();
  	notifyErrorListeners($l + "|Missing '()' for derivative breakpoints list '" + $s + "'");
  }
  # incorrectDerBreakpointsList
| incorrectLeftParenthesis derBreakpointsListElements incorrectRightParenthesis 
  {
  	$s = $derBreakpointsListElements.text;
  	$l = $s.length();
  	if ($incorrectLeftParenthesis.text != null) {
  		$l = $l + $incorrectLeftParenthesis.text.length();
  	}
  	if ($incorrectRightParenthesis.text != null) {
  		$l = $l + $incorrectRightParenthesis.text.length();
  	}
  	notifyErrorListeners($l + "|Wrong bracket format for derivative breakpoints list '" + $s + "', expecting '()'");
  }
  # incorrectDerBreakpointsList
;
derBreakpointsListElements
: derBreakpointsInOneChr (';' derBreakpointsInOneChr)* 
| incorrectDerBreakpointsListElements
  {
  	notifyErrorListeners($incorrectDerBreakpointsListElements.text.length() + "|Incorrect use of separators inside breakpoints list '" + $incorrectDerBreakpointsListElements.text + "', expecting ';'");
  }
;     
incorrectDerBreakpointsListElements: derBreakpointsInOneChr ((';'|',') derBreakpointsInOneChr)+ ; 
derBreakpointsInOneChr: derBreakpoints                                               # normalDerBreakpoints
                      | derBreakpoints (('o' | 'O') ('r' | 'R') derBreakpoints)+     # multipleInterpretationDerBreakpoints
                      ;           
chrList locals [String s = "", int l = 0]
: '(' chrListElements ')'   # correctChrList
| chrListElements ')' 
  {
  	$s = $chrListElements.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing '(' for chromosome list '" + $s + "'");
  } 
  # incorrectChrList 	
| '(' chrListElements
  {
  	$s = $chrListElements.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing ')' for chromosome list '" + $s + "'");
  }
  # incorrectChrList   
| chrListElements
  {
  	$s = $chrListElements.text;
  	$l = $s.length();
  	notifyErrorListeners($l + "|Missing '()' for chromosome list '" + $s + "'");
  }
  # incorrectChrList  
| incorrectLeftParenthesis chrListElements incorrectRightParenthesis 
  {
  	$s = $chrListElements.text;
  	$l = $s.length();
  	if ($incorrectLeftParenthesis.text != null) {
  		$l = $l + $incorrectLeftParenthesis.text.length();
  	}
  	if ($incorrectRightParenthesis.text != null) {
  		$l = $l + $incorrectRightParenthesis.text.length();
  	}
  	notifyErrorListeners($l + "|Wrong bracket format for chromosome list '" + $s + "', expecting '()'");
  }
  # incorrectChrList
;
chrListElements
: chr (';' chr)* 
| incorrectChrListElements 
  {
  	notifyErrorListeners($incorrectChrListElements.text.length() + "|Incorrect use of separators inside chromosome list '" + $incorrectChrListElements.text + "', expecting ';'");
  }
; 
incorrectChrListElements: chr ((';'|',') chr)+ ;                     
breakpointsList locals [String s = "", int l = 0]
: '(' breakpointsListElements ')'   # correctBreakpointsList
| breakpointsListElements ')' 
  {
  	$s = $breakpointsListElements.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing '(' for breakpoints list '" + $s + "'");
  }
  # incorrectBreakpointsList	
| '(' breakpointsListElements
  {
  	$s = $breakpointsListElements.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing ')' for breakpoints list '" + $s + "'");
  }
  # incorrectBreakpointsList
| breakpointsListElements   
  {
  	$s = $breakpointsListElements.text;
  	$l = $s.length();
  	notifyErrorListeners($l + "|Missing '()' for breakpoints list '" + $s + "'");
  }
  # incorrectBreakpointsList
| incorrectLeftParenthesis breakpointsListElements incorrectRightParenthesis 
  {
  	$s = $breakpointsListElements.text;
  	$l = $s.length();
  	if ($incorrectLeftParenthesis.text != null) {
  		$l = $l + $incorrectLeftParenthesis.text.length();
  	}
  	if ($incorrectRightParenthesis.text != null) {
  		$l = $l + $incorrectRightParenthesis.text.length();
  	}
  	notifyErrorListeners($l + "|Wrong bracket format for breakpoints list '" + $s + "', expecting '()'");
  }
  # incorrectBreakpointsList 
;
breakpointsListElements
: breakpointsInOneChr (';' breakpointsInOneChr)*  
| incorrectBreakpointsListElements 
  {
  	notifyErrorListeners($incorrectBreakpointsListElements.text.length() + "|Incorrect use of separators inside breakpoints list '" + $incorrectBreakpointsListElements.text + "', expecting ';'");
  }
  ;                          
incorrectBreakpointsListElements: breakpointsInOneChr ((';'|',') breakpointsInOneChr)+ ; 
breakpointsInOneChr: breakpoints                                            # normalBreakpoints
                   | breakpoints (('o' | 'O') ('r' | 'R') breakpoints)+     # multipleInterpretationBreakpoints
                   ;                                
rChrList locals [String s = "", int l = 0]
: '(' chrListElements ')'   # correctRChrList 
| chrListElements ')' 
  {
  	$s = $chrListElements.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing '(' for chromosome list '" + $s + "'");
  } 
  # incorrectRChrList 	
| '(' chrListElements
  {
  	$s = $chrListElements.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing ')' for chromosome list '" + $s + "'");
  }
  # incorrectRChrList  
| chrListElements
  {
  	$s = $chrListElements.text;
  	$l = $s.length();
  	notifyErrorListeners($l + "|Missing '()' for chromosome list '" + $s + "'");
  }
  # incorrectRChrList  
| incorrectLeftParenthesis chrListElements incorrectRightParenthesis 
  {
  	$s = $chrListElements.text;
  	$l = $s.length();
  	if ($incorrectLeftParenthesis.text != null) {
  		$l = $l + $incorrectLeftParenthesis.text.length();
  	}
  	if ($incorrectRightParenthesis.text != null) {
  		$l = $l + $incorrectRightParenthesis.text.length();
  	}
  	notifyErrorListeners($l + "|Wrong bracket format for chromosome list '" + $s + "', expecting '()'");
  }
  # incorrectRChrList
;
rBreakpointsList locals [String s = "", int l = 0]
: '(' breakpointsListElements ')'   # correctRBreakpointsList 
| breakpointsListElements ')' 
  {
  	$s = $breakpointsListElements.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing '(' for breakpoints list '" + $s + "'");
  }
  # incorrectRBreakpointsList
| '(' breakpointsListElements
  {
  	$s = $breakpointsListElements.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing ')' for breakpoints list '" + $s + "'");
  }
  # incorrectRBreakpointsList
| breakpointsListElements   
  {
  	$s = $breakpointsListElements.text;
  	$l = $s.length();
  	notifyErrorListeners($l + "|Missing '()' for breakpoints list '" + $s + "'");
  }
  # incorrectRBreakpointsList
| incorrectLeftParenthesis breakpointsListElements incorrectRightParenthesis 
  {
  	$s = $breakpointsListElements.text;
  	$l = $s.length();
  	if ($incorrectLeftParenthesis.text != null) {
  		$l = $l + $incorrectLeftParenthesis.text.length();
  	}
  	if ($incorrectRightParenthesis.text != null) {
  		$l = $l + $incorrectRightParenthesis.text.length();
  	}
  	notifyErrorListeners($l + "|Wrong bracket format for breakpoints list '" + $s + "', expecting '()'");
  }
  # incorrectRBreakpointsList
;


idUncertainChr: ('a' | 'A') ('d' | 'D') ('d' | 'D') 
              | ('d' | 'D') ('e' | 'E') ('l' | 'L')
              | (('p' | 'P') ('s' | 'S') ('u' | 'U'))? ('i' | 'I') ('d' | 'D') ('i' | 'I') ('c' | 'C') 
              | ('d' | 'D') ('u' | 'U') ('p' | 'P') 
              | ('t' | 'T') ('r' | 'R') ('p' | 'P') 
              | ('q' | 'Q') ('d' | 'D') ('p' | 'P') 
              | ('h' | 'H') ('s' | 'S') ('r' | 'R') 
              | ('i' | 'I') ('n' | 'N') ('s' | 'S') 
              | ('i' | 'I') ('n' | 'N') ('v' | 'V')
              | ('i' | 'I')
              ;                
uncertainChr locals [String s = "", int l = 0]
: '(' uncertainChrContent ')'   # correctUncertainChr
| uncertainChrContent ')' 
  {
  	$s = $uncertainChrContent.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing '(' for an uncertain chr '" + $s + "'");
  }
  # incorrectUncertainChr
| '(' uncertainChrContent
  {
  	$s = $uncertainChrContent.text;
  	$l = $s.length() + 1;
  	notifyErrorListeners($l + "|Missing ')' for an uncertain chr '" + $s + "'");
  }
  # incorrectUncertainChr
| uncertainChrContent 
  {
  	$s = $uncertainChrContent.text;
  	$l = $s.length();
  	notifyErrorListeners($l + "|Missing '()' for an uncertain chr '" + $s + "'");
  }
  # incorrectUncertainChr
| incorrectLeftParenthesis uncertainChrContent incorrectRightParenthesis  
  {
  	$s = $uncertainChrContent.text;
  	$l = $s.length();
  	if ($incorrectLeftParenthesis.text != null) {
  		$l = $l + $incorrectLeftParenthesis.text.length();
  	}
  	if ($incorrectRightParenthesis.text != null) {
  		$l = $l + $incorrectRightParenthesis.text.length();
  	}
  	notifyErrorListeners($l + "|Wrong bracket format for an uncertain chr '" + $s + "', expecting '()'");
  }
  # incorrectUncertainChr   	
;
uncertainChrContent: chr QUES? arm ; 

               
     
arm: 'p' | 'P' | 'q' | 'Q' ;
band: QUES? arm (digit | QUES)+ | QUES (digit | QUES)+ | QUES | arm numRangeTypeII ;
breakpoint: band subband? ;
breakpoints: breakpoint+  ;   
c: 'c' | 'C';    
cellNum locals [int l = 0]
: '[' cellNumContent ']'  # correctCellNum
| cellNumContent ']'
  {
  	$l = $cellNumContent.text.length() + 1;
  	notifyErrorListeners($l + "|Missing '[' for the cell number designation");
  }
  # incorrectCellNum
| '[' cellNumContent
  {
  	$l = $cellNumContent.text.length() + 1;
  	notifyErrorListeners($l + "|Missing ']' for the cell number designation");
  }
  # incorrectCellNum
| incorrectLeftParenthesisII cellNumContent incorrectRightParenthesis
  {
  	$l = $cellNumContent.text.length();
  	$l = $l + $incorrectLeftParenthesisII.text.length();
  	if ($incorrectRightParenthesis.text != null) {
  		$l = $l + $incorrectRightParenthesis.text.length();
  	}
  	notifyErrorListeners($l + "|Wrong bracket format for the cell number designation");
  }
  # incorrectCellNum 
| cp? ('i' | 'I') ('n' | 'N') cellNumContent 
  {
  	$l = $cellNumContent.text.length() + 2;
  	if ($cp.text != null) {
  		$l = $l + $cp.text.length();
  	}
  	notifyErrorListeners($l + "|Wrong expression for the cell number designation");
  }
  # incorrectCellNum
| cellNumContent ('c' | 'C') ('e' | 'E') ('l' | 'L') ('l' | 'L') (s='s' | s='S')?
  {
  	$l = $cellNumContent.text.length() + 4;
  	if ($s.text != null) {
  		$l = $l + 1;
  	}
  	notifyErrorListeners($l + "|Wrong expression for the cell number designation");
  }
  # incorrectCellNum         
;
cellNumContent locals [int l = 0]
: cp? integer 
| integer cp
  {
  	$l = $integer.text.length() + 2;
  	notifyErrorListeners($l + "|Incorrect expression of composite karyotype (cp)");
  }
| cp? integer ('c' | 'C') ('e' | 'E') ('l' | 'L') ('l' | 'L') (s='s' | s='S')?
  {
  	$l = $integer.text.length() + 4;
  	if ($cp.text != null) {
  		$l = $l + 2;
  	}
  	if ($s.text != null) {
  		$l = $l + 1;
  	}
  	notifyErrorListeners($l + "|Incorrect addition of the word 'cell(s)', expecting integer only");
  } 
| integer ('c' | 'C') ('e' | 'E') ('l' | 'L') ('l' | 'L') (s='s' | s='S')?  cp
  {
  	$l = $integer.text.length() + 4;
  	if ($cp.text != null) {
  		$l = $l + 2;
  	}
  	if ($s.text != null) {
  		$l = $l + 1;
  	}
  	notifyErrorListeners($l + "|i) Incorrect addition of the word 'cell(s)', expecting integer only; ii) Incorrect expression of composite karyotype (cp)");
  }
;      
incorrectLeftParenthesisII: ('['|'{'|'<'|'(')+;
incorrectRightParenthesisII: (']'|'}'|'>'|')')+;              
cen: QUES? arm integer | QUES integer | QUES ;
chr: QUES? (integer | sex) | digit QUES digit? | QUES ;
chrNum: numRangeTypeI | QUES+ ;
cp: ('c' | 'C') ('p'  | 'P');
derBreakpoints: cen ;
derChr: QUES? (integer | sex) | digit QUES digit? | QUES ;
derId: ('i' | 'I') ('d' | 'D') ('e' | 'E') ('r' | 'R') 
     | ('d' | 'D') ('e' | 'E') ('r' | 'R') ;
digit: '0' | POSITIVEDIGIT ;
dminNum: numRangeTypeI | numRangeTypeIII ;
gainChr: integer | sex ;
id: ('a' | 'A') ('d' | 'D') ('d' | 'D') 
  | ('d' | 'D') ('e' | 'E') ('l' | 'L') 
  | (('p' | 'P') ('s' | 'S') ('u' | 'U'))? ('d' | 'D') ('i' | 'I') ('c' | 'C') 
  | (('p' | 'P') ('s' | 'S') ('u' | 'U'))? ('i' | 'I') ('d' | 'D') ('i' | 'I') ('c' | 'C') 
  | ('d' | 'D') ('u' | 'U') ('p' | 'P') 
  | ('t' | 'T') ('r' | 'R') ('p' | 'P') 
  | ('q' | 'Q') ('d' | 'D') ('p' | 'P') 
  | ('h' | 'H') ('s' | 'S') ('r' | 'R') 
  | ('i' | 'I') ('n' | 'N') ('s' | 'S') 
  | ('i' | 'I') ('n' | 'N') ('v' | 'V') 
  | ('i' | 'I') 
  | (('d' | 'D') ('i' | 'I') ('c' | 'C') | ('t' | 'T') ('r' | 'R') ('c' | 'C'))? ('r' | 'R') 
  | ('t' | 'T') ('a' | 'A') ('s' | 'S') 
  | ('t' | 'T') 
  | (('p' | 'P') ('s' | 'S') ('u' | 'U'))? ('t' | 'T') ('r' | 'R') ('c' | 'C') 
  | ('r' | 'R') ('o' | 'O') ('b' | 'B')
  ;
inh: ('d' | 'D') ('n' | 'N') | ('i' | 'I') ('n' | 'N') ('h' | 'H') | ('m' | 'M') ('a' | 'A') ('t' | 'T') | ('p' | 'P') ('a' | 'A') ('t' | 'T') ;
integer: digit+ ;
lossChr: integer | sex ;
marNum: numRangeTypeI ;
modalDesc: modalPrefix modalLevel modalSuffix ;
modalLevel: ('h' | 'H') ('a' | 'A')  
          | ('d' | 'D') ('i' | 'I')
          | ('t' | 'T') ('r' | 'R') ('i' | 'I')
          | ('t' | 'T') ('e' | 'E') ('t' | 'T') ('r' | 'R') ('a' | 'A')
          | ('p' | 'P') ('e' | 'E') ('n' | 'N') ('t' | 'T') ('a' | 'A')
          | ('h' | 'H') ('e' | 'E') ('x' | 'X') ('a' | 'A')
          | ('h' | 'H') ('e' | 'E') ('p' | 'P') ('t' | 'T') ('a' | 'A')
          | ('o' | 'O') ('c' | 'C') ('t' | 'T') ('a' | 'A')
          ;
modalPrefix: ('n' | 'N') ('e' | 'E') ('a' | 'A') ('r' | 'R') '-'?
           | ('h' | 'H') ('y' | 'Y') ('p' | 'P') ('o' | 'O')
           | ('h' | 'H') ('y' | 'Y') ('p' | 'P') ('e' | 'E') ('r' | 'R')
           | ('p' | 'P') ('s' | 'S') ('e' | 'E') ('u' | 'U') ('d' | 'D') ('o' | 'O')
           ;
modalSuffix: ('p' | 'P') ('l' | 'L') ('o' | 'O') ('i' | 'I') ('d' | 'D') ;

mosChi: ('m' | 'M') ('o' | 'O') ('s' | 'S') | ('c' | 'C') ('h' | 'H') ('i' | 'I') ;
multiplication: ('x' | 'X') integer ;
numRangeTypeI: integer ((APPROX | MINUS) integer)? ;
numRangeTypeII: integer (APPROX | MINUS) integer ;
numRangeTypeIII: (APPROX | MINUS) integer ;  
prefix: (PLUS | MINUS)? QUES | QUES? (PLUS | MINUS) ;
prefixMinus: MINUS QUES? | QUES MINUS ; 
prefixPlus: PLUS QUES? | QUES PLUS ;
rId: ('a' | 'A') ('d' | 'D') ('d' | 'D') 
   | ('d' | 'D') ('e' | 'E') ('l' | 'L') 
   | ('d' | 'D') ('u' | 'U') ('p' | 'P') 
   | ('t' | 'T') ('r' | 'R') ('p' | 'P') 
   | ('q' | 'Q') ('d' | 'D') ('p' | 'P') 
   | ('h' | 'H') ('s' | 'S') ('r' | 'R') 
   | ('i' | 'I') ('n' | 'N') ('s' | 'S') 
   | ('i' | 'I') ('n' | 'N') ('v' | 'V') 
   | ('r' | 'R') 
   | ('t' | 'T')
   ; 
sex: 'x' | 'X' | 'y' | 'Y' ;
sexChr: (sex | QUES)+ ;
suffix: (c | inh)? multiplication | multiplication? (c | inh) ;
subband: '.' ((digit | QUES)+ | numRangeTypeII) ;
undeterminedPrefix: (MINUS PLUS | PLUS MINUS ) QUES? | QUES (MINUS PLUS | PLUS MINUS ) ;

APPROX: '~' ;
POSITIVEDIGIT: [1-9] ;
MINUS: '-' ;
PLUS: '+' ;
QUES: '?' ;
LETTER: [a-zA-Z] ;

WS : [ \t\r\n]+ -> skip ;
