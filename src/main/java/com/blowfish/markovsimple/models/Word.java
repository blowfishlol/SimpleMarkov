package com.blowfish.markovsimple.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Word {

	private String literal;
	private boolean ender;
	private Map<String,WordLink> wordLinks = new HashMap<String,WordLink>();
	
	public String getLiteral() {
		return literal;
	}
	public Word setLiteral(String literal) {
		this.literal = literal;
		if(literal.substring(literal.length()-1, literal.length()).equals(".")) {
			ender = true;
		}
		return this;
	}


	public boolean isEnder() {
		return ender;
	}
	public void setEnder(boolean ender) {
		this.ender = ender;
	}
	public Map<String, WordLink> getWordLinks() {
		return wordLinks;
	}
	public void setWordLinks(Map<String, WordLink> wordLinks) {
		this.wordLinks = wordLinks;
	}
	public void putWord(Word word) {
		WordLink wl = wordLinks.get(word.getLiteral());
		if(wl==null) {
			wordLinks.put(word.getLiteral(), new WordLink().setWord(word).setCount(1));
		} else {
			wl.setCount(wl.getCount() + 1);
		}
	}
	
	
}
