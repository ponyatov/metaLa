package io.github.ponyatov.metaL;

class Bin extends Int {
    public              Bin(String V)   { super("bin",Long.parseLong(V,0x02)); }
    public              Bin(Long   V)   { super("bin",V); }
    protected String    _val()          { return Long.toBinaryString(val); }
}
