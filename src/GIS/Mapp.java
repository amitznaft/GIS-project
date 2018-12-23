package GIS;

import Geom.Point3D;

public class Mapp {

	Point3D up = new Point3D(32.106046, 35.202574);
	Point3D down = new Point3D(32.101858, 35.212405);
	int hightFrame;
	int wightFrame;

	public Mapp (int h , int w) {
		this.hightFrame = h;
		this.wightFrame = w;
	}

	
	public Point3D PixelToPoint(Pixel p) {
		double distUpDown_X = up.x() -down.x();
		double distUpDown_Y = up.y() -down.y();
		
		double distCoords_X = (distUpDown_X*p.getWightPixel())/wightFrame;
		double distCoords_Y = (distUpDown_Y*p.getHightPixel())/hightFrame;
		
		double x = distCoords_X + this.down.x();
		double y = distCoords_Y + this.down.y();
		Point3D r = new Point3D(x,y);
		return r;
	}
	
	public Pixel PointToPixel (Point3D p) {
		
		double diff_X = p.x() - down.x();
		double diff_Y = p.y() - down.y();
		
		double distUpDown_X = up.x() -down.x();
		double distUpDown_Y = up.y() -down.y();
		
		double new_x = (diff_X*wightFrame) / distUpDown_X;
		double new_y = (diff_Y*hightFrame) / distUpDown_Y;
		
		return new Pixel ((int)new_x,(int)new_y);
	}
}
