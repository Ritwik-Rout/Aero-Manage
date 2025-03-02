package airline.management.system;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;

public class JourneyDetails extends JFrame implements ActionListener {
    JTable table;
    JTextField pnr;
    JButton show;
    
    public JourneyDetails(){
        // Set a light, airline-inspired background color
        getContentPane().setBackground(new Color(245, 245, 245)); 
        setLayout(null);
        
        // Title label at the top
        JLabel titleLabel = new JLabel("Journey Details", SwingConstants.CENTER);
        titleLabel.setBounds(0, 10, 800, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(new Color(0, 51, 102));  // Dark blue
        add(titleLabel);
        
        // Row for PNR input
        JLabel lblpnr = new JLabel("PNR Details:");
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpnr.setForeground(new Color(0, 51, 102));
        lblpnr.setBounds(30, 60, 120, 25);
        add(lblpnr);
        
        pnr = new JTextField();
        pnr.setBounds(160, 60, 120, 25);
        pnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(pnr);
        
        show = new JButton("Show Details");
        show.setBackground(new Color(0, 51, 102)); // Dark blue
        show.setForeground(Color.WHITE);
        show.setFont(new Font("Tahoma", Font.PLAIN, 16));
        show.setBounds(300, 60, 140, 25);
        show.addActionListener(this);
        add(show);
        
        // Flight details table setup
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(25);
        
        // Customize table header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        header.setBackground(new Color(0, 51, 102));
        header.setForeground(Color.WHITE);
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(30, 100, 740, 200);
        jsp.setBackground(Color.WHITE);
        jsp.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        add(jsp);
        
        // Set frame size and center it on screen
        setSize(800, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from reservation where PNR = '" + pnr.getText() + "'");
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found");
                return;
            }
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        new JourneyDetails();
    }
}
