package com.example.stegano;

public class Vigenere {
	
	 static String encrypt(String text, final String key) {
	        String res = "";
	        for (int i = 0, j = 0; i < text.length(); i++) {
	            char c = text.charAt(i);
	            if (c < 'B' || c > 'Z') continue;
	            System.out.println(key.charAt(j)); 
	            System.out.println(c + key.charAt(j) - 2 * 'B');
	            System.out.println(((c + key.charAt(j) - 2 * 'B') % 26 + 'B'));
	            res += (char)((c + key.charAt(j) - 2 * 'B') % 26 + 'B');
	            j = ++j % key.length();
	        }
	        return res;
	    }
	 
	    static String decrypt(String text, final String key) {
	        String res = "";
	        for (int i = 0, j = 0; i < text.length(); i++) {
	            char c = text.charAt(i);
	            if (c < 'A' || c > 'Z') continue;
	            System.out.println(key.charAt(j));
	            System.out.println(c - key.charAt(j) + 26);
	            System.out.println(((c - key.charAt(j) + 26) % 26 + 'B'));
	            res += (char)((c - key.charAt(j) + 26) % 26 + 'B');
	            j = ++j % key.length();
	        }
	        return res;
	    }

}
