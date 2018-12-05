package com.jscheng.spluto.markdown.view;

import com.jscheng.spluto.markdown.core.bean.Block;
import com.jscheng.spluto.markdown.core.parser.Analyzer;
import com.jscheng.spluto.markdown.view.parser.CodePanelBuilder;
import com.jscheng.spluto.markdown.view.parser.CommonTextBuilder;
import com.jscheng.spluto.markdown.view.parser.HeadLinePanelBuilder;
import com.jscheng.spluto.markdown.view.parser.IPanelBuilder;
import com.jscheng.spluto.markdown.view.parser.ListPanelBuilder;
import com.jscheng.spluto.markdown.view.parser.TablePanelBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Chengjunsen on 2018/11/21
 */
public class PanelBuilder {
    private List<IPanelBuilder> mBuilders;

    public PanelBuilder() {
        mBuilders = new ArrayList<>();
        mBuilders.add(new CodePanelBuilder());
        mBuilders.add(new CommonTextBuilder());
        mBuilders.add(new HeadLinePanelBuilder());
        mBuilders.add(new ListPanelBuilder());
        mBuilders.add(new TablePanelBuilder());
    }

    public List<Panel> build(String source) {
        List<Block> blocks = Analyzer.analyze(source);
        List<Panel> panels = new ArrayList<>();
        for (Block block : blocks) {
            panels.addAll(build(block));
        }
        return panels;
    }

    private List<Panel> build(Block block) {
        for (IPanelBuilder builder : mBuilders) {
            List<Panel> panels = builder.build(block);
            if (panels != null && !panels.isEmpty()) {
                return panels;
            }
        }
        return null;
    }
}
