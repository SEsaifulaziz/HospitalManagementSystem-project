package hotelmanagementsystem;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class SearchRoom extends JFrame implements ActionListener{

    JTable table;
    JButton back, submit;
    JComboBox jbedtype;
    JCheckBox available;

    SearchRoom(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Search for Room");
        text.setBounds(400, 30, 200, 20);
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(text);

        JLabel lbltype = new JLabel("Bed Type");
        lbltype.setBounds(60, 100, 100, 20);
        add(lbltype);

        String [] str = {"Single", "Double"};

        jbedtype = new JComboBox(str);
        jbedtype.setBounds(150, 100, 150, 25);
        jbedtype.setBackground(Color.WHITE);
        add(jbedtype);

        available = new JCheckBox("Only Available");
        available.setBounds(650, 100, 150, 25);
        available.setBackground(Color.WHITE);
        add(available);

        JLabel number = new JLabel("Room Number");
            number.setBounds(50, 160, 100, 20);
        add(number);

        JLabel availability = new JLabel("Availability");
        availability.setBounds(270, 160, 100, 20);
        add(availability);

        JLabel status = new JLabel("Status");
        status.setBounds(450, 160, 100, 20);
        add(status);

        JLabel price = new JLabel("Price");
        price.setBounds(670, 160, 100, 20);
        add(price);

        JLabel lblbedtype = new JLabel("Bed Type");
        lblbedtype.setBounds(870, 160, 100, 20);
        add(lblbedtype);

        table = new JTable();
        table.setBounds(0, 200, 1000, 300);
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

                String selectedBedType = (String) jbedtype.getSelectedItem();

                PreparedStatement pst;
                ResultSet rs;


                if (available.isSelected()){

                    pst = conn.prepareStatement ("SELECT * FROM room WHERE availability = ? AND type = ? ");
                    pst.setString(1, "Available");
                    pst.setString(2, selectedBedType);

                }
                else{

                    pst = conn.prepareStatement("SELECT * FROM room WHERE type = ?  ");
                    pst.setString(1, selectedBedType);

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
        new SearchRoom();
    }
}
 