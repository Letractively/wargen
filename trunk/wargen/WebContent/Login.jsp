<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/estilo.css" />
<link rel="stylesheet" type="text/css" href="css/forms.css" />
<title>Realidade Aumentada Online</title>
</head>
<body>
	<div class="panel_login borda_arredondada">
		<jsp:include page="BarraMensagem.jsp" />
		<form action="/leae/EfetuarLogin" method="POST">
			<div class="float_left">
				<div>
					<div class="coluna_100 float_left" align="right"><label>Usuário</label></div>
					<div class="coluna_180 float_left"><input name="login" type="text" maxlength="30" /></div>
				</div>
				<div>
					<div class="coluna_100 float_left" align="right"><label>Senha</label></div>
					<div class="coluna_180 float_left"><input name="senha" type="password" /></div>
				</div>
			</div>
			<div class="float_left">
				<div align="center">
					<input name="acao" type="image" src="images/ico_login.png" height="64px" width="64px" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>