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
        this.shl(       new Cmd("NOP"       ,(ctx)->{}  ));
        this.set("?",   new Cmd("Q"         ,VM::Q      ));

        this.shl(new Cmd("DUP"      ,VM::DUP    ));
        this.shl(new Cmd("DROP"     ,VM::DROP   ));
        this.shl(new Cmd("SWAP"     ,VM::SWAP   ));
        this.shl(new Cmd("OVER"     ,VM::OVER   ));
        this.shl(new Cmd("PRESS"    ,VM::PRESS  ));
        this.set(".",   new Cmd("DOT"       ,VM::DOT    ));

        this.shl(new Cmd("ADD",VM::ADD)); this.set("+",this.get("ADD"));
        this.shl(new Cmd("SUB",VM::SUB)); this.set("-",this.get("SUB"));
        this.shl(new Cmd("MUL",VM::MUL)); this.set("*",this.get("MUL"));
        this.shl(new Cmd("DIV",VM::DIV)); this.set("/",this.get("DIV"));
        this.shl(new Cmd("POW",VM::POW)); this.set("^",this.get("POW"));
        this.shl(new Cmd("MOD",VM::MOD)); this.set("%",this.get("MOD"));
        this.shl(new Cmd("NEG",VM::NEG));
        this.shl(new Cmd("INT",VM::INT));
        this.shl(new Cmd("FLO",VM::FLO));
        this.shl(new Cmd("DEC",VM::DEC));
        this.shl(new Cmd("HEX",VM::HEX));
        this.shl(new Cmd("BIN",VM::BIN));

        this.shl(       new Cmd("WORD"      ,VM::WORD   ));
        this.shl(       new Cmd("INTERP"    ,VM::INTERP ));
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

    public static void DUP(Frame ctx) { ctx.dup(); }
    public static void DROP(Frame ctx) { ctx.drop(); }
    public static void SWAP(Frame ctx) { ctx.swap(); }
    public static void OVER(Frame ctx) { ctx.over(); }
    public static void PRESS(Frame ctx) { ctx.press(); }
    public static void DOT(Frame ctx) { ctx.dropall(); }

    public static void ADD(Frame ctx) { Frame B = ctx.pop(), A = ctx.pop(); ctx.push(A.add(B)); }
    public static void SUB(Frame ctx) { Frame B = ctx.pop(), A = ctx.pop(); ctx.push(A.sub(B)); }
    public static void MUL(Frame ctx) { Frame B = ctx.pop(), A = ctx.pop(); ctx.push(A.mul(B)); }
    public static void DIV(Frame ctx) { Frame B = ctx.pop(), A = ctx.pop(); ctx.push(A.div(B)); }
    public static void POW(Frame ctx) { Frame B = ctx.pop(), A = ctx.pop(); ctx.push(A.pow(B)); }
    public static void MOD(Frame ctx) { Frame B = ctx.pop(), A = ctx.pop(); ctx.push(A.mod(B)); }

    public static void NEG(Frame ctx) { Frame A = ctx.pop(); ctx.push(A.neg()); }
    public static void INT(Frame ctx) { Frame A = ctx.pop(); ctx.push(A.int_()); }
    public static void FLO(Frame ctx) { Frame A = ctx.pop(); ctx.push(A.flo()); }
    public static void DEC(Frame ctx) { Frame A = ctx.pop(); ctx.push(A.dec()); }
    public static void HEX(Frame ctx) { Frame A = ctx.pop(); ctx.push(A.hex()); }
    public static void BIN(Frame ctx) { Frame A = ctx.pop(); ctx.push(A.bin()); }

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
