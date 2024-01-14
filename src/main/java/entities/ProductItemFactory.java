package entities;

import app.Shop;
import java.io.IOException;
import java.util.Scanner;


public class ProductItemFactory {
    public static Object createCatalogItem(int itemType, Catalogue catalog, Shop shop) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            switch (itemType) {
                case 1:
                    System.out.println("Ingrese el nombre del árbol:");
                    String treeName = scanner.nextLine();
                    System.out.println("Ingrese la altura del árbol:");
                    String treeHeight = scanner.nextLine();
                    System.out.println("Ingrese el precio del árbol:");
                    double treePrice = scanner.nextDouble();
                    Tree tree = new Tree(treeName, treeHeight, treePrice);
                    catalog.addItem(tree);
                    catalog.guardarCatalogo(shop.getCatalogueDbName());
                    Stock stockTree = Stock.getInstance(); // Obtener la instancia única de Stock
                    System.out.println("¿Desea agregar stock? (S/N)");
                    char choice1 = scanner.next().charAt(0);
                    if (choice1 == 'S' || choice1 == 's') {
                        System.out.println("Ingrese la cantidad:");
                        int quantity = scanner.nextInt();
                        StockItem stockItem = new StockItem(tree.getId(), quantity);
                        stockTree.addStockItem(stockItem);
                        stockTree.guardarStock(shop.getStockDbName());
                        System.out.println("Stock creado.");
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
                    catalog.guardarCatalogo(shop.getCatalogueDbName());
                    Stock stockFlower = Stock.getInstance(); // Obtener la instancia única de Stock
                    System.out.println("¿Desea agregar stock? (S/N)");
                    char choice2 = scanner.next().charAt(0);
                    if (choice2 == 'S' || choice2 == 's') {
                        System.out.println("Ingrese la cantidad:");
                        int quantity = scanner.nextInt();
                        StockItem stockItem = new StockItem(flower.getId(), quantity);
                        stockFlower.addStockItem(stockItem);
                        stockFlower.guardarStock(shop.getStockDbName());
                        System.out.println("Stock creado.");
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
                    catalog.guardarCatalogo(shop.getCatalogueDbName());
                    Stock stockDeco = Stock.getInstance(); // Obtener la instancia única de Stock
                    System.out.println("¿Desea agregar stock? (S/N)");
                    char choice3 = scanner.next().charAt(0);
                    if (choice3 == 'S' || choice3 == 's') {
                        System.out.println("Ingrese la cantidad:");
                        int quantity = scanner.nextInt();
                        StockItem stockItem = new StockItem(decoration.getId(), quantity);
                        stockDeco.addStockItem(stockItem);
                        stockDeco.guardarStock(shop.getStockDbName());
                        System.out.println("Stock creado.");
                    }
                    return decoration;

                case 4:
                    catalog.displayCatalog();
                    System.out.println("Ingrese el ID del árbol a retirar:");
                    int treeToRemove = scanner.nextInt();
                    catalog.removeProductById(treeToRemove);
                    return null;

                case 5:
                    catalog.displayCatalog();
                    System.out.println("Ingrese el ID de la flor a retirar:");
                    int flowerToRemove = scanner.nextInt();
                    catalog.removeProductById(flowerToRemove);
                    return null;

                case 6:
                    catalog.displayCatalog();
                    System.out.println("Ingrese el ID de la decoración a retirar:");
                    int decorationToRemove = scanner.nextInt();
                    catalog.removeProductById(decorationToRemove);
                    return null;

                case 7:
                    // Ver todos los tickets


                case 8://TODO: Ver stock con cantidades de productos de clase Shop

                case 9:
                    // Crear floristería
                    System.out.println("Ya se creó la floristería.");
                    return null;

                case 10:




                case 0:
                    System.out.println("Saliendo del programa.");
                    catalog.displayCatalog();
                    return null;

                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
}

