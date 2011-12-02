package com.nuitinfo.object;

public class Product {
	
	private int id;
	private String ean;
	private String name;
	private String description;
	private double price;
	private int compteur;
	
	public Product(int id, String ean, String name, String description, double price, int compteur){
		this.id = id;
		this.ean = ean;
		this.name = name;
		this.description = description;
		this.price = price;
		this.compteur = compteur;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEan() {
		return ean;
	}
	public void setEan(String ean) {
		this.ean = ean;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCompteur() {
		return compteur;
	}
	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}
	
	

}
