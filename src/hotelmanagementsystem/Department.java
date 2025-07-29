package hotelmanagementsystem;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Department extends JFrame implements ActionListener{

    JTable table;
    JButton back;

    Department(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel dep = new JLabel("Department");
        dep.setBounds(180, 10, 100, 20);
        add(dep);

        JLabel budget = new JLabel("Budget");
        budget.setBounds(470, 10, 100, 20);
        add(budget);

        table = new JTable();
        table.setBounds(0, 50, 700, 350);
        add(table);

        try{
            Conn c = new Conn();
            Connection conn = c.getConnection();

            String query = "SELECT * FROM department";

            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            e.printStackTrace();
        }

        back = new JButton("Back");
        back.setBounds(280, 400, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);


        setBounds(150, 65, 700, 480);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == back){
            setVisible(false);
            new  Reception();
        }
    }

    public static void main(String args []){
        new Department();
    }
}