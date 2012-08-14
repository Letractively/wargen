<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="css/estilo.css" />
</head>
<body>
	<div><label> <%
					if (request.getSession().getAttribute("mensagem") == null) {
						request.getSession().setAttribute("mensagem", "");
					}
	
					out.println(request.getSession().getAttribute("mensagem"));
					request.getSession().setAttribute("mensagem", "");
				 %></label></div>	
</body>
</html>