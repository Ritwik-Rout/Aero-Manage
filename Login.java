package airline.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton submit, reset, close;
    JTextField tfusername;
    JPasswordField tfpassword;

    public Login() {
        setTitle("Login");
        setLayout(null);
        // New smaller frame dimensions
        setSize(800, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. Load the original image from resources
        ImageIcon originalIcon = new ImageIcon(
            getClass().getResource("/airline/management/system/icons/background.jpg")
        );
        Image originalImage = originalIcon.getImage();

        // 2. Scale the image exactly to 800×500 so it fully fills the frame
        int frameWidth = 800;
        int frameHeight = 500;
        Image scaledImage = originalImage.getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, frameWidth, frameHeight);
        background.setLayout(null);
        add(background);

        // 3. Reposition the login panel so it appears under the plane wing
        // New bounds calculated to look proportional in the 800×500 frame.
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(80, 170, 300, 280);
        loginPanel.setBackground(new Color(255, 255, 255, 180));
        background.add(loginPanel);

        // Fonts
        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);

        // Username label
        JLabel lblusername = new JLabel("Username:");
        lblusername.setBounds(40, 40, 100, 25);
        lblusername.setFont(labelFont);
        loginPanel.add(lblusername);

        // Username text field
        tfusername = new JTextField();
        tfusername.setBounds(140, 40, 130, 30);
        tfusername.setFont(fieldFont);
        loginPanel.add(tfusername);

        // Password label
        JLabel lblpassword = new JLabel("Password:");
        lblpassword.setBounds(40, 80, 100, 25);
        lblpassword.setFont(labelFont);
        loginPanel.add(lblpassword);

        // Password field
        tfpassword = new JPasswordField();
        tfpassword.setBounds(140, 80, 130, 30);
        tfpassword.setFont(fieldFont);
        loginPanel.add(tfpassword);

        // Submit button (Blue)
        submit = new JButton("Submit");
        submit.setBounds(160, 130, 100, 40);
        submit.setFont(buttonFont);
        submit.setForeground(Color.WHITE);
        submit.setBackground(new Color(0, 123, 255));
        submit.setFocusPainted(false);
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit.setBorder(new RoundedBorder(10));
        submit.addActionListener(this);
        loginPanel.add(submit);

        // Reset button (Gray)
        reset = new JButton("Reset");
        reset.setBounds(40, 130, 100, 40);
        reset.setFont(buttonFont);
        reset.setForeground(Color.WHITE);
        reset.setBackground(new Color(108, 117, 125));
        reset.setFocusPainted(false);
        reset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        reset.setBorder(new RoundedBorder(10));
        reset.addActionListener(this);
        loginPanel.add(reset);

        // Close button (Red)
        close = new JButton("Close");
        close.setBounds(100, 190, 100, 40);
        close.setFont(buttonFont);
        close.setForeground(Color.WHITE);
        close.setBackground(new Color(220, 53, 69));
        close.setFocusPainted(false);
        close.setCursor(new Cursor(Cursor.HAND_CURSOR));
        close.setBorder(new RoundedBorder(10));
        close.addActionListener(this);
        loginPanel.add(close);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String username = tfusername.getText();
            String password = new String(tfpassword.getPassword());
            try {
                Conn c = new Conn();
                String query = "SELECT * FROM login WHERE username='" + username + "' AND password='" + password + "'";
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    new Home();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == close) {
            setVisible(false);
        } else if (ae.getSource() == reset) {
            tfusername.setText("");
            tfpassword.setText("");
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}

// Custom RoundedBorder class remains unchanged
class RoundedBorder implements javax.swing.border.Border {
    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.GRAY);
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}
