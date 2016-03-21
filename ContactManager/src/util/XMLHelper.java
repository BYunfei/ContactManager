package util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import entity.Items;

public class XMLHelper {

	public static List<Element> getListFromXML(String lable,File file){
		try{
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build(file);
			Element rootElement = doc.getRootElement();
			List<Element> elementList = rootElement.getChildren(lable);
			return elementList;
		}catch(JDOMException saxe){
			saxe.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return null;
	}

	public static boolean save(Document doc,File file){
		return false;
	}

}
