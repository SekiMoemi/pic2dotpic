<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>画像入力:ドット変換くん2号</title>
</head>
<body>
	<h2>ドット変換くん2号とは？</h2>
	<p>
		画像をドット絵に変換するアプリケーションです。<br>
		画像サイズは「入力画像のまま」または「正方形」のどちらかが選択できます。<br>
		(正方形の場合、短辺の大きさで中央部分がドット絵となって出力されます)
	</p>
	<h2>画像選択</h2>
	<form action="Main" method="post" enctype="multipart/form-data">
		変換する画像:<input type="file" name="file"><br>
		<input type="radio" name="size" value="nomal"checked>入力画像のまま
		<input type="radio" name="size" value="square">正方形
		<button type="submit">送信</button>
	</form>
	<%
	if(request.getAttribute("result") != null){
	%>
	<h2>入力画像</h2>
	<p><%=request.getAttribute("name") %></p>
	<img src="<%=request.getAttribute("name") %>" alt="入力画像" height="200">
	<h2>出力画像</h2>
	<p><%=request.getAttribute("result") %></p>
	<img src="<%=request.getAttribute("result") %>" alt="出力画像" height="200">
	<%
	}
	%>
</body>
</html>