package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Ellipse extends AbstractFigure {

	private Point2D upper_left;
	private double w;
	private double h;
	
	

	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 * @param upper_left
	 * @param w
	 * @param h
	 */
	public Ellipse(BasicStroke stroke, Paint edge, Paint fill,
			Point2D upper_left, double w, double h) {
		super(stroke, edge, fill);
		shape = new Ellipse2D.Double(upper_left.getX(), upper_left.getY(), w, h);
	}

	@Override
	public void setLastPoint(Point2D p) {
		w = Math.abs(p.getX() - upper_left.getX());
		h = Math.abs(p.getY() - upper_left.getY());

	}

	@Override
	public Point2D getCenter() {
		return new Point2D.Double(upper_left.getX()+w, upper_left.getY()-h);
	}

}
