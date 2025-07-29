package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddDriver extends JFrame  implements ActionListener{

    JTextField tfname, tfage, tfcarcompany, tfcarbrand, tflocation;
    JComboBox combogender, comboavailable;
    JButton addDriver, cancel;

    AddDriver(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setBounds(170, 120, 980, 470);

        JLabel lbladdDriver = new JLabel("Add Drivers");
        lbladdDriver.setFont(new Font("Tahoma", Font.PLAIN, 20 ));
        lbladdDriver.setBounds(150, 20, 200, 30);
        add(lbladdDriver);

        JLabel name = new JLabel("Name");
        name.setFont(new Font("Tahoma", Font.PLAIN, 16));
        name.setBounds(60, 80, 200, 30);
        add(name);

        tfname = new JTextField();
        tfname.setBounds(200, 80, 150, 30);
        add(tfname);

        JLabel age = new JLabel("Age");
        age.setFont(new Font("Tahoma", Font.PLAIN, 16));
        age.setBounds(60, 120, 200, 30);
        add(age);

        tfage = new JTextField();
        tfage.setBounds(200, 120, 150, 30);
        add(tfage);

        JLabel gender = new JLabel("Gender");
        gender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gender.setBounds(60, 160, 150, 30);
        add(gender);

        String cgender [] = {"Male", "Female"};
        combogender = new JComboBox(cgender);
        combogender.setBounds(200, 160, 150, 30);
        combogender.setBackground(Color.WHITE);
        add(combogender);

        JLabel carcompany = new JLabel("Car Company");
        carcompany.setFont(new Font("Tahoma", Font.PLAIN, 16));
        carcompany.setBounds(60, 200, 200, 30);
        add(carcompany);

        tfcarcompany = new JTextField();
        tfcarcompany.setBounds(200, 200, 150, 30);
        add(tfcarcompany);

        JLabel carbrand = new JLabel("Car Brand");
        carbrand.setFont(new Font("Tahoma", Font.PLAIN, 16));
        carbrand.setBounds(60, 240, 200,  30);
        add(carbrand);

        tfcarbrand = new JTextField();
        tfcarbrand.setBounds(200, 240, 150, 30);
        add(tfcarbrand);

        JLabel available = new JLabel("Available");
        available.setFont(new Font("Tahoma", Font.PLAIN, 16));
        available.setBounds(60, 280, 200,  30);
        add(available);

        String cavailable [] = {"Available", "Busy"};
        comboavailable = new JComboBox(cavailable);
        comboavailable.setBounds(200, 280, 150, 30);
        comboavailable.setBackground(Color.WHITE);
        add(comboavailable);

        JLabel location = new JLabel("Location");
        location.setFont(new Font("Tahoma", Font.PLAIN, 16));
        location.setBounds(60, 320, 200,  30);
        add(location);

        tflocation = new JTextField();
        tflocation.setBounds(200, 320, 150, 30);
        add(tflocation);

        addDriver = new JButton("Add Driver");
        addDriver.setBounds(60, 370, 130, 30);
        addDriver.setForeground(Color.WHITE);
        addDriver.setBackground(Color.BLACK);
        addDriver.addActionListener(this);
        add(addDriver);

        cancel = new JButton("Cancel");
        cancel.setBounds(220, 370, 130, 30);
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 30, 500, 300);
        add(image);

        setVisible(true);


    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == addDriver){
            String name = tfname.getText();
            String age= tfage.getText();
            String gender = (String) combogender.getSelectedItem();
            String company = tfcarcompany.getText();
            String brand = tfcarbrand.getText();
            String available = (String) comboavailable.getSelectedItem();
            String location = tflocation.getText();

            try{
                Conn c = new Conn();
                Connection conn = c.getConnection();

                String query = "INSERT INTO driver (name, age, gender, company, brand, availability, location) VALUES(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, name);
                pst.setString(2, age);
                pst.setString(3, gender);
                pst.setString(4, company);
                pst.setString(5, brand);
                pst.setString(6, available);
                pst.setString(7, location);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null,"Driver, Added Successfully");

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else {
            setVisible(false);
        }
    }
    public static void main(String [] args){
        new AddDriver();
    }
}


