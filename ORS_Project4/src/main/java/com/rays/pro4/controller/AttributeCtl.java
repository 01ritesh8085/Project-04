/*
 * package com.rays.pro4.controller;
 * 
 * import java.io.IOException;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.annotation.WebServlet; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.apache.log4j.Logger;
 * 
 * import com.rays.pro4.Bean.BaseBean; import com.rays.pro4.Bean.AttributeBean;
 * import com.rays.pro4.Model.CartModel; import com.rays.pro4.Util.DataUtility;
 * import com.rays.pro4.Util.DataValidator; import
 * com.rays.pro4.Util.PropertyReader; import com.rays.pro4.Util.ServletUtility;
 * 
 * public class AttributeCtl {
 * 
 * @WebServlet(name = "AttributeCtl", urlPatterns = { "/ctl/AttributeCtl" })
 * public class CartCtl extends BaseCtl {
 * 
 * private static Logger log = Logger.getLogger(AttributeCtl.class);
 * 
 * @Override protected boolean validate(HttpServletRequest request) {
 * System.out.println("uctl Validate");
 * 
 * boolean pass = true;
 * 
 * if (DataValidator.isNull(request.getParameter("displayeName"))) {
 * request.setAttribute("displayeName", PropertyReader.getValue("error.require",
 * "Displaye Name")); pass = false; } else if
 * (!DataValidator.isName(request.getParameter("displayeName"))) {
 * request.setAttribute("displayeName", "Displaye Name contains alphabet only");
 * pass = false; }
 * 
 * if (DataValidator.isNull(request.getParameter("datatype"))) {
 * request.setAttribute("datatype", PropertyReader.getValue("error.require",
 * "datatype")); pass = false; }
 * 
 * if (DataValidator.isNull(request.getParameter("isActive"))) {
 * request.setAttribute("isActive", PropertyReader.getValue("error.require",
 * "IsActive")); pass = false; } else if
 * (!DataValidator.isDate(request.getParameter("transactionDate"))) {
 * request.setAttribute("transactionDate", PropertyReader.getValue("error.date",
 * "Transaction Date")); pass = false; }
 * 
 * if (DataValidator.isNull(request.getParameter("description"))) {
 * request.setAttribute("description", PropertyReader.getValue("error.require",
 * "Description")); pass = false; } else if
 * (!DataValidator.isInteger(request.getParameter("description"))) {
 * request.setAttribute("description", PropertyReader.getValue("error.integer",
 * "Description")); pass = false; }
 * 
 * log.debug("AttributeCtl Method validate Ended");
 * 
 * return pass; } } protected BaseBean populateBean(HttpServletRequest request)
 * { System.out.println(" uctl Base bean P bean");
 * log.debug("AttributeCtl Method populatebean Started");
 * 
 * AttributeBean bean = new AttributeBean();
 * 
 * bean.setId(DataUtility.getLong(request.getParameter("id")));
 * 
 * bean.setDisplayeName(DataUtility.getString(request.getParameter(
 * "customerName")));
 * 
 * bean.set(DataUtility.getString(request.getParameter("datatype ")));
 * 
 * bean.setTransactionDate(DataUtility.getDate(request.getParameter(
 * "transactionDate")));
 * 
 * bean.setQuantity(DataUtility.getString(request.getParameter("quantity")));
 * 
 * return bean;
 * 
 * }
 * 
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException {
 * log.debug("CartCtl Method doGet Started");
 * 
 * String op = DataUtility.getString(request.getParameter("operation")); // get
 * model CartModel model = new CartModel(); long id =
 * DataUtility.getLong(request.getParameter("id"));
 * System.out.println("Cart Edit Id >= " + id); if (id != 0 && id > 0) {
 * System.out.println("in id > 0  condition"); AttributeBean bean; try { bean =
 * model.findByPK(id); System.out.println(bean); ServletUtility.setBean(bean,
 * request); } catch (Exception e) { log.error(e);
 * ServletUtility.handleException(e, request, response); return; } }
 * 
 * ServletUtility.forward(getView(), request, response);
 * log.debug("CartCtl Method doGet Ended"); }
 * 
 * protected void doPost(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException {
 * System.out.println("uctl Do Post");
 * 
 * String op = DataUtility.getString(request.getParameter("operation"));
 * 
 * long id = DataUtility.getLong(request.getParameter("id"));
 * 
 * System.out.println(">>>><<<<>><<><<><<><>**********" + id + op);
 * 
 * CartModel model = new CartModel();
 * 
 * if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
 * 
 * AttributeBean bean = (AttributeBean) populateBean(request);
 * 
 * if (id > 0) {
 * 
 * try { model.update(bean); ServletUtility.setBean(bean, request);
 * ServletUtility.setSuccessMessage("Cart is successfully Updated", request); }
 * catch (Exception e) { System.out.println("Cart not update");
 * e.printStackTrace(); }
 * 
 * } else {
 * 
 * try { long pk = model.add(bean); ServletUtility.setBean(bean, request);
 * ServletUtility.setSuccessMessage("Cart is successfully Added", request);
 * bean.setId(pk); } catch (Exception e) { System.out.println("Cart not added");
 * e.printStackTrace(); }
 * 
 * }
 * 
 * } ServletUtility.forward(getView(), request, response);
 * 
 * }
 * 
 * @Override protected String getView() { return ORSView.CART_VIEW; }
 * 
 * 
 * 
 * } }
 */