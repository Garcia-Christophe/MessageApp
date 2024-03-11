package main.java.com.ubo.tp.message.message.view;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MessageView extends JPanel {

  public MessageView(Message message, boolean ownMessage) {
    this.setLayout(new GridBagLayout());
    this.removeAll();

    // bordures
    this.setOpaque(true);
    this.setBorder(new LineBorder(Color.BLACK, 1, true));
    this.setBackground(ownMessage ? Color.CYAN : Color.WHITE);

    // nom + tag
    User user = message.getSender();
    JLabel userDetails = new JLabel(user.getName() + " (" + user.getUserTag() + ")", SwingConstants.LEFT);
    Font boldFont = new Font(userDetails.getFont().getName(), Font.BOLD, userDetails.getFont().getSize());
    userDetails.setFont(boldFont);
    this.add(userDetails, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

    // date
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = new Date(message.getEmissionDate());
    JLabel dateMsg = new JLabel(date.toString(), SwingConstants.RIGHT);
    this.add(dateMsg, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

    // contenu
    JLabel content = new JLabel(message.getText());
    this.add(content, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.EAST,
        GridBagConstraints.BOTH, new Insets(10, 0, 0, 0), 0, 0));

    this.setVisible(true);
  }

}
