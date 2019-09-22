package io.github.ponyatov.metaL;

class Task extends Plan {
    public Task(String V) { super("task",V); }
    public static void TASK(Frame ctx) { ctx.push(new Task(ctx.pop().val)); }
}
