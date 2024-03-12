package main.java.com.ubo.tp.message;

import javafx.application.Application;
import javafx.application.Platform;
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
import main.java.com.ubo.tp.message.javafx.FxMainController;
import main.java.com.ubo.tp.message.javafx.FxSignInController;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;
import main.java.com.ubo.tp.message.ihm.session.Session;
import main.java.com.ubo.tp.message.javafx.FxSignUpController;
import main.java.com.ubo.tp.message.sign.ISwitchSignViewObserver;
import main.java.com.ubo.tp.message.sign.controller.SignInController;
import main.java.com.ubo.tp.message.sign.controller.SignOutController;
import main.java.com.ubo.tp.message.sign.controller.SignUpController;

import java.io.IOException;

/**
 * Classe de lancement de l'application.
 *
 * @author S.Lucas
 */
public class MessageAppLauncherFx extends Application implements IDatabaseObserver, ISessionObserver, ISwitchSignViewObserver {

	protected Stage stage;
	protected IDatabase mDatabase;
	protected ISession mSession;
	protected EntityManager mEntityManager;
	protected IWatchableDirectory mWatchableDirectory;
	protected String mExchangeDirectoryPath = "C:\\Users\\Christophe\\Documents\\_Projets_\\messageapp\\exchangeDirectory";

	protected String loaderPathMain = "./javafx/fxml_main.fxml";
	protected String loaderPathConnection = "./javafx/fxml_connection.fxml";
	protected String loaderPathInscription = "./javafx/fxml_inscription.fxml";

	protected SignInController signInController;
	protected SignUpController signUpController;
	protected SignOutController signOutController;

	@Override
	public void start(Stage stage) {
		// init
		this.stage = stage;
		mDatabase = new Database();
		mDatabase.addObserver(this);
		mEntityManager = new EntityManager(mDatabase);
		mSession = new Session();
		mSession.addObserver(this);

		// Lancement de la Frame (Swing)
		MessageApp messageApp = new MessageApp(mDatabase, mEntityManager, mSession);
		messageApp.init();
		messageApp.show();

		// Controleurs
		// sign in
		signInController = messageApp.getSignComponent().getSignInController();
		signInController.addObserver(this);
		// sign up
		signUpController = messageApp.getSignComponent().getSignUpController();
		signUpController.addObserver(this);
		// sign out
		signOutController = messageApp.getSignComponent().getSignOutController();

		// Lancement de la Scène (JavaFX)
		this.switchTo(this.loaderPathConnection);
	}

	protected void switchTo(String loaderPath) {
		// controleurs
		FXMLLoader loader = new FXMLLoader(getClass().getResource(loaderPath));

		loader.setControllerFactory(controllerClass -> {
			if (controllerClass == FxSignInController.class) {
				return new FxSignInController(signInController);
			} else if (controllerClass == FxSignUpController.class) {
				return new FxSignUpController(signUpController);
			} else if (controllerClass == FxMainController.class) {
				return new FxMainController(mDatabase, signOutController);
			}
			return controllerClass;
		});

		Platform.runLater(() -> {
			try {
				Parent root = loader.load();
				Scene scene = new Scene(root, 500, 500);
				stage.setTitle("MessageApp");
				stage.setScene(scene);
				stage.show();
			} catch(IOException e) {
				System.err.println(e);
			}
		});
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
		// switch to main
		switchTo(this.loaderPathMain);
	}

	@Override
	public void notifyLogout() {
		// switch to sign in
		switchTo(this.loaderPathConnection);
	}

	@Override
	public void notifySwitchToSignInView() {
		// switch to sign in
		switchTo(this.loaderPathConnection);
	}

	@Override
	public void notifySwitchToSignUpView() {
		// switch to sign up
		switchTo(this.loaderPathInscription);
	}
}
