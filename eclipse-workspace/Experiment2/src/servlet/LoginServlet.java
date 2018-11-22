package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import other.Database;
import other.Database2;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DB_Name = "loginformation";
	//private int 
    /**
     * Default constructor. 
     */
    public LoginServlet() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(check(request, response)) {
//			RequestDispatcher dispatcher = request.getRequestDispatcher("GoodsServlet");
//			dispatcher.forward(request, response);
//			String id = request.getParameter("id");
//			HttpSession session = request.getSession();
//			session.setAttribute("id", id);
			response.sendRedirect("GoodsServlet");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("upload.jsp");
//			dispatcher.forward(request, response);
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	private boolean check(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		Database2 customer = new Database2();
		ResultSet resultSet = customer.querry(id);
		String rigthPassword = "";

		try {
			if(resultSet.next()) {
				rigthPassword = resultSet.getString("password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(password.equals(rigthPassword));
		return (password.equals(rigthPassword));
	}
}
