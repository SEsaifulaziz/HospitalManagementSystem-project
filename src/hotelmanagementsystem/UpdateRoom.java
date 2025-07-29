package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateRoom extends JFrame implements ActionListener {

    JLabel text, customerid, room, availability, status;
    JTextField tfroom, tfavailability, tfstatus;
    JButton check, update, back;
    Choice ccustomer;

    UpdateRoom(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        text = new JLabel("Update Room Status");
        text.setBounds(30 ,40, 300,30);
        text.setFont(new Font("Arial", Font.PLAIN, 30));
        text.setForeground(Color.BLUE);
        add(text);

        customerid = new JLabel("Customer Id");
        customerid.setBounds(30, 120, 120, 30);
        customerid.setFont(new Font("Arial", Font.PLAIN, 18));
        add(customerid);

        ccustomer = new Choice();
        ccustomer.setBounds(200,120, 150, 40);
        add(ccustomer);

        try{
            Conn c = new Conn();
            Connection conn = c.getConnection();

            String query = "SELECT * FROM customer";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                ccustomer.add(rs.getString("number"));
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        room = new JLabel("Room Number");
        room.setBounds(30, 170, 120, 30);
        room.setFont(new Font("Arial", Font.PLAIN, 18));
        add(room);

        tfroom = new JTextField();
        tfroom.setBounds(200, 170, 150, 30);
        add(tfroom);

        availability = new JLabel("Availability");
        availability.setBounds(30, 220, 120, 30);
        availability.setFont(new Font("Arial", Font.PLAIN, 18));
        add(availability);

        tfavailability = new JTextField();
        tfavailability.setBounds(200, 220, 150, 30);
        add(tfavailability);

        status = new JLabel("Cleaning Status");
        status.setBounds(30, 270, 150, 30);
        status.setFont(new Font("Arial", Font.PLAIN, 18));
        add(status);

        tfstatus = new JTextField();
        tfstatus.setBounds(200, 270, 150, 30);
        add(tfstatus);

        tfroom = new JTextField();
        tfroom.setBounds(200, 170, 150, 30);
        add(tfroom);

        check = new JButton("Check");
        check.setBounds(30, 380, 120, 30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.addActionListener(this);
        add(check);

        update = new JButton("Update");
        update.setBounds(170, 380, 120, 30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(310, 380, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 500, 300);
        add(image);


        setBounds(150, 50, 980, 500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == check){
            String id = ccustomer.getSelectedItem();
            String query = "SELECT * FROM customer WHERE number = '"+id+"'";

            try{
                Conn c = new Conn();
                Connection conn = c.getConnection();
                PreparedStatement pst = conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery(query);

                while(rs.next()){
                    tfroom.setText(rs.getString("room"));
                }

                String query1 = "SELECT * FROM room WHERE roomnumber = '"+tfroom.getText()+"'";
                PreparedStatement pst1 = conn.prepareStatement(query1);
                ResultSet rs1 = pst1.executeQuery(query1);

                while(rs1.next()){
                    tfavailability.setText(rs1.getString("availability"));
                    tfstatus.setText(rs1.getString("status"));
                }

            }
            catch(Exception e){
                e.printStackTrace();
            }

        }
        else if(ae.getSource() == update){
            String number = ccustomer.getSelectedItem();
            String room = tfroom.getText();
            String available = tfavailability.getText();
            String status = tfstatus.getText();
            try{
                Conn c = new Conn();
                Connection conn = c.getConnection();

                String query2 = "UPDATE room SET availability = '"+available+"', status = '"+status+"' WHERE roomnumber = '"+room+"' ";
                PreparedStatement pst2 = conn.prepareStatement(query2);
                pst2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Room Updated Successfully");
                setVisible(false);
                new Reception();

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String [] args){
        new UpdateRoom();
    }
}
