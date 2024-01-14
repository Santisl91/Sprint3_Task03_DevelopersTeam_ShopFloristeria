package interfaces;

import java.io.IOException;

public interface Ipersistence {
    void guardarCatalogo(String catalogoFileName) throws IOException;
    void guardarStock(String stockFileName) throws IOException;
    void guardarTicket(String ticketFileName) throws IOException;

    void leerCatalogo(String catalogoFileName) throws IOException;
    void leerStock(String stockFileName) throws IOException;
    void leerTicket(String ticketFileName) throws IOException;
}
