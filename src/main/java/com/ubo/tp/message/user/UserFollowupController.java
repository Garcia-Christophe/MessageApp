package main.java.com.ubo.tp.message.user;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;

public class UserFollowupController {

  protected ISession mSession;

  protected EntityManager mEntityManager;

  public UserFollowupController(ISession session, EntityManager em) {
    this.mSession = session;
    this.mEntityManager = em;
  }

  public boolean follow(User userToFollow) {
    User connectedUser = this.mSession.getConnectedUser();

    if (!connectedUser.isFollowing(connectedUser)) {
      connectedUser.addFollowing(userToFollow.getUserTag());
      this.mEntityManager.writeUserFile(connectedUser);
      return true;
    }
    return false;
  }

  public boolean unfollow(User userToUnfollow) {
    User connectedUser = this.mSession.getConnectedUser();

    if (connectedUser.isFollowing(userToUnfollow)) {
      connectedUser.removeFollowing(userToUnfollow.getUserTag());
      this.mEntityManager.writeUserFile(connectedUser);
      return true;
    }
    return false;
  }

}
