package io.github.ponyatov.metaL;

public class Cmd extends Active {
    public interface _cmd { void eval(Frame ctx); }
    private _cmd fn;
    public Cmd(String V, _cmd F) { super("cmd",V); fn = F; }
    public void eval(Frame ctx) { fn.eval(ctx); }
}
