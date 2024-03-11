package main.java.com.ubo.tp.message.user;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.sign.view.SignOutView;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class UserView extends JPanel {

  protected JPanel followPanel;

  protected JButton followBtn, unfollowBtn;

  protected UserFollowupController userFollowupController;

  public UserView(User user, UserFollowupController controller, boolean ownProfile, boolean followed) {
    this.userFollowupController = controller;
    this.setLayout(new GridBagLayout());

    // bordures
    this.setOpaque(true);
    this.setBorder(new LineBorder(ownProfile ? Color.CYAN : Color.BLACK, 1, true));

    // avatar
    JLabel imgAvatar = new JLabel(new ImageIcon(new ImageIcon(user.getAvatarPath()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
    this.add(imgAvatar, new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.WEST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

    JPanel identitePanel = new JPanel();
    identitePanel.setLayout(new GridBagLayout());

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

}
