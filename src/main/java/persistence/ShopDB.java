package persistence;

import app.Shop;
import entities.ShopManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class ShopDB {

    private Shop shop;
    private ShopManager shopManager;

    private static ShopDB instance;

    public ShopDB() {
        this.shop = new Shop();  // Inicializa con un objeto Shop por defecto
        this.shopManager = ShopManager.getInstance();  // Inicializa con una instancia de ShopManager por defecto
    }

    public ShopDB(Shop sh, ShopManager shManager) {
        this.shop = sh;
        this.shopManager = shManager;
    }


    public static ShopDB getInstance() {
        if (instance == null) {
            instance = new ShopDB();
        }
        return instance;
    }

    public void leerShop() throws IOException {
        File file = new File("Shop.txt");

        // Verificar si el archivo existe
        if (!file.exists()) {
            // Si no existe, crear el archivo
            file.createNewFile();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("Shop.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty() && !line.trim().equals("{}")) {  // Ignorar líneas vacías o con objetos JSON vacíos
                    try {
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
                            System.out.println("Error: El archivo Shop.txt no tiene el formato esperado en la línea: " + line);
                        }
                    } catch (JSONException e) {
                        System.out.println("Error al procesar la línea JSON en el archivo Shop.txt: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            // Lanzar la excepción o manejarla de acuerdo a la lógica de tu aplicación
            throw new IOException("Error al cargar la base de datos de Shop: " + e.getMessage());
        }
    }


    public void guardarShop() throws IOException {
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
        f.setNombre("Shop.txt");
        ShopManager shopManager5 = ShopManager.getInstance();

        for (Map.Entry<String, Shop> entry : shopManager5.getItems().entrySet()) {
            Shop shop = entry.getValue();
            JSONObject jsonShop = new JSONObject();

            jsonShop.put("Nombre", shop.getName());
            jsonShop.put("Catalogo", shop.getCatalogueDbName());
            jsonShop.put("Stock", shop.getStockDbName());
            jsonShop.put("Ticket", shop.getTicketDbName());

            // Escribir el JSONObject en el archivo
            f.write(jsonShop);
        }
    }
}