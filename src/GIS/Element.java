package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class Element implements GIS_element {

	private Data a ;
	private Point3D b  ;
	
	public Element (String [] c ) {
		double lat = Double.parseDouble(c[6]);
		double lon = Double.parseDouble(c[7]);
		double alt = Double.parseDouble(c[8]);
		b = new Point3D(lat,lon,alt);
		a = new Data(c);
		
	}
	public Geom_element getGeom() {
		return b;
	}
	
	public Meta_data getData() {
		return a;
	}
	
	public void translate(Point3D vec) {
		MyCoords c = new MyCoords();
		Point3D n = c.add(b, vec);
		b = new Point3D(n);	
	}
	
}
