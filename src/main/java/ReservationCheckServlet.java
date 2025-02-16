

import java.io.IOException;
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
 * Servlet implementation class ReservationCheckServlet
 */
@WebServlet("/ReservationCheckServlet")
public class ReservationCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String eventDate = (String)request.getParameter("eventDate");
		String startTime = (String)request.getParameter("startTime");
		String endTime = (String)request.getParameter("endTime");
		
		HttpSession session = request.getSession();
		session.setAttribute("eventDate", eventDate);
		session.setAttribute("startTime", startTime);
		session.setAttribute("endTime", endTime);
		
		int numPeople = Integer.parseInt(request.getParameter("numPeople"));
		//参加者情報を格納するためのリスト
        List<Map<String, String>> participants = new ArrayList<>();

        // 各人のフォームデータを取得
        for (int i = 0; i < numPeople; i++) {
        	//１人分の参加者情報を格納するためのマップオブジェクト生成
            Map<String, String> participant = new HashMap<>();
            participant.put("sei", request.getParameter("sei" + i));
            participant.put("mei", request.getParameter("mei" + i));
            participant.put("height", request.getParameter("height" + i));
            participant.put("age", request.getParameter("age" + i));
            participant.put("handedness", request.getParameter("handedness" + i));
            participants.add(participant);
        }

        // リクエストスコープにデータを保存
        request.setAttribute("participants", participants);
        request.setAttribute("numPeople", numPeople);

        //転送処理
		RequestDispatcher rd = request.getRequestDispatcher("/reservationConfirm.jsp");
		rd.forward(request, response);
	}

}
