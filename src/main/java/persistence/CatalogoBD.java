package persistence;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;

import entities.Catalogue;
import entities.Decoration;
import entities.Flower;
import entities.Tree;

public class CatalogoBD {
	private Catalogue catalogo;
	
	public CatalogoBD(Catalogue cat) {
		catalogo = cat;
	}

	public void leerBd()
	{
		JsonNode jsonNode;
		ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
		f.setNombre("Catalogo.txt");
		jsonNode = (JsonNode) f.leer();

		JsonNode cat = jsonNode.get(Catalogue.class.toString());
		for (JsonNode prod: cat) {
			String tipo = prod.get("tipo").asText();
			if (tipo.equals(Flower.class.toString()))
			{
				Flower fl = new Flower(prod.get("id").asInt(),prod.get("name").asText(),prod.get("color").asText(),prod.get("price").asDouble());
				catalogo.addItem(fl);

			}else if (tipo.equals(Tree.class.toString()))
			{
				Tree tr = new Tree(prod.get("id").asInt(),prod.get("name").asText(),prod.get("height").asText(),prod.get("price").asDouble());
				catalogo.addItem(tr);

			}else if (tipo.equals(Decoration.class.toString()))
			{
				Decoration dc = new Decoration(prod.get("id").asInt(),prod.get("name").asText(),prod.get("material").asText(),prod.get("price").asDouble());
				catalogo.addItem(dc);

			}
		}
	}
	public void guardarBd() {
		ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
		f.setNombre("Catalogo.txt");
		JSONObject jsonCatalogo = new JSONObject();
		jsonCatalogo.put(catalogo.getClass().toString(), catalogo.getItems());
		f.write(jsonCatalogo);

	}

}
