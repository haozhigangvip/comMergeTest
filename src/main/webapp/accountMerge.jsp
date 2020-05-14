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
							<span class="nav-label">客户合并</span> </a></li>
					<li><a href="listhistory.jsp"><i class="fa fa-list-alt"></i>
							<span class="nav-label">客户合并记录</span></a></li>
					<li><a href="accountMerge.jsp"><i class="fa fa-user-plus"></i>
							<span class="nav-label">联系人合并</span> </a></li>
					<li><a href="listaccounthistory.jsp"><i class="fa fa-address-card"></i>
							<span class="nav-label">联系人合并记录</span></a></li>
				</ul>

			</div>
		</nav>

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
							href="#"><i class="fa fa-bars"></i> </a>

					</div>

				</nav>
			</div>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<h2>联系人合并</h2>
				</div>
			</div>
		


				<div class="tab-content">
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
											<div class="ibox-content" style="height: 500px">

												<div class="activity-stream">
													<div class="panel-body">
														<form class="form-inline">
															<div class="form-group">
																<label for="customerName">联系人名称</label> <input
																	id="search_kw" type="text" class="form-control ui-com "
																	placeholder="请输入需要合并联系人名称" style="width: 400px"
																	value="${custName }">
															</div>
															<div class="form-group">
																<button type="button" class="btn btn-primary"
																	id="oldCompany">加入</button>
															</div>
														</form>
													</div>
												</div>
												<table class="table table-bordered table-striped"
													id="oldcompanyList">
													<thead>
														<tr>
															<th width="30%">comID</th>
															<th width="50%">联系人名称</th>
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
																		onclick="removeSession('${row.comID}','oldcompanyList')">移除</button>
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
											<h5>合并后新的联系人</h5>
										</div>

										<div class="ibox-content" style="height: 500px">
											<div class="activity-stream">
												<div class="panel-body">
													<form class="form-inline">
														<div class="form-group">
															<label for="customerName">联系人名称</label> <input
																id="search_kw_new" type="text"
																class="form-control ui-com" placeholder="请输入合并后新的联系人名称"
																value="${custName }" style="width: 400px">
														</div>
														<button type="button" class="btn btn-primary"
															id="newCompany">加入</button>
													</form>
												</div>
											</div>
											<div class="activity-stream">
												<table class="table table-bordered table-striped"
													id="newCompanyList">
													<thead>
														<tr>
															<th>comID</th>
															<th>客户名称</th>
															<th style="text-align: center;">操作</th>
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
												<button type="submit" class="btn btn-primary" id="btn_merge">开始合并</button>
											</div>
										</nav>


									</div>
								</div>
							</div>
							</div>
							<div class="panel-body">
							<div class="tab-pane" id="tab-2">
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
		<script src="js/jquery.min.js"></script>
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







		<script type="text/javascript">
			jQuery(function($) {

				$(document)
						.ready(
								function() {
									(function() {
										var insertOptions = function(data, id) {

											var result = new Array();
											if (id.indexOf("account") >= 0) {
												for (var ii = 0, rr = data.length; ii < rr; ii++) {
													result
															.push(data[ii].countID
																	+ "-"
																	+ data[ii].countname);
												}
											} else {
												for (var ii = 0, rr = data.length; ii < rr; ii++) {
													result
															.push(data[ii].comID
																	+ "-"
																	+ data[ii].companyname);
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
											getListCompany("search_kw");
										});

										$('#search_kw_new').keyup(function() {
											getListCompany("search_kw_new");
										});

										$('#search_account').keyup(function() {
											getListCompany("search_account");
										});

										$('#search_account_new')
												.keyup(
														function() {
															getListCompany("search_account_new");
														});

										function getListCompany(right_id) {
											var $url = null;
											var skeyword = null;
											var key = null;
											if (right_id.indexOf("account") >= 0) {
												$url = "${pageContext.request.contextPath}/searchAccount.action";
												skeyword = JSON.stringify({
													'accountname' : $(
															"#" + right_id)
															.val().trim()
												});
												key = 'listaccount';
											} else {
												$url = "${pageContext.request.contextPath}/searchCompany.action";
												skeyword = JSON.stringify({
													'companyname' : $(
															"#" + right_id)
															.val().trim()
												});
												key = 'listCompany';
											}
											$
													.ajax({
														type : "post",
														url : $url,
														processData : false,
														contentType : "application/json;charset=utf-8",
														data : skeyword,
														cache : false,
														success : function(data) {

															insertOptions(
																	data[key],
																	right_id);
															console
																	.log(data[key]);

														}

													});
										}

										function refreshCompanyList(data,tablekey) {
											$("#" + tablekey).empty();
											$("#" + tablekey).append('<tr><th width="30%">comID</th><th width="50%">客户名称</th><th width="20%" style="text-align:center;">操作</th></tr>');
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
											}
										}

										$('#oldCompany')
												.click(
														function() {
															var $url = "${pageContext.request.contextPath}/addSourceList.action";
															var key = $(
																	'#search_kw')
																	.val();
															key = key
																	.substring(
																			0,
																			key
																					.indexOf("-"));

															$
																	.ajax({
																		type : "post",
																		url : $url,
																		contentType : "application/json;charset=utf-8",
																		dataType : 'json',
																		data : JSON.stringify({'comID' : key}),
																		success : function(data) {
																			refreshCompanyList(data,'oldcompanyList');
																			$("#search_kw").val("");
																		}
																	});
														});

										$('#newCompany')
												.click(
														function() {
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

										$('#oldAccount')
												.click(
														function() {
															var $url = "${pageContext.request.contextPath}/addAccountTargetList.action";
															var key = $(
																	'#search_account_new')
																	.val();
															key = key
																	.substring(
																			0,
																			key
																					.indexOf("-"));
															$
																	.ajax({
																		type : "post",
																		url : $url,
																		contentType : "application/json;charset=utf-8",
																		dataType : 'json',
																		data : JSON
																				.stringify({
																					'comID' : key
																				}),
																		success : function(data) {
																			$("#accountList_new").empty();
																			$("#accountList_new").append('<tr><th width="30%">accountID</th><th width="50%">联系人名称</th><th width="20%" style="text-align:center;">操作</th></tr>');
																			for (var i = 0; i < data.length; i++) {
																				var trHTML = '<tr ><td>'
																						+ data[i].comID
																						+ '</td><td>'
																						+ data[i].companyname
																						+ '</td><td align="center">'
																						+ '<button class="btn btn-danger btn-xs" onclick="removeaccountSession(&#39;'
																						+ data[i].comID
																								.trim()
																						+ '&#39;,&#39;TargetList&#39;)">移除</button>'
																						+ '</td></tr>';
																				$("#accountList_new").append(trHTML);
																			}

																		}
																	});
														});

										$('#newAccount')
												.click(
														function() {
															var $url = "${pageContext.request.contextPath}/addAccountTargetList.action";
															var key = $(
																	'#search_account_new')
																	.val();
															key = key
																	.substring(
																			0,
																			key
																					.indexOf("-"));
															$
																	.ajax({
																		type : "post",
																		url : $url,
																		contentType : "application/json;charset=utf-8",
																		dataType : 'json',
																		data : JSON
																				.stringify({
																					'comID' : key
																				}),
																		success : function(
																				data) {
																			console
																					.log(data);
																			$(
																					"#accountList_new")
																					.empty();
																			$(
																					"#accountList_new")
																					.append(
																							'<tr><th width="30%">accountID</th><th width="50%">联系人名称</th><th width="20%" style="text-align:center;">操作</th></tr>');
																			for (var i = 0; i < data.length; i++) {
																				var trHTML = '<tr ><td>'
																						+ data[i].comID
																						+ '</td><td>'
																						+ data[i].companyname
																						+ '</td><td align="center">'
																						+ '<button class="btn btn-danger btn-xs" onclick="removeaccountSession(&#39;'
																						+ data[i].comID
																								.trim()
																						+ '&#39;,&#39;TargetList&#39;)">移除</button>'
																						+ '</td></tr>';
																				$(
																						"#accountList_new")
																						.append(
																								trHTML);
																			}

																		}
																	});
														});

										removeSession = function(com, tablename) {

											var $url = "${pageContext.request.contextPath}/removeCompanySession.action";
											var session = "";
											switch (tablename) {
											case "oldcompanyList":
												session = "SourceList";
												break;
											case "newCompanyList":
												session = "TargetList";
												break;
											case "oldaccountList":
												session = "accountSourceList";
												break;
											case "accountList_new":
												session = "accountTargetList";
												break;
											}

											$
													.ajax({
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

										$('#btn_merge')
												.click(
														function() {
															<!--
															$('#loadingModal')
																	.modal(
																			{
																				backdrop : true,
																				keyboard : false,
																				show : true
																			});
															-->
															$('#loadingModal')
																	.modal(
																			{
																				backdrop : "static",
																				keyboard : false
																			});
															var $url = "${pageContext.request.contextPath}/mergeCompany.action";

															$
																	.ajax({
																		type : "post",
																		url : $url,
																		contentType : "application/json;charset=utf-8",
																		dataType : 'json',
																		data : "123",
																		success : function(
																				data) {
																			if (data["code"] == 0) {
																				swal(
																						{
																							title : " ",
																							text : data['message'],
																							type : "success"
																						},
																						function() {
																							window.location.reload();
																						});
																			} else {
																				swal({
																					title : " ",
																					text : data['message'],
																					type : "error",
																					closeOnConfirm : false
																				});
																			}

																		},
																		error : function(
																				e) {
																			swal({
																				title : " ",
																				text : e,
																				type : "error",
																				closeOnConfirm : false
																			});
																		}

																	});
														});

									})();
								});
			});
		</script>
</body>

</html>


