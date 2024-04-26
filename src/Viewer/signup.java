package Viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.Connect_db;
import DAO.LoginAndSignupControllerJDBC;

public class signup extends JFrame {
	
	JPanel buttonsPanelInWest = new JPanel();
	JPanel SignInForm= new JPanel(new GridLayout(3,1));
	JPanel SignUpForm = new JPanel(new GridLayout(4,2));
	JPanel emailSignInPanel = new JPanel();
	JPanel emailSignUpPanel = new JPanel();
	JPanel passwordSignInPanel = new JPanel();
	JPanel passwordSignUpPanel = new JPanel();
	JPanel signInButtonPanel = new JPanel();
	JPanel signUpButtonPanel = new JPanel();
	JPanel nomSignUpPanel = new JPanel();
	JLabel TitleLabel = new JLabel("Sign In :",JLabel.RIGHT);
	JLabel errurLabel = new JLabel("");
	JButton SwitchToSignIn = new JButton("Sign In");
	JButton SwitchToSignUp = new JButton("Sign Up");
	
	
	JLabel emailLabel = new JLabel("Email:");
    JTextField emailField = new JTextField(20);
    JLabel passwordLabel = new JLabel("Password:");
    JPasswordField passwordField = new JPasswordField(20);
    JButton signInButton = new JButton("Sign In");
    
    
    JLabel nameLabel = new JLabel("Name:");
    JTextField nameField = new JTextField(20);
    JLabel Labelemail = new JLabel("Email:");
    JTextField Fieldemail = new JTextField(20);
    JLabel Labelpassword = new JLabel("Password:");
    JPasswordField Fieldpassword = new JPasswordField(20);
    JButton signUpButton = new JButton("Sign Up");
    
