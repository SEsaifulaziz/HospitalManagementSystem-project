package hotelmanagementsystem;
import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import net.proteanit.sql.*;


public class AddCustomer extends JFrame implements ActionListener{

    JButton addCustomer, back;
    JComboBox cid, cRoomNumber;
    JRadioButton rmale, rfemale;
    JTextField tfnumber, tfname, tfcountry, tfdeposit;
    Choice croom;
    JLabel checkintime;


    AddCustomer(){
        setBounds(250, 60, 800, 550);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel title = new JLabel("NEW CUSTOMER FORM");
        title.setBounds(100, 20, 300, 30);
        title.setFont(new Font("Raleway", Font.PLAIN, 20));
        title.setForeground(Color.BLUE);
        add(title);

        JLabel id = new JLabel("ID");
        id.setBounds(35, 80, 100, 20);
        id.setFont(new Font("Raleway", Font.PLAIN,18));
        add(id);

        String str [] = {"CNIC", "Passport", "Driving License", "Voter-id Card", "Ration card"};
        cid = new JComboBox(str);
        cid.setBounds(200, 80, 150, 25);
        cid.setBackground(Color.WHITE);
        cid.setForeground(Color.BLACK);
        add(cid);

        JLabel number = new JLabel("Number");
        number.setBounds(35, 120, 100, 20);
        number.setFont(new Font("Raleway", Font.PLAIN,18));
        add(number);

        tfnumber = new JTextField();
        tfnumber.setBounds(200, 120, 150, 25);
        add(tfnumber);

        JLabel name = new JLabel("Name");
        name.setBounds(35, 160, 150, 20);
        name.setFont(new Font("Raleway", Font.PLAIN,18));
        add(name);

        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        add(tfname);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(35, 200, 100, 20);
        gender.setFont(new Font("Raleway", Font.PLAIN,18));
        add(gender);

        rmale = new JRadioButton("Male");
        rmale.setBounds(200 ,200, 60, 25);
        rmale.setFont(new Font("Raleway", Font.PLAIN, 14));
        rmale.setBackground(Color.WHITE);
        add(rmale);

        rfemale = new JRadioButton("Female");
        rfemale.setBounds(270, 200, 100, 25);
        rfemale.setFont(new Font("Raleway", Font.PLAIN, 14));
        rfemale.setBackground(Color.WHITE);
        add(rfemale);

        JLabel country = new JLabel("Country");
        country.setBounds(35, 240, 100, 20);
        country.setFont(new Font("Raleway", Font.PLAIN,18));
        add(country);

        tfcountry = new JTextField();
        tfcountry.setBounds(200, 240, 150, 25);
        add(tfcountry);

        JLabel cRoomNumber = new JLabel("Room Number");
        cRoomNumber.setBounds(35, 280, 150, 20);
        cRoomNumber.setFont(new Font("Raleway", Font.PLAIN,18));
        add(cRoomNumber);

        croom = new Choice();
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            String query = "SELECT * FROM room WHERE availability  = 'Available'";
            PreparedStatement pst = conn.prepareStatement(query);
            try (ResultSet rs = pst.executeQuery(query)) {

                while (rs.next()) {
                    croom.add(rs.getString("roomnumber"));
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        croom.setBounds(200, 280, 150, 25);
        add(croom);

        JLabel lbltime = new JLabel("Check-in Time");
        lbltime.setBounds(35, 320, 150, 20);
        lbltime.setFont(new Font("Raleway", Font.PLAIN,18));
        add(lbltime);

        Date date = new Date();


        checkintime = new JLabel("" + date);
        checkintime.setBounds(200, 320, 145, 25);
        checkintime.setFont(new Font("Raleway", Font.PLAIN,15));
        add(checkintime);

        JLabel deposit = new JLabel("Deposit");
        deposit.setBounds(35, 360, 100, 20);
        deposit.setFont(new Font("Raleway", Font.PLAIN,18));
        add(deposit);

        tfdeposit = new JTextField();
        tfdeposit.setBounds(200, 360, 150, 25);
        add(tfdeposit);

        addCustomer = new JButton("Add");
        addCustomer.setBounds(50, 410, 120, 30);
        addCustomer.setBackground(Color.BLACK);
        addCustomer.setForeground(Color.WHITE);
        addCustomer.addActionListener(this);
        add(addCustomer);

        back = new JButton("Back");
        back.setBounds(200, 410, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 300, 400);
        add(image);


        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == addCustomer){
            String id  = (String) cid.getSelectedItem();
            String number = tfnumber.getText();
            String name = tfname.getText();
            String gender = null;
              if(rmale.isSelected()){
                  gender = "Male";
              }
              else{
                  gender = "Female";
              }
            String country = tfcountry.getText();
            String room = croom.getSelectedItem();
            String time = checkintime.getText();
            String deposit = tfdeposit.getText();

            try{
                Conn c = new Conn();
                Connection conn = c.getConnection();


                String query = "INSERT INTO customer (id, number, name, gender, country, room, checkintime, deposit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                String query2 = "UPDATE room SET availability = 'Occupied' WHERE roomnumber = '"+room+"'";

                PreparedStatement pst  = conn.prepareStatement(query);
                PreparedStatement pst1 = conn.prepareStatement(query2);

                pst.setString(1, id);
                pst.setString(2, number);
                pst.setString(3, name);
                pst.setString(4, gender);
                pst.setString(5, country);
                pst.setString(6, room);
                pst.setString(7, time);
                pst.setString(8, deposit);

                pst.executeUpdate();
                pst1.executeUpdate();

                JOptionPane.showMessageDialog(null, "Customer Added Successfully");

                setVisible(false);
                new Reception();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == back){
            new Reception();
            setVisible(false);
        }
    }

    public static void main(String [] args){
        new AddCustomer();
    }
}
