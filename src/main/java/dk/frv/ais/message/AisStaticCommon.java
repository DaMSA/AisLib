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
package dk.frv.ais.message;

import dk.frv.ais.sentence.Vdm;

/**
 * Abstract base class for static AIS messages 5 and 24 
 */
public abstract class AisStaticCommon extends AisMessage {
	
	/**
	 * Call sign:
	 * 7 = 6 bit ASCII characters, @@@@@@@ = not available = default
	 */
	protected String callsign; // 7x6 (42) bits
	
	/**
	 * Ship name:
	 * Maximum 20 characters 6 bit ASCII, as defined in Table 44
	 * @@@@@@@@@@@@@@@@@@@@ = not available = default. 
	 * For SAR aircraft, it should be set to "SAR AIRCRAFT NNNNNNN" 
	 * where NNNNNNN equals the aircraft registration number
	 */
	protected String name; // 20x6 (120) bits
	
	/**
	 * Type of ship and cargo type:
	 * 0 = not available or no ship = default
	 * 1-99 = as defined in � 3.3.2
	 * 100-199 = reserved, for regional use
	 * 200-255 = reserved, for future use
	 * Not applicable to SAR aircraft
	 */
	protected int shipType; // 8 bits 
	
	/**
	 * GPS Ant. Distance from bow (A):
	 * Reference point for reported position.
	 * Also indicates the dimension of ship (m) (see Fig. 42 and § 3.3.3)
	 * 
	 * NOTE: When GPS position is not available, but the ships dimensions is
	 * available, then this field should be 0
	 */
	protected int dimBow; // 9 bits
	
	/**
	 * GPS Ant. Distance from stern (B)
	 * Reference point for reported position.
	 * Also indicates the dimension of ship (m) (see Fig. 42 and § 3.3.3)
	 * 
	 * NOTE: When GPS position is not available, but the ships dimensions is
	 * available, then this field should be representing the length of the ship
	 */
	protected int dimStern; // 9 bits
	
	/**
	 * GPS Ant. Distance from port (C)
	 * Reference point for reported position.
	 * Also indicates the dimension of ship (m) (see Fig. 42 and § 3.3.3)
	 *  
	 * NOTE: When GPS position is not available, but the ships dimensions is
	 * available, then this field should be 0
	 */
	protected int dimPort; // 6 bits
	
	/**
	 * GPS Ant. Distance from starboard (D):
	 * Reference point for reported position.
	 * Also indicates the dimension of ship (m) (see Fig. 42 and § 3.3.3)  
	 * 
	 * NOTE: When GPS position is not available, but the ships dimensions is
	 * available, then this field should be representing the with of the ship
	 */
	protected int dimStarboard; // 6 bits

	public AisStaticCommon(int msgId) {
		super(msgId);
	}
	
	public AisStaticCommon(Vdm vdm) {
		super(vdm);
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getShipType() {
		return shipType;
	}

	public void setShipType(int shipType) {
		this.shipType = shipType;
	}

	public int getDimBow() {
		return dimBow;
	}

	public void setDimBow(int dimBow) {
		this.dimBow = dimBow;
	}

	public int getDimStern() {
		return dimStern;
	}

	public void setDimStern(int dimStern) {
		this.dimStern = dimStern;
	}

	public int getDimPort() {
		return dimPort;
	}

	public void setDimPort(int dimPort) {
		this.dimPort = dimPort;
	}

	public int getDimStarboard() {
		return dimStarboard;
	}

	public void setDimStarboard(int dimStarboard) {
		this.dimStarboard = dimStarboard;
	}

}
