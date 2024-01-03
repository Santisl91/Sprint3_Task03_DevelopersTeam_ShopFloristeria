package persistence;

import com.fasterxml.jackson.databind.JsonNode;
import entities.Product;
import entities.Stock;
import entities.StockItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class StockDB {
    private Stock stockDb;

    public StockDB(Stock stock) {
        stockDb = stock;
    }

    public void leerBd() throws IOException {
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
        f.setNombre("Stock.txt");

        File file = new File(f.getNombre());
        if (!file.exists()) {
            throw new IOException("El archivo Stock.txt no existe");
        }

        JsonNode jsonNode = (JsonNode) f.leer();

        if (jsonNode != null) {
            if (jsonNode.has("stock")) {
                JsonNode stockNode = jsonNode.get("stock");
                for (JsonNode stockItemNode : stockNode) {
                    int productId = stockItemNode.get("id").asInt();
                    int quantity = stockItemNode.get("cantidad").asInt();

                    // Construir un objeto StockItem con id y cantidad
                    Product product = new Product("Nombre Predeterminado", 0.0);
                    product.setId(productId);

                    StockItem item = new StockItem(product, quantity);
                    stockDb.getItems().add(item);
                }
            } else {
                throw new IOException("Error al leer el archivo JSON - No se encontró el nodo 'stock'");
            }
        } else {
            throw new IOException("Error al leer el archivo JSON - El contenido es nulo");
        }
    }


    public void guardarBd() throws IOException {
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
        f.setNombre("Stock.txt");

        JSONArray stockArray = new JSONArray();
        for (StockItem stockItem : stockDb.getItems()) {
            JSONObject stockItemObject = new JSONObject();
            stockItemObject.put("id", stockItem.getProduct().getId());
            stockItemObject.put("cantidad", stockItem.getQuantity());
            stockArray.put(stockItemObject);
        }

        JSONObject jsonStock = new JSONObject();
        jsonStock.put("stock", stockArray);

        f.write(jsonStock);  // Agrega sangrías y saltos de línea para hacer el JSON más legible
    }

}
