package GIS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Geom.Point3D;

public class Data implements Meta_data {
	private String name;
	private String dateInString;
	private String BSSID;
	private String color;
	private String Capabilities;


	public Data (String [] b ) {
		dateInString = b[3];
		name = b[1];
		BSSID = b[0];
		Capabilities = b[2];
		if (Integer.parseInt(b[5]) >-90) {
			color = "#red";
		}
		else { color = "#green";}
		
	}
	public String getName() {
		return name;
	}

	public String getDateInString() {
		return dateInString;
	}

	public String getBSSID() {
		return BSSID;
	}
	public String getColor() {
		return color;
	}

	public String getCapabilities() {
		return Capabilities;
	}

	public long getUTC() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = null;
		try {
			date = format.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

	public String toString() {
		return "Data [name=" + name + ", dateInString=" + dateInString + ", BSSID=" + BSSID + ", color=" + color
				+ ", Capabilities=" + Capabilities + "]";
	}

	public Point3D get_Orientation() {
		return null;
	}
}
