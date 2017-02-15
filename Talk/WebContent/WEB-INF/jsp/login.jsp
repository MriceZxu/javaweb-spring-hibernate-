<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TALK</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />

<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/login.js"></script>
<script src="js/bootstrap.min.js"></script>

</head>
<body onload="load()">
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-4 column"></div>
			<div class="col-md-4 column">
				<div class="page-header">
					<h1>TALK</h1>
				</div>
			</div>
			<div class="col-md-4 column"></div>
		</div>
		<div class="row clearfix">
			<div class="col-md-4 column"></div>
			<div class="col-md-4 column">
				<div id="errorMessage" class="alert alert-warning">${warnMessage}</div>
				<form:form method="POST"  modelAttribute="account"  action="/Talk/login.do">
					<table>
						<tr>
							<td><form:label path="name" >名字：</form:label></td>
							<td><form:input path="name" id="name" class="form-control input-sm"/></td>
						</tr>
						<tr>
							<td><form:label path="password">密码：</form:label></td>
							<td><form:password path="password" id="password" class="form-control input-sm"/></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="登录" /></td>
						</tr>
					</table>
				</form:form>
			</div>
			<div class="col-md-4 column">
				<form:form method="GET" action="/Talk/regist.do">
					<table>
						<tr>
							<td colspan="2"><input type="submit" value="注册" /></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>