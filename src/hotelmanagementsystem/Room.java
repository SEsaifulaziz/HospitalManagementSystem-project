package hotelmanagementsystem;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Room extends JFrame implements ActionListener{

    JTable table;
    JButton back;
    Room(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500, 0, 600, 600);
        add(image);

        JLabel number = new JLabel("Room Number");
        number.setBounds(10, 12, 100, 20);
        add(number);

        JLabel availability = new JLabel("availability");
        availability.setBounds(120, 12, 100, 20);
        add(availability);

        JLabel status = new JLabel("Status");
        status.setBounds(220, 12, 100, 20);
        add(status);

        JLabel price = new JLabel("Price");
        price.setBounds(330, 12, 100, 20);
        add(price);

        JLabel type = new JLabel("Bed Type");
        type.setBounds(410, 12, 100, 20);
        add(type);

        table = new JTable();
        table.setBounds(0, 40, 500, 400);
        add(table);

        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            String query = "SELECT * FROM room ";

            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            e.printStackTrace();
        }

        back = new JButton("Back");
        back.setBounds(200, 500, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);


        setBounds(150, 50, 1050, 600);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == back){
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String args []){
        new Room();
    }
}
