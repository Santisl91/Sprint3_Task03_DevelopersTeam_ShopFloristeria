package persistence;

import interfaces.IConexion;

public class FactoryDb {

	public static IConexion getConexionBD(String tipo) {
		switch (tipo) {
			case "TXT": {
				return new FileConnection();
			}

			default: {
				throw new IllegalArgumentException("Unexpected value: " + tipo);
			}

		}
	}
}
