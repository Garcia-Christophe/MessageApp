package main.java.com.ubo.tp.message.ihm;

import java.io.File;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.core.directory.IWatchableDirectory;
import main.java.com.ubo.tp.message.core.directory.WatchableDirectory;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;

import javax.swing.*;

/**
 * Classe principale l'application.
 *
 * @author S.Lucas
 */
public class MessageApp implements IDatabaseObserver {

	/**
	 * Apparence lookandfeel de l'application.
	 * "Metal", "System", "Motif", "GTK".
	 */
	final static String LOOK_AND_FEEL = "System";

	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected MessageAppMainView mMainView;

	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;

	/**
	 * Constructeur.
	 *
	 * @param entityManager
	 * @param database
	 */
	public MessageApp(IDatabase database, EntityManager entityManager) {
		this.mDatabase = database;
		this.mEntityManager = entityManager;
		this.mMainView = new MessageAppMainView();

		// Observe la base de données
		this.mDatabase.addObserver(this);
	}

	/**
	 * Initialisation de l'application.
	 */
	public void init() {
		// Init du look and feel de l'application
		this.initLookAndFeel();

		// Initialisation du répertoire d'échange
		this.initDirectory();

		// Initialisation de l'IHM
		this.initGui();
	}

	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel() {
		// Par défaut, LOOK_AND_FEEL = "System"
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();

			switch(LOOK_AND_FEEL) {
				case "Metal":
					lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
					break;
				case "Motif":
					lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
					break;
				case "GTK":
					lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
					break;
			}

			try {
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		this.mMainView.initGUI();
	}

	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	protected void initDirectory() {
		String exchangeDirectoryPath = null;
		while(exchangeDirectoryPath == null || !isValideExchangeDirectory(new File(exchangeDirectoryPath))) {
			exchangeDirectoryPath = this.mMainView.getExchangeDirectoryPath();
		}
		this.initDirectory(exchangeDirectoryPath);
	}

	/**
	 * Indique si le fichier donné est valide pour servir de répertoire d'échange
	 *
	 * @param directory , Répertoire à tester.
	 */
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}

	/**
	 * Initialisation du répertoire d'échange.
	 *
	 * @param directoryPath
	 */
	protected void initDirectory(String directoryPath) {
		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}

	public void show() {
		// ... setVisible?
	}

	@Override
	public void notifyMessageAdded(Message addedMessage) {
		System.out.println("Message ajouté");
	}

	@Override
	public void notifyMessageDeleted(Message deletedMessage) {
		System.out.println("Message supprimé");
	}

	@Override
	public void notifyMessageModified(Message modifiedMessage) {
		System.out.println("Message modifié");
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		System.out.println("Utilisateur ajouté");
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		System.out.println("Utilisateur supprimé");
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		System.out.println("Utilisateur modifié");
	}
}
