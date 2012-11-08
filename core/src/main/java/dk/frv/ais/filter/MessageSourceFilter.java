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
package dk.frv.ais.filter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dk.frv.ais.country.MidCountry;
import dk.frv.ais.message.AisMessage;
import dk.frv.ais.proprietary.IProprietarySourceTag;

/**
 * Filtering based on the source information attached to message.
 * 
 * @see dk.frv.ais.data.AisTargetSourceData
 */
public class MessageSourceFilter extends GenericFilter {
	
	/**
	 * Initialize allowed filter names
	 */
	public static final Set<String> filterNames;
	static {
		String[] filterNamesStr = {"basestation", "region", "targetCountry", "country"};
		filterNames = new HashSet<String>();
		for (String filterName : filterNamesStr) {
			filterNames.add(filterName);
		}
	}
	
	/**
	 * Map from filter name to set of accepted values
	 */
	private Map<String, HashSet<String>> filter = new HashMap<String, HashSet<String>>();	
	
	public MessageSourceFilter() {
		
	}

	@Override
	public void receive(AisMessage aisMessage) {
		if (isEmpty()) {
			sendMessage(aisMessage);
			return;
		}
		
		// Get tag
		IProprietarySourceTag tag = aisMessage.getSourceTag();
		if (tag == null) {
			return;
		}
		HashMap<String, String> tagMap = new HashMap<String, String>();
		if (tag.getBaseMmsi() != null) {
			tagMap.put("basestation", Long.toString(tag.getBaseMmsi()));
		}
		if (tag.getRegion() != null) {
			tagMap.put("region", tag.getRegion());
		}
		if (tag.getCountry() != null) {
			tagMap.put("country", tag.getCountry().getThreeLetter());
		}
		MidCountry cntr = MidCountry.getCountryForMmsi(aisMessage.getUserId());
		if (cntr != null) {
			tagMap.put("targetCountry", cntr.getThreeLetter());
		}
		
		for (String filterName : filter.keySet()) {
			HashSet<String> values = filter.get(filterName);
			// Get tag value
			String tagValue = tagMap.get(filterName);			
			if (tagValue == null || !values.contains(tagValue)) {
				return;
			}			
		}
		
		
		// Send message
		sendMessage(aisMessage);
	}
	
	public boolean isEmpty() {
		return (filter.size() == 0);
	}
	
	public void addFilterValue(String filterName, String value) {
		if (!filterNames.contains(filterName)) {
			throw new IllegalArgumentException("Unknown filter: " + filterName);
		}
		
		HashSet<String> filterValues = filter.get(filterName);
		if (filterValues == null) {
			filterValues = new HashSet<String>();
			filter.put(filterName, filterValues);
		}		
		filterValues.add(value);		
	}

}
