
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Login - Contact Book</title>
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
                <td height="350px" valign="top">
                    <%-- content --%>
                    <h3>User Login</h3>
            <c:if test="${err!=null}">
                <p class="error">${err}</p>
            </c:if>
            <c:if test="${param.act eq 'lo'}">
                <p class="success">Logout Successfully! <br/>
                    Thanks for using Application.</p>
            </c:if>
            <c:if test="${param.act eq 'reg'}">
                <p class="success">User registered Successfully! <br/>
                    Please login.</p>
            </c:if>
                    <s:url var="url_login" value="/login"/>
            <f:form action="${url_login}" modelAttribute="command">
                <table border="1">
                    <tr>
                        <td>Username</td>
                        <td><f:input path="username"/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><f:password path="password"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                            <button>LOGIN</button><br/>
                            <a href="reg_form">New User Registration</a>
                        </td>
                    </tr>
                </table>
            </f:form>
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
