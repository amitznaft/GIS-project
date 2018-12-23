package GUI;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;


import GIS.Fruit;
import GIS.Game;
import GIS.Mapp;
import GIS.Packman;
import GIS.Path;
import GIS.Pixel;
import GIS.ShortestPathAlgo;
import GIS.Solution;
import Geom.Point3D;


public class MainWindow extends JFrame implements MouseListener, Runnable
{
	public BufferedImage myImage;
	public BufferedImage ImagePackman;
	public BufferedImage ImageFruit;
	int button;
	Game game = new Game();
	Game game1;
	boolean b = false;
	boolean bo = false;

	public MainWindow() 
	{
		initGUI();		
		this.addMouseListener(this); 
	}
	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu menu1 = new Menu("File"); 
		MenuItem item1 = new MenuItem("save");
		MenuItem item2 = new MenuItem("clear");
		MenuItem item3 = new MenuItem("exit");
		Menu menu2 = new Menu("Input"); 
		MenuItem item4 = new MenuItem("csv file");
		MenuItem item5 = new MenuItem("run");

		menuBar.add(menu1);
		menuBar.add(menu2);
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		menu2.add(item4);
		menu2.add(item5);
		this.setMenuBar(menuBar);
		item4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter( "CSV file","csv");
				fc.setFileFilter(filter);
				fc.setAcceptAllFileFilterUsed(false);
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						game1 = new Game (file.getPath());
						b = true;
						Scanner input = new Scanner(file);
						input.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
				repaint();
			}
		});
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (b) {
					game1.clear();
				}
				game.clear();
				button = -1;
				bo = false;
				repaint();
			}});

		item3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}});
		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						game.save(game, file.getPath());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					file.getName().endsWith(".csv");
				}}});
		MainWindow temp = this;
		item5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread t = new Thread(temp);
				t.start();
				//				bo = true;
				//				repaint();
			}});

		try {
			myImage = ImageIO.read(new File("Ariel1.png"));
			ImagePackman= ImageIO.read(new File("packman.png"));
			ImageFruit =ImageIO.read(new File("Fruit.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	int x = -1;
	int y = -1;
	public void paint(Graphics g)
	{
		g.drawImage(myImage, 0, 0,this.getWidth(), this.getHeight(), this);
		Mapp map = new Mapp(this.getHeight() , this.getWidth());
		Iterator<Fruit> it1 = game.iteratorfruit();
		Iterator<Packman> it2 = game.iteratorpack();
		while (it1.hasNext()) {
			Fruit a = (Fruit) it1.next();
			Pixel b = map.PointToPixel(a.getGps());
			g.drawImage(ImageFruit, b.getWightPixel(), b.getHightPixel(),30,30, this);	
		}
		while (it2.hasNext()) {
			Packman a = (Packman) it2.next();
			Pixel b = map.PointToPixel(a.getGps());
			g.drawImage(ImagePackman, b.getWightPixel(), b.getHightPixel(),30,30, this);	
		}
		if (b) {
			Iterator<Fruit> it3 = game1.iteratorfruit();
			Iterator<Packman> it4 = game1.iteratorpack();
			while (it3.hasNext()) {
				Fruit a = it3.next();
				Pixel b = map.PointToPixel(a.getGps());
				g.drawImage(ImageFruit, b.getWightPixel(), b.getHightPixel(),30,30, this);	
			}
			while (it4.hasNext()) {
				Packman a = it4.next();
				Pixel b = map.PointToPixel(a.getGps());
				g.drawImage(ImagePackman, b.getWightPixel(), b.getHightPixel(),30,30, this);	
			}
		}
		if(x!=-1 && y!=-1)
		{
			if (button == 1 ) {
				g.drawImage(ImageFruit, x, y,30,30, this);
				Pixel pixel = new Pixel(x,y);
				Point3D p = map.PixelToPoint(pixel);
				Fruit a = new Fruit(p);
				game.addFruit(a);
				button = -1;
			}
			if (button == 3) {
				g.drawImage(ImagePackman, x, y,30,30, this);
				Pixel pixel = new Pixel(x,y);
				Point3D p = map.PixelToPoint(pixel);
				Packman a = new Packman(p,1,1);
				game.addPack(a);
				button = -1;
			}
		}
		if (bo) {
			ShortestPathAlgo s;
			if (b) {
				s = new ShortestPathAlgo(game1);
			}
			else {
				s = new ShortestPathAlgo(game);
			}
			g.setColor(Color.red);
			Solution ad = s.shortpath();
			Iterator <Path> itp = ad.itPath();
			while(itp.hasNext()) {
				Path path = itp.next();
				Iterator<Point3D> it = path.iteratorpack(); 
				Point3D p = it.next();
				while (it.hasNext()) {
					Point3D p2 = it.next();
					Pixel pix1 = map.PointToPixel(p);
					Pixel pix2 = map.PointToPixel(p2);
					g.drawLine(pix1.getWightPixel()+10,pix1.getHightPixel()+10,pix2.getWightPixel()+10,pix2.getHightPixel()+10);
					p = p2;
				}
			}
		}	
	}
	@Override
	public void mouseClicked(MouseEvent arg) {
		System.out.println("("+arg.getX() +"," + arg.getY()+")");
		button = arg.getButton();
		x = arg.getX();
		y = arg.getY();
		repaint(x,y,30,30);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("mouse entered");

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}
	public void run () {
		if (b) {
			Mapp map = new Mapp(this.getHeight() , this.getWidth());
			ShortestPathAlgo s;
			s = new ShortestPathAlgo(game1);
			Solution ad = s.shortpath();
			Iterator <Path> itp = ad.itPath();
			while(itp.hasNext()) {
				Path path = itp.next();
				Iterator<Point3D> itpoint = path.iteratorpack();
				Point3D p = new Point3D (itpoint.next());
				Iterator <Packman> it = game1.iteratorpack();
				while(it.hasNext()) {
					Packman a = it.next();
					if (a.getGps().equals(p)) {
						a.setPath(path);
						break;
					}
				}
			}
			Point3D [] b  =  new Point3D[game1.getAllPackman().size()]; ;
			for (int p =0; p < game1.getAllPackman().size(); p++){
				Packman a = game1.getAllPackman().get(p);

				b[p]=a.getPath().LestIndex();
			}
			int count = 0;
			while(!game1.getAllfruit().isEmpty()) {  
				for (int p =0; p < game1.getAllPackman().size(); p++){
					Packman a = game1.getAllPackman().get(p);
					count ++;
					if (a.getPath().getAllPoints().size() == 1 ) { continue; }
					else if (a.getPath().getAllPoints().get(0).equals(a.getPath().getAllPoints().get(1) )&& a.isB()==false) {	
						if ((a.getGps().equals(b[p]) && count < game1.getAllPackman().size())) {
							Iterator <Fruit> itf = game1.iteratorfruit();
							while(itf.hasNext()) {
								Fruit f = itf.next();
								if (f.getGps().equals(a.getGps())){
									game1.getAllfruit().remove(f);
									a.setB(true);
									break;
								}
							}
						}
						else if(a.getGps().equals(a.getPath().getAllPoints().get(1)) && a.isB()==false) {
							Iterator <Fruit> itf = game1.iteratorfruit();
							while(itf.hasNext()) {
								Fruit f = itf.next();
								if (f.getGps().equals(a.getGps())){
									game1.getAllfruit().remove(f);
									a.setB(true);
									break;
								}
							}
						}
					}
					else if(a.getGps().equals(b[p])) {

						continue;
					}
					else {
						Point3D pac = a.getPath().nextFruit(a);
						Pixel pix2 = map.PointToPixel(a.getGps());
						Pixel pix0 = new Pixel(pix2.getWightPixel(), pix2.getHightPixel());
						Pixel pix1 = map.PointToPixel(pac);

						if (pix1.getWightPixel() > pix0.getWightPixel()+1 && pix1.getWightPixel() > pix0.getWightPixel()) {
							pix0.setWightPixel(pix0.getWightPixel() +2);
						}
						else if (pix1.getWightPixel() < pix0.getWightPixel()+1 &&pix1.getWightPixel() < pix0.getWightPixel()) {
							pix0.setWightPixel(pix0.getWightPixel() -2);
						}

						if (pix1.getHightPixel() > pix0.getHightPixel()+1 && pix1.getHightPixel() > pix0.getHightPixel()) {
							pix0.setHightPixel(pix0.getHightPixel() +2);
						}
						else if (pix1.getHightPixel() < pix0.getHightPixel()+1 && pix1.getHightPixel() < pix0.getHightPixel()) {
							pix0.setHightPixel(pix0.getHightPixel() -2);
						}

						Point3D ppp = map.PixelToPoint(pix0);
						a.setGps(ppp);
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						repaint();

						Iterator <Fruit> itf = game1.iteratorfruit();
						if (pix0.equael(pix1))
							while(itf.hasNext()) {
								Fruit f = itf.next();
								Point3D new_a = new Point3D(f.getGps());
								if (f.getGps().equals(pac)){
									game1.getAllPackman().get(p).setGps(new_a);
									game1.getAllfruit().remove(f);
									break;
								}
							}
						if(p == game1.getAllPackman().size()-1) {
							p=-1;
						}

					}	
				}	
			}
		}

		else {
			Mapp map = new Mapp(this.getHeight() , this.getWidth());
			ShortestPathAlgo s;
			s = new ShortestPathAlgo(game);
			Solution ad = s.shortpath();
			Iterator <Path> itp = ad.itPath();
			while(itp.hasNext()) {
				Path path = itp.next();
				Iterator<Point3D> itpoint = path.iteratorpack();
				Point3D p = new Point3D (itpoint.next());
				Iterator <Packman> it = game.iteratorpack();
				while(it.hasNext()) {
					Packman a = it.next();
					if (a.getGps().equals(p)) {
						a.setPath(path);
						break;
					}
				}
			}
			Point3D [] b  =  new Point3D[game.getAllPackman().size()]; ;
			for (int p =0; p < game.getAllPackman().size(); p++){
				Packman a = game.getAllPackman().get(p);

				b[p]=a.getPath().LestIndex();
			}
			int count = 0;
			while(!game.getAllfruit().isEmpty()) {  
				for (int p =0; p < game.getAllPackman().size(); p++){
					Packman a = game.getAllPackman().get(p);
					count ++;
					if (a.getGps().equals(b[p]) && count < game.getAllPackman().size()) {
						Iterator <Fruit> itf = game.iteratorfruit();
						while(itf.hasNext()) {
							Fruit f = itf.next();
							if (f.getGps().equals(a.getGps())){
								game.getAllfruit().remove(f);
								break;
							}
						}
					}
					else if(a.getGps().equals(b[p])) {

						continue;
					}
					else {
						Point3D pac = a.getPath().nextFruit(a);
						Pixel pix2 = map.PointToPixel(a.getGps());
						Pixel pix0 = new Pixel(pix2.getWightPixel(), pix2.getHightPixel());
						Pixel pix1 = map.PointToPixel(pac);

						if (pix1.getWightPixel() > pix0.getWightPixel()+1 && pix1.getWightPixel() > pix0.getWightPixel()) {
							pix0.setWightPixel(pix0.getWightPixel() +2);
						}
						else if (pix1.getWightPixel() < pix0.getWightPixel()+1 &&pix1.getWightPixel() < pix0.getWightPixel()) {
							pix0.setWightPixel(pix0.getWightPixel() -2);
						}

						if (pix1.getHightPixel() > pix0.getHightPixel()+1 && pix1.getHightPixel() > pix0.getHightPixel()) {
							pix0.setHightPixel(pix0.getHightPixel() +2);
						}
						else if (pix1.getHightPixel() < pix0.getHightPixel()+1 && pix1.getHightPixel() < pix0.getHightPixel()) {
							pix0.setHightPixel(pix0.getHightPixel() -2);
						}

						Point3D ppp = map.PixelToPoint(pix0);
						a.setGps(ppp);
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						repaint();

						Iterator <Fruit> itf = game.iteratorfruit();
						if (pix0.equael(pix1))
							while(itf.hasNext()) {
								Fruit f = itf.next();
								Point3D new_a = new Point3D(f.getGps());
								if (f.getGps().equals(pac)){
									game.getAllPackman().get(p).setGps(new_a);
									game.getAllfruit().remove(f);
									break;
								}
							}
						if(p == game.getAllPackman().size()-1) {
							p=-1;
						}

					}	
				}	
			}
		}
	}
}


