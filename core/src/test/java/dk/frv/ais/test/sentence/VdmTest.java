package dk.frv.ais.test.sentence;

import junit.framework.Assert;

import org.junit.Test;

import dk.frv.ais.binary.SixbitException;
import dk.frv.ais.message.AisMessage;
import dk.frv.ais.message.AisMessage12;
import dk.frv.ais.message.AisMessageException;
import dk.frv.ais.sentence.SentenceException;
import dk.frv.ais.sentence.Vdm;

public class VdmTest {
	
	@Test
	public void msgidTest() {
		Vdm vdm = new Vdm();
		int result = -1;
		try {
			result = vdm.parse("!AIVDM,1,1,,B,19NS7Sp02wo?HETKA2K6mUM20<L=,0*27\r\n");
			Assert.assertEquals("Adding message 1 failed", 0, result);
			Assert.assertEquals("Message ID wrong", 1, vdm.getMsgId());			
			result = vdm.parse("JunkInFront!AIVDM,1,1,,B,19NS7Sp02wo?HETKA2K6mUM20<L=,0*27\r\n");
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			return;
		}		
	}
	
	@Test
	public void multipleTest() {
		Vdm vdm = new Vdm();
		int result = -1;
		try {
			result = vdm.parse("!AIVDM,1,1,,B,19NS7Sp02wo?HETKA2K6mUM20<L=,0*27\r\n");
			Assert.assertEquals("Adding message 1 failed", 0, result);
			vdm = new Vdm();
			result = vdm.parse("!AIVDM,2,1,6,B,55ArUT02:nkG<I8GB20nuJ0p5HTu>0hT9860TV16000006420BDi@E53,0*33\r\n");
			Assert.assertEquals("Adding message 5 part 1 failed", 1, result);			
			result = vdm.parse("!AIVDM,2,2,6,B,1KUDhH888888880,2*6A");
			Assert.assertEquals("Adding message 5 part 1 failed", 0, result);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void parseTest() throws SentenceException, SixbitException, AisMessageException {
		String sentence = "!AIVDM,1,1,,,<>j?1GhlFfBPD5CDP6B?=P6BF,0*42";
		Vdm vdm = new Vdm();
		int result = vdm.parse(sentence);
		Assert.assertEquals("Adding message 12 failed", 0, result);
		Assert.assertEquals("Message ID wrong", 12, vdm.getMsgId());
		AisMessage12 msg = (AisMessage12)AisMessage.getInstance(vdm);
		System.out.println(msg);
		Assert.assertEquals("Wrong destination", 219593000, msg.getDestination());
		Assert.assertEquals("Wrong user id", 992199007, msg.getUserId());
		
		
		
		
	}
	
}
