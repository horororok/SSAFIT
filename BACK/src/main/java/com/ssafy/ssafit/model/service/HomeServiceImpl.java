package com.ssafy.ssafit.model.service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dto.News;

@Service
public class HomeServiceImpl implements HomeService{

	@Override
	public List<News> getNewsList() {
		List<News> list = null;
		
		String clientId = "upZYLrlhyM8L1zcLdVkg"; //애플리케이션 클라이언트 아이디
        String clientSecret = "V2ZaSjGhQB"; //애플리케이션 클라이언트 시크릿

        String apiURL;
		try {
			apiURL = String.format("https://openapi.naver.com/v1/search/news.json?query=%s&display=5&sort=date", URLEncoder.encode("스포츠", "UTF-8"));
			Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	        String responseBody = get(apiURL,requestHeaders);
	        
	        JSONParser Jparser = new JSONParser();
	        JSONObject obj = (JSONObject) Jparser.parse(responseBody);
	        JSONArray items = (JSONArray) obj.get("items");
	       
	        list = new ArrayList<News>();
	        for(int i=0; i<items.size(); i++) {
	        	News news = new News();
	        	JSONObject item = (JSONObject) items.get(i);
	        	
	        	news.setTitle((String) item.get("title"));
	        	news.setLink((String) item.get("link"));
	        	
	        	list.add(news);
	        }

	        System.out.println("responsebody: " + responseBody);
	        System.out.println("api 실행 완료");
	        System.out.println("news list : " + list);
		} catch (Exception e) {
			e.printStackTrace();  
		}
		return list;
	}
	
	private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
	
}
