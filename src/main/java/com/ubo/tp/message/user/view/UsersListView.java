package main.java.com.ubo.tp.message.user.view;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.ihm.session.ISession;

import javax.swing.*;
import java.awt.*;

public class UsersListView extends JPanel {

  protected IDatabase mDatabase;

  protected ISession mSession;

  protected JPanel usersPanel;

  public UsersListView(IDatabase database, ISession session) {
    this.mDatabase = database;
    this.mSession = session;

    this.setLayout(new GridBagLayout());
    this.setVisible(true);
  }

  public void initGUI() {
    this.usersPanel = new JPanel();
    this.usersPanel.setLayout(new GridBagLayout());
    JScrollPane scrollPane = new JScrollPane(usersPanel,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.getVerticalScrollBar().setUnitIncrement(10);
    this.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
  }

  public void addUser(UserView userView, int gridy) {
    this.usersPanel.add(userView, new GridBagConstraints(0, gridy, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
  }

  public void removeContent() {
    this.usersPanel.removeAll();
  }

  public void update() {
    this.usersPanel.revalidate();
    this.usersPanel.repaint();
  }
}
