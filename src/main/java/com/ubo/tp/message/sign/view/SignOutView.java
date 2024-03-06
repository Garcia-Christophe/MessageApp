package main.java.com.ubo.tp.message.sign.view;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.sign.controller.SignOutController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignOutView extends JPanel {
  private SignOutController signOutController;

  public SignOutView(SignOutController controller) {
    this.signOutController = controller;
    this.setLayout(new GridBagLayout());

    this.setVisible(true);
  }

  public void refreshView() {
    User user = this.signOutController.getUser();
    this.removeAll();

    // Avatar de l'utilisateur
    JLabel imgAvatar = new JLabel(new ImageIcon(new ImageIcon(user.getAvatarPath()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
    this.add(imgAvatar, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

    // Tag et nom de l'utilisateur
    JLabel userLabel = new JLabel(user.getName() + "(" + user.getUserTag() + ")");
    this.add(userLabel, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

    // Bouton Se déconnecter
    JButton buttonSignOut = new JButton("Se déconnecter");
    this.add(buttonSignOut, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.EAST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    buttonSignOut.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SignOutView.this.signOutController.signOut();
      }
    });

    this.revalidate();
    this.repaint();
  }
}
