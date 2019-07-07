package com.javapapers.webservices.rest.jersey;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Path("/filmLocations")
public class FilmLocations {

	@GET
	@Path("/film")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Film> getObject(@QueryParam("title") String title, @QueryParam("locations") String locations) {
		// this would be the data from the original api
		
		if(title == null && locations == null) {
			return getDataFromOriginalAPI();
		} else if(title == null && locations != null) {
			System.out.println("im in here");
			return getLocations(locations);
		} else if(title != null && locations == null) {
			return getTitles(title);
		} else {
			return getTitleAndLoc(title, locations);
		}

	}
	
	public ArrayList<Film> getTitleAndLoc(String title, String locations) {
		ArrayList<Film> films = getDataFromOriginalAPI();
		
		ArrayList<Film> temp = new ArrayList<>();
		for (int i = 0; i < films.size(); i++) {
			if(films.get(i).getTitle().equals(title) && films.get(i).getLocations().equals(locations)) {
				temp.add(films.get(i));
			}
		}
		
		return temp;
	}

	public ArrayList<Film> getLocations(String locations) {
		ArrayList<Film> films = getDataFromOriginalAPI();
		
		ArrayList<Film> temp = new ArrayList<>();
		for (int i = 0; i < films.size(); i++) {
			if(films.get(i).getLocations() != null) {
				if(films.get(i).getLocations().equals(locations)) {
					temp.add(films.get(i));
				}
			}
		}
		
		return temp;
	}
	
	public ArrayList<Film> getTitles(String title) {
		ArrayList<Film> films = getDataFromOriginalAPI();
		
		ArrayList<Film> temp = new ArrayList<>();
		for (int i = 0; i < films.size(); i++) {
			if(films.get(i).getTitle() != null) {
				if(films.get(i).getTitle().equals(title)) {
					temp.add(films.get(i));
				}
			}
		}
		
		return temp;
	}
		
	public ArrayList<Film> getDataFromOriginalAPI() {
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

}

