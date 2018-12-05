package com.jscheng.spluto.markdown.core.bean;

import com.jscheng.spluto.markdown.core.parser.TextType;
import com.jscheng.spluto.markdown.core.parser.tool.SymbolUtil;

import java.util.ArrayList;
import java.util.List;

public class ValuePart{
	private String value;
	private List<TextType> types;
	private String url;
	private String title;
	
	public ValuePart(){
		this.types = new ArrayList<>();
	}
	
	public ValuePart(String value){
		this.value = SymbolUtil.revertValue(value);
	}

	public ValuePart(String value, TextType... types) {
        this.types = new ArrayList<>();
	    this.value = SymbolUtil.revertValue(value);
	    for (TextType type: types) {
	        this.types.add(type);
        }
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = SymbolUtil.revertValue(value);
	}

	public List<TextType> getTypes() {
		return types;
	}

	public void setTypes(List<TextType> types) {
		this.types = types;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addType(TextType type){
	    types.add(type);
	}
}