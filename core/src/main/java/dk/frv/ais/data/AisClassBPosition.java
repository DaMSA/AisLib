/* Copyright (c) 2012 Danish Maritime Authority
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
package dk.frv.ais.data;

import dk.frv.ais.message.AisMessage18;
import dk.frv.ais.message.IGeneralPositionMessage;

/**
 * Class to represent a class B postion
 */
public class AisClassBPosition extends AisVesselPosition {
	
	public AisClassBPosition() {
		super();
	}
	
	public AisClassBPosition(AisMessage18 msg18) {
		super();
		update(msg18);
	}
	
	/**
	 * Update data object with data from AIS message
	 * @param msg18
	 */
	public void update(AisMessage18 msg18) {
		super.update((IGeneralPositionMessage)msg18);
	}

}
