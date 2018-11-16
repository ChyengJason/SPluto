package com.jscheng.spluto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static String content = "fdsaf";
    private MarkDownView mMarkView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMarkView = findViewById(R.id.main_view);
        mMarkView.setMarkDownSource(content);
    }
}
