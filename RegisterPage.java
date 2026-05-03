import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisterPage extends JFrame {

    public RegisterPage() {

        setTitle("Register");
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(20,20,20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Create Account");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField userField = new JTextField();
        userField.setMaximumSize(new Dimension(300,40));

        JPasswordField passField = new JPasswordField();
        passField.setMaximumSize(new Dimension(300,40));

        JButton registerBtn = new JButton("Register");
        styleBtn(registerBtn);

        registerBtn.addActionListener(e -> {
            try {
                Connection con = DBConnection.getConnection();

                String q = "INSERT INTO users (username,password) VALUES (?,?)";
                PreparedStatement ps = con.prepareStatement(q);

                ps.setString(1, userField.getText());
                ps.setString(2, new String(passField.getPassword()));

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Registered Successfully");

                new ModernLoginPage().setVisible(true);
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "User already exists");
            }
        });

        panel.add(Box.createVerticalStrut(30));
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));
        panel.add(label("Username"));
        panel.add(userField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(label("Password"));
        panel.add(passField);
        panel.add(Box.createVerticalStrut(20));
        panel.add(registerBtn);

        add(panel);
    }

    private JLabel label(String t){
        JLabel l=new JLabel(t);
        l.setForeground(Color.WHITE);
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        return l;
    }

    private void styleBtn(JButton b){
        b.setBackground(new Color(255,140,0));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setMaximumSize(new Dimension(200,40));
    }
}