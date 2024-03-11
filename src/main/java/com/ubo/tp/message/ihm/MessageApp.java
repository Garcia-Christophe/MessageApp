package main.java.com.ubo.tp.message.ihm;

import java.io.File;

import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;
import main.java.com.ubo.tp.message.ihm.session.Session;
import main.java.com.ubo.tp.message.message.controller.MessagesModuleComponent;
import main.java.com.ubo.tp.message.sign.SignComponent;
import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.core.directory.IWatchableDirectory;
import main.java.com.ubo.tp.message.core.directory.WatchableDirectory;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.user.UsersModuleComponent;

import javax.swing.*;

/**
 * Classe principale l'application.
 *
 * @author S.Lucas
 */
public class MessageApp implements IDatabaseObserver, ISessionObserver {

	/**
	 * Apparence lookandfeel de l'application.
	 * "Metal", "System", "Motif", "GTK".
	 */
	final static String LOOK_AND_FEEL = "System";

	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	protected ISession mSession;

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

	protected SignComponent signComponent;

	protected UsersModuleComponent usersModuleComponent;

	protected MessagesModuleComponent messagesModuleComponent;

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
		this.mSession = new Session();
		this.mSession.addObserver(this);

		// Controleurs
		this.signComponent = new SignComponent(this.mDatabase, this.mEntityManager, this.mSession);
		this.usersModuleComponent = new UsersModuleComponent(this.mDatabase, this.mSession);
		this.messagesModuleComponent = new MessagesModuleComponent(this.mDatabase, this.mSession, this.mEntityManager);
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
		String lookAndFeel = null;

		switch(LOOK_AND_FEEL) {
			case "Metal":
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
				break;
			case "System":
				lookAndFeel = UIManager.getSystemLookAndFeelClassName();
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
		// init GUI de la vue principale
		this.mMainView.initGUI();

		// init GUI des vues des composants
		this.signComponent.initGUI();
		this.usersModuleComponent.initGUI();
		this.messagesModuleComponent.initGUI();

		this.mMainView.showCenter(this.signComponent.getSignView(), null);
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
		this.mMainView.mFrame.setVisible(true);
	}

	@Override
	public void notifyMessageAdded(Message addedMessage) {
		System.out.println("MessageApp : database m'informe qu'un message a été ajouté");
	}

	@Override
	public void notifyMessageDeleted(Message deletedMessage) {
		System.out.println("MessageApp : database m'informe qu'un message a été supprimé");
	}

	@Override
	public void notifyMessageModified(Message modifiedMessage) {
		System.out.println("MessageApp : database m'informe qu'un message a été modifié");
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		System.out.println("MessageApp : database m'informe qu'un utilisateur été ajouté");
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		System.out.println("MessageApp : database m'informe qu'un utilisateur été supprimé");
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		System.out.println("MessageApp : database m'informe qu'un utilisateur été modifié");
	}

	@Override
	public void notifyLogin(User connectedUser) {
		// login session
		System.out.println("MessageApp: Session m'informe de la connexion d'un utilisateur");

		this.mMainView.showCenter(this.messagesModuleComponent.getMessagesModuleView(), this.usersModuleComponent.getUsersModuleView());
		this.mMainView.showNorth(this.signComponent.getSignView());
	}

	@Override
	public void notifyLogout() {
		// logout session
		System.out.println("MessageApp: Session m'informe de la déconnexion d'un utilisateur");

		this.mMainView.showCenter(this.signComponent.getSignView(), null);
		this.mMainView.showNorth(new JPanel());
	}
}
