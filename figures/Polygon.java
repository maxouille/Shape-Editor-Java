package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;

public class Polygon extends AbstractFigure {
	
	protected int nbPoints;
	protected int[] lx;
	protected int[] ly;
	
	public Polygon(BasicStroke stroke, Paint edge, Paint fill,Point2D p) {
		super(stroke, edge, fill);
		lx = new int[30];
		lx[0]=(int) p.getX();
		ly = new int[30];
		ly[0]=(int) p.getY();
		nbPoints = 1;
		shape = new java.awt.Polygon(lx, ly, 2);
	}

	@Override
	public void setLastPoint(Point2D p) {
		int x = (int) p.getX();
		int y = (int) p.getY();
		if(nbPoints<2 || ((x!=lx[nbPoints])||(y!=ly[nbPoints-1]))){
			if((x!=lx[nbPoints])||(y!=ly[nbPoints])){
				lx[nbPoints]=x;
				ly[nbPoints]=y;
				shape= new java.awt.Polygon(lx, ly, nbPoints+1);
			} else {
				lx[nbPoints]=x;
				ly[nbPoints]=y;
				nbPoints=nbPoints+1;
				shape= new java.awt.Polygon(lx, ly, nbPoints);
			}
		}
	}

	@Override
	public Point2D getCenter() {
		double x=0;
		double y=0;
		for(int i=0; i <= nbPoints;i++){
			x=x+(double)lx[i];
			y=y+(double)ly[i];
		}
		x=x/(nbPoints+1);
		y=y/(nbPoints+1);
		
		return (new Point2D.Double(x,y));
	}
}
