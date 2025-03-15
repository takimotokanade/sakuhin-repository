package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import dto.EventsDTO;

public class EventsDAO {
	
	//イベントテーブルを取得するメソッド
	public List<EventsDTO> selectAll() {
		List<EventsDTO> eventList = new ArrayList<>();
		String sql = "SELECT event_name, price, event_content, event_id FROM events ;";
		
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement prst = con.prepareStatement(sql);
			ResultSet rs = prst.executeQuery();
			
			while (rs.next()) {
				EventsDTO event = new EventsDTO();
				event.setEventName(rs.getString("event_name"));
				event.setPrice(rs.getInt("price")); // price は数値型と想定
                event.setEventContent(rs.getString("event_content"));
                event.setEventId(rs.getInt("event_id"));
                
                eventList.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return eventList;
	}
	
	//イベント詳細情報を取得するメソッド

}
