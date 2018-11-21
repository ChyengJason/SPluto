package com.jscheng.spluto.view.parser;

import com.jscheng.spluto.core.bean.Block;
import com.jscheng.spluto.view.Panel;

import java.util.List;

/**
 * Created By Chengjunsen on 2018/11/21
 */
public interface IPanelBuilder {
    List<Panel> build(Block block);
}
