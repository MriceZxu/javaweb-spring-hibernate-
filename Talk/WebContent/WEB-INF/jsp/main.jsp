<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Talk</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
</head>
<body onload="load()">
	<div class="container">
		<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a id="accountName" class="navbar-brand">${talkContent.getSrcAccountName()}</a>
			</div>
			<div>
				<ul class="nav navbar-nav">
					<li><a href="#"><form:form method="GET"
								action="/Talk/regist.do">
								<table>
									<tr>
										<td><input type="submit" value="修改密码" /></td>
									</tr>
								</table>
							</form:form></a></li>
					<li><a href="#"><form:form method="GET"
								action="/Talk/logout.do">
								<input type="hidden" name="name" value=${talkContent.getSrcAccountName()} />
								<table>
									<tr>
										<td><input type="submit" value="注销" /></td>
									</tr>
								</table>
							</form:form></a></li>
					<li><a href="#"><form:form method="POST" action="/Talk/refresh.do">
							<input type="hidden" name="srcName" value=${talkContent.getSrcAccountName()} />
							<input type="hidden" name="targetName" value=${talkContent.getTargetAccountName()} />
								<table>
									<tr>
										<td><input type="submit" value="刷新" /></td>
									</tr>
								</table>
							</form:form></a></li>
				</ul>
			</div>
		</div>
		</nav>
	</div>
	<div class="row clearfix">
		<div class="col-md-1 column"></div>
		<div class="col-md-6 column">
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3 id="targetAccountName" class="panel-title">${talkContent.getTargetAccountName()}</h3>
					<form:form method="POST" action="/Talk/history.do">
						<input type="hidden" name="srcName" value=${talkContent.getSrcAccountName()} />
						<input type="hidden" name="targetName" value=${talkContent.getTargetAccountName()} />
						<table>
							<tr>
								<td><input type="submit" value="历史信息" /></td>
							</tr>
						</table>
					</form:form>
				</div>
				<div class="panel-body" data-spy="scroll"
					data-target="#navbar-example" data-offset="300"
					style="height: 300px; overflow: auto; position: relative;">
					<pre id="talks">${talks}</pre>
				</div>
			</div>
		</div>
		<div class="col-md-2 column">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">在线用户</h3>
					<h2 id="onlineNum" class="panel-title">在线人数 ${onlineNum}</h2>
				</div>
				<div class="panel-body">
					<div class="panel-body" data-spy="scroll"
						data-target="#navbar-example" data-offset="0"
						style="height: 250px; overflow: auto; position: relative;">
							<c:forEach items="${accounts}" var="account">
								<table>
									<tr>
									<td><form:form method="POST" action="/Talk/refresh.do">
											<input type="hidden" name="srcName" value=${talkContent.getSrcAccountName()} />
											<input type="hidden" name="targetName" value=${account} />
											<table>
												<tr>
													<td><input class="btn-link" type="submit" value=${account} /></td>
												</tr>
											</table>
										</form:form></td>
								</tr>
								</table>
							</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-2 column">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">消息提示</h3>
				</div>
				<div class="panel-body">
					<div class="panel-body" data-spy="scroll"
						data-target="#navbar-example" data-offset="0"
						style="height: 250px; overflow: auto; position: relative;">
						<pre id="message">${message}</pre>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-1 column"></div>
		<div class="col-md-6 column">
			<div class="input-group">
				<input id="talk" type="text" class="form-control"> 
				<span class="input-group-addon"> <button id="send" type="button" class="btn btn-default">发送</button></span>
			</div>
		</div>
		<div class="col-md-6 column"></div>
	</div>
</body>
</html>