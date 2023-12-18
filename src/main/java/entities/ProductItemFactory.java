package entities;

import java.util.Scanner;

public class ProductItemFactory {
    public static Product createCatalogItem(int itemType, Catalogue catalog) {
        Scanner scanner = new Scanner(System.in);
        do {

            switch (itemType) {
                case 1:
                    System.out.println("Ingrese el nombre del árbol:");
                    String treeName = scanner.nextLine();
                    System.out.println("Ingrese la especie del árbol:");
                    String treeSpecies = scanner.nextLine();
                    return new Tree(treeName, treeSpecies);

                case 2:
                    System.out.println("Ingrese el nombre de la flor:");
                    String flowerName = scanner.nextLine();
                    System.out.println("Ingrese el color de la flor:");
                    String flowerColor = scanner.nextLine();
                    return new Flower(flowerName, flowerColor);

                case 3:
                    System.out.println("Ingrese el nombre de la decoración:");
                    String decorationName = scanner.nextLine();
                    System.out.println("Ingrese el material de la decoración:");
                    String decorationMaterial = scanner.nextLine();
                    return new Decoration(decorationName, decorationMaterial);

                case 0:
                    System.out.println("Saliendo del programa.");
                    System.out.println("Imprimiendo catálogo:");
                    catalog.displayCatalog();
                    return null;

                default:
                    System.out.println("Opción no válida");
                    return null;
            }
        } while (true);
    }
}

