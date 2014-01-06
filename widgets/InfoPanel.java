package widgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

/**
 * Panel dans lequel afficher des informations sur la figure en dessous du
 * pointeur de la souris (grâce à {@link Shape#contains(double, double)}). Les
 * informations à afficher sont les suivantes
 * <ul>
 * <li>Le nom du type de la figure</li>
 * <li>Les coordonnées du point en haut à gauche grâce à
 * {@link Shape#getBounds()} ou {@link Shape#getBounds2D()}</li>
 * <li>Les coordonnées du point en bas à droite grâce à
 * {@link Shape#getBounds()} ou {@link Shape#getBounds2D()}</li>
 * </ul>
 *
 * @author davidroussel
 */
public class InfoPanel extends JPanel
{
	/**
	 * Une chaine vide pour remplir les champs lorsque la souris n'est au dessus
	 * d'aucune figure
	 */
	public static final String emptyString = new String();

	/**
	 * Le formatteur à utiliser pour formater les coordonnés
	 */
	private final static DecimalFormat coordFormat = new DecimalFormat("000");

	/**
	 * Le label contenant le type de la figure
	 */
	private JLabel lblFigure;

	/**
	 * Le label contenant l'abcisse du point en haut à gauche
	 */
	private JLabel lblTopLeftX;

	/**
	 * le label contenant l'ordonnée du point en haut à gauche
	 */
	private JLabel lblTopLeftY;

	/**
	 * Le label contenant l'abcisse du point en bas à droite
	 */
	private JLabel lblBottomRightX;

	/**
	 * le label contenant l'ordonnée du point en bas à droite
	 */
	private JLabel lblBottomRightY;

	/**
	 * Le label contenant l'abcisse du centre
	 */
	private JLabel lblCenterX;

	/**
	 * le label contenant l'ordonnée du centre
	 */
	private JLabel lblCenterY;

	/**
	 * le label contenant la largeur de la figure
	 */
	private JLabel lblDimensionX;

	/**
	 * le label contenant la hauteur de la figure
	 */
	private JLabel lblDimensionY;

