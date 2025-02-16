
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManagementServlet
 */
@WebServlet("/ManagementServlet")
public class ManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//定数宣言
	private static final String DATABASE_NAME = "sakuhin_sample";
	private static final String PROPATIES = "?characterEncoding=utf-8";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + PROPATIES;
	private static final String USER = "root";
	private static final String PASS = "";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		doGet(request, response);
		// イベントIDを取得
		int eventId = Integer.parseInt(request.getParameter("eventId"));

		// データベースからイベント名を取得
		String eventName = getEventName(eventId);

		// データベースからイベントの開催日とレッスン時間を取得
		List<Map<String, String>> eventDates = getEventDates(eventId);

		// データベースから各レッスン時間の予約者情報を取得
		List<Map<String, String>> reservations = getReservations(eventId);

		// JSPにデータを渡す
		request.setAttribute("eventName", eventName);
		request.setAttribute("eventDates", eventDates);
		request.setAttribute("reservations", reservations);

		// JSPにフォワード
		request.getRequestDispatcher("/management.jsp").forward(request, response);
	}

	/**
	 * イベント名を取得するメソッド
	 */
	private String getEventName(int eventId) {
		String eventName = "";
		String sql = "SELECT event_name FROM events WHERE event_id = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, eventId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					eventName = rs.getString("event_name");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventName;
	}

	/**
	 * イベントの開催日とレッスン時間を取得するメソッド
	 */
	private List<Map<String, String>> getEventDates(int eventId) {
		List<Map<String, String>> eventDates = new ArrayList<>();
		String sql = "SELECT ed.event_date, lt.start_time, lt.end_time " +
				"FROM event_dates ed " +
				"JOIN lesson_times lt ON ed.event_date_id = lt.event_date_id " +
				"WHERE ed.event_id = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, eventId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Map<String, String> date = new HashMap<>();
					date.put("eventDate", rs.getString("event_date"));
					date.put("startTime", rs.getString("start_time"));
					date.put("endTime", rs.getString("end_time"));
					eventDates.add(date);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventDates;
	}

	/**
	 * 各レッスン時間の予約者情報を取得するメソッド
	 */
	private List<Map<String, String>> getReservations(int eventId) {
		List<Map<String, String>> reservations = new ArrayList<>();
		String sql = "SELECT ed.event_date, lt.start_time, lt.end_time, r.last_name, r.first_name, r.height, r.age, r.dominant_hand, " +
				"u.last_name AS user_last_name, u.first_name AS user_first_name, u.phonenumber " +
				"FROM reserve r " +
				"JOIN lesson_times lt ON r.lesson_time_id = lt.lesson_time_id " +
				"JOIN event_dates ed ON lt.event_date_id = ed.event_date_id " +
				"JOIN users u ON r.id = u.id " +
				"WHERE ed.event_id = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				System.out.println("各レッスン時間の予約者情報を取得するメソッドのSQLログに表示");
				System.out.println(pstmt.toString());

			pstmt.setInt(1, eventId);
			System.out.println(pstmt.toString());
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Map<String, String> reservation = new HashMap<>();
					reservation.put("eventDate", rs.getString("event_date"));
					reservation.put("startTime", rs.getString("start_time"));
					reservation.put("endTime", rs.getString("end_time"));
					reservation.put("lastName", rs.getString("last_name"));
					reservation.put("firstName", rs.getString("first_name"));
					reservation.put("height", rs.getString("height"));
					reservation.put("age", rs.getString("age"));
					reservation.put("dominantHand", rs.getString("dominant_hand"));
					reservation.put("userLastName", rs.getString("user_last_name"));
					reservation.put("userFirstName", rs.getString("user_first_name"));
					reservation.put("phoneNumber", rs.getString("phonenumber"));
					reservations.add(reservation);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservations;
	}

}
