package GIS;

import java.util.ArrayList;
import java.util.Iterator;

import Geom.Point3D;

public class Path {

	Packman a;
	private ArrayList<Point3D> path = new ArrayList<Point3D>();
	private ArrayList<Double> times = new ArrayList<Double>();
	private ArrayList<Boolean> bol = new ArrayList<Boolean>();

	public void addF (Point3D e) {
		path.add(e);
	}

	public boolean isEmpty() {
		return path.isEmpty();
	}
	public Iterator<Point3D> iteratorpack() {
		return this.path.iterator();
	}
	public void addTime (double time) {
		this.times.add(time);
	}
	public Iterator<Double> itTime(){
		return this.times.iterator();
	}
	public ArrayList<Point3D> getAllPoints (){
		return this.path;
	}
	public ArrayList<Double> getAllTimes (){
		return this.times;
	}
	public Point3D getfirst() {
		return this.path.get(0);
	}

	public Point3D LestIndex() {
		return path.get(path.size()-1);
	}
	public Point3D nextFruit(Packman pac) {
		boolean [] b = new boolean[path.size()] ;
		for(int i = 0 ; i < path.size() ; i++) {
			if(pac.getGps().equals( path.get(i))) {
				bol.add(true);
			}
		}
		for(int i = 0 ; i<bol.size(); i++) {
			b[i]= bol.get(i);
		}
		
		Point3D pointToReturn = null;
		for(int i = 0; i <b.length; i++) {
			if(b[i] != false) {
				continue;
			}
			pointToReturn = path.get(i);
			break;
		} 
		return pointToReturn;
	}
}
