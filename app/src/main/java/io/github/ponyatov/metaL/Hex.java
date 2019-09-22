package io.github.ponyatov.metaL;

class Hex extends Int {
    public           Hex(String V)  { super("hex", Long.parseLong(V,0x10)); }
    public           Hex(Long   V)  { super("hex", V); }
    protected String _val()         { return Long.toHexString(val); }
}
