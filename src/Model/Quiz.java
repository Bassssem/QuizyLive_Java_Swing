package Model;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class Quiz {
	private int id;
	private String Nom;
	private String Date;

	public Quiz(int id, String nom, String date) {
		this.id = id;
		Nom = nom;
		Date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	
	
}
