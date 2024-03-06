package main.java.com.ubo.tp.message.sign.view;

import javax.swing.*;
import java.awt.*;

public class SignView extends JPanel {

  public SignView() {
    this.setLayout(new BorderLayout());
    this.setVisible(true);
  }

  public void show(JPanel panel) {
    this.removeAll();
    this.add(panel, BorderLayout.CENTER);
    this.revalidate();
    this.repaint();
  }

}
