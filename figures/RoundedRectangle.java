package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

public class RoundedRectangle extends AbstractFigure {
	
	private Double x;
	private Double y;
	private Double w;
	private Double h;
	
	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 */
	public RoundedRectangle(BasicStroke stroke, Paint edge, Paint fill, Double x, Double y, Double w, Double h) {
		super(stroke, edge, fill);
		shape = new RoundRectangle2D.Double(x, y, w, h, 0, 0);
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
	
	public void setArc(Point2D p) { 
		RoundRectangle2D.Double rect = (RoundRectangle2D.Double) shape;
		double new_x = p.getX();
		double new_y = p.getY();
		if (new_x > x+w && new_y > y+h) {
			rect.archeight = new_y - (y+h); 
		}
		if (new_x < x+w && new_y < y+h) {
			rect.arcwidth = x+w - new_x;
		}
	}
}
