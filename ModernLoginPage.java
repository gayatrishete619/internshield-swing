import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModernLoginPage extends JFrame {

    private final Color backgroundColorStart = new Color(15, 15, 15);
    private final Color backgroundColorEnd = new Color(40, 40, 40);
    private final Color cardColor = new Color(30, 30, 30);
    private final Color orangeColor = new Color(255, 140, 0);
    private final Color textColor = new Color(220, 220, 220);
    private final Color fieldColor = new Color(45, 45, 45);

    public ModernLoginPage() {
        setTitle("Modern Login - InternShield");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth(), h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, backgroundColorStart, w, h, backgroundColorEnd);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };

        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(cardColor);
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setPreferredSize(new Dimension(400, 500));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(60, 60, 60), 1, true),
                new EmptyBorder(40, 50, 40, 50)
        ));

        JLabel titleLabel = new JLabel("InternShield Login");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userLabel = createLabel("Username");
        JTextField userField = createTextField();

        JLabel passLabel = createLabel("Password");
        JPasswordField passField = createPasswordField();

        JButton loginButton = createButton("Login");

        // ✅ DATABASE LOGIN LOGIC
        loginButton.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            try {
                Connection con = DBConnection.getConnection();
                String query = "SELECT * FROM users WHERE username=? AND password=?";
                PreparedStatement pst = con.prepareStatement(query);

                pst.setString(1, username);
                pst.setString(2, password);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");
                    new DashboardPage(username).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Credentials");
                }

                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // ✅ REGISTER BUTTON
        JButton registerButton = createButton("Register");
        registerButton.addActionListener(e -> {
            new RegisterPage().setVisible(true);
        });

        // UI Layout
        cardPanel.add(titleLabel);
        cardPanel.add(Box.createVerticalStrut(30));
        cardPanel.add(userLabel);
        cardPanel.add(Box.createVerticalStrut(5));
        cardPanel.add(userField);
        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(passLabel);
        cardPanel.add(Box.createVerticalStrut(5));
        cardPanel.add(passField);
        cardPanel.add(Box.createVerticalStrut(30));
        cardPanel.add(loginButton);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(registerButton);

        mainPanel.add(cardPanel);
        add(mainPanel);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(textColor);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        field.setBackground(fieldColor);
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(80, 80, 80), 1),
                new EmptyBorder(0, 10, 0, 10)
        ));
        return field;
    }

    private JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        field.setBackground(fieldColor);
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(80, 80, 80), 1),
                new EmptyBorder(0, 10, 0, 10)
        ));
        return field;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(200, 45));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(orangeColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ModernLoginPage().setVisible(true);
        });
    }
}