Alle Filme ausgeben: http://localhost:8080/RESTful_Film_Locations/rest/filmLocations/allFilms
Filme anhand von Namen oder Location suchen: http://localhost:8080/RESTful_Film_Locations/rest/filmLocations/film
	--> Suche nach einem Titel: http://localhost:8080/RESTful_Film_Locations/rest/filmLocations/film?title=film1
	--> Suche nach einer location: http://localhost:8080/RESTful_Film_Locations/rest/filmLocations/film?locations=brig
	--> Suche nach Titel und Location: http://localhost:8080/RESTful_Film_Locations/rest/filmLocations/film?title=film3&&locations=brig

In der Klasse "DataFromOriginalAPI" werden title und locations von der Orginal-API gelesen als JSON-Objekte und Objekte vom Typ Film erstellt.
Es wird eine Arraylist vom Typ Film erstellt und an die @GET Methoden gegeben, die Arraylist-Objekte enthalten nur den title und die locations.

//--------------------- Grund weshalb ich diese beiden Teile nicht zusammenfügen konnte --------------------------------
Leider ist mir ein Fehler aufgetretten den ich innerhalb der gegebenen 5h nicht lösen konnte:

Jul 06, 2019 6:23:53 PM org.apache.catalina.core.StandardWrapperValve invoke
SCHWERWIEGEND: Servlet.service() for servlet [RESTful Jersey Film Locations Service] in context with path [/RESTful_Film_Locations] threw exception [org.glassfish.jersey.server.ContainerException: java.lang.NoClassDefFoundError: org/json/simple/parser/JSONParser] with root cause
java.lang.ClassNotFoundException: org.json.simple.parser.JSONParser
	at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1309)
	at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1138)
	at com.javapapers.webservices.rest.jersey.FilmLocations.getDataFromOriginalAPI(FilmLocations.java:75)
	at com.javapapers.webservices.rest.jersey.FilmLocations.getObject(FilmLocations.java:25)
	......
	
org.json.simple.parser.JSONParser ist ein Teil des externen jar-files json-simple-1.1.jar welches ich in den build-path hinzugefügt habe (habe ich bereits bei meiner Bachelorarbeit über Linked Data verwendet)
Ich konnte leider wie gesagt nicht herausfinden warum es diesen Fehler gibt, ich habe keine Lösung gefunden.