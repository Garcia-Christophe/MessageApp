package main.java.com.ubo.tp.message;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.Database;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.core.directory.IWatchableDirectory;
import main.java.com.ubo.tp.message.core.directory.WatchableDirectory;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.MessageApp;
import main.java.com.ubo.tp.message.ihm.javafx.FxSignInController;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;
import main.java.com.ubo.tp.message.ihm.session.Session;
import main.java.com.ubo.tp.message.sign.controller.SignInController;
import mock.MessageAppMock;

import javax.swing.*;
import java.io.File;

/**
 * Classe de lancement de l'application.
 *
 * @author S.Lucas
 */
public class MessageAppLauncherFx extends Application implements IDatabaseObserver, ISessionObserver {

	protected IDatabase mDatabase;
	protected ISession mSession;
	protected EntityManager mEntityManager;
	protected IWatchableDirectory mWatchableDirectory;
	protected String mExchangeDirectoryPath = "C:\\Users\\Christophe\\Documents\\_Projets_\\messageapp\\exchangeDirectory";

	@Override
	public void start(Stage stage) throws Exception {
		// init
		mDatabase = new Database();
		mDatabase.addObserver(this);
		mEntityManager = new EntityManager(mDatabase);
		mSession = new Session();
		mSession.addObserver(this);

		FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("./ihm/javafx/fxml_main.fxml"));
		FXMLLoader loaderConnection = new FXMLLoader(getClass().getResource("./ihm/javafx/fxml_connection.fxml"));

		// controleurs
		loaderConnection.setControllerFactory(controllerClass -> {
			SignInController signInController = new SignInController(mDatabase, mEntityManager, mSession);
			FxSignInController fxSignInController = new FxSignInController(signInController, loaderMain);

			if (controllerClass == fxSignInController.getClass()) {
				return fxSignInController;
			}
			return controllerClass;
		});

		// init directory
		this.initDirectory();

		// Lancement de la Scène
		Parent root = loaderConnection.load();
		Scene scene = new Scene(root, 300, 275);
		stage.setTitle("Message App");
		stage.setScene(scene);
		stage.show();
	}

	protected void initDirectory() {
		mWatchableDirectory = new WatchableDirectory(mExchangeDirectoryPath);
		mEntityManager.setExchangeDirectory(mExchangeDirectoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}

	@Override
	public void notifyMessageAdded(Message addedMessage) {
		System.out.println("MessageAppLauncherFx : notifyMessageAdded");
	}

	@Override
	public void notifyMessageDeleted(Message deletedMessage) {
		System.out.println("MessageAppLauncherFx : notifyMessageDeleted");
	}

	@Override
	public void notifyMessageModified(Message modifiedMessage) {
		System.out.println("MessageAppLauncherFx : notifyMessageModified");
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		System.out.println("MessageAppLauncherFx : notifyUserAdded");
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		System.out.println("MessageAppLauncherFx : notifyUserDeleted");
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		System.out.println("MessageAppLauncherFx : notifyUserModified");
	}

	@Override
	public void notifyLogin(User connectedUser) {
		System.out.println("MessageAppLauncherFx : notifyLogin");
	}

	@Override
	public void notifyLogout() {
		System.out.println("MessageAppLauncherFx : notifyLogout");
	}
}
