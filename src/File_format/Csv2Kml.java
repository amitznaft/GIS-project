package File_format;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;

import GIS.Data;
import GIS.GIS_element;
import GIS.GIS_layer;
import Geom.Point3D;

public class Csv2Kml {

	public void toKml(GIS_layer g, String path) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(new FileOutputStream(path));
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		writer.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		writer.println("<Document>");
		writer.println("<Style");
		writer.println("id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style");
		writer.println("id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style");
		writer.println("id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>");
		Iterator<GIS_element> it = g.iterator();
		while (it.hasNext()) {
			GIS_element e = it.next();
			Point3D p = (Point3D) e.getGeom();
			Data d = (Data) e.getData();
			writer.println("<Placemark>");
			writer.println("<name><![CDATA["+d.getName()+"]]></name>" );
			writer.println("<description><![CDATA[BSSID: <b>"+d.getBSSID()+"</b><br/>Capabilities: <b>"+d.getCapabilities()+"</b><br/>Timestamp: <b>"+d.getUTC()+"</b><br/>Date: <b>"+d.getDateInString()+"</b>]]></description><styleUrl>"+d.getColor()+"</styleUrl>");
			writer.println("<Point>");
			writer.println("<coordinates>" + p.y() + "," + p.x() + "," + p.z()+ "</coordinates>");
			writer.println("</Point>");
			writer.println("</Placemark>");
		}
		writer.println("</Folder>");
		writer.println("</Document>");
		writer.println("</kml>");
		writer.close();
		System.out.println("done");
	}

	

}
