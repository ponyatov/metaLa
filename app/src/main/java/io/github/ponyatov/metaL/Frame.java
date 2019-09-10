package io.github.ponyatov.metaL;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    String type;
    String val;
    List<Frame> nest;
    public Frame(String T, String V) {
        this.type = T; this.val = V;
        this.nest = new ArrayList<Frame>();
    }
    public String toString() { return this.dump(0,""); }
    public String head(String prefix) { return "<" + this.type + ":" + this.val + ">"; }
    public String dump(int depth,String prefix) {
        String tree = this._pad(depth) + this.head(prefix);
        for (Frame j: this.nest)
            tree += j.dump(depth+1,"");
        return tree;
    }
    public Frame push(Frame that) { this.nest.add(that); return this; }
    public String _pad(int depth) {
        String s = "\n";
        for(int i=0;i<depth;i++) s += "    ";
        return s;
    }
}
