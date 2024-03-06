package main.java.com.ubo.tp.message.user;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.ihm.session.ISession;

public class UsersModuleComponent {

  protected IDatabase mDatabase;

  protected ISession mSession;

  protected UsersModuleView usersModuleView;

  protected UsersListView usersListView;

  public UsersModuleComponent(IDatabase database, ISession session) {
    this.mDatabase = database;
    this.mSession = session;

    this.usersModuleView = new UsersModuleView();
  }

  public void initGUI() {
    // Vues
    this.usersListView = new UsersListView(this.mDatabase, this.mSession);
    this.usersModuleView.setCenter(this.usersListView);
  }

  public UsersModuleView getUsersModuleView() {
    this.usersListView.refreshView();
    return this.usersModuleView;
  }
}
