package airline.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {  
    public Home() {
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(getClass().getResource("/airline/management/system/icons/front.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1600, 800);
        add(image);
        
        JLabel heading = new JLabel("AIR INDIA WELCOMES YOU");
        heading.setBounds(400, 20, 1000, 40);
        heading.setForeground(Color.white);  // Font color change
        heading.setFont(new Font("Tahoma", Font.BOLD, 46));
        image.add(heading);
        
        // Menu
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        JMenu details = new JMenu("Details");
        menubar.add(details); // Adding Details in menubar
        
        JMenuItem flightDetails = new JMenuItem("Flight Details");
        flightDetails.addActionListener(this);
        details.add(flightDetails);
        
        JMenuItem customerDetails = new JMenuItem("Add Customer Details");
        customerDetails.addActionListener(this);
        details.add(customerDetails);
        
        JMenuItem bookFlight = new JMenuItem("Book Flight");
        bookFlight.addActionListener(this);
        details.add(bookFlight);
        
        JMenuItem reservationDetails = new JMenuItem("Reservation Details");
        details.add(reservationDetails);
        
        JMenuItem journeyDetails = new JMenuItem("Journey Details");
        journeyDetails.addActionListener(this);
        details.add(journeyDetails);  // Corrected: Added journeyDetails to 'details'
        
        JMenuItem ticketCancellation = new JMenuItem("Cancel Ticket");
        ticketCancellation.addActionListener(this);
        details.add(ticketCancellation);
        
        JMenu ticket = new JMenu("Ticket");
        menubar.add(ticket); // Adding Ticket in menubar
        
        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        boardingPass.addActionListener(this);
        ticket.add(boardingPass);

        setExtendedState(JFrame.MAXIMIZED_BOTH); // Max view
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {  
        String text = ae.getActionCommand();
        
        if(text.equals("Add Customer Details")){
            new AddCustomer();
        } else if(text.equals("Flight Details")){
            new FlightInfo();
        } else if(text.equals("Book Flight")){
            new BookFlight();
        } else if(text.equals("Journey Details")){
            new JourneyDetails();
        } else if(text.equals("Cancel Ticket")){
            new Cancel();
        }else if(text.equals("Boarding Pass")){
            new BoardingPass();
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
