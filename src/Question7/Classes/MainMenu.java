package Classes;

import Entities.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class MainMenu {
    //Frames
    JFrame mainMenuFrame;
//    JFrame forgotPasswordFrame;
    //Panels
    JPanel loginPanel;
    JPanel signupPanel;
    JPanel signupMainPanel;


    JLabel community;
    JLabel loginUsernameLabel;
    JLabel loginPasswordLabel;
    JLabel sideImage;
//    JLabel forgotpassword;
    JLabel makethemostoutofurprofessionallife;
    JLabel byclicking;
    JLabel termsandconditions;
    JLabel signupEmailLabel;
    JLabel signupPasswordLabel;
    JLabel signupUsernameLabel;
//    JLabel EmailLabelForForgotPassword = new JLabel();
    //Textfields
    JTextField loginUsernameTextfield;
    JTextField loginPasswordTextfield;
    JTextField signupEmailTextfield;
    JTextField signupPasswordTextfield;
    JTextField signupUsernameTextfield;
    JTextField otpTextField = new JTextField();
    //Buttons
    JButton loginButton;
    JButton newAccountButton;
    JButton wAccountButton;
    JButton signupButton;

    int otp;

    public MainMenu() throws SQLException{

        //Establishing connection to Mysql database
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","binita");
        Statement statement = connection.createStatement();

        //Creating Database and table if not exists
        statement.execute("create database if not exists dsa_db");
        statement.execute("use dsa_db");
        statement.execute("create table if not exists usercredentials (email varchar(100),username varchar(100),password varchar(100))");

        mainMenuFrame = new JFrame();
        mainMenuFrame.setTitle("SocialMedia App");
        mainMenuFrame.setSize(1200,700);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setResizable(false);
        ImageIcon VTagIcon = new ImageIcon("Images\\VTagIcon.png");
        mainMenuFrame.setIconImage(VTagIcon.getImage());

        loginPanel = new JPanel();
        loginPanel.setBackground(Color.PINK);
        loginPanel.setBounds(0,0,1200,700);



        community = new JLabel("Tiktok App");
        community.setFont(new Font("Helvetica",Font.ITALIC,40));
        community.setForeground(Color.white);
        community.setBounds(50, 95, 700, 45);

        loginUsernameLabel = new JLabel("Username");
        loginUsernameLabel.setFont(new Font("Helvetica", Font.PLAIN,14));
        loginUsernameLabel.setForeground(Color.white);
        loginUsernameLabel.setBounds(50,170,100,20);


        loginUsernameTextfield = new JTextField();
        loginUsernameTextfield.setBounds(50,195,310,45);
        loginUsernameTextfield.setForeground(Color.black);
        loginUsernameTextfield.setBorder(new LineBorder(Color.decode("#000000")));
        loginUsernameTextfield.setFont(new Font("Helvetica", Font.PLAIN, 20));

        loginUsernameTextfield.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                loginUsernameTextfield.setBackground(Color.decode("#F5F5F5"));
                loginUsernameTextfield.setBorder(new LineBorder(Color.decode("#000000"),2));
            }
            public void mouseExited(MouseEvent e){
                loginUsernameTextfield.setBackground(new Color(255,255,255));
                loginUsernameTextfield.setBorder(new LineBorder(Color.decode("#000000")));
            }
        });

        loginPasswordLabel = new JLabel("Password");
        loginPasswordLabel.setFont(new Font("Helvetica", Font.PLAIN,14));
        loginPasswordLabel.setForeground(Color.white);
        loginPasswordLabel.setBounds(50,270,100,20);

        loginPasswordTextfield = new JPasswordField();
        loginPasswordTextfield.setBounds(50,295,310,45);
        loginPasswordTextfield.setForeground(Color.black);
        loginPasswordTextfield.setBorder(new LineBorder(Color.decode("#000000")));
        loginPasswordTextfield.setFont(new Font("Helvetica", Font.PLAIN, 20));

        loginPasswordTextfield.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                loginPasswordTextfield.setBackground(Color.decode("#F5F5F5"));
                loginPasswordTextfield.setBorder(new LineBorder(Color.decode("#000000"),2));
            }
            public void mouseExited(MouseEvent e){
                loginPasswordTextfield.setBackground(new Color(255,255,255));
                loginPasswordTextfield.setBorder(new LineBorder(Color.decode("#000000")));
            }
        });





// Set font style, size, and weight for the OTP text field
        otpTextField.setFont(new Font("Helvetica", Font.BOLD, 12));

// Set the position and size of the OTP text field within the GUI
        otpTextField.setBounds(60, 110, 300, 40);

