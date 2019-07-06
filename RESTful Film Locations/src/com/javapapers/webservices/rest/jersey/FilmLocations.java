package com.javapapers.webservices.rest.jersey;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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
//		DataFromOriginalAPI data = new DataFromOriginalAPI();
//		ArrayList<Film> films = data.getData();
		
		// this is just test-data
		ArrayList<Film> a = new ArrayList<>();
		a.add(new Film("film1", "wien"));
		a.add(new Film("film2", "brig"));
		a.add(new Film("film3", "brig"));
		a.add(new Film("film4", "brig"));
		
		//only display the data that was searched for
		ArrayList<Film> temp = new ArrayList<>();
		for (int i = 0; i < a.size(); i++) {
			if(a.get(i).getTitle().equals(title) && a.get(i).getLocations().equals(locations)) {
				temp.add(a.get(i));
			} else if(title == null && a.get(i).getLocations().equals(locations)) {
				temp.add(a.get(i));
			} else if (a.get(i).getTitle().equals(title) && locations == null) {
				temp.add(a.get(i));
			}
		}
		
		// return data that was searched
		return temp;
	}
		

	@GET
	@Path("/allFilms")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Film> viewAllFilms() {
		ArrayList<Film> a = new ArrayList<>();
		// this would be the data from the original api
//		DataFromOriginalAPI data = new DataFromOriginalAPI();
//		ArrayList<Film> films = data.getData();
		
		// this is just test-data
		a.add(new Film("film1", "wien"));
		a.add(new Film("film2", "brig"));
		a.add(new Film("film3", "brig"));
		a.add(new Film("film4", "brig"));
		
		//show all the films
		return a;
	}

	
	public ArrayList<Film> getDataFromOriginalAPI() {
		System.out.println("im inside hello");
		String inline = "";
		try {
			URL url = new URL("https://data.sfgov.org/resource/wwmu-gmzc.json"); 
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
							
			if(responsecode != 200) {
				System.out.println("Response code is: " +responsecode);
				//return null;
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
				
				System.out.println("im now adding something");
				
				films.add(new Film(titleFilm, locations));
				
//				System.out.println("Title: " + titleFilm);
//				System.out.println("Location: " + locations);
				
			}
			conn.disconnect();
			return films;	
			
		} catch(Exception e) { e.printStackTrace(); }
		return null;
	}

}

