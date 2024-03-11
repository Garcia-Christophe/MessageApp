package main.java.com.ubo.tp.message.user.model;

import main.java.com.ubo.tp.message.core.ISearchModel;
import main.java.com.ubo.tp.message.core.ISearchModelObserver;

import java.util.ArrayList;
import java.util.List;

public class UsersSearchModel implements ISearchModel {

  protected String searchString;

  protected List<ISearchModelObserver> observers;

  public UsersSearchModel() {
    this.searchString = "";
    this.observers = new ArrayList<>();
  }

  public String getSearchString() {
    return searchString;
  }

  public void setSearchString(String searchString) {
    this.searchString = searchString;

    for (ISearchModelObserver observer : observers) {
      observer.searchStringChanged(this.searchString);
    }
  }

  @Override
  public void addObserver(ISearchModelObserver observer) {
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(ISearchModelObserver observer) {
    this.observers.remove(observer);
  }
}
