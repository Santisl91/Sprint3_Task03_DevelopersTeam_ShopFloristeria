package app;

import java.io.FileWriter;
import java.io.IOException;

public class Shop {
    private String name;
    private String catalogueDbName;
    private String stockDbName;
    private String ticketDbName;

    public Shop(){

    }
    public Shop(String name) {
        this.name = name;
        this.catalogueDbName = name + "_Catalogue.txt";
        this.stockDbName = name + "_Stock.txt";
        this.ticketDbName = name + "_Ticket.txt";
    }

    public Shop(String name, String catalogueDbName, String stockDbName, String ticketDbName) {
        this.name = name;
        this.catalogueDbName = catalogueDbName;
        this.stockDbName = stockDbName;
        this.ticketDbName = ticketDbName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalogueDbName() {
        return catalogueDbName;
    }

    public void setCatalogueDbName(String catalogueDbName) {
        this.catalogueDbName = catalogueDbName;
    }

    public String getStockDbName() {
        return stockDbName;
    }

    public void setStockDbName(String stockDbName) {
        this.stockDbName = stockDbName;
    }

    public String getTicketDbName() {
        return ticketDbName;
    }

    public void setTicketDbName(String ticketDbName) {
        this.ticketDbName = ticketDbName;
    }
    public void crearArchivosEnBlanco() {
        try {
            // Crear archivos en blanco
            FileWriter catalogoWriter = new FileWriter(catalogueDbName);
            catalogoWriter.close();

            FileWriter stockWriter = new FileWriter(stockDbName);
            stockWriter.close();

            FileWriter ticketWriter = new FileWriter(ticketDbName);
            ticketWriter.close();

            System.out.println("Archivos en blanco creados para la tienda '" + name + "'.");
        } catch (IOException e) {
            System.out.println("Error al crear archivos en blanco: " + e.getMessage());
        }
    }
}
