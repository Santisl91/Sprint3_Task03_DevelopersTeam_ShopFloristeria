package persistence;

import interfaces.IConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection implements IConexion {

    private String url;
    private String usuario;
    private String contrasena;

    public MySQLConnection(String url, String usuario, String contrasena) {
        this.url = url;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    @Override
    public void conectar() {
        try {
            Connection connection = DriverManager.getConnection(url, usuario, contrasena);
            // Puedes almacenar la conexión en algún lugar para su posterior uso
            System.out.println("Connected to MySQL database.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    @Override
    public void desconectar() {
        // Puedes cerrar la conexión aquí si la has almacenado
        System.out.println("Disconnected from MySQL database.");
    }
}
