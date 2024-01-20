package menu;

import entities.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                catalog.guardarCatalogo(shop.getCatalogueDbName());
                return null;

            case 5:
                catalog.displayCatalog();
                System.out.println("Enter the ID of the flower to collect:");
                int flowerToRemove = scanner.nextInt();
                catalog.removeProductById(flowerToRemove);
                catalog.guardarCatalogo(shop.getCatalogueDbName());
                return null;

            case 6:
                catalog.displayCatalog();
                System.out.println("Enter the ID of the decoration to be removed:");
                int decorationToRemove = scanner.nextInt();
                catalog.removeProductById(decorationToRemove);
                catalog.guardarCatalogo(shop.getCatalogueDbName());
                return null;

            case 7:
                Stock stock = Stock.getInstance();
                Stock.showStockWithQuantities(shop);
                System.out.println("Enter the ID of the product you want to add stock:");
                int idToAdd = scanner.nextInt();
                System.out.println("Enter the new stock:");
                int newStock = scanner.nextInt();
                stock.setStockQuantityById(idToAdd, newStock);
                stock.guardarStock(shop.getStockDbName());
                return null;
            case 8:
                showAllTickets(shop);
                return null;

            case 9:
                Stock.getInstance();
                Stock.showStockWithQuantities(shop);
                return null;

            case 10:
                System.out.println("Create new florist.");
                System.out.print("Enter the name of the florist: ");
                String newFlowerShopName = scanner.nextLine();
                ShopManager shopManager = ShopManager.getInstance();
                shopManager.crearNuevaFloristeria(newFlowerShopName);
                return null;

            case 11:
                Stock.showStockWithQuantities(shop);
                createTicket(shop, scanner, Catalogue.getInstance());
                return null;

            case 12:
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
        return createProduct("tree", catalog, shop, scanner);
    }

    private static Flower createFlower(Catalogue catalog, Shop shop, Scanner scanner) throws IOException {
        return createProduct("flower", catalog, shop, scanner);
    }

    private static Decoration createDecoration(Catalogue catalog, Shop shop, Scanner scanner) throws IOException {
        return createProduct("decoration", catalog, shop, scanner);
    }

    private static <T extends Product> T createProduct(String productType, Catalogue catalog, Shop shop, Scanner scanner) throws IOException {
        String productName = null;
        T product = null;

        if (productType.equals("tree")) {
            System.out.println("Enter the name of the " + productType + ":");
            productName = getValidInput("^[a-zA-Z]+$", "The name should not contain numbers, special characters, or spaces.", scanner);

            System.out.println("Enter the height of the tree:");
            double treeHeight = getValidDoubleInput("Invalid height. Please enter a number.", scanner);

            System.out.println("Enter the price of the tree:");
            double treePrice = getValidDoubleInput("Invalid price. Please enter a number.", scanner);

            product = (T) new Tree(productName, treeHeight, treePrice);
        } else if (productType.equals("flower")) {
            System.out.println("Enter the name of the " + productType + ":");
            productName = getValidInput("^[a-zA-Z]+$", "The name should not contain numbers, special characters, or spaces.", scanner);

            System.out.println("Enter the color of the flower:");
            String flowerColor = getValidInput("^[a-zA-Z]+$", "The color should not contain numbers, special characters, or spaces.", scanner);

            System.out.println("Enter the price of the flower:");
            double flowerPrice = getValidDoubleInput("Invalid price. Please enter a number.", scanner);

            product = (T) new Flower(productName, flowerColor, flowerPrice);
        } else if (productType.equals("decoration")) {
            System.out.println("Enter the decoration material ('madera' or 'plástico'):");
            String decorationMaterial = getValidInput("^(madera|plástico)$", "Invalid material. Please enter 'madera' or 'plástico'.", scanner);

            System.out.println("Enter the price of the decoration:");
            double decorationPrice = getValidDoubleInput("Invalid price. Please enter a number.", scanner);

            product = (T) new Decoration(productName, decorationMaterial, decorationPrice);
        }

        if (product != null) {
            catalog.addItem(product);
            catalog.guardarCatalogo(shop.getCatalogueDbName());

            Stock stock = Stock.getInstance();
            System.out.println("Do you want to add stock? (Y/N)");
            char choice = getValidCharInput(scanner);

            if (choice == 'Y' || choice == 'y') {
                scanner.nextLine();

                System.out.println("Enter the quantity:");
                int quantity = getValidIntInput("Invalid quantity. Please enter a number.", scanner);
                StockItem stockItem = new StockItem(product.getId(), quantity);
                stock.addStockItem(stockItem);
                System.out.println("Stock created.");
            } else if (choice == 'N' || choice == 'n') {
                Stock.createProductWithZeroQuantity(product.getId());
            }
            stock.guardarStock(shop.getStockDbName());
        }
        return product;
    }

    private static void createTicket(Shop shop, Scanner scanner, Catalogue catalogo) throws IOException {
        TicketManager ticketManager = TicketManager.getInstance();
        List<Ticket> tickets = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        do {
            System.out.println("Enter the product ID (0 to finish adding products):");
            int productId = scanner.nextInt();

            if (productId == 0) {
                break;
            }

            scanner.nextLine();
            System.out.println("Enter the quantity:");
            int quantity = scanner.nextInt();

            Product product = catalogo.getProductById(productId);

            if (product != null) {
                int availableStock = Stock.getInstance().getItems().getOrDefault(productId, 0);

                if (availableStock >= quantity) {
                    String productName = product.getName();
                    Ticket ticket = new Ticket(productId, productName, quantity, currentDate);
                    tickets.add(ticket);
                    System.out.println("Product added to the ticket.");

                    Stock.getInstance().reduceStock(product, quantity);
                } else {
                    System.out.println("Error: Insufficient stock for Product ID '" + productId + "'. Available stock: " + availableStock);
                }
            } else {
                System.out.println("Product ID '" + productId + "' not found in the catalogue.");
            }
        } while (true);

        if (!tickets.isEmpty()) {
            tickets.forEach(ticketManager::addTicket);
            System.out.println("Ticket created successfully:");
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
                ticketManager.guardarTicket(shop.getTicketDbName());
            }
        } else {
            System.out.println("No products added to the ticket.");
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

    private static String getValidInput(String pattern, String errorMessage, Scanner scanner) {
        String input;
        do {
            input = scanner.nextLine().trim();
            if (!input.matches(pattern) || input.isEmpty()) {
                System.out.println(errorMessage);
            }
        } while (!input.matches(pattern) || input.isEmpty());
        return input;
    }

    private static double getValidDoubleInput(String errorMessage, Scanner scanner) {
        double input = 0;
        while (true) {
            String userInput = scanner.nextLine().trim();
            try {
                input = Double.parseDouble(userInput);
                break;
            } catch (NumberFormatException ex) {
                System.out.println(errorMessage);
            }
        }
        return input;
    }

    private static int getValidIntInput(String errorMessage, Scanner scanner) {
        int input = 0;
        while (true) {
            String userInput = scanner.nextLine().trim();
            try {
                input = Integer.parseInt(userInput);
                break;
            } catch (NumberFormatException ex) {
                System.out.println(errorMessage);
            }
        }
        return input;
    }


    private static char getValidCharInput(Scanner scanner) {
        char choice;
        do {
            choice = scanner.next().charAt(0);
            if (Character.isLetter(choice)) {
                choice = Character.toLowerCase(choice);
            } else {
                System.out.println("Invalid choice. Please enter 'Y' or 'N'.");
            }
        } while (choice != 'y' && choice != 'n');
        return choice;
    }
}




