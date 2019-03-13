package com.hampcode.web.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hampcode.core.model.Customer;
import com.hampcode.core.service.CustomerService;


@WebServlet("/imagen/*")
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Inject
    private CustomerService customerService;
    
	
    public ImageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String route = request.getPathInfo().substring(1);
			System.out.println("Ruta:"+request.getPathInfo());
			if (route != null && !route.equalsIgnoreCase("")) {
				int id = Integer.parseInt(route);
				Customer customer = new Customer();
				customer.setId(id);
				customer = customerService.findById(customer);
				response.setContentType(getServletContext().getMimeType("image/jpg"));
				response.setContentLength(customer.getPhoto().length);
				System.out.println("Length:"+customer.getPhoto().length);
				response.getOutputStream().write(customer.getPhoto());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
