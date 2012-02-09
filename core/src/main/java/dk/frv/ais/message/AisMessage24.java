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

import dk.frv.ais.binary.BinArray;
import dk.frv.ais.binary.SixbitEncoder;
import dk.frv.ais.binary.SixbitException;
import dk.frv.ais.sentence.Vdm;

/**
 * AIS message 24
 * 
 * Static data report as defined by ITU-R M.1371-4
 * 
 */
public class AisMessage24 extends AisStaticCommon {

	/**
	 * Identifier for the message part number
	 */
	int partNumber; // 2 bits 0 - Part A 1 - Part B

	/*
	 * Part B
	 */

	/**
	 * Unique identification of the Unit by a number as defined by the
	 * manufacturer (option; “@@@@@@@” = not available = default)
	 */
	long vendorId; // 42 bits

	/**
	 * Spare bits
	 */
	int spare; // 6 bits

	public AisMessage24(Vdm vdm) throws AisMessageException, SixbitException {
		super(vdm);
		parse();
	}

	public void parse() throws AisMessageException, SixbitException {
		BinArray binArray = vdm.getBinArray();
		if (binArray.getLength() > 168)
			throw new AisMessageException("Message 24 wrong length " + binArray.getLength());

		super.parse(binArray);

		this.partNumber = (int) binArray.getVal(2);

		// Handle part A
		if (partNumber == 0) {
			this.name = binArray.getString(20);
			return;
		}

		// Handle part B
		this.shipType = (int) binArray.getVal(8);
		this.vendorId = binArray.getVal(42);
		this.callsign = binArray.getString(7);
		this.dimBow = (int) binArray.getVal(9);
		this.dimStern = (int) binArray.getVal(9);
		this.dimPort = (int) binArray.getVal(6);
		this.dimStarboard = (int) binArray.getVal(6);
		this.spare = (int) binArray.getVal(6);
	}

	@Override
	public SixbitEncoder getEncoded() {
		// TODO
		return null;
	}

	public int getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public int getSpare() {
		return spare;
	}

	public void setSpare(int spare) {
		this.spare = spare;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(", partNumber=");
		builder.append(partNumber);
		builder.append(", callsign=");
		builder.append(callsign);
		builder.append(", dimBow=");
		builder.append(dimBow);
		builder.append(", dimPort=");
		builder.append(dimPort);
		builder.append(", dimStarboard=");
		builder.append(dimStarboard);
		builder.append(", dimStern=");
		builder.append(dimStern);
		builder.append(", name=");
		builder.append(name);
		builder.append(", shipType=");
		builder.append(shipType);
		builder.append(", spare=");
		builder.append(spare);
		builder.append("]");
		return builder.toString();
	}

}
