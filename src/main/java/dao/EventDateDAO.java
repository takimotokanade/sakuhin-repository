package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import dto.EventDatesDTO;

public class EventDateDAO {
	
	//開催日テーブルを取得するメソッド
	public List<EventDatesDTO> selectAll() {
		List<EventDatesDTO> eventDatesList = new ArrayList<>();
		String sql = "SELECT event_date_id, event_id, event_date FROM event_dates;";
		
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement prst = con.prepareStatement(sql);
			ResultSet rs = prst.executeQuery();
			
			while (rs.next()) {
				EventDatesDTO eventDate = new EventDatesDTO();
				eventDate.setEventDateId(rs.getInt("event_date_id"));
				eventDate.setEventId(rs.getInt("event_id"));
				eventDate.setEventDate(rs.getDate("event_date"));
				
				eventDatesList.add(eventDate);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return eventDatesList;
	}

}