	/**
	 * Constructeur d'un info panel contenant les labels nécessaires pour
	 * l'affichage : du type de figure sur laquelle se trouve le pointeur de la
	 * souris, des bornes de la figure (topleft, bottoright) et du centre de la
	 * figure.
	 */
	public InfoPanel()
	{
		super(true);
		setToolTipText("Informations sur la figure sous le curseur");
		setPreferredSize(new Dimension(210, 130));
		setAlignmentY(Component.TOP_ALIGNMENT);
		setMinimumSize(new Dimension(210, 120));
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null,
				null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 80, 60, 60 };
		gridBagLayout.rowHeights = new int[] { 30, 20, 20, 0, 20 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		setLayout(gridBagLayout);

		lblFigure = new JLabel("Figure");
		GridBagConstraints gbc_lblFigure = new GridBagConstraints();
		gbc_lblFigure.gridwidth = 3;
		gbc_lblFigure.insets = new Insets(0, 0, 5, 0);
		gbc_lblFigure.gridx = 0;
		gbc_lblFigure.gridy = 0;
		add(lblFigure, gbc_lblFigure);

		JLabel lblX = new JLabel("x");
		lblX.setFont(lblX.getFont().deriveFont(lblX.getFont().getSize() - 3f));
		lblX.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblX = new GridBagConstraints();
		gbc_lblX.anchor = GridBagConstraints.EAST;
		gbc_lblX.insets = new Insets(0, 0, 5, 5);
		gbc_lblX.gridx = 1;
		gbc_lblX.gridy = 1;
		add(lblX, gbc_lblX);

		JLabel lblY = new JLabel("y");
		lblY.setFont(lblY.getFont().deriveFont(lblY.getFont().getSize() - 3f));
		lblY.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblY = new GridBagConstraints();
		gbc_lblY.anchor = GridBagConstraints.EAST;
		gbc_lblY.insets = new Insets(0, 0, 5, 0);
		gbc_lblY.gridx = 2;
		gbc_lblY.gridy = 1;
		add(lblY, gbc_lblY);

		JLabel lblTopLeft = new JLabel("top left");
		lblTopLeft.setFont(lblTopLeft.getFont().deriveFont(
				lblTopLeft.getFont().getSize() - 3f));
		lblTopLeft.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblTopLeft = new GridBagConstraints();
		gbc_lblTopLeft.anchor = GridBagConstraints.WEST;
		gbc_lblTopLeft.insets = new Insets(0, 0, 5, 5);
		gbc_lblTopLeft.gridx = 0;
		gbc_lblTopLeft.gridy = 2;
		add(lblTopLeft, gbc_lblTopLeft);

		lblTopLeftX = new JLabel("tlx");
		lblTopLeftX.setFont(lblTopLeftX.getFont().deriveFont(
				lblTopLeftX.getFont().getSize() - 3f));
		lblTopLeftX.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTopLeftX.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblTopleftx = new GridBagConstraints();
		gbc_lblTopleftx.anchor = GridBagConstraints.EAST;
		gbc_lblTopleftx.insets = new Insets(0, 0, 5, 5);
		gbc_lblTopleftx.gridx = 1;
		gbc_lblTopleftx.gridy = 2;
		add(lblTopLeftX, gbc_lblTopleftx);

		lblTopLeftY = new JLabel("tly");
		lblTopLeftY.setFont(lblTopLeftY.getFont().deriveFont(
				lblTopLeftY.getFont().getSize() - 3f));
		lblTopLeftY.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblToplefty = new GridBagConstraints();
		gbc_lblToplefty.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblToplefty.insets = new Insets(0, 0, 5, 0);
		gbc_lblToplefty.gridx = 2;
		gbc_lblToplefty.gridy = 2;
		add(lblTopLeftY, gbc_lblToplefty);

		JLabel lblBottomRight = new JLabel("bottom right");
		lblBottomRight.setFont(lblBottomRight.getFont().deriveFont(
				lblBottomRight.getFont().getSize() - 3f));
		lblBottomRight.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblBottomRight = new GridBagConstraints();
		gbc_lblBottomRight.anchor = GridBagConstraints.WEST;
		gbc_lblBottomRight.insets = new Insets(0, 0, 5, 5);
		gbc_lblBottomRight.gridx = 0;
		gbc_lblBottomRight.gridy = 3;
		add(lblBottomRight, gbc_lblBottomRight);

		lblBottomRightX = new JLabel("brx");
		lblBottomRightX.setFont(lblBottomRightX.getFont().deriveFont(
				lblBottomRightX.getFont().getSize() - 3f));
		lblBottomRightX.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblBottomrightx = new GridBagConstraints();
		gbc_lblBottomrightx.anchor = GridBagConstraints.EAST;
		gbc_lblBottomrightx.insets = new Insets(0, 0, 5, 5);
		gbc_lblBottomrightx.gridx = 1;
		gbc_lblBottomrightx.gridy = 3;
		add(lblBottomRightX, gbc_lblBottomrightx);

		lblBottomRightY = new JLabel("bry");
		lblBottomRightY.setFont(lblBottomRightY.getFont().deriveFont(
				lblBottomRightY.getFont().getSize() - 3f));
		lblBottomRightY.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblBottomrighty = new GridBagConstraints();
		gbc_lblBottomrighty.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBottomrighty.insets = new Insets(0, 0, 5, 0);
		gbc_lblBottomrighty.gridx = 2;
		gbc_lblBottomrighty.gridy = 3;
		add(lblBottomRightY, gbc_lblBottomrighty);

		JLabel lblDimensions = new JLabel("dimensions");
		lblDimensions.setFont(lblDimensions.getFont().deriveFont(
				lblDimensions.getFont().getSize() - 3f));
		GridBagConstraints gbc_lblDimensions = new GridBagConstraints();
		gbc_lblDimensions.anchor = GridBagConstraints.WEST;
		gbc_lblDimensions.insets = new Insets(0, 0, 5, 5);
		gbc_lblDimensions.gridx = 0;
		gbc_lblDimensions.gridy = 4;
		add(lblDimensions, gbc_lblDimensions);

		lblDimensionX = new JLabel("dx");
		lblDimensionX.setFont(lblDimensionX.getFont().deriveFont(
				lblDimensionX.getFont().getSize() - 3f));
		GridBagConstraints gbc_lblDimensionx = new GridBagConstraints();
		gbc_lblDimensionx.anchor = GridBagConstraints.EAST;
		gbc_lblDimensionx.insets = new Insets(0, 0, 5, 5);
		gbc_lblDimensionx.gridx = 1;
		gbc_lblDimensionx.gridy = 4;
		add(lblDimensionX, gbc_lblDimensionx);

		lblDimensionY = new JLabel("dy");
		lblDimensionY.setFont(lblDimensionY.getFont().deriveFont(
				lblDimensionY.getFont().getSize() - 3f));
		GridBagConstraints gbc_lblDimensiony = new GridBagConstraints();
		gbc_lblDimensiony.anchor = GridBagConstraints.EAST;
		gbc_lblDimensiony.insets = new Insets(0, 0, 5, 0);
		gbc_lblDimensiony.gridx = 2;
		gbc_lblDimensiony.gridy = 4;
		add(lblDimensionY, gbc_lblDimensiony);

		JLabel lblCenter = new JLabel("center");
		lblCenter.setFont(lblCenter.getFont().deriveFont(
				lblCenter.getFont().getSize() - 3f));
		lblCenter.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblCenter = new GridBagConstraints();
		gbc_lblCenter.anchor = GridBagConstraints.WEST;
		gbc_lblCenter.insets = new Insets(0, 0, 0, 5);
		gbc_lblCenter.gridx = 0;
		gbc_lblCenter.gridy = 5;
		add(lblCenter, gbc_lblCenter);

		lblCenterX = new JLabel("cx");
		lblCenterX.setFont(lblCenterX.getFont().deriveFont(
				lblCenterX.getFont().getSize() - 3f));
		lblCenterX.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCenterx = new GridBagConstraints();
		gbc_lblCenterx.anchor = GridBagConstraints.EAST;
		gbc_lblCenterx.insets = new Insets(0, 0, 0, 5);
		gbc_lblCenterx.gridx = 1;
		gbc_lblCenterx.gridy = 5;
		add(lblCenterX, gbc_lblCenterx);

		lblCenterY = new JLabel("cy");
		lblCenterY.setFont(lblCenterY.getFont().deriveFont(
				lblCenterY.getFont().getSize() - 3f));
		lblCenterY.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCentery = new GridBagConstraints();
		gbc_lblCentery.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCentery.gridx = 2;
		gbc_lblCentery.gridy = 5;
		add(lblCenterY, gbc_lblCentery);
	}

