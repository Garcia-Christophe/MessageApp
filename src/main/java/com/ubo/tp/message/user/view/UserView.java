package main.java.com.ubo.tp.message.user.view;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.user.controller.UserFollowupController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserView extends JPanel implements MouseListener {

  protected JPanel followPanel;

  protected JButton followBtn, unfollowBtn;

  protected UserFollowupController userFollowupController;

  protected Color defaultBackgroundColor;

  protected LineBorder defaultLineBorder;

  public UserView(User user, UserFollowupController controller, boolean ownProfile, boolean followed) {
    this.userFollowupController = controller;
    this.setLayout(new GridBagLayout());
    this.addMouseListener(this);

    // bordures
    this.setOpaque(true);
    if (ownProfile) {
      this.defaultLineBorder = new LineBorder(Color.BLACK, 1, true);
      this.defaultBackgroundColor = Color.CYAN;
    } else {
      this.defaultLineBorder = new LineBorder(Color.BLACK, 1, true);
      this.defaultBackgroundColor = Color.WHITE;
    }
    this.setBorder(this.defaultLineBorder);
    this.setBackground(this.defaultBackgroundColor);

    // avatar
    JLabel imgAvatar = new JLabel(new ImageIcon(new ImageIcon(user.getAvatarPath()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
    imgAvatar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
    this.add(imgAvatar, new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.WEST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

    JPanel identitePanel = new JPanel();
    identitePanel.setLayout(new GridBagLayout());
    identitePanel.setOpaque(false);

    // nom
    JLabel name = new JLabel(user.getName());
    identitePanel.add(name, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
        GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

    // tag
    JLabel tag = new JLabel("#" + user.getUserTag());
    tag.setBackground(Color.red);
    identitePanel.add(tag, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

    this.add(identitePanel, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    // Bouton d'abonnement
    followBtn = new JButton("S'abonner");
    followBtn.addMouseListener(this);
    followBtn.setBackground(this.defaultBackgroundColor);
    followBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (UserView.this.userFollowupController.follow(user)) {
          UserView.this.switchFollowState(true);
        } else {
          System.err.println("Vous suivez déjà cet utilisateur.");
        }
      }
    });

    // Bouton de désabonnement
    unfollowBtn = new JButton("Se désabonner");
    unfollowBtn.addMouseListener(this);
    unfollowBtn.setBackground(this.defaultBackgroundColor);
    unfollowBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (UserView.this.userFollowupController.unfollow(user)) {
          UserView.this.switchFollowState(false);
        } else {
          System.err.println("Vous êtes déjà désabonné de cet utilisateur.");
        }
      }
    });

    followPanel = new JPanel();
    followPanel.setLayout(new GridBagLayout());
    this.add(followPanel, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.EAST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));

    // init state
    if (!ownProfile) {
      switchFollowState(followed);
    }

    this.setVisible(true);
  }

  public void switchFollowState(boolean followed) {
    followPanel.removeAll();
    followPanel.add(followed ? unfollowBtn : followBtn, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    followPanel.revalidate();
    followPanel.repaint();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // rien
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // rien
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // rien
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    this.setBackground(Color.lightGray);
    this.followBtn.setBackground(Color.lightGray);
    this.unfollowBtn.setBackground(Color.lightGray);
    this.setBorder(new LineBorder(Color.RED, 1, true));
  }

  @Override
  public void mouseExited(MouseEvent e) {
    this.setBackground(this.defaultBackgroundColor);
    this.followBtn.setBackground(this.defaultBackgroundColor);
    this.unfollowBtn.setBackground(this.defaultBackgroundColor);
    this.setBorder(this.defaultLineBorder);
  }
}
