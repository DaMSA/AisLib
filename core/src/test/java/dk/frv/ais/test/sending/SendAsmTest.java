package dk.frv.ais.test.sending;

import org.junit.Assert;
import org.junit.Test;

import dk.frv.ais.message.AisMessage6;
import dk.frv.ais.message.binary.RouteSuggestionReply;
import dk.frv.ais.reader.AisReader;
import dk.frv.ais.reader.AisReader.Status;
import dk.frv.ais.reader.AisTcpReader;
import dk.frv.ais.reader.SendException;
import dk.frv.ais.sentence.Abk;

public class SendAsmTest {
	
	@Test
	public void emptyTest() {
		
	}

	//@Test
	public void sendRouteSuggestionReply() throws InterruptedException, SendException {
		String hostPort = "aistrans1.fomfrv.dk:4001";
		// int destination = 219015063; // DAMSA1
		//int destination = 992199007; // ais-frv-obo
		int destination = 992199100; // SOK

		// Make AisReader instance and start
		AisReader aisReader = new AisTcpReader(hostPort);
		aisReader.start();
		Thread.sleep(3000);
		if (aisReader.getStatus() != Status.CONNECTED) {
			Assert.fail("Could not connect to AIS source within 3 secs");
		}

		// Make ASM
		RouteSuggestionReply routeSuggestionReply = new RouteSuggestionReply();
		routeSuggestionReply.setRefMsgLinkId(1);
		routeSuggestionReply.setResponse(2);

		// Make ais message 6
		AisMessage6 msg6 = new AisMessage6();
		msg6.setDestination(destination);
		msg6.setAppMessage(routeSuggestionReply);
		msg6.setRetransmit(0);

		// Send
		Abk abk = aisReader.send(msg6, 1, destination);
		// We are now guaranteed to have ABK
		System.out.println("ABK: " + abk);
		Assert.assertTrue(abk.isSuccess());
		aisReader.stopReader();
	}

}
