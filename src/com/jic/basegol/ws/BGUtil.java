package com.jic.basegol.ws;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.util.Base64;

import com.jic.basegol.util.Utiles;

public class BGUtil 
{
	
	 private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
	
	public static String getJSONStringfromNET(String netString)
	{
        DocumentBuilder builder;
        InputSource is;
        String jsonResponse = "";
        try {
            builder = factory.newDocumentBuilder();
            is = new InputSource(new StringReader(netString));
            Document doc = builder.parse(is);
            NodeList list = doc.getElementsByTagName("string");
            jsonResponse = list.item(0).getTextContent();
        } catch (ParserConfigurationException e) {
        } catch (SAXException e) {
        } catch (IOException e) {
        }
        
        return jsonResponse;
	}
	
	
	
	public static String getGot(String r)
	{
		
		String r2  = "NzY2N2hidXlhc2R0aGF0d29mYnV5YXNkYmZneTg3OGNhbnlvd";
		return r2;
		
	}

}
