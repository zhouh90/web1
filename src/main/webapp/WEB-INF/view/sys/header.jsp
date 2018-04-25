<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<!-- <button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button> -->
			
			<a class="navbar-brand" href="#">
				<img alt="Brand" src="resources/common/images/visa.png">XXX后台系统
			</a>
		</div>

		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<!-- <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
				<li><a href="#">Link</a></li> -->
				<li id="m_1" class="dropdown">
					<a id="user_manager" href="#" class="dropdown-toggle"	data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						用户管理 <span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">新增用户</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">用户权限管理</a></li>
					</ul>
				</li>
			</ul>
			<form class="navbar-form navbar-left">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li><a>欢迎：</a></li>
				<li class="dropdown"><a id="user-name" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">个人资料</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="/reset_password">修改密码</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="/sys/user/logout">安全退出</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>

