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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import Controller.PlayerController;
import DAO.Connect_db;
import DAO.ConversationControllerJDBC;
import DAO.PlayerControllerJDBC;
import DAO.RoomControllerJDBC;
import Model.Player;
import Model.TableOfPlayer;

public class CreateRoomPage extends JFrame{
	
	int QuizID;
    int RoomID;
    JLabel RoomStartedLabel ;
    JLabel TextLabel ;
    JButton GoToRankPage;
    JButton LunchRoom=new JButton("Lunch Room");
    
    JButton sendMessageButton = new JButton("Send");
	JTextField sendMessageField = new JTextField(20);
	
    JButton tableButton = new JButton(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\table.png"),20,25));
	JButton flowButton = new JButton(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\cards.png"),20,25));
	JPanel flowPanel = new JPanel(new FlowLayout(20, 20, 20));
	JPanel ButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel containers = new JPanel(new BorderLayout());
	
	JPanel ConversationPanel = new JPanel(new BorderLayout());
	JPanel sendMessagePanel = new JPanel();
	JPanel footerPanel = new JPanel();
	JPanel MessagesPanel = new JPanel(new GridLayout(0, 1,10,10));
	JScrollPane msg = new JScrollPane(MessagesPanel);
    
    PlayerController players = new PlayerController();
    TableOfPlayer tm = new TableOfPlayer();
    JTable tbl = new JTable(tm);
    JScrollPane jsp = new JScrollPane(tbl);
    
    PlayerControllerJDBC PlayersControll = new PlayerControllerJDBC();
    RoomControllerJDBC RoomControll = new RoomControllerJDBC();
	ConversationControllerJDBC ConversationControll = new ConversationControllerJDBC();
    
	public CreateRoomPage(int quizID, Dimension ScreeSize , Point ScreenLocation) {
		super("Create a Room");
		QuizID = quizID;
        RoomID = (int) (Math.random() * 100000000);
        System.out.println(RoomID);
        
        
        try {
			
        	RoomControll.CreateRoom(RoomID, QuizID);
			
        	PlayersControll.ajoutPlayer(new Player(RoomID, "Admin", 0, "admin.png"),RoomID);
	
			 
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        
        
        ConversationPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE,4),"Conversation :"));
		msg.setPreferredSize(new Dimension(300, 200));
		sendMessagePanel.add(sendMessageField);
		sendMessagePanel.add(sendMessageButton);
		ConversationPanel.add(msg);
		ConversationPanel.add(sendMessagePanel,BorderLayout.SOUTH);
		
		
        ButtonsPanel.add(flowButton);
		ButtonsPanel.add(tableButton);
		
        RoomStartedLabel = new JLabel("room created with ID : "+RoomID,JLabel.CENTER);
        RoomStartedLabel.setForeground(Color.blue);
        RoomStartedLabel.setFont(new Font("Tahoma", Font.ITALIC, 30));
        
        TextLabel = new JLabel("Room Lunched",JLabel.CENTER);
        GoToRankPage = new JButton("Go to rank page");
        footerPanel.add(TextLabel);
        footerPanel.add(GoToRankPage);
        
        TextLabel.setForeground(Color.green);
        TextLabel.setFont(new Font("Tahoma", Font.ITALIC, 30));
        
        containers.add(ButtonsPanel,BorderLayout.NORTH);
		containers.add(flowPanel,BorderLayout.CENTER);
		
        add(RoomStartedLabel,BorderLayout.NORTH);
        add(containers,BorderLayout.CENTER);
        add(LunchRoom,BorderLayout.SOUTH);
        add(ConversationPanel,BorderLayout.EAST);

        setSize(ScreeSize);
        setLocation(ScreenLocation);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        GoToRankPage.addActionListener(c->new rankPage(RoomID, RoomID, ScreeSize, ScreenLocation));
        LunchRoom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					RoomControll.ChangeRoomStatus(RoomID);
					LunchRoom.setVisible(false);
					add(footerPanel,BorderLayout.SOUTH);
					 
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
        
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
        		ConversationControll.sendMessage(RoomID,Input);
        		sendMessageField.setText("");
                System.out.println("msg send succesful");
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        Timer timer = new Timer(5000, e ->CheckAndUpdate());
        timer.start();
    }

    private void CheckAndUpdate() {
        try {
        	List<Player> listOfPlayers =  PlayersControll.getListPlayers(RoomID);
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
                if(resultSet.getInt(2)==RoomID) {
                	JLabel text = new JLabel(resultSet.getString(3)+"  ",JLabel.RIGHT);
                	text.setForeground(Color.green);
                	text.setFont(new Font("Tahoma", Font.ITALIC, 20));
                	MessagesPanel.add(text);
                }
                else {
                	JLabel img = new JLabel(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\"+image),30,30));
        			JLabel name = new JLabel(playerName);
        			JLabel text = new JLabel(resultSet.getString(3));
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
    		player.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE,4),""));
			JLabel img = new JLabel(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\"+listOfPlayers.get(i).getImage()),100,100));
			player.add(img,BorderLayout.NORTH);
			JLabel name = new JLabel(listOfPlayers.get(i).getName(),JLabel.CENTER);
			name.setFont(new Font("Tahoma", Font.ITALIC, 20));
			player.add(name,BorderLayout.CENTER);
    		flowPanel.add(player);
    	}
    }
    
}
