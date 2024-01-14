package app;

public class Shop {
    private String name;
    private String catalogueDbName;
    private String stockDbName;
    private String ticketDbName;

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
}
