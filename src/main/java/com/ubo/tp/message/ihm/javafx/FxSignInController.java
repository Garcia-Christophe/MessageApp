package main.java.com.ubo.tp.message.ihm.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.com.ubo.tp.message.sign.controller.SignInController;

import java.io.IOException;

public class FxSignInController {

  @FXML
  protected TextField tagField;

  @FXML
  protected PasswordField passwordField;

  protected SignInController signInController;

  protected FXMLLoader nextView;

  public FxSignInController(SignInController signInController, FXMLLoader nextView) {
    this.signInController = signInController;
    this.nextView = nextView;
  }

  @FXML public void signIn() throws IOException {
    if (this.signInController.signIn(tagField.getText(), passwordField.getText())) {
      // switch view
      Parent root = nextView.load();
      Stage stage = (Stage) tagField.getScene().getWindow();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } else {
      System.out.println("Utilisateur inconnu ! ");
    }
  }
}
