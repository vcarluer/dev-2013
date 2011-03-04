package gamers.associate.proto;

import java.io.File;
import java.io.IOException;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGRectangle;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGSVGElement;

public class SvgParserTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String parser = XMLResourceDescriptor.getXMLParserClassName();
			SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
			File file = new File("resources/dessin.svg");
			String uri = "file:" + file.getAbsolutePath();
			System.out.println("Opening " + uri);
			SVGDocument svgDoc = f.createSVGDocument(uri);			
			SVGSVGElement elem = svgDoc.getRootElement();
			System.out.println(elem.getNodeName());
			SVGDOMImplementation impl = new  SVGDOMImplementation();			
			/*int count = elem.getChildNodes().getLength();
			int i = 0;
			while (i < count) {
				Node node = elem.getChildNodes().item(i);	
				System.out.println(node.getNodeName());
				i++;
			}	*/
			
			System.out.println("SVG Document parsed");
		}
		catch (IOException ex) {
			System.out.println(ex.getMessage());
		}		

	}

}
