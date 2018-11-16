package com.jscheng.spluto;

import com.jscheng.spluto.core.bean.Block;
import com.jscheng.spluto.core.parser.Analyzer;

import org.junit.Test;

import java.util.List;

/**
 * Created By Chengjunsen on 2018/11/16
 */
public class markdownTest {
    private static String content = "### fdsaf\n" +
            "1. fdsaf\n" +
            "2. fdsafsa\n" +
            "- FSDA\n" +
            "- [] fdsafsad\n" +
            "![fdasfdsa](fdsafsda.wps.cn)";

    @Test
    public void tranferMd() {
        List<Block> blocks = Analyzer.analyze(content);
        System.out.println(blocks);
        assert true;
    }
}
