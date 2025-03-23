package dto;

import java.sql.Time;

public class LessonTimesDTO {
	private int lessonTimeId;
    private int eventDateId;
    private Time startTime;
    private Time endTime;

    public LessonTimesDTO() {
    }

    public LessonTimesDTO(int lessonTimeId, int eventDateId, Time startTime, Time endTime) {
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

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
}
