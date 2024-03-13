package main.java.com.ubo.tp.message.ui_javafx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.sign.controller.SignOutController;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FxMainController {

  @FXML
  private ListView<GridPane> users_list;

  protected SignOutController signOutController;

  public FxMainController(IDatabase database, SignOutController signOutController) {
    this.signOutController = signOutController;

    Platform.runLater(() -> {
      for (User user : database.getUsers()) {
        try {
          URL url = getClass().getClassLoader().getResource("fxml_user_view.fxml");
          FXMLLoader loaderUserView = new FXMLLoader(url);
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
