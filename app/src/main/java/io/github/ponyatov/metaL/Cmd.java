package io.github.ponyatov.metaL;

public class Cmd extends Active {
    public interface _cmd { void eval(Frame ctx); }
    private _cmd fn;
    public Cmd(String V, _cmd F) { super("cmd",V); fn = F; }
    public void eval(Frame ctx) { fn.eval(ctx); }

    public static void DUP(Frame ctx) { ctx.dup(); }
    public static void DROP(Frame ctx) { ctx.drop(); }
    public static void SWAP(Frame ctx) { ctx.swap(); }
    public static void OVER(Frame ctx) { ctx.over(); }
    public static void PRESS(Frame ctx) { ctx.press(); }
    public static void DOT(Frame ctx) { ctx.dropall(); }

    public static void ADD(Frame ctx) { Frame B = ctx.pop(), A = ctx.pop(); ctx.push(A.add(B)); }
    public static void SUB(Frame ctx) { Frame B = ctx.pop(), A = ctx.pop(); ctx.push(A.sub(B)); }
    public static void MUL(Frame ctx) { Frame B = ctx.pop(), A = ctx.pop(); ctx.push(A.mul(B)); }
    public static void DIV(Frame ctx) { Frame B = ctx.pop(), A = ctx.pop(); ctx.push(A.div(B)); }
    public static void POW(Frame ctx) { Frame B = ctx.pop(), A = ctx.pop(); ctx.push(A.pow(B)); }
    public static void MOD(Frame ctx) { Frame B = ctx.pop(), A = ctx.pop(); ctx.push(A.mod(B)); }

    public static void NEG(Frame ctx) { Frame A = ctx.pop(); ctx.push(A.neg()); }
    public static void INT(Frame ctx) { Frame A = ctx.pop(); ctx.push(A.int_()); }
    public static void FLO(Frame ctx) { Frame A = ctx.pop(); ctx.push(A.flo()); }
    public static void DEC(Frame ctx) { Frame A = ctx.pop(); ctx.push(A.dec()); }
    public static void HEX(Frame ctx) { Frame A = ctx.pop(); ctx.push(A.hex()); }
    public static void BIN(Frame ctx) { Frame A = ctx.pop(); ctx.push(A.bin()); }

}
