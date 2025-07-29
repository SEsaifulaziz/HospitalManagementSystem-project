package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddEmployee extends JFrame implements ActionListener{

    JTextField tfname, tfage, tfsalary, tfphone, tfemail, tfcnic;
    JRadioButton rbmale, rbfemale;
    JButton submit;
    JComboBox cbjob;


    AddEmployee(){
        setLayout(null);

        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60, 30, 120, 30);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 30, 150, 30);
        add(tfname);

        JLabel lblage = new JLabel("AGE");
        lblage.setBounds(60, 80, 120, 30);
        lblage.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblage);

        tfage = new JTextField();
        tfage.setBounds(200, 70, 150, 30);
        add(tfage);

        JLabel lblgender = new JLabel("GENDER");
        lblgender.setBounds(60, 130, 120, 30);
        lblage.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblgender);

        rbmale = new JRadioButton("Male");
        rbmale.setBounds(200, 130, 70, 30);
        rbmale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbmale.setBackground(Color.WHITE);
        add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(280, 130, 70, 30);
        rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbfemale.setBackground(Color.WHITE);
        add(rbfemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);

        JLabel lbljob = new JLabel("JOB");
        lbljob.setBounds(60, 170, 120, 30);
        lbljob.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbljob);

        String str [] = {"Front Desk Clerks", "Porters", "Housekeeping",  "Kitchen Staff", "Room Service", "Chefs", "Waiter/Waitress", "Manager", "Accountant"};
        cbjob = new JComboBox(str);
        cbjob.setBounds(200, 180, 150, 30);
        cbjob.setBackground(Color.WHITE);
        add(cbjob);

        JLabel lblsalary = new JLabel("SALARY");
        lblsalary.setBounds(60, 230, 120, 30);
        lblsalary.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblsalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(200, 230, 150, 30);
        add(tfsalary);

        JLabel lblphone = new JLabel("PHONE");
        lblphone.setBounds(60, 280, 120, 30);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(200, 280, 150, 30);
        add(tfphone);

        JLabel lblemail = new JLabel("EMAIL");
        lblemail.setBounds(60, 330, 120, 30);
        lblemail.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 330, 150, 30);
        add(tfemail);

        JLabel lblcnic = new JLabel("Identity");
        lblcnic.setBounds(60, 380, 120, 30);
        lblcnic.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblcnic);

        tfcnic = new JTextField();
        tfcnic.setBounds(200, 380, 150, 30);
        add(tfcnic);

        submit = new JButton("Submit");
        submit.setBounds(200, 430, 150, 30);
        submit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel lblimage = new JLabel(i3);
        lblimage.setBounds(380, 60, 450, 370);
        add(lblimage);


        getContentPane().setBackground(Color.WHITE);
        setBounds(250, 100, 850, 540);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){

        String name = tfname.getText();
        String age = tfage.getText();
        String salary = tfsalary.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();
        String cnic = tfcnic.getText();

        String gender = null;

        if (name.equals("")){
            JOptionPane.showMessageDialog(null, "Name should not be empty");
            return;
        }
        else if(age.equals("")){
            JOptionPane.showMessageDialog(null, "Age should not be empty");
            return;
        }
        else if(salary.equals("")){
            JOptionPane.showMessageDialog(null, "Salary should not be empty");
            return;
        }
        else if(phone.equals("")){
            JOptionPane.showMessageDialog(null, "Phone number should not be empty");
            return;
        }
        else if(email.equals("")){
            JOptionPane.showMessageDialog(null, "Email should not be empty");
            return;
        }

        if (rbmale.isSelected()){
            gender = "Male";
        }
        else if(rbfemale.isSelected()){
            gender = "Female";
        }

        String job = (String) cbjob.getSelectedItem();

        try{
            Conn c = new Conn();
            Connection conn = c.getConnection();

            String query = "INSERT INTO employee (name, age, gender, job, salary, phone, email, cnic) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, age);
            pst.setString(3, gender);
            pst.setString(4, job);
            pst.setString(5, salary);
            pst.setString(6, phone);
            pst.setString(7, email);
            pst.setString(8, cnic);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Employee Added Successfully");

            setVisible(false);


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String [] args){
        new AddEmployee();
    }
}
