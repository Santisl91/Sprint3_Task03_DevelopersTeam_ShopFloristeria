package menu;

import interfaces.IConexion;
import persistence.FileConnection;
import persistence.MySQLConnection;

public class FactoryDb {

	public static IConexion getConexionBD(String tipo) {
		switch (tipo) {
			case "TXT": {
				return new FileConnection();
			}

			case "MySQL": {
				return new MySQLConnection("jdbc:mysql://localhost:3306/tu_basedatos", "mysql -u root -p ", "=(Santiasco)=1991");
			}

			/*case "MongoDB": {
				return new MongoDBConnection("mongodb://localhost:27017");
			}*/

			default: {
				throw new IllegalArgumentException("Unexpected value: " + tipo);
			}
		}
	}
}