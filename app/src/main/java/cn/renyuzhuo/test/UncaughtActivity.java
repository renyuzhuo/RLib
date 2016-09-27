package cn.renyuzhuo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UncaughtActivity extends AppCompatActivity {

    Object object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        object.toString();
    }
}
