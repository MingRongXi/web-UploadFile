package other;

import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class Listener
 *
 */
@WebListener
public class Listener implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener, ServletRequestListener, ServletRequestAttributeListener {
	private int userCounts=0;
	

    /**
     * Default constructor. 
     */
	public Listener() {
        // TODO Auto-generated constructor stub
    }
    
    public void sessionCreated(HttpSessionEvent se) {
    	userCounts++;
    	HttpSession session = se.getSession();
    	session.getServletContext().setAttribute("userCounts", userCounts);
    	System.out.println("userCounts: " + userCounts);
    }
    
    public void sessionDestroyed(HttpSessionEvent se) {
    	userCounts--;
    	HttpSession session = se.getSession();
    	session.getServletContext().setAttribute("userCounts", userCounts);
    }
    
    public int getCounts() {
    	return userCounts;
    }
}
