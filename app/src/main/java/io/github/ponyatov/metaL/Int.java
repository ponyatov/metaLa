package io.github.ponyatov.metaL;

class Int extends Num {
    public long val;
    public Int(String V)            { super("int",V);  val = Long.parseLong(V); }
    public Int(Long   V)            { super("int","0");  val = V;            }
    public Int(String T, String V)  { super(        T,V); }
    public Int(String T, Long V)    { super(        T,"0"); val =V; }
    protected String _val()         { return Long.toString(val); }

    public Frame add(Frame that) {
        switch (that.type) {
            case "int": return new Int(val + ((Int)that).val);
            case "num": return new Num(val + ((Num)that).val);
            default:    return super.add(that);
        }
    }

    public Frame sub(Frame that) {
        switch (that.type) {
            case "int": return new Int(val - ((Int)that).val);
            case "num": return new Num(val - ((Num)that).val);
            default:    return super.sub(that);
        }
    }

    public Frame mul(Frame that) {
        switch (that.type) {
            case "int": return new Int(val * ((Int)that).val);
            case "num": return new Num(val * ((Num)that).val);
            default:    return super.mul(that);
        }
    }

    public Frame div(Frame that) {
        switch (that.type) {
            case "int": return new Int(val / ((Int)that).val);
            case "num": return new Num(val / ((Num)that).val);
            default:    return super.div(that);
        }
    }

    public Frame pow(Frame that) {
        switch (that.type) {
            case "int": double _pow = Math.pow(val , ((Int)that).val);
                        if (Math.abs(_pow) < Long.MAX_VALUE) return new Int((long)_pow);
                        else                                 return new Num(_pow);
            case "num": return new Num(Math.pow(val , ((Num)that).val));
            default:    return super.pow(that);
        }
    }

    public Frame mod(Frame that) {
        switch (that.type) {
            case "int": return new Int(val % ((Int)that).val);
            default:    return super.mod(that);
        }
    }

    public Frame neg () { return new Int(-val); }
    public Frame flo () { return new Num((double)val); }
    public Frame dec () { return new Int(val); }
    public Frame hex () { return new Hex(val); }
    public Frame bin () { return new Bin(val); }

}
