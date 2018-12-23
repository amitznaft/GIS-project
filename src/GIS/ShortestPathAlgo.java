package GIS;


import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;

public class ShortestPathAlgo {

	ArrayList<Fruit> ff;
	ArrayList<Packman> pp;

	public ShortestPathAlgo(Game game) {
		ff = new ArrayList<Fruit> (game.getAllfruit());
		pp = new ArrayList<Packman> (game.getAllPackman());
		Iterator<Packman> it = pp.iterator();
		while (it.hasNext()) {
			Packman g = it.next();
			g.addP(g.getGps());
			g.getPath().addTime(0);
		}
	}

	public Solution shortpath () {
		Solution answer = new Solution();
		double time =Double.MAX_VALUE;
		Fruit f = null;
		Packman p = null;
		double alltime =0;
		while (!ff.isEmpty()) {
			Iterator<Packman> it = pp.iterator();
			while (it.hasNext()) {
				Packman a = it.next();
				Fruit b =  findclosedfruit(a,ff);
				if (a.getTime() < time) {
					time = a.getTime();
					f = b;
					p = a;
				}
			}
			p.addP(f.gps);
			p.getPath().addTime(time);
			p.setGps(f.gps);
			ff.remove(f);
			alltime += time;
			time = Double.MAX_VALUE;
		}
		Iterator<Packman> it = pp.iterator();
		while (it.hasNext()) {
			Packman g = it.next();
			answer.addPath(g.getPath());
			g.setGps(g.getPath().getAllPoints().get(0));
		}

		System.out.println(alltime);
		return answer;
	}

	public Fruit findclosedfruit (Packman a,ArrayList<Fruit> ff ) {
		MyCoords m = new MyCoords();
		Fruit answer = null;
		Iterator<Fruit> it = ff.iterator();
		double min =Double.MAX_VALUE;
		while(it.hasNext()) {
			Fruit f = it.next();
			double dist = (m.distance3d(a.getGps(), f.getGps())) / a.getSpeed();
			if (dist < min) {
				min = dist;
				answer = f;
			}
		}
		a.setTime(min);
		return answer;
	}
}
