package jbosswildfly.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gwcon/consoleForm")
public class ConsoleFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ConsoleFormServlet#doGet");
		System.out.println(req.getQueryString());
		resp.sendRedirect("consoleForm.html");
	}

	@Override
	protected long getLastModified(HttpServletRequest req) {
		System.out.println("ConsoleFormServlet#getLastModified");
		return super.getLastModified(req);
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ConsoleFormServlet#doHead");
		super.doHead(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ConsoleFormServlet#doPost");
		super.doPost(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ConsoleFormServlet#doPut");
		super.doPut(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ConsoleFormServlet#doDelete");
		super.doDelete(req, resp);
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ConsoleFormServlet#doOptions");
		super.doOptions(req, resp);
	}

	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ConsoleFormServlet#doTrace");
		super.doTrace(req, resp);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ConsoleFormServlet#service");
		super.service(req, resp);
	}

	public ConsoleFormServlet() {
		// TODO Auto-generated constructor stub
	}

}
