package main.java.com.ubo.tp.message.user;

import javax.swing.*;
import java.awt.*;

public class UsersModuleView extends JPanel {

  protected JPanel listPanel, searchPanel;

  public UsersModuleView() {
    this.setLayout(new GridBagLayout());
    this.setVisible(true);
  }

  public void initGUI() {
    // titre
    JLabel title = new JLabel("Utilisateurs", SwingConstants.CENTER);
    Font boldFont = new Font(title.getFont().getName(), Font.BOLD, 32);
    title.setFont(boldFont);
    this.add(title, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

    // panel pour le search
    searchPanel = new JPanel();
    searchPanel.setLayout(new GridBagLayout());
    this.add(searchPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

    // panel pour listView
    listPanel = new JPanel();
    listPanel.setLayout(new GridBagLayout());
    this.add(listPanel, new GridBagConstraints(0, 2, 1, 1, 1, 10, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
  }

  public void setNorth(JPanel panel) {
    searchPanel.removeAll();
    searchPanel.add(panel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    searchPanel.revalidate();
    searchPanel.repaint();
  }

  public void setCenter(JPanel panel) {
    listPanel.removeAll();
    listPanel.add(panel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    listPanel.revalidate();
    listPanel.repaint();
  }

}
