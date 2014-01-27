import com.google.appengine.api.users.*;
import com.google.appengine.api.datastore.*;
import java.io.IOException;
import javax.servlet.http.*;

public class Teacher extends HttpServlet {
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

		String usercode = request.getParameter("teacher_code");
		String username = request.getParameter("teacher_name");
		String schoolcode = request.getParameter("school_code");
		int day_joined = request.getParameter("day");
		int month_joined = request.getParameter("month");
		int year_joined = request.getParameter("year");
		Date joined = new Date(year_joined, month_joined, day_joined);

		Key u = KeyFactory.createKey("Teacher", user);
		Entity teach = new Entity("Teacher", u);
		teach.setProperty("usercode", usercode);
		teach.setProperty("username", username);
		teach.setProperty("schoolcode", schoolcode);
		teach.setProperty("joined", joined);

		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		ds.put(teach);
	}
}