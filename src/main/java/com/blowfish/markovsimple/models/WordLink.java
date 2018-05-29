package com.blowfish.markovsimple.models;

public class WordLink {
	
	private Word word;
	private int count;
	
	public Word getWord() {
		return word;
	}
	public WordLink setWord(Word word) {
		this.word = word;
		return this;
	}
	public int getCount() {
		return count;
	}
	public WordLink setCount(int count) {
		this.count = count;
		return this;
	}
	
}
