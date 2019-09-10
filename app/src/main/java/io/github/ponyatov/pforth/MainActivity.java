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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vm.push(new Frame("Hello","World"));

        // Example of a call to a native method
        TextView dump = findViewById(R.id.dump);
//        dump.setText(stringFromJNI());
        dump.setText(this.vm.toString());

        EditText pad = findViewById(R.id.pad);

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String metaL_dump();
}
