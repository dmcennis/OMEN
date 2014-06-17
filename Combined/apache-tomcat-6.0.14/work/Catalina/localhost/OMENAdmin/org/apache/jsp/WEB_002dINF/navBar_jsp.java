package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class navBar_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<td class=\"navContainer\">\n");
      out.write("<table class=\"navCell\">\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMENAdmin/Protected/DoHome\">Administrator Home</a></h3></td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>Home page.</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMENAdmin/Protected/ManageResearchers\">Manage Researchers</a></h3></td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>Allow editing and deleting of researcher accounts.</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMENAdmin/Protected/ManageLibraries\">Manage Library Accounts</a></h3></td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>Add, delete, or edit library accounts</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMENAdmin/Protected/InitPublishFeatures\">Process New Feature Requests</a></h3></td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>View new feature submissions and either accept or reject them.</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMENAdmin/Protected/ManageData\">Manage Data</a></h3></td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>View and delete extracted features, file lists, or settings files from OMEN's repository</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMENAdmin/Protected/ManageAccount\">Manage Account</a></h3></td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>Make changes to the Administrator account</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMENAdmin/LogOut\">Log Out</a></h3></td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>Exit the administrator interface</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("</table>\n");
      out.write("</td>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
