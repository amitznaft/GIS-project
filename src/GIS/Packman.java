package GIS;

import Geom.Point3D;

public class Packman {
	
	private Point3D gps ;
	private int radius;
	private int speed;
	private Path path = new Path();
	private double time = 0;
	private boolean b;
	
	public boolean isB() {
		return b;
	}
	public void setB(boolean b) {
		this.b = b;
	}
	public Packman(Point3D gps,int speed,int radius) {
		this.gps = gps;
		this.radius = radius;
		this.speed = speed;
	}
	public Packman(Packman a) {
		this.gps = a.gps;
		this.radius = a.radius;
		this.speed = a.speed;
	}
	
	public Point3D getGps() {
		return gps;
	}

	public int getRadius() {
		return radius;
	}

	public int getSpeed() {
		return speed;
	}
	public void setGps(Point3D gps) {
		this.gps = gps;
	}
	public void addP (Point3D p) {
		this.path.addF(p);
	}
	public Path getPath () {
		return this.path;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public double getTime() {
		return this.time;
	}
	public void setPath (Path p) {
		this.path = p;
	}
	

	
	


}
