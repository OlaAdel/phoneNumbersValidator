package com.phonebook.validator.util;

import java.util.HashMap;
import java.util.Map;

import com.phonebook.validator.model.Country;


public class CountryUtil {
	
	public static Map<?,?> codeCountryMap;	
	public static Map<?,?> countryRegexMap;
	public static String codeCountryFilePath = "codes.json";
	public static String countryRegexFilePath = "countries.json";

	static 
    {
			try {
				codeCountryMap = (HashMap<?, ?>) Utilities.readJson(codeCountryFilePath);
				countryRegexMap = (HashMap<?, ?>) Utilities.readJson(countryRegexFilePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
    }
	
	public static boolean isCodeExist(String code) {
		return codeCountryMap.containsKey(code);
	}
	
	public static Country getCountryByCode(String code) {
		
		Country country = new Country();
		
		country.setCode(code);	
		country.setName(codeCountryMap.get(country.getCode()).toString());
		country.setRegex(countryRegexMap.get(country.getName()).toString());
		
		return country;
	}
}
