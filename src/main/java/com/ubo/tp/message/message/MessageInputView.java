package main.java.com.ubo.tp.message.message;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MessageInputView extends JPanel implements IMessageInput {

  List<IMessageInputObserver> observers = new ArrayList<>();

  protected JTextField inputMessage;

  public MessageInputView() {
    this.setLayout(new GridBagLayout());
    this.removeAll();

    // bordures
    this.setOpaque(true);
    this.setBorder(new LineBorder(Color.BLACK, 1, true));

    // champs de saisie du message
    inputMessage = new JTextField();
    this.add(inputMessage, new GridBagConstraints(0, 0, 1, 1, 10, 1, GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));

    // bouton d'envoi du message
    JButton sendButton = new JButton(">");
    sendButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (IMessageInputObserver observer : MessageInputView.this.observers) {
          observer.newMessage(inputMessage.getText());
        }
      }
    });
    this.add(sendButton, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

    this.setVisible(true);
  }

  public void resetInput() {
    inputMessage.setText("");
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
