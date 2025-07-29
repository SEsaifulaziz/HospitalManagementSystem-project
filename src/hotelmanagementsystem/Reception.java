package hotelmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Reception extends JFrame implements ActionListener{

    JButton customer, rooms, department, employees, customerinfo, managerinfo, checkout, updatestatus, updateroomstatus, pickup, searchroom, logout;

    Reception(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        customer = new JButton("New Customer Form");
        customer.setBounds(10, 30, 200, 30);
        customer.setBackground(Color.BLACK);
        customer.setForeground(Color.WHITE);
        customer.addActionListener(this);
        add(customer);

        rooms = new JButton("Rooms");
        rooms.setBounds(10, 70, 200, 30);
        rooms.setBackground(Color.BLACK);
        rooms.setForeground(Color.WHITE);
        rooms.addActionListener(this);
        add(rooms);

        department = new JButton("Department");
        department.setBounds(10, 110, 200, 30);
        department.setBackground(Color.BLACK);
        department.setForeground(Color.WHITE);
        department.addActionListener(this);
        add(department);

        employees = new JButton("All Employees");
        employees.setBounds(10, 150, 200, 30);
        employees.setBackground(Color.BLACK);
        employees.setForeground(Color.WHITE);
        employees.addActionListener(this);
        add(employees);

        customerinfo = new JButton("Customer Info");
        customerinfo.setBounds(10, 190, 200, 30);
        customerinfo.setBackground(Color.BLACK);
        customerinfo.setForeground(Color.WHITE);
        customerinfo.addActionListener(this);
        add(customerinfo);

        managerinfo = new JButton("Manager Info");
        managerinfo.setBounds(10, 230, 200, 30);
        managerinfo.setBackground(Color.BLACK);
        managerinfo.setForeground(Color.WHITE);
        managerinfo.addActionListener(this);
        add(managerinfo);

        checkout = new JButton("Checkout");
        checkout.setBounds(10, 270, 200, 30);
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.addActionListener(this);
        add(checkout);

        updatestatus = new JButton("Update Status");
        updatestatus.setBounds(10, 310, 200, 30);
        updatestatus.setBackground(Color.BLACK);
        updatestatus.setForeground(Color.WHITE);
        updatestatus.addActionListener(this);
        add(updatestatus);

        updateroomstatus = new JButton("Update Room Status");
        updateroomstatus.setBounds(10, 350, 200, 30);
        updateroomstatus.setBackground(Color.BLACK);
        updateroomstatus.setForeground(Color.WHITE);
        updateroomstatus.addActionListener(this);
        add(updateroomstatus);

        pickup = new JButton("Pickup Service");
        pickup.setBounds(10, 390, 200, 30);
        pickup.setBackground(Color.BLACK);
        pickup.setForeground(Color.WHITE);
        pickup.addActionListener(this);
        add(pickup);

        searchroom = new JButton("Search Room");
        searchroom.setBounds(10, 430, 200, 30);
        searchroom.setBackground(Color.BLACK);
        searchroom.setForeground(Color.WHITE);
        searchroom.addActionListener(this);
        add(searchroom);

        logout = new JButton("Logout");
        logout.setBounds(10, 470, 200, 30);
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.WHITE);
        logout.addActionListener(this);
        add(logout);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(250, 30, 500, 470);
        add(image);


        setBounds(250, 60, 800, 570);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == customer){
            setVisible(false);
            new AddCustomer();
        }
        else if(ae.getSource() == rooms){
            setVisible(false);
            new Room();
        }
        else if(ae.getSource() == department){
            setVisible(false);
            new Department();
        }
        else if(ae.getSource() == updatestatus){
            setVisible(false);
            new UpdateStatus();
        }
        else if(ae.getSource() == updateroomstatus){
            setVisible(false);
            new UpdateRoom();
        }
        else if (ae.getSource() == employees){
            setVisible(false);
            new AllEmployeesInfo();
        }
        else if(ae.getSource() == managerinfo){
            setVisible(false);
            new ManagerInfo();
        }
        else if(ae.getSource() == customerinfo){
            setVisible(false);
            new CustomerInfo();
        }
        else if(ae.getSource() == searchroom){
            setVisible(false);
            new SearchRoom();
        }
        else if(ae.getSource() == pickup){
            setVisible(false);
            new PickupService();
        }
        else if(ae.getSource() == checkout){
            setVisible(false);
            new CheckOut();
        }
        else if (ae.getSource() == logout){
            System.exit(0);
        }
    }

    public static void main(String [] args){
        new Reception();
    }
}
