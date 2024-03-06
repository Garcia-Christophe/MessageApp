package main.java.com.ubo.tp.message.sign.controller;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;

public class SignOutController {

  protected ISession mSession;

  public SignOutController(ISession session) {
    this.mSession = session;
  }

  public void signOut() {
    this.mSession.disconnect();
  }

  public User getUser() {
    return this.mSession.getConnectedUser();
  }
}
