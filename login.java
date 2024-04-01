import javax.swing.*;

import project1.Item;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class login extends JFrame implements ActionListener {

    private JTextField userText;

    private JPasswordField passwordField;

    private JButton loginButton;

    private JLabel userLabel, passwordLabel;

    private JLabel validateLabel;



    public static void main(String[] args) {

        login project = new login();

        project.initializeUI();

        

    }



    private void initializeUI() {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Hotel Management");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setSize(400, 200);

            frame.setLayout(new FlowLayout());

            frame.getContentPane().setBackground(new Color(240, 240, 240));



            JPanel loginPanel = new JPanel(new GridLayout(3, 2, 5, 5));

            loginPanel.setBackground(new Color(200, 200, 200));



            userLabel = new JLabel("Username: ");

            userLabel.setForeground(Color.BLACK);

            userLabel.setFont(new Font("Arial", Font.BOLD, 14));

            

            userText = new JTextField(15);

            userText.setFont(new Font("Arial", Font.PLAIN, 14));

            

            passwordLabel = new JLabel("Password: ");

            passwordLabel.setForeground(Color.BLACK);

            passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));

            

            passwordField = new JPasswordField(15);

            passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

            loginButton = new JButton("Login");

//            loginButton.addActionListener(this);
            loginButton.addActionListener(e ->{

                String usr = userText.getText();
                String pwd = new String(passwordField.getPassword());
                
                if (authenticateUser(usr, pwd)) {

                	try {
        				project1.main(null);
        				frame.setVisible(false);
        			} catch (ClassNotFoundException | SQLException e1) {
        				e1.printStackTrace();
        			}
                } else {

                    validateLabel.setText("Invalid username or password. Please try again.");

                }

                });

            loginButton.setFont(new Font("Arial", Font.BOLD, 14));

            validateLabel = new JLabel();

            validateLabel.setFont(new Font("Arial", Font.PLAIN, 14));



            loginPanel.add(userLabel);

            loginPanel.add(userText);

            loginPanel.add(passwordLabel);

            loginPanel.add(passwordField);

            loginPanel.add(new JLabel());

            loginPanel.add(loginButton);



            frame.add(loginPanel);

            frame.add(validateLabel);



            frame.setVisible(true);

        });

    }


//
//    public void actionPerformed(ActionEvent e) {
//
//
//
//    }

    private boolean authenticateUser(String username, String password) {
    	
        if (username.isEmpty() || !(username.contentEquals("om"))) {

            return false;

        }
        if(!(password.contentEquals("1234"))) { return false;}
        
        return true;
        

    }

}