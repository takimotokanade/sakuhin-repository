package dto;

public class EventDatesDTO {
	private int eventDateId;
    private int eventId;
    private String eventDate;

	public EventDatesDTO() {
    }

    public EventDatesDTO(int eventDateId, int eventId, String eventDate) {
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

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

}
