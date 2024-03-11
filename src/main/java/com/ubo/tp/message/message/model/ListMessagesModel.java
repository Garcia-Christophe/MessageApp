package main.java.com.ubo.tp.message.message.model;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.message.IListMessagesModel;
import main.java.com.ubo.tp.message.message.IListMessagesModelObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListMessagesModel implements IListMessagesModel {

  protected Set<Message> filteredMessagesList;

  protected List<IListMessagesModelObserver> observers;

  public ListMessagesModel() {
    this.observers = new ArrayList<>();
  }

  public void setFilteredMessagesList(Set<Message> filteredMessagesList) {
    this.filteredMessagesList = filteredMessagesList;

    for (IListMessagesModelObserver observer : observers) {
      observer.listMessagesChanged(this.filteredMessagesList);
    }
  }

  @Override
  public void addObserver(IListMessagesModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(IListMessagesModelObserver observer) {
    observers.remove(observer);
  }
}
