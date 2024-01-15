package app;

import entities.*;
import persistence.CatalogueDb;
import persistence.StockDb;
import persistence.TicketDb;

import java.io.IOException;
import java.util.Scanner;

import persistence.ShopDb;

public class Main {
    private static ShopManager shopManager = ShopManager.getInstance();
    static Catalogue catalogo = Catalogue.getInstance();
    static Stock stock = Stock.getInstance();
    static TicketManager ticket = TicketManager.getInstance();
    ShopDb shopDB = new ShopDb();

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
                    Object nuevoItem = ProductItemFactory.createCatalogItem(eleccion, catalogo, shop);
                } else {
                    System.out.println("Closed program.");
                }

            } while (eleccion != 0);

            catalogo.guardarCatalogo(shop.getCatalogueDbName());
            stock.guardarStock(shop.getStockDbName());
        }
    }
    private static void mostrarMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add tree");
        System.out.println("2. Add flower");
        System.out.println("3. Add decoration");
        System.out.println("4. Remove tree");
        System.out.println("5. Remove flower");
        System.out.println("6. Remove decoration");
        System.out.println("7. Show all tickets");
        System.out.println("8. Show stock with product quantities");
        System.out.println("9. Create Florist.");
        System.out.println("10. Create Ticket.");
        System.out.println("11. Total sales.");
        System.out.println("0. Exit menu.");

    }
    private static Shop seleccionarFloristeria() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the florist app.");

        ShopDb shopDB = new ShopDb();
        Shop shop = null;

        do {
            System.out.print("Enter the name of the florist: ");
            String flowerShopName = scanner.nextLine();

            shop = shopManager.getShop(flowerShopName);

            if (shop == null) {
                System.out.println("Florist not found '" + flowerShopName + "'.");
                System.out.print("Do you want to create a new flower shop? (y/n): ");
                String crearNueva = scanner.nextLine();

                if (crearNueva.equalsIgnoreCase("y")) {
                    shop = new Shop(flowerShopName);

                    String catalogoFileName = flowerShopName + ".catalog.txt";
                    String stockFileName = flowerShopName + ".stock.txt";
                    String ticketFileName = flowerShopName + ".stock.txt";

                    shop.setCatalogueDbName(catalogoFileName);
                    shop.setStockDbName(stockFileName);
                    shop.setTicketDbName(ticketFileName);

                    shopDB.guardarShop();

                    CatalogueDb catalogueDb = CatalogueDb.getInstance();
                    catalogueDb.guardarBd(shop.getCatalogueDbName());

                    StockDb stockDB = StockDb.getInstance();
                    stockDB.guardarBd(shop.getStockDbName());

                    TicketDb ticketDB = TicketDb.getInstance();
                    ticketDB.guardarBd(shop.getTicketDbName());

                    System.out.println("New florist created: " + shop.getName());
                }
            } else {
                System.out.println("Selected florista: " + shop.getName());

                try {
                    catalogo.leerCatalogo(shop.getCatalogueDbName());
                    stock.leerStock(shop.getStockDbName());
                    ticket.leerTicket(shop.getTicketDbName());

                } catch (IOException e) {
                    System.out.println("Error reading florist files: " + e.getMessage());
                }

                break;
            }
            System.out.println("");
        } while (shop == null);

        return shop;
    }
}

