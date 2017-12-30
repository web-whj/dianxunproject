<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>口袋排行</title>
<link rel="img/LOGO.ico" href="">
<link rel="stylesheet" href="css/Adaptive.css">
<link rel="stylesheet" href="css/rankinglist.css">
<link rel="stylesheet"
	href="http://at.alicdn.com/t/font_487681_kpg3mggyv5pwg66r.css">
<script src="js/rankinglist.js"></script>
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
					href="<%=request.getContextPath()%>/DateServlet?method=Info">矿场情况</a></li>
				<li><a href="<%=path %>/counter.jsp">矿力计算</a></li>
				<li><a
					href="<%=request.getContextPath()%>/QueryTrendServlet?method=queryDe&address=0x0003fadb6afb6885e0634366a31552cab47e5599">区块查询</a></li>
				<li><a
					href="<%=request.getContextPath()%>/QKServlet?method=top"
					class="current">口袋排行</a></li>
				<li><a href="">链克论坛</a></li>
			</ul>
		</div>
	</nav>
	<img src="img/banner1.jpg" alt="" class="banner">
	<div class="content col-lg-10 col-md-10 col-sm-10 col-xs-10 col-sj-10">
		<div class="top">
			<h3>玩客币TOP100排行榜</h3>
		</div>
		<div class="nav3">
			<span class="span1">财富榜</span> <span>交易榜</span> <span>最新交易</span>
		</div>
		<input type="text" placeholder="&#xe60c; 搜索钱包地址" class="iconfont">
		<div class="table table-frist">
			<div class="table-top">
				<p>#</p>
				<p>地址</p>
				<p>余额</p>
			</div>
			<%
				Map<Integer, String> map1 = (Map<Integer, String>)request.getAttribute("map1");
			/* 	Map<Integer, Double> map2 = request.getAttribute("map2");
				Map<Integer, String> map3 = request.getAttribute("map3");
				Map<Integer, Double> map4 = request.getAttribute("map4");
				Map<Integer, Double> map5 = request.getAttribute("map5");
				Map<Integer, Double> map6 = request.getAttribute("map6"); */
			%>
			 <%Set<Integer> set = map1.keySet();
	              Iterator<Integer> it = set.iterator();
	               Integer ie = 0;
	                  while(it.hasNext()){
	                      ie=it.next();
			  %>
			<ul>
				<li><span><%=ie %></span> <a href="<%=request.getContextPath()%>/QueryTrendServlet?method=queryDe&address=<%=map1.get(ie) %>"><%=map1.get(ie) %></a>
					<p>
						903,174. <span>1783</span>
					</p></li>
					  
				<!-- <li><span>2</span> <a href="">asdasdwadaw565486adas534d65</a>
					<p>
						903,174. <span>1783</span>
					</p></li> -->
			</ul>
			<%   }%> 
		</div>
		<div class="table">
			<div class="table-top">
				<p>#</p>
				<p>地址</p>
				<p>交易笔数</p>
			</div>
			<ul>
				<li><span>1</span> <a href="">asdasdwadaw565486adas534d65</a>
					<p>24067</p></li>
				<li><span>2</span> <a href="">asdasdwadaw565486adas534d65</a>
					<p>23680</p></li>
			</ul>
		</div>
		<div class="table">
			<div class="table-top"></div>
			<ul class="table-3">
				<li>
					<div class="top">
						<span>2017-12-24 19:10:11</span><span class="top-2">0 .01</span>
					</div> <a href="" class="cen">0x0da0d0a0xadq5564</a>
					<div class="cons">2659 wkc</div> <a href="" class="cen">0xde3adad35dads5</a>
				</li>
				<li>
					<div class="top">
						<span>2017-12-24 19:10:11</span><span class="top-2">0 .01</span>
					</div>
					<div class="cen">0x0da0d0a0xadq5564</div>
					<div class="cons">2659 wkc</div>
					<div class="cen">0xde3adad35dads5</div>
				</li>
			</ul>
		</div>
	</div>
	<footer>
		<div
			class="footer-bottom col-lg-10 col-md-10 col-sm-10 col-xs-10 col-sj-10">
			<span>Copyright©2017 xycdn.All Rights Reserved 山西省易科科技有限公司版权所有
				晋B2-20050219-1</span> <span class="footers-b">|</span> <a href=""
				class="footers"> 关于我们</a> <span class="footers-b">|</span> <a
				href="" class="footers"> 联系我们</a>
		</div>
	</footer>
</body>
</html>
