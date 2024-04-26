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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controller.QuestionController;
import DAO.Connect_db;
import DAO.ConversationControllerJDBC;
import DAO.QuestionControllerJDBC;
import DAO.QuizControllerJDBC;
import Model.Question;
import Model.TableOfQuestion;


public class CreateQuiz extends JFrame{
	int QuizID ;
	int UserID;
	String UserName;
	
	JPanel TablePanel = new JPanel(new BorderLayout());
	JPanel formPanel = new JPanel(new GridLayout(8, 1));
	JPanel headerPanel = new JPanel(new GridLayout(1,2));
	JPanel HomePageButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel QuizNamePanel = new JPanel();
	JPanel AddQuestionLabelPanel = new JPanel();
	JPanel QuestionPanel = new JPanel();
	JPanel CorrectInswerPanel = new JPanel();
	JPanel OtherInswer1Panel = new JPanel();
	JPanel OtherInswer2Panel = new JPanel();
	JPanel OtherInswer3Panel = new JPanel();
	JPanel SaveButtonPanel = new JPanel();
	JButton DeleteQuestion = new JButton("Delete");
	JButton HomePageButton = new JButton(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\back.png"),20,25));
	Color backgroundColor = new Color(238, 238, 238);
	QuestionController Questions = new QuestionController();
	TableOfQuestion tm = new TableOfQuestion();
	JTable tbl = new JTable(tm);
	JScrollPane jsp = new JScrollPane(tbl);
	
	
	QuestionControllerJDBC QuestionControll = new QuestionControllerJDBC();
	QuizControllerJDBC QuizControll = new QuizControllerJDBC();
	
	public CreateQuiz(int userID,String userName, Dimension ScreeSize , Point ScreenLocation) {
		super("Create a Quiz");
		UserID=userID;
		UserName=userName;
		
		
		JLabel CreateQuizLabel = new JLabel("Create Quiz : ",JLabel.LEFT);
		CreateQuizLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
		HomePageButton.setBackground(backgroundColor);
		HomePageButton.setBorderPainted(false);
		
		HomePageButtonPanel.add(HomePageButton);
		
		headerPanel.add(CreateQuizLabel);
		headerPanel.add(HomePageButtonPanel);
		
        JLabel QuizNameLabel = new JLabel("Quiz name:");
        JTextField QuizNameField = new JTextField(10);
        JButton CreateQuizButton = new JButton("Create");
        QuizNamePanel.add(QuizNameLabel);
        QuizNamePanel.add(QuizNameField);
        QuizNamePanel.add(CreateQuizButton);

		JLabel AddQuestionLabel = new JLabel("ADD Questions :");
		AddQuestionLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
		
        JLabel QuestionLabel = new JLabel("question:");
        JTextField QuestionField = new JTextField(10);
        QuestionPanel.add(QuestionLabel);
        QuestionPanel.add(QuestionField);

        JLabel correct_inswerLabel = new JLabel("correct inswer:");
        JTextField correct_inswerField = new JTextField(10);
        CorrectInswerPanel.add(correct_inswerLabel);
        CorrectInswerPanel.add(correct_inswerField);
        
        JLabel other_inswerLabel = new JLabel("other inswer:");
        JTextField other_inswerField = new JTextField(10);
        OtherInswer1Panel.add(other_inswerLabel);
        OtherInswer1Panel.add(other_inswerField);
        
        JLabel other_inswer2Label = new JLabel("other inswer:");
        JTextField other_inswer2Field = new JTextField(10);
        OtherInswer2Panel.add(other_inswer2Label);
        OtherInswer2Panel.add(other_inswer2Field);
        
        JLabel other_inswer3Label = new JLabel("other inswer:");
        JTextField other_inswer3Field = new JTextField(10);
        OtherInswer3Panel.add(other_inswer3Label);
        OtherInswer3Panel.add(other_inswer3Field);

        JButton saveQuestion = new JButton("save");
        SaveButtonPanel.add(saveQuestion);
        
        
        
        formPanel.add(QuizNamePanel);
        formPanel.add(AddQuestionLabel);
        formPanel.add(QuestionPanel);
        formPanel.add(CorrectInswerPanel);
        formPanel.add(OtherInswer1Panel);
        formPanel.add(OtherInswer2Panel);
        formPanel.add(OtherInswer3Panel);
        formPanel.add(SaveButtonPanel);
        
        AddQuestionLabel.setVisible(false);
	    QuestionLabel.setVisible(false);
	    QuestionField.setVisible(false);
	    correct_inswerLabel.setVisible(false);
	    correct_inswerField.setVisible(false);     
	    other_inswerLabel.setVisible(false);
	    other_inswerField.setVisible(false);
	    other_inswer2Label.setVisible(false);
	    other_inswer2Field.setVisible(false);
	    other_inswer3Label.setVisible(false);
	    other_inswer3Field.setVisible(false);
	    saveQuestion.setVisible(false);
	    TablePanel.setVisible(false);
        
        this.add(headerPanel,BorderLayout.NORTH);
        this.add(formPanel,BorderLayout.CENTER);
        
        TablePanel.add(jsp,BorderLayout.CENTER);
        TablePanel.add(DeleteQuestion,BorderLayout.SOUTH);
        this.add(TablePanel,BorderLayout.EAST);
        
        
        setSize(ScreeSize);
        setLocation(ScreenLocation);
        System.out.println(getSize());
        System.out.println(getLocation());
       
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        CreateQuizButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String QuizName = QuizNameField.getText();
				QuizID = (int)(Math.random()*100000000);
			    CreateQuizButton.setVisible(false);
			    QuizNameLabel.setVisible(false);
			    QuizNameField.setVisible(false);

				try {
					QuizControll.createQuiz(QuizID, userID, QuizName);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			    AddQuestionLabel.setVisible(true);
			    QuestionLabel.setVisible(true);
			    QuestionField.setVisible(true);
			    correct_inswerLabel.setVisible(true);
			    correct_inswerField.setVisible(true);     
			    other_inswerLabel.setVisible(true);
			    other_inswerField.setVisible(true);
			    other_inswer2Label.setVisible(true);
			    other_inswer2Field.setVisible(true);
			    other_inswer3Label.setVisible(true);
			    other_inswer3Field.setVisible(true);
			    TablePanel.setVisible(true);
			        
			    saveQuestion.setVisible(true);
			    TablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.magenta,4),"Quiz name : "+QuizName));
			    formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.MAGENTA,4),"Add Question : "));
				
				
			}
		});

        saveQuestion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String Qt = QuestionField.getText();
				String correct_inswer = correct_inswerField.getText();
				String other_inswer1 = other_inswerField.getText();
				String other_inswer2 = other_inswer2Field.getText();
				String other_inswer3 = other_inswer3Field.getText();
				
				try {
					 QuestionControll.ajouterQuestionToDB(QuizID, Qt,correct_inswer ,other_inswer1,other_inswer2 ,other_inswer3);
					 
					 
					 tm.charger(QuestionControll.getListeOfQuestionWithID(QuizID));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			    
			    QuestionField.setText("");
			    correct_inswerField.setText("");
			    other_inswerField.setText("");
			    other_inswer2Field.setText("");
			    other_inswer3Field.setText("");
			    
				
			}
		});

        HomePageButton.addActionListener(e->{
        	new HomePage(UserID,UserName,getSize(),getLocation());
        	dispose();
        });
        
        DeleteQuestion.addActionListener(e->{
        	int index = tbl.getSelectedRow();
        	System.out.println(index);
        	int id = (int) tm.getValueAt(index, 0);
        	
        	System.out.println(id);
        	try {
				QuestionControll.deleteQuestion(id);
				tm.charger(QuestionControll.getListeOfQuestionWithID(QuizID));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	
        });
        
	}
	
	ImageIcon resize(ImageIcon icon,int width, int height) {
    	
    	Image img = icon.getImage();
    	Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    	return new ImageIcon(newImg);
    }

}
