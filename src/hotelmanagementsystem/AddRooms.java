package hotelmanagementsystem;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;


public class AddRooms extends JFrame implements ActionListener{

    JTextField tfrno, tfprice;
    JButton addroom, cancel;
    JComboBox availablerooms, cleancombo ,bedtype;

    AddRooms (){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setBounds(170, 140, 940, 470);


        JLabel title = new JLabel("Add Rooms");
        title.setFont(new Font("Tahoma", Font.PLAIN, 20));
        title.setBounds(150, 20 ,200, 20);
        add(title);

        JLabel lblrno = new JLabel("Room Number");
        lblrno.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblrno.setBounds(60, 80, 120, 20);
        add(lblrno);

        tfrno = new JTextField();
        tfrno.setBounds(200, 80, 150, 30);
        add(tfrno);

        JLabel lblavailablerooms = new JLabel("Available");
        lblavailablerooms.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblavailablerooms.setBounds(60, 130, 120, 20);
        add(lblavailablerooms);

        String availableOptions [] = {"Available", "Occupied"};
        availablerooms = new JComboBox(availableOptions);
        availablerooms.setBounds(200, 130, 150, 30);
        availablerooms.setBackground(Color.WHITE);
        add(availablerooms);

        JLabel lblclean = new JLabel("Cleaning Status");
        lblclean.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblclean.setBounds(60, 180, 150, 20);
        add(lblclean);

        String cleanOption [] = {"Cleaned", "Dirty"};
        cleancombo = new JComboBox(cleanOption);
        cleancombo.setBounds(200, 180, 150, 30);
        cleancombo.setBackground(Color.WHITE);
        add(cleancombo);

        JLabel lblprice = new JLabel("Price");
        lblprice.setFont(new Font("tahoma", Font.PLAIN, 16));
        lblprice.setBounds(60, 230, 150, 20);
        add(lblprice);

        tfprice = new JTextField();
        tfprice.setBounds(200, 230, 150, 30);
        add(tfprice);

        JLabel lbltype = new JLabel("Bed Type");
        lbltype.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbltype.setBounds(60, 280, 150, 20);
        add(lbltype);

        String type [] = {"Single", "Double"};
        bedtype = new JComboBox(type);
        bedtype.setBounds(200, 280, 150, 30);
        bedtype.setBackground(Color.WHITE);
        add(bedtype);

        addroom = new JButton("Add Room");
        addroom.setBounds(60, 350, 130, 30);
        addroom.setForeground(Color.WHITE);
        addroom.setBackground(Color.BLACK);
        addroom.addActionListener(this);
        add(addroom);

        cancel = new JButton("Cancel");
        cancel.setBounds(220, 350, 130, 30);
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));

        JLabel image = new JLabel(i1);
        image.setBounds(400, 30, 500, 300);
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == addroom){
            String roomnumber = tfrno.getText();
            String availability = (String) availablerooms.getSelectedItem();
            String status = (String) cleancombo.getSelectedItem();
            String price = tfprice.getText();
            String type = (String) bedtype.getSelectedItem();

            try{
                Conn c = new Conn();
                Connection conn = c.getConnection();

                String query = "INSERT INTO room (roomnumber, availability, status, price, type) VALUES (?,?,?,?,?)";

                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, roomnumber);
                pst.setString(2, availability);
                pst.setString(3, status);
                pst.setString(4, price);
                pst.setString(5, type);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Room Added Successfully");


            }catch(Exception e){
                e.printStackTrace();
            }

        }
        else{
            setVisible(false);
        }
    }

    public static void main(String [] args){
        new AddRooms();

    }
}
