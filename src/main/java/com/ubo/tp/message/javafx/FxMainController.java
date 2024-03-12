package main.java.com.ubo.tp.message.javafx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.sign.controller.SignInController;
import main.java.com.ubo.tp.message.sign.controller.SignOutController;

import java.io.IOException;

public class FxMainController {

  @FXML
  private ListView<GridPane> users_list;

  protected SignOutController signOutController;

  public FxMainController(IDatabase database, SignOutController signOutController) {
    this.signOutController = signOutController;

    Platform.runLater(() -> {
      for (User user : database.getUsers()) {
        try {
          FXMLLoader loaderUserView = new FXMLLoader(getClass().getResource("./fxml_user_view.fxml"));
          GridPane userView = loaderUserView.load();
          FxUserViewController fxUserViewController = loaderUserView.getController();
          fxUserViewController.initialize(user.getAvatarPath(), user.getName(), user.getUserTag());
          users_list.getItems().add(userView);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  @FXML public void signOut() {
    this.signOutController.signOut();
  }
}
