

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
 * Servlet implementation class CreateServlet
 */
@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
        String eventName = request.getParameter("eventName");
        String eventContent = request.getParameter("eventContent");
        String price = request.getParameter("price");
        String maxParticipants = request.getParameter("maxParticipants");

        // 動的フィールドのデータを取得
        //int型の変数numEventsにリクエストパラメータから取得したイベントの数を代入する
        int numEvents = Integer.parseInt(request.getParameter("numEvents"));
        //HashMapオブジェクトを生成
        Map<Integer, Map<String, List<List<String>>>> eventDetails = new HashMap<>();

        //作成した開催日の数だけ繰り返す（最大3回まで可能）
        for (int i = 1; i <= numEvents; i++) {
        	//HashMapオブジェクトを生成する。Map<String, List<String>>型の変数eventDataに代入。
        	Map<String, List<List<String>>> eventData = new HashMap<>();
            List<List<String>> dates = new ArrayList<>();
            List<List<String>> startTimes = new ArrayList<>();
            List<List<String>> endTimes = new ArrayList<>();

         // 開催日を取得
            List<String> dateList = new ArrayList<>();
            dateList.add(request.getParameter("eventDate" + i));
            dates.add(dateList);

            // レッスン時間を取得
            List<String> startTimeList = new ArrayList<>();
            List<String> endTimeList = new ArrayList<>();
            for (int j = 1; j <= 3; j++) {
                String startTime = request.getParameter("eventTime_" + i + "_start_" + j);
                String endTime = request.getParameter("eventTime_" + i + "_end_" + j);
                if (startTime != null && !startTime.isEmpty() && endTime != null && !endTime.isEmpty()) {
                    startTimeList.add(startTime);
                    endTimeList.add(endTime);
                }
            }
            startTimes.add(startTimeList);
            endTimes.add(endTimeList);

            // イベントデータをマップに追加
            eventData.put("dates", dates);
            eventData.put("startTimes", startTimes);
            eventData.put("endTimes", endTimes);
            eventDetails.put(i, eventData);
        }

        // セッションにデータを格納
        HttpSession session = request.getSession();
        session.setAttribute("eventName", eventName);
        session.setAttribute("eventContent", eventContent);
        session.setAttribute("price", price);
        session.setAttribute("maxParticipants", maxParticipants);
        session.setAttribute("eventDetails", eventDetails);

        //転送処理
      	RequestDispatcher dispatcher = request.getRequestDispatcher("/createConfirm.jsp");
        dispatcher.forward(request, response);
	}

}
