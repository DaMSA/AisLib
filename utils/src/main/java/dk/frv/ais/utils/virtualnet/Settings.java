/* Copyright (c) 2011 Danish Maritime Safety Administration
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 3 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this library.  If not, see <http://www.gnu.org/licenses/>.
*/
package dk.frv.ais.utils.virtualnet;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Settings class to load settings and setup project entities
 */
public class Settings {

	private static final Logger LOG = Logger.getLogger(Settings.class);

	private Properties props;

	public Settings() {
	}
	
	public void load(String filename) throws IOException {
		props = new Properties();
		URL url = ClassLoader.getSystemResource(filename);
		if (url == null) {
			throw new IOException("Could not find properties file: " + filename);
		}
		props.load(url.openStream());
		
		// TODO the rest
		
	}
	
	

}
