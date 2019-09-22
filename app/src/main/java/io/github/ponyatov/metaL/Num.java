package io.github.ponyatov.metaL;

class Num extends Prim {
    public double val;
    public Num(String V)            { super("num",V);  val = Double.parseDouble(V); }
    public Num(Double V)            { super("num","0");  val = V; }
    public Num(String T, String V)  { super(        T,V); }
    protected String _val()         { return Double.toString(val); }

    public Frame add(Frame that) {
        switch (that.type) {
            case "int": return new Num(val + ((Int)that).val);
            case "num": return new Num(val + ((Num)that).val);
            default:    return super.add(that);
        }
    }

    public Frame sub(Frame that) {
        switch (that.type) {
            case "int": return new Num(val - ((Int)that).val);
            case "num": return new Num(val - ((Num)that).val);
            default:    return super.sub(that);
        }
    }

    public Frame mul(Frame that) {
        switch (that.type) {
            case "int": return new Num(val * ((Int)that).val);
            case "num": return new Num(val * ((Num)that).val);
            default:    return super.mul(that);
        }
    }

    public Frame div(Frame that) {
        switch (that.type) {
            case "int": return new Num(val / ((Int)that).val);
            case "num": return new Num(val / ((Num)that).val);
            default:    return super.div(that);
        }
    }

    public Frame pow(Frame that) {
        switch (that.type) {
            case "int": return new Num(Math.pow(val , ((Int)that).val));
            case "num": return new Num(Math.pow(val , ((Num)that).val));
            default:    return super.pow(that);
        }
    }

    public Frame int_() { return new Int((long)val); }
    public Frame dec () { return this; }

}
