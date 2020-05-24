<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>TargetMol 客户合并系统</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.css" rel="stylesheet">
<!-- Toastr style -->
<link href="css/toastr.min.css" rel="stylesheet">
<!-- Sweet Alert -->
<link href="css/sweetalert.css" rel="stylesheet">

<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<!-- Ladda style -->
<link href="css/ladda-themeless.min.css" rel="stylesheet">
</head>

<body>
	<style type="text/css">
.ui-com {
	font-size: 14px;
	max-height: 100px;
	max-width: 772px;
	width: 772px;
	overflow-y: auto;
	/* 防止水平滚动条 */
	overflow-x: hidden;
}
</style>


	<div id="wrapper">

		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav metismenu" id="side-menu">
					<li class="nav-header">

						<div class="logo-element">IN+</div>
					</li>
					<li><a href="index.jsp"><i class="fa fa-calendar-plus-o"></i>
							<span class="nav-label">客户/联系人合并</span> </a></li>
					<li><a href="listhistory.jsp"><i class="fa fa-list-alt"></i>
							<span class="nav-label">客户/联系人合并记录</span></a></li>


				</ul>

			</div>
		</nav>

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
							href="#"><i class="fa fa-exchange"></i> </a>

					</div>

				</nav>
			</div>
			<form action="${pageContext.request.contextPath}/zoho/login.action" method="post">
				<button type="submit">登录</button>
			</form>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<h2>合并客户/联系人</h2>
				</div>
			</div>
			
			<ul class="nav nav-tabs" id="myTab" role="tablist">
			  <li class="nav-item">
			    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">合并客户</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">合并联系人</a>
			  </li>
			</ul>
		
			
			
			
				
			<div class="tab-content" id="myTabContent">
				<!-- 客户合并页 -->
	  			<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
	  						<div class="wrapper wrapper-content  animated fadeInRight">
							<div class="tab-pane active" id="tab-1">
							<div class="panel-body">
							
							<div class="row">
								<div class="col-lg-6">
									<div class="ibox ">
										<div class="ibox-title">
											<h5>待合并客户</h5>
										</div>


										<div class="activity-stream">
											<div class="ibox-content" style="height: 450px" id="tab1">

												<div class="activity-stream">
													<div class="panel-body">
														<form class="form-inline">
															<div class="form-group">
																<label for="customerName">客户名称</label> <input
																	id="search_kw" type="text" class="form-control ui-com "
																	placeholder="请输入需要合并的客户名称" style="width: 450px"
																	value="${custName }">
															</div>
															<div class="form-group">
																<button type="button" class="btn btn-primary"
																	id="oldCompany">加入</button>
															</div>
														</form>
													</div>
												</div>
												<table class="table table-bordered table-striped" id="oldCompanyList">
													<thead>
														<tr>
															<th width="30%">comID</th>
															<th width="50%">客户名称</th>
															<th width="20%" align="center">操作</th>
														</tr>
													</thead>
													<tbody>
														<tr></tr>
														<c:forEach items="${sessionScope.SourceList}" var="row">
															<tr>
																<td width="30%">${row.comID}</td>
																<td width="50%">${row.companyname}</td>
																<td width="20%" align="center">
																	<button class="btn btn-danger btn-xs"
																		onclick="removeSession('${row.comID}','oldCompanyList')">移除</button>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="iibox ">
										<div class="ibox-title">
											<h5>合并后新客户</h5>
										</div>

										<div class="ibox-content" style="height: 450px" >
											<div class="activity-stream">
												<div class="panel-body">
													<form class="form-inline">
														<div class="form-group">
															<label for="customerName">客户名称</label> <input
																id="search_kw_new" type="text"
																class="form-control ui-com" placeholder="请输入合并后新客户名称"
																value="${custName }" style="width: 450px">
														</div>
														<button type="button" class="btn btn-primary"
															id="newCompany">加入</button>
													</form>
												</div>
											</div>
											<div class="activity-stream" id="tab1-1">
												<table class="table table-bordered table-striped" id="newCompanyList">
													<thead>
														<tr>
															<th>comID</th>
															<th>客户名称</th>
															<th style="text-align:center">操作</th>
														</tr>
													</thead>
													<tbody>
														<tr></tr>
														<c:forEach items="${sessionScope.TargetList}" var="row">
															<tr>
																<td width="30%">${row.comID}</td>
																<td width="50%">${row.companyname}</td>
																<td width="20%" align="center">
																	<button class="btn btn-danger btn-xs"
																		onclick="removeSession('${row.comID}','newCompanyList')">移除</button>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>

										<nav class="navbar navbar-static-top" role="navigation"
											style="margin-bottom: 0">
											<div class="navbar-header">
												<button type="button" class="ladda-button ladda-button-demo btn btn-primary"  data-style="zoom-in" id="btn_merge">开始合并</button>
											</div>
										</nav>


									</div>
								</div>
							</div>
							</div>

						</div>

				
					</div>

				</div>
				<!-- 联系人合并页 -->
				
				
	  			<div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
	  						  						<div class="wrapper wrapper-content  animated fadeInRight">
							<div class="tab-pane active" id="tab-1">
							<div class="panel-body">
							
							<div class="row">
								<div class="col-lg-6">
									<div class="ibox ">
										<div class="ibox-title">
											<h5>待合并联系人</h5>
										</div>


										<div class="activity-stream">
											<div class="ibox-content" style="height: 450px" id="tab2" >

												<div class="activity-stream">
													<div class="panel-body">
														<form class="form-inline">
															<div class="form-group">
																<label for="ContactName">联系人名称</label> <input
																	id="search_Contact" type="text" class="form-control ui-com "
																	placeholder="请输入需要合并的联系人名称" style="width: 450px"
																	value="${name }">
															</div>
															<div class="form-group">
																<button type="button" class="btn btn-primary"
																	id="oldContact">加入</button>
															</div>
														</form>
													</div>
												</div>
												<table class="table table-bordered table-striped"
													id="oldContactList">
													<thead>
														<tr>
															<th width="10%">contID</th>
															<th width="20%">联系人名称</th>
															<th width="60%">公司名称</th>
															<th width="10%" style="text-align:center">操作</th>
														</tr>
													</thead>
													<tbody>
														<tr></tr>
														<c:forEach items="${sessionScope.contactSourceList}" var="row">
															<tr>
																<td width="10%">${row.contID}</td>
																<td width="20%">${row.name}</td>
																<td width="60%">${row.companyname}</td>
																<td width="10%" align="center">
																	<button class="btn btn-danger btn-xs"
																		onclick="removeSession('${row.contID}','oldContactList')">移除</button>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="iibox ">
										<div class="ibox-title">
											<h5>合并后新联系人</h5>
										</div>

										<div class="ibox-content" style="height: 450px">
											<div class="activity-stream">
												<div class="panel-body">
													<form class="form-inline">
														<div class="form-group">
															<label for="contactName">联系人名称</label> <input
																id="search_Contact_new" type="text"
																class="form-control ui-com" placeholder="请输入合并后新联系人名称"
																value="${name }" style="width: 450px">
														</div>
														<button type="button" class="btn btn-primary"
															id="newContact">加入</button>
													</form>
												</div>
											</div>
											<div class="activity-stream" id="tab2" >
												<table class="table table-bordered table-striped" id="newContactList">
													<thead>
														<tr>
															<th width="10%">contID</th>
															<th width="20%">联系人名称</th>
															<th width="60%">公司名称</th>
															<th width="10%" style="text-align:center">操作</th>
														</tr>
													</thead>
													<tbody>
														<tr></tr>
														<c:forEach items="${sessionScope.contactTargetList}" var="row">
															<tr>
																<td width="10%">${row.contID}</td>
																<td width="20%">${row.name}</td>
																<td width="60%">${row.companyname}</td>
																<td width="10%" align="center">
																	<button class="btn btn-danger btn-xs"
																		onclick="removeSession('${row.contID}','newContactList')">移除</button>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>

										<nav class="navbar navbar-static-top" role="navigation"
											style="margin-bottom: 0">
											<div class="navbar-header">
												<button type="submit" class="btn btn-primary"   data-style="zoom-in"  id="btn_Contact_merge">开始合并</button>
											</div>
										</nav>


									</div>
								</div>
							</div>
							</div>

						</div>

				
					</div>

	  					
	  			</div>
	  		</div>


				<!-- - -->
				<div class="footer">
					<div class="float-right">
						<strong>Copyright </strong> © 2015 Target molecule Corp.
					</div>

				</div>

			</div>
		</div>



		<!-- Mainly scripts -->
		<script src="js/jquery-3.1.1.min.js"></script>
		<script src="js/jquery-ui.min.js"></script>
		<script src="js/popper.min.js"></script>
		<script src="js/bootstrap.js"></script>
		<script src="js/jquery.metisMenu.js"></script>
		<script src="js/jquery.slimscroll.min.js"></script>

		<script src="js/inspinia.js"></script>
		<script src="js/pace.min.js"></script>
		<!-- jQuery UI -->
		<script src="js/jquery-ui.min.js"></script>
		<!-- Sweet alert -->
		<script src="js/sweetalert.min.js"></script>
		<!-- Ladda -->
	    <script src="js/spin.min.js"></script>
	    <script src="js/ladda.min.js"></script>



		<script type="text/javascript">
			jQuery(function($) {

				$(document).ready(
								function() {
									(function() {
											var insertOptions = function(data, id) {
											var result = new Array();
											if (id.indexOf("Contact") >= 0) {
												for (var ii = 0, rr = data.length; ii < rr; ii++) {
													result.push(data[ii].contID +"-" +$.trim(data[ii].name)+ "-"
																	+ $.trim(data[ii].companyname));
													console.log(result);


												}
											} else {
												for (var ii = 0, rr = data.length; ii < rr; ii++) {
													result.push(data[ii].comID
																	+ "-"
																	+ $.trim(data[ii].companyname));
												}
											}
											
											$('#' + id).autocomplete({
												source : result
											});
										};

										var insertaccountOptions = function(
												data, id) {

											var result = new Array();
											for (var ii = 0, rr = data.length; ii < rr; ii++) {
												result
														.push(data[ii]['comID']
																+ "-"
																+ data[ii]['companyname']);

											}

											$('#' + id).autocomplete({
												source : result
											});
										};

										$('#search_kw').keyup(function() {
											getList("search_kw");
										});

										$('#search_kw_new').keyup(function() {
											getList("search_kw_new");
										});

										$('#search_Contact').keyup(function() {
											getList("search_Contact");
										});

										$('#search_Contact_new')
												.keyup(
														function() {
															getList("search_Contact_new");
														});

										function getList(right_id) {
											
											var $url = null;
											var skeyword = null;
											var key = null;
											if (right_id.indexOf("Contact") >= 0) {
												$url = "${pageContext.request.contextPath}/searchContact.action";
												skeyword = JSON.stringify({'name' : $("#" + right_id).val().trim()
												});
												key = 'listContact';
											} else {
												$url = "${pageContext.request.contextPath}/searchCompany.action";
												skeyword = JSON.stringify({'companyname' : $("#" + right_id).val().trim()
												});
												key = 'listCompany';
											}
											$.ajax({
														type : "post",
														url : $url,
														processData : false,
														contentType : "application/json;charset=utf-8",
														data : skeyword,
														cache : false,
														success : function(data) {

															insertOptions(data[key],right_id);

														}

													});
										}

										function refreshCompanyList(data,tablekey) {
											$("#" + tablekey).empty();

											if(tablekey.indexOf('Contact')>0){
												$("#" + tablekey).append('<thead><tr><th width="10%">contID</th><th width="20%">联系人名称</th><th width="60%">公司名称</th><th width="10%" style="text-align:center">操作</th></tr></thead><tr></tr>');
												if(data!=null){
													for (var i = 0; i < data.length; i++) {
														var trHTML = '<tr ><td>'
																+ data[i].contID
																+ '</td><td>'
																+ data[i].name
																+ '</td><td>'
																+ data[i].companyname
																+ '</td><td align="center">'
																+ '<button class="btn btn-danger btn-xs" onclick="removeSession(&#39;'
																+ data[i].contID.trim()
																+ '&#39;,&#39;'+tablekey+'&#39;)">移除</button>'
																+ '</td></tr>';
														$("#" + tablekey).append(trHTML);
														}
													height=$("#" + tablekey)[0].offsetHeight+100;
													if(height>=415){
														$("#tab2").height(height);
													}else if(height<415){
														$("#tab2").height(415);

													}
 
													}
											}else{
												$("#" + tablekey).append('<thead><tr><th width="30%">comID</th><th width="50%">客户名称</th><th width="20%" style="text-align:center">操作</th></tr></thead><tr></tr>');
												if(data!=null){
													for (var i = 0; i < data.length; i++) {
														var trHTML = '<tr ><td>'
																+ data[i].comID
																+ '</td><td>'
																+ data[i].companyname
																+ '</td><td align="center">'
																+ '<button class="btn btn-danger btn-xs" onclick="removeSession(&#39;'
																+ data[i].comID.trim()
																+ '&#39;,&#39;'+tablekey+'&#39;)">移除</button>'
																+ '</td></tr>';
														$("#" + tablekey).append(trHTML);
														}
													height=$("#" + tablekey)[0].offsetHeight+100;
													if(height>=415){
														$("#tab1").height(height);
													}else if(height<415){
														$("#tab1").height(415);

													}

													}
											}
											

										}

										$('#oldCompany').click(function() {
															var $url = "${pageContext.request.contextPath}/addSourceList.action";
															var key = $('#search_kw').val();
															key = key.substring(0,key.indexOf("-"));

															$.ajax({
																		type : "post",
																		url : $url,
																		contentType : "application/json;charset=utf-8",
																		dataType : 'json',
																		data : JSON.stringify({'comID' : key}),
																		success : function(data) {
																			refreshCompanyList(data,'oldCompanyList');
																			$("#search_kw").val("");
																		}
																	});
														});

										$('#newCompany').click(function() {
															var $url = "${pageContext.request.contextPath}/addTargetList.action";
															var key = $('#search_kw_new').val();
															key = key.substring(0,key.indexOf("-"));
															$.ajax({
																		type : "post",
																		url : $url,
																		contentType : "application/json;charset=utf-8",
																		dataType : 'json',
																		data : JSON
																				.stringify({'comID' : key}),
																		success : function(
																				data) {
																			console.log(data);
																			refreshCompanyList(data,'newCompanyList');
																			$("#search_kw_new").val("");

																		}
																	});
														});

										$('#oldContact').click(
														function() {
															var $url = "${pageContext.request.contextPath}/addContactSourceList.action";
															var key = $('#search_Contact').val();
															key = key.substring(0,key.indexOf("-"));
															$.ajax({
																		type : "post",
																		url : $url,
																		contentType : "application/json;charset=utf-8",
																		dataType : 'json',
																		data : JSON.stringify({'contID' : key}),
																		success : function(data) {
																			refreshCompanyList(data,'oldContactList');
																			$("#search_Contact").val("");

																			}

																		
																	});
														});

										$('#newContact').click(
														function() {
															var $url = "${pageContext.request.contextPath}/addContactTargetList.action";
															var key = $(
																	'#search_Contact_new')
																	.val();
															key = key.substring(0,key.indexOf("-"));
															$.ajax({
																		type : "post",
																		url : $url,
																		contentType : "application/json;charset=utf-8",
																		dataType : 'json',
																		data : JSON.stringify({'contID' : key}),
																		success : function(
																				data) {console.log(data);
																				refreshCompanyList(data,'newContactList');
																				$("#search_Contact_new").val("");

																		}
																	});
														});

										removeSession = function(com, tablename) {
											
											var $url =null
											var session = "";
											switch (tablename) {
											case "oldCompanyList":
												$url= "${pageContext.request.contextPath}/removeCompanySession.action";
												session = "SourceList";
												break;
											case "newCompanyList":
												$url= "${pageContext.request.contextPath}/removeCompanySession.action";
												session = "TargetList";
												break;
											case "oldContactList":
												$url= "${pageContext.request.contextPath}/removeContactSession.action";
												session = "contactSourceList";
												break;
											case "newContactList":
												$url= "${pageContext.request.contextPath}/removeContactSession.action";
												session = "contactTargetList";
												break;
											}


											$.ajax({
														type : "post",
														url : $url,
														contentType : "application/json;charset=utf-8",
														dataType : 'json',
														data : JSON
																.stringify({
																	'comID' : com,
																	'sessionName' : session
																}),
														success : function(data) {
															console.log(data);
															refreshCompanyList(
																	data,
																	tablename);

														}
													});

										}

						
										$('#btn_merge').click(function() {
										
											var l = Ladda.create(this);

											var url = "${pageContext.request.contextPath}/mergeCompany.action";
											var title='<tr><th width="30%">comID</th><th width="50%">客户名称</th><th width="20%" style="text-align:center">操作</th></tr>'

											
												merge(url,title,"CompanyList",l);
											
											
											return false;
	
										});
										
										$('#btn_Contact_merge').click(function() {												
											var l = Ladda.create(this);

											var url = "${pageContext.request.contextPath}/mergeContact.action";
											var title='<tr><th width="30%">contID</th><th width="50%">联系人名称</th><th width="20%" style="text-align:center">操作</th></tr>'

											merge(url,title,"Contact",l);
											return false;
										});
										
										var merge =function($url,$title,$tab,l){
											
											l.start();
											$('#loadingModal').modal({
												backdrop : "static",
												keyboard : false
												});
											
										$.ajax({type : "post",
											url : $url,
											contentType : "application/json;charset=utf-8",
											dataType : 'json',
											data : "123",
											success : function(data) {
												l.stop();
												if (data["code"] == 0) {
													swal({title : " ",
														  text : data['message'],
														  type : "success"},
														  function() {

																$("table[id*='"+$tab+"']").empty();
																$("table[id*='"+$tab+"']").append($title);
															});} else {
													swal({
														title : " ",
														text : data['message'],
														type : "error",
														closeOnConfirm : false});}
													},
											error : function(e) {
												l.stop();

					
												swal({
													title : "",
													text : e,
													type : "error",
													closeOnConfirm : false
												});
											}
										});
										
										}
									})();
								});
			});
		</script>
</body>

</html>


