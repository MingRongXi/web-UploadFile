package servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import other.Database;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UploadServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			uploadFile(request, response);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("GoodsServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void uploadFile(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException {
		//文件储存路径为项目中的一个文件夹
		String savePath = "C:\\Users\\dell\\eclipse-workspace\\Experiment2\\WebContent\\Image";
		File file = new File(savePath);
		if(!file.exists() && !file.isDirectory()) {
			System.out.println(savePath + "目录不存在,需要创建");
			file.mkdir();
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		if(!ServletFileUpload.isMultipartContent(request)) {
			System.out.println("fail");
			return;
		}
		List<FileItem> list = upload.parseRequest(request);
		for(FileItem l: list) {
			System.out.println(l);
		}
		ArrayList<String> information = new ArrayList<>();
		for(FileItem item: list) {
			String filename = item.getFieldName();
			//System.out.println(filename);
			String target = "";
			if(!item.isFormField()){
				if(filename == null || filename.trim().equals("")) {
				continue;
				}
				
				try {
					//为了防止重名，将获得的文件使用随机数命名
					filename = System.currentTimeMillis()+".jpg";
					InputStream inputStream = item.getInputStream();
					FileOutputStream outputStream;
					
					//储存路径 = 文件所在位置 + 文件名
					target = savePath + "/" + filename;
					information.add(filename);
					outputStream = new FileOutputStream(target);
					byte buffer[] = new byte[1024];
					int len = 0;
					while((len=inputStream.read(buffer)) > 0) {
						outputStream.write(buffer, 0, len);
					}
					inputStream.close();
					outputStream.close();
					item.delete();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			
			else {
				String value = item.getString("UTF-8");
				information.add(value);
			}
		}
		
		//如果增加商品，使用增加的sql语句
		Database database = new Database("goods");
		if(request.getParameter("method").equals("add")) {
			String sql = "INSERT INTO goodsinfo(path,id,name,product,type,typeNumber,address,description)values(?,?,?,?,?,?,?,?)";
			database.add(sql,information);

		}
		//如果是更新图片，就是用修改的sql语句
		else if(request.getParameter("method").equals("update")) {
			String id = request.getParameter("id");
			String sql = "UPDATE goodsinfo set path=? WHERE id="+id;
			database.update(sql, information);
		}
	}
}
