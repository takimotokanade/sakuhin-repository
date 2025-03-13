package dto;

public class LessonTimesDTO {
	private int lessonTimeId;
    private int eventDateId;
    private String startTime;
    private String endTime;

    public LessonTimesDTO() {
    }

    public LessonTimesDTO(int lessonTimeId, int eventDateId, String startTime, String endTime) {
        this.lessonTimeId = lessonTimeId;
        this.eventDateId = eventDateId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

	public int getLessonTimeId() {
		return lessonTimeId;
	}

	public void setLessonTimeId(int lessonTimeId) {
		this.lessonTimeId = lessonTimeId;
	}

	public int getEventDateId() {
		return eventDateId;
	}

	public void setEventDateId(int eventDateId) {
		this.eventDateId = eventDateId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
