package persistence;

import entities.Product;
import entities.ShopManager;
import entities.Stock;
import org.json.JSONException;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import app.Shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ShopDB {

    private Shop shop;
    private ShopManager shopManager;

    public ShopDB(Shop shop, ShopManager shopManager) {
        this.shop = shop;
        this.shopManager = shopManager;
    }

    public ShopDB() {

    }

    public void leerShop() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("Shop.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Convertir la línea JSON a un JSONObject
                JSONObject jsonShop = new JSONObject(line);

                // Validar que el JSONObject tenga las claves necesarias
                if (jsonShop.has("Nombre") && jsonShop.has("Catalogo") && jsonShop.has("Stock") && jsonShop.has("Ticket")) {
                    // Obtener los valores del JSONObject
                    String nombre = jsonShop.getString("Nombre");
                    String catalogo = jsonShop.getString("Catalogo");
                    String stock = jsonShop.getString("Stock");
                    String ticket = jsonShop.getString("Ticket");

                    // Crear una instancia de Shop con la información obtenida
                    Shop shop = new Shop(nombre, catalogo, stock, ticket);

                    // Puedes hacer algo con el objeto Shop cargado, como almacenarlo en una lista
                    shopManager.addShop(shop);
                } else {
                    System.out.println("Error: El archivo Shop.txt no tiene el formato esperado.");
                }
            }
        } catch (IOException | JSONException e) {
            // Lanzar la excepción o manejarla de acuerdo a la lógica de tu aplicación
            throw new IOException("Error al cargar la base de datos de Shop: " + e.getMessage());
        }
    }

    public void guardarShop() throws IOException {
        if (shop != null) {
            ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
            f.setNombre("Shop.txt");

            // Validar que el Shop no sea nulo antes de intentar guardarlo
            if (shop != null) {
                // Crear un JSONObject con la información del Shop
                JSONObject jsonShop = new JSONObject();
                jsonShop.put("Nombre", shop.getName());
                jsonShop.put("Catalogo", shop.getCatalogueDbName());
                jsonShop.put("Stock", shop.getStockDbName());
                jsonShop.put("Ticket", shop.getTicketDbName());

                // Escribir el JSONObject en el archivo
                f.write(jsonShop);
            } else {
                System.out.println("Error: El objeto Shop es nulo y no puede ser guardado.");
            }
        }
    }
}