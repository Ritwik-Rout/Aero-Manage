package airline.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCustomer extends JFrame implements ActionListener {
    JTextField tfname, tfnationality, tfaadhar, tfaddress, tfphone;
    JRadioButton rbmale, rbfemale;
    JButton save;

    public AddCustomer() {
        setTitle("Add Passenger");
        setSize(750, 500);  // Compact frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Load the background image and set it as the content pane using BackgroundPanel
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/airline/management/system/icons/passenger.jpg"));
        Image bgImage = bgIcon.getImage().getScaledInstance(750, 500, Image.SCALE_SMOOTH);
        BackgroundPanel background = new BackgroundPanel(bgImage, 0.5f);  // 50% opacity
        setContentPane(background);
        background.setLayout(null);

        // Create a semi-transparent panel for form components
        JPanel formPanel = new JPanel();
        formPanel.setBounds(50, 50, 650, 380);
        formPanel.setBackground(new Color(255, 255, 255, 220));  // White with slight transparency
        formPanel.setLayout(null);
        background.add(formPanel);

        // Heading
        JLabel heading = new JLabel("ADD PASSENGER DETAILS", SwingConstants.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(new Color(0, 51, 102)); // Dark blue
        heading.setBounds(0, 10, 650, 40);
        formPanel.add(heading);

        // Form fields with consistent spacing
        Font labelFont = new Font("Tahoma", Font.BOLD, 16);
        Font inputFont = new Font("Tahoma", Font.PLAIN, 16);
        int labelX = 50, inputX = 220, compWidth = 180, compHeight = 30;
        int yPosition = 70, gap = 35;

        JLabel lblname = new JLabel("Full Name:");
        lblname.setFont(labelFont);
        lblname.setBounds(labelX, yPosition, compWidth, compHeight);
        formPanel.add(lblname);

        tfname = new JTextField();
        tfname.setFont(inputFont);
        tfname.setBounds(inputX, yPosition, compWidth, compHeight);
        formPanel.add(tfname);

        yPosition += gap;
        JLabel lblnationality = new JLabel("Nationality:");
        lblnationality.setFont(labelFont);
        lblnationality.setBounds(labelX, yPosition, compWidth, compHeight);
        formPanel.add(lblnationality);

        tfnationality = new JTextField();
        tfnationality.setFont(inputFont);
        tfnationality.setBounds(inputX, yPosition, compWidth, compHeight);
        formPanel.add(tfnationality);

        yPosition += gap;
        JLabel lblaadhar = new JLabel("Aadhar No:");
        lblaadhar.setFont(labelFont);
        lblaadhar.setBounds(labelX, yPosition, compWidth, compHeight);
        formPanel.add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setFont(inputFont);
        tfaadhar.setBounds(inputX, yPosition, compWidth, compHeight);
        formPanel.add(tfaadhar);

        yPosition += gap;
        JLabel lbladdress = new JLabel("Address:");
        lbladdress.setFont(labelFont);
        lbladdress.setBounds(labelX, yPosition, compWidth, compHeight);
        formPanel.add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setFont(inputFont);
        tfaddress.setBounds(inputX, yPosition, compWidth, compHeight);
        formPanel.add(tfaddress);

        yPosition += gap;
        JLabel lblgender = new JLabel("Gender:");
        lblgender.setFont(labelFont);
        lblgender.setBounds(labelX, yPosition, compWidth, compHeight);
        formPanel.add(lblgender);

        rbmale = new JRadioButton("Male");
        rbmale.setFont(inputFont);
        rbmale.setBackground(new Color(255, 255, 255, 220));
        rbmale.setBounds(inputX, yPosition, 80, compHeight);
        formPanel.add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setFont(inputFont);
        rbfemale.setBackground(new Color(255, 255, 255, 220));
        rbfemale.setBounds(inputX + 90, yPosition, 100, compHeight);
        formPanel.add(rbfemale);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbmale);
        genderGroup.add(rbfemale);

        yPosition += gap;
        JLabel lblphone = new JLabel("Phone No:");
        lblphone.setFont(labelFont);
        lblphone.setBounds(labelX, yPosition, compWidth, compHeight);
        formPanel.add(lblphone);

        tfphone = new JTextField();
        tfphone.setFont(inputFont);
        tfphone.setBounds(inputX, yPosition, compWidth, compHeight);
        formPanel.add(tfphone);

        // Save Button (Centered in the form panel)
        int buttonWidth = 150;
        int buttonX = (650 - buttonWidth) / 2;
        JButton save = new JButton("SAVE");
        save.setFont(new Font("Tahoma", Font.BOLD, 16));
        save.setBackground(new Color(0, 102, 204));
        save.setForeground(Color.WHITE);
        save.setBounds(buttonX, yPosition + 50, buttonWidth, 35);
        save.addActionListener(this);
        formPanel.add(save);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String name = tfname.getText();
        String nationality = tfnationality.getText();
        String aadhar = tfaadhar.getText();
        String address = tfaddress.getText();
        String phone = tfphone.getText();
        String gender = rbmale.isSelected() ? "Male" : "Female";

        try {
            Conn conn = new Conn();
            String query = "INSERT INTO passenger VALUES('" + name + "','" + nationality + "','" + phone + "','" + address + "','" + aadhar + "','" + gender + "')";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}

// Custom BackgroundPanel to paint the image as the full background with reduced opacity
class BackgroundPanel extends JPanel {
    private Image backgroundImage;
    private float opacity;
    
    public BackgroundPanel(Image image, float opacity) {
        this.backgroundImage = image;
        this.opacity = opacity;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
}
