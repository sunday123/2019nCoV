package com.ij34.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ij34.model.City;
import com.ij34.util.HtmltoJson;
import com.ij34.util.UrlPing;
import com.ij34.util.Urltohtml;

public class testWork {
   private static final String url="https://ncov.dxy.cn/ncovh5/view/pneumonia_peopleapp?from=timeline&isappinstalled=0";
   //下面这些当作城市处理
   private static final List<String> provincelist=getprovinceList();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 UrlPing up=new UrlPing();
		 Urltohtml ss=new Urltohtml();
		 HtmltoJson hj =new HtmltoJson();
		 if(up.isPing(url)){
				String htmlStr=ss.getDocumentHTML(url);
				if(htmlStr!=null){
						String jsonStr=hj.getJsonStrbyHtml(htmlStr);
//						System.out.println(jsonStr);
						ArrayList<City> citys =doJsonArray(jsonStr);
						Collections.sort(citys,City::compareTo);
						Date now = new Date();
						SimpleDateFormat f = new SimpleDateFormat("截至时间: " + "yyyy年  MM月 dd日 HH点 mm分 ss秒 E");
						System.out.println(f.format(now));
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.err.println("城市确诊人数排名");
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("排名\t"+"城市\t"+"确诊人数\t"+"省份");
						int rank=0;//排名
						int preCount=-1;//前一个数量
						int sameCount=0;//同名次的个数
						for(int i=0;i<citys.size();i++){
							City city =citys.get(i);
							if(city.getCount()==preCount){
								sameCount++;
								System.out.println((rank)+"\t"+city);
							}else{
								preCount=city.getCount();
								rank=rank+sameCount+1;
								sameCount=0;
								System.out.println((rank)+"\t"+city);
							}
							
							if((i+1)%50==0){
								System.out.println("-----------------前"+(i+1)+"分割线↑--------------------");
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
						}
						
						
				}
//				System.out.println("-------------------------------------");
		 }else{
			 System.err.println("Ping不通"+url);
		 }
	}
	
	
	
  private static  ArrayList<City> doJsonArray(String jsonStr){
	  ArrayList<City> result=new ArrayList<City>();
	 JSONArray provinceArray =JSONArray.parseArray(jsonStr);
		for (int i = 0; i < provinceArray.size(); i++) {
			JSONObject provinceObject = provinceArray.getJSONObject(i);
			String provinceName =provinceObject.getString("provinceName");
			String provinceShortName =provinceObject.getString("provinceShortName");
			if(provincelist.contains(provinceShortName)){
				int count=provinceObject.getIntValue("confirmedCount");
				result.add(new City(provinceShortName, count, provinceName));
			}else{
				JSONArray citys = provinceObject.getJSONArray("cities");
				for (int j = 0; j < citys.size(); j++) {
					JSONObject cityObject = citys.getJSONObject(j);
					String cityName =cityObject.getString("cityName");
					if(!cityName.equals("待明确地区")){
						int confirmedCount=cityObject.getIntValue("confirmedCount");
						result.add(new City(cityName, confirmedCount, provinceName));
					}

				}
			}
			
		}
		return result;
	 
  }
  
  //下面这些当作城市处理的行政区
  private static List<String> getprovinceList(){
	  ArrayList<String> list = new ArrayList<String>();
	  list.add("香港");
	  list.add("澳门");
	  list.add("台湾");
	  list.add("北京");
	  list.add("天津");
	  list.add("上海");
	  list.add("重庆");
	  return list;
  }
}
