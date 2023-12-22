package persistence;

import interfaces.IConexion;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConexionFichero implements IConexion {
	private String camino;
	private String nombre;

	public String getCamino() {
		return camino;
	}

	public void setCamino(String camino) {
		this.camino = camino;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ConexionFichero() {
		// TODO Auto-generated constructor stub

	}

	public JsonNode leer() {
		JsonNode jsonNode = null;
		if (this.nombre != null) {
			ObjectMapper objectMapper = new ObjectMapper();

			try {
				jsonNode = objectMapper.readTree(new File(nombre));

				System.out.println("Contenido JSON completo:");
				System.out.println(jsonNode.toPrettyString());
			} catch (IOException e) {
				e.printStackTrace();

				throw new RuntimeException("Error al leer el fichero ", e);
			}
		}
		return jsonNode;
	}
	public void write(JSONObject jsonObj) {
		try (FileWriter fileWriter = new FileWriter(this.nombre)) {
			fileWriter.write(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	@Override
	public void conectar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void desconectar() {
		// TODO Auto-generated method stub

	}

}
