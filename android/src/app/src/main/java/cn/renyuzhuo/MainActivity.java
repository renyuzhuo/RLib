package cn.renyuzhuo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.renyuzhuo.rlib.NumUtil;
import cn.renyuzhuo.rlib.rlog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long t = 231321323;
        float f = 1.3f;
        double d = 3.5;

        rlog.e("err");
        rlog.d("debug");
        rlog.d('g');
        rlog.d(13);
        rlog.d(f);
        rlog.d(d);
        rlog.w("123213221231321321321");
        rlog.d(t);
        rlog.d((Object) null);
        rlog.i(new Object());
        rlog.d(new Integer[]{1, 2, 3, 4, 5, 5, 6});
        rlog.json("{\"abc\": 123}");
        rlog.json("[{\"abc\": 123},{\"abc\": 123, \"bfd\": 432432432}]");
        rlog.xml("<abc><t><tt>432</tt><tt>432433324</tt></t></abc>");
        rlog.objects(123, null, null);

        rlog.d(NumUtil.getRandomNum());
        rlog.d(NumUtil.getRandomNum(10));

        rlog.d(new String[]{});

    }
}
