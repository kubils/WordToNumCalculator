package com.ks.converter;

import java.util.Locale;

public class ConverterImp implements Converter {

	private NumberToWordConv numberToWordConv;
	private WordToNumberConv wordToNumberConv;
	
	public ConverterImp() {
		
		this.numberToWordConv = new NumberToWordConv();
		this.wordToNumberConv = new WordToNumberConv();
		

	}
	
	public int convertToNum(String text) {
		try {
			return this.wordToNumberConv.parseWordToNum(text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public String convertToWord(int num) {
		return numberToWordConv.convertNumToWord(num);
	}
}
