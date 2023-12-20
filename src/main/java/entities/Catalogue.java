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
import persistence.ConexionFichero;
import persistence.FactoryBD;

public class Catalogue implements Ipersistence {
	private List<Product> items = new ArrayList<>();

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
		JsonNode jsonNode;
		ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
		f.setNombre("Catalogo.txt");
		jsonNode = (JsonNode) f.leer();
		
		JsonNode cat = jsonNode.get(Catalogue.class.toString());
		for (JsonNode prod: cat) {
			String tipo = prod.get("tipo").asText();
			if (tipo.equals(Flower.class.toString()))
			{
				Flower fl = new Flower(prod.get("id").asInt(),prod.get("name").asText(),prod.get("color").asText());
				this.addItem(fl);
				
			}else if (tipo.equals(Tree.class.toString()))
			{
				Tree tr = new Tree(prod.get("id").asInt(),prod.get("name").asText(),prod.get("height").asText());
				this.addItem(tr);

			}else if (tipo.equals(Decoration.class.toString()))
			{
				Decoration dc = new Decoration(prod.get("id").asInt(),prod.get("name").asText(),prod.get("material").asText());
				this.addItem(dc);

			}
		}
		
	}

	
	@Override
	public void guardarBd() {
		ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
		f.setNombre("Catalogo.txt");
		JSONObject jsonCatalogo = new JSONObject();
		jsonCatalogo.put(this.getClass().toString(), items);
		f.write(jsonCatalogo);
	}


}