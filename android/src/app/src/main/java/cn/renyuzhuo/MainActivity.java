package cn.renyuzhuo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.renyuzhuo.rlib.rlog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlog.setDebugLever(rlog.DEBUG_LEVEL.info);
    }
}
