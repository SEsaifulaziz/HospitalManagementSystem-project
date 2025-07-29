package hotelmanagementsystem;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class CustomerInfo extends JFrame implements ActionListener{

    JTable table;
    JButton back;

    CustomerInfo(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel document = new JLabel("Identity");
        document.setBounds(10, 12, 100, 20);
        add(document);

        JLabel number = new JLabel("Number");
        number.setBounds(160, 12, 100, 20);
        add(number);

        JLabel name = new JLabel("Name");
        name.setBounds(290, 12, 100, 20);
        add(name);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(410, 12, 100, 20);
        add(gender);

        JLabel country = new JLabel("Country");
        country.setBounds(510, 12, 100, 20);
        add(country);

        JLabel roomnumber = new JLabel("Room Number");
        roomnumber.setBounds(640, 12, 100, 20);
        add(roomnumber);

        JLabel checkinTime = new JLabel("Check-in Time");
        checkinTime.setBounds(760, 12, 100, 20);
        add(checkinTime);

        JLabel deposit = new JLabel("Deposit");
        deposit.setBounds(910, 12, 100, 20);
        add(deposit);


        table = new JTable();
        table.setBounds(0, 50, 1000, 400);
        add(table);

        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            String query = "SELECT * FROM customer ";

            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            e.printStackTrace();
        }

        back = new JButton("Back");
        back.setBounds(420, 500, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);


        setBounds(150, 50, 1000, 600);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == back){
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String args []){
        new CustomerInfo();
    }
}

