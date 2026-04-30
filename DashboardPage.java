import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class DashboardPage extends JFrame {

    private final Color bgColor = new Color(20, 20, 20);
    private final Color cardColor = new Color(35, 35, 35);
    private final Color orange = new Color(255, 140, 0);
    private final Color textColor = new Color(220, 220, 220);

    public DashboardPage(String username) {

        setTitle("InternShield Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(bgColor);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // ================= HEADER =================
        JLabel title = new JLabel("Fake Internship Message Detector");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JLabel welcome = new JLabel("Welcome, " + username);
        welcome.setForeground(textColor);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(bgColor);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(title, BorderLayout.CENTER);
       // topPanel.add(welcome, BorderLayout.EAST);

        // ================= CENTER CARD =================
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(cardColor);
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(60, 60, 60), 1, true),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel instruction = new JLabel("Paste Internship Message Below:");
        instruction.setFont(new Font("Segoe UI", Font.BOLD, 16)); // BOLD here
        instruction.setForeground(textColor);
        instruction.setAlignmentX(Component.CENTER_ALIGNMENT);


        JTextArea messageArea = new JTextArea(3, 25); 
        messageArea.setMaximumSize(new Dimension(300, 80)); 
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setBackground(new Color(50, 50, 50));
        messageArea.setForeground(Color.WHITE);
        messageArea.setCaretColor(Color.WHITE);
        messageArea.setBorder(new EmptyBorder(10,10,10,10));

        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setBorder(null);              
        scrollPane.setViewportBorder(null);     

       // scrollPane.setBorder(new LineBorder(new Color(80, 80, 80)));
        messageArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setMaximumSize(new Dimension(400, 450));
        scrollPane.setPreferredSize(new Dimension(400, 450));

        JButton checkBtn = new JButton("Check Message");
        checkBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkBtn.setBackground(orange);
        checkBtn.setForeground(Color.WHITE);
        checkBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        checkBtn.setFocusPainted(false);
        checkBtn.setBorderPainted(false);  // removes border line
        checkBtn.setBorder(null);          // removes default border
        checkBtn.setFont(new Font("Segoe UI", Font.BOLD, 16)); // bigger & bold text
        checkBtn.setMaximumSize(new Dimension(220, 50));       // increase size
        checkBtn.setPreferredSize(new Dimension(220, 50));     // ensure size


        JLabel resultLabel = new JLabel("Result: ");
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= LOGIC =================
        checkBtn.addActionListener(e -> {
            String text = messageArea.getText().toLowerCase();

            if (text.contains("pay") || text.contains("registration fee") || text.contains("urgent")) {
                resultLabel.setText("⚠ Suspicious Message (Possible Scam)");
                resultLabel.setForeground(Color.RED);
            } else {
                resultLabel.setText("✅ Safe Message");
                resultLabel.setForeground(Color.GREEN);
            }
        });

        // ================= LOGOUT =================
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(orange);
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setPreferredSize(new Dimension(200, 40));
        logoutBtn.setMaximumSize(new Dimension(200, 40));
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        logoutBtn.addActionListener(e -> {
            new ModernLoginPage().setVisible(true);
            dispose();
        });

        // ================= ADD COMPONENTS =================
        card.add(instruction);
        card.add(Box.createVerticalStrut(10));
        card.add(scrollPane);
        card.add(Box.createVerticalStrut(20));
        card.add(checkBtn);
        card.add(Box.createVerticalStrut(20));
        card.add(resultLabel);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(card, BorderLayout.CENTER);
        mainPanel.add(logoutBtn, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new DashboardPage("Test User").setVisible(true);
    });
}
}


