package com.phonebook.validator.util;

import java.io.FileReader;

import org.json.simple.parser.JSONParser;

public class Utilities {

	
	public static Object readJson(String filename) throws Exception {
	    FileReader reader = new FileReader(filename);
	    JSONParser jsonParser = new JSONParser();
	    return jsonParser.parse(reader);
	}
	
}