    JButton BackButton = new JButton(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\back.png"),20,25));
    JPanel headerPanel = new JPanel(new GridLayout(1,2));
	JPanel BackButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
	String fileName = "C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\DAO\\QuizyAccount.txt";
	LoginAndSignupControllerJDBC LoginAndSignupControll = new LoginAndSignupControllerJDBC(); 
	
	public signup(Dimension ScreeSize , Point ScreenLocation) {
		super("Login OR Signup");
		
		emailSignInPanel.add(emailLabel);
		emailSignInPanel.add(emailField);
		passwordSignInPanel.add(passwordLabel);
		passwordSignInPanel.add(passwordField);
		
		signInButtonPanel.add(signInButton);
		
		SignInForm.add(emailSignInPanel);
		SignInForm.add(passwordSignInPanel);
		SignInForm.add(signInButtonPanel);
		
		nomSignUpPanel.add(nameLabel);
		nomSignUpPanel.add(nameField);
		emailSignUpPanel.add(Labelemail);
		emailSignUpPanel.add(Fieldemail);
		passwordSignUpPanel.add(Labelpassword);
		passwordSignUpPanel.add(Fieldpassword);
		
		signUpButtonPanel.add(signUpButton);
		
		SignUpForm.add(nomSignUpPanel);
		SignUpForm.add(emailSignUpPanel);
		SignUpForm.add(passwordSignUpPanel);
		SignUpForm.add(signUpButtonPanel);
		
		buttonsPanelInWest.add(SwitchToSignIn);
		buttonsPanelInWest.add(SwitchToSignUp);

	
	errurLabel.setForeground(Color.red);
	errurLabel.setBounds(100, 60, 300, 40);
	errurLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
	
	
	BackButtonPanel.add(BackButton);
	headerPanel.add(TitleLabel);
	headerPanel.add(BackButtonPanel);
  
	add(headerPanel,BorderLayout.NORTH);
    add(buttonsPanelInWest,BorderLayout.CENTER);
    add(SignInForm,BorderLayout.SOUTH);
    
    
    setSize(ScreeSize);
    setLocation(ScreenLocation);
    this.setVisible(true);
    
    try {
        FileReader reader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
        	int id = Integer.parseInt(bufferedReader.readLine());
	        String nom = bufferedReader.readLine();
	        int save = JOptionPane.showConfirmDialog(signup.this, "did you want to contunie with : "+nom);
            	if(save == 0) {
			        bufferedReader.close();
			        new HomePage(id,nom,getSize(),getLocation());
			        this.dispose();
            	}
        }
        catch (Exception e) {
        	 System.out.println(e.getMessage());
		}
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
    
    BackButton.addActionListener(e->{
    	new JoinRoomPage();
    	dispose();
    });
    
    signInButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String emailText =  emailField.getText();
			char[] psd = passwordField.getPassword();
			String passwordText = new String(psd);
			if (!emailText.contains("@") || (!emailText.endsWith(".tn") && !emailText.endsWith(".com") && !emailText.endsWith(".fr")))
			    JOptionPane.showMessageDialog(signup.this, "Please enter a valid email!");
			else {
				if(passwordText.length() < 8 )
					JOptionPane.showMessageDialog(signup.this, "Please enter a valid password !");
				else {    
					try {
						
						ResultSet rowsInserted = LoginAndSignupControll.VerifLogin(emailText, passwordText);
						 
			                if (rowsInserted.next()) {
			                    System.out.println("Sign In  successfully!");
			                    int save = JOptionPane.showConfirmDialog(signup.this, "did you want to save you account !");
			                    if(save == 0) {

			                        try {
			                            File file = new File(fileName);
			                            FileWriter writer = new FileWriter(file);
			                            BufferedWriter bufferedWriter = new BufferedWriter(writer);
			                            bufferedWriter.write(rowsInserted.getInt(1)+"\n"+rowsInserted.getString(2));
			                            bufferedWriter.close();
			                            System.out.println("you account saved !");

			                        } catch (IOException e1) {
			                            System.out.println(e1.getMessage());
			                        }
			                    }
			                    dispose();
			                    new HomePage(rowsInserted.getInt(1),rowsInserted.getString(2),getSize(),getLocation());
			                }else
			                	JOptionPane.showMessageDialog(signup.this, "Wrong Email or Password !");
			                
						 
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			
		}
	}
    });
    
    signUpButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String nom = nameField.getText();
			String emailText =  Fieldemail.getText();
			char[] psd = Fieldpassword.getPassword();
			String passwordText = new String(psd);
			if(nom.equals(""))
				JOptionPane.showMessageDialog(signup.this, "Please enter a valid name !");
			else {
				if (!emailText.contains("@") || (!emailText.endsWith(".tn") && !emailText.endsWith(".com") && !emailText.endsWith(".fr")))
				    JOptionPane.showMessageDialog(signup.this, "Please enter a valid email!");
				else {
					if(passwordText.length() < 8 )
						JOptionPane.showMessageDialog(signup.this, "Please enter a valid password !");
					else {
						try {
							int rowsInserted = LoginAndSignupControll.VerifSignup(nom, emailText, passwordText);
				             if (rowsInserted==-1) {
				            	 JOptionPane.showMessageDialog(signup.this, "email Already Used !");
				             }
				             else {
					                if (rowsInserted > 0) {
					                    System.out.println("Sign Up  successfully!");
					                    TitleLabel.setText("Sign In");
					                	SignUpForm.setVisible(false);
					                	SignInForm.setVisible(true);
					                    add(SignInForm,BorderLayout.SOUTH);
					               }
				             }
							 
							 
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
                
		}
	});
    
    SwitchToSignUp.addActionListener(e->{
    	TitleLabel.setText("Sign Up");
    	SignInForm.setVisible(false);
    	SignUpForm.setVisible(true);
    	add(SignUpForm,BorderLayout.SOUTH);
    });
    SwitchToSignIn.addActionListener(e->{
    	TitleLabel.setText("Sign In");
    	SignUpForm.setVisible(false);
    	SignInForm.setVisible(true);
    	add(SignInForm,BorderLayout.SOUTH);
    });
}
	ImageIcon resize(ImageIcon icon,int width, int height) {
    	
    	Image img = icon.getImage();
    	Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    	return new ImageIcon(newImg);
    }
}
