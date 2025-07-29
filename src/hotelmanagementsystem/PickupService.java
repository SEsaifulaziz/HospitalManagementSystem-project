package hotelmanagementsystem;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class PickupService extends JFrame implements ActionListener{

    JTable table;
    JButton back, submit;
    Choice cartype;
    JCheckBox available;

    PickupService(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Search for Driver");
        text.setBounds(400, 30, 200, 20);
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(text);

        JLabel lbltype = new JLabel("Type of Car");
        lbltype.setBounds(60, 100, 100, 20);
        add(lbltype);

//        String [] str = {"Single", "Double"};

        cartype = new Choice();
        cartype.setBounds(150, 100, 150, 25);
        cartype.setBackground(Color.WHITE);
        add(cartype);

        try{
            Conn c = new Conn();
            Connection conn =c.getConnection();

            String query = "SELECT * FROM driver ";
            PreparedStatement pst = conn.prepareStatement(query);

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                cartype.add(rs.getString("brand"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        available = new JCheckBox("Only Available");
        available.setBounds(650, 100, 150, 25);
        available.setBackground(Color.WHITE);
        add(available);

        JLabel number = new JLabel("Name");
        number.setBounds(50, 160, 100, 20);
        add(number);

        JLabel availability = new JLabel("Age");
        availability.setBounds(200, 160, 100, 20);
        add(availability);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(300, 160, 100, 20);
        add(gender);

        JLabel status = new JLabel("Company");
        status.setBounds(450, 160, 100, 20);
        add(status);

        JLabel price = new JLabel("Model");
        price.setBounds(600, 160, 100, 20);
        add(price);

        JLabel lblbedtype = new JLabel("Availability");
        lblbedtype.setBounds(740, 160, 100, 20);
        add(lblbedtype);

        JLabel lblavailability = new JLabel("Location");
        lblavailability.setBounds(870, 160, 100, 20);
        add(lblavailability);

        table = new JTable();
        table.setBounds(0, 200, 1000, 300);
        add(table);

        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            String query = "SELECT * FROM driver ";

            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            e.printStackTrace();
        }

        submit = new JButton("Submit");
        submit.setBounds(300, 500, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        back = new JButton("Back");
        back.setBounds(500, 500, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);


        setBounds(150, 50, 1000, 600);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource() == submit){

            try{
                Conn c = new Conn();
                Connection conn = c.getConnection();

                String selectedCarType = cartype.getSelectedItem();

                PreparedStatement pst;
                ResultSet rs;


                if (available.isSelected()){

                    pst = conn.prepareStatement ("SELECT * FROM driver WHERE availability = ? AND brand = ? ");
                    pst.setString(1, "Available");
                    pst.setString(2, selectedCarType);

                }
                else{

                    pst = conn.prepareStatement("SELECT * FROM driver WHERE brand = ?  ");
                    pst.setString(1, selectedCarType);

                }

                rs = pst.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
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

    public static void main(String args []){
        new PickupService();
    }
}
