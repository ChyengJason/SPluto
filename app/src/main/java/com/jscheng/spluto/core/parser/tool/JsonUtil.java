package com.jscheng.spluto.core.parser.tool;


import com.google.gson.Gson;
import com.jscheng.spluto.core.bean.Block;

/**
 * Created by chengjunsen on 2018/11/2.
 */
public class JsonUtil {
    private static Gson gson = new Gson();

    public static String toJson(Block block) {
        return gson.toJson(block);
    }
}
