package dto;

import java.sql.Date;

public class EventDatesDTO {
	private int eventDateId;
    private int eventId;
    private Date eventDate;

	public EventDatesDTO() {
    }

    public EventDatesDTO(int eventDateId, int eventId, Date eventDate) {
        this.eventDateId = eventDateId;
        this.eventId = eventId;
        this.eventDate = eventDate;
    }

    public int getEventDateId() {
		return eventDateId;
	}

	public void setEventDateId(int eventDateId) {
		this.eventDateId = eventDateId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

}
