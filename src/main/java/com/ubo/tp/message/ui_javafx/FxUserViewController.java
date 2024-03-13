package main.java.com.ubo.tp.message.ui_javafx;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class FxUserViewController {

  @FXML
  protected ImageView user_avatar;

  @FXML
  protected Text user_name;

  @FXML
  protected Text user_tag;

 public void initialize(String avatarPath, String name, String tag) {
   if (avatarPath != null) {
     user_avatar.setImage(new Image("file:" + avatarPath));
     user_avatar.setPreserveRatio(true);
     user_avatar.setFitWidth(100);
   }
   user_name.setText(name);
   user_tag.setText(tag);
  }
}
