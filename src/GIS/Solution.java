package GIS;

import java.util.ArrayList;
import java.util.Iterator;

public class Solution {
	
	private ArrayList<Path> paths = new ArrayList<Path>();
	
	public void addPath(Path a) {
		paths.add(a);
	}
	public Iterator<Path> itPath(){
		return paths.iterator();
	}
	public ArrayList<Path> getAllpaths(){
		return this.paths;
	}


}
