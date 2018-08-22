<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>

<s:url var="url_logout" value="/logout"/>

<c:if test="${sessionScope.userId==null}">
    <%-- guest menu --%>
<s:url var="url_reg_form" value="/reg_form" />
<s:url var="url_home" value="/home" />
<s:url var="url_about" value="/about" />
||<a href="${url_home}">HOME</a>||<a href="#">LOGIN</a>||<a href="${url_reg_form}">REGISTER</a>||<a href="${url_about}">ABOUT</a>||<a href="#">HELP</a>||
</c:if>

<c:if test="${sessionScope.userId != null && sessionScope.role == 1}">
    <%-- admin menu --%>
<s:url var="url_ahome" value="/admin/dashboard"/>
<s:url var="url_ulist" value="/admin/users"/> 
<s:url var="url_reg" value="/reg_form" />
<s:url var="url_about" value="/about" />
    ||<a href="${url_ahome}">HOME</a>||<a href="${url_ulist}">USER-LIST</a>||<a href="${url_reg}">ADD-USER</a>||<a href="${url_logout}">LOGOUT</a>||<a href="${url_about}">ABOUT</a>||<a href="#">HELP</a>||
</c:if>

<c:if test="${sessionScope.userId != null && sessionScope.role == 2}">   
    <%-- user menu --%>
<s:url var="url_uhome" value="/user/dashboard" />
<s:url var="url_cform" value="/user/contact_form" />
<s:url var="url_clist" value="/user/clist" /> 
<s:url var="url_about" value="/about" />
 ||<a href="${url_uhome}">HOME</a>||<a href="${url_cform}">ADD-CONTACT</a>||<a href="${url_clist}">CONTACT-LIST</a>||<a href="${url_logout}">LOGOUT</a>||<a href="${url_about}">ABOUT</a>||<a href="#">HELP</a>||
</c:if>