<%-- 
    Document   : about
    Created on : 26 Apr, 2018, 10:40:54 PM
    Author     : ww
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About - Contact Book</title>
        <s:url var="url_css" value="/static/css/style.css" />
        <link href="${url_css}" rel="stylesheet" type="text/css"/>
    </head>
    
    <s:url var="url_bg" value="/static/images/bg.jpg" />
    <body background="${url_bg}">
        <table border="1" width="80%" align="center">
            <tr>
                <td height="10px">
                    <%-- header --%> 
                    <jsp:include page="include/header.jsp"/>
                </td>
            </tr>
            <tr>
                <td height="25px">
                    <%-- menu --%> 
                    <jsp:include page="include/menu.jsp"/>
                </td>
            </tr>
            <tr>
                <td  height="350px">
                    <%-- content --%>
                    <h3 align="center">WELCOME to Contact-Book</h3>
                    <p> Hello!<br>
                      <p>This app helps you to maintain ones contact records with
                        their Name,<br> Number, Relation, Address, E-mail Id with Remark.
                      </p>
                      <p>
                        Basically, there are 3 major category of users:<br>
                        1. Admin<br> 2. User<br> 3. Guest-User<br>
                      </p>
                       <p align="center">
                        <s:url var="url_er" value="/static/images/er.jpg" /> 
                        <image src="${url_er}" />
                       </p>
                    </p>
                    
                </td>
            </tr>
            <tr>
                <td height="35px">
                <%-- footer--%>
                <jsp:include page="include/footer.jsp"/>
                </td>
            </tr>
           </table>
    </body>
</html>
