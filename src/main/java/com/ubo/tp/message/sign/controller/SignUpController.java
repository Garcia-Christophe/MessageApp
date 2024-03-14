package main.java.com.ubo.tp.message.sign.controller;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.sign.ISwitchSignView;
import main.java.com.ubo.tp.message.sign.ISwitchSignViewObserver;

import java.util.*;

public class SignUpController implements ISwitchSignView  {

  protected IDatabase mDatabase;

  protected EntityManager mEntityManager;

  protected ISession mSession;

  protected List<ISwitchSignViewObserver> observers = new ArrayList<>();

  public SignUpController(IDatabase database, EntityManager entityManager, ISession session) {
    this.mDatabase = database;
    this.mEntityManager = entityManager;
    this.mSession = session;
  }

  public boolean signUp(String name, String tag, String password, String avatarPath) {
    if (name != null && !name.isEmpty() && tag != null && !tag.isEmpty() && password != null && !password.isEmpty()) {
      Set<User> users = this.mDatabase.getUsers();
      User user = new User(UUID.randomUUID(), tag, password, name, new HashSet<String>(), avatarPath);
      boolean isInscriptionOk = true;

      // Vérification de la connexion
      Iterator<User> userIt = users.iterator();
      while (isInscriptionOk && userIt.hasNext()) {
        User u = userIt.next();
        if (u.getUserTag().equals(tag)) {
          isInscriptionOk = false;
        }
      }

      if (isInscriptionOk) {
        // Ajout le nouvel utilisateur et remonte l'information de connexion
        this.mEntityManager.writeUserFile(user);
      }

      return isInscriptionOk;
    } else {
      System.err.println("Champs manquants !");
      return false;
    }
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
    for (ISwitchSignViewObserver observer : observers) {
      observer.notifySwitchToSignInView();
    }
  }

  @Override
  public void switchToSignUpView() {
    // rien
  }
}
