package servlet;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import client.SocketClient;
import client.SocketClientConstants;
import model.Automobile;

@WebServlet("/ConfigurePage")
public class ClientOption extends HttpServlet implements SocketClientConstants {
	private static final long serialVersionUID = 1L;
	private String modelName;// get the request parameter "model" from page
								// "selectmodel"
	private SocketClient client;

	/**
	 * @see HttpServlet#HttpServlet() init the servlet
	 */
	@Override
	public void init(ServletConfig config) {
		String strLocalHost = "";
		// get local IP address
		try {
			strLocalHost = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.err.println("Unable to find local host");
		}
		// start this client
		this.client = new SocketClient(strLocalHost, iDAYTIME_PORT);
		client.start();
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		client.closeSession();
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		modelName = request.getParameter("model");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// wait the client to open connection to server
		while (!client.getOpenTag()) {

		}

		// get interaction from and to server from SocketClient thread instance
		ObjectOutputStream objectOutputStream = client.getObjectOutputStream();
		ObjectInputStream objectInputStream = client.getObjectInputStream();

		// write operation 4 in server and start a dialog for getting a car
		objectOutputStream.writeObject("4");
		objectOutputStream.flush();

		// 4.1 send a model name to server
		objectOutputStream.writeObject(modelName);
		objectOutputStream.flush();

		// 4.2. get auto name list from stream
		Automobile auto = null;
		try {
			auto = (Automobile) objectInputStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		// handle thread issues
		if (auto == null) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// use this session to send model name and base price to resultpage
		HttpSession session = request.getSession();
		// handle session thread issues
		while (session == null) {

		}
		session.setAttribute("modelbaseprice", modelName + "=" + auto.getBasePrice());

		// get option and price from auto
		LinkedHashMap<String, Float> colorSet = auto.getOptionSetMap("Color");
		LinkedHashMap<String, Float> tranmmissionSet = auto.getOptionSetMap("Transmission");
		LinkedHashMap<String, Float> absSet = auto.getOptionSetMap("ABS/Traction Control");
		LinkedHashMap<String, Float> airbagSet = auto.getOptionSetMap("Side Impact Airbags");
		LinkedHashMap<String, Float> roofSet = auto.getOptionSetMap("Power Moonroof");

		// set color selection list
		String colorStr = "<select name=color>" + "<optgroup label=\"select color\">";
		for (String s : colorSet.keySet()) {
			colorStr += "<option value=\"" + s + "=" + colorSet.get(s) + "\">" + s + "</option>";
		}
		colorStr += "</optgroup>";

		// set transmission selection list
		String transStr = "<select name=transmission>" + "<optgroup label=\"select transmission\">";
		for (String s : tranmmissionSet.keySet()) {
			transStr += "<option value=\"" + s + "=" + tranmmissionSet.get(s) + "\">" + s + "</option>";
		}
		transStr += "</optgroup>";

		// set abs selection list
		String absStr = "<select name=abs>" + "<optgroup label=\"select abs\">";
		for (String s : absSet.keySet()) {
			absStr += "<option value=\"" + s + "=" + absSet.get(s) + "\">" + s + "</option>";
		}
		absStr += "</optgroup>";

		// set airbag selection list
		String airbagStr = "<select name=airbag>" + "<optgroup label=\"select airbag\">";
		for (String s : airbagSet.keySet()) {
			airbagStr += "<option value=\"" + s + "=" + airbagSet.get(s) + "\">" + s + "</option>";
		}
		airbagStr += "</optgroup>";

		// set moonroof selection list
		String moonroofStr = "<select name=moonroof>" + "<optgroup label=\"select moonroof\">";
		for (String s : roofSet.keySet()) {
			moonroofStr += "<option value=\"" + s + "=" + roofSet.get(s) + "\">" + s + "</option>";
		}
		moonroofStr += "</optgroup>";

		// save for output to webpage
		String[][] variables = { { "Make/Model:", modelName }, { "Color:", colorStr }, { "Transmission:", transStr },
				{ "ABS/Traction Control:", absStr }, { "Side Impact Air Bags:", airbagStr },
				{ "Power Moonroof", moonroofStr } };

		// output html page
		String title = "Basic Car Choice";
		out.println(ServletUtilities.headWithTitle(title) + "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1 ALIGN=\"CENTER\">"
				+ title + "</H1>\n" + "<form action=\"ResultPage.jsp\" method=\"Get\">"
				+ "<TABLE BORDER=1 ALIGN=\"CENTER\">\n");
		
		// for each table row, set the name and option lists
		for (int i = 0; i < variables.length; i++) {
			String varName = variables[i][0];
			String varValue = variables[i][1];
			if (varValue == null)
				varValue = "<I>Not specified</I>";
			out.println("<TR><TD>" + varName + "<TD>" + varValue);
		}
		out.println("<TR><TD> <TD> <input type=\"submit\" value=\"Done\">");
		out.println("</TABLE>");
		out.println("</form ></BODY></HTML>");

	}

	/** POST and GET requests handled identically. */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}