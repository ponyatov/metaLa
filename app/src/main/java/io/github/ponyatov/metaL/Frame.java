package io.github.ponyatov.metaL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Frame {
    String type;
    String val;
    List<Frame> nest;
    Map<String,Frame> slot;
    public Frame(String T, String V) {
        type = T;
        val  = V;
        nest = new ArrayList<Frame>();
        slot = new HashMap<String,Frame>();
    }

    public String toString() { return this.dump(0,""); }
    public String head(String prefix) { return prefix + "<" + type + ":" + _val() + ">"; }
    public String dump(int depth,String prefix) {
        String tree = _pad(depth) + head(prefix);
        for (Map.Entry<String, Frame> i : slot.entrySet())
            tree += i.getValue().dump(depth+1,i.getKey() + " = ");
        for (Frame j: nest)
            tree += j.dump(depth+1,"");
        return tree;
    }
    private String _val() { return val.toString(); }
    private String _pad(int depth) {
        String s = "\n"; for(int i=0;i<depth;i++) s += "    "; return s; }

    public Frame push(Frame that) { nest.add(that); return this; }
    public Frame set(String key, Frame what) { slot.put(key,what); return this; }

}
