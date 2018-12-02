package GIS;

import Geom.Point3D;

public class DataLayer implements Meta_data {

	long time;
	
	public DataLayer() {
		time = System.currentTimeMillis();
	}

	public long getUTC() {
		return time;
	}

	public Point3D get_Orientation() {
		return null;
	}
	
	

}
