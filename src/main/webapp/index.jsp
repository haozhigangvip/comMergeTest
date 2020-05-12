<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<link rel="stylesheet" href="css/jquery-ui.min.css">
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
                   
                    <div class="logo-element">
                        IN+
                    </div>
                </li>
                <li>
                    <a href="index.jsp"><i class="fa fa-th-large"></i> 
                    <span class="nav-label">客户（公司）合并</span> </a>

                </li>
                <li>
                    <a href="listhistory.jsp"><i class="fa fa-diamond"></i> 
                    <span class="nav-label">合并记录</span></a>
                </li>
                
            </ul>

        </div>
    </nav>

        <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
           
        </div>

        </nav>
        </div>
            <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2>客户（公司）合并系统</h2>
                </div>
            </div>
           
             	
            <div class="wrapper wrapper-content  animated fadeInRight">

                <div class="row">
                    <div class="col-lg-6">
                        <div class="ibox ">
                            <div class="ibox-title">
                                <h5>待合并客户（公司)</h5>
                            </div>
						
                            
								<div class="activity-stream">
								<div class="ibox-content" style="height: 500px">

                                <div class="activity-stream">
                              	  <div class="panel-body">
                               		<form class= "form-inline" action="${pageContext.request.contextPath }/addSourceList.action" method="post">
										<div class="form-group">
											<label for="customerName">公司名称</label> 
											<input id="search_kw" type="text" name="myname" class="form-control ui-com "  
											placeholder="请输入待合并的源公司名称"  style="width:  400px"
											value="${custName }" >
										</div>
										<div class="form-group">
											<button type="submit" class="btn btn-primary">加入</button>
										</div>
									</form>	
								   </div>
                                </div>
                                <table class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>comID</th>
											<th>公司名称</th>
											<th style="text-align:center;">操作</th>
										</tr>
									</thead>
									<tbody>
										<tr></tr>
										<c:forEach items="${sessionScope.SourceList}" var="row">
											<tr>
												<td>${row.comID.trim()}</td>
												<td>${row.companyname.trim()}</td>
												<td align="center">
													<button  class="btn btn-danger btn-xs" onclick="removeSession('${row.comID}','SourceList')">移除</button>
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
                        <div class="iibox " >
                            <div class="ibox-title">
                                <h5>合并后目标客户（公司）</h5>
                            </div>

                            <div class="ibox-content"  style="height: 500px">
								<div class="activity-stream">
									<div class="panel-body">
										<form class="form-inline" action="${pageContext.request.contextPath }/addTargetList.action" method="post">
											<div class="form-group">
												<label for="customerName">公司名称</label> 
												<input id="search_kw_new" type="text" name="myname_new" class="form-control ui-com"  placeholder="请输入合并成为目标公司名称"  value="${custName }"  style="width:  400px">
											</div>
											<button type="submit" class="btn btn-primary">加入</button>
										</form>	
									</div>
								</div>
								<div class="activity-stream">
									<table class="table table-bordered table-striped">
										<thead>
											<tr>
												<th>comID</th>
												<th>公司名称</th>
												<th style="text-align:center;">操作</th>
											</tr>
										</thead>
										<tbody>		
											<tr></tr>			
											<c:forEach items="${sessionScope.TargetList}" var="row">								
												<tr>
													<td>${row.comID}</td>
													<td>${row.companyname}</td>
													<td align="center">
														<button  class="btn btn-danger btn-xs" onclick="removeSession('${row.comID}','TargetList')">移除</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>							
								</div>
							 </div>
							
							 <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
            					<div class="navbar-header">
            						<button type="submit" class="btn btn-primary" id="btn_merge">开始合并</button>
           						</div>
           		 			 </nav>
           		 			 
							 
                        </div>
                    </div>
                </div>
            </div>
	        <div class="footer">
            <div class="float-right">
                 <strong>Copyright </strong>  © 2015 Target molecule Corp.
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
function removeSession(com,session){
	var httpRequest = new XMLHttpRequest();//第一步：创建需要的对象
	httpRequest.open('POST', '${pageContext.request.contextPath }/removeSourceList.action', true); //第二步：打开连接
	httpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");//设置请求头 注：post方式必须设置请求头（在建立连接后设置请求头）
	httpRequest.send('comID='+com+'&session_name='+session);//发送请求 将情头体写在send中
	location.reload();
}
function clearSession(){
	var httpRequest = new XMLHttpRequest();//第一步：创建需要的对象
	httpRequest.open('POST', '${pageContext.request.contextPath }/clearSourceList.action', true); //第二步：打开连接
	httpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");//设置请求头 注：post方式必须设置请求头（在建立连接后设置请求头）
	httpRequest.send();//发送请求 将情头体写在send中
	location.reload();
}

</script>




<script type="text/javascript">
jQuery(function ($) {

$(document).ready(function () {  
    (function(){  
        var insertOptions = function(data, id) {  
            
        	var result = new Array();  
        	 for(var ii=0,rr=data.length;ii<rr;ii++){
        		 result.push(data[ii]['comID']+"-" +data[ii]['companyname']);
        		 
        		 } 

           
           
            
            $('#'+id).autocomplete({  
                source: result  
            });  
        };  

        $('#search_kw').keyup(function(){  
        	getListCompany("search_kw");
        });  
       
        $('#search_kw_new').keyup(function(){  
        	getListCompany("search_kw_new");
        });  
        
        
        
        
        function getListCompany(right_id){

            
            var $url="${pageContext.request.contextPath}/searchCompany.action";
         
           var skeyword =JSON.stringify({'companyname': $("#"+right_id).val().trim()});
            $.ajax({
            	type: "post",
                url:  $url,
                processData: false,
                contentType:"application/json;charset=utf-8",
                data : skeyword,
                cache: false,
                success: function(data) {
                	 insertOptions(data['listCompany'], right_id);   
                	
                    }

                });
        }
    
        $('#btn_merge').click(function(){  
        	<!-- $('#loadingModal').modal({backdrop: true, keyboard: false,show:true});-->	
        	$('#loadingModal').modal({backdrop: "static", keyboard: false});
            var $url="${pageContext.request.contextPath}/mergeCompany.action";
			
            $.ajax({
            	type: "post",
                url:  $url,
                contentType:"application/json;charset=utf-8",
                dataType : 'json',
				data:"123",
                success: function(data) {
                		if(data["code"]==0){
	                	   swal({
	                           title:" ",
	                           text: data['message'],
		                	   type: "success"
	                	   }, function () {
	                		   window.location.reload()
	                       });
                	   }else{
                		   swal({
	                           title:" " ,
	                           text: data['message'],
		                	   type: "error",
		                	   closeOnConfirm: false
	                	   });
                	   }
                 	
                    },
                error:function(e){
                	swal({
                        title:" " ,
                        text: e,
	                	   type: "error",
	                	   closeOnConfirm: false
             	   });                 }
                
                });
        });  

    })();  
});  
});
</script>

</body>

</html>


