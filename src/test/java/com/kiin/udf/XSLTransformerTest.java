package com.kiin.udf;

import java.io.IOException;

import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;

public class XSLTransformerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		XSLTranformer parser = new XSLTranformer();
		String xml = "<ns:instance> <ns:alert type=\"Array\"> <ns:alert type=\"Structure\"> <ns:description type=\"String\">Interface PO4/6 Index:52 Up on node 6ntp-core01.</ns:description> <ns:impact type=\"String\">4</ns:impact> <ns:resourceName type=\"String\">6ntp-core01</ns:resourceName> <ns:interfaceName type=\"String\">PO4/6</ns:interfaceName> <ns:resourceType type=\"String\">PORT</ns:resourceType> <ns:eventType type=\"String\">InterfaceDown</ns:eventType> <ns:eventFamily type=\"String\"/> <ns:siaFlag type=\"Boolean\">true</ns:siaFlag> <ns:severity type=\"String\">Major</ns:severity> <ns:eventTime type=\"DateTime\">2013-12-31T00:58:25.000014</ns:eventTime> <ns:eventId type=\"String\">79475468091</ns:eventId> <ns:flappingCount type=\"Int\">0</ns:flappingCount> </ns:alert></ns:alert> </ns:instance>";
		TupleFactory tf = TupleFactory.getInstance();
		Tuple t = tf.newTuple();
		t.append(xml);
		try {
		Tuple output = parser.exec(t);
		System.out.println(output);
		} catch (IOException e) {
		e.printStackTrace();
		}

	}

}
