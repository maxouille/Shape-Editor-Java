package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends AbstractFigure {
	
	private Double x;
	private Double y;
	private Double w;
	private Double h;
	
	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 */
	public Rectangle(BasicStroke stroke, Paint edge, Paint fill, Double x, Double y, Double w, Double h) {
		super(stroke, edge, fill);
		shape = new Rectangle2D.Double(x, y, w, h);
	}
	
	@Override
	public void setLastPoint(Point2D p) {
		w = Math.abs(p.getX() - x);
		h = Math.abs(p.getY() - y);
	}

	@Override
	public Point2D getCenter() {
		return new Point2D.Double((x+w)/2, (y-h)/2);
	}

}
