package com.jscheng.spluto.dao;

public class MarkdownTextInfo {
    private int id;
    private String name;
    private String path;
    private String summary;
    private boolean exist;

    public MarkdownTextInfo(String name, String path, boolean exist) {
        this.name = name;
        this.path = path;
        this.exist = exist;
    }

    public MarkdownTextInfo(int id, String name, String path, String summary, boolean exist) {
        this.name = name;
        this.path = path;
        this.exist = exist;
        this.id = id;
        this.summary = summary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
