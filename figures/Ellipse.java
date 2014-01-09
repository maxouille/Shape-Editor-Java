package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
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
		this.upper_left = upper_left;
		this.w = w;
		this.h = h;
	}

	/**
	 * @return the upper_left
	 */
	public Point2D getUpper_left() {
		return upper_left;
	}

	/**
	 * @param upper_left the upper_left to set
	 */
	public void setUpper_left(Point2D upper_left) {
		this.upper_left = upper_left;
	}

	/**
	 * @return the w
	 */
	public double getW() {
		return w;
	}

	/**
	 * @param w the w to set
	 */
	public void setW(double w) {
		this.w = w;
	}

	/**
	 * @return the h
	 */
	public double getH() {
		return h;
	}

	/**
	 * @param h the h to set
	 */
	public void setH(double h) {
		this.h = h;
	}

	@Override
	public void setLastPoint(Point2D p) {
		// TODO Auto-generated method stub

	}

	@Override
	public Point2D getCenter() {
		double x = upper_left.getX()+(w/2);
		double y = upper_left.getY()-(h/2);
		return new Point2D.Double(x, y);
	}

}