	/**
	 * Mise à jour des label avec de nouvelles données concernant la figure sous
	 * le pointeur de la souris
	 *
	 * @param figureType le type de figure
	 * @param bounds le rectangle contenant la figure
	 * @param center le point centre de la figure
	 */
	public void updateLabels(String figureType,
			Rectangle2D bounds,
			Point2D center)
	{
		lblFigure.setText(figureType);

		double minX = bounds.getMinX();
		double maxX = bounds.getMaxX();
		double minY = bounds.getMinY();
		double maxY = bounds.getMaxY();
		double width = maxX - minX;
		double height = maxY - minY;

		lblTopLeftX.setText(coordFormat.format(minX));
		lblTopLeftY.setText(coordFormat.format(minY));
		lblBottomRightX.setText(coordFormat.format(maxX));
		lblBottomRightY.setText(coordFormat.format(maxY));

		lblDimensionX.setText(coordFormat.format(width));
		lblDimensionY.setText(coordFormat.format(height));

		lblCenterX.setText(coordFormat.format(center.getX()));
		lblCenterY.setText(coordFormat.format(center.getY()));
	}

	/**
	 * Vidage des labels lorsqu'aucune figure ne se trouve sous le curseur de la
	 * souris
	 */
	public void resetLabels()
	{
		lblFigure.setText(emptyString);

		lblTopLeftX.setText(emptyString);
		lblTopLeftY.setText(emptyString);
		lblBottomRightX.setText(emptyString);
		lblBottomRightY.setText(emptyString);
		lblDimensionX.setText(emptyString);
		lblDimensionY.setText(emptyString);
		lblCenterX.setText(emptyString);
		lblCenterY.setText(emptyString);
	}
}
