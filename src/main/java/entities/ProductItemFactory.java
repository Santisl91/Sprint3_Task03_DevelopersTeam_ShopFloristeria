package entities;

import app.Shop;
import entities.Stock;
import java.util.Scanner;

public class ProductItemFactory {
    public static Product createCatalogItem(int itemType, Catalogue catalog, Shop shop) {
        Scanner scanner = new Scanner(System.in);

        switch (itemType) {
            case 1:
                System.out.println("Ingrese el nombre del árbol:");
                String treeName = scanner.nextLine();
                System.out.println("Ingrese la altura del árbol:");
                String treeHeigth = scanner.nextLine();
                System.out.println("Ingrese el precio del árbol:");
                double treePrice = scanner.nextDouble();
                Tree tree = new Tree(treeName, treeHeigth, treePrice);
                catalog.addItem(tree);
                Stock stockTree = shop.encontrarStock(tree);
                if (stockTree != null) {
                    stockTree.addStockItem(tree, 0); // 0 es la cantidad inicial
                } else {
                    System.out.println("Error: Stock no encontrado para el árbol.");
                }

                return tree;

            case 2:
                System.out.println("Ingrese el nombre de la flor:");
                String flowerName = scanner.nextLine();
                System.out.println("Ingrese el color de la flor:");
                String flowerColor = scanner.nextLine();
                System.out.println("Ingrese el precio de la flor:");
                double flowerPrice = scanner.nextDouble();
                Flower flower = new Flower(flowerName, flowerColor, flowerPrice);
                catalog.addItem(flower);
                Stock stockFlower = shop.encontrarStock(flower);
                if (stockFlower != null) {
                    stockFlower.addStockItem(flower, 0); // 0 es la cantidad inicial
                } else {
                    System.out.println("Error: Stock no encontrado para el árbol.");
                }

                return flower;

            case 3:
                System.out.println("Ingrese el nombre de la decoración:");
                String decorationName = scanner.nextLine();
                System.out.println("Ingrese el material de la decoración:");
                String decorationMaterial = scanner.nextLine();
                System.out.println("Ingrese el precio de la decoración:");
                double decoPrice = scanner.nextDouble();
                Decoration decoration = new Decoration(decorationName, decorationMaterial, decoPrice);
                catalog.addItem(decoration);
                Stock stockDeco = shop.encontrarStock(decoration);
                if (stockDeco != null) {
                    stockDeco.addStockItem(decoration, 0); // 0 es la cantidad inicial
                } else {
                    System.out.println("Error: Stock no encontrado para el árbol.");
                }

                return decoration;

            case 4:
                System.out.println("Ingrese el nombre del árbol a retirar:");
                String treeToRemove = scanner.nextLine();
                catalog.removeProductByName(treeToRemove);
                return null;

            case 5:
                System.out.println("Ingrese el nombre de la flor a retirar:");
                String flowerToRemove = scanner.nextLine();
                catalog.removeProductByName(flowerToRemove);
                return null;

            case 6:
                System.out.println("Ingrese el nombre de la decoración a retirar:");
                String decorationToRemove = scanner.nextLine();
                catalog.removeProductByName(decorationToRemove);
                return null;

            case 7:
                // Ver todos los tickets
                for (Ticket ticket : shop.getTickets()) {
                    System.out.println(ticket);
                }
                return null;

            case 8:
                // Ver stock con cantidades de productos
                for (Stock stock : shop.getStockProducts()) {
                    System.out.println("Stock ID: " + stock.getId());
                    for (StockItem item : stock.getItems()) {
                        System.out.println("   - Producto: " + item.getProduct().getName() + ", Cantidad: " + item.getQuantity());
                    }
                }
                return null;

            case 9:
                // Crear floristería
                System.out.println("Ya se creó la floristería.");
                return null;

            case 0:
                System.out.println("Saliendo del programa.");
                catalog.displayCatalog();
                return null;

            default:
                System.out.println("Opción no válida");
                return null;
        }
    }
}


