/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.9
 * Generated at: 2021-08-10 09:15:28 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class insertForm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<script src=\"//code.jquery.com/jquery-3.6.0.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"http://dmaps.daum.net/map_js_init/postcode.v2.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("\tfunction openDaumPostcode() {\r\n");
      out.write("\t\tnew daum.Postcode({\r\n");
      out.write("\t\t\toncomplete : function(data) {\r\n");
      out.write("\t\t\t\tdocument.getElementById('zipcode').value = data.zonecode;\r\n");
      out.write("\t\t\t\tdocument.getElementById('addr').value = data.address;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}).open();\r\n");
      out.write("\t}//openDaumPostcode()---\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\tfunction check() {\r\n");
      out.write("\t\t//데이터 유효성 체크\r\n");
      out.write("\t\tif ($('#id').val() == '') {\r\n");
      out.write("\t\t\talert(\"id를 입력 하시오\");\r\n");
      out.write("\t\t\t$('#id').focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tif ($('#pw').val() == '') {\r\n");
      out.write("\t\t\talert(\"암호를 입력 하시오\");\r\n");
      out.write("\t\t\t$('#pw').focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tif ($('#pw2').val() == '') {\r\n");
      out.write("\t\t\talert(\"암호확인를 입력 하시오\");\r\n");
      out.write("\t\t\t$('#pw2').focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t//암호와 암호확인이 같은 비교\r\n");
      out.write("\t\tif ($('#pw').val() != $('#pw2').val()) {\r\n");
      out.write("\t\t\talert(\"암호와 암호확인이 다릅니다\");\r\n");
      out.write("\t\t\t$('#pw').val('');//내용삭제\r\n");
      out.write("\t\t\t$('#pw2').val('');\r\n");
      out.write("\t\t\t$('#pw').focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t//이름\r\n");
      out.write("\t\tif ($(\"#name\").val() == '') {\r\n");
      out.write("\t\t\talert(\"이름을 입력 하세요 \");\r\n");
      out.write("\t\t\t$(\"#name\").focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t}//function-end\r\n");
      out.write("\r\n");
      out.write("\t//Ajax\r\n");
      out.write("\tfunction confirmIDCheck() {\r\n");
      out.write("\t\tif ($('#id').val() == '') {\r\n");
      out.write("\t\t\talert(\"ID를 입력 하시요\");\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\t//ID가 입력 되었을때 \r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\turl : \"confirmID.do\",\r\n");
      out.write("\t\t\t\tdata : \"id=\" + $('#id').val(),//서버로 넘길 인수값\r\n");
      out.write("\t\t\t\tdataType : 'JSON',//서버가 보내준 자료 타입\r\n");
      out.write("\t\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t\tif (data.check == 1) {\r\n");
      out.write("\t\t\t\t\t\t//사용가능한 ID\r\n");
      out.write("\t\t\t\t\t\talert(\"사용가능한 ID\");\r\n");
      out.write("\t\t\t\t\t\t$('#pw').focus();\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t} else if (data.check == -1) {\r\n");
      out.write("\t\t\t\t\t\t//사용중인 ID\r\n");
      out.write("\t\t\t\t\t\talert(\"사용중인 ID\");\r\n");
      out.write("\t\t\t\t\t\t$('#id').val('').focus();\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}//success-end\r\n");
      out.write("\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}//else-end\r\n");
      out.write("\t}//cinfirmIDCheck()-end\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("h2 {\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("table {\r\n");
      out.write("\tmargin: auto;\r\n");
      out.write("\tbackground-color: ivoy;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<h2>회원가입</h2>\r\n");
      out.write("\t<form method=\"post\" action=\"insertPro.do\" onSubmit=\"return check()\">\r\n");
      out.write("\r\n");
      out.write("\t\t<table border=\"1\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>ID</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" name=\"id\" id=\"id\" size=\"20\"> <input\r\n");
      out.write("\t\t\t\t\ttype=\"button\" value=\"ID중복체크\" onclick=\"confirmIDCheck()\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>암호</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"password\" name=\"pw\" id=\"pw\" size=\"10\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>암호확인</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"password\" name=\"pw2\" id=\"pw2\" size=\"10\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>이름</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" name=\"name\" id=\"name\" size=\"30\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>이메일</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" name=\"email1\" id=\"email1\">@ <select\r\n");
      out.write("\t\t\t\t\tname=\"email2\" id=\"email2\">\r\n");
      out.write("\t\t\t\t\t\t<option value=\"@naver.com\">naver.com</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"@daum.net\">daum.net</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"@nate.com\">nate.com</option>\r\n");
      out.write("\t\t\t\t</select></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<!-- 전화번호 -->\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>전화번호</td>\r\n");
      out.write("\t\t\t\t<td><select name=\"tel1\" id=\"tel1\">\r\n");
      out.write("\t\t\t\t\t\t<option value=\"010\">010</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"017\">017</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"018\">018</option>\r\n");
      out.write("\t\t\t\t</select> <input type=\"text\" name=\"tel2\" id=\"tel2\" size=\"4\"> <input\r\n");
      out.write("\t\t\t\t\ttype=\"text\" name=\"tel3\" id=\"tel3\" size=\"4\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<!-- 우편번호 -->\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>우편번호</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" name=\"zipcode\" id=\"zipcode\" size=\"7\"\r\n");
      out.write("\t\t\t\t\treadonly> <input type=\"button\" value=\"주소검색\"\r\n");
      out.write("\t\t\t\t\tonClick=\"openDaumPostcode()\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<!-- 주소 -->\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>주소</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" name='addr' id=\"addr\" size=\"60\" readonly>\r\n");
      out.write("\t\t\t\t\t<br> 상세주소:<input type=\"text\" name=\"addr2\" id=\"addr2\" size=\"40\">\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td colspan=\"2\"><input type=\"submit\" value=\"회원가입\"> <input\r\n");
      out.write("\t\t\t\t\ttype=\"reset\" value=\"다시입력\"> <input type=\"button\"\r\n");
      out.write("\t\t\t\t\tvalue=\"가입안함\" onClick=\"location='main.do'\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
