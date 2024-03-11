package main.java.com.ubo.tp.message.message.model;

import main.java.com.ubo.tp.message.message.IMessageInputObserver;
import main.java.com.ubo.tp.message.message.ISearchModel;
import main.java.com.ubo.tp.message.message.ISearchModelObserver;

import java.util.ArrayList;
import java.util.List;

public class SearchMessageModel implements ISearchModel {

  protected String searchString;

  protected List<ISearchModelObserver> observers;

  public SearchMessageModel() {
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
