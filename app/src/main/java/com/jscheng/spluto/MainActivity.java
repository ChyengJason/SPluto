package com.jscheng.spluto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static String content = "#fd会放大沙发沙发的发的的撒的撒onisa你会f \n" +
            "你会放大沙发沙发的发的的撒的撒onin娇\n" +
            "haofdsfdsaonin\n " +
            " ## fdasfsd\n" +
            "![描述](https://upload-images.jianshu.io/upload_images/587163-b24965ffb4783d61.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/408/format/webp) [阿比杜](www.baidu.com)" +
            "`fdasfdsafs` \n" +
            "2. 你覅大黄蜂fdsafdsafdsafdsafdsa\n" +
            "3. 年__哈__佛导师了\n" +
            "* nihao \n" +
            "* 你发大水\n" +
            "- [ ] 你发大水是否发生范德萨范德萨飞洒 的萨芬士大夫撒旦 士大夫撒\n" +
            "- [x] ## 你覅大宋\n" +
            "> fdsafdsafdsafsafdsafsdafdsafdsafdsafdsafdsafsdafsdafsdafsda\n" +
            "> ### 你覅到書房\n" +
            "你回復撒o\n" +
            "===  \n" +
            "```\n" +
            "nihfjksaffdsafdsafdsa" +
            "fdsafdsafsda\n" +
            "   fdsafdsafsda\n" +
            "   public main() {\n" +
            "```\n" +
            "![fengmian](https://upload-images.jianshu.io/upload_images/1698563-15ebfe4753bead6a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp)" +
            "![fadsfas](https://www.jianshu.com/fp/notice/now?utm_medium=index-banner&utm_source=desktop)\n" +
            "![warning](https://upload-images.jianshu.io/upload_images/11897912-81c32ed00c9374c9?imageMogr2/auto-orient/strip%7CimageView2/2/w/640/format/webp)\n";
    private MarkDownView mMarkView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMarkView = findViewById(R.id.main_view);
        mMarkView.setMarkDownSource(content);
    }
}
