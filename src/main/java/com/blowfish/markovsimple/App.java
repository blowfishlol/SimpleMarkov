package com.blowfish.markovsimple;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.blowfish.markovsimple.models.Word;
import com.blowfish.markovsimple.models.WordLink;
import com.blowfish.markovsimple.processors.ChainProcessor;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
    	
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("TextMarkov");
        String text = null;
        try(Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.name())) {
        	text = scanner.useDelimiter("\\A").next();
        }
        
        StringTokenizer st = new StringTokenizer(text);
        Map<String, Word> words = new HashMap<String, Word>();
        
        Word prevWord = null;
        while(st.hasMoreTokens()) {
        	
        	String token = st.nextToken().toLowerCase();
        	Word word = words.get(token);
        	if(word == null) {
        		words.put(token, new Word().setLiteral(token));
        		word = words.get(token);
        	}         	
        	
        	if(prevWord != null) {
        		prevWord.putWord(word);
        	}
        	
        	prevWord = word;
        	
        }
        
        Scanner in = new Scanner(System.in);
        
        System.out.print("Type a word from the text >");
        String input = in.nextLine().toLowerCase();
        
        ChainProcessor cp = new ChainProcessor(words);
        String result = cp.getSentence(input);
        System.out.println(result);
        
    }
}
