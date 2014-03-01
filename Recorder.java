import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class Recorder extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		
		if (user != null) {
			response.setContentType("text/plain");
			response.getWriter().println("Hello, " + user.getNickname());
		}
		else {
			resp.sendRedirect(userService.createLoginURL(req.getRequestURL()));
		}

		// get parameters
		String usercode = request.getParameter("recorder_code");
		String username = request.getParameter("recorder_name");
		String email = request.getParameter("recorder_email");
		String admin = request.getParameter("admin_code");

		Key u = KeyFactory.createKey("Recorder", usercode);
		Entity rec = new Entity("Recorder", u);
		rec.setProperty("usercode", usercode);
		rec.setProperty("username", username);
		rec.setProperty("email", email);
		rec.setProperty("adminCode", admin);

		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		ds.put(rec);

	}

}