package shop.spring.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import shop.dao.TestDAO;
import shop.db.Database;
import shop.spring.model.TestModel;

@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");

		System.out.println("Home Page Requested");
		ModelAndView model = new ModelAndView("home");
		try {
			Database db = new Database();
			TestDAO testDAO = new TestDAO(db);
			ArrayList<TestModel> testList = testDAO.FindAll();
			db.close();
			
			request.setAttribute("testList", testList);
			
			return model;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return model;
	}
}
