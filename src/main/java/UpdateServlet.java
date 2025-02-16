

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		// イベントIDを取得
	    String eventId = request.getParameter("eventId");
	    HttpSession session = request.getSession();
	    session.setAttribute("eventId", eventId);
	    
	    try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            // イベント情報を取得
            String eventSql = "SELECT event_name, event_content, price, max_participants FROM events WHERE event_id = ?";
            try (PreparedStatement prst = con.prepareStatement(eventSql)) {
                prst.setString(1, eventId);  // eventId をパラメータとして設定
                System.out.println("Executing SQL: " + prst.toString());  // デバッグ用のログ出力
                try (ResultSet rs = prst.executeQuery()) {
                    if (rs.next()) {
                        String eventName = rs.getString("event_name");
                        String eventContent = rs.getString("event_content");
                        String price = rs.getString("price");
                        String maxParticipants = rs.getString("max_participants");

                        // 開催日と時間を取得
                        String dateSql = "SELECT event_date, start_time, end_time FROM event_dates ed " +
                                         "JOIN lesson_times lt ON ed.event_date_id = lt.event_date_id " +
                                         "WHERE ed.event_id = ?";
                        try (PreparedStatement dateStmt = con.prepareStatement(dateSql)) {
                            dateStmt.setString(1, eventId);
                            try (ResultSet dateRs = dateStmt.executeQuery()) {
                            	Set<String> uniqueDates = new HashSet<>();
                            	List<String> eventDates = new ArrayList<>();
                            	List<List<String>> startTimes = new ArrayList<>();
                            	List<List<String>> endTimes = new ArrayList<>();

                                while (dateRs.next()) {
                                	String eventDate = dateRs.getString("event_date");
                                    
                                    if (!uniqueDates.contains(eventDate)) {
                                        uniqueDates.add(eventDate);
                                        eventDates.add(eventDate);
                                        startTimes.add(new ArrayList<>());
                                        endTimes.add(new ArrayList<>());
                                    }
                                    
                                    int index = eventDates.indexOf(eventDate);
                                    startTimes.get(index).add(dateRs.getString("start_time"));
                                    endTimes.get(index).add(dateRs.getString("end_time"));
                                }

                                // リクエストにデータをセット
//                                HttpSession session = request.getSession();
                                session.setAttribute("eventName", eventName);
                                session.setAttribute("eventContent", eventContent);
                                session.setAttribute("price", price);
                                session.setAttribute("maxParticipants", maxParticipants);
                                session.setAttribute("eventDates", eventDates);
                                session.setAttribute("startTimes", startTimes);
                                session.setAttribute("endTimes", endTimes);
                                //idを保持する
                                session.setAttribute("eventId", eventId);
                            }
                        }
                    } else {
                        // イベントが見つからない場合の処理
                        request.setAttribute("errorMsg", "指定されたイベントが見つかりません。");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "データベースエラーが発生しました。");
        }

	    //転送処理
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/update.jsp");
	    dispatcher.forward(request, response);
	}

}
