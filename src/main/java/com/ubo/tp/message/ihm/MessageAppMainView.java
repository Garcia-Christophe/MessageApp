package main.java.com.ubo.tp.message.ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;

/**
 * Classe de la vue principale de l'application.
 */
public class MessageAppMainView {

  /**
   * Fenetre de l'application.
   */
  protected JFrame mFrame;

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
    ImageIcon logo = new ImageIcon("src/main/resources/images/logo_50.png");
    this.mFrame.setIconImage(logo.getImage());
    this.mFrame.setLayout(new GridBagLayout());

    // Ajout du menu
    JMenuBar menubar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem menuItemQuitter = new JMenuItem("Quitter");
    menuItemQuitter.setIcon(new ImageIcon("src/main/resources/images/exitIcon_20.png"));
    menuItemQuitter.setToolTipText("Fermer l'application");
    menuItemQuitter.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        exit(0);
      }
    });

    JMenuItem menuItemAPropos = new JMenuItem("?");
    menuItemAPropos.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        JOptionPane.showMessageDialog(MessageAppMainView.this.mFrame,
            "<html>" +
                "<div style=\"display: flex; flex-direction: column; justify-content: center;\">" +
                "<p style=\"text-align: center\">UBO M2-TIIL</p>" +
                "<p style=\"text-align: center\">Département Informatique</p>" +
                "</div>" +
                "</html>", "A propos",
            JOptionPane.PLAIN_MESSAGE, logo);
      }
    });
    menu.add(menuItemQuitter);
    menubar.add(menu);
    menubar.add(menuItemAPropos);
    this.mFrame.setJMenuBar(menubar);

    // Affichage
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    this.mFrame.setMinimumSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
    this.mFrame.setLocationRelativeTo(null);
    this.mFrame.setVisible(true);
    this.mFrame.pack();
  }
}
