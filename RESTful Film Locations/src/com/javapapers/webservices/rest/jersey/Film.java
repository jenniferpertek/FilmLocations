package com.javapapers.webservices.rest.jersey;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Film {

	private String title;
	private String locations;
	
	public Film(String title, String locations) {
		this.title = title;
		this.locations = locations;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocations() {
		return locations;
	}
	public void setLocations(String locations) {
		this.locations = locations;
	}
	
	@Override
	public String toString() {
		// only for testing
		return "title: " + title + ", locations: " + locations;
	}
}