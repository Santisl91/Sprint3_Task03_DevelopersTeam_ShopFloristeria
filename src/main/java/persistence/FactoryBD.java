package persistence;

import interfaces.IConexion;

public class FactoryBD {

	public static IConexion getConexionBD(String tipo) {
		switch (tipo) {
			case "TXT": {
				return new ConexionFichero();
			}

			default: {
				throw new IllegalArgumentException("Unexpected value: " + tipo);
			}

		}
	}
}
