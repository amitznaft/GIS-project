package File_format;

import java.util.ArrayList;

import GIS.Game;
import GIS.Path;
import GIS.ShortestPathAlgo;
import GIS.Solution;

public class Main {

	public static void main(String[] args) {
		Game game = new Game("game_1543693911932_a.csv");
		ShortestPathAlgo s = new ShortestPathAlgo(game);
		Solution sol = s.shortpath();
		ArrayList<Path> p = sol.getAllpaths();
		PathToKml m = new PathToKml();
		m.toKmlFile("out", p);
	}

}
