package main.java.com.ubo.tp.message.message.controller;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.message.IMessageInputObserver;
import main.java.com.ubo.tp.message.message.ISearchModelObserver;
import main.java.com.ubo.tp.message.message.model.ListMessagesModel;
import main.java.com.ubo.tp.message.message.model.SearchMessageModel;
import main.java.com.ubo.tp.message.message.view.MessageInputView;
import main.java.com.ubo.tp.message.message.view.MessagesListView;
import main.java.com.ubo.tp.message.message.view.MessagesModuleView;
import main.java.com.ubo.tp.message.message.view.SearchMessageView;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MessagesModuleComponent implements IMessageInputObserver, IDatabaseObserver, ISearchModelObserver {

  protected IDatabase mDatabase;

  protected ISession mSession;

  protected EntityManager mEntityManager;

  protected MessagesModuleView messagesModuleView;

  protected SearchMessageView searchMessageView;

  protected MessagesListView messagesListView;

  protected MessageInputView messageInputView;

  protected SearchMessageModel searchMessageModel;

  protected ListMessagesModel listMessagesModel;

  public MessagesModuleComponent(IDatabase database, ISession session, EntityManager entityManager) {
    this.mDatabase = database;
    this.mDatabase.addObserver(this);
    this.mSession = session;
    this.mEntityManager = entityManager;

    // Models
    this.searchMessageModel = new SearchMessageModel();
    this.searchMessageModel.addObserver(this);
    this.listMessagesModel = new ListMessagesModel();

    // Vues
    this.messagesModuleView = new MessagesModuleView();
    this.searchMessageView = new SearchMessageView(this.searchMessageModel);
    this.messagesListView = new MessagesListView(this.mSession);
    this.messageInputView = new MessageInputView();
  }

  public void initGUI() {
    // Vue principale
    this.messagesModuleView.initGUI();

    // Vue search message
    this.searchMessageView.initGUI();
    this.messagesModuleView.setNorth(this.searchMessageView);

    // Vue liste
    this.messagesListView.initGUI();
    this.messagesModuleView.setCenter(this.messagesListView);
    this.listMessagesModel.addObserver(this.messagesListView);

    // Vue input message
    this.messageInputView.initGUI();
    this.messageInputView.addObserver(this);
    this.messagesModuleView.setSouth(this.messageInputView);
  }

  public MessagesModuleView getMessagesModuleView() {
    this.listMessagesModel.setFilteredMessagesList(this.mDatabase.getMessages()); // donne la liste à afficher

    return this.messagesModuleView;
  }

  private Set<Message> getFilteredMessages(String searchString) {
    Set<Message> messagesList = this.mDatabase.getMessages();
    Predicate<Message> streamsPredicate;

    // Multi-filtres possibles (séparés par des espaces)
    for (String filter : searchString.split("\\s+")) {
      if (searchString.isEmpty()) {
        streamsPredicate = message -> true;
      } else if (filter.charAt(0) == '@') {
        // messages émis par cet utilisateur et ceux dans lesquels l'utilisateur est cité
        streamsPredicate = message -> message.getSender().getUserTag().equals(filter.substring(1)) ||
            message.getUserTags().contains(filter.substring(1));
      } else if (filter.charAt(0) == '#') {
        // messages contenant ce tag
        streamsPredicate = message -> message.getTags().contains(filter.substring(1));
      } else {
        // union des critères précédents
        streamsPredicate = message -> message.getSender().getUserTag().equals(filter) ||
            message.getUserTags().contains(filter) || message.getTags().contains(filter);
      }

      messagesList = messagesList.stream()
          .filter(streamsPredicate)
          .collect(Collectors.toSet());
    }

    return messagesList;
  }

  @Override
  public void newMessage(String content) {
    // enregistrement du message
    Message message = new Message(this.mSession.getConnectedUser(), content);
    this.mEntityManager.writeMessageFile(message);
    this.messageInputView.resetInput();
  }

  @Override
  public void notifyMessageAdded(Message addedMessage) {
    // Mise à jour de la liste des messages
    // Modification éventuelle de la liste des messages affichée (si filtre applicable sur le nouveau message)
    this.listMessagesModel.setFilteredMessagesList(this.getFilteredMessages(this.searchMessageModel.getSearchString()));
  }

  @Override
  public void notifyMessageDeleted(Message deletedMessage) {
    // Mise à jour de la liste des messages
    // Modification éventuelle de la liste des messages affichée (si filtre applicable sur le nouveau message)
    this.listMessagesModel.setFilteredMessagesList(this.getFilteredMessages(this.searchMessageModel.getSearchString()));
  }

  @Override
  public void notifyMessageModified(Message modifiedMessage) {
    // Mise à jour de la liste des messages
    // Modification éventuelle de la liste des messages affichée (si filtre applicable sur le nouveau message)
    this.listMessagesModel.setFilteredMessagesList(this.getFilteredMessages(this.searchMessageModel.getSearchString()));
  }

  @Override
  public void notifyUserAdded(User addedUser) {
    // rien
  }

  @Override
  public void notifyUserDeleted(User deletedUser) {
    // rien
  }

  @Override
  public void notifyUserModified(User modifiedUser) {
    // rien
  }

  @Override
  public void searchStringChanged(String searchString) {
    // Le filtre de message a changé
    this.listMessagesModel.setFilteredMessagesList(this.getFilteredMessages(searchString));
  }
}
