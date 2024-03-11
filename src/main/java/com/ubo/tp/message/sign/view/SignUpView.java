package main.java.com.ubo.tp.message.sign.view;

import main.java.com.ubo.tp.message.sign.controller.SignUpController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpView extends JPanel {
  protected SignUpController signUpController;

  protected String name;

  protected String tag;

  protected String password;

  protected String avatarPath;

  protected JPanel imgAvatarPanel;

  protected JTextField inputName, inputTag;

  protected JPasswordField inputPassword;

  public SignUpView(SignUpController controller) {
    this.signUpController = controller;
    this.setLayout(new GridBagLayout());

    JLabel title = new JLabel("Inscription", SwingConstants.CENTER);
    Font boldFont = new Font(title.getFont().getName(), Font.BOLD, 32);
    title.setFont(boldFont);
    this.add(title, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

    JPanel formPanel = new JPanel();
    formPanel.setLayout(new GridBagLayout());

    // Label nom
    JLabel labelName = new JLabel("Nom");
    formPanel.add(labelName, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
        GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

    // Input nom
    inputName = new JTextField();
    formPanel.add(inputName, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

    // Label tag
    JLabel labelTag = new JLabel("Tag");
    formPanel.add(labelTag, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST,
        GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

    // Input tag
    inputTag = new JTextField();
    formPanel.add(inputTag, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

    // Label mot de passe
    JLabel labelPassword = new JLabel("Mot de passe");
    formPanel.add(labelPassword, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.WEST,
        GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

    // Input mot de passe
    inputPassword = new JPasswordField();
    formPanel.add(inputPassword, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

    // Label avatar
    JLabel labelAvatar = new JLabel("Avatar");
    formPanel.add(labelAvatar, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.WEST,
        GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

    // Input avatar
    JPanel avatarGrid = new JPanel();
    JButton buttonAvatar = new JButton("Sélectionner un fichier");
    buttonAvatar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SignUpView.this.selectAvatar();
      }
    });
    this.imgAvatarPanel = new JPanel();
    avatarGrid.add(this.imgAvatarPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
    avatarGrid.add(buttonAvatar, new GridBagConstraints(1, 0, 1, 1, 2, 1, GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
    formPanel.add(avatarGrid, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

    // Bouton S'inscrire
    JPanel buttonsGrid = new JPanel();
    buttonsGrid.setLayout(new GridBagLayout());
    JButton buttonSignUp = new JButton("S'inscrire");
    buttonsGrid.add(buttonSignUp, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
    buttonSignUp.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SignUpView.this.name = SignUpView.this.inputName.getText();
        SignUpView.this.tag = SignUpView.this.inputTag.getText();
        SignUpView.this.password = new String(SignUpView.this.inputPassword.getPassword());
        if (SignUpView.this.signUpController.signUp(SignUpView.this.name, SignUpView.this.tag, SignUpView.this.password, SignUpView.this.avatarPath)) {
          System.out.println("utilisateur créé");

          SignUpView.this.tag = "";
          SignUpView.this.name = "";
          SignUpView.this.password = "";
          SignUpView.this.avatarPath = "";
          SignUpView.this.inputTag.setText("");
          SignUpView.this.inputName.setText("");
          SignUpView.this.inputPassword.setText("");
          SignUpView.this.imgAvatarPanel.removeAll();
          SignUpView.this.imgAvatarPanel.revalidate();
          SignUpView.this.imgAvatarPanel.repaint();
        } else {
          System.err.println("Ce tag est déj pris par un autre utilisateur !");
        }
      }
    });

    // Bouton Déjà un compte ?
    JButton buttonSignIn = new JButton("Déjà un compte ?");
    buttonsGrid.add(buttonSignIn, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
    buttonSignIn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SignUpView.this.signUpController.switchToSignInView();
      }
    });
    formPanel.add(buttonsGrid, new GridBagConstraints(0, 4, 2, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

    this.add(formPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

    this.setVisible(true);
  }

  private void selectAvatar() {
    JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png"));
    chooser.setAcceptAllFileFilterUsed(false);

    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      this.avatarPath = chooser.getSelectedFile().getAbsolutePath();
      JLabel imgAvatar = new JLabel(new ImageIcon(new ImageIcon(this.avatarPath).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
      this.imgAvatarPanel.removeAll();
      this.imgAvatarPanel.add(imgAvatar, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
          GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
      this.imgAvatarPanel.revalidate();
      this.imgAvatarPanel.repaint();
    }
  }
}
