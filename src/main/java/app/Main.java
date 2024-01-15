package app;

import entities.*;
import persistence.CatalogoBD;
import persistence.StockDB;
import persistence.TicketDB;
import app.Shop;

import java.io.IOException;
import java.lang.runtime.SwitchBootstraps;
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
            ticket.guardarTicket(shop.getTicketDbName());
            shopManager.guardarShop();
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

        ShopManager shopManager = ShopManager.getInstance();
        shopManager.leerShop();
        System.out.println(shopManager.toString());

        Shop selectedShop = null;

        do {
            System.out.print("Ingrese el nombre de la floristería: ");
            String flowerShopName = scanner.nextLine();

            selectedShop = shopManager.getShop(flowerShopName);

            if (selectedShop == null) {
                System.out.println("No se encontró la floristería '" + flowerShopName + "'.");
                System.out.print("¿Deseas crear una nueva floristería? (s/n): ");
                String crearNueva = scanner.nextLine();

                if (crearNueva.equalsIgnoreCase("s")) {
                    // Crear una nueva tienda
                    Shop newShop = new Shop(flowerShopName);

                    // Guardar la nueva tienda y sus datos
                    shopManager.addShop(newShop);
                    shopManager.guardarShop();

                    // Crear archivos TXT asociados a la nueva floristería
                    CatalogoBD catalogoBD = CatalogoBD.getInstance();
                    catalogoBD.guardarBd(newShop.getCatalogueDbName());

                    StockDB stockDB = new StockDB();
                    stockDB.guardarBd(newShop.getStockDbName());

                    TicketDB ticketDB = TicketDB.getInstance();
                    ticketDB.guardarBd(newShop.getTicketDbName());

                    System.out.println("Nueva floristería creada: " + newShop.getName());
                }
            } else {
                System.out.println("Floristería seleccionada: " + selectedShop.getName());

                try {
                    shopManager.leerShop();
                    catalogo.leerCatalogo(selectedShop.getCatalogueDbName());
                    stock.leerStock(selectedShop.getStockDbName());
                    ticket.leerTicket(selectedShop.getTicketDbName());

                } catch (IOException e) {
                    System.out.println("Error al leer los archivos de la floristería: " + e.getMessage());
                }

                break;
            }

            System.out.println("");
        } while (selectedShop == null);

        return selectedShop;
    }

}

