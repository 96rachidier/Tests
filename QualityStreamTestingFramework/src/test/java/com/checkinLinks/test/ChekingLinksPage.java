package com.checkinLinks.test;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ChekingLinksPage { //creamos la clase page para usar el patrón pom
	
	private WebDriver driver;
	
	public ChekingLinksPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public boolean chekingPageList() {
		List<WebElement> links = driver.findElements(By.tagName("a")); //encontrar todos los elementos con la etiqueta "a", los links tienen la etiqueta a
	    String url = ""; //aca se ba a ir guardando cada uno de los links de la lista anterior de webelement
		List<String> brokenLinks = new ArrayList<String>(); //lista para guardar los links inválidos
		List<String> okLinks = new ArrayList<String>(); // lista pa guardar los links que funcan
		
		HttpURLConnection httpConection = null; //para establecer conexiones
		int responseCode = 200; //guardamos el código de estado de la pagina tipo 404 o todos esos
		Iterator<WebElement> it = links.iterator(); //para ir iterando, (accediendo a objetos), a traves de la lista de links
		
		while(it.hasNext()) {
			url = it.next().getAttribute("href"); //guardamos en la Variable string url la url del link q viene en el atributo href = pagina a donde vamos con el enlace
			if(url == null || url.isEmpty()) { //o no tiene href o está vacío
				System.out.println(url + "url is not configured or it is empty");
				continue;//q continue en el código
				
			}
			try {
				httpConection = (HttpURLConnection)(new URL(url).openConnection()); //conectamos
				httpConection.setRequestMethod("HEAD"); //estamos solicitando el método head, solicita el encabezado de la pagina html
				httpConection.connect();
				responseCode = httpConection.getResponseCode(); //codigo de respuesta 1xx, 2xx. 3xx ...5xx https://www.w3.org/Protocols/rfc2616/rfc2616-sec6.html
			
			    if(responseCode>400) { //a partir de 400 o 4xx son errores de link rotos o no válidos de 1xx a 3xx son válidos
			    	System.out.println("ERROR BROKEN LINK: -- " + url);
			    	brokenLinks.add(url); // agregamos la url a la lista de los rotos
			    }else{ //mas si el código no es mayor a 400
			    	System.out.println("VALID LINK: -- " + url);
			    	okLinks.add(url);
			    	
			    }
			}catch (Exception e){ //excepciones
				e.printStackTrace(); //las imprime en la consola
				
			}
		}
		
		System.out.println("VALID LINKS: " + okLinks.size()); //el .size() es para saber cuántos links válidos hay en total
		System.out.println("INVALID LINKS: " + brokenLinks.size());
		
		if(brokenLinks.size()>0) { //si hay links rotos....
			System.out.println("****** ERROR ---------------------- BROKEN LINKS");
			for(int i = 0; i < brokenLinks.size(); i++) {
				System.out.println(brokenLinks.get(i));
				
			}
			return false;
			
		} else {
				return true;
			}		
		}
	}
