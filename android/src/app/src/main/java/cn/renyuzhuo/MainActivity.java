package cn.renyuzhuo;

import android.app.Activity;
import android.os.Bundle;

import cn.renyuzhuo.rlog.rlog;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlog.d("in MainActivity");

    }
}
