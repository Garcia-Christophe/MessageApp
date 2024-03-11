package main.java.com.ubo.tp.message.sign.view;

import main.java.com.ubo.tp.message.sign.controller.SignInController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInView extends JPanel {
  protected SignInController signInController;

  protected String tag;

  protected String password;

  protected JTextField inputTag;

  protected JPasswordField inputPassword;

  public SignInView(SignInController controller) {
    this.signInController = controller;
    this.setLayout(new GridBagLayout());

    JLabel title = new JLabel("Connexion", SwingConstants.CENTER);
    Font boldFont = new Font(title.getFont().getName(), Font.BOLD, 32);
    title.setFont(boldFont);
    this.add(title, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

    JPanel formPanel = new JPanel();
    formPanel.setLayout(new GridBagLayout());

    // Label nom
    JLabel labelName = new JLabel("Tag");
    formPanel.add(labelName, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
        GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

    // Input tag
    inputTag = new JTextField();
    formPanel.add(inputTag, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

    // Label mot de passe
    JLabel labelTag = new JLabel("Mot de passe");
    formPanel.add(labelTag, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST,
        GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

    // Input mot de passe
    inputPassword = new JPasswordField();
    formPanel.add(inputPassword, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

    // Bouton Se connecter
    JPanel buttonsGrid = new JPanel();
    buttonsGrid.setLayout(new GridBagLayout());
    JButton buttonSignIn = new JButton("Se connecter");
    buttonsGrid.add(buttonSignIn, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
    buttonSignIn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SignInView.this.tag = SignInView.this.inputTag.getText();
        SignInView.this.password = new String(SignInView.this.inputPassword.getPassword());
        if (SignInView.this.signInController.signIn(SignInView.this.tag, SignInView.this.password)) {
          System.out.println("Utilisateur connecté");

          SignInView.this.tag = "";
          SignInView.this.password = "";
          SignInView.this.inputTag.setText("");
          SignInView.this.inputPassword.setText("");
        } else {
          System.err.println("Utilisateur inconnu ou mauvais mot de passe !");
        }
      }
    });

    // Bouton Nouvel utilisateur ?
    JButton buttonSignUp = new JButton("Nouvel utilisateur ?");
    buttonsGrid.add(buttonSignUp, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
    buttonSignUp.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SignInView.this.signInController.switchToSignUpView();
      }
    });
    formPanel.add(buttonsGrid, new GridBagConstraints(0, 2, 2, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));


    this.add(formPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

    this.setVisible(true);
  }
}
