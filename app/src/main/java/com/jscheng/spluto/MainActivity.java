package com.jscheng.spluto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static String content = "#fd会放大沙发沙发的发的的撒的撒onisa你会f \n你会放大沙发沙发的发的的撒的撒onin娇\nhaofdsfdsaonin\n  "+" ## fdasfsd\n"
            + "![描述](www.baidu.com)";
    private MarkDownView mMarkView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMarkView = findViewById(R.id.main_view);
        mMarkView.setMarkDownSource(content);
    }
}
