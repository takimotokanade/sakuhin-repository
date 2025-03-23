package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import dto.LessonTimesDTO;

public class LessonTimesDAO {
	
	//レッスン時間を取得するメソッド
	public List<LessonTimesDTO> selectAll() {
        List<LessonTimesDTO> lessonTimesList = new ArrayList<>();
        String sql = "SELECT lesson_time_id, event_date_id, start_time, end_time, capacity, remaining_capacity FROM lesson_times";

        try {
        	Connection con = DatabaseConnection.getConnection();
            PreparedStatement prst = con.prepareStatement(sql);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                LessonTimesDTO lessonTime = new LessonTimesDTO();
                lessonTime.setLessonTimeId(rs.getInt("lesson_time_id"));
                lessonTime.setEventDateId(rs.getInt("event_date_id"));
                lessonTime.setStartTime(rs.getTime("start_time"));
                lessonTime.setEndTime(rs.getTime("end_time"));

                lessonTimesList.add(lessonTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lessonTimesList;
    }
}
