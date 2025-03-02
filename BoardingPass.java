package airline.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BoardingPass extends JFrame implements ActionListener {
    JTextField tfpnr;
    JLabel tfname, tfnationality, lblsrc, lbldest, labelfname, labelfcode, labeldate;
    JButton fetchButton;
    
    public BoardingPass() {
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Load the background image (pass.jpg) and create a BackgroundPanel with 50% opacity
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/airline/management/system/icons/pass.jpg"));
        Image bgImg = bgIcon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        BackgroundPanel background = new BackgroundPanel(bgImg, 0.5f); // 50% opacity
        setContentPane(background);
        background.setLayout(null);
        
        // Heading: "AIR INDIA" centered
        JLabel heading = new JLabel("AIR INDIA", SwingConstants.CENTER);
        heading.setBounds(200, 10, 400, 40);
        heading.setFont(new Font("Tahoma", Font.BOLD, 32));
        heading.setForeground(Color.BLACK);
        background.add(heading);
        
        // Subheading: "Boarding Pass" centered and dark grey
        JLabel subheading = new JLabel("Boarding Pass", SwingConstants.CENTER);
        subheading.setBounds(200, 60, 400, 30);
        subheading.setFont(new Font("Tahoma", Font.BOLD, 24));
        subheading.setForeground(Color.DARK_GRAY);
        background.add(subheading);
        
        // Row 1: PNR DETAILS label, text field, and "Enter" button
        JLabel lblpnr = new JLabel("PNR DETAILS");
        lblpnr.setBounds(50, 110, 150, 25);
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        background.add(lblpnr);
        
        tfpnr = new JTextField();
        tfpnr.setBounds(210, 110, 150, 25);
        tfpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        background.add(tfpnr);
        
        fetchButton = new JButton("Enter");
        fetchButton.setBounds(370, 110, 100, 25);
        fetchButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.addActionListener(this);
        background.add(fetchButton);
        
        // Passenger Info Rows
        addLabelAndValue(background, "NAME", 150, tfname = new JLabel());
        addLabelAndValue(background, "NATIONALITY", 190, tfnationality = new JLabel());
        addLabelAndValue(background, "SRC", 230, lblsrc = new JLabel());
        addLabelAndValue(background, "DEST", 270, lbldest = new JLabel());
        addLabelAndValue(background, "Flight Name", 310, labelfname = new JLabel());
        addLabelAndValue(background, "Flight Code", 350, labelfcode = new JLabel());
        addLabelAndValue(background, "DATE", 390, labeldate = new JLabel());
        
        setVisible(true);
    }
    
    // Helper method to add labels and corresponding value labels
    private void addLabelAndValue(JPanel panel, String text, int y, JLabel label) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(50, y, 150, 25);
        lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(lbl);
        
        label.setBounds(210, y, 150, 25);
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(label);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String pnr = tfpnr.getText();
        try {
            Conn conn = new Conn();
            String query = "select * from reservation where PNR = '" + pnr + "'";
            ResultSet rs = conn.s.executeQuery(query);
            if (rs.next()) {
                tfname.setText(rs.getString("name")); 
                tfnationality.setText(rs.getString("nationality")); 
                lblsrc.setText(rs.getString("src")); 
                lbldest.setText(rs.getString("des"));  
                labelfname.setText(rs.getString("flightname"));  
                labelfcode.setText(rs.getString("flightcode"));  
                labeldate.setText(rs.getString("ddate")); 
            } else {
                JOptionPane.showMessageDialog(null, "Please enter correct PNR");                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new BoardingPass();
    }
}

// Custom BackgroundPanel class to paint a background image with reduced opacity
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
