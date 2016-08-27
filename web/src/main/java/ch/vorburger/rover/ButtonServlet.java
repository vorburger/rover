package ch.vorburger.rover;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.vorburger.raspberry.mc33926.TwoMotorsProvider;
import ch.vorburger.raspberry.motors.TwoMotors;
import ch.vorburger.raspberry.turtle.AsyncTurtle;
import ch.vorburger.raspberry.turtle.Turtle;

@WebServlet(urlPatterns = {"/button/*"}, loadOnStartup = 1)
public class ButtonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger LOG = LoggerFactory.getLogger(ButtonServlet.class);

	private static Turtle turtle;

	@Override
	public void init() throws ServletException {
		if (turtle == null) {
			TwoMotors twoMotors = new TwoMotorsProvider().get();
			turtle = new AsyncTurtle(twoMotors);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		long time = req.getParameter("time") != null ? Long.parseLong(req.getParameter("time")) : 0;
		boolean up = Boolean.parseBoolean(req.getParameter("up"));
		LOG.info("/button: name={}, up={}, time={}", name, up, time);

		if (up) {
			turtle.halt();
		} else {
			switch (name) {
			case "forward":
				turtle.forward((2.0*time) / 1000.0);
				break;

			case "back":
				turtle.backward((2.0*time) / 1000.0);
				break;

			case "left":
				turtle.turnLeft(90);
				break;

			case "right":
				turtle.turnRight(90);
				break;

			default:
				break;
			}
		}

		resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

}
