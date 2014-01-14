import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import widgets.EditorFrame;

/**
 * Programme principal lançant la fenêtre {@link EditorFrame}
 * @author davidroussel
 */
public class Editor
{

	/**
	 * Programme principal
	 * @param args arguments [non utilisés]
	 */
	public static void main(String[] args)
	{
		/*
		 * On va essayer d'utiliser le look and feel du système s'il est
		 * présent sinon, on utilisera le look and feel par défaut de java
		 */
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}

		/*
		 * Création de la fenêtre
		 */
		final EditorFrame frame = new EditorFrame();

		/*
		 * Insertion de la fenêtre dans la file des évènements GUI
		 */
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					frame.pack();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}

