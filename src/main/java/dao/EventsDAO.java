package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import dto.EventDatesDTO;
import dto.EventsDTO;
import dto.LessonTimesDTO;

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
	public List<EventsDTO> selectAllEventDetails() {
		List<EventsDTO> eventList = new ArrayList<>();
		String sql = "SELECT e.event_name, e.event_content, e.price, e.event_id, " +
				"d.event_date, t.start_time, t.end_time, t.lesson_time_id " +
				"e.max_participants - IFNULL(COUNT(r.reserve_id), 0) AS available_seats " +
				"FROM events e " +
				"LEFT JOIN event_dates d ON e.event_id = d.event_id " +
				"LEFT JOIN lesson_times t ON d.event_date_id = t.event_date_id " +
				"ORDER BY e.event_id, d.event_date, t.start_time;";

		try (Connection con = DatabaseConnection.getConnection();
				PreparedStatement prst = con.prepareStatement(sql);
				ResultSet rs = prst.executeQuery()) {

			while (rs.next()) {
				EventsDTO event = new EventsDTO();
				event.setEventName(rs.getString("event_name"));
				event.setPrice(rs.getInt("price"));
				event.setEventContent(rs.getString("event_content"));
				event.setEventId(rs.getInt("event_id"));
				event.setAvailableSeats(rs.getInt("available_seats"));

				// 開催日情報をセット
				EventDatesDTO eventDate = new EventDatesDTO();
				eventDate.setEventDate(rs.getDate("event_date"));

				// レッスン時間情報をセット
				LessonTimesDTO lessonTime = new LessonTimesDTO();
				lessonTime.setStartTime(rs.getTime("start_time"));
				lessonTime.setEndTime(rs.getTime("end_time"));
				lessonTime.setLessonTimeId(rs.getInt("lesson_time_id"));

				// EventsDTO に開催日とレッスン時間を設定
				event.setEventDates(eventDate);
				event.setLessonTimes(lessonTime);

				// イベントリストに追加
				eventList.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return eventList;
	}

}
