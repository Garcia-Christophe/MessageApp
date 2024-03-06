package main.java.com.ubo.tp.message.user;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;
import main.java.com.ubo.tp.message.sign.ISign;
import main.java.com.ubo.tp.message.sign.ISignObserver;
import main.java.com.ubo.tp.message.sign.ISwitchSignViewObserver;
import main.java.com.ubo.tp.message.sign.controller.SignInController;
import main.java.com.ubo.tp.message.sign.controller.SignUpController;
import main.java.com.ubo.tp.message.sign.view.SignInView;
import main.java.com.ubo.tp.message.sign.view.SignUpView;
import main.java.com.ubo.tp.message.sign.view.SignView;

import java.util.ArrayList;
import java.util.List;

public class UserComponent {

  protected IDatabase mDatabase;

  protected ISession mSession;

  protected UserView userView;

  public UserComponent(IDatabase database, ISession session) {
    this.mDatabase = database;
    this.mSession = session;
  }

  public void initGUI() {
    // Vues
    this.userView = new UserView(this.mDatabase);
  }

  public UserView getUserView() {
    this.userView.refreshView();
    return this.userView;
  }
}
