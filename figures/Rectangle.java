package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;

public class Rectangle extends AbstractFigure {

	private Point2D x;
	private Point2D y;
	
	
	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 * @param x
	 * @param y
	 */
	public Rectangle(BasicStroke stroke, Paint edge, Paint fill, Point2D x, Point2D y) {
		super(stroke, edge, fill);
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public Point2D getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(Point2D x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public Point2D getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(Point2D y) {
		this.y = y;
	}

	@Override
	public void setLastPoint(Point2D p) {
		// TODO Auto-generated method stub

	}

	@Override
	public Point2D getCenter() {
		double new_x = (x.getX()+y.getX())/2;
		double new_y = (x.getY()+y.getY())/2;
		return new Point2D.Double(new_x, new_y);
	}

}
