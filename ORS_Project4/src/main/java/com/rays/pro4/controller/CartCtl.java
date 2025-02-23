
package com.rays.pro4.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.CartBean;
import com.rays.pro4.Bean.UserBean;
import com.rays.pro4.Bean.CartBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;

import com.rays.pro4.Model.CartModel;
import com.rays.pro4.Model.UserModel;
import com.rays.pro4.Model.CartModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "CartCtl", urlPatterns = { "/ctl/CartCtl" })
public class CartCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(CartCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("customerName"))) {
			request.setAttribute("customerName", PropertyReader.getValue("error.require", "Customer Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("customerName"))) {
			request.setAttribute("customerName", "Customer Name contains alphabet only");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("product"))) {
			request.setAttribute("product", PropertyReader.getValue("error.require", "Product"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("transactionDate"))) {
			request.setAttribute("transactionDate", PropertyReader.getValue("error.require", "Transaction Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("transactionDate"))) {
			request.setAttribute("transactionDate", PropertyReader.getValue("error.date", "Transaction Date"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("quantity"))) {
			request.setAttribute("quantity", PropertyReader.getValue("error.require", "Quantity"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("quantity"))) {
			request.setAttribute("quantity", PropertyReader.getValue("error.integer", "Quantity"));
			pass = false;
		}

		log.debug("CartCtl Method validate Ended");

		return pass;
	}

	protected BaseBean populateBean(HttpServletRequest request) {
		System.out.println(" uctl Base bean P bean");
		log.debug("CartCtl Method populatebean Started");

		CartBean bean = new CartBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setCustomerName(DataUtility.getString(request.getParameter("customerName")));

		bean.setProduct(DataUtility.getString(request.getParameter("product")));

		bean.setTransactionDate(DataUtility.getDate(request.getParameter("transactionDate")));

		bean.setQuantity(DataUtility.getString(request.getParameter("quantity")));

		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("CartCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		CartModel model = new CartModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		System.out.println("Cart Edit Id >= " + id);
		if (id != 0 && id > 0) {
			System.out.println("in id > 0  condition");
			CartBean bean;
			try {
				bean = model.findByPK(id);
				System.out.println(bean);
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("CartCtl Method doGet Ended");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("uctl Do Post");

		log.debug("CartCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println(">>>><<<<>><<><<><>**********" + id + op);

		CartModel model = new CartModel();
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			CartBean bean = (CartBean) populateBean(request);
			System.out.println(" U ctl DoPost 11111111");

			try {
				if (id > 0) {
 
					// SRystem.out.println("hi i am in dopost update");
					model.update(bean);
					ServletUtility.setBean(bean, request);
					System.out.println(" U ctl DoPost 222222");
					ServletUtility.setSuccessMessage("Cart is successfully Updated", request);

				} else {
					System.out.println(" U ctl DoPost 33333");
					long pk = model.add(bean);
					// bean.setId(pk);
					ServletUtility.setBean(bean, request);

					ServletUtility.setSuccessMessage("Cart is successfully Added", request);
					//ServletUtility.forward(getView(), request, response);
					bean.setId(pk);
				}
				/*
				 * ServletUtility.setBean(bean, request);
				 * ServletUtility.setSuccessMessage("User is successfully saved", request);
				 */

			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			System.out.println(" U ctl D p 5555555");

			CartBean bean = (CartBean) populateBean(request);
			try {
				model.delete(bean);
				System.out.println(" u ctl D Post  6666666");
				ServletUtility.redirect(ORSView.CART_CTL, request, response);
				return;
			} catch (Exception e) {
				// TODO: handle exception
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println(" U  ctl Do post 77777");

			ServletUtility.redirect(ORSView.CART_LIST_CTL, request, response);
			return;
		}
		log.debug("Cart Method doPostEnded");
		ServletUtility.forward(getView(), request, response);


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.ors.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		return ORSView.CART_VIEW;
	}

}
