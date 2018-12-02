package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import GIS.Element;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Layer;


public class CSVReader {



	private String line = "";
	private String cvsSplitBy = ",";
	private GIS_layer g;
	private int i=0; 
	
	
	public GIS_layer CsvtoLayer (String csvFile ) {
		g =  new Layer();
	
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			while ((line = br.readLine()) != null ) 
			{
				String[] userInfo = line.split(cvsSplitBy);
				i++;
				if (i!=1 && i!=2) {
					GIS_element e = new Element(userInfo);
					g.add(e);
				}
			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return g;
	}

}