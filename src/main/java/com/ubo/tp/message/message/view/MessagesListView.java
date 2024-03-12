package main.java.com.ubo.tp.message.message.view;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.ihm.session.ISession;
import main.java.com.ubo.tp.message.message.IListMessagesModelObserver;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MessagesListView extends JPanel implements IListMessagesModelObserver {

  protected ISession mSession;

  protected JPanel messagesPanel;

  protected Set<Message> messagesList;

  protected long dateTime;

  public MessagesListView(ISession session) {
    this.mSession = session;
    this.messagesList = new HashSet<>();
    this.messagesPanel = new JPanel();
    this.updateDateTime();

    this.setLayout(new GridBagLayout());
    this.setVisible(true);
  }

  public void initGUI() {
    this.messagesPanel.setLayout(new GridBagLayout());
    JScrollPane scrollPane = new JScrollPane(messagesPanel);
    scrollPane.getVerticalScrollBar().setUnitIncrement(10);
    this.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
  }

  public void refreshView() {
    this.messagesPanel.removeAll();

    // Affichage de la liste des utilisateurs
    Set<Message> messagesSorted = new TreeSet<Message>((msg1, msg2) -> Long.compare(msg2.getEmissionDate(), msg1.getEmissionDate()));
    messagesSorted.addAll(this.messagesList);
    Iterator<Message> messagesIt = messagesSorted.iterator();
    int nbMessage = 0;

    while (messagesIt.hasNext()) {
      Message message = messagesIt.next();
      boolean userMessage = message.getSender().getUserTag().equals(this.mSession.getConnectedUser().getUserTag());
      boolean newFollowedMsg = this.dateTime < message.getEmissionDate() && this.mSession.getConnectedUser().isFollowing(message.getSender());
      this.messagesPanel.add(new MessageView(message, userMessage, newFollowedMsg), new GridBagConstraints(0, nbMessage, 1, 1, 1, 1, userMessage ? GridBagConstraints.EAST : GridBagConstraints.WEST,
            GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
      nbMessage++;
    }

    this.revalidate();
    this.repaint();
  }

  @Override
  public void listMessagesChanged(Set<Message> referenceList) {
    this.messagesList = referenceList;
    this.refreshView();
  }

  public void updateDateTime() {
    this.dateTime = new Date().getTime();
  }

  public long getDateTime() {
    return this.dateTime;
  }
}
