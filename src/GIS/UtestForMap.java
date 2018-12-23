package GIS;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Before;
import org.junit.Test;
import Geom.Point3D;
import org.junit.Assert;

public class UtestForMap {

	BufferedImage myImage;
	private Mapp map;
	
	@Before
	public void setup() {

		try {
			myImage = ImageIO.read(new File("Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		map  = new Mapp (myImage.getHeight(), myImage.getWidth());
	}


	@Test
	public void PixelToPoint() {
		Pixel p = new Pixel (783,79);
		Point3D exepted = new Point3D(32.104146348918356 ,35.21119526635514);
		Point3D convert =  map.PixelToPoint(p);
		Assert.assertTrue(exepted.equals(convert));
	}
	
	@Test
	public void PointToPixel() {
		Point3D p = new Point3D(32.104998,35.208434);
		Pixel exepted = new Pixel (1074,259);
		Pixel convert = map.PointToPixel(p);
		Assert.assertTrue(exepted.equael(convert));
	}
	
	

}
