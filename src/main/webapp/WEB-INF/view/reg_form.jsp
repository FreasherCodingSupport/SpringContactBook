

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Registration - Contact Book</title>
        <s:url var="url_css" value="/static/css/style.css" />
        <link href="${url_css}" rel="stylesheet" type="text/css"/>
        <s:url var="url_jqlib" value="/static/js/jquery-3.3.1min.js"/>
        <script src="${url_jqlib}"></script>
        <script>
            $(document).ready(function(){
             ${"#id_check_avail"}.click(function(){
                $.ajax({
                    url: 'check_avail',
                    data:{user_name:$("#id_user_name").val()},
                    success: function(data){
                        $("#id_res_div").html(data);
                    }
                 });     
             });   
            });
        </script>
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
                <td height="350px">
                    <%-- content --%>
                    <h3>User Registration</h3>
            <c:if test="${err!=null}">
                <p class="error">${err}</p>
            </c:if>
                   
           <s:url var="url_reg" value="/register"/>
            <f:form action="${url_reg}" modelAttribute="command">
                <table border="1">
                    <tr>
                        <td>Name</td>
                        <td><f:input path="user.name"/></td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td><f:input path="user.phone"/></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><f:input path="user.email"/></td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><f:input path="user.address"/></td>
                    </tr>
                    <tr>
                        <td>Username</td>
                        <td><f:input id="id_user_name" path="user.username"/></td>
                       
                         <button type="button" id="id_check_avail">Check Availability!</button>
                        <div id="id_res_div"  class="error"> </div>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><f:password path="user.password"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                            <button>SUBMIT</button><br/>
                            <a href="reg_form.jsp">New User Registration</a>
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
