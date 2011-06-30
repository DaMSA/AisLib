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
package dk.frv.ais.examples.sender;

import org.apache.log4j.Logger;

import dk.frv.ais.message.AisMessage12;
import dk.frv.ais.reader.AisReader;
import dk.frv.ais.reader.AisTcpReader;
import dk.frv.ais.reader.ISendResultListener;
import dk.frv.ais.reader.SendException;
import dk.frv.ais.reader.SendRequest;
import dk.frv.ais.sentence.Abk;

public class SrmSend implements ISendResultListener {
	
	private static final Logger LOG = Logger.getLogger(SrmSend.class);			
	
	private Abk abk = null;
	private Boolean abkReceived = false;
	private AisReader aisReader;
	
	
	private SrmSend(String hostPort) {
		aisReader = new AisTcpReader(hostPort);
		// Start reader thread
		aisReader.start();		
	}

	/**
	 * Receive the ABK result
	 */
	public void sendResult(Abk abk) {
		synchronized (abkReceived) {
			this.abk = abk;
			this.abkReceived = true;
		}
	}
	
    private void sendMessage(int destination, String message) {
    	AisMessage12 msg12 = new AisMessage12();
		msg12.setDestination(destination);
		msg12.setMessage(message);
		
		LOG.info("msg12: " + msg12);    	
    	// Make send request
    	SendRequest sendRequest = new SendRequest(msg12, 1, destination);    	
    	
    	// Wait for connected
		while (aisReader.getStatus() == AisReader.Status.DISCONNECTED) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		LOG.info("Send request");
		try {
			aisReader.send(sendRequest, this);
		} catch (SendException e) {
			e.printStackTrace();
		}
		LOG.info("Busy wait for result");
		
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			synchronized (abkReceived) {
				if (abkReceived) {
					break;
				}
			}
			
		}
		
		LOG.info("ABK received: " + abk);	
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}


	public static void main(String[] args) {
		if (args.length < 3) {
            usage();
            System.exit(1);
        }
		String hostPort = args[0];
		int destination = Integer.parseInt(args[1]);
		String message = args[2];

		SrmSend srmSend = new SrmSend(hostPort);
		srmSend.sendMessage(destination, message);
		
		System.exit(0);		
	}
	
	public static void usage() {
        System.out.println("Usage: SrmSend <host:port> <destination> <message>");

    }

}
