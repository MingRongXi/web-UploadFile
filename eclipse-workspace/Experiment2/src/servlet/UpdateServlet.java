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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UpdateServlet() {
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
					filename = System.currentTimeMillis()+".jpg";
					InputStream inputStream = item.getInputStream();
					FileOutputStream outputStream;
					target = savePath + "/" + filename;
					//String saveInDB = "file://" + target;
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
//				request.setAttribute("message", message);
//				try {
//					request.getRequestDispatcher("upload.jsp").forward(request, response);
//				} catch (ServletException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
			
			else {
				String name = item.getFieldName();
				String value = item.getString("UTF-8");
				information.add(value);
			}
		}
		System.out.println(request.getParameter("method"));
		Database database = new Database();
		if(request.getParameter("method").equals("add")) {
			database.add(information);

		}
		else if(request.getParameter("method").equals("modify")) {
			System.out.println(request.getParameter("id"));
			database.update(request.getParameter("id"), information);
		}

		for(String i: information) {
			System.out.println("s" + i);
		}
	}
}
