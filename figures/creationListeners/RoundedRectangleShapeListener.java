package figures.creationListeners;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JLabel;

import figures.Drawing;

public class RoundedRectangleShapeListener extends AbstractCreationListener {

	protected Point2D previousPoint;
	/**
	 * @param model
	 * @param infoLabel
	 * @param nbSteps
	 */
	public RoundedRectangleShapeListener(Drawing model, JLabel infoLabel,
			int nbSteps) {
		super(model, infoLabel, nbSteps);
		tips[0] = "Bouton Gauche et tirez pour commencer la figure ";
		tips[1] = "Relacher le bouton Gauche pour finir la figure       ";
		tips[2] = "Bougez la souris pour créer l'arrondi                       ";
		updateTip();
		previousPoint = null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (currentStep== 2) {
			/*if (e.getButton() == MouseEvent.BUTTON3) {
					RoundedRectangle rect = (RoundedRectangle) currentFigure;
					rect.setArc(e.getPoint());
					drawingModel.update();
					nextStep();*/
				endFigure(e);
			/*}*/
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (currentStep==0) {
			startFigure(e);
			previousPoint = e.getPoint();
			updateTip();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(currentStep==1){
			currentFigure.setLastPoint(previousPoint);
			drawingModel.update();
			nextStep();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(this.currentStep==1 && !(e.getPoint().equals(previousPoint))){
			currentFigure.setLastPoint(e.getPoint());
			previousPoint=e.getPoint();
			drawingModel.update();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(this.currentStep==2){
			currentFigure.setLastPoint(e.getPoint());
			drawingModel.update();
		}	
	}

}
