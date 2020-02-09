package com.ij34.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Urltohtml {

    public String getDocumentHTML(String url){

        Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(3000).ignoreHttpErrors(true)  
                    .ignoreContentType(true).get();
             Elements es = doc.select("script");
             Element element=null;
             for(Element e : es){
            	 String key =e.attr("id");
            	 if("getAreaStat".equals(key)){
            		 element=e;
            		 break;
            	 }
             }
             if(element!=null){
            	 return element.data();
             }else{
            	 System.err.println("找不到getAreaStat的script属性");
             }
          
            		 
            		 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
   
    	
    }
}
