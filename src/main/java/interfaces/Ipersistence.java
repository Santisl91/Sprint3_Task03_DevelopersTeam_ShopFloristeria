
package interfaces;

import java.io.IOException;

public interface Ipersistence {
	public void guardarBd() throws IOException;
	public void leerBd() throws IOException;
	
}