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
        type = T;//this.getClass().toString();//T;
        val  = V;
        nest = new ArrayList<Frame>();
        slot = new HashMap<String,Frame>();
    }

    public String toString() { return this.dump(0,"",true,true); }
    public String head(String prefix) { return prefix + "<" + type + ":" + _val() + ">"; }
    public String dump(int depth,String prefix,boolean voc,boolean stk) {
        String tree = _pad(depth) + head(prefix);
        if (voc)
            for (Map.Entry<String, Frame> i : slot.entrySet())
                tree += i.getValue().dump(depth+1,i.getKey() + " = ",voc,stk);
        if (stk)
            for (Frame j: nest)
                tree += j.dump(depth+1,"",voc,stk);
        return tree;
    }
    protected String _val() { return val.toString(); }
    protected String _pad(int depth) {
        String s = "\n"; for(int i=0;i<depth;i++) s += "    "; return s; }

    public Frame push(Frame that) { nest.add(that); return this;    }
    public Frame pop()      { return nest.remove(nest.size()-1);    }
    public Frame pip()      { return nest.remove(nest.size()-2);    }
    public Frame top()      { return nest.get(nest.size()-1);       }
    public Frame tip()      { return nest.get(nest.size()-2);       }
    public Frame dup()      { return push(top());                   }
    public Frame drop()     { pop(); return this;                   }
    public Frame swap()     { return push(pip());                   }
    public Frame over()     { return push(tip());                   }
    public Frame press()    { pip(); return this;                   }
    public void dropall()   { nest.clear();                         }

    public Frame get(String key) { return slot.get(key); }
    public Frame set(String key, Frame what) { slot.put(key,what); return this; }
    public Frame shl(Frame that) { return set(that.val,that); }

    public void eval(Frame ctx) { ctx.push(this); }

    public Frame add(Frame that) { return new Error(head("") + " + " + that.head("")); }
    public Frame sub(Frame that) { return new Error(head("") + " - " + that.head("")); }
    public Frame mul(Frame that) { return new Error(head("") + " * " + that.head("")); }
    public Frame div(Frame that) { return new Error(head("") + " / " + that.head("")); }
    public Frame pow(Frame that) { return new Error(head("") + " ^ " + that.head("")); }
    public Frame mod(Frame that) { return new Error(head("") + " % " + that.head("")); }

    public Frame neg () { return new Error( "neg( " + head("") + " ) "); }
    public Frame int_() { return new Error( "int( " + head("") + " ) "); }
    public Frame flo () { return new Error( "flo( " + head("") + " ) "); }
    public Frame dec () { return new Error( "dec( " + head("") + " ) "); }
    public Frame hex () { return new Error( "hex( " + head("") + " ) "); }
    public Frame bin () { return new Error( "bin( " + head("") + " ) "); }

    }
