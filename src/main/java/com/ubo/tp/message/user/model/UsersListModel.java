package main.java.com.ubo.tp.message.user.model;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.user.IListUsersModel;
import main.java.com.ubo.tp.message.user.IListUsersModelObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UsersListModel implements IListUsersModel {

  protected Set<User> filteredUsersList;

  protected List<IListUsersModelObserver> observers;

  public UsersListModel() {
    this.observers = new ArrayList<>();
  }

  public void setFilteredUsersList(Set<User> filteredMessagesList) {
    this.filteredUsersList = filteredMessagesList;

    for (IListUsersModelObserver observer : observers) {
      observer.listUsersChanged(this.filteredUsersList);
    }
  }

  @Override
  public void addObserver(IListUsersModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(IListUsersModelObserver observer) {
    observers.remove(observer);
  }
}
