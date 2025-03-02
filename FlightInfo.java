package airline.management.system;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class FlightInfo extends JFrame {

    public FlightInfo() {
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);

        // Title label
        JLabel titleLabel = new JLabel("✈ FLIGHT INFORMATION", SwingConstants.CENTER);
        titleLabel.setBounds(50, 10, 800, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.ORANGE);
        add(titleLabel);

        // Flight details table
        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(255, 165, 0)); // Orange highlight
        table.setSelectionForeground(Color.BLACK);

        // Customizing table header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        header.setBackground(Color.ORANGE);
        header.setForeground(Color.BLACK);

        try {
            Conn conn = new Conn();
            String query = "select * from flight";
            ResultSet rs = conn.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

        // Centering the frame
        int frameWidth = 900;
        int frameHeight = 550;
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setBounds(x, y, frameWidth, frameHeight);

        // Positioning the scroll pane
        jsp.setBounds(50, 70, frameWidth - 100, frameHeight - 120);
        add(jsp);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlightInfo());
    }
}
