package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Dao.BeeDAO;
import Model.Bee;

@WebServlet("/bee")

/* HaiNT47 */

public class BeeAjax extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();
		BeeDAO beeDAO = new BeeDAO();
		String action = req.getParameter("action");
		if(action.equalsIgnoreCase("createBee")) {
			out.print(gson.toJson(beeDAO.randomBee()));
			out.flush();
			out.close();
		} else if(action.equalsIgnoreCase("damage")) {
			int damage = Integer.parseInt(req.getParameter("number"));
			out.print(gson.toJson(beeDAO.damgeBee(damage)));
			out.flush();
			out.close();
		} else if(action.equalsIgnoreCase("save")) {
			out.print(gson.toJson(beeDAO.saveBee()));
			out.flush();
			out.close();
		} else if(action.equalsIgnoreCase("load")) {
			out.print(gson.toJson(beeDAO.loadBee()));
			out.flush();
			out.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
