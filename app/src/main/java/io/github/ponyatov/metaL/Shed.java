package io.github.ponyatov.metaL;

class Shed extends Plan {
    public Shed(String V) { super("shed", V); }
    public static void SHED(Frame ctx) { ctx.push(new Shed(ctx.pop().val)); }
}
