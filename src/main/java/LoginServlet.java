

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EventsDAO;
import dao.UsersDAO;
import database.DatabaseConnection;
import dto.EventsDTO;
import dto.UsersDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String strUserName = request.getParameter("userName");
		String strPassword = request.getParameter("password");
		
		HttpSession session = request.getSession();
		Connection con = null;
		PreparedStatement prst = null;
		String url = "/error.jsp";
		
		//ログイン処理
		UsersDAO usersDAO = new UsersDAO();
		UsersDTO usersDTO = usersDAO.login(strUserName, strPassword);
		if (usersDTO != null) {
			session.setAttribute("userId", usersDTO.getId());
			session.setAttribute("username", usersDTO.getUsername());
			session.setAttribute("password", usersDTO.getPassword());
			session.setAttribute("isAdmin", usersDTO.isAdmin());
			session.setAttribute("lastName", usersDTO.getLastName());
			session.setAttribute("firstName", usersDTO.getFirstName());
			
			if (usersDTO.isAdmin()) {
				url = "/eventListAdmin.jsp";
			} else {
				url = "/eventList.jsp";
			}
		} else {
			request.setAttribute("errorMsg", "ユーザID または パスワードに誤りがあります。");
		    url = "/login.jsp";
		}
		
		//イベントテーブルを取得
		EventsDAO eventsDAO = new EventsDAO();
		List<EventsDTO> eventList = eventsDAO.selectAll();
		request.setAttribute("LIST", eventList);
		
		//データベースからイベントテーブル、開催日テーブル、レッスンテーブル（結合したもの）を取得
		try {
			con = DatabaseConnection.getConnection();
			System.out.println("データベース接続");
			String sql = "SELECT " +
	                 "e.event_name, e.event_content, d.event_date, t.start_time, t.end_time, t.lesson_time_id, e.max_participants," +
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
			while(rs.next()) {
				ArrayList<String> event2 = new ArrayList<String>();
				event2.add(rs.getString("event_name"));
				event2.add(rs.getString("event_content"));
				event2.add(rs.getString("event_date"));
				event2.add(rs.getString("start_time"));
				event2.add(rs.getString("end_time"));
				event2.add(rs.getString("lesson_time_id"));
				event2.add(rs.getString("max_participants"));
				event2.add(rs.getString("available_seats"));  // 参加可能人数を追加
				
				eventList2.add(event2);
			}
			
			request.setAttribute("LIST2", eventList2);
			
		} catch (SQLException e) {
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
			con = DatabaseConnection.getConnection();
		    System.out.println("データベース接続");
		    
		    // ユーザーIDをセッションから取得
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
		    
		} catch (SQLException e) {
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
		
		session.removeAttribute("eventName");
		session.removeAttribute("eventContent");
		session.removeAttribute("price");
		session.removeAttribute("maxParticipants");
		session.removeAttribute("eventDates");
		session.removeAttribute("startTimes");
		session.removeAttribute("endTimes");
		session.removeAttribute("eventId");
		
		//転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
