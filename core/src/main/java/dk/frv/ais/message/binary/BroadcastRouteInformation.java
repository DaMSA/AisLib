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
package dk.frv.ais.message.binary;

import dk.frv.ais.binary.BinArray;
import dk.frv.ais.binary.SixbitException;

public class BroadcastRouteInformation extends RouteInformation {
	
	public BroadcastRouteInformation() {
		super(27);
	}
	
	public BroadcastRouteInformation(BinArray binArray) throws SixbitException {
		super(27, binArray);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BroadcastRouteInformation [");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
