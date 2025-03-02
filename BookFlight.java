package airline.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class BookFlight extends JFrame implements ActionListener {

    JTextField tfaadhar;
    JLabel tfname, tfaddress, tfnationality, labelgender, labelfname, labelfcode;
    Choice source, destination;
    JButton bookflight, fetchButton, flight;
    JDateChooser dcdate;

    public BookFlight() {
        // Set frame size and center it
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the background image as the content pane
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/airline/management/system/icons/airindia.png"));
        Image bgImg = bgIcon.getImage().getScaledInstance(1000, 650, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(bgImg));
        setContentPane(background);
        background.setLayout(null);

        // Heading: Bold and centered near top
        JLabel heading = new JLabel("BOOK FLIGHT");
        heading.setBounds(350, 10, 300, 40);
        heading.setFont(new Font("Arial Black", Font.BOLD, 32));
        heading.setForeground(Color.BLACK);
        background.add(heading);

        // Row 1: Aadhar Number and Fetch User button (y = 70)
        JLabel lblaadhar = new JLabel("Aadhar Number");
        lblaadhar.setBounds(60, 70, 120, 25);
        lblaadhar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(190, 70, 200, 25);
        tfaadhar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(tfaadhar);

        fetchButton = new JButton("Fetch User");
        fetchButton.setBounds(410, 70, 150, 30);
        fetchButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        fetchButton.setBackground(new Color(64, 64, 64));
        fetchButton.setForeground(Color.WHITE);
        fetchButton.addActionListener(this);
        background.add(fetchButton);

        // Row 2: Name (y = 110)
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 110, 120, 25);
        lblname.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(lblname);

        tfname = new JLabel();
        tfname.setBounds(190, 110, 200, 25);
        tfname.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(tfname);

        // Row 3: Nationality (y = 150)
        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 150, 120, 25);
        lblnationality.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(lblnationality);

        tfnationality = new JLabel();
        tfnationality.setBounds(190, 150, 200, 25);
        tfnationality.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(tfnationality);

        // Row 4: Address (y = 190)
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 190, 120, 25);
        lbladdress.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(lbladdress);

        tfaddress = new JLabel();
        tfaddress.setBounds(190, 190, 200, 25);
        tfaddress.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(tfaddress);

        // Row 5: Gender (y = 230)
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 230, 120, 25);
        lblgender.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(lblgender);

        labelgender = new JLabel();
        labelgender.setBounds(190, 230, 200, 25);
        labelgender.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(labelgender);

        // Row 6: Source (y = 270)
        JLabel lblsource = new JLabel("Source");
        lblsource.setBounds(60, 270, 120, 25);
        lblsource.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(lblsource);

        source = new Choice();
        source.setBounds(190, 270, 200, 25);
        background.add(source);

        // Row 7: Destination (y = 310)
        JLabel lbldest = new JLabel("Destination");
        lbldest.setBounds(60, 310, 120, 25);
        lbldest.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(lbldest);

        destination = new Choice();
        destination.setBounds(190, 310, 200, 25);
        background.add(destination);

        // Fetch Flights button (aligned with Destination row, y = 310)
        flight = new JButton("Fetch Flights");
        flight.setBackground(new Color(64, 64, 64));
        flight.setForeground(Color.WHITE);
        flight.setBounds(410, 310, 150, 30);
        flight.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        flight.addActionListener(this);
        background.add(flight);

        // Row 8: Flight Name (y = 350)
        JLabel lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60, 350, 120, 25);
        lblfname.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(lblfname);

        labelfname = new JLabel();
        labelfname.setBounds(190, 350, 200, 25);
        labelfname.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(labelfname);

        // Row 9: Flight Code (y = 390)
        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(60, 390, 120, 25);
        lblfcode.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(lblfcode);

        labelfcode = new JLabel();
        labelfcode.setBounds(190, 390, 200, 25);
        labelfcode.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(labelfcode);

        // Row 10: Date of Travel (y = 430)
        JLabel lbldate = new JLabel("Date of Travel");
        lbldate.setBounds(60, 430, 120, 25);
        lbldate.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        background.add(lbldate);

        dcdate = new JDateChooser();
        dcdate.setBounds(190, 430, 200, 25);
        background.add(dcdate);

        // Book Flight button: Centered horizontally, moved up to y = 470
        bookflight = new JButton("Book Flight");
        bookflight.setBackground(Color.BLACK);
        bookflight.setForeground(Color.WHITE);
        // Centered: (1000 - 180)/2 = 410, y = 470, width = 180, height = 35
        bookflight.setBounds(410, 470, 180, 35);
        bookflight.setFont(new Font("Arial", Font.PLAIN, 18));
        bookflight.addActionListener(this);
        background.add(bookflight);

        // Populate flights in choices
        try {
            Conn c = new Conn();
            String query = "select * from flight";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                source.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String aadhar = tfaadhar.getText();
            try {
                Conn conn = new Conn();
                String query = "select * from passenger where aadhar = '" + aadhar + "'";
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    tfname.setText(rs.getString("name"));
                    tfnationality.setText(rs.getString("nationality"));
                    tfaddress.setText(rs.getString("address"));
                    labelgender.setText(rs.getString("gender"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct aadhar");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == flight) {
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();
            try {
                Conn conn = new Conn();
                String query = "select * from flight where source = '" + src + "' and destination = '" + dest + "'";
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    labelfname.setText(rs.getString("f_name"));
                    labelfcode.setText(rs.getString("f_code"));
                } else {
                    JOptionPane.showMessageDialog(null, "No Flights Found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Random random = new Random();
            String aadhar = tfaadhar.getText();
            String name = tfname.getText();
            String nationality = tfnationality.getText();
            String flightname = labelfname.getText();
            String flightcode = labelfcode.getText();
            String src = source.getSelectedItem();
            String des = destination.getSelectedItem();
            String ddate = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
            try {
                Conn conn = new Conn();
                String query = "insert into reservation values('PNR-" + random.nextInt(1000000) + "', 'TIC-" + random.nextInt(10000) + "', '" + aadhar + "', '" + name + "', '" + nationality + "', '" + flightname + "', '" + flightcode + "', '" + src + "', '" + des + "', '" + ddate + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Ticket Booked Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}
