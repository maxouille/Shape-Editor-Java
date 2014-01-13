package figures.creationListeners;

import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import figures.Drawing;

public class PolygonShapeListener extends AbstractCreationListener {

	/**
	 * @param model
	 * @param infoLabel
	 * @param nbSteps
	 */
	public PolygonShapeListener(Drawing model, JLabel infoLabel, int nbSteps) {
		super(model, infoLabel, nbSteps);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		startFigure(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			endPoint = e.getPoint();
			nextStep();
			checkZeroSizeFigure();
			drawingModel.update();
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			endFigure(e);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//currentFigure.setLastPoint(e.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
