package persistence;
/*
import entities.Product;
import entities.Stock;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;

import app.Shop;

import java.io.File;
import java.io.IOException;

public class ShopDB {

    private Shop shop;

    public ShopDB(Shop shop) {
        this.shop = shop;
    }
    public void leerBd() {
        JsonNode jsonNode;
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
        f.setNombre("Shop.txt");

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
            JsonNode shopNode = jsonNode.get(Shop.class.toString());
            for (JsonNode stock: shopNode) {
                int id = stock.get("id").asInt();
                Stock newStock = new Stock(id);
                for (JsonNode item: stock.get("items")) {
                    String productType = item.get("tipo").asText();
                    double price = item.get("price").asDouble();
                    int quantity = item.get("quantity").asInt();
                    Product product = new Product(productType, price);
                    newStock.addStockItem(product, quantity);
                }
                shop.getStockProducts().add(newStock);
            }
        } else {
            System.out.println("Error al leer el archivo JSON");
        }
    }
    public void guardarBd() {
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
        f.setNombre("Shop.txt");
        JSONObject jsonShop = new JSONObject();
        jsonShop.put(shop.getClass().toString(), shop.getStockProducts());
        f.write(jsonShop);
    }
}*/
