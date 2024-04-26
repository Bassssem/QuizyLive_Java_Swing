package Viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import DAO.Connect_db;
import DAO.PlayerControllerJDBC;
import Model.Player;

public class JoinRoomPageWithName extends JFrame{
	int RoomID ;
	int PlayerID;
	String Nom;
	JPanel formPanel = new JPanel(new BorderLayout());
	JPanel imagesPanel = new JPanel();
	JPanel namePanel = new JPanel();
	JLabel roomLabel ;
	JButton startButton = new JButton("start");
	JLabel nameLabel = new JLabel("your name :");
    JTextField nameField=new JTextField(20);
    JRadioButton[] imagesRadioButton = new JRadioButton[14];
    String[] imagesNames= {"1.png","2.png","3.png","4.png","5.png","6.png","7.png","8.png","9.png","10.png","11.png","12.png","13.png","14.png"};
	ButtonGroup radioButtonsGroup = new ButtonGroup();
	PlayerControllerJDBC PlayersControll = new PlayerControllerJDBC();
	public JoinRoomPageWithName(int roomID) {
		super("Complete your Profile");
		this.RoomID = roomID;
    	this.PlayerID = (int)(Math.random()*100000000);
    	System.out.println(RoomID);
    	System.out.println(PlayerID);
    	roomLabel = new JLabel("Room Id : "+RoomID,JLabel.CENTER);
    	roomLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
    	nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        JLabel image = new JLabel(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\quiz.png"));
        
        
        for(int i = 0 ; i < imagesNames.length ; i++) {
        	
        	JPanel OneImage = new JPanel(new BorderLayout());
        	imagesRadioButton[i] = new JRadioButton(imagesNames[i]);
        	radioButtonsGroup.add(imagesRadioButton[i]);
        	OneImage.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.orange,2),""));
        	OneImage.add(new JLabel(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\"+imagesNames[i]),50,50)));
        	OneImage.add(imagesRadioButton[i],BorderLayout.SOUTH);
        	imagesPanel.add(OneImage);
        }
        
        
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        
        formPanel.add(imagesPanel,BorderLayout.CENTER);
        formPanel.add(namePanel,BorderLayout.NORTH);
        formPanel.add(startButton,BorderLayout.SOUTH);
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,4),"Complete your profile to start"));
        
        add(roomLabel,BorderLayout.NORTH);
        add(formPanel,BorderLayout.CENTER);
        //add(image,BorderLayout.WEST);
        
        
        
        
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        this.setVisible(true);
        startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Nom=nameField.getText();
				String img = "";
				for(JRadioButton button : imagesRadioButton)
					if(button.isSelected())
						img=button.getText();
				if(Nom.isEmpty())
					JOptionPane.showMessageDialog(JoinRoomPageWithName.this, "Please enter a Valid Name");
				else if(img.isEmpty())
					JOptionPane.showMessageDialog(JoinRoomPageWithName.this, "Please choose a image to start !");
				else {
					int rowsInserted = PlayersControll.ajoutPlayer(new Player(PlayerID, Nom, 0, img),RoomID);
					if (rowsInserted > 0) {
		                   dispose();
		                   new WaitRoomToStartPage(RoomID, PlayerID, Nom,getSize(),getLocation());
		                 
		              }
				}
				
				
			}
		});
	}

	
	ImageIcon resize(ImageIcon icon,int width, int height) {
    	
    	Image img = icon.getImage();
    	Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    	return new ImageIcon(newImg);
    }


	public static void main(String[] args) {
		
		new JoinRoomPageWithName(5);
	}

}
