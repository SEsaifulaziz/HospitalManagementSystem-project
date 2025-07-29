package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateStatus extends JFrame implements ActionListener {

    JLabel text, lblid, lblroomnumber, lblname, lblcheckin, lblpaid, lblpending;
    Choice ccustomer;
    JTextField tfroomnumber, tfname, tfcheckin, tfpaid, tfpending;
    JButton bcheck, bupdate, bback;


    UpdateStatus(){
       getContentPane().setBackground(Color.WHITE);
       setLayout(null);

        text = new JLabel("Update Status");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(90, 20, 200, 30);
        text.setForeground(Color.BLACK);
        add(text);

        lblid = new JLabel("Customer Id");
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblid.setBounds(30, 80, 100, 18);
        lblid.setForeground(Color.BLACK);
        add(lblid);

        ccustomer = new Choice();
        ccustomer.setBounds(200, 80, 150 ,25);
        add(ccustomer);

        try{
            Conn c = new Conn();
            Connection conn = c.getConnection();

            String query  = "SELECT * FROM customer ";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                ccustomer.add(rs.getString("number"));
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        lblroomnumber = new JLabel("Room Number");
        lblroomnumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblroomnumber.setBounds(30, 130, 150, 20);
        add(lblroomnumber);

        tfroomnumber = new JTextField();
        tfroomnumber.setBounds(200, 130, 150, 25);
        add(tfroomnumber);

        lblname = new JLabel("name");
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblname.setBounds(30, 180, 150, 20);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 180, 150, 25);
        add(tfname);

        lblcheckin = new JLabel("Checkin Time");
        lblcheckin.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblcheckin.setBounds(30, 230, 150, 20);
        add(lblcheckin);

        tfcheckin = new JTextField();
        tfcheckin.setBounds(200, 230, 150, 25);
        add(tfcheckin);

        lblpaid = new JLabel("Amount paid");
        lblpaid.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblpaid.setBounds(30, 280, 150, 20);
        add(lblpaid);

        tfpaid = new JTextField();
        tfpaid.setBounds(200, 280, 150, 25);
        add(tfpaid);

        lblpending = new JLabel("Pending Amount");
        lblpending.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblpending.setBounds(30, 330, 150, 20);
        add(lblpending);

        tfpending = new JTextField();
        tfpending.setBounds(200, 330, 150, 25);
        add(tfpending);

        bcheck = new JButton("Check");
        bcheck.setBounds(30, 400, 100, 30);
        bcheck.setBackground(Color.BLACK);
        bcheck.setForeground(Color.WHITE);
        bcheck.addActionListener(this);
        add(bcheck);

        bupdate = new JButton("Update");
        bupdate.setBounds(150, 400, 100, 30);
        bupdate.setBackground(Color.BLACK);
        bupdate.setForeground(Color.WHITE);
        bupdate.addActionListener(this);
        add(bupdate);

        bback = new JButton("Back");
        bback.setBounds(270, 400, 100, 30);
        bback.setBackground(Color.BLACK);
        bback.setForeground(Color.WHITE);
        bback.addActionListener(this);
        add(bback);

        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 50, 500, 300);
        add(image);

        setBounds(150, 120, 980, 550);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == bcheck){
            String id = ccustomer.getSelectedItem();
            String query = "SELECT * FROM customer WHERE number = '"+id+"' ";

            try{
                Conn c = new Conn();
                Connection conn = c.getConnection();
                PreparedStatement pst = conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery();

                while(rs.next()){
                    tfroomnumber.setText(rs.getString("room"));
                    tfname.setText(rs.getString("name"));
                    tfcheckin.setText(rs.getString("checkintime"));
                    tfpaid.setText(rs.getString("deposit"));

                }

                String query1 = "SELECT * FROM room WHERE roomnumber = '"+tfroomnumber.getText()+ "'";
                PreparedStatement pst1 = conn.prepareStatement(query1);
                ResultSet rs1 = pst1.executeQuery(); // executeQuery() is for DDL commands and executeUpdate() is for DML commands

                while(rs1.next()){
                    String price = rs1.getString("price");
                    int amountPaid = Integer.parseInt(price) - Integer.parseInt(tfpaid.getText());
                    tfpending.setText("" + amountPaid);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == bupdate){
            String number = ccustomer.getSelectedItem();
            String room = tfroomnumber.getText();
            String name = tfname.getText();
            String checkintime = tfcheckin.getText();
            String deposit = tfpaid.getText();

            try{
                Conn c = new Conn();
                Connection conn = c.getConnection();

                String query2 = "UPDATE customer SET room  = '"+room+"', name  = '"+name+"', checkintime = '"+checkintime+"', deposit = '"+deposit+"' WHERE number = '"+number+"' ";
                PreparedStatement pst2 = conn.prepareStatement(query2);
                pst2.executeUpdate();

                JOptionPane.showMessageDialog(null,"Data Updated Successfully");

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
        new UpdateStatus();
    }

}
