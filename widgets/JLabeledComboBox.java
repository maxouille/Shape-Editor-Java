package widgets;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

/**
 * Classe contenant un titre et une liste déroulante utilisant des JLabel avec
 * des icones pour les élements de la liste déroulante
 */
public class JLabeledComboBox extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Le titre de cette liste */
	private String title;

	/** Icons pour les labels */
	private ImageIcon[] icons;

	/** Les textes pour les items */
	private String[] captions;

	/**
	 * La combobox utilisée à l'intérieur pour pouvoir à ajouter des listener
	 * par la suite
	 */
	@SuppressWarnings("rawtypes")
	private JComboBox combobox;

	/**
	 * le répertoire des images. Attention, il s'agit du répertoire relatif à
	 * cette classe et non pas au programme principal
	 */
	private final static String ImageBase = "/images/";

	/** l'extension des images */
	private final static String ImageType = ".png";

	/**
	 * Constructeur
	 * @param title le titre du panel
	 * @param captions les légendes des éléments de la liste
	 * @param selectedIndex l'élément sélectionné initialement
	 * @param listener le listener à appeller quand l'élement sélectionné de la
	 *            liste change
	 * @see #createImageIcon(String)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JLabeledComboBox(String title, String[] captions, int selectedIndex,
	        ItemListener listener)
	{
		setAlignmentX(Component.LEFT_ALIGNMENT);

		this.title = title;
		this.captions = captions;

		// Charge les images et créé un tableau d'indexs pour le combobox
		// qui seront utilisés dans le ComboBoxRenderer
		icons = new ImageIcon[this.captions.length];
		Integer[] intArray = new Integer[this.captions.length];
		for (int i = 0; i < icons.length; i++)
		{
			intArray[i] = new Integer(i);
			icons[i] = createImageIcon(ImageBase + this.captions[i] + ImageType);
			if (icons[i] != null)
			{
				icons[i].setDescription(this.captions[i]);
			}
		}
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		// Creates the title
		JLabel label = new JLabel((this.title != null ? this.title : "text"));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		add(label);

		// Creates the Combobox
		combobox = new JComboBox(intArray);
		combobox.setAlignmentX(Component.LEFT_ALIGNMENT);
		combobox.setEditable(false);
		int index;
		if ((selectedIndex < 0) || (selectedIndex > captions.length))
		{
			index = 0;
		}
		else
		{
			index = selectedIndex;
		}
		combobox.setSelectedIndex(index);
		combobox.addItemListener(listener);
		// Mise en place du renderer pour les élements de la liste
		JLabelRenderer renderer = new JLabelRenderer();
		renderer.setPreferredSize(new Dimension(100, 32));
		combobox.setRenderer(renderer);
		// Ajout de la liste
		add(combobox);
		// setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}

	/**
	 * Ajout d'un nouveau listener déclenché lorsqu'un élément est sélectionné
	 * @param aListener le nouveau listener à ajouter.
	 */
 	public void addItemListener(ItemListener aListener)
 	{
 		if (combobox != null)
 		{
 			combobox.addItemListener(aListener);
 		}
 	}

 	/**
 	 * Obtention de l'index de l'élément sélectionné dans le combobox
 	 * @return l'index de l'élément sélectionné dans le combobox
 	 */
 	public int getSelectedIndex()
 	{
 		return combobox.getSelectedIndex();
 	}

	/**
	 * Création d'une icône à partir d'un fichier
	 * @param path le chemin du fichier
	 * @return une nouvelle icône si le fichier existe ou bien null
	 * si le fichier n'a pas été trouvé
	 */
	protected ImageIcon createImageIcon(String path)
	{
		URL imgURL = JLabeledComboBox.class.getResource(path);
		if (imgURL != null)
		{
			return new ImageIcon(imgURL);
		}
		else
		{
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Renderer pour les Labels du combobox
	 */
	@SuppressWarnings("rawtypes")
	protected class JLabelRenderer extends JLabel implements ListCellRenderer
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/** fonte pour les items à problèmes */
		private Font pbFont;

		/**
		 * Constructeur
		 */
		public JLabelRenderer()
		{
			setOpaque(true);
			setHorizontalAlignment(LEFT);
			setVerticalAlignment(CENTER);
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing
		 * .JList, java.lang.Object, int, boolean, boolean)
		 */
		@SuppressWarnings("synthetic-access")
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
		        int index, boolean isSelected, boolean cellHasFocus)
		{
			// Obtention de l'indice de l'élément selectionné : le paramètre
			// index est toujours valide, il faut juste le caster
			int selectedIndex = ((Integer) value).intValue();

			if (isSelected)
			{
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			}
			else
			{
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			// Mise en place de l'icone et du texte dans le label
			// Si l'icone est null afficher un label particulier avec
			// setPbText
			ImageIcon itemIcon = icons[selectedIndex];
			String itemString = captions[selectedIndex];
			setIcon(itemIcon);
			if (itemIcon != null)
			{
				setText(itemString);
				setFont(list.getFont());
			}
			else
			{
				setPbText(itemString + " (pas d'image)", list.getFont());
			}

			return this;
		}

		/**
		 * Mise en place du texte s'il y a un pb pour cet item
		 * @param pbText le texte à afficher
		 * @param normalFont la fonte à utiliser (italique)
		 */
		protected void setPbText(String pbText, Font normalFont)
		{
			if (pbFont == null)
			{ // lazily create this font
				pbFont = normalFont.deriveFont(Font.ITALIC);
			}
			setFont(pbFont);
			setText(pbText);
		}
	}
}
