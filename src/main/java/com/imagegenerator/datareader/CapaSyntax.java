
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package com.imagegenerator.datareader;

import com.imagegenerator.treebb.BBTree;
import com.imagegenerator.matrix.SparseMatrix;
import java_cup.runtime.XMLElement;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class CapaSyntax extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return symCapa.class;
}

  /** Default constructor. */
  @Deprecated
  public CapaSyntax() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public CapaSyntax(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public CapaSyntax(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\007\000\002\002\004\000\002\002\003\000\002\003" +
    "\011\000\002\003\003\000\002\004\005\000\002\005\004" +
    "\000\002\005\002" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\021\000\006\002\ufffb\011\006\001\002\000\006\002" +
    "\ufffb\011\006\001\002\000\004\002\000\001\002\000\004" +
    "\005\011\001\002\000\004\002\010\001\002\000\004\002" +
    "\001\001\002\000\006\006\013\011\012\001\002\000\004" +
    "\004\015\001\002\000\006\002\ufffe\011\ufffe\001\002\000" +
    "\006\002\ufffd\011\ufffd\001\002\000\004\011\016\001\002" +
    "\000\004\004\017\001\002\000\004\013\020\001\002\000" +
    "\004\007\021\001\002\000\006\006\013\011\012\001\002" +
    "\000\006\002\uffff\011\uffff\001\002\000\004\002\ufffc\001" +
    "\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\021\000\010\002\006\004\003\005\004\001\001\000" +
    "\006\004\003\005\022\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\003" +
    "\013\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\003\021\001\001\000\002" +
    "\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$CapaSyntax$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$CapaSyntax$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$CapaSyntax$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    SparseMatrix matrix = new SparseMatrix(0);

    BBTree tree = new BBTree();

    public BBTree getTree(){
        return tree;
    }


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$CapaSyntax$actions {
  private final CapaSyntax parser;

  /** Constructor */
  CUP$CapaSyntax$actions(CapaSyntax parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$CapaSyntax$do_action_part00000000(
    int                        CUP$CapaSyntax$act_num,
    java_cup.runtime.lr_parser CUP$CapaSyntax$parser,
    java.util.Stack            CUP$CapaSyntax$stack,
    int                        CUP$CapaSyntax$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$CapaSyntax$result;

      /* select the action based on the action number */
      switch (CUP$CapaSyntax$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= beginning EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-1)).value;
		RESULT = start_val;
              CUP$CapaSyntax$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-1)), ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$CapaSyntax$parser.done_parsing();
          return CUP$CapaSyntax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // beginning ::= cap_re 
            {
              Object RESULT =null;

              CUP$CapaSyntax$result = parser.getSymbolFactory().newSymbol("beginning",0, ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.peek()), ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.peek()), RESULT);
            }
          return CUP$CapaSyntax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // auxcap ::= NUMBER COMA NUMBER COMA COLOR COLON auxcap 
            {
              Object RESULT =null;
		int rowleft = ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-6)).left;
		int rowright = ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-6)).right;
		Object row = (Object)((java_cup.runtime.Symbol) CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-6)).value;
		int colleft = ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-4)).left;
		int colright = ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-4)).right;
		Object col = (Object)((java_cup.runtime.Symbol) CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-4)).value;
		int hexcolorleft = ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-2)).left;
		int hexcolorright = ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-2)).right;
		Object hexcolor = (Object)((java_cup.runtime.Symbol) CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-2)).value;
		matrix.insert(Integer.parseInt(String.valueOf(col)),Integer.parseInt(String.valueOf(row)),String.valueOf(hexcolor));
              CUP$CapaSyntax$result = parser.getSymbolFactory().newSymbol("auxcap",1, ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-6)), ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.peek()), RESULT);
            }
          return CUP$CapaSyntax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // auxcap ::= CLOSE_CURLY 
            {
              Object RESULT =null;
		matrix = new SparseMatrix(0);
              CUP$CapaSyntax$result = parser.getSymbolFactory().newSymbol("auxcap",1, ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.peek()), ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.peek()), RESULT);
            }
          return CUP$CapaSyntax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // cap ::= NUMBER OPEN_CURLY auxcap 
            {
              Object RESULT =null;
		int layerleft = ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-2)).left;
		int layerright = ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-2)).right;
		Object layer = (Object)((java_cup.runtime.Symbol) CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-2)).value;
		 matrix.setLayer(Integer.parseInt(String.valueOf(layer)));
                                                tree.addNode(Integer.parseInt(String.valueOf(layer)),matrix); 
              CUP$CapaSyntax$result = parser.getSymbolFactory().newSymbol("cap",2, ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-2)), ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.peek()), RESULT);
            }
          return CUP$CapaSyntax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // cap_re ::= cap cap_re 
            {
              Object RESULT =null;

              CUP$CapaSyntax$result = parser.getSymbolFactory().newSymbol("cap_re",3, ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.elementAt(CUP$CapaSyntax$top-1)), ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.peek()), RESULT);
            }
          return CUP$CapaSyntax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // cap_re ::= 
            {
              Object RESULT =null;

              CUP$CapaSyntax$result = parser.getSymbolFactory().newSymbol("cap_re",3, ((java_cup.runtime.Symbol)CUP$CapaSyntax$stack.peek()), RESULT);
            }
          return CUP$CapaSyntax$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$CapaSyntax$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$CapaSyntax$do_action(
    int                        CUP$CapaSyntax$act_num,
    java_cup.runtime.lr_parser CUP$CapaSyntax$parser,
    java.util.Stack            CUP$CapaSyntax$stack,
    int                        CUP$CapaSyntax$top)
    throws java.lang.Exception
    {
              return CUP$CapaSyntax$do_action_part00000000(
                               CUP$CapaSyntax$act_num,
                               CUP$CapaSyntax$parser,
                               CUP$CapaSyntax$stack,
                               CUP$CapaSyntax$top);
    }
}

}
