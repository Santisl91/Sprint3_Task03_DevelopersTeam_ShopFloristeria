package app;

import entities.*;
import persistence.CatalogoBD;
import persistence.StockDB;
import persistence.TicketDB;
import app.Shop;

import java.io.IOException;
import java.util.Scanner;

import persistence.ShopDB;

public class Main {

    private static ShopManager shopManager = ShopManager.getInstance();
    static Catalogue catalogo = Catalogue.getInstance();
    static Stock stock = Stock.getInstance();
    static TicketManager ticket = TicketManager.getInstance();
    ShopDB shopDB = ShopDB.getInstance();


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Shop shop = seleccionarFloristeria();

        if (shop.getName() != null) {

            int eleccion;

            do {
                mostrarMenu();
                eleccion = scanner.nextInt();
                scanner.nextLine();

                if (eleccion != 0) {
                    if (eleccion == 8) {
                        ProductItemFactory.verStockConCantidades(shop);
                    } else {
                        Object nuevoItem = ProductItemFactory.createCatalogItem(eleccion, catalogo, shop);
                    }
                } else {
                    System.out.println("Programa cerrado.");
                }

            } while (eleccion != 0);

            catalogo.guardarCatalogo(shop.getCatalogueDbName());
            stock.guardarStock(shop.getStockDbName());
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
        System.out.println("8. Ver stock con cantidades de productos");
        System.out.println("9. Crear Floristeria.");
        System.out.println("10. Crear Ticket.");
        System.out.println("0. Salir");

    }

    private static Shop seleccionarFloristeria() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a la aplicación de floristería.");

        ShopManager shM = ShopManager.getInstance();
        ShopDB shopDB = ShopDB.getInstance();
        Shop shop = null;

        shM.leerShop();
        System.out.println(shopManager.toString());

        do {
            System.out.print("Ingrese el nombre de la floristería: ");
            String flowerShopName = scanner.nextLine();

            shop = shopManager.getShop(flowerShopName);

            if (shop == null) {
                System.out.println("No se encontró la floristería '" + flowerShopName + "'.");
                System.out.print("¿Deseas crear una nueva floristería? (s/n): ");
                String crearNueva = scanner.nextLine();

                if (crearNueva.equalsIgnoreCase("s")) {
                    shop = new Shop(flowerShopName);
                    shM.guardarShop();

                    // Crear archivos TXT asociados a la nueva floristería
                    CatalogoBD catalogoBD = CatalogoBD.getInstance();
                    catalogoBD.guardarBd(shop.getCatalogueDbName());

                    StockDB stockDB = new StockDB();
                    stockDB.guardarBd(shop.getStockDbName());

                    TicketDB ticketDB = TicketDB.getInstance();
                    ticketDB.guardarBd(shop.getTicketDbName());
                    stockDB.guardarBd(shop.getStockDbName());

                    System.out.println("Nueva floristería creada: " + shop.getName());
                }
            } else {
                System.out.println("Floristería seleccionada: " + shop.getName());

                try {
                    shM.leerShop();
                    catalogo.leerCatalogo(shop.getCatalogueDbName());
                    stock.leerStock(shop.getStockDbName());
                    ticket.leerTicket(shop.getTicketDbName());

                } catch (IOException e) {
                    System.out.println("Error al leer los archivos de la floristería: " + e.getMessage());
                }

                break;
            }

            System.out.println("");
        } while (shop == null);

        return shop;
    }
}

