public class ExpressionTree{

  /**return the expression as an infix notation string with parenthesis
  *@return String
  /* The sample tree would be: "(3 + (2 * 10))"     */
  public String toString(){
    if(isOp()){ //if there are more values
      String left = getLeft().toString();
      String right = getRight().toString();
      return "(" + left + " " + getOp() + " " + right + ")";
    }else return "" + getValue(); //if it's a single value
  }

  /**return the expression as a postfix notation string without parenthesis
  *@return String
  /* The sample tree would be: "3 2 10 * +"     */
  public String toStringPostfix(){
    if(isOp()){ //if there are more values
      String left = getLeft().toStringPostfix();
      String right = getRight().toStringPostfix();
      return "" + left + " " + right + " " + getOp();
    }else return "" + getValue(); //if it's a single value
  }

  /**return the expression as a prefix notation string without parenthesis
  *@return String
  /* The sample tree would be: "+ 3 * 2 10"     */
  public String toStringPrefix(){
    if(isOp()){ //if there are more values
      String left = getLeft().toStringPrefix();
      String right = getRight().toStringPrefix();
      return "" + getOp() + " " + left + " " + right;
    }else return "" + getValue(); //if it's a single value
  }

  /**return the value of the specified expression tree
  *@return double the value of the tree
  */
  public double evaluate(){
    if(isOp()){ //evaluate the left and right if there are more values and operations
      double left = getLeft().evaluate();
      double right = getRight().evaluate();
      return apply(getOp(), left, right);
    }else return getValue(); //return if it's a single value
    }

  /**use the correct operator on both a and b, and return that value
  *@param char op is the operator
  *@param double a the first value
  *@param double b the second value
  *@return double the result after performing the correct operator on a and b
  */
  private double apply(char op, double a, double b){
    //checks to find the operator and performs it
    if(op == '+') return a + b;
    if(op == '-') return a - b;
    if(op == '*') return a * b;
    else return a / b;
    }

  private char op;
  private double value;
  private ExpressionTree left,right;

  /*TreeNodes are immutable, so no issues with linking them across multiple
  *  expressions. The can be constructed with a value, or operator and 2
  * sub-ExpressionTrees*/
  public ExpressionTree(double value){
    this.value = value;
    op = '~';
  }
  public ExpressionTree(char op,ExpressionTree l, ExpressionTree r){
    this.op = op;
    left = l;
    right = r;
  }

  /**An accessor method that returns the operator character
  *@return char
  */
  public char getOp(){
    return op;
  }

  /**accessor method for Value, precondition is that isValue() is true.
  *@return double
  */
  private double getValue(){
    return value;
  }
  /**accessor method for left, precondition is that isOp() is true.
  *@return ExpressionTree
  */
  private ExpressionTree getLeft(){
    return left;
  }
  /**accessor method for right, precondition is that isOp() is true.
  *@return ExpressionTree
  */
  private ExpressionTree getRight(){
    return right;
  }

  /**A method that checks if the current ExpressionTree is an operator
  *@return boolean
  */
  private boolean isOp(){
    return hasChildren();
  }

  /**A method that checks if the current ExpressionTree is a value
  *@return boolean
  */
  private boolean isValue(){
    return !hasChildren();
  }

  /**A method that checks if an ExpressionTree has left or right branches
  *@return boolean
  */
  private boolean hasChildren(){
    return left != null && right != null;
  }


  public static void main(String[] args){
    //ugly main sorry!
    ExpressionTree a = new ExpressionTree(4.0);
    ExpressionTree b = new ExpressionTree(2.0);

    ExpressionTree c = new ExpressionTree('+',a,b);
    System.out.println(c);
    System.out.println(c.toStringPostfix());
    System.out.println(c.toStringPrefix());
    System.out.println(c.evaluate());//6.0

    ExpressionTree d = new ExpressionTree('*',c,new ExpressionTree(3.5));
    System.out.println(d);
    System.out.println(d.toStringPostfix());
    System.out.println(d.toStringPrefix());
    System.out.println(d.evaluate());//21

    ExpressionTree ex = new ExpressionTree('-',d,new ExpressionTree(1.0));
    System.out.println(ex);
    System.out.println(ex.toStringPostfix());
    System.out.println(ex.toStringPrefix());
    System.out.println(ex.evaluate());//20

    ex = new ExpressionTree('+',new ExpressionTree(1.0),ex);
    System.out.println(ex);
    System.out.println(ex.toStringPostfix());
    System.out.println(ex.toStringPrefix());
    System.out.println(ex.evaluate());//21

    ex = new ExpressionTree('/',ex,new ExpressionTree(2.0));
    System.out.println(ex);
    System.out.println(ex.toStringPostfix());
    System.out.println(ex.toStringPrefix());
    System.out.println(ex.evaluate());//10.5

  }
}
