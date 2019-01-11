<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String web_path=request.getContextPath();%>

<html>
	<head>
		<meta charset="utf-8">
		<title>QYZQ</title>
		<meta name="viewpoint" content="width=device-width,initial-scale=1">
		<link rel="stylesheet" href="css/404.css" />
		<script src="<%=web_path%>/js/jquery.min.js"></script>
	</head>
	<body>
		<div class="code">
			<p>ERROR 404</p>
		</div>
		<div class="road">
			<div class="shadow">
				<div class="shelt">
					<div class="head">
						<div class="eyes">
							<div class="lefteye">
								<div class="eyeball"></div>
								<div class="eyebrow"></div>
							</div>
							<div class="righteye">
								<div class="eyeball"></div>
								<div class="eyebrow"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="hat"></div>
				<div class="bubble">
					<a href="page.html">Go back Home?</a>
				</div>
			</div>
			<p>PAGE NOT FOUND</p>
		</div>
	</body>
	<script type="text/javascript" src="<%=web_path%>/js/404.js" ></script>
</html>
