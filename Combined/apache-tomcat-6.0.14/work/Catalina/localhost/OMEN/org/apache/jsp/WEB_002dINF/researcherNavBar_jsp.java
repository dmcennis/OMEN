package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class researcherNavBar_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<td class=\"navContainer\" valign=\"top\">\n");
      out.write("<table class=\"navCell\">\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMEN/DoResearcher\">Home</a></h3>\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>Status page for ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("<!-- Simple Query -->\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMEN/SimpleQuery\">File Selection</a></h3>\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>Query the combined databases using the simple query form to obtain file lists for analysis.\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMEN/uploadSettings.jsp\">Upload Feature Settings</a></h3>\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>Upload jAudio Feature Settings file for future analysis\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMEN/uploadNewFeature.jsp\">Upload New Feature</a></h3>\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>Upload a new feature for use in the OMEN system.\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><A href=\"/OMEN/CreateSettings\">Create Feature Settings</A></h3></td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>Define feature list manually</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMEN/StartAnalysis\">Submit Analysis</a></h3></td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>Request that meta data be generated and made available for download</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMEN/DeleteEntries\">Delete Entries</a></h3>\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>Remove unwanted extra entries from the system.\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\t\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMEN/ManageAccount\">Manage Account</a></h3>\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>Change one's password or email address.\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\t\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMEN/ListEntries\">List features</a></h3></td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>List all the feature files</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("\t<td>\n");
      out.write("\t\t<table class=\"navCellBody\">\n");
      out.write("\t\t<tr class=\"titleCellBody\">\n");
      out.write("\t\t\t<td><h3><a href=\"/OMEN/LogOut\">Log Out</a></h3>\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr class=\"bodyCellBody\">\n");
      out.write("\t\t\t<td>End this session</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("</table>\n");
      out.write("</td>\n");
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
