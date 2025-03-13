package dto;

public class ReserveDTO {
	private int reserveId;
    private int userId;
    private int lessonTimeId;
    private String lastName;
    private String firstName;
    private int age;
    private int height;
    private String dominantHand;
    
    public ReserveDTO() {
    	
    }
    
    public ReserveDTO(int reserveId, int userId, int lessonTimeId, String lastName, String firstName, int age, int height, String dominantHand) {
        this.reserveId = reserveId;
        this.userId = userId;
        this.lessonTimeId = lessonTimeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.height = height;
        this.dominantHand = dominantHand;
    }
    
	public int getReserveId() {
		return reserveId;
	}
	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getLessonTimeId() {
		return lessonTimeId;
	}
	public void setLessonTimeId(int lessonTimeId) {
		this.lessonTimeId = lessonTimeId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getDominantHand() {
		return dominantHand;
	}
	public void setDominantHand(String dominantHand) {
		this.dominantHand = dominantHand;
	}
}
