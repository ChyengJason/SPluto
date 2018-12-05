package com.jscheng.spluto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jscheng.spluto.markdown.MarkdownScrollView;
import com.jscheng.spluto.util.FileUtil;

public class MainActivity extends AppCompatActivity {
    private MarkdownScrollView mMarkView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMarkView = findViewById(R.id.main_view);

        String content = FileUtil.readAsset(this, "test.md");
        mMarkView.setMarkDownSource(content);
    }
}
