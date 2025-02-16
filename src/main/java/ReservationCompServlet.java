
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReservationCompServlet
 */
@WebServlet("/ReservationCompServlet")
public class ReservationCompServlet extends HttpServlet {
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
		//文字コードを設定
		request.setCharacterEncoding("UTF8");
//		int reserverCount = (int)request.getAttribute("reserverCount");
//		request.setAttribute("reserverCount", reserverCount);
		//初期値設定
		Connection con = null;
		PreparedStatement prst = null;
		//セッションスコープから値を取得
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		String strLessonTimeId = (String)session.getAttribute("lessonTimeId");
		int lessonTimeId = Integer.parseInt(strLessonTimeId);
		
		List<Map<String, String>> participants = new ArrayList<>();
	    int i = 0;

	    while (request.getParameter("participants[" + i + "].sei") != null) {
	        Map<String, String> participant = new HashMap<>();
	        participant.put("sei", request.getParameter("participants[" + i + "].sei"));
	        participant.put("mei", request.getParameter("participants[" + i + "].mei"));
	        participant.put("height", request.getParameter("participants[" + i + "].height"));
	        participant.put("age", request.getParameter("participants[" + i + "].age"));
	        participant.put("handedness", request.getParameter("participants[" + i + "].handedness"));
	        participants.add(participant);
	        i++;
	    }

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("データベース接続");
			String sql = "INSERT INTO reserve (id, lesson_time_id, last_name, first_name, age, height, dominant_hand) VALUES (?, ?, ?, ?, ?, ?, ?)";
			prst = con.prepareStatement(sql);
			//コンソールに表示
			System.out.println(prst.toString());

			for (int j = 0; j < participants.size(); j++) {
				Map<String, String> participant = participants.get(j);

				prst.setInt(1, userId); // 自動生成される場合、デフォルト値として0を指定
				prst.setInt(2, lessonTimeId); // レッスン時間ID
				prst.setString(3, participant.get("sei")); // セイ
				prst.setString(4, participant.get("mei")); // メイ
				prst.setInt(5, Integer.parseInt(participant.get("age"))); // 年齢
				prst.setInt(6, Integer.parseInt(participant.get("height"))); // 身長
				prst.setString(7, participant.get("handedness")); // 利き手
				//デバッグ
				System.out.println(prst.toString());
				prst.executeUpdate();
//				prst.addBatch(); 
			}
			// バッチを実行
//			prst.executeBatch();
			// 参加者リストから人数を取得してリクエストスコープに保存
			int numPeople = participants.size();
			request.setAttribute("numPeople", numPeople);
			
			//転送処理
			RequestDispatcher rd = request.getRequestDispatcher("/reservationComplete.jsp");
			rd.forward(request, response);

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

		
	}

}
