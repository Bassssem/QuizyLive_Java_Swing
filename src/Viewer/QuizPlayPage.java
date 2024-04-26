package Viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

import Controller.PlayerController;
import DAO.Connect_db;
import DAO.PlayerControllerJDBC;
import DAO.QuestionControllerJDBC;
import Model.Player;
import Model.Question;

public class QuizPlayPage extends JFrame{
	
	int score;
	int RoomID ;
	int PlayerID;
	String Nom;
	LocalDateTime startAtTime ;
	LocalDateTime endAtTime ;
	List <Question> listOfQuestions;
	int question_index = 0;
	
	JPanel header = new JPanel(new GridLayout(1,2));
	JLabel scoreLabel ;
	JLabel questionLabel;
	JButton inswer1 , inswer2 , inswer3 , inswer4 ;
	JPanel InswerGridPanel = new JPanel(new GridLayout(2,2,20,20));
	
	JPanel QuestionsBorderPanel = new JPanel(new BorderLayout());
	Color c1 = new Color(197, 174, 172);
	Color c2 = new Color(243, 201, 139);
	Color c3 = new Color(218, 165, 136);
	Color c4 = new Color(196, 109, 94);

	JPanel flowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
	PlayerController players = new PlayerController();
	PlayerControllerJDBC PlayersControll = new PlayerControllerJDBC();
	QuestionControllerJDBC QuestionControll = new QuestionControllerJDBC();
	public QuizPlayPage(int roomID , int playerID , String nomPlayer, Dimension ScreeSize , Point ScreenLocation){
		super("Quiz Play Page");
		RoomID=roomID;
		PlayerID=playerID;
		Nom=nomPlayer;
		
		listOfQuestions = QuestionControll.getListeOfQuestion(RoomID);
		
		
		JLabel nom = new JLabel("User : "+Nom,JLabel.LEFT);
		scoreLabel = new JLabel("Score : "+score);
		header.add(nom);
		header.add(scoreLabel);
		
		
		nom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		questionLabel = new JLabel(" Question : ",JLabel.CENTER);
		questionLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		inswer1 = new JButton("");
		inswer1.setBackground(c1);
		inswer1.setForeground(Color.white);
		inswer1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		inswer2 = new JButton("");	
		inswer2.setBackground(c2);
		inswer2.setForeground(Color.white);
		inswer2.setFont(new Font("Tahoma", Font.PLAIN, 20));

		inswer3 = new JButton("");
		inswer3.setBackground(c3);
		inswer3.setForeground(Color.white);
		inswer3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		inswer4 = new JButton("");
		inswer4.setBackground(c4);
		inswer4.setForeground(Color.white);
		inswer4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		
		
		InswerGridPanel.add(inswer1);
		InswerGridPanel.add(inswer2);
		InswerGridPanel.add(inswer3);
		InswerGridPanel.add(inswer4);
		
		QuestionsBorderPanel.add(questionLabel,BorderLayout.CENTER);
		QuestionsBorderPanel.add(InswerGridPanel,BorderLayout.SOUTH);
		
		flowPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE,4),"Your Friend :"));
		
		this.add(header,BorderLayout.NORTH);
		this.add(QuestionsBorderPanel,BorderLayout.CENTER);
		this.add(flowPanel,BorderLayout.SOUTH);
		

		setSize(ScreeSize);
        setLocation(ScreenLocation);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
        
        creteNextQuestion();
        
        Timer timer = new Timer(1000, e ->CheckAndUpdate());
        timer.start();
        
        inswer1.addActionListener(e->checkInswer(inswer1.getText()));
        inswer2.addActionListener(e->checkInswer(inswer2.getText()));
        inswer3.addActionListener(e->checkInswer(inswer3.getText()));
        inswer4.addActionListener(e->checkInswer(inswer4.getText()));
		
	}
	
	public void checkInswer(String ResponseSelected) {
			endAtTime = LocalDateTime.now();
			
			if(ResponseSelected!="" && ResponseSelected.equals(listOfQuestions.get(question_index).getCorrect_inswer())) {
				int second=(endAtTime.getMinute()*60+endAtTime.getSecond())-(startAtTime.getMinute()*60+startAtTime.getSecond());
				System.out.println(second);
				if(second>50)
					second=49;
				score += (1000-second*20);
				scoreLabel.setText("Score : "+score);
				
				try {
					PlayersControll.ChangeScoreOfPlayer(score, PlayerID);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			question_index++;
			if(question_index>=listOfQuestions.size()) {
				dispose();
				new rankPage(RoomID,PlayerID,getSize(),getLocation());
			}	
			else
				creteNextQuestion();
			
		}
	
	private void CheckAndUpdate() {
        try  {
        	List<Player> listOfPlayers =  PlayersControll.getListPlayers(RoomID);
            players.sortInverse(listOfPlayers);
            chargerFlowLayout(listOfPlayers);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
	
	private void chargerFlowLayout(List<Player> listOfPlayers){
    	flowPanel.removeAll();
    	flowPanel.revalidate();
    	flowPanel.repaint();
    	for(int i = 0 ; i < listOfPlayers.size() ; i++) {
    		JPanel player = new JPanel(new BorderLayout());
    		if(listOfPlayers.get(i).getId()==PlayerID)
    			player.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.green,4),""));
    		else	
    			player.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE,4),""));
			JLabel img = new JLabel(resize(new ImageIcon("C:\\Users\\fbass\\eclipse-workspace\\Quizy\\src\\assets\\"+listOfPlayers.get(i).getImage()),40,40));
			player.add(img,BorderLayout.NORTH);
			JLabel name = new JLabel(listOfPlayers.get(i).getName(),JLabel.CENTER);
			name.setFont(new Font("Tahoma", Font.PLAIN, 15));
			player.add(name,BorderLayout.CENTER);
    		flowPanel.add(player);
    	}
    }
	
	ImageIcon resize(ImageIcon icon,int width, int height) {
    	
    	Image img = icon.getImage();
    	Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    	return new ImageIcon(newImg);
    }
	
	void creteNextQuestion() {
		
		Question question = listOfQuestions.get(question_index);
		questionLabel.setText("Question : "+question.getQt());
		question.melanger();
		
		inswer1.setText(question.getInswers().get(0));
		inswer2.setText(question.getInswers().get(1));
		if(question.getInswers().get(2).equals(""))
			inswer3.setVisible(false);
		else {
			inswer3.setText(question.getInswers().get(2));
			inswer3.setVisible(true);
		}
		
		if(question.getInswers().get(3).equals(""))
			inswer4.setVisible(false);
		else {
			inswer4.setText(question.getInswers().get(3));
			inswer4.setVisible(true);
			}
		
		startAtTime = LocalDateTime.now();
		
	}

}
