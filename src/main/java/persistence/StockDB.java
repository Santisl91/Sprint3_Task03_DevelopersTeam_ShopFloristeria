package persistence;

import entities.Product;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;

import entities.Stock;
import entities.StockItem;

import java.io.File;
import java.io.IOException;

public class StockDB {
    private Stock stockDb;

    public StockDB(Stock stock) {
        this.stockDb = stock;
    }
    public void leerBd() {
        JsonNode jsonNode;
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
        f.setNombre("Stock.txt");

        File file = new File(f.getNombre());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        jsonNode = (JsonNode) f.leer();

        if (jsonNode != null) {
            JsonNode stockNode = jsonNode.get(Stock.class.toString());
            if (stockNode != null) {
                for (JsonNode prod: stockNode) {
                    String tipo = prod.get("tipo").asText();
                    double precio = prod.get("precio").asDouble();
                    int cantidad = prod.get("cantidad").asInt();
                    Product product = new Product(tipo, precio);
                    StockItem item = new StockItem(product, cantidad);
                    stockDb.getItems().add(item);
                }
            } else {
                System.out.println("Error al leer el archivo JSON");
            }
        }
    }
    public void guardarBd() {
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
        f.setNombre("Stock.txt");
        JSONObject jsonStock = new JSONObject();
        jsonStock.put(stockDb.getClass().toString(), stockDb.getItems());
        f.write(jsonStock);

    }

}
