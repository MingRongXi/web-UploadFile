package other;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Filter
 */
@WebFilter("/Filter")
public class CheckFilter implements javax.servlet.Filter {

    /**
     * Default constructor. 
     */
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
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
	     HttpSession session = httpRequest.getSession();
		//判断用户是否登录，如果登录就可以进入，否则重新登录
	     if(UsefulFunc.isNull((String)session.getAttribute("id"))){
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
            return;
        }
		else {
			chain.doFilter(request, response);
		}
	}
	


	/**
	 * @see CheckFilter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	public void testPrint(String s) {
		System.out.println(s);
	}
}
