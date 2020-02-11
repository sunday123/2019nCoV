package com.ij34.model;
/**
* @author 作者
* 类说明
*/
public class City implements Comparable<City>{
	



	private String name;
    private int count;
    private String province;
	public City(String name, int count, String province) {
		super();
		this.name = name;
		this.count = count;
		this.province = province;
	}
	
	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	
	@Override
	public String toString() {
		return name + "\t" + count + "\t" + province;
	}
    @Override
	public int compareTo(City o) {
		// TODO Auto-generated method stub
        if (this.count > o.count){
        	 return -1;
        }else if (this.count < o.count){
        	 return 1;
        }else{
        	 return 0;
        }
        
    
	}

    
    

}
