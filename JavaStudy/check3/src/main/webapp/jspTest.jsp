<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/style.css"/>
<!--現在地からstyle.cssまで行きたいので、まず現在地を.で表記する。その後、同じ階層のcssまでいき、その下のstyle.cssに行き着く  -->
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp" %>
<!--headerを読み込む作業-->
<table class="name"><!--table要素　<table> ～ </table>
表の大枠を示す要素です。表の大きさや罫線などは、この要素の属性で設定することになります。  -->
<tr><!--tr要素　<tr> ～ </tr>
表の行を示す要素です。例えば、3行の表を作成する場合は、この要素を3つ配置することになります。  -->
<th><label for = "name1">name</label></th><!--th要素　<th> ～ </th>
表のセル（見出し用のセル）を示す要素です。td要素の代わりにこの要素を使用すると、そのセルが見出しとして扱われます。-->
<th><input type="text" name="namae" id="name1"></th>
</tr>
<tr>
<th><label for = "id1">id</label></th><!--フォームの部品をlabelタグで挟みます。
「LABEL」とは、フォームの中でフォームの項目名と構成部品（チェックボックス、ラジオボタンなど）を関連付けるためのタグです。-->
<th><input type="text" name="idmei" id="id1"></th>
</tr>
</table>
<%@ include file="footer.jsp" %>
<!--footerを読み込む作業  -->
</body>
</html>