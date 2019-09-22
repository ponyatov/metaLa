package io.github.ponyatov.metaL;

class Plan extends Frame {
    public Plan(String T, String V) { super(T,V); }
    public Plan(String V) { super("plan",V); }
    public static void PLAN(Frame ctx) { ctx.push(new Plan(ctx.pop().val)); }
}
