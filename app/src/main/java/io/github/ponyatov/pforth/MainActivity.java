package io.github.ponyatov.pforth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import io.github.ponyatov.metaL.*;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    VM vm = new VM("metaL") ;
    Lexer lexer = new Lexer("FORTH");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vm.push(new Frame("Hello","World"));
        vm.set("lexer",lexer);
        lexer.input("# comment\n-01 +02.30 -04e+05 0xDeadBeef 0b1101 'some\\n\\tstring' \n");
        for (Frame tok = lexer.token(); tok !=null; tok = lexer.token())
            vm.push(tok);

        // Example of a call to a native method
        TextView dump = findViewById(R.id.dump);
//        dump.setText(stringFromJNI());

        vm.shl(new Cmd("activity",(ctx)->{dump.setText(this.toString());}));

        dump.setText(vm.toString());

        ((Cmd)vm.get("activity")).eval(vm);

        EditText pad = findViewById(R.id.pad);

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String metaL_dump();
}
