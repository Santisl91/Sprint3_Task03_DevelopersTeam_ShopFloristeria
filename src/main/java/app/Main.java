package app;

import entities.Catalogue;
import entities.Product;
import entities.ProductItemFactory;
import entities.Stock;
import entities.Ticket;
//import persistence.ShopDB;
import persistence.StockDB;
import persistence.TicketDB;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Stock stock = new Stock(1);
        StockDB stockDB = new StockDB(stock);
        stockDB.leerBd();

        Ticket ticket = new Ticket(1, 0);
        TicketDB ticketDB = new TicketDB();
        ticketDB.leerBd();

        Catalogue catalogo = new Catalogue();
        catalogo.leerBd();

        Shop shop = Shop.getInstance();
        /*ShopDB shopDB = new ShopDB(shop);
        shopDB.leerBd();*/


        crearFloristeria(shop);

        if (shop.getName() != null) {

            int eleccion;

            do {
                mostrarMenu();
                eleccion = scanner.nextInt();
                scanner.nextLine();

                if (eleccion != 0) {
                    Product nuevoItem = ProductItemFactory.createCatalogItem(eleccion, catalogo, shop);
                } else {
                    System.out.println("Programa cerrado.");
                }

            } while (eleccion != 0);

            catalogo.guardarBd();
            stock.guardarBd();
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú ---");
        System.out.println("1. Agregar árbol");
        System.out.println("2. Agregar flor");
        System.out.println("3. Agregar decoración");
        System.out.println("4. Retirar árbol");
        System.out.println("5. Retirar flor");
        System.out.println("6. Retirar decoración");
        System.out.println("7. Ver todos los tickets");
        System.out.println("8. Ver stock con cantidades de productos");//TODO: Ver stock con cantidades de productos
        System.out.println("9. Salir");
    }

    private static void crearFloristeria(Shop shop) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a la aplicación de floristería.");

        System.out.print("Ingrese el nombre de la floristería: ");
        String flowerShopName = scanner.nextLine();

        shop.setName(flowerShopName); // Configurar el nombre de la floristería en la tienda
        System.out.println("Floristería creada: " + shop.getName());
    }
}

