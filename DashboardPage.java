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

        // ================= TOP BAR =================
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(bgColor);

        JLabel logo = new JLabel("🛡 InternShield");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 22)); // bigger
        logo.setForeground(Color.WHITE);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(orange);
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setPreferredSize(new Dimension(120, 35));

        logoutBtn.addActionListener(e -> {
            new ModernLoginPage().setVisible(true);
            dispose();
        });

        topPanel.add(logo, BorderLayout.WEST);
        topPanel.add(logoutBtn, BorderLayout.EAST);

        // ================= CARD =================
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(cardColor);
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(60, 60, 60), 1, true),
                new EmptyBorder(30, 30, 30, 30)
        ));

        JLabel instruction = new JLabel("Paste Internship Message Below:");
        instruction.setFont(new Font("Segoe UI", Font.BOLD, 18));
        instruction.setForeground(textColor);
        instruction.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ===== BIGGER TEXT AREA =====
        JTextArea messageArea = new JTextArea(8, 40);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setBackground(new Color(50, 50, 50));
        messageArea.setForeground(Color.WHITE);
        messageArea.setCaretColor(Color.WHITE);
        messageArea.setBorder(new EmptyBorder(15,15,15,15));

        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setBorder(null);

        // CENTERING the box properly
        JPanel textWrapper = new JPanel();
        textWrapper.setBackground(cardColor);
        textWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
        scrollPane.setPreferredSize(new Dimension(650, 220)); // bigger + centered
        textWrapper.add(scrollPane);

        // ===== BUTTON =====
        JButton checkBtn = new JButton("Check Message");
        checkBtn.setBackground(orange);
        checkBtn.setForeground(Color.WHITE);
        checkBtn.setFocusPainted(false);
        checkBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        checkBtn.setPreferredSize(new Dimension(220, 45));

        JPanel btnWrapper = new JPanel();
        btnWrapper.setBackground(cardColor);
        btnWrapper.add(checkBtn);

        // ===== RESULT =====
        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JPanel resultWrapper = new JPanel();
        resultWrapper.setBackground(cardColor);
        resultWrapper.add(resultLabel);

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

        // ================= ADD =================
        card.add(instruction);
        card.add(Box.createVerticalStrut(20));
        card.add(textWrapper); // centered box
        card.add(Box.createVerticalStrut(25));
        card.add(btnWrapper); // centered button
        card.add(Box.createVerticalStrut(20));
        card.add(resultWrapper); // centered result

        // ================= FOOTER =================
        JLabel footer = new JLabel("© 2026 InternShield | Stay Safe Online");
        footer.setForeground(Color.GRAY);
        footer.setHorizontalAlignment(SwingConstants.CENTER);

        // ================= FINAL =================
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(card, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);
    }
}