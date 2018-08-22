<%-- 
    Document   : test
    Created on : 2 Apr, 2018, 11:51:08 AM
    Author     : ww
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <s:url var="url_jqlib" value="/static/js/jquery-3.3.1.min.js"/>
        <script src="${url_jqlib}"></script>
        <script>
            ${document}.ready(function(){
              //alert('JQuery is rready and integrated.!');  
              ${"id_get_time"}.cllick(function(){
                  //alert('Button Clicked!');
                  $.ajax({
                      url: 'get_time',
                      success: function(data){
                          $("#id_time").html(data);
                      }
                  });
              });
            });
        </script>
        <title>AJAX Test Page</title>
    </head>
    
    <body>
        <h1>Hello World!</h1>
        <button id="id_get_time">GET(Server Time)</button>
        <p id="id_time"></p>
    </body>
</html>
