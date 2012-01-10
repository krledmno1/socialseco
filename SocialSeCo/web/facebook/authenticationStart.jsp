<%-- 
    Document   : authenticationStart
    Created on : Jan 7, 2012, 3:02:36 PM
    Author     : krle
--%>

<%@page import="socialseco.facebook.FacebookAuthenticator"%>
<%@page import="java.io.DataInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Facebook Login</title>
    </head>
    <body>
     
       
       <%
       FacebookAuthenticator facebookAuth = new FacebookAuthenticator();
       
       if(request.getParameter("code")==null)
       {
          response.sendRedirect(facebookAuth.doUserAuth());
       }
       else
       {
          if(facebookAuth.doAppAuth(request.getParameter("code")))
          {
              session.setAttribute("facebook_authenticator", facebookAuth);
              response.sendRedirect(facebookAuth.getAuthUrl());

          }
       }
       %>
       
      
    </body>
</html>
