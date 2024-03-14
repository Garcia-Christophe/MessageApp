package main.java.com.ubo.tp.message.ui_javafx;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.com.ubo.tp.message.sign.controller.SignUpController;

public class FxSignUpController {

  @FXML
  protected TextField tagField;

  @FXML
  protected TextField nameField;

  @FXML
  protected PasswordField passwordField;

  protected SignUpController signUpController;

  public FxSignUpController(SignUpController signUpController) {
    this.signUpController = signUpController;
  }

  @FXML public void signUp() {
    if (nameField.getText() == null || nameField.getText().isEmpty() || tagField.getText() == null || tagField.getText().isEmpty() || passwordField.getText() == null || passwordField.getText().isEmpty()) {
      System.err.println("Champs manquants !");
    } else {
      this.signUpController.signUp(nameField.getText(), tagField.getText(), passwordField.getText(), null);
    }
  }

  @FXML public void switchToSignInView() {
    this.signUpController.switchToSignInView();
  }
}
