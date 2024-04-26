package Viewer; 

import javax.swing.*;
import javax.swing.JOptionPane;

import DAO.Connect_db;
import DAO.PlayerControllerJDBC;
import DAO.RoomControllerJDBC;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinRoomPage extends JFrame {
	
	JPanel formPanel = new JPanel(new GridLayout(3,0));
	JPanel roomIdPanel = new JPanel();
	JPanel signupPanel = new JPanel();
	JPanel buttonsPanel = new JPanel();
	Checkbox nuitmood = new Checkbox("mod nuit");
	JLabel welcome = new JLabel("Welcome to Quizy :",JLabel.CENTER);
	JLabel footerLabel = new JLabel("© 2024. All Rights Reserved At https://github.com/Bassssem/QuizyLive_Java_Swing",JLabel.CENTER);
	JLabel roomIdLabel = new JLabel("Room id :");
	JTextField rommId=new JTextField(10);
	JButton delete = new JButton("Delete");
	JButton join = new JButton("Join");
	JButton signup = new JButton("create your quiz");
	JLabel image = new JLabel(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\quiz.png"));
	
	RoomControllerJDBC RoomControll = new RoomControllerJDBC();
    
    
	
    public JoinRoomPage() {
    	super("Join Room");
    	welcome.setForeground(Color.CYAN);
    	welcome.setFont(new Font("Tahoma", Font.ITALIC, 30));
    	footerLabel.setForeground(Color.red);
    	footerLabel.setFont(new Font("Tahoma", Font.ITALIC, 15));
    	
    	signupPanel.add(signup);
    	
    	buttonsPanel.add(delete);
    	buttonsPanel.add(join);
    	
    	roomIdPanel.add(roomIdLabel);
    	roomIdPanel.add(rommId);
    	
    	
    	
    	formPanel.add(roomIdPanel);
    	formPanel.add(buttonsPanel);
    	formPanel.add(signupPanel);
    	
    	this.add(welcome,BorderLayout.NORTH);
    	this.add(image,BorderLayout.WEST);
    	this.add(formPanel,BorderLayout.CENTER);
    	this.add(footerLabel,BorderLayout.SOUTH);
    	
       setSize(800, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        delete.addActionListener(e->rommId.setText(""));
        join.addActionListener(e-> {
				String roomid = rommId.getText();
				int idroom;
				try {
					idroom = Integer.parseInt(roomid);
					try {
						int rowsInserted = RoomControll.verifRoomIfExist(idroom);
						if (rowsInserted>0) {
							System.out.println("correct id room joinned");
			                new JoinRoomPageWithName(idroom);
							dispose();
			                }
			                else
			                	JOptionPane.showMessageDialog(JoinRoomPage.this, "Room ID Incorrect Please verifie and try agin !");						 
					} catch (SQLException e1) {
						e1.printStackTrace();
						} 
				}catch (Exception e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(JoinRoomPage.this, "Please Enter a valid Room ID !");
				}
			}
		);
        signup.addActionListener(e-> {
				setVisible(false);
				new signup(getSize(),getLocation());
			}
		);
        nuitmood.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1) 
					nuitMoodFunction();
				else 
					jourMoodFunction();
					
				
			}
		});
    }
    
    void nuitMoodFunction() {
    	getContentPane().setBackground(Color.DARK_GRAY);
    	welcome.setForeground(Color.white);
    	roomIdLabel.setForeground(Color.white);
    	nuitmood.setBackground(Color.DARK_GRAY);
    	nuitmood.setForeground(Color.white);
    	delete.setBackground(Color.DARK_GRAY);
    	delete.setForeground(Color.white);
    	join.setBackground(Color.DARK_GRAY);
    	join.setForeground(Color.white);
    	signup.setBackground(Color.DARK_GRAY);
    	signup.setForeground(Color.white);
    	
    }
    void jourMoodFunction() {
    	getContentPane().setBackground(getBackground());
    	welcome.setForeground(Color.black);
    	roomIdLabel.setForeground(Color.black);
    	nuitmood.setBackground(getBackground());
    	nuitmood.setForeground(Color.black);
    	delete.setBackground(getBackground());
    	delete.setForeground(Color.black);
    	join.setBackground(getBackground());
    	join.setForeground(Color.black);
    	signup.setBackground(getBackground());
    	signup.setForeground(Color.black);
    }
    


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JoinRoomPage();
	}

}
