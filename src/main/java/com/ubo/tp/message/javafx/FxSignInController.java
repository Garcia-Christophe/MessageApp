package main.java.com.ubo.tp.message.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.com.ubo.tp.message.sign.controller.SignInController;

public class FxSignInController {

  @FXML
  protected TextField tagField;

  @FXML
  protected PasswordField passwordField;

  protected SignInController signInController;

  public FxSignInController(SignInController signInController) {
    this.signInController = signInController;
  }

  @FXML public void signIn() {
    if (this.signInController.signIn(tagField.getText(), passwordField.getText())) {
      System.out.println("Utilisateur connecté avec succès.");
    } else {
      System.out.println("Utilisateur inconnu ! ");
    }
  }

  @FXML public void switchToSignUpView() {
    this.signInController.switchToSignUpView();
  }
}
