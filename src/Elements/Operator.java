package Elements;

public class Operator {
    
    public String symbol, semantics;
    public int precedence, operands;
    public boolean isLeft;
    
    public Operator(String Symb, String Semantics, int Prec, int ops, boolean IsLeft){
        symbol = Symb;
        semantics = Semantics;
        
        if(ops >= 1) operands = ops;
        else operands = 2;
        
        if(Prec > 0) precedence = Prec;
        else precedence = 0;
        
        isLeft = IsLeft;
    }
}
