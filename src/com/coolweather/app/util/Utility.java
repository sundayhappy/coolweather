package com.coolweather.app.util;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

public class Utility {

	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response){
		
		if((response != "") && (response != null)){
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length > 0){
				for(String p : allProvinces){
					String[] array = p.split("\\|");//???????????????????????????????????
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);;
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}		
		return false;
	}
	
	public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
		
		if((response != "") && (response != null)){
			String[] allCities = response.split(",");
			if(allCities != null && allCities.length > 0){
				for(String c : allCities){
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		
		return false;
		
	}
	
	
	public synchronized static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,String response,int cityId){
		
		if((response != "") && (response != null)){
			String[] allcounties = response.split(",");
			if(allcounties != null && allcounties.length > 0){
				for(String c : allcounties){
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					coolWeatherDB.savecounty(county);
				}
				return true;
			}
		}
		return false;
		
	}
}
