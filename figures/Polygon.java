package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;

public class Polygon extends AbstractFigure {
	
	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 */
	public Polygon(BasicStroke stroke, Paint edge, Paint fill, Point2D p) {
		super(stroke, edge, fill);
		int[] lx = new int[] {(int) p.getX(), (int) p.getX()};
		int[] ly = new int[] {(int) p.getY(), (int) p.getY()};
		shape = new java.awt.Polygon(lx, ly, 2);
	}

	@Override
	public void setLastPoint(Point2D p) {
		java.awt.Polygon poly = (java.awt.Polygon) shape;
		int longueur = poly.xpoints.length;
		poly.xpoints[longueur-1] = (int) p.getX();
		poly.ypoints[longueur-1] = (int) p.getY();
	}

	@Override
	public Point2D getCenter() {
		java.awt.Polygon poly = (java.awt.Polygon) shape;
		int resx = 0;
		for (int i = 0; i < poly.xpoints.length; i++) {
			resx += poly.xpoints[i];
		}
		int resy = 0;
		for (int i = 0; i < poly.ypoints.length; i++) {
			resy += poly.ypoints[i];
		}		
		double resmeanx = resx/poly.xpoints.length;
		double resmeany = resy/poly.ypoints.length;
		return new Point2D.Double(resmeanx,  resmeany);
	}
	
}
