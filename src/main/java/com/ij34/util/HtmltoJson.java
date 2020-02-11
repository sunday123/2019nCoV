package com.ij34.util;
/**
* @author 作者
* 类说明
*/
public class HtmltoJson {

	public  String getJsonStrbyHtml(String html) {
		int startIndex = html.indexOf("[");
		int endIndex =html.lastIndexOf("]")+1;
		
		if(startIndex!=-1 && endIndex!=0){
			return html.substring(startIndex,endIndex);
		}else{
			return null;
		}
		
		
		
		
		
		
	}

}
