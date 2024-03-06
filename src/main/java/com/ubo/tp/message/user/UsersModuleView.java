package main.java.com.ubo.tp.message.user;

import javax.swing.*;
import java.awt.*;

public class UsersModuleView extends JPanel {

  protected JPanel listPanel, inputPanel;

  public UsersModuleView() {
    this.setLayout(new GridBagLayout());
    this.removeAll();

    // titre
    JLabel title = new JLabel("Utilisateurs", SwingConstants.CENTER);
    Font boldFont = new Font(title.getFont().getName(), Font.BOLD, 32);
    title.setFont(boldFont);
    this.add(title, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

    // panel pour listView
    listPanel = new JPanel();
    listPanel.setLayout(new GridBagLayout());
    this.add(listPanel, new GridBagConstraints(0, 1, 1, 1, 1, 10, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    // panel pour l'input
    inputPanel = new JPanel();
    inputPanel.setLayout(new GridBagLayout());
    this.add(inputPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.SOUTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

    this.setVisible(true);
  }

  public void setCenter(JPanel panel) {
    listPanel.removeAll();
    listPanel.add(panel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    listPanel.revalidate();
    listPanel.repaint();
  }

  public void setSouth(JPanel panel) {
    inputPanel.removeAll();
    inputPanel.add(panel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    inputPanel.revalidate();
    inputPanel.repaint();
  }

}
