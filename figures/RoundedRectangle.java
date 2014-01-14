package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

public class RoundedRectangle extends Rectangle {
	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 * @param premierPoint
	 */
	int etat;
	double arcWidth;
	double arcHeight;
	
	
	public RoundedRectangle(BasicStroke stroke, Paint edge, Paint fill,
			Point2D premierPoint) {
		super(stroke, edge, fill, premierPoint.getX(), premierPoint.getY());
		etat=0;
		arcWidth=0;
		arcHeight=0;
	}
	
	@Override
	public void setLastPoint(Point2D p) {
		Point2D firstPoint = new Point2D.Double(x, y);
		Point2D secondPoint = new Point2D.Double(x+w, y+h);
		if(etat==0){
			if(! p.equals(secondPoint)){
				super.setLastPoint(p);
			}else{
				etat=1;
			}
		}else{
			arcWidth=Math.abs(p.getX()-secondPoint.getX());
			arcHeight=Math.abs(p.getY()-secondPoint.getY());
			double x = firstPoint.getX();
			double y = firstPoint.getY();
			double w = secondPoint.getX()-x;
			double h = secondPoint.getY()-y;
			if(w<0){
				x = x + w;
				w = Math.abs(w);
			}
			if(h<0){
				y = y + h;
				h = Math.abs(h);
			}
			shape = new RoundRectangle2D.Double(x, y, w, h, arcWidth, arcHeight);
		}
	}	
}
