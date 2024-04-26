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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.PlayerController;
import Controller.QuestionController;
import Controller.QuizController;
import Controller.RoomController;
import DAO.Connect_db;
import DAO.ConversationControllerJDBC;
import DAO.PlayerControllerJDBC;
import DAO.QuestionControllerJDBC;
import DAO.QuizControllerJDBC;
import DAO.RoomControllerJDBC;
import Model.Player;
import Model.Question;
import Model.Quiz;
import Model.Room;
import Model.TableOfPlayer;
import Model.TableOfQuestion;
import Model.TableOfQuiz;
import Model.TableOfRoom;

public class HomePage extends JFrame{
	
	int UserID,QuizID;
	String UserName;
	JPanel tableOfQuizAndButtonPanel = new JPanel(new BorderLayout());
	JPanel tableOfRoomAndButtonPanel = new JPanel(new BorderLayout());
	JPanel tableOfQuestionAndButtonPanel = new JPanel(new BorderLayout());
	QuizController Quizs = new QuizController();
	TableOfQuiz tm = new TableOfQuiz();
	JTable tbl = new JTable(tm);
	JScrollPane jsp = new JScrollPane(tbl);
	JPanel ButtonsControl  = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel headerPanel = new JPanel(new GridLayout(1,2));
	JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JLabel welcome;
	JLabel footer;
	JButton Logout;
	JButton AddQuiz;
	JButton CreateRoom;
	JButton deleteQuiz;
	JButton deleteQuestion;
	JButton HistoryOfQuiz;
	JButton ViewQuestionOfQuiz;
	Color backgroundColor = new Color(238, 238, 238);
	
	
	QuestionController Questions = new QuestionController();
	TableOfQuestion tmOfQuestion = new TableOfQuestion();
	JTable tblOfQuestion = new JTable(tmOfQuestion);
	JScrollPane jspOfQuestion = new JScrollPane(tblOfQuestion);
	
	
	RoomController Rooms = new RoomController();
	TableOfRoom tmOfRoom = new TableOfRoom();
	JTable tblOfRoom = new JTable(tmOfRoom);
	JScrollPane jspOfRoom = new JScrollPane(tblOfRoom);
	

	RoomControllerJDBC RoomControll = new RoomControllerJDBC();
	ConversationControllerJDBC ConversationControll = new ConversationControllerJDBC();
	QuestionControllerJDBC QuestionControll = new QuestionControllerJDBC();
	QuizControllerJDBC QuizControll = new QuizControllerJDBC();
	
