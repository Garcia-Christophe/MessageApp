package main.java.com.ubo.tp.message.ihm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static java.lang.System.exit;

/**
 * Classe de la vue principale de l'application.
 */
public class MessageAppMainView {

  /**
   * Fenetre de l'application.
   */
  protected JFrame mFrame;

  protected JPanel panelCenter;

  protected JPanel panelNorth;

  protected String getExchangeDirectoryPath() {
    String exchangeDirectoryPath = null;

    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int returnVal = chooser.showOpenDialog(this.mFrame);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      exchangeDirectoryPath = chooser.getSelectedFile().getAbsolutePath();
    } else if (returnVal == JFileChooser.CANCEL_OPTION) {
      exit(0);
    }

    return exchangeDirectoryPath;
  }

  /**
   * Initialisation de l'IHM
   */
  protected void initGUI() {
    // Création de la fenetre principale
    this.mFrame = new JFrame("MessageApp");
    ImageIcon logo = null;
    try {
      Image image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("logo_50.png")));
      logo = new ImageIcon(image);
      this.mFrame.setIconImage(image);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Setup panels
    this.panelCenter = new JPanel();
    this.panelCenter.setLayout(new GridBagLayout());
    this.mFrame.add(this.panelCenter, BorderLayout.CENTER);
    this.panelNorth = new JPanel();
    this.panelNorth.setLayout(new GridBagLayout());
    this.mFrame.add(this.panelNorth, BorderLayout.NORTH);

    // Ajout du menu
    JMenuBar menubar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem menuItemQuitter = new JMenuItem("Quitter");
    try {
      Image image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("exitIcon_20.png")));
      menuItemQuitter.setIcon(new ImageIcon(image));
    } catch (Exception e) {
      e.printStackTrace();
    }
    menuItemQuitter.setToolTipText("Fermer l'application");
    menuItemQuitter.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        exit(0);
      }
    });

    JMenuItem menuItemAPropos = new JMenuItem("?");
    ImageIcon finalLogo = logo;
    menuItemAPropos.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        JOptionPane.showMessageDialog(MessageAppMainView.this.mFrame,
            "<html>" +
                "<p style=\"text-align: center\">UBO M2-TIIL</p>" +
                "<p style=\"text-align: center\">Département Informatique</p>" +
                "</html>", "A propos",
            JOptionPane.PLAIN_MESSAGE, finalLogo);
      }
    });
    menu.add(menuItemQuitter);
    menubar.add(menu);
    menubar.add(menuItemAPropos);
    this.mFrame.setJMenuBar(menubar);

    // Affichage
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    this.mFrame.setSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
    this.mFrame.setLocationRelativeTo(null);
    this.mFrame.setVisible(true);
  }

  public void showCenter(JPanel panelLeft, JPanel panelRight) {
    this.panelCenter.removeAll();
    this.panelCenter.add(panelLeft, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 5, 0, 5), 0, 0));
    if (panelRight != null) {
      this.panelCenter.add(panelRight, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
          GridBagConstraints.BOTH, new Insets(0, 5, 0, 5), 0, 0));
    }
    this.panelCenter.revalidate();
    this.panelCenter.repaint();
  }

  public void showNorth(JPanel panel) {
    this.panelNorth.removeAll();
    this.panelNorth.add(panel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    this.panelNorth.revalidate();
    this.panelNorth.repaint();
  }
}
