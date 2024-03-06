package main.java.com.ubo.tp.message.user;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class UserView extends JPanel {

  protected IDatabase mDatabase;

  public UserView(IDatabase database) {
    this.mDatabase = database;

    this.setLayout(new GridBagLayout());
    this.setVisible(true);
  }

  public void refreshView() {
    this.removeAll();

    // Affichage de la liste des utilisateurs
    Iterator<User> usersIt = this.mDatabase.getUsers().iterator();
    int nbUser = 0;

    while (usersIt.hasNext()) {
      User user = usersIt.next();
      JLabel label = new JLabel(user.toString());
      this.add(label, new GridBagConstraints(0, nbUser, 1, 1, 1, 1, GridBagConstraints.CENTER,
          GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
      nbUser++;
    }

    this.revalidate();
    this.repaint();
  }

}