	public HomePage(int userID,String userName, Dimension ScreeSize , Point ScreenLocation) {
		super("Home Page");
		UserID=userID;
		UserName=userName;
		try  {
			List<Quiz> listOfQuiz = QuizControll.getListeOfQuiz(UserID);
			Quizs.insertListOfQuiz(listOfQuiz);
            tm.charger(listOfQuiz);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		try  {
			
            tmOfRoom.charger(RoomControll.getListRooms(userID));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		AddQuiz = new JButton(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\add.png"),25,25));
		AddQuiz.setBackground(backgroundColor);
		AddQuiz.setBorderPainted(false);
		CreateRoom = new JButton(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\start.png"),100,60));
		CreateRoom.setBackground(backgroundColor);
		CreateRoom.setBorderPainted(false);
		deleteQuiz = new JButton(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\delete.png"),25,25));
		deleteQuiz.setBackground(backgroundColor);
		deleteQuiz.setBorderPainted(false);
		HistoryOfQuiz = new JButton(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\view.png"),25,25));
		HistoryOfQuiz.setBackground(backgroundColor);
		HistoryOfQuiz.setBorderPainted(false);
		ViewQuestionOfQuiz = new JButton(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\flech.png"),25,25));
		ViewQuestionOfQuiz.setBackground(Color.GRAY);
		ViewQuestionOfQuiz.setBorderPainted(false);
		deleteQuestion = new JButton(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\delete.png"),25,25));
		deleteQuestion.setBackground(backgroundColor);
		deleteQuestion.setBorderPainted(false);
		
		ButtonsControl.add(AddQuiz);
		ButtonsControl.add(CreateRoom);
		ButtonsControl.add(deleteQuiz);
		
		
		tableOfRoomAndButtonPanel.add(HistoryOfQuiz,BorderLayout.NORTH);
		tableOfRoomAndButtonPanel.add(jspOfRoom);
		
		tableOfQuestionAndButtonPanel.add(deleteQuestion,BorderLayout.NORTH);
		tableOfQuestionAndButtonPanel.add(jspOfQuestion);
		
		System.out.println(getBackground());
		
		
		welcome = new JLabel(" Welcome back "+UserName+" !");
		footer = new JLabel("© 2024. All Rights Reserved At https://github.com/Bassssem/QuizyLive_Java_Swing",JLabel.CENTER);
		welcome.setFont(new Font("Tahoma", Font.PLAIN, 25));
		footer.setForeground(Color.red);
		footer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Logout = new JButton(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\logout.png"),20,25));
		
		logoutPanel.add(Logout);
		
		headerPanel.add(welcome);
		headerPanel.add(logoutPanel);
		Logout.setBackground(backgroundColor);
		Logout.setBorderPainted(false);
		
		
		tableOfQuizAndButtonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE,4),"Your Quiz :"));
		tableOfRoomAndButtonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE,4),"Your room history :"));
		tableOfQuestionAndButtonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE,4),"Your Questions :"));
		
		tableOfQuizAndButtonPanel.add(ButtonsControl,BorderLayout.NORTH);
		tableOfQuizAndButtonPanel.add(jsp,BorderLayout.CENTER);
		tableOfQuizAndButtonPanel.add(ViewQuestionOfQuiz,BorderLayout.EAST);
		
		add(headerPanel,BorderLayout.NORTH);
		add(tableOfQuizAndButtonPanel,BorderLayout.WEST);
		add(tableOfQuestionAndButtonPanel,BorderLayout.CENTER);
		add(tableOfRoomAndButtonPanel,BorderLayout.EAST);
		add(footer,BorderLayout.SOUTH);
		
		
		
		setSize(ScreeSize);
        setLocation(ScreenLocation);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		deleteQuiz.addActionListener(e->{
				
					int index = tbl.getSelectedRow();
		        	System.out.println(index);
		        	if(index==-1)
		        		JOptionPane.showMessageDialog(this, "Please choose a quiz to delete it !");
		        	else {
			        	int id = (int) tm.getValueAt(index, 0);
			        	String nomQuiz = (String) tm.getValueAt(index, 1);
			        	System.out.println(id);
			        	int confirm = JOptionPane.showConfirmDialog(this, "did you want to delete "+nomQuiz+" Quiz ?");
						if(confirm==0) {
							try {
								QuizControll.delete(id);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								tm.charger(QuizControll.getListeOfQuiz(UserID));
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
		        	}
			});
		AddQuiz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateQuiz(userID,UserName,getSize(),getLocation());
				dispose();
				
			}
		});
		CreateRoom.addActionListener(e->{
				
					int index = tbl.getSelectedRow();
		        	System.out.println(index);
		        	if(index==-1)
		        		JOptionPane.showMessageDialog(this, "Please choose a quiz to create a room !");
		        	else {
			        	int id = (int) tm.getValueAt(index, 0);
			        	System.out.println(id);
						if(Quizs.findQuiz(id)) {
							new CreateRoomPage(id,getSize(),getLocation());
							dispose();
							}
						}
		});
		Logout.addActionListener(e->{
			String fileName = "C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\Config\\QuizyAccount.txt";

	        try {
	            File file = new File(fileName);
	            FileWriter writer = new FileWriter(file, false);
	            writer.write("");
	            writer.close();
	            System.out.println("Logout Succes");

	        } catch (IOException e1) {
	            System.out.println(e1.getMessage());
	        }
			dispose();
			new JoinRoomPage();
		});
			
		HistoryOfQuiz.addActionListener(e->{
			int index = tblOfRoom.getSelectedRow();
        	System.out.println(index);
        	if(index==-1)
        		JOptionPane.showMessageDialog(this, "Please choose a room to see elhistory mte3ha !");
        	else {
	        	int RoomID = (int) tmOfRoom.getValueAt(index, 0);
	        	System.out.println(RoomID);
	        	new rankPage(RoomID, RoomID,getSize(),getLocation());
        	}
		});
		
		ViewQuestionOfQuiz.addActionListener(e->{
			
			
			int index = tbl.getSelectedRow();
        	System.out.println(index);
        	if(index==-1)
        		JOptionPane.showMessageDialog(this, "Please choose a quiz to see  their questions !");
        	else {
        		QuizID = (int) tm.getValueAt(index, 0);
            	System.out.println(QuizID);
            	try  {
            		
    	            tmOfQuestion.charger(QuestionControll.getListeOfQuestionWithID(QuizID));
    	            
    	        } catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }
        	}
        	
        	
		});
		deleteQuestion.addActionListener(e->{
			int index = tblOfQuestion.getSelectedRow();
        	System.out.println(index);
        	if(index==-1)
        		JOptionPane.showMessageDialog(this, "Please choose a question to delete it !");
        	else {
        		int id = (int) tmOfQuestion.getValueAt(index, 0);
            	System.out.println(id);
            	String Qt = (String) tmOfQuestion.getValueAt(index, 1);
	        	System.out.println(id);
	        	int confirm = JOptionPane.showConfirmDialog(this, "did you want to delete "+Qt+" Question ?");
				if(confirm==0) {
	            	try {
						QuestionControll.deleteQuestion(id);
						System.out.println(QuizID);
						tmOfQuestion.charger(QuestionControll.getListeOfQuestionWithID(QuizID));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
	

}

				 
