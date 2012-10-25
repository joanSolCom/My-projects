package ie;

// $ANTLR 2.7.7 (2006-11-01): "ieParser.g" -> "IEParser.java"$

  

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;
import java.io.Serializable;

public class IEParser extends antlr.LLkParser       implements IEParserTokenTypes,Serializable
 {

  public static final String t2S(int i) {
    return _tokenNames[i];
  }

protected IEParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public IEParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected IEParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public IEParser(TokenStream lexer) {
  this(lexer,1);
}

public IEParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void program() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST program_AST = null;
		
		global();
		astFactory.addASTChild(currentAST, returnAST);
		main();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop3:
		do {
			if ((LA(1)==DDOT)) {
				modules();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop3;
			}
			
		} while (true);
		}
		match(Token.EOF_TYPE);
		program_AST = (IEAST)currentAST.root;
		program_AST=(IEAST)astFactory.make( (new ASTArray(2)).add((IEAST)astFactory.create(LIST,"program")).add(program_AST)) ;
		currentAST.root = program_AST;
		currentAST.child = program_AST!=null &&program_AST.getFirstChild()!=null ?
			program_AST.getFirstChild() : program_AST;
		currentAST.advanceChildToEnd();
		program_AST = (IEAST)currentAST.root;
		returnAST = program_AST;
	}
	
	public final void global() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST global_AST = null;
		
		IEAST tmp2_AST = null;
		tmp2_AST = (IEAST)astFactory.create(LT(1));
		astFactory.makeASTRoot(currentAST, tmp2_AST);
		match(DDOT);
		IEAST tmp3_AST = null;
		tmp3_AST = (IEAST)astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp3_AST);
		match(ID);
		dec_know();
		astFactory.addASTChild(currentAST, returnAST);
		global_AST = (IEAST)currentAST.root;
		returnAST = global_AST;
	}
	
	public final void main() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST main_AST = null;
		
		IEAST tmp4_AST = null;
		tmp4_AST = (IEAST)astFactory.create(LT(1));
		astFactory.makeASTRoot(currentAST, tmp4_AST);
		match(DDOT);
		IEAST tmp5_AST = null;
		tmp5_AST = (IEAST)astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp5_AST);
		match(ID);
		mod_bloc();
		astFactory.addASTChild(currentAST, returnAST);
		main_AST = (IEAST)currentAST.root;
		returnAST = main_AST;
	}
	
	public final void modules() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST modules_AST = null;
		
		IEAST tmp6_AST = null;
		tmp6_AST = (IEAST)astFactory.create(LT(1));
		astFactory.makeASTRoot(currentAST, tmp6_AST);
		match(DDOT);
		IEAST tmp7_AST = null;
		tmp7_AST = (IEAST)astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp7_AST);
		match(ID);
		mod_bloc();
		astFactory.addASTChild(currentAST, returnAST);
		modules_AST = (IEAST)currentAST.root;
		returnAST = modules_AST;
	}
	
	public final void dec_know() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST dec_know_AST = null;
		
		{
		_loop9:
		do {
			if ((_tokenSet_0.member(LA(1)))) {
				knowD();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop9;
			}
			
		} while (true);
		}
		dec_know_AST = (IEAST)currentAST.root;
		dec_know_AST=(IEAST)astFactory.make( (new ASTArray(2)).add((IEAST)astFactory.create(LIST,"dec_know")).add(dec_know_AST)) ;
		currentAST.root = dec_know_AST;
		currentAST.child = dec_know_AST!=null &&dec_know_AST.getFirstChild()!=null ?
			dec_know_AST.getFirstChild() : dec_know_AST;
		currentAST.advanceChildToEnd();
		dec_know_AST = (IEAST)currentAST.root;
		returnAST = dec_know_AST;
	}
	
	public final void mod_bloc() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST mod_bloc_AST = null;
		
		{
		_loop12:
		do {
			if ((_tokenSet_0.member(LA(1)))) {
				term();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop12;
			}
			
		} while (true);
		}
		mod_bloc_AST = (IEAST)currentAST.root;
		mod_bloc_AST=(IEAST)astFactory.make( (new ASTArray(2)).add((IEAST)astFactory.create(LIST,"mod_bloc")).add(mod_bloc_AST)) ;
		currentAST.root = mod_bloc_AST;
		currentAST.child = mod_bloc_AST!=null &&mod_bloc_AST.getFirstChild()!=null ?
			mod_bloc_AST.getFirstChild() : mod_bloc_AST;
		currentAST.advanceChildToEnd();
		mod_bloc_AST = (IEAST)currentAST.root;
		returnAST = mod_bloc_AST;
	}
	
	public final void knowD() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST knowD_AST = null;
		
		switch ( LA(1)) {
		case INTCONST:
		case REALCONST:
		case OSB:
		case ID:
		case NOT:
		case MINUS:
		case SHARP:
		case OP:
		case OCURLY:
		{
			expr();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case ASIG:
			{
				IEAST tmp8_AST = null;
				tmp8_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp8_AST);
				match(ASIG);
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				match(DOT);
				break;
			}
			case MACRO:
			{
				IEAST tmp10_AST = null;
				tmp10_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp10_AST);
				match(MACRO);
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				match(DOT);
				break;
			}
			case DDDOT:
			{
				IEAST tmp12_AST = null;
				tmp12_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp12_AST);
				match(DDDOT);
				type();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			knowD_AST = (IEAST)currentAST.root;
			break;
		}
		case ABORT:
		case PRINT:
		{
			{
			switch ( LA(1)) {
			case PRINT:
			{
				IEAST tmp13_AST = null;
				tmp13_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp13_AST);
				match(PRINT);
				break;
			}
			case ABORT:
			{
				IEAST tmp14_AST = null;
				tmp14_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp14_AST);
				match(ABORT);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case INTCONST:
			case REALCONST:
			case OSB:
			case ID:
			case NOT:
			case MINUS:
			case SHARP:
			case OP:
			case OCURLY:
			case STRING:
			{
				exprs();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case DOT:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(DOT);
			knowD_AST = (IEAST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = knowD_AST;
	}
	
	public final void term() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST term_AST = null;
		
		{
		switch ( LA(1)) {
		case INTCONST:
		case REALCONST:
		case OSB:
		case ID:
		case NOT:
		case MINUS:
		case SHARP:
		case OP:
		case OCURLY:
		{
			expr();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case ASIG:
			{
				IEAST tmp16_AST = null;
				tmp16_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp16_AST);
				match(ASIG);
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				match(DOT);
				break;
			}
			case MACRO:
			{
				IEAST tmp18_AST = null;
				tmp18_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp18_AST);
				match(MACRO);
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				match(DOT);
				break;
			}
			case NORMAL:
			{
				IEAST tmp20_AST = null;
				tmp20_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp20_AST);
				match(NORMAL);
				ik();
				astFactory.addASTChild(currentAST, returnAST);
				match(DOT);
				break;
			}
			case PERSISTENT:
			{
				IEAST tmp22_AST = null;
				tmp22_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp22_AST);
				match(PERSISTENT);
				ik();
				astFactory.addASTChild(currentAST, returnAST);
				match(DOT);
				break;
			}
			case DDDOT:
			{
				IEAST tmp24_AST = null;
				tmp24_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp24_AST);
				match(DDDOT);
				type();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			break;
		}
		case ABORT:
		case PRINT:
		{
			{
			switch ( LA(1)) {
			case PRINT:
			{
				IEAST tmp25_AST = null;
				tmp25_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp25_AST);
				match(PRINT);
				break;
			}
			case ABORT:
			{
				IEAST tmp26_AST = null;
				tmp26_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp26_AST);
				match(ABORT);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case INTCONST:
			case REALCONST:
			case OSB:
			case ID:
			case NOT:
			case MINUS:
			case SHARP:
			case OP:
			case OCURLY:
			case STRING:
			{
				exprs();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case DOT:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(DOT);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		term_AST = (IEAST)currentAST.root;
		returnAST = term_AST;
	}
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST expr_AST = null;
		
		expr2();
		astFactory.addASTChild(currentAST, returnAST);
		expr_AST = (IEAST)currentAST.root;
		returnAST = expr_AST;
	}
	
	public final void ik() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST ik_AST = null;
		
		{
		switch ( LA(1)) {
		case INTCONST:
		case REALCONST:
		case OSB:
		case ID:
		case ABORT:
		case PRINT:
		case NOT:
		case MINUS:
		case SHARP:
		case OP:
		case OCURLY:
		{
			know();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop21:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					know();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop21;
				}
				
			} while (true);
			}
			break;
		}
		case DOT:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		ik_AST = (IEAST)currentAST.root;
		ik_AST=(IEAST)astFactory.make( (new ASTArray(2)).add((IEAST)astFactory.create(LIST,"list_knows")).add(ik_AST)) ;
		currentAST.root = ik_AST;
		currentAST.child = ik_AST!=null &&ik_AST.getFirstChild()!=null ?
			ik_AST.getFirstChild() : ik_AST;
		currentAST.advanceChildToEnd();
		ik_AST = (IEAST)currentAST.root;
		returnAST = ik_AST;
	}
	
	public final void type() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST type_AST = null;
		
		typeB();
		astFactory.addASTChild(currentAST, returnAST);
		{
		switch ( LA(1)) {
		case APP:
		{
			IEAST tmp29_AST = null;
			tmp29_AST = (IEAST)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp29_AST);
			match(APP);
			type();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case EOF:
		case INTCONST:
		case REALCONST:
		case DDOT:
		case OSB:
		case CSB:
		case ID:
		case COMMA:
		case DOT:
		case ABORT:
		case PRINT:
		case NOT:
		case MINUS:
		case SHARP:
		case OP:
		case CP:
		case OCURLY:
		case CCURLY:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		type_AST = (IEAST)currentAST.root;
		returnAST = type_AST;
	}
	
	public final void exprs() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST exprs_AST = null;
		
		switch ( LA(1)) {
		case INTCONST:
		case REALCONST:
		case OSB:
		case ID:
		case NOT:
		case MINUS:
		case SHARP:
		case OP:
		case OCURLY:
		{
			expr();
			astFactory.addASTChild(currentAST, returnAST);
			exprs_AST = (IEAST)currentAST.root;
			break;
		}
		case STRING:
		{
			IEAST tmp30_AST = null;
			tmp30_AST = (IEAST)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp30_AST);
			match(STRING);
			exprs_AST = (IEAST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = exprs_AST;
	}
	
	public final void know() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST know_AST = null;
		
		switch ( LA(1)) {
		case INTCONST:
		case REALCONST:
		case OSB:
		case ID:
		case NOT:
		case MINUS:
		case SHARP:
		case OP:
		case OCURLY:
		{
			expr();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case ASIG:
			{
				IEAST tmp31_AST = null;
				tmp31_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp31_AST);
				match(ASIG);
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case MACRO:
			{
				IEAST tmp32_AST = null;
				tmp32_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp32_AST);
				match(MACRO);
				expr();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case DDDOT:
			{
				IEAST tmp33_AST = null;
				tmp33_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp33_AST);
				match(DDDOT);
				type();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			know_AST = (IEAST)currentAST.root;
			break;
		}
		case ABORT:
		case PRINT:
		{
			{
			switch ( LA(1)) {
			case PRINT:
			{
				IEAST tmp34_AST = null;
				tmp34_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp34_AST);
				match(PRINT);
				break;
			}
			case ABORT:
			{
				IEAST tmp35_AST = null;
				tmp35_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp35_AST);
				match(ABORT);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case INTCONST:
			case REALCONST:
			case OSB:
			case ID:
			case NOT:
			case MINUS:
			case SHARP:
			case OP:
			case OCURLY:
			case STRING:
			{
				exprs();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case COMMA:
			case DOT:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			know_AST = (IEAST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = know_AST;
	}
	
	public final void typeB() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST typeB_AST = null;
		
		switch ( LA(1)) {
		case OSB:
		{
			IEAST tmp36_AST = null;
			tmp36_AST = (IEAST)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp36_AST);
			match(OSB);
			type();
			astFactory.addASTChild(currentAST, returnAST);
			match(CSB);
			typeB_AST = (IEAST)currentAST.root;
			break;
		}
		case OCURLY:
		{
			IEAST tmp38_AST = null;
			tmp38_AST = (IEAST)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp38_AST);
			match(OCURLY);
			type();
			astFactory.addASTChild(currentAST, returnAST);
			{
			int _cnt34=0;
			_loop34:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					type();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt34>=1 ) { break _loop34; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt34++;
			} while (true);
			}
			match(CCURLY);
			typeB_AST = (IEAST)currentAST.root;
			break;
		}
		case ID:
		{
			IEAST tmp41_AST = null;
			tmp41_AST = (IEAST)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp41_AST);
			match(ID);
			typeB_AST = (IEAST)currentAST.root;
			break;
		}
		case UNIF2:
		case MUL:
		{
			uni();
			astFactory.addASTChild(currentAST, returnAST);
			typeB_AST = (IEAST)currentAST.root;
			break;
		}
		case OP:
		{
			match(OP);
			type();
			astFactory.addASTChild(currentAST, returnAST);
			match(CP);
			typeB_AST = (IEAST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = typeB_AST;
	}
	
	public final void uni() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST uni_AST = null;
		
		switch ( LA(1)) {
		case MUL:
		{
			IEAST tmp44_AST = null;
			tmp44_AST = (IEAST)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp44_AST);
			match(MUL);
			uni_AST = (IEAST)currentAST.root;
			break;
		}
		case UNIF2:
		{
			IEAST tmp45_AST = null;
			tmp45_AST = (IEAST)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp45_AST);
			match(UNIF2);
			uni_AST = (IEAST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = uni_AST;
	}
	
	public final void expr2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST expr2_AST = null;
		
		expr3();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop39:
		do {
			if ((LA(1)==OR)) {
				IEAST tmp46_AST = null;
				tmp46_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp46_AST);
				match(OR);
				expr3();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop39;
			}
			
		} while (true);
		}
		expr2_AST = (IEAST)currentAST.root;
		returnAST = expr2_AST;
	}
	
	public final void expr3() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST expr3_AST = null;
		
		expr4();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop42:
		do {
			if ((LA(1)==AND)) {
				IEAST tmp47_AST = null;
				tmp47_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp47_AST);
				match(AND);
				expr4();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop42;
			}
			
		} while (true);
		}
		expr3_AST = (IEAST)currentAST.root;
		returnAST = expr3_AST;
	}
	
	public final void expr4() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST expr4_AST = null;
		
		exprC();
		astFactory.addASTChild(currentAST, returnAST);
		{
		switch ( LA(1)) {
		case EQUAL:
		case NOTEQUAL:
		case LESSEQUAL:
		case GREATEQUAL:
		case LESS:
		case GREAT:
		{
			{
			switch ( LA(1)) {
			case EQUAL:
			{
				IEAST tmp48_AST = null;
				tmp48_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp48_AST);
				match(EQUAL);
				break;
			}
			case NOTEQUAL:
			{
				IEAST tmp49_AST = null;
				tmp49_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp49_AST);
				match(NOTEQUAL);
				break;
			}
			case LESSEQUAL:
			{
				IEAST tmp50_AST = null;
				tmp50_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp50_AST);
				match(LESSEQUAL);
				break;
			}
			case GREATEQUAL:
			{
				IEAST tmp51_AST = null;
				tmp51_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp51_AST);
				match(GREATEQUAL);
				break;
			}
			case LESS:
			{
				IEAST tmp52_AST = null;
				tmp52_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp52_AST);
				match(LESS);
				break;
			}
			case GREAT:
			{
				IEAST tmp53_AST = null;
				tmp53_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp53_AST);
				match(GREAT);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			exprC();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case DDDOT:
		case CSB:
		case ASIG:
		case COMMA:
		case DOT:
		case MACRO:
		case NORMAL:
		case PERSISTENT:
		case OR:
		case AND:
		case CP:
		case CCURLY:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		expr4_AST = (IEAST)currentAST.root;
		returnAST = expr4_AST;
	}
	
	public final void exprC() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST exprC_AST = null;
		
		expr5();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop49:
		do {
			if ((LA(1)==PLUSPLUS||LA(1)==MINUSMINUS)) {
				{
				switch ( LA(1)) {
				case PLUSPLUS:
				{
					IEAST tmp54_AST = null;
					tmp54_AST = (IEAST)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp54_AST);
					match(PLUSPLUS);
					break;
				}
				case MINUSMINUS:
				{
					IEAST tmp55_AST = null;
					tmp55_AST = (IEAST)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp55_AST);
					match(MINUSMINUS);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				expr5();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop49;
			}
			
		} while (true);
		}
		exprC_AST = (IEAST)currentAST.root;
		returnAST = exprC_AST;
	}
	
	public final void expr5() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST expr5_AST = null;
		
		expr6();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop53:
		do {
			if ((LA(1)==PLUS||LA(1)==MINUS)) {
				{
				switch ( LA(1)) {
				case PLUS:
				{
					IEAST tmp56_AST = null;
					tmp56_AST = (IEAST)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp56_AST);
					match(PLUS);
					break;
				}
				case MINUS:
				{
					IEAST tmp57_AST = null;
					tmp57_AST = (IEAST)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp57_AST);
					match(MINUS);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				expr6();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop53;
			}
			
		} while (true);
		}
		expr5_AST = (IEAST)currentAST.root;
		returnAST = expr5_AST;
	}
	
	public final void expr6() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST expr6_AST = null;
		
		expr8();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop57:
		do {
			if (((LA(1) >= MUL && LA(1) <= REST))) {
				{
				switch ( LA(1)) {
				case MUL:
				{
					IEAST tmp58_AST = null;
					tmp58_AST = (IEAST)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp58_AST);
					match(MUL);
					break;
				}
				case DIV:
				{
					IEAST tmp59_AST = null;
					tmp59_AST = (IEAST)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp59_AST);
					match(DIV);
					break;
				}
				case REST:
				{
					IEAST tmp60_AST = null;
					tmp60_AST = (IEAST)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp60_AST);
					match(REST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				expr8();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop57;
			}
			
		} while (true);
		}
		expr6_AST = (IEAST)currentAST.root;
		returnAST = expr6_AST;
	}
	
	public final void expr8() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST expr8_AST = null;
		
		switch ( LA(1)) {
		case NOT:
		case MINUS:
		{
			{
			switch ( LA(1)) {
			case NOT:
			{
				IEAST tmp61_AST = null;
				tmp61_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp61_AST);
				match(NOT);
				break;
			}
			case MINUS:
			{
				IEAST tmp62_AST = null;
				tmp62_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp62_AST);
				match(MINUS);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			expr8();
			astFactory.addASTChild(currentAST, returnAST);
			expr8_AST = (IEAST)currentAST.root;
			break;
		}
		case INTCONST:
		case REALCONST:
		case OSB:
		case ID:
		case SHARP:
		case OP:
		case OCURLY:
		{
			exprB();
			astFactory.addASTChild(currentAST, returnAST);
			expr8_AST = (IEAST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = expr8_AST;
	}
	
	public final void exprB() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST exprB_AST = null;
		
		switch ( LA(1)) {
		case INTCONST:
		case REALCONST:
		case OSB:
		case ID:
		case OP:
		case OCURLY:
		{
			expr9();
			astFactory.addASTChild(currentAST, returnAST);
			exprB_AST = (IEAST)currentAST.root;
			break;
		}
		case SHARP:
		{
			IEAST tmp63_AST = null;
			tmp63_AST = (IEAST)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp63_AST);
			match(SHARP);
			expr9();
			astFactory.addASTChild(currentAST, returnAST);
			exprB_AST = (IEAST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = exprB_AST;
	}
	
	public final void expr9() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST expr9_AST = null;
		
		expr11();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop63:
		do {
			if ((LA(1)==INDEX)) {
				IEAST tmp64_AST = null;
				tmp64_AST = (IEAST)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp64_AST);
				match(INDEX);
				expr11();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop63;
			}
			
		} while (true);
		}
		expr9_AST = (IEAST)currentAST.root;
		returnAST = expr9_AST;
	}
	
	public final void expr11() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST expr11_AST = null;
		
		switch ( LA(1)) {
		case ID:
		{
			IEAST tmp65_AST = null;
			tmp65_AST = (IEAST)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp65_AST);
			match(ID);
			{
			_loop66:
			do {
				switch ( LA(1)) {
				case ID:
				{
					IEAST tmp66_AST = null;
					tmp66_AST = (IEAST)astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp66_AST);
					match(ID);
					break;
				}
				case INTCONST:
				case REALCONST:
				case OSB:
				case OP:
				case OCURLY:
				{
					expr12();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					break _loop66;
				}
				}
			} while (true);
			}
			expr11_AST = (IEAST)currentAST.root;
			break;
		}
		case INTCONST:
		case REALCONST:
		case OSB:
		case OP:
		case OCURLY:
		{
			expr12();
			astFactory.addASTChild(currentAST, returnAST);
			expr11_AST = (IEAST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = expr11_AST;
	}
	
	public final void expr12() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST expr12_AST = null;
		
		switch ( LA(1)) {
		case OP:
		{
			match(OP);
			expr();
			astFactory.addASTChild(currentAST, returnAST);
			match(CP);
			expr12_AST = (IEAST)currentAST.root;
			break;
		}
		case INTCONST:
		{
			IEAST tmp69_AST = null;
			tmp69_AST = (IEAST)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp69_AST);
			match(INTCONST);
			expr12_AST = (IEAST)currentAST.root;
			break;
		}
		case REALCONST:
		{
			IEAST tmp70_AST = null;
			tmp70_AST = (IEAST)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp70_AST);
			match(REALCONST);
			expr12_AST = (IEAST)currentAST.root;
			break;
		}
		case OCURLY:
		{
			tuple();
			astFactory.addASTChild(currentAST, returnAST);
			expr12_AST = (IEAST)currentAST.root;
			break;
		}
		case OSB:
		{
			list();
			astFactory.addASTChild(currentAST, returnAST);
			expr12_AST = (IEAST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = expr12_AST;
	}
	
	public final void tuple() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST tuple_AST = null;
		
		IEAST tmp71_AST = null;
		tmp71_AST = (IEAST)astFactory.create(LT(1));
		astFactory.makeASTRoot(currentAST, tmp71_AST);
		match(OCURLY);
		expr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		int _cnt70=0;
		_loop70:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				expr();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				if ( _cnt70>=1 ) { break _loop70; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt70++;
		} while (true);
		}
		match(CCURLY);
		tuple_AST = (IEAST)currentAST.root;
		returnAST = tuple_AST;
	}
	
	public final void list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		IEAST list_AST = null;
		
		IEAST tmp74_AST = null;
		tmp74_AST = (IEAST)astFactory.create(LT(1));
		astFactory.makeASTRoot(currentAST, tmp74_AST);
		match(OSB);
		{
		switch ( LA(1)) {
		case INTCONST:
		case REALCONST:
		case OSB:
		case ID:
		case NOT:
		case MINUS:
		case SHARP:
		case OP:
		case OCURLY:
		{
			expr();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop74:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					expr();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop74;
				}
				
			} while (true);
			}
			break;
		}
		case CSB:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(CSB);
		list_AST = (IEAST)currentAST.root;
		returnAST = list_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"INTCONST",
		"REALCONST",
		"DDOT",
		"DDDOT",
		"APP",
		"UNIF2",
		"OSB",
		"CSB",
		"ID",
		"ASIG",
		"COMMA",
		"DOT",
		"MACRO",
		"NORMAL",
		"PERSISTENT",
		"ABORT",
		"PRINT",
		"INDEX",
		"NOT",
		"OR",
		"AND",
		"EQUAL",
		"NOTEQUAL",
		"LESSEQUAL",
		"GREATEQUAL",
		"LESS",
		"GREAT",
		"MUL",
		"DIV",
		"REST",
		"PLUS",
		"MINUS",
		"PLUSPLUS",
		"MINUSMINUS",
		"SHARP",
		"OP",
		"CP",
		"OCURLY",
		"CCURLY",
		"STRING",
		"CONST",
		"LETTER",
		"DIGIT",
		"COMMENT",
		"WHITESPACE",
		"LIST"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 3058022487088L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	
	}
