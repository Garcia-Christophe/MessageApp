package main.java.com.ubo.tp.message.user;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class UsersListView extends JPanel {

  protected IDatabase mDatabase;

  protected ISession mSession;

  protected JPanel usersPanel;

  public UsersListView(IDatabase database, ISession session) {
    this.mDatabase = database;
    this.mSession = session;

    this.setLayout(new GridBagLayout());
    this.usersPanel = new JPanel();
    this.usersPanel.setLayout(new GridBagLayout());
    JScrollPane scrollPane = new JScrollPane(usersPanel,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.getVerticalScrollBar().setUnitIncrement(10);
    this.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    this.setVisible(true);
  }

  public void refreshView() {
    this.usersPanel.removeAll();

    // Affichage de la liste des utilisateurs
    Iterator<User> usersIt = this.mDatabase.getUsers().iterator();
    int nbUser = 0;

    while (usersIt.hasNext()) {
      User user = usersIt.next();
      if (!user.getUserTag().equals(this.mSession.getConnectedUser().getUserTag())) {
        this.usersPanel.add(new UserView(user), new GridBagConstraints(0, nbUser, 1, 1, 1, 1, GridBagConstraints.CENTER,
            GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
      }
      nbUser++;
    }

    this.revalidate();
    this.repaint();
  }

}
