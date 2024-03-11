package main.java.com.ubo.tp.message.user;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.ISearchModelObserver;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.message.model.ListMessagesModel;
import main.java.com.ubo.tp.message.message.model.SearchMessageModel;
import main.java.com.ubo.tp.message.user.model.UsersListModel;
import main.java.com.ubo.tp.message.user.model.UsersSearchModel;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UsersModuleComponent implements ISearchModelObserver, IListUsersModelObserver {

  protected IDatabase mDatabase;

  protected ISession mSession;

  protected EntityManager mEntityManager;

  protected UsersModuleView usersModuleView;

  protected UsersListView usersListView;

  protected UsersSearchView usersSearchView;

  protected UsersSearchModel usersSearchModel;

  protected UsersListModel usersListModel;

  protected Set<User> usersList;

  public UsersModuleComponent(IDatabase database, ISession session, EntityManager em) {
    this.mDatabase = database;
    this.mSession = session;
    this.mEntityManager = em;

    // Models
    this.usersSearchModel = new UsersSearchModel();
    this.usersSearchModel.addObserver(this);
    this.usersListModel = new UsersListModel();
    this.usersListModel.addObserver(this);

    // Vues
    this.usersModuleView = new UsersModuleView();
    this.usersListView = new UsersListView(this.mDatabase, this.mSession);
    this.usersSearchView = new UsersSearchView(this.usersSearchModel);
  }

  public void initGUI() {
    // Vue principale
    this.usersModuleView.initGUI();

    // Vue search user
    this.usersSearchView.initGUI();
    this.usersModuleView.setNorth(this.usersSearchView);

    // Vue liste users
    this.usersListView.initGUI();
    this.usersModuleView.setCenter(this.usersListView);
  }

  public void refreshListView() {
    this.usersListView.removeContent();

    // Affichage de la liste des utilisateurs
    Iterator<User> usersIt = this.usersList.iterator();
    int gridy = 0;

    while (usersIt.hasNext()) {
      User user = usersIt.next();

      boolean ownProfile = user.getUserTag().equals(this.mSession.getConnectedUser().getUserTag());
      boolean followed = this.mSession.getConnectedUser().isFollowing(user);

      UserFollowupController controller = new UserFollowupController(this.mSession, this.mEntityManager);
      UserView userView = new UserView(user, controller, ownProfile, followed);
      this.usersListView.addUser(userView, gridy);

      gridy++;
    }

    this.usersListView.update();
  }

  public UsersModuleView getUsersModuleView() {
    this.usersListModel.setFilteredUsersList(this.mDatabase.getUsers()); // donne la liste à afficher
    this.refreshListView();
    return this.usersModuleView;
  }

  private Set<User> getFilteredUsers(String searchString) {
    Set<User> usersList = this.mDatabase.getUsers();
    Predicate<User> streamsPredicate = user -> true;

    if (!searchString.isEmpty()) {
      // tag ou name
      streamsPredicate = user -> user.getUserTag().equals(searchString) || user.getName().equals(searchString);
    }

    return usersList.stream()
        .filter(streamsPredicate)
        .collect(Collectors.toSet());
  }

  @Override
  public void searchStringChanged(String searchString) {
    // Le filtre de users a changé
    this.usersListModel.setFilteredUsersList(this.getFilteredUsers(searchString));
  }

  @Override
  public void listUsersChanged(Set<User> referenceList) {
    this.usersList = referenceList;
    this.refreshListView();
  }
}
