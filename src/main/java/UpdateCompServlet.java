
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateCompServlet
 */
@WebServlet("/UpdateCompServlet")
public class UpdateCompServlet extends HttpServlet {
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

		request.setCharacterEncoding("UTF-8");
//		String eventIdStr = request.getParameter("eventId");
		String eventName = request.getParameter("eventName");
		String eventContent = request.getParameter("eventContent");
		
		HttpSession session = request.getSession();
		String strEventId = (String)session.getAttribute("eventId");
		int eventId = Integer.parseInt(strEventId);

		//初期化部分
		Connection con = null;
		PreparedStatement prst = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("データベース接続");

			String sql = "UPDATE events SET event_name = ?, event_content = ? WHERE event_id = ?";
			prst = con.prepareStatement(sql);
			prst.setString(1, eventName);
			prst.setString(2, eventContent);
			prst.setInt(3, eventId);
			prst.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != prst) {
					prst.close();
				}
				if (null != con) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//データベースからイベントテーブルを取得
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("データベース接続");
			String sql = "SELECT event_name, price, event_content, event_id FROM events ;";
			prst = con.prepareStatement(sql);
			ResultSet rs = prst.executeQuery();

			ArrayList<ArrayList<String>> eventList = new ArrayList<ArrayList<String>>();
			while (rs.next()) {
				ArrayList<String> event = new ArrayList<String>();
				event.add(rs.getString("event_name"));
				event.add(rs.getString("price"));
				event.add(rs.getString("event_id"));
				event.add(rs.getString("event_content"));
				eventList.add(event);
			}

			request.setAttribute("LIST", eventList);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//prst(SQL実行の準備オブジェクト)を閉じ、リソースを開放
				if (null != prst) {
					prst.close();
				}
				//データベース接続を閉じ、リソースを開放
				if (null != con) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//データベースからイベントテーブル、開催日テーブル、レッスンテーブル（結合したもの）を取得
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("データベース接続");
			String sql = "SELECT " +
					"e.event_name, e.event_content, d.event_date, t.start_time, t.end_time, t.lesson_time_id, e.max_participants,"
					+
					"e.max_participants - IFNULL(COUNT(r.reserve_id), 0) AS available_seats " +
					"FROM events e " +
					"LEFT JOIN event_dates d ON e.event_id = d.event_id " +
					"LEFT JOIN lesson_times t ON d.event_date_id = t.event_date_id " +
					"LEFT JOIN reserve r ON t.lesson_time_id = r.lesson_time_id " +
					"GROUP BY d.event_date, t.start_time, t.end_time, e.max_participants;";
			prst = con.prepareStatement(sql);
			System.out.println(prst.toString());
			ResultSet rs = prst.executeQuery();

			ArrayList<ArrayList<String>> eventList2 = new ArrayList<ArrayList<String>>();
			while (rs.next()) {
				ArrayList<String> event2 = new ArrayList<String>();
				event2.add(rs.getString("event_name"));
				event2.add(rs.getString("event_content"));
				event2.add(rs.getString("event_date"));
				event2.add(rs.getString("start_time"));
				event2.add(rs.getString("end_time"));
				event2.add(rs.getString("lesson_time_id"));
				event2.add(rs.getString("max_participants"));
				event2.add(rs.getString("available_seats")); // 参加可能人数を追加

				eventList2.add(event2);
			}

			request.setAttribute("LIST2", eventList2);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//prst(SQL実行の準備オブジェクト)を閉じ、リソースを開放
				if (null != prst) {
					prst.close();
				}
				//データベース接続を閉じ、リソースを開放
				if (null != con) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// データベースから予約済みレッスンIDを取得
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("データベース接続");

			// ユーザーIDをセッションから取得
//			HttpSession session = request.getSession();
			int userId = (int) session.getAttribute("userId");

			String sql = "SELECT lesson_time_id FROM reserve WHERE id = ?";
			prst = con.prepareStatement(sql);
			prst.setInt(1, userId);
			ResultSet rs = prst.executeQuery();

			ArrayList<Integer> reservedLessonIds = new ArrayList<>();
			while (rs.next()) {
				reservedLessonIds.add(rs.getInt("lesson_time_id"));
			}

			// JSPに予約済みレッスンIDを渡す
			request.setAttribute("RESERVED_LESSON_IDS", reservedLessonIds);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != prst) {
					prst.close();
				}
				if (null != con) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

//		HttpSession session = request.getSession();
		session.removeAttribute("eventName");
		session.removeAttribute("eventContent");
		session.removeAttribute("price");
		session.removeAttribute("maxParticipants");
		session.removeAttribute("eventDates");
		session.removeAttribute("startTimes");
		session.removeAttribute("endTimes");
		session.removeAttribute("eventId");

		request.setAttribute("successMessage", "”" + eventName + "”を更新しました。");

		RequestDispatcher rd = request.getRequestDispatcher("/eventListAdmin.jsp");
		rd.forward(request, response);
	}

}
