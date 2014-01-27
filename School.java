import com.google.appengine.api.users.*;
import com.google.appengine.api.datastore.*;
import java.io.IOException;
import javax.servlet.http.*;

public class School extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if (user != null) {
			response.setContentType("text/plain");
			response.getWriter().println("Hello, " + user.getNickname());
		}
		else {
			resp.sendRedirect(userService.createLoginURL(req.getRequestURL()));
		}

		String schoolcode = request.getParameter("school_code");
		String schoolname = request.getParameter("school_name");
		String state = request.getParameter("state");
		String town = request.getParameter("town");

		Key u = KeyFactory.createKey("School", user);
		Entity sch = new Entity("School", u);
		sch.setProperty("schoolcode", schoolcode);
		sch.setProperty("schoolname", schoolname);
		sch.setProperty("state", state);
		sch.setProperty("town", town);

		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		ds.put(sch);
	}
}