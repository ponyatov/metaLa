package io.github.ponyatov.metaL;

public class VM extends Active {
    public VM(String V) {
        super("vm",V);
        this.shl(new Cmd("NOP",(ctx)->{}));
    }
}
