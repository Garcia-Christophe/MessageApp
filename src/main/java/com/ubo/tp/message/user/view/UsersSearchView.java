package main.java.com.ubo.tp.message.user.view;

import main.java.com.ubo.tp.message.user.model.UsersSearchModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersSearchView extends JPanel {

  protected UsersSearchModel model;

  public UsersSearchView(UsersSearchModel searchUserModel) {
    this.model = searchUserModel;
    this.setLayout(new GridBagLayout());
    this.setVisible(true);
  }

  public void initGUI() {
    // bordures
    this.setOpaque(true);
    this.setBorder(new LineBorder(Color.BLACK, 1, true));

    // champs de saisie de la recherche
    JTextField inputMessage = new JTextField();
    this.add(inputMessage, new GridBagConstraints(0, 0, 1, 1, 10, 1, GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));

    // bouton d'envoi de la recherche
    JButton sendButton = new JButton("Rechercher");
    sendButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        UsersSearchView.this.model.setSearchString(inputMessage.getText() != null ? inputMessage.getText() : "");
      }
    });
    this.add(sendButton, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
  }

}
