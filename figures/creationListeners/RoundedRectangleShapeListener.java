package figures.creationListeners;

import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import figures.Drawing;
import figures.RoundedRectangle;

public class RoundedRectangleShapeListener extends AbstractCreationListener {

	/**
	 * @param model
	 * @param infoLabel
	 * @param nbSteps
	 */
	public RoundedRectangleShapeListener(Drawing model, JLabel infoLabel,
			int nbSteps) {
		super(model, infoLabel, nbSteps);
		tips[0] = "Bouton Gauche + drag pour commencer la figure";
		tips[1] = "Relacher le bouton pour finir la figure";
		tips[2] = "Bouton Droit pour créer l'arrondi";
		updateTip();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (currentStep== 2) nextStep();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		/** currentStep -> 1 */
		startFigure(e);
		updateTip();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		endPoint = e.getPoint();
		nextStep();
		checkZeroSizeFigure();
		drawingModel.update();
		updateTip();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (currentStep == 1) {
			currentFigure.setLastPoint(e.getPoint());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		while (currentStep == 2) {
			RoundedRectangle rect = (RoundedRectangle) currentFigure;
			rect.setArc(e.getPoint());
			drawingModel.update();
		}
	}

}
