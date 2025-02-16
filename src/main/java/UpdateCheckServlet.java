

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateCheckServlet
 */
@WebServlet("/UpdateCheckServlet")
public class UpdateCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//定数宣言
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

        if ("confirm".equals(action)) {
            // 確認画面への遷移処理
            String eventName = request.getParameter("eventName");
            String eventContent = request.getParameter("eventContent");

            HttpSession session = request.getSession();
            session.setAttribute("eventName", eventName);
            session.setAttribute("eventContent", eventContent);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/updateConfirm.jsp");
            dispatcher.forward(request, response);
        } else if ("update".equals(action)) {
            // 更新処理
            // （ここに既存のDB更新処理を追加する）
        }
	}

}
