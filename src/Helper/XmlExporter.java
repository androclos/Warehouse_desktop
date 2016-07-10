package Helper;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.util.Map;
import javax.xml.transform.OutputKeys;



/**
 * Metodusokat tartalmaz xml exportalashoz
 * @author Pifko
 */
public class XmlExporter {
    
    /**
     * Segedosztaly: Termeket ir ki xml formatumban
     * @param products
     * @param userfullname
     */
    public static void ImportProducts(Map<Integer,ProductContainer> products, String userfullname){
    
        
        try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.newDocument();
                
		// root elements
		
		Element rootElement = doc.createElement("Products");
		doc.appendChild(rootElement);
                

                
                for (Map.Entry<Integer, ProductContainer> entry : products.entrySet()) {
                
                    ProductContainer temp = entry.getValue();
                    
                    Element Product = doc.createElement("Product");
                    rootElement.appendChild(Product);
                 
                    Element name = doc.createElement("Name");
                    name.appendChild(doc.createTextNode(temp.getName()));
                    Product.appendChild(name);
                    
                    Element type = doc.createElement("Type");
                    type.appendChild(doc.createTextNode(temp.getType()));
                    Product.appendChild(type);
                    
                    Element amount = doc.createElement("Amount");
                    amount.appendChild(doc.createTextNode(String.valueOf(temp.getAmount())));
                    Product.appendChild(amount);
                    
                    Element comment = doc.createElement("Comment");
                    comment.appendChild(doc.createTextNode(temp.getComment()));
                    Product.appendChild(comment);
                

                }

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		DOMSource source = new DOMSource(doc);
                String filename = userfullname + "-ProductList.xml";
		StreamResult result = new StreamResult(new File(filename));
 
 
		transformer.transform(source, result);
 

 
	  } catch (ParserConfigurationException pce) {
		
	  } catch (TransformerException tfe) {
		
	  }
    
    
    
    }
    
    /**
     *cucc
     * @param users
     */
    public static void ImportUsers(Map<Integer,UserContainer> users){
    
        
        try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.newDocument();
                
		// root elements
		
		Element rootElement = doc.createElement("Users");
		doc.appendChild(rootElement);
                

                for (Map.Entry<Integer, UserContainer> entry : users.entrySet()) {
                
                    UserContainer temp = entry.getValue();
                    
                    Element User = doc.createElement("User");
                    rootElement.appendChild(User);
                 
                    Element name = doc.createElement("Name");
                    name.appendChild(doc.createTextNode(temp.getName()));
                    User.appendChild(name);
                    
                    Element username = doc.createElement("UserName");
                    username.appendChild(doc.createTextNode(temp.getUsername()));
                    User.appendChild(username);
                    
                    Element email = doc.createElement("Email");
                    email.appendChild(doc.createTextNode(String.valueOf(temp.getEmail())));
                    User.appendChild(email);
                    
                    Element bday = doc.createElement("BirthDate");
                    bday.appendChild(doc.createTextNode(temp.getBday()));
                    User.appendChild(bday);
                
                    Element role = doc.createElement("Role");
                    role.appendChild(doc.createTextNode(temp.getRole()));
                    User.appendChild(role);

                }

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
		DOMSource source = new DOMSource(doc);
                String filename = "UsersList.xml";
		StreamResult result = new StreamResult(new File(filename));
 
 
		transformer.transform(source, result);
 

 
	  } catch (ParserConfigurationException pce) {
		
	  } catch (TransformerException tfe) {
		
	  }
    
    
    
    }
    
}
