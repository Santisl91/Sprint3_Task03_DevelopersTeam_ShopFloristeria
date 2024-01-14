package persistence;

import interfaces.IConexion;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConexionFichero implements IConexion {

	private String camino;
	private String nombre;

	public ConexionFichero() {
	}

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
			fileWriter.write(System.lineSeparator());  // Agregar un salto de línea
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void conectar() {
		System.out.println("Conectando a fichero " + this.nombre);
	}

	@Override
	public void desconectar() {
		System.out.println("Desconectando de fichero " + this.nombre);
	}

}
