package ch.vorburger.rover;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is useful to log messages from the browers, especially when debugging
 * mobile browsers which don't have development tools with Console like Desktop
 * browsers.
 *
 * <p>This only works with Firefox - Chrome, for some strange reason, "cancels" the XMLHttpRequest.
 *
 * @author Michael Vorburger
 */
@WebServlet(urlPatterns = {"/log/*"}, loadOnStartup = 1)
public class BrowserLogServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger LOG = LoggerFactory.getLogger(BrowserLogServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message = req.getParameter("message");
		LOG.info(message);
		resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

}
