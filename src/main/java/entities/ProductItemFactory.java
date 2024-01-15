package entities;

import app.Shop;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.List;
import java.util.Map;

public class ProductItemFactory {

    public static Object createCatalogItem(int itemType, Catalogue catalog, Shop shop) throws IOException {
        Scanner scanner = new Scanner(System.in);

        switch (itemType) {
            case 1:
                return createTree(catalog, shop, scanner);

            case 2:
                return createFlower(catalog, shop, scanner);

            case 3:
                return createDecoration(catalog, shop, scanner);

            case 4:
                catalog.displayCatalog();
                System.out.println("Enter the ID of the tree to remove:");
                int treeToRemove = scanner.nextInt();
                catalog.removeProductById(treeToRemove);
                return null;

            case 5:
                catalog.displayCatalog();
                System.out.println("Enter the ID of the flower to collect:");
                int flowerToRemove = scanner.nextInt();
                catalog.removeProductById(flowerToRemove);
                return null;

            case 6:
                catalog.displayCatalog();
                System.out.println("Enter the ID of the decoration to be removed:");
                int decorationToRemove = scanner.nextInt();
                catalog.removeProductById(decorationToRemove);
                return null;

            case 7:
                showAllTickets(shop);
                return null;

            case 8:
                showStockWithQuantities(shop);
                return null;

            case 9:
                System.out.println("The flower shop has already been created.");
                return null;

            case 10:
                createTicket(shop, scanner, Catalogue.getInstance());
                return null;

            case 11:
                showTotalSales(shop);
                return null;

            case 0:
                System.out.println("Exiting the program.");
                catalog.displayCatalog();
                return null;

            default:
                System.out.println("Invalid option.");
                return null;
        }
    }

    private static Tree createTree(Catalogue catalog, Shop shop, Scanner scanner) throws IOException {
        System.out.println("Enter the name of the tree:");
        String treeName = scanner.nextLine();
        System.out.println("Enter the height of the tree:");
        String treeHeight = scanner.nextLine();
        System.out.println("Enter the price of the tree:");
        double treePrice = scanner.nextDouble();
        Tree tree = new Tree(treeName, treeHeight, treePrice);
        catalog.addItem(tree);
        catalog.guardarCatalogo(shop.getCatalogueDbName());

        Stock stockTree = Stock.getInstance();
        System.out.println("Do you want to add stock? (Y/N)");
        char choice1 = scanner.next().charAt(0);
        if (choice1 == 'Y' || choice1 == 'y') {
            System.out.println("Enter the quantity:");
            int quantity = scanner.nextInt();
            StockItem stockItem = new StockItem(tree.getId(), quantity);
            stockTree.addStockItem(stockItem);
            stockTree.guardarStock(shop.getStockDbName());
            System.out.println("Stock created.");
        }
        return tree;
    }

    private static Flower createFlower(Catalogue catalog, Shop shop, Scanner scanner) throws IOException {
        System.out.println("Enter the name of the flower:");
        String flowerName = scanner.nextLine();
        System.out.println("Enter the color of the flower:");
        String flowerColor = scanner.nextLine();
        System.out.println("Enter the price of the flower:");
        double flowerPrice = scanner.nextDouble();
        Flower flower = new Flower(flowerName, flowerColor, flowerPrice);
        catalog.addItem(flower);
        catalog.guardarCatalogo(shop.getCatalogueDbName());

        Stock stockFlower = Stock.getInstance();
        System.out.println("Do you want to add stock? (Y/N)");
        char choice2 = scanner.next().charAt(0);
        if (choice2 == 'Y' || choice2 == 'y') {
            System.out.println("Enter the quantity:");
            int quantity = scanner.nextInt();
            StockItem stockItem = new StockItem(flower.getId(), quantity);
            stockFlower.addStockItem(stockItem);
            stockFlower.guardarStock(shop.getStockDbName());
            System.out.println("Stock created.");
        }
        return flower;
    }

    private static Decoration createDecoration(Catalogue catalog, Shop shop, Scanner scanner) throws IOException {
        System.out.println("Enter the name of the decoration:");
        String decorationName = scanner.nextLine();
        System.out.println("Enter the decoration material:");
        String decorationMaterial = scanner.nextLine();
        System.out.println("Enter the price of the decoration:");
        double decoPrice = scanner.nextDouble();
        Decoration decoration = new Decoration(decorationName, decorationMaterial, decoPrice);
        catalog.addItem(decoration);
        catalog.guardarCatalogo(shop.getCatalogueDbName());

        Stock stockDeco = Stock.getInstance();
        System.out.println("Do you want to add stock? (Y/N)");
        char choice3 = scanner.next().charAt(0);
        if (choice3 == 'Y' || choice3 == 'y') {
            System.out.println("Enter the quantity:");
            int quantity = scanner.nextInt();
            StockItem stockItem = new StockItem(decoration.getId(), quantity);
            stockDeco.addStockItem(stockItem);
            stockDeco.guardarStock(shop.getStockDbName());
            System.out.println("Stock created.");
        }
        return decoration;
    }

    private static void showStockWithQuantities(Shop shop) {
        Stock stock = Stock.getInstance();
        Catalogue catalogue = Catalogue.getInstance();

        System.out.println("\n--- Stock with product quantities ---");
        for (Map.Entry<Integer, Integer> entry : stock.getItems().entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();
            Product product = catalogue.getProductById(productId);

            if (product != null) {
                System.out.println("- Product ID: " + productId + " - Product: " + product.getName() + " - Quantity: " + quantity);
            } else {
                System.out.println("Product ID '" + productId + "' not found in the catalogue.");
            }
        }
    }
    private static void showAllTickets(Shop shop) {
        TicketManager ticketManager = TicketManager.getInstance();

        try {
            ticketManager.leerTicket(shop.getTicketDbName());
        } catch (IOException e) {
            System.out.println("Error reading tickets: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        List<Ticket> tickets = ticketManager.getTickets();

        if (tickets.isEmpty()) {
            System.out.println("There are no tickets available.");
        } else {
            System.out.println("\n--- All Tickets ---");
            for (Ticket ticket : tickets) {
                System.out.println("Ticket ID: " + ticket.getTicketId());
                System.out.println("Product ID: " + ticket.getProductId());
                System.out.println("Product name: " + ticket.getProductName());
                System.out.println("Ticket Date: " + ticket.getTicketDate());
                System.out.println("Quantity: " + ticket.getQuantity());
                System.out.println("Total Price: " + ticket.getTotalPrice());
                System.out.println("------------------------");
            }
        }
    }
    private static void createTicket(Shop shop, Scanner scanner, Catalogue catalogo) {
        System.out.println("Enter the product ID:");
        int productId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the quantity:");
        int quantity = scanner.nextInt();

        String productName = catalogo.getProductById(productId).getName();

        Ticket ticket = new Ticket(productId, productName, quantity);

        TicketManager.getInstance().addTicket(ticket);

        System.out.println("Ticket created successfully:");
        System.out.println(ticket);
    }
    private static void showTotalSales(Shop shop) {
        System.out.println("Calculating total sales: ");

        TicketManager ticketManager = TicketManager.getInstance();

        try {
            ticketManager.leerTicket(shop.getTicketDbName());
        } catch (IOException e) {
            System.out.println("Error reading tickets: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        List<Ticket> tickets = ticketManager.getTickets();

        if (tickets.isEmpty()) {
            System.out.println("There are no tickets available.");
        } else {
            double totalSales = 0.0;

            for (Ticket ticket : tickets) {
                totalSales += ticket.getTotalPrice();
            }
            System.out.println("Total sales: " + totalSales);
        }
    }
}