package main.java.com.ubo.tp.message.sign.controller;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.sign.ISwitchSignView;
import main.java.com.ubo.tp.message.sign.ISwitchSignViewObserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SignInController implements ISwitchSignView {

  protected IDatabase mDatabase;

  protected EntityManager mEntityManager;

  protected ISession mSession;

  protected List<ISwitchSignViewObserver> observers = new ArrayList<>();

  public SignInController(IDatabase database, EntityManager entityManager, ISession session) {
    this.mDatabase = database;
    this.mEntityManager = entityManager;
    this.mSession = session;
  }

  public boolean signIn(String tag, String password) {
    Set<User> users = this.mDatabase.getUsers();
    User user = null;
    boolean isConnexionOk = false;

    // Vérification de la connexion
    Iterator<User> userIt = users.iterator();
    while (!isConnexionOk && userIt.hasNext()) {
      User u = userIt.next();
      if (u.getUserTag().equals(tag) && u.getUserPassword().equals(password)) {
        isConnexionOk = true;
        user = u;
      }
    }

    if (user != null) {
      // Remonte l'information de connexion
      this.mSession.connect(user);
    }

    return isConnexionOk;
  }

  @Override
  public void addObserver(ISwitchSignViewObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ISwitchSignViewObserver observer) {
    observers.remove(observer);
  }

  @Override
  public void switchToSignInView() {
    // rien
  }

  @Override
  public void switchToSignUpView() {
    for (ISwitchSignViewObserver observer : observers) {
      observer.notifySwitchToSignUpView();
    }
  }
}
