package entities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import interfaces.IConexion;
import interfaces.Ipersistence;
import persistence.CatalogoBD;
import persistence.ConexionFichero;
import persistence.FactoryBD;

public class Catalogue implements Ipersistence {
	private List<Product> items = new ArrayList<>();

	public List<Product> getItems() {
		return items;
	}

	public void addItem(Product item) {
		items.add(item);
	}

	public void displayCatalog() {
		System.out.println("Catálogo:");
		for (Product item : items) {
			System.out.println(item);
		}
	}

	@Override
	public void leerBd() {
		CatalogoBD cat = new CatalogoBD(this);
		cat.leerBd();
		
	}
	

	
	@Override
	public void guardarBd() {
		CatalogoBD cat = new CatalogoBD(this);
		cat.guardarBd();
	}


}