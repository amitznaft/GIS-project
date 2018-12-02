package GIS;

import Geom.Point3D;

public class DataProject implements Meta_data {

	long time;
	
	public DataProject() {
		time = System.currentTimeMillis();
	}
	public long getUTC() {
		return time;
	}

	public Point3D get_Orientation() {
		return null;
	}
	
	

}
