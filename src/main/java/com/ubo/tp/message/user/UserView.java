package main.java.com.ubo.tp.message.user;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.session.ISession;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Iterator;

public class UserView extends JPanel {

  public UserView(User user) {
    this.setLayout(new GridBagLayout());
    this.removeAll();

    // bordures
    this.setOpaque(true);
    this.setBorder(new LineBorder(Color.BLACK, 1, true));

    // avatar
    JLabel imgAvatar = new JLabel(new ImageIcon(new ImageIcon(user.getAvatarPath()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
    this.add(imgAvatar, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

    JPanel identitePanel = new JPanel();
    identitePanel.setLayout(new GridBagLayout());

    // nom
    JLabel name = new JLabel(user.getName());
    identitePanel.add(name, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
        GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

    // tag
    JLabel tag = new JLabel("#" + user.getUserTag());
    identitePanel.add(tag, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST,
        GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

    this.add(identitePanel, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

    this.setVisible(true);
  }

}
