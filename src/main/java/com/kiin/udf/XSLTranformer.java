/**
 * 
 */
package com.kiin.udf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;

/**
 * @author Kailash
 * Simple XML parser UDF for PiG. 
 * Target pig version is .12 (PHD 2.1)
 */
public class XSLTranformer extends EvalFunc<Tuple> {

	TransformerFactory factory = null;
	public XSLTranformer()
	{
		factory = TransformerFactory.newInstance();
	}
	@Override
	public Tuple exec(Tuple input) throws IOException {
		// TODO Auto-generated method stub
		String textValue = null;
		String titleValue = null;
		TupleFactory tf = TupleFactory.getInstance();
		if (input == null || input.size() == 0)
			return null;
		
		try {
			// Pig will pass in a tuple with a single string value of the <page>...</page> content.
			String xml = (String) input.get(0);
			String xmlQualifier = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			String nameSpaceForReplacing = "<ns:instance xmlns:ns=\"http://www.w3.org/1999/XSL/Transform/Coo\">";
			String xmlWithNamespace = xml.replaceAll("<ns:instance>", nameSpaceForReplacing);
			InputStream in = getClass().getResourceAsStream(ConfigUtil.getPropertyByName("xslfilepath")); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			Source xsl = new StreamSource(reader);
	        Transformer transformer = factory.newTransformer(xsl);
	        Source xmlSource = new StreamSource(new StringReader(xmlQualifier+xmlWithNamespace));
	       
	        StringWriter xmlOutWriter = new StringWriter();
	        transformer.transform(xmlSource, new StreamResult(xmlOutWriter));
	        
			Tuple t = tf.newTuple();
			
			t.append(xmlOutWriter.toString());
		
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		return tf.newTuple();
		}
		
	}
}
