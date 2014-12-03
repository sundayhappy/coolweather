package com.coolweather.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class HttpUtil {

	public static void sendHttpRequest(final String address,final HttpCallbackListener listener){
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				Log.d("testtest", "sendHttpRequest run() in");
				// TODO Auto-generated method stub
				HttpURLConnection connection = null;
				
				try {
					Log.d("testtest", "sendHttpRequest run() in try ");
					URL url = new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while((line = reader.readLine()) != null){
						response.append(line);
					}
					
					Log.d("testtest", response.toString());
					
					
					if(listener != null){ 
						listener.onFinish(response.toString());
					}
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					if(listener != null){
						listener.onError(e);
					}
				}finally{
					if(connection != null){
						connection.disconnect();
					}
				}
			}
			
		}).start();
		
	}
	
}
