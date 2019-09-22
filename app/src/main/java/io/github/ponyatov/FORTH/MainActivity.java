package io.github.ponyatov.FORTH;

import android.content.res.AssetManager;
<<<<<<< HEAD
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.method.ScrollingMovementMethod;
=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
>>>>>>> eb82748227d6f296d4250c214b64710d4cf7603b
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import io.github.ponyatov.metaL.*;

public class MainActivity extends AppCompatActivity {

//    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("native-lib");
//    }

    VM vm = new VM("metaL");

    TextView dump;
    EditText pad;

<<<<<<< HEAD
    boolean voc = true;

    // model/view sync
    private void sync() {
        SpanDump sp = new SpanDump(vm.dump(0,"",voc,true));
        sp.slotname = ContextCompat.getColor(this, R.color.slotname);
        sp.sloteq   = ContextCompat.getColor(this, R.color.sloteq);
        sp.typetag  = ContextCompat.getColor(this, R.color.typetag);
        dump.setText(sp.span());
=======
    // model/view sync
    private void sync() {
        dump.setText(vm.dump(0,"",false,true));
>>>>>>> eb82748227d6f296d4250c214b64710d4cf7603b
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dump = findViewById(R.id.dump);
        pad  = findViewById(R.id.pad );
        // Example of a call to a native method
        // dump.setText(stringFromJNI());

        vm.activity = this; // for error processing

        vm.push(new Frame("Hello","World"));

        // .ini file
        try {
            AssetManager am = getAssets();
            InputStream ini = am.open("metaL.ini");
            byte[] src = new byte[ini.available()]; ini.read(src); ini.close();
            vm.push(new Str(new String(src)));
            VM.INTERP(vm); sync();
        } catch (IOException e) {
            e.printStackTrace();
        }

<<<<<<< HEAD
//        dump.setOnClickListener((view)->{ voc = !voc; sync(); });
        dump.setMovementMethod(new ScrollingMovementMethod());

=======
>>>>>>> eb82748227d6f296d4250c214b64710d4cf7603b
        // longpress command run enable only after .ini
        dump.setOnLongClickListener((view)->{
            vm.push(new Str(pad.getText().toString()));
            VM.INTERP(vm); sync();
            return false;
        });

<<<<<<< HEAD
        // longpress on command field pushed new task
        pad.setOnLongClickListener((view)->{
            pad.setText("'task' task");
            pad.setSelection(1,1+4);
            return false;
        });

=======
>>>>>>> eb82748227d6f296d4250c214b64710d4cf7603b
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String metaL_dump();
}
