package main.java.com.ubo.tp.message.message;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;

public class MessagesModuleComponent implements IMessageInputObserver, IDatabaseObserver {

  protected IDatabase mDatabase;

  protected ISession mSession;

  protected EntityManager mEntityManager;

  protected MessagesModuleView messagesModuleView;

  protected MessagesListView messagesListView;

  protected MessageInputView messageInputView;

  public MessagesModuleComponent(IDatabase database, ISession session, EntityManager entityManager) {
    this.mDatabase = database;
    this.mSession = session;
    this.mEntityManager = entityManager;
    
    this.messagesModuleView = new MessagesModuleView();
  }

  public void initGUI() {
    // Vues
    this.messagesListView = new MessagesListView(this.mDatabase, this.mSession);
    this.messagesModuleView.setCenter(this.messagesListView);
    
    this.messageInputView = new MessageInputView();
    this.messageInputView.addObserver(this);
    this.messagesModuleView.setSouth(this.messageInputView);
  }

  public MessagesModuleView getMessagesModuleView() {
    this.messagesListView.refreshView();
    return this.messagesModuleView;
  }

  @Override
  public void newMessage(String content) {
    Message message = new Message(this.mSession.getConnectedUser(), content);
    this.mEntityManager.writeMessageFile(message);
    this.mDatabase.addMessage(message);

    this.messagesListView.refreshView();
    this.messageInputView.resetInput();
  }

  @Override
  public void notifyMessageAdded(Message addedMessage) {

  }

  @Override
  public void notifyMessageDeleted(Message deletedMessage) {

  }

  @Override
  public void notifyMessageModified(Message modifiedMessage) {

  }

  @Override
  public void notifyUserAdded(User addedUser) {

  }

  @Override
  public void notifyUserDeleted(User deletedUser) {

  }

  @Override
  public void notifyUserModified(User modifiedUser) {

  }
}
