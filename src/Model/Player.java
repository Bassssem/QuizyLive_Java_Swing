package Model;

public class Player {
	private int id;
	private String name;
	private int Score;
	private String image;
	public Player(int id,String name,int Score,String image){
		this.id=id;
		this.name=name;
		this.Score=Score;
		this.image=image;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}