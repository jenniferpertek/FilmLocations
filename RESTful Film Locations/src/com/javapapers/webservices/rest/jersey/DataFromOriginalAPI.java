package com.javapapers.webservices.rest.jersey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataFromOriginalAPI {

	public DataFromOriginalAPI() {}
	
	//this main is only for testing that the function getData is working properly
	public static void main(String[] args) {
		DataFromOriginalAPI api = new DataFromOriginalAPI();
		ArrayList<Film> films = api.getData();
		api.viewFilms(films);
	}

	public ArrayList<Film> getData() {
		String inline = "";
		try {
			URL url = new URL("https://data.sfgov.org/resource/wwmu-gmzc.json"); 
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
							
			if(responsecode != 200) {
				System.out.println("Response code is: " +responsecode);
			} else {
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext()) { 
					inline+=sc.nextLine();
					System.out.println(inline);
				}
				sc.close();
			}
			JSONParser mainParser = new JSONParser();
			JSONArray filmArray = (JSONArray)mainParser.parse(inline);
			ArrayList<Film> films = new ArrayList<>(); 
		
			for(int i = 0; i < filmArray.size(); i++) {
				JSONObject filmObject = (JSONObject)filmArray.get(i);
				
				String titleFilm = (String)filmObject.get("title");
				String locations = (String)filmObject.get("locations");
				
				films.add(new Film(titleFilm, locations));				
			}
			conn.disconnect();
			
			return films;	
			
		} catch(Exception e) { e.printStackTrace(); }
		
		return null;
	}
	
	//shows content of arraylist
	public void viewFilms(ArrayList<Film> films) {
		for (int i = 0; i < films.size(); i++) {
			System.out.println(films.get(i).toString());
		}
	}
	
	public void hallo() {	
		try {
	
	        URL url = new URL("https://data.sfgov.org/resource/wwmu-gmzc.json");//your url i.e fetch data from .
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");
	        if (conn.getResponseCode() != 200) {
	            throw new RuntimeException("Failed : HTTP Error code : "
	                    + conn.getResponseCode());
	        }
	        InputStreamReader in = new InputStreamReader(conn.getInputStream());
	        BufferedReader br = new BufferedReader(in);
	        String output;

	        while ((output = br.readLine()) != null) {
	            System.out.println(output);
	        }
	        
	        conn.disconnect();
	
	    } catch (Exception e) {
	        System.out.println("Exception in NetClientGet:- " + e);
	    }

	}
	
	public String getResponse() {
		
//		ClientConfig clientConfig = new ClientConfig();
//		Client client = ClientBuilder.newClient(clientConfig);
//		URI serviceURI = UriBuilder.fromUri(webServiceURI).build();
//		WebTarget webTarget = client.target(serviceURI);
//
//		// response
//		System.out.println(webTarget.path("resource").path("wwmu-gmzc.json").request()
//				.accept(MediaType.APPLICATION_JSON).get(Response.class).toString());

		String resp = "";
		try {
				URL urlForGetRequest = new URL("https://data.sfgov.org/resource/wwmu-gmzc.json");
			    String readLine = null;
			    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
			    conection.setRequestMethod("GET");
			    conection.setRequestProperty("Accept", "application/json"); // set userId its a sample here
			    int responseCode = conection.getResponseCode();
			    StringBuffer response = new StringBuffer();
			    if (responseCode == HttpURLConnection.HTTP_OK) {
			        BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
			        while ((readLine = in.readLine()) != null) {
			            response.append(readLine);
			        } in.close();
			        // print result
			        System.out.println("JSON String Result " + response.toString());
			        //GetAndPost.POSTRequest(response.toString());
			        
			    } else {
			        System.out.println("GET NOT WORKED");
			        return null;
			    }
			    resp = response.toString();
		} catch ( IOException e) {
			e.printStackTrace();
		}
		return resp;

	}
}