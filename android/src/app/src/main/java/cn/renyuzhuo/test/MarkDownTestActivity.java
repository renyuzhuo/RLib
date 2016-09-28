package cn.renyuzhuo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.renyuzhuo.R;
import cn.renyuzhuo.rlib.widget.MarkDownView;

public class MarkDownTestActivity extends AppCompatActivity {

    MarkDownView markDownView0, markDownView1, markDownView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_down_test);

        MarkDownView markDownView0 = (MarkDownView) findViewById(R.id.markdown0);
        MarkDownView markDownView1 = (MarkDownView) findViewById(R.id.markdown1);
        MarkDownView markDownView2 = (MarkDownView) findViewById(R.id.markdown2);
        markDownView0.loadMarkdown("# Test");
        markDownView1.loadMarkdown("## Test");
        markDownView2.loadMarkdown("### Test");

    }
}
