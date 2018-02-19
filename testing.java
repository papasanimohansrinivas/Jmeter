import java.io.File;
import java.io.IOException;
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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class testing {

	public static final String xmlFilePath = "C:\\Users\\mohanSrinivas\\Desktop\\current-working-plan-3.jmx";

	public static final String fileHomePath = "\"C:/Users/mohanSrinivas/Desktop/JmeterAPISample/filePaths.json\"";


	public static void traverseNode(Node nodes){
       if(nodes.hasChildNodes()  || nodes.getNodeType()!=3){
           
           if (nodes.getNodeName().equals("stringProp")) {

           		NamedNodeMap attr = nodes.getAttributes();

           		Node n = attr.getNamedItem("name");

           		if (n.getTextContent().equals("Argument.value")) {
           			System.out.println("FOUND");
           			// 
           			nodes.setTextContent(fileHomePath);

           		}

           }
           NodeList nl=nodes.getChildNodes();
           for(int j=0;j<nl.getLength();j++)traverseNode(nl.item(j));
       }
   }

   public static void changeProp() throws Exception{

   		try {

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document document = documentBuilder.parse(xmlFilePath);

			NodeList nodes =  document.getElementsByTagName("Arguments");

			if (nodes.getLength()==1) {

				traverseNode(nodes.item(0));
				
			}
			else
			{
				System.out.println("will implement for multiple Elements later");
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			
			Transformer transformer= transformerFactory.newTransformer();

			DOMSource source = new DOMSource(document);

			StreamResult result=new StreamResult(new File(xmlFilePath));	
			transformer.transform(source, result);


		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}

   }


	public static void main(String argv[]) throws Exception {

		changeProp();

		
	}
}