// Set default text for the OTP text field
        otpTextField.setText("Enter OTP");

// Set border style for the OTP text field
        otpTextField.setBorder(new LineBorder(Color.gray, 2));

// Add mouse listener to handle mouse enter event for the OTP text field
        otpTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Change border color and background color when mouse enters the text field
                otpTextField.setBorder(new LineBorder(Color.black, 2));
                otpTextField.setBackground(new Color(239, 239, 239));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore border color and background color when mouse exits the text field
                otpTextField.setBorder(new LineBorder(Color.gray, 2));
                otpTextField.setBackground(Color.white);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Clear the text field when it's clicked
                otpTextField.setText("");
            }
        });

// Create and configure the "Submit" button for OTP verification
        JButton otpSubmitButton = new JButton("Submit");
        otpSubmitButton.setBounds(280, 170, 80, 40);
        otpSubmitButton.setForeground(Color.white);
        otpSubmitButton.setBackground(Color.CYAN);

// Add mouse listener to handle mouse enter, exit, and click events for the "Submit" button
        otpSubmitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Change background color when mouse enters the button
                otpSubmitButton.setBackground(new Color(0, 65, 130));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore background color when mouse exits the button
                otpSubmitButton.setBackground(new Color(10, 102, 194));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Verify OTP entered by the user
                System.out.println(otp);
                if (otpTextField.getText().equals(Integer.toString(otp))) {
                    try {
                        // Delete user credentials if OTP is correct and switch to the signup panel
                        statement.execute("delete from usercredentials where username = '" + loginUsernameTextfield.getText() + "'");
                        loginPanel.setVisible(false);
                        loginUsernameTextfield.setText("");
                        signupPanel.setVisible(true);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    // Change the text field border color to indicate incorrect OTP
                    textFieldToRed(otpTextField);
                }
            }
        });


        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(10,102,194));
        loginButton.setForeground(new Color(255,255,255));
        loginButton.setFont(new Font("Helvetica",Font.BOLD,15));
        loginButton.setBounds(50,400,310,45);

        loginButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                loginButton.setBackground(new Color(0, 65, 130));
            }
            public void mouseExited(MouseEvent e){
                loginButton.setBackground(new Color(10,102,194));
            }
            public void mouseClicked(MouseEvent e){
                String emailID = loginUsernameTextfield.getText();
                String password = loginPasswordTextfield.getText();

                if(emailID.isBlank())
                    textFieldToRed(loginUsernameTextfield);
                if(password.isBlank())
                    textFieldToRed(loginPasswordTextfield);
                if(!emailID.isBlank() && !password.isBlank()){
                    try {
                        ResultSet resultSet = statement.executeQuery("select * from usercredentials");
                        String username = "";
                        String pass = "";
                        String email = "";
                        boolean loggedIn = false;
                        while(resultSet.next()){
                            email = resultSet.getString(1);
                            username = resultSet.getString(2);
                            pass = resultSet.getString(3);
                            if(username.equals(emailID) && pass.equals(password)){
                                loggedIn = true;
                                break;
                            }
                        }

                        if (!loggedIn) {
                            textFieldToRed(loginUsernameTextfield);
                            textFieldToRed(loginPasswordTextfield);
                        } else {
                            mainMenuFrame.setVisible(false);
                            try {
                                // Attempt to create an instance of MainPage
                                new MainPage(new User(email, username, password));
                            } catch (Exception ex) {
                                // Handle any exceptions that might occur during MainPage creation
                                ex.printStackTrace(); // This will print the stack trace of the exception for debugging purposes
                                JOptionPane.showMessageDialog(null, "Error while opening mainPageFrame: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });

        ImageIcon sideimg = new ImageIcon("Images\\p.jpg");
        sideImage = new JLabel(sideimg);
        sideImage.setBounds(0,0,1200,700);
        newAccountButton = new JButton("Create new account");
        newAccountButton.setBackground(new Color(255,255,255));
        newAccountButton.setForeground(new Color(0, 0, 0, 191));
        newAccountButton.setFont(new Font("Helvetica",Font.PLAIN,15));
        newAccountButton.setBorder(new LineBorder(new Color(0, 0, 0, 191)));
        newAccountButton.setBounds(50,465,310,45);

        newAccountButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                newAccountButton.setBackground(Color.decode("#F5F5F5"));
                newAccountButton.setBorder(new LineBorder(Color.decode("#000000"),2));
            }
            public void mouseExited(MouseEvent e){
                newAccountButton.setBackground(new Color(255,255,255));
                newAccountButton.setBorder(new LineBorder(Color.decode("#000000")));
            }
            public void mouseClicked(MouseEvent e){
                loginPanel.setVisible(false);
                signupPanel.setVisible(true);
            }
        });

        signupPanel = new JPanel();
        signupPanel.setBackground(Color.pink);
        signupPanel.setBounds(0,0,1200,700);

        makethemostoutofurprofessionallife = new JLabel("RegisterPage");
        makethemostoutofurprofessionallife.setForeground(new Color(24,24,24));
        makethemostoutofurprofessionallife.setFont(new Font("Helvetica",Font.ITALIC,30));
        makethemostoutofurprofessionallife.setBounds(330,60,1000,40);

        signupMainPanel = new JPanel();
        signupMainPanel.setBackground(new Color(255,255,255));
        signupMainPanel.setBounds(410,140,350,450);



        signupUsernameLabel = new JLabel("Username");
        signupUsernameLabel.setFont(new Font("Helvetica", Font.PLAIN,14));
        signupUsernameLabel.setForeground(new Color(0, 0, 0, 191));
        signupUsernameLabel.setBounds(30,120,100,20);

        signupUsernameTextfield = new JTextField();
        signupUsernameTextfield.setBounds(30,150,294,45);
        signupUsernameTextfield.setForeground(new Color(0, 0, 0, 191));
        signupUsernameTextfield.setBorder(new LineBorder(Color.decode("#000000")));
        signupUsernameTextfield.setFont(new Font("Helvetica", Font.PLAIN, 20));

        signupUsernameTextfield.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                signupUsernameTextfield.setBackground(Color.decode("#F5F5F5"));
                signupUsernameTextfield.setBorder(new LineBorder(Color.decode("#000000"),2));
            }
            public void mouseExited(MouseEvent e){
                signupUsernameTextfield.setBackground(new Color(255,255,255));
                signupUsernameTextfield.setBorder(new LineBorder(Color.decode("#000000")));
            }
        });

        signupEmailLabel = new JLabel("Email");
        signupEmailLabel.setFont(new Font("Helvetica", Font.PLAIN,14));
        signupEmailLabel.setForeground(new Color(0, 0, 0, 191));
        signupEmailLabel.setBounds(30,30,100,20);

        signupEmailTextfield = new JTextField();
        signupEmailTextfield.setBounds(30,60,294,45);
        signupEmailTextfield.setForeground(new Color(0, 0, 0, 191));
        signupEmailTextfield.setBorder(new LineBorder(Color.decode("#000000")));
        signupEmailTextfield.setFont(new Font("Helvetica", Font.PLAIN, 20));

        signupEmailTextfield.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                signupEmailTextfield.setBackground(Color.decode("#F5F5F5"));
                signupEmailTextfield.setBorder(new LineBorder(Color.decode("#000000"),2));
            }
            public void mouseExited(MouseEvent e){
                signupEmailTextfield.setBackground(new Color(255,255,255));
                signupEmailTextfield.setBorder(new LineBorder(Color.decode("#000000")));
            }
        });

        signupPasswordLabel = new JLabel("Password");
        signupPasswordLabel.setFont(new Font("Helvetica", Font.PLAIN,14));
        signupPasswordLabel.setForeground(new Color(0, 0, 0, 191));
        signupPasswordLabel.setBounds(30,210,100,20);

        signupPasswordTextfield = new JPasswordField();
        signupPasswordTextfield.setBounds(30,240,294,45);
        signupPasswordTextfield.setForeground(new Color(0, 0, 0, 191));
        signupPasswordTextfield.setBorder(new LineBorder(Color.decode("#000000")));
        signupPasswordTextfield.setFont(new Font("Helvetica", Font.PLAIN, 20));

        signupPasswordTextfield.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                signupPasswordTextfield.setBackground(Color.decode("#F5F5F5"));
                signupPasswordTextfield.setBorder(new LineBorder(Color.decode("#000000"),2));
            }
            public void mouseExited(MouseEvent e){
                signupPasswordTextfield.setBackground(new Color(255,255,255));
                signupPasswordTextfield.setBorder(new LineBorder(Color.decode("#000000")));
            }
        });

        byclicking = new JLabel("");
        byclicking.setForeground(new Color(102,102,102));
        byclicking.setFont(new Font("Helvetica",Font.PLAIN,10));
        byclicking.setBounds(38,310,500,13);

        termsandconditions = new JLabel("");
        termsandconditions.setForeground(new Color(10,102,194));
        termsandconditions.setFont(new Font("Helvetica",Font.PLAIN,10));
        termsandconditions.setBounds(60,325,500,13);

        signupButton = new JButton("Sign Up");
        signupButton.setBackground(new Color(10,102,194));
        signupButton.setForeground(new Color(255,255,255));
        signupButton.setFont(new Font("Helvetica",Font.BOLD,15));
        signupButton.setBounds(30,350,294,45);

        wAccountButton = new JButton("Back to loginPage");
        wAccountButton.setBackground(new Color(255,255,255));
        wAccountButton.setForeground(new Color(0, 0, 0, 191));
        wAccountButton.setFont(new Font("Helvetica",Font.PLAIN,15));
        wAccountButton.setBorder(new LineBorder(new Color(0, 0, 0, 191)));
        wAccountButton.setBounds(50,465,310,45);

        wAccountButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                wAccountButton.setBackground(Color.decode("#F5F5F5"));
                wAccountButton.setBorder(new LineBorder(Color.decode("#000000"),2));
            }
            public void mouseExited(MouseEvent e){
                wAccountButton.setBackground(new Color(255,255,255));
                wAccountButton.setBorder(new LineBorder(Color.decode("#000000")));
            }
            public void mouseClicked(MouseEvent e){
                signupPanel.setVisible(true);
                loginPanel.setVisible(true); // Show the login panel
            }
        });


        signupButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                signupButton.setBackground(new Color(0, 65, 130));
            }
            public void mouseExited(MouseEvent e){
                signupButton.setBackground(new Color(10,102,194));
            }
            public void mouseClicked(MouseEvent e){
                String emailID = signupEmailTextfield.getText();
                String username = signupUsernameTextfield.getText();
                String password = signupPasswordTextfield.getText();

                if(emailID.isBlank())
                    textFieldToRed(signupEmailTextfield);
                if(username.isBlank())
                    textFieldToRed(signupUsernameTextfield);
                if(password.isBlank())
                    textFieldToRed(signupPasswordTextfield);

                if(!emailID.isBlank() && !password.isBlank() && !username.isBlank()){

                    try {
                        ResultSet resultSet = statement.executeQuery("select * from usercredentials");
                        boolean userExist = false;
                        while(resultSet.next()){
                            if(resultSet.getString(1).equals(emailID)){
                                userExist  =  true;
                                break;
                            }
                        }

                        if(userExist){
                            System.out.println("User already exist!");
                            textFieldToRed(signupEmailTextfield);
                            textFieldToRed(signupPasswordTextfield);
                            textFieldToRed(signupUsernameTextfield);
                        }
                        else{
                            String insertQuery = "INSERT INTO usercredentials VALUES (?, ? , ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                            preparedStatement.setString(1, emailID);
                            preparedStatement.setString(2, username);
                            preparedStatement.setString(3, password);
                            preparedStatement.executeUpdate();
                            userExist = false;

                            signupPanel.setVisible(false);
                            loginPanel.setVisible(true);
                        }

                        signupEmailTextfield.setText("");
                        signupPasswordTextfield.setText("");
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        signupMainPanel.setLayout(null);
        signupMainPanel.add(signupEmailLabel);
        signupMainPanel.add(signupEmailTextfield);
        signupMainPanel.add(signupUsernameLabel);
        signupMainPanel.add(signupUsernameTextfield);
        signupMainPanel.add(signupPasswordLabel);
        signupMainPanel.add(signupPasswordTextfield);
        signupMainPanel.add(byclicking);
        signupMainPanel.add(termsandconditions);
        signupMainPanel.add(signupButton);

        signupPanel.setLayout(null);
        signupPanel.add(makethemostoutofurprofessionallife);
        signupPanel.add(signupMainPanel);
        signupPanel.setVisible(false);


        loginPanel.add(community);
        loginPanel.add(loginUsernameLabel);
        loginPanel.add(loginPasswordLabel);
        loginPanel.add(loginUsernameTextfield);
        loginPanel.add(loginPasswordTextfield);
        loginPanel.add(loginButton);
        loginPanel.add(newAccountButton);
        loginPanel.add(wAccountButton);
        loginPanel.add(sideImage);
        loginPanel.setLayout(null);

        // mainMenuFrame.add(sideImage);
        mainMenuFrame.add(loginPanel);
        mainMenuFrame.add(signupPanel);
        mainMenuFrame.setLayout(null);
        mainMenuFrame.setVisible(true);

    }
    void textFieldToRed(JTextField textField){
            textField.setBorder(new LineBorder(Color.red));;
            new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(70);
                return null;
            }
            @Override
            protected void done() {
                textField.setBorder(new LineBorder(new Color(145,153,157), 1));;
            }
            }.execute();
    }

}
