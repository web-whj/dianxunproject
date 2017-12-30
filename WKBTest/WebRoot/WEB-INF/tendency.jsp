<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>Document</title>
<link rel="img/LOGO.ico" href="">
<link rel="stylesheet" href="css/Adaptive.css">
<link rel="stylesheet" href="css/tendency.css">
<link rel="stylesheet"
	href="http://at.alicdn.com/t/font_518775_28rxw0wmc2yjsjor.css">
<script src="js/method.js"></script>
<script src="js/index.js"></script>
<link href="/css/basic.css" type="text/css" rel="stylesheet">
<link href="<%=path %>/css/visualize.css" type="text/css" rel="stylesheet">
<link href="<%=path %>/css/visualize-dark.css" type="text/css" rel="stylesheet">
<link href="<%=path %>/css/visualize-light.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/excanvas.js"></script>
<script type="text/javascript" src="js/visualize.jQuery.js"></script>
<script type="text/javascript" src="js/example.js"></script>
<style type="text/css">
</style>
</head>
<body>
	<header>
	<div class="name col-lg-10 col-md-10 col-sm-10 col-xs-10">
		<a
			href="http://qm.qq.com/cgi-bin/qm/qr?k=aB9H7UnPWVmgrhgFyUv3nyZXXw8NpSdt">点击加QQ群：链克涛哥交流群
		</a>
	</div>
	</header>
	<nav>
	<div class="con col-lg-10 col-md-10 col-sm-10 col-xs-10 col-sj-10">
		<div class="logo col-lg-2 col-md-2 col-sm-2 col-xs-2 col-sj-2">
			<a href=""> <img src="img/LOGO.ico" alt="" width="46px"
				height="46px">

			</a>
		</div>
		<ul class="col-lg-10 col-md-10 col-sm-10 col-xs-10 col-sj-10">
				<li><a href="<%=request.getContextPath()%>/DateServlet">实时行情</a></li>
				<li><a
					href="<%=request.getContextPath()%>/DateServlet?method=Info"  class="current">矿场情况</a></li>
				<li><a href="<%=path %>/counter.jsp">矿力计算</a></li>
				<li><a
					href="<%=request.getContextPath()%>/QueryTrendServlet?method=queryDe&address=0x0003fadb6afb6885e0634366a31552cab47e5599">区块查询</a></li>
				<li><a
					href="<%=request.getContextPath()%>/QKServlet?method=top">口袋排行</a></li>
				<li><a href="">链克论坛</a></li>
		</ul>
	</div>
	</nav>
	<img src="img/banner1.jpg" alt="" class="banner">
	<div class="content col-lg-10 col-md-10 col-sm-10 col-xs-10 col-sj-10">
<table style="display: none;">

	<caption>2017 Employee Sales by Department</caption>

	<thead>

		<tr>

			<td></td>
			<%Map<Integer,String> map11 = (Map<Integer, String>)request.getAttribute("map11"); %>
			
		<%Set<Integer> set = map11.keySet();
	              Iterator<Integer> it = set.iterator();
	               Integer ie = 0;
	                String ss = "";
	                String date = "";
	                  while(it.hasNext()){
	                      ie=it.next();
	                      if(ie%2 == 0){
	                      	 date = map11.get(ie); 
	                        ss= date.substring(4, date.length());
	             %> 
			<th scope="col" id="th"><%=ss %></th>
 <%}}%>
		</tr>

	</thead>

	<tbody>

		<tr>
			<%Map<Integer,Integer> map8 = (Map<Integer, Integer>)request.getAttribute("map8"); %>
			
		<%Set<Integer> set1 = map8.keySet();
	              Iterator<Integer> it1 = set1.iterator();
	               Integer ie1 = 0;
	               Integer date1 = 0 ;
	                  while(it1.hasNext()){
	                      ie1=it1.next();
	                    if(ie1%2 == 0){
	                      	 date1 = map8.get(ie1); 
	             %> 
			<td><%=date1 %></td>
			 <%}}%>
			 <th scope="row">人均的币数</th>
		</tr>

	</tbody>

</table>

</div>
	<footer>
	<div
		class="footer-bottom col-lg-10 col-md-10 col-sm-10 col-xs-10 col-sj-10">
		<span>Copyright©2017 xycdn.All Rights Reserved 山西省易科科技有限公司版权所有
			晋B2-20050219-1</span> <span class="footers-b">|</span> <a href=""
			class="footers"> 关于我们</a> <span class="footers-b">|</span> <a href=""
			class="footers"> 联系我们</a>
	</div>
	</footer>
</body>
</html>
