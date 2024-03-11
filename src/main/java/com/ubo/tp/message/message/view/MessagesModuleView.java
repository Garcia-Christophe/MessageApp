package main.java.com.ubo.tp.message.message.view;

import main.java.com.ubo.tp.message.datamodel.Message;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MessagesModuleView extends JPanel {

  protected JPanel listPanel, inputPanel, searchPanel;

  public MessagesModuleView() {
    this.setLayout(new GridBagLayout());
    this.setVisible(true);
  }

  public void initGUI() {
    // titre
    JLabel title = new JLabel("Messages", SwingConstants.CENTER);
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

    // panel pour l'input
    inputPanel = new JPanel();
    inputPanel.setLayout(new GridBagLayout());
    this.add(inputPanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.SOUTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
  }

  public void setNorth(JPanel panel) {
    searchPanel.removeAll();
    searchPanel.add(panel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
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

  public void setSouth(JPanel panel) {
    inputPanel.removeAll();
    inputPanel.add(panel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    inputPanel.revalidate();
    inputPanel.repaint();
  }

}
