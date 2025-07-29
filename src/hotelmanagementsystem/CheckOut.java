package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class CheckOut extends JFrame implements ActionListener{

    JButton bcheckout, bback, bdetails;
    Choice ccustomerid;
    JLabel  lblcheckouttime, lblcheckintime;
    JTextField tfroomnumber, tfname, tfpaid, tfpending, tfcheckin;

    CheckOut(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel tick = new JLabel(i3);
        tick.setBounds(370, 80, 20, 20);
        add(tick);

        JLabel text = new JLabel("Checkout");
        text.setBounds(100, 20, 100, 30);
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setForeground(Color.BLUE);
        add(text);

        JLabel customerid = new JLabel("Customer Id");
        customerid.setFont(new Font("Tahoma", Font.PLAIN, 18));
        customerid.setBounds(30, 80, 120, 30);
        add(customerid);

        ccustomerid = new Choice();
        ccustomerid.setBounds(210, 80, 150, 35);
        add(ccustomerid);
        try{
            Conn c = new Conn();
            Connection conn = c.getConnection();

            String query1 = "SELECT * FROM customer ";
            PreparedStatement pst = conn.prepareStatement(query1);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                ccustomerid.add(rs.getString("number"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel name = new JLabel("Name");
        name.setFont(new Font("Tahoma", Font.PLAIN, 18));
        name.setBounds(30, 130, 120, 30);
        add(name);

        tfroomnumber = new JTextField();
        tfroomnumber.setBounds(210, 130, 150, 30);
        add(tfroomnumber);

        JLabel rnumber = new JLabel("Room Number");
        rnumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
        rnumber.setBounds(30, 180, 120, 30);
        add(rnumber);

        tfname = new JTextField();
        tfname.setBounds(210, 180, 150, 30);
        add(tfname);

        JLabel checkin = new JLabel("Check-in Time");
        checkin.setFont(new Font("Tahoma", Font.PLAIN, 18));
        checkin.setBounds(30, 230, 120, 30);
        add(checkin);

        tfcheckin = new JTextField();
        tfcheckin.setBounds(210, 230, 150, 30);
        add(tfcheckin);

//        try{
//            Conn c = new Conn();
//            Connection conn = c.getConnection();
//
//            String query3 = "SELECT * FROM customer";
//            PreparedStatement pst = conn.prepareStatement(query3);
//            ResultSet rs = pst.executeQuery();
//            while(rs.next()){
//                lblcheckintime.setText(rs.getString("checkintime"));
//            }
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }

        JLabel checkout = new JLabel("Check-out Time");
        checkout.setFont(new Font("Tahoma", Font.PLAIN, 18));
        checkout.setBounds(30, 280, 120, 30);
        add(checkout);

        Date date = new Date();
        lblcheckouttime = new JLabel("" + date);
        lblcheckouttime.setBounds(210, 280,150, 30 );
        add(lblcheckouttime);

        JLabel paid = new JLabel("Amount Paid");
        paid.setFont(new Font("Tahoma", Font.PLAIN, 18));
        paid.setBounds(30, 330, 120, 30);
        add(paid);

        tfpaid = new JTextField();
        tfpaid.setBounds(210, 330, 150, 30);
        add(tfpaid);

        JLabel pending = new JLabel("Pending Amount");
        pending.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pending.setBounds(30, 380, 150, 30);
        add(pending);

        tfpending = new JTextField();
        tfpending.setBounds(210, 380, 150, 30);
        add(tfpending);

        bdetails = new JButton("View Details");
        bdetails.setBounds(30, 450, 120, 30);
        bdetails.setBackground(Color.BLACK);
        bdetails.setForeground(Color.WHITE);
        bdetails.addActionListener(this);
        add(bdetails);

        bcheckout = new JButton("Checkout");
        bcheckout.setBounds(170, 450, 120, 30);
        bcheckout.setBackground(Color.BLACK);
        bcheckout.setForeground(Color.WHITE);
        bcheckout.addActionListener(this);
        add(bcheckout);

        bback = new JButton("Back");
        bback.setBounds(300, 450, 120, 30);
        bback.setBackground(Color.BLACK);
        bback.setForeground(Color.WHITE);
        bback.addActionListener(this);
        add(bback);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        Image i5 = i4.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image = new JLabel(i6);
        image.setBounds(450, 50, 400, 250);
        add(image);


        setBounds(150, 120, 980, 550);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == bdetails){
            String id = ccustomerid.getSelectedItem();
            String query4 = "SELECT * FROM customer WHERE number = '"+id+"'";

            try{
                Conn c = new Conn();
                Connection conn = c.getConnection();
                PreparedStatement pst = conn.prepareStatement(query4);
                ResultSet rs = pst.executeQuery();

                while(rs.next()){
                    tfroomnumber.setText(rs.getString("room"));
                    tfname.setText(rs.getString("name"));
                    tfcheckin.setText(rs.getString("checkintime"));
                    tfpaid.setText(rs.getString("deposit"));
                }

                String query2 = "SELECT * FROM room WHERE roomnumber = '"+tfroomnumber.getText()+"'";
                PreparedStatement pst1 = conn.prepareStatement(query2);
                ResultSet rs1 = pst1.executeQuery();

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
        else if(ae.getSource() == bcheckout){

            String query5 = ("DELETE FROM customer WHERE number = '"+ccustomerid.getSelectedItem()+"'");
            String query6 = ("UPDATE room SET availability = 'Available' WHERE roomnumber = '"+tfroomnumber.getText()+"'");

            try{
                Conn c = new Conn();
                Connection conn = c.getConnection();

                PreparedStatement pst = conn.prepareStatement(query5);
                pst.executeUpdate();

                PreparedStatement pst1 = conn.prepareStatement(query6);
                pst1.executeUpdate();

                JOptionPane.showMessageDialog(null, "customer has been checked out successfully");
                setVisible(false);
                new Reception();

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String [] args){
        new CheckOut();
    }
}
