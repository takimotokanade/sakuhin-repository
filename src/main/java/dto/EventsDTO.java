package dto;

public class EventsDTO {
	private int eventId;
    private String eventName;
    private String eventContent;
    private int price;
    private int maxParticipants;

    public EventsDTO() {
    }
    public EventsDTO(int eventId, String eventName, String eventContent, int price, int maxParticipants) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventContent = eventContent;
        this.price = price;
        this.maxParticipants = maxParticipants;
    }

    public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}
}
