package Viewer;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;

import Controller.PlayerController;
import DAO.Connect_db;
import DAO.ConversationControllerJDBC;
import DAO.PlayerControllerJDBC;
import DAO.RoomControllerJDBC;
import Model.Player;
import Model.TableOfPlayer;

public class rankPage extends JFrame {
	private int RoomID;
	private int PlayerID;
	JLabel WelcomeLabel ;
	JLabel TextLabel ;
	JButton tableButton = new JButton(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\table.png"),20,25));
	JButton flowButton = new JButton(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\cards.png"),20,25));
	JPanel flowPanel = new JPanel(new FlowLayout(20, 50, 50));
	JPanel ButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel containers = new JPanel(new BorderLayout());
    PlayerController players = new PlayerController();
    TableOfPlayer tm = new TableOfPlayer();
    JTable tbl = new JTable(tm);
    JScrollPane jsp = new JScrollPane(tbl);
    
    JPanel ConversationPanel = new JPanel(new BorderLayout());
	JPanel sendMessagePanel = new JPanel();
	JPanel MessagesPanel = new JPanel(new GridLayout(0, 1,10,10));
	JScrollPane msg = new JScrollPane(MessagesPanel);
	
	JButton sendMessageButton = new JButton("Send");
	JTextField sendMessageField = new JTextField(20);
	
	JPanel header = new JPanel(new GridLayout(1, 2));
	JPanel BackButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JButton BackButton = new JButton("Back");
	
    PlayerControllerJDBC PlayersControll = new PlayerControllerJDBC();
    RoomControllerJDBC RoomControll = new RoomControllerJDBC();
	ConversationControllerJDBC ConversationControll = new ConversationControllerJDBC();
	
	public rankPage(int RoomID,int PlayerID, Dimension ScreeSize , Point ScreenLocation) {
		super("Rank Page");
		this.RoomID=RoomID;
		this.PlayerID=PlayerID;
		
		
		ConversationPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE,4),"Conversation :"));
		msg.setPreferredSize(new Dimension(300, 200));
		sendMessagePanel.add(sendMessageField);
		sendMessagePanel.add(sendMessageButton);
		ConversationPanel.add(msg);
		ConversationPanel.add(sendMessagePanel,BorderLayout.SOUTH);
		
		ButtonsPanel.add(flowButton);
		ButtonsPanel.add(tableButton);
		WelcomeLabel = new JLabel("Getting data ...",JLabel.CENTER);
		WelcomeLabel.setForeground(Color.blue);
		WelcomeLabel.setFont(new Font("Tahoma", Font.ITALIC, 30));
		TextLabel = new JLabel("Room Finished",JLabel.CENTER);
		TextLabel.setForeground(Color.red);
		TextLabel.setFont(new Font("Tahoma", Font.ITALIC, 30));
		
		BackButtonPanel.add(BackButton);
		
		header.add(WelcomeLabel);
		header.add(BackButtonPanel);
        
		containers.add(ButtonsPanel,BorderLayout.NORTH);
		containers.add(flowPanel,BorderLayout.CENTER);
        add(header,BorderLayout.NORTH);
        add(containers,BorderLayout.CENTER);
        add(TextLabel,BorderLayout.SOUTH);
        add(ConversationPanel,BorderLayout.EAST);

        setSize(ScreeSize);
        setLocation(ScreenLocation);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
		
	    Timer timer = new Timer(1000, e ->CheckAndUpdate());
	    timer.start();
	
		
	    flowButton.addActionListener(e->{
        	jsp.setVisible(false);
        	flowPanel.setVisible(true);
        	containers.add(flowPanel,BorderLayout.CENTER);
        });
        
        tableButton.addActionListener(e->{
        	jsp.setVisible(true);
        	flowPanel.setVisible(false);
        	containers.add(jsp,BorderLayout.CENTER);
        });
        
        sendMessageButton.addActionListener(e->{
        	String Input = sendMessageField.getText();
        	try  {
        		ConversationControll.sendMessage(this.PlayerID,Input);
        		sendMessageField.setText("");
        		
                System.out.println("msg send succesful");
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        
        BackButton.addActionListener(e->{
        	dispose();
        });
	}
	private void CheckAndUpdate() {
        try  {
        	List<Player> listOfPlayers =  PlayersControll.getListPlayers(RoomID);
        	players.trier(listOfPlayers);
            WelcomeLabel.setText("Your Rank is :"+players.FindRankWithID(this.PlayerID,listOfPlayers));
            chargerFlowLayout(listOfPlayers);
            tm.charger(listOfPlayers);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
        	MessagesPanel.removeAll();
        	MessagesPanel.revalidate();
        	MessagesPanel.repaint();
        	
        	ResultSet resultSet = ConversationControll.getMessage(RoomID);
            
            while (resultSet.next()) {
                String playerName = resultSet.getString(7)+" : ";
                String image = resultSet.getString(9);
                JPanel Message = new JPanel(new FlowLayout(FlowLayout.LEFT));
                if(resultSet.getInt(2)==PlayerID) {
                	JLabel text = new JLabel(resultSet.getString(3)+"  ",JLabel.RIGHT);
                	MessagesPanel.add(text);
                }
                else {
                	JLabel img = new JLabel(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\"+image),30,30));
        			JLabel name = new JLabel(playerName);
        			JLabel text = new JLabel(resultSet.getString(3));
        			if(resultSet.getInt(2)==RoomID) {
        				text.setForeground(Color.green);
        				text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        			}
        			Message.add(img,BorderLayout.WEST);
                    Message.add(name,BorderLayout.CENTER);
                    Message.add(text,BorderLayout.EAST);
                    MessagesPanel.add(Message);
                }
                
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

	ImageIcon resize(ImageIcon icon,int width, int height) {
	    	
	    	Image img = icon.getImage();
	    	Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    	return new ImageIcon(newImg);
	    }
	
    private void chargerFlowLayout(List<Player> listOfPlayers){
    	flowPanel.removeAll();
    	flowPanel.revalidate();
    	flowPanel.repaint();
    	
    	for(int i = 0 ; i < listOfPlayers.size() ; i++) {
    		JPanel player = new JPanel(new BorderLayout());
    		player.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE,4),"Rank : "+players.FindRankWithID(listOfPlayers.get(i).getId(),listOfPlayers)));
			JLabel score = new JLabel("Score : "+listOfPlayers.get(i).getScore(),JLabel.CENTER);
    		JLabel img = new JLabel(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\"+listOfPlayers.get(i).getImage()),100,100));
			JLabel name = new JLabel(listOfPlayers.get(i).getName(),JLabel.CENTER);
			name.setFont(new Font("Tahoma", Font.ITALIC, 20));
			player.add(score,BorderLayout.NORTH);
			player.add(img,BorderLayout.CENTER);
			player.add(name,BorderLayout.SOUTH);
    		flowPanel.add(player);
    	}
    }

}
