package GIS;

public class Pixel {
	
	private int hightPixel;
	private int wightPixel;
	
	public Pixel (int wight, int hight) {
		this.hightPixel = hight;
		this.wightPixel = wight;
	}
	public int getHightPixel() {
		return hightPixel;
	}
	public int getWightPixel() {
		return wightPixel;
	}
	public String toString () {
		return "("+this.wightPixel +","+ this.hightPixel +")";
	}
	public void setHightPixel( int hight) {
		this.hightPixel = hight;
	}
	public void setWightPixel(int wight) {
		this.wightPixel = wight;
	}

	
	public boolean equael(Pixel p) {
		boolean hight = false;
		boolean wight = false;
		if(this.getHightPixel() == p.getHightPixel()|| this.getHightPixel() == p.getHightPixel()+1||this.getHightPixel() == p.getHightPixel()-1) {
			hight = true;
		}
		if(this.getWightPixel() == p.getWightPixel()|| this.getWightPixel() == p.getWightPixel()+1||this.getWightPixel() == p.getWightPixel()-1) {
			wight = true; 
		}
		if(hight == true && wight == true) {
			return true;
		}
		return false;
	}
}
	
