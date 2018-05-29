package com.blowfish.markovsimple.processors;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import com.blowfish.markovsimple.models.Word;
import com.blowfish.markovsimple.models.WordLink;

public class ChainProcessor {
	
	private Map<String, Word> words;
	
	public ChainProcessor(Map<String, Word> words) {
		this.words = words;
	}
	
	public String getSentence(String initial) {
		String result = initial;
		Word current = words.get(initial);
        if(current == null) {
        	System.out.println("Word not found");
        	return result;
        } else {
        	
        	while(!current.isEnder()) {
        		Word nextWord = this.decideNextWord(current.getWordLinks());
        		result += " " + nextWord.getLiteral();
        		current = nextWord;
        	}
        	
        	return result;
        }
	}
	
	private Word decideNextWord(Map<String, WordLink> wordLinks) {
		int total = 0;
		ArrayList<WordLink> wordLinkArray = new ArrayList<WordLink>();
		for(Map.Entry<String, WordLink> entry: wordLinks.entrySet()) {
    		WordLink nextLink = entry.getValue();
    		wordLinkArray.add(nextLink);
    		total = total + nextLink.getCount();
    		
    	}
		
		Random rand = new Random();
		double totalDouble = (double)total;
		//erghh not efficient
		while(true) {
			for(WordLink wl : wordLinkArray){
				double randScore = rand.nextDouble();
				double nextLinkScore =(double)wl.getCount();
				double result = nextLinkScore / total;
				if(result >= randScore) {
					return wl.getWord();
				}
			}
	    	
		}
		
		
	}
	
	
	
}
