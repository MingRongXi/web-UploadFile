package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import other.Database;
import other.GoodsBean;

/**
 * Servlet implementation class GoodsServlet
 */
@WebServlet("/GoodsServlet")
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GoodsServlet() {
    	super();
        // TODO Auto-generated constructor stub

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8"); 
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//addGoods();
		try {
			readGoods(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}
	
	/**
	 * 读取数据库中的内容，将其传到goods.jsp页面
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void readGoods(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Database database = new Database("goods");
		ArrayList<GoodsBean> goodsList = new ArrayList<>();
		String sql = "SELECT path,id,name,product,type,typeNumber,address,description FROM goodsinfo";
		ResultSet resultSet = database.querry(sql);
		while(resultSet.next()) {
			GoodsBean goods = new GoodsBean();
			goods.setPath(resultSet.getString("path"));
			goods.setId(resultSet.getString("id"));
			goods.setName(resultSet.getString("name"));
			goods.setProduct(resultSet.getString("product"));
			goods.setType(resultSet.getString("type"));
			goods.setTypeNumber(resultSet.getString("typeNumber"));
			goods.setAddress(resultSet.getString("address"));
			goods.setDescription(resultSet.getString("description"));
			goodsList.add(goods);
		}

		request.setAttribute("goodsList", goodsList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("goods.jsp");
		dispatcher.forward(request, response);
	}

}
