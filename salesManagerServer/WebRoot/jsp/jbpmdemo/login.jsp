<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<fieldset>
			<legend>
				登陆
			</legend>
			<form action="doLogin.jsp" method="post">
				用户名：
				<input type="text" name="username" value="" />
				<br />
				<input type="submit" />
			</form>
			使用user、manager和boss的角色登录，分别代表普通用户、部门经理和老板三种角色,各角色用户模拟如下。
			<br>
			<br />
			userRoleMap.put("users", "goulin");
			<br>
			<br />
			userRoleMap.put("manager", "like,wanghong");
			<br>
			<br />
			userRoleMap.put("boss", "zhangdx");
			<br>
			<br />
			其中like是部门1的经理，wanghong是部门2的经理，zhangdx是老板，流程图详见页面内
		</fieldset>

	</body>
</html>