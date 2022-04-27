import java.util.HashMap;

class Expression {
    protected HashMap<String,Integer> VariableValuation;

    public Expression() {
        VariableValuation = new HashMap<String,Integer>();
    }
    public void SetVariableValue(String name, int val) {
        VariableValuation.put(name,val);
    }
    public int evaluate() {
        return 0;
    }
    public String toString() {
        return "";
    }

    //zad4
    public Expression derivate() {
        return this;
    }
}

class Const extends Expression {
    private Integer val;

    public Const(int _val) {
        val = _val;
    }
    public int evaluate() {
        return val;
    }
    public String toString() {
        return val.toString();
    }

    //zad4
    public Expression derivate() {
        return new Const(0);
    }
}

class Variable extends Expression {
    private String name;

    public Variable(String _name) {
        name = _name;
    }
    public int evaluate() {
        return VariableValuation.get(name);
    }
    public String toString() {
        return name;
    }
    
    //zad4
    public Expression derivate() {
        return new Const(1);
    }
}

class Addition extends Expression {
    private Expression LeftExp, RightExp;

    public Addition(Expression l, Expression r) {
        LeftExp = l;
        RightExp = r;
    }
    public void SetVariableValue(String name, int val) {
        VariableValuation.put(name,val);
        LeftExp.SetVariableValue(name, val);
        RightExp.SetVariableValue(name, val);
    }
    public int evaluate() {
        return LeftExp.evaluate() + RightExp.evaluate();
    }
    public String toString() {
        return LeftExp.toString() + "+" + RightExp.toString();
    }

    //zad4
    public Expression derivate() {
        return new Addition(LeftExp.derivate(), RightExp.derivate());
    }
}

class Multiplication extends Expression {
    private Expression LeftExp, RightExp;

    public Multiplication(Expression l, Expression r) {
        LeftExp = l;
        RightExp = r;
    }
    public void SetVariableValue(String name, int val) {
        VariableValuation.put(name,val);
        LeftExp.SetVariableValue(name, val);
        RightExp.SetVariableValue(name, val);
    }
    public int evaluate() {
        return LeftExp.evaluate() * RightExp.evaluate();
    }
    public String toString() {
        String ls = LeftExp.toString();
        String rs = RightExp.toString();
        if(LeftExp instanceof Addition) ls = "(" + ls + ")";
        if(RightExp instanceof Addition) rs = "(" + rs + ")";
        return ls + "*" + rs;
    }

    //zad4
    public Expression derivate() {
        return new Addition(new Multiplication(LeftExp.derivate(), RightExp),
                            new Multiplication(LeftExp, RightExp.derivate())); // (f*g)' = f'g + fg'
    }
}

public class zad2_i_4 {
    public static void main(String[] args) {
        Expression expr = new Addition(new Const(4), new Variable("x"));
        System.out.println(expr.toString());

        Expression e2 = new Addition(new Multiplication(new Const(5), new Const(7)), new Const(3));
        System.out.println(e2);
        System.out.println(e2.evaluate());

        expr.SetVariableValue("x",8);
        System.out.println(expr.evaluate());
        expr.SetVariableValue("x",11);
        System.out.println(expr.evaluate());

        Expression f = new Multiplication(new Variable("x"), new Variable("y"));
        System.out.println(f);
        f.SetVariableValue("x",10);
        f.SetVariableValue("y",8);
        System.out.println(f.evaluate());
        
        Expression e3 = new Multiplication(new Addition(new Const(3), new Const(7)),
                                           new Multiplication(new Addition(new Const(4), new Const(11)),
                                                        new Multiplication(new Const(5), new Const(2))));
        System.out.println(e3);  
        System.out.println(e3.evaluate());
        
        Expression g = new Addition(new Multiplication(new Variable("x"), new Variable("x")),
                                    new Addition(new Multiplication(new Const(5), new Variable("x")),
                                                 new Const(7)));
        System.out.println(g);
        System.out.println(g.derivate());                                           
    }
}