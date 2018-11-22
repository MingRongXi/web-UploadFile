package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
		testPrint("goods1");

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8"); 
		response.getWriter().append("Served at: ").append(request.getContextPath());
		testPrint("goods2");
		//addGoods();
		try {
			readGoods(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		RequestDispatcher dispatcher = request.getRequestDispatcher("upload.jsp");
//		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		testPrint("goods3");

		doGet(request, response);
	}
	
//	private void addGoods() throws IOException {
//		GoodsBean goodsBean = new GoodsBean();
//		String picturePath = this.getServletContext().getRealPath("first.PNG");
//		File picture = new File(picturePath);
//		System.out.println(picture.exists());
//		InputStream inputStream = new FileInputStream(picture);
//		goodsBean.setInputStream(inputStream);
//		goodsBean.setId("1");
//		goodsBean.setName("test");
//		goodsBean.setProduct("¶«±±");
//		goodsBean.setType("aa");
//		goodsBean.setTypeNumber("11");
//		goodsBean.setDescription("bb");
//				Database database = new Database("goods");
//		database.add(sql, goodsBean);
//	}
	
	private void readGoods(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Database database = new Database();
		ArrayList<GoodsBean> goodsList = new ArrayList<>();
		ResultSet resultSet = database.querry("");
		while(resultSet.next()) {
			GoodsBean goods = new GoodsBean();
			goods.setPath(resultSet.getString("path"));
			goods.setId(resultSet.getString("id"));
			goods.setName(resultSet.getString("name"));
			goods.setProduct(resultSet.getString("product"));
			goods.setType(resultSet.getString("type"));
			goods.setTypeNumber(resultSet.getString("typeNumber"));
			goods.setDescription(resultSet.getString("description"));
			goodsList.add(goods);
		}

		request.setAttribute("goodsList", goodsList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("goods.jsp");
		dispatcher.forward(request, response);
	}
	private void testPrint(String s) {
		System.out.println(s);
	}
}
