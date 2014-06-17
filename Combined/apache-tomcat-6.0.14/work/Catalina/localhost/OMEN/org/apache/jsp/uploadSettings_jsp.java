package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class uploadSettings_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n");
      out.write("<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("<title>Research Homepage</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<center><h1>Uploading jAudio Settings</h1></center>\n");
      out.write("<table class=\"container\">\n");
      out.write("<tr class=\"container\" valaign=\"top\">\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/researcherNavBar.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("<!-- Main Content -->\n");
      out.write("\t<td class=\"bodyContainer\" valign=\"top\" >\n");
      out.write("\t\t<center><h2>Instructions</h2></center>\n");
      out.write("\t\t<p><ol>\n");
      out.write("\t\t\t<li>Download jAudio (<a href=\"\">here</a>)</li>\n");
      out.write("\t\t\t<li>Select desired features and settings</li>\n");
      out.write("\t\t\t<li>Select \"Save Settings\" from the File menu</li>\n");
      out.write("\t\t\t<li>Upload Settings file using the form below</li>\n");
      out.write("\t\t</ol>\n");
      out.write("\t\t<form action=\"/OMEN/ReceiveSettings\" enctype=\"multipart/form-data\" method=\"POST\">\n");
      out.write("\t\t\t<input type=\"FILE\" name=\"filename\">\n");
      out.write("\t\t\t<input type=\"SUBMIT\">\n");
      out.write("\t\t</form>\n");
      out.write("\t</td>\n");
      out.write("</tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
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
