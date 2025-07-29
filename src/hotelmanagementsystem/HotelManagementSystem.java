package hotelmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HotelManagementSystem extends JFrame implements ActionListener{

   HotelManagementSystem(){

       setBounds(-10, 0, 1360, 565);
       setLayout(null);

       ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
       JLabel image = new JLabel(icon);
       image.setBounds(0, 0, 1366, 565); // Location X, Location Y Location Left, Location Right
       add(image);

       JLabel text = new JLabel("Hotel Management System");
       text.setBounds(20, 430, 1000, 90);
       text.setForeground(Color.WHITE);
       text.setFont(new Font("Serif", Font.PLAIN, 50));
       image.add(text);

       JLabel text2 = new JLabel("Created by Saiful Aziz");
       text2.setBounds(1130, 496, 200, 30);
       text2.setForeground(Color.WHITE);
       text2.setFont(new Font("ariel", Font.PLAIN, 14));
       image.add(text2);

       JButton next = new JButton("Next");
       next.setBounds(1065, 450, 150, 50);
       next.setBackground(Color.WHITE);
       next.setForeground(Color.MAGENTA);
       next.addActionListener(this);
       next.setFont(new Font("serif", Font.PLAIN, 20));
       image.add(next);

       setVisible(true);


       while(true){
           text.setVisible(false);
           try{
               Thread.sleep(500);
           }catch(Exception e){
               e.printStackTrace();
           }
           text.setVisible(true);
           try {
               Thread.sleep(500);
           }catch(Exception e){
               e.printStackTrace();
           }
       }
   }

   public void actionPerformed(ActionEvent ae){
       setVisible(false);
       new Login();
   }

    public static void main(String [] args){
        new HotelManagementSystem();
    }
}
