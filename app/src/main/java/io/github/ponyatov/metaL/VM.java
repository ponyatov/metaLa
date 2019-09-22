package io.github.ponyatov.metaL;

import android.app.AlertDialog;
import android.content.DialogInterface;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.github.ponyatov.FORTH.MainActivity;

public class VM extends Active {

    public static Lexer lexer = new Lexer("FORTH");
    public MainActivity activity;

    public VM(String V) {
        super("vm",V);
        this.shl(       new Cmd("NOP"   ,(ctx)->{}  ));
        this.set("?",   new Cmd("Q"     ,VM::Q      ));

        this.shl(       new Cmd("DUP"   ,Cmd::DUP   ));
        this.shl(       new Cmd("DROP"  ,Cmd::DROP  ));
        this.shl(       new Cmd("SWAP"  ,Cmd::SWAP  ));
        this.shl(       new Cmd("OVER"  ,Cmd::OVER  ));
        this.shl(       new Cmd("PRESS" ,Cmd::PRESS ));
        this.set(".",   new Cmd("DOT"   ,Cmd::DOT   ));

        this.shl(       new Cmd("ADD",Cmd::ADD)); this.set("+",this.get("ADD"));
        this.shl(       new Cmd("SUB",Cmd::SUB)); this.set("-",this.get("SUB"));
        this.shl(       new Cmd("MUL",Cmd::MUL)); this.set("*",this.get("MUL"));
        this.shl(       new Cmd("DIV",Cmd::DIV)); this.set("/",this.get("DIV"));
        this.shl(       new Cmd("POW",Cmd::POW)); this.set("^",this.get("POW"));
        this.shl(       new Cmd("MOD",Cmd::MOD)); this.set("%",this.get("MOD"));
        this.shl(       new Cmd("NEG",Cmd::NEG));
        this.shl(       new Cmd("INT",Cmd::INT));
        this.shl(       new Cmd("FLO",Cmd::FLO));
        this.shl(       new Cmd("DEC",Cmd::DEC));
        this.shl(       new Cmd("HEX",Cmd::HEX));
        this.shl(       new Cmd("BIN",Cmd::BIN));

        this.shl(       new Cmd("WORD", VM::WORD));
        this.shl(       new Cmd("INTERP",VM::INTERP));

        this.shl(       new Cmd("TASK", Task::TASK));
        this.shl(       new Cmd("PLAN", Plan::PLAN));
        this.shl(       new Cmd("SHED", Shed::SHED));

    }

    public static void Q(Frame ctx) {
        boolean me = false;
        AlertDialog.Builder alert = new AlertDialog.Builder(((VM)ctx).activity);
        alert.setTitle("?");
        alert.setCancelable(false);
        alert.setMessage(ctx.dump(0,"",true,true));
        alert.setNeutralButton("OK", (dialog,id) -> { dialog.cancel();});
//            new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) { me = true; dialog.cancel(); }});
        alert.create().show();
//        while (!me);
    }

    public static Frame WORD(Frame ctx) {
        Frame token = lexer.token();
        if (token != null) ctx.push(token);
        return token;
    }

    public static Frame FIND(Frame ctx) {
        Frame token = ctx.pop();
        Frame found = ctx.get(token.val);
        if (found == null)
            found = ctx.get(token.val.toUpperCase());
        if (found != null) ctx.push(found);
        else               ctx.push(token);
        return found;
    }

    public static void EVAL(Frame ctx) { ctx.pop().eval(ctx); }

    public static void INTERP(Frame ctx) {
        lexer.input(ctx.pop().val);
        while (true) {
            if (WORD(ctx) == null) break;
            if (ctx.top() instanceof Sym)
                if (FIND(ctx) == null) {
                    lexer.purge();
                    AlertDialog.Builder alert = new AlertDialog.Builder(((VM)ctx).activity);
                    alert.setTitle(ctx.top().head("not found  "));
                    alert.setMessage(ctx.dump(0,"",true,true));
                    alert.create().show();
                }
            EVAL(ctx);
        }
    }

}
