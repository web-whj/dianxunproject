<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta  charset="UTF-8">
	<meta content="8" http-equiv="refresh">
	<title>Document</title>
	<link rel="img/LOGO.ico" href="">
	<link rel="stylesheet" href="css/Adaptive.css">
	<link rel="stylesheet" href="css/index.css">
	<link rel="stylesheet" href="http://at.alicdn.com/t/font_518775_28rxw0wmc2yjsjor.css">
	<script src="js/method.js"></script>
	<script src="js/index.js"></script>
</head>
<body>
	<header>
		<div class="name col-lg-10 col-md-10 col-sm-10 col-xs-10">
			<a href="http://qm.qq.com/cgi-bin/qm/qr?k=aB9H7UnPWVmgrhgFyUv3nyZXXw8NpSdt">点击加QQ群：链克涛哥交流群
			</a>
		</div>
	</header>
	<nav>
		<div class="con col-lg-10 col-md-10 col-sm-10 col-xs-10 col-sj-10">
			<div class="logo col-lg-2 col-md-2 col-sm-2 col-xs-2 col-sj-2">
				<a href="">
				
				<img src="img/LOGO.ico" alt="" width="44px" height="44px">

				</a>
			</div>
			<ul class="col-lg-10 col-md-10 col-sm-10 col-xs-10 col-sj-10">
					<li><a href="<%=request.getContextPath()%>/DateServlet" class="current">实时行情</a></li>
				<li><a
					href="<%=request.getContextPath()%>/DateServlet?method=Info">矿场情况</a></li>
				<li><a href="<%=path %>/counter.jsp">矿力计算</a></li>
				<li><a
					href="<%=request.getContextPath()%>/QueryTrendServlet?method=queryDe&address=0x0003fadb6afb6885e0634366a31552cab47e5599">区块查询</a></li>
				<li><a
					href="<%=request.getContextPath()%>/QKServlet?method=top"
					>口袋排行</a></li>
				<li><a href="">链克论坛</a></li>
			</ul>
		</div>
	</nav>
	<img src="img/banner1.jpg" alt="" class="banner">
	<div class="content col-lg-10 col-md-10 col-sm-10 col-xs-10 col-sj-10">
		<div class="top">
			<h3>玩客币实时行情</h3>
		</div>
		<div class="top-bo">
			<span>数字货币市值TOP 100</span>
			<p><span>8</span>后刷新</p>
		</div>
		<ul>
			<li>平台</li>
			<li>最新成交价</li>
			<li>成交量
				<i class="iconfont icon-fill-expand iconfont-down"></i>
			</li>
			<li>日涨幅</li>
		</ul>
		<%
		Map<Integer, Double> map = (Map<Integer, Double>)request.getAttribute("map");
		Map<Integer, Double> map1 = (Map<Integer, Double>)request.getAttribute("map1");
		Map<Integer, Object> map2 = (Map<Integer, Object>)request.getAttribute("map2");
		Map<Integer,String> map3 = (Map<Integer, String>)request.getAttribute("map3");
		 %>
		<ul>
			<li><a href=""><%=map3.get(0) %></a></li>
			<li class="content-money">￥<%=map.get(0) %>
				<i class="iconfont icon-triangle-copy iconfont-up"></i>
				<i class="iconfont icon-fill-expand iconfont-downgreen"></i>
			</li>
			<li><%=map1.get(0) %></li>
			<li><% if(map2.get(0).equals(0.0) ){%>
				<%="--" %>
			<%}else{ %>
			<%=map2.get(0) %>%
			<%} %>
			</li>
		</ul>
		<ul>
			<li><a href=""><%=map3.get(1) %></a></li>
			<li class="content-money">￥<%=map.get(1) %>
				<i class="iconfont icon-triangle-copy iconfont-up"></i>
				<i class="iconfont icon-fill-expand iconfont-downgreen"></i>
			</li>
			<li><%=map1.get(1) %></li>
			<li>
				<% if(map2.get(1).equals(0.0) ){%>
				<%="--" %>
			<%}else{ %>
			<%=map2.get(1) %>%
			<%} %>
			</li>
		</ul>
		<ul>
			<li><a href=""><%=map3.get(2) %></a></li>
			<li class="content-money">￥<%=map.get(2) %>
				<i class="iconfont icon-triangle-copy iconfont-up"></i>
				<i class="iconfont icon-fill-expand iconfont-downgreen"></i>
			</li>
			<li><%=map1.get(2) %></li>
			<li> 
			<% if(map2.get(2).equals(0.0) ){%>
				<%="--" %>
			<%}else{ %>
			<%=map2.get(2) %>%
			<%} %>
			</li>
		</ul>
		<ul>
			<li><a href=""><%=map3.get(3) %></a></li>
			<li class="content-money">￥<%=map.get(3) %>
				<i class="iconfont icon-triangle-copy iconfont-up"></i>
				<i class="iconfont icon-fill-expand iconfont-downgreen"></i>
			</li>
			<li><%=map1.get(3) %></li>
			<li><% if(map2.get(3).equals(0.0) ){%>
				<%="--" %>
			<%}else{ %>
			<%=map2.get(3) %>%
			<%} %></li>
		</ul>
		<%
		Map<Integer, Integer> map4 = (Map<Integer, Integer>)request.getAttribute("map4");
		Map<Integer, Integer> map5 = (Map<Integer, Integer>)request.getAttribute("map5");
		Map<Integer,Integer> map6 = (Map<Integer, Integer>)request.getAttribute("map6");
		Map<Integer,Integer> map7 = (Map<Integer, Integer>)request.getAttribute("map7");
		Map<Integer,Integer> map8 = (Map<Integer, Integer>)request.getAttribute("map8");
		Map<Integer,Integer> map9 = (Map<Integer, Integer>)request.getAttribute("map9");
		Map<Integer,Integer> map10 = (Map<Integer, Integer>)request.getAttribute("map10");
		Map<Integer,String> map11 = (Map<Integer, String>)request.getAttribute("map11");
		 %> 
		 
		 <%Set<Integer> set8 = map11.keySet();
	              Iterator<Integer> it8 = set8.iterator();
	              String day = "";
	                  while(it8.hasNext()){
	                	  Integer ie8=it8.next();
	                      day = map11.get(ie8-1);
	              }%>   
		<div class="top">
			<span class="title">玩客币昨日矿场情况</span><span class="top-time"><%=day %></span>
		</div>
		
		<ul class="yesterday">
			<li>
				<p>
				<%Set<Integer> set = map10.keySet();
	              Iterator<Integer> it = set.iterator();
	               Integer ie = 0;
	                  while(it.hasNext()){
	                      ie=it.next();
	              }%>   
				
				<%=map10.get(ie) %></p>
				<p class="p-2">挖矿总量</p>
			</li>
			<li>
				<p>
				<%Set<Integer> set1 = map7.keySet();
	              Iterator<Integer> it1 = set1.iterator();
	               Integer ie1 = 0;
	                  while(it1.hasNext()){
	                      ie1=it1.next();
	              }%>  
	              <%=map7.get(ie1) %>
	               </p>
				<p class="p-2">区块高度</p>
			</li>
			<li>
				<p>
				<%Set<Integer> set2 = map9.keySet();
	              Iterator<Integer> it2 = set2.iterator();
	               Integer ie2 = 0;
	                  while(it2.hasNext()){
	                      ie2=it2.next();
	              }%>  
	              <%=map9.get(ie2) %>
				</p>
				<p class="p-2">出币数量</p>
			</li>
		</ul>
		<ul class="yesterday">
			<li>
				<p>
					<%Set<Integer> set3 = map6.keySet();
	              Iterator<Integer> it3 = set3.iterator();
	               Integer ie3 = 0;
	                  while(it3.hasNext()){
	                      ie3=it3.next();
	              }%>  
	              <%=map6.get(ie3) %>
				小时</p>
				<p class="p-2">人均在线时长</p></li>
			<li>
				<p> 
					<%Set<Integer> set4 = map4.keySet();
	              Iterator<Integer> it4 = set4.iterator();
	               Integer ie4 = 0;
	                  while(it4.hasNext()){
	                      ie4=it4.next();
	              }%>  
	              <%=map4.get(ie4) %>
				Mbps</p>
				<p class="p-2">人均贷款</p>
			</li>
			<li>
				<p>	<%Set<Integer> set5 = map5.keySet();
	              Iterator<Integer> it5 = set5.iterator();
	               Integer ie5 = 0;
	                  while(it5.hasNext()){
	                      ie5=it5.next();
	              }%>  
	              <%=map5.get(ie5) %>GB</p>
				<p class="p-2">人均储存</p>
			</li>
		</ul>
		<ul class="yesterday">
			<li>
				<p>	<%Set<Integer> set6 = map8.keySet();
	              Iterator<Integer> it6 = set6.iterator();
	               Integer ie6 = 0;
	                  while(it6.hasNext()){
	                      ie6=it6.next();
	              }%>  
	              <%=map8.get(ie6) %></p>
				<p class="p-2">昨日最高得币</p>
			</li>
			<li>
				<p>462596</p>
				<p class="p-2">预计矿机数量</p>
			</li>
			<li>
				<p>3.50</p>
				<p class="p-2">人均得币数量</p>
			</li>
		</ul>
	</div>
	<footer>
		<div class="footer-bottom col-lg-10 col-md-10 col-sm-10 col-xs-10 col-sj-10">
			<span>Copyright©2017 xycdn.All Rights Reserved 山西省易科科技有限公司版权所有 晋B2-20050219-1</span>
			<span class="footers-b">|</span>
			<a href="" class="footers">
			
			关于我们</a>
			<span class="footers-b">|</span>
			<a href="" class="footers">
				
			联系我们</a>
		</div>
	</footer>
</body>
</html>
