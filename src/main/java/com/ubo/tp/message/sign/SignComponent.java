package main.java.com.ubo.tp.message.sign;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;
import main.java.com.ubo.tp.message.sign.controller.SignInController;
import main.java.com.ubo.tp.message.sign.controller.SignOutController;
import main.java.com.ubo.tp.message.sign.controller.SignUpController;
import main.java.com.ubo.tp.message.sign.view.SignInView;
import main.java.com.ubo.tp.message.sign.view.SignOutView;
import main.java.com.ubo.tp.message.sign.view.SignUpView;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.sign.view.SignView;

import java.util.ArrayList;
import java.util.List;

public class SignComponent implements ISessionObserver, ISign, ISwitchSignViewObserver {

  protected IDatabase mDatabase;

  protected EntityManager mEntityManager;

  protected ISession mSession;

  protected SignInController signInController;

  protected SignUpController signUpController;

  protected SignOutController signOutController;

  protected SignView signView;

  protected SignInView signInView;

  protected SignUpView signUpView;

  protected SignOutView signOutView;

  protected User mConnectedUser;

  protected List<ISignObserver> mObservers = new ArrayList<>();

  public SignComponent(IDatabase database, EntityManager entityManager, ISession session) {
    this.mDatabase = database;
    this.mEntityManager = entityManager;
    this.mSession = session;
    this.mSession.addObserver(this);

    // Contrôleurs
    this.signInController = new SignInController(this.mDatabase, this.mEntityManager, this.mSession);
    this.signInController.addObserver(this);
    this.signUpController = new SignUpController(this.mDatabase, this.mEntityManager, this.mSession);
    this.signUpController.addObserver(this);
    this.signOutController = new SignOutController(this.mSession);
  }

  public void initGUI() {
    // Vues
    this.signView = new SignView();
    this.signInView = new SignInView(this.signInController);
    this.signUpView = new SignUpView(this.signUpController);
    this.signOutView = new SignOutView(this.signOutController);
    this.signView.show(this.signInView);
  }

  public SignView getSignView() {
    return this.signView;
  }

  @Override
  public void notifyLogin(User connectedUser) {
    this.mConnectedUser = connectedUser;
    this.signOutView.refreshView();
    this.signView.show(this.signOutView);

    for (ISignObserver observer : this.mObservers) {
      observer.notifySignIn(this.mConnectedUser);
    }
  }

  @Override
  public void notifyLogout() {
    this.mConnectedUser = null;
    this.signView.show(this.signInView);

    for (ISignObserver observer : this.mObservers) {
      observer.notifySignOut();
    }
  }

  @Override
  public void addObserver(ISignObserver observer) {
    this.mObservers.add(observer);
  }

  @Override
  public void removeObserver(ISignObserver observer) {
    this.mObservers.remove(observer);
  }

  @Override
  public User getConnectedUser() {
    return this.mConnectedUser;
  }

  @Override
  public void notifySwitchToSignInView() {
    this.signView.show(this.signInView);
  }

  @Override
  public void notifySwitchToSignUpView() {
    this.signView.show(this.signUpView);
  }
}
