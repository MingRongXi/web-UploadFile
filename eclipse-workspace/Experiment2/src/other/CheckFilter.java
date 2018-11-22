package other;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.tomcat.util.descriptor.web.LoginConfig;

/**
 * Servlet Filter implementation class Filter
 */
@WebFilter("/Filter")
public class CheckFilter implements javax.servlet.Filter {

    /**
     * Default constructor. 
     */
	private String id;
    public CheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see CheckFilter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see CheckFilter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
	     //HttpServletResponse httpResponse = (HttpServletResponse) response;
	     HttpSession session = httpRequest.getSession();
	     //String testid = request.getParameter("id");
	     //System.out.println(testid);
		if(UsefulFunc.isNull((String)session.getAttribute("id"))){
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
//			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }
		else {
			chain.doFilter(request, response);
		}

		// pass the request along the filter chain

	}
	


	/**
	 * @see CheckFilter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		id= fConfig.getInitParameter("id"); 
	}
	
	public void testPrint(String s) {
		System.out.println(s);
	}
}
