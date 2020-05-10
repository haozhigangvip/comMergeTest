<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page trimDirectiveWhitespaces="true"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<head>
<title>客户列表-BootCRM</title>
<!-- Bootstrap Core CSS -->
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">


<!-- Custom CSS -->
<link href="<%=basePath%>css/sb-admin-2.css" rel="stylesheet">
<!-- Custom Fonts -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

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


</head>

<body>




<div id="wrapper">
	<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				
			</button>
			<a class="navbar-brand" href="#">客户合并</a>
		</div>


		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li class="sidebar-search">
						
					</li>
					<li><a href="" class="active"> 客户管理</a></li>
					<li><a href="salevisit.action">客户拜访</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
	</nav>
	
	
		<div id="page-wrapper" >
			<div class="row ">
				<div class="col-lg-12">
					<h1 class="page-header">客户管理</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row mx-md-n5">
 </div>
			
			
			
			<!-- /.row -->
			<div class="panel panel-default">
				<div class="panel-body">
						<form class="form-inline" action="${pageContext.request.contextPath }/addSourceList.action" method="post">
							<div class="form-group">
								<label for="customerName">公司名称</label> 
								<input id="search_kw" type="text" name="myname" class="form-control ui-com"  placeholder="请输入待合并的源公司名称"  value="${custName }">
							</div>
							<button type="submit" class="btn btn-primary">加入</button>
							
											<div>
				</div>
						</form>	
				<button type="submit" class="btn btn-primary" id="btn_merge">开始合并</button>
						
				</div>

			</div>
			
			 <div class="col px-md-5">
				<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">待合并的公司信息列表</div>
						<!-- /.panel-heading -->
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>comID</th>
									<th>公司名称</th>
									
								</tr>
							</thead>
							<tbody>
							
								<c:forEach items="${sessionScope.SourceList}" var="row">
								
									<tr>
										<td>${row.comID.trim()}</td>
										<td>${row.companyname.trim()}</td>
										<td>
											
											<button  class="btn btn-danger btn-xs" onclick="removeSession('${row.comID}','SourceList')">移除</button>
										
						
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<div class="col-md-12 text-right">
							<button type="button" class="btn btn-primary" id="clear" onclick="clearSession()">清空</button>
						</div>
						<!-- /.panel-body -->
					</div>
					
					
					
					
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
		
		<!-- 合并新公司                   -->
					<div class="panel panel-default">
				<div class="panel-body">
						<form class="form-inline" action="${pageContext.request.contextPath }/addTargetList.action" method="post">
							<div class="form-group">
								<label for="customerName">公司名称</label> 
								<input id="search_kw_new" type="text" name="myname_new" class="form-control ui-com"  placeholder="请输入合并成为目标公司名称"  value="${custName }">
							</div>
							<button type="submit" class="btn btn-primary">加入</button>
						</form>	
				</div>
			</div>
	
			 <div class="col px-md-5">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">合并为目标公司</div>
						<!-- /.panel-heading -->
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>comID</th>
									<th>公司名称</th>
									
								</tr>
							</thead>
							<tbody>					
								<c:forEach items="${sessionScope.TargetList}" var="row">								
									<tr>
										<td>${row.comID}</td>
										<td>${row.companyname}</td>
										<td>
											<button  class="btn btn-danger btn-xs" onclick="removeSession('${row.comID}','TargetList')">移除</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						
						<!-- /.panel-body -->
					</div>
					
					
					
					
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
		
		
		</div>
		</div>
		






</body>



<!-- jQuery -->

	<script src="<%=basePath%>js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<%=basePath%>js/bootstrap.min.js"></script>


	<script src="<%=basePath%>js/jquery-ui.js"></script>
	
   	


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
                	alert(data['message']);
                	 window.location.reload()
                    },
                error:function(e){
                	alert(e);	
                }
                
                });
        });  

    })();  
});  
});
</script>





  
</html>
