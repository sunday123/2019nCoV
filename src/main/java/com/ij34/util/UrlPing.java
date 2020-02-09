package com.ij34.util;

import java.io.IOException;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;

public class UrlPing {

    public boolean isPing(String url){
        boolean flag;

		try {
			Jsoup.connect(url).timeout(3000).ignoreHttpErrors(true)  
                    .ignoreContentType(true).get();
			flag=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			flag=false;
		}
   
		return flag;
    	
    }



}