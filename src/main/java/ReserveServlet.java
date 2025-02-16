

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReserveServlet
 */
@WebServlet("/ReserveServlet")
public class ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータ受け取り
		request.setCharacterEncoding("UTF-8");
		String eventName = request.getParameter("eventName");
		String eventContent = request.getParameter("eventContent");
		String eventDate = request.getParameter("eventDate");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String lessonTimeId = request.getParameter("lessonTimeId");
		
		//セッションスコープに格納
		HttpSession session = request.getSession();
		session.setAttribute("lessonTimeId", lessonTimeId);
		
		//リクエストスコープに格納
		request.setAttribute("eventName", eventName);
		request.setAttribute("eventDate", eventDate);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("eventContent", eventContent);
		request.setAttribute("lessonTimeId", lessonTimeId);
		
		//転送処理
		RequestDispatcher dispatcher = request.getRequestDispatcher("/reservationForm.jsp");
        dispatcher.forward(request, response);
	}

}
