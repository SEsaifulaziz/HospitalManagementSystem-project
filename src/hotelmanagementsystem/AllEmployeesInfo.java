package hotelmanagementsystem;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class AllEmployeesInfo extends JFrame implements ActionListener{

    JTable table;
    JButton back;

    AllEmployeesInfo(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel name = new JLabel("Name");
        name.setBounds(40, 12, 100, 20);
        add(name);

        JLabel age = new JLabel("Age");
        age.setBounds(170, 12, 100, 20);
        add(age);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(290, 12, 100, 20);
        add(gender);

        JLabel job = new JLabel("job");
        job.setBounds(400, 12, 100, 20);
        add(job);

        JLabel salary = new JLabel("Salary");
        salary.setBounds(540, 12, 100, 20);
        add(salary);

        JLabel phone = new JLabel("Phone Number");
        phone.setBounds(630, 12, 100, 20);
        add(phone);

        JLabel email = new JLabel("Email");
        email.setBounds(790, 12, 100, 20);
        add(email);

        JLabel docType = new JLabel("Identity");
        docType.setBounds(880, 12, 100, 20);
        add(docType);


        table = new JTable();
        table.setBounds(0, 50, 1000, 400);
        add(table);

        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            String query = "SELECT * FROM employee ";

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
        new AllEmployeesInfo();
    }
}

