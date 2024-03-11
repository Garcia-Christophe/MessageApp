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

    this.setVisible(true);
  }
}
