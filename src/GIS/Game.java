package GIS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import Geom.Point3D;

public class Game {

	private ArrayList<Packman> pack = new ArrayList<Packman>();
	private ArrayList<Fruit> fruit = new ArrayList<Fruit>();
	private String line = "";
	private String cvsSplitBy = ",";
	private int i=0; 

	public Game(Game g) {
		this.pack = g.getAllPackman();
		this.fruit = g.getAllfruit();
	}
	public Game (String csvFile ) {
		File file = new File(csvFile);
		try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))) 
		{
			while ((line = br.readLine()) != null ) 
			{
				String[] userInfo = line.split(cvsSplitBy);
				if (i!=0 ) {
					if (userInfo[0].equals("P")) {
						double lat = Double.parseDouble(userInfo[2]);
						double lon = Double.parseDouble(userInfo[3]);
						double alt = Double.parseDouble(userInfo[4]);
						int speed = Integer.parseInt(userInfo[5]);
						int radius = Integer.parseInt(userInfo[6]);
						Point3D point = new Point3D(lat,lon,alt);
						Packman p = new Packman(point,speed,radius);
						pack.add(p);
					}
					else if (userInfo[0].equals("F")) {
						double lat = Double.parseDouble(userInfo[2]);
						double lon = Double.parseDouble(userInfo[3]);
						double alt = Double.parseDouble(userInfo[4]);
						Point3D point = new Point3D(lat,lon,alt);
						Fruit f = new Fruit (point);
						fruit.add(f);
					}
				}
				i++;
			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	public Game () {	
	}

	public void addPack (Packman e) {
		pack.add(e);
	}

	public void addFruit (Fruit e) {
		fruit.add(e);
	}

	public Iterator<Packman> iteratorpack() {
		return this.pack.iterator();
	}

	public Iterator<Fruit> iteratorfruit() {
		return this.fruit.iterator();
	}
	public void clear(){
		this.pack.clear();
		this.fruit.clear();
	}
	public void save (Game game,String path) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(new FileOutputStream(path+".csv"));
		StringBuilder sb = new StringBuilder();
		int i =0;
		sb.append("Type");
		sb.append(',');
		sb.append("id");
		sb.append(',');
		sb.append("Lat");
		sb.append(',');
		sb.append("Lon");
		sb.append(',');
		sb.append("Alt");
		sb.append(',');
		sb.append("Speed/Weight");
		sb.append(',');
		sb.append("Radius");
		sb.append('\n');
		Iterator<Fruit> it1 = game.iteratorfruit();
		Iterator<Packman> it2 = game.iteratorpack();
		while (it2.hasNext()) {
			Packman a = (Packman) it2.next();
			sb.append("P");
			sb.append(',');
			sb.append(i);
			sb.append(',');
			sb.append(a.getGps().x());
			sb.append(',');
			sb.append(a.getGps().y());
			sb.append(',');
			sb.append(a.getGps().z());
			sb.append(',');
			sb.append(a.getSpeed());
			sb.append(',');
			sb.append(a.getRadius());
			sb.append('\n');
			i++;	
		}
		while(it1.hasNext()) {
			Fruit a = (Fruit) it1.next();
			sb.append("F");
			sb.append(',');
			sb.append(i);
			sb.append(',');
			sb.append(a.getGps().x());
			sb.append(',');
			sb.append(a.getGps().y());
			sb.append(',');
			sb.append(a.getGps().z());
			sb.append(',');
			sb.append(1);
			sb.append('\n');
			i++;

		}
		writer.write(sb.toString());
		writer.close();
	}
	
	public ArrayList<Fruit> getAllfruit (){
		return this.fruit;
	}
	public ArrayList<Packman> getAllPackman (){
		return this.pack;
	}
}
