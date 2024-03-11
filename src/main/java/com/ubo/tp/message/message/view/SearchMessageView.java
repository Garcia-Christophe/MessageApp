package main.java.com.ubo.tp.message.message.view;

import main.java.com.ubo.tp.message.message.IMessageInput;
import main.java.com.ubo.tp.message.message.IMessageInputObserver;
import main.java.com.ubo.tp.message.message.model.SearchMessageModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SearchMessageView extends JPanel implements IMessageInput {

  List<IMessageInputObserver> observers = new ArrayList<>();

  protected SearchMessageModel model;

  public SearchMessageView(SearchMessageModel searchMessageModel) {
    this.model = searchMessageModel;
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
        SearchMessageView.this.model.setSearchString(inputMessage.getText() != null ? inputMessage.getText() : "");
      }
    });
    this.add(sendButton, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
  }

  @Override
  public void addObserver(IMessageInputObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(IMessageInputObserver observer) {
    observers.remove(observer);
  }
}
