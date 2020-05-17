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
	  <link href="css/footable.core.css" rel="stylesheet">
	
    <!-- Toastr style -->
    <link href="css/toastr.min.css" rel="stylesheet">
	 <!-- Sweet Alert -->
    <link href="css/sweetalert.css" rel="stylesheet">
    
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
	<link href="css/strip.css" rel="stylesheet">
	
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/dataTables.jqueryui.css">	 <style>
         .highlight {
             background-color: skyblue
         }
     </style>
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
        overflow-x: hidden
      }
      .listHistory_filter{
      	width:450px;
      }
       
        th.ui-state-default{
        	height:60px;
        	text-align:center;
        	vertical-align:middle!important;;
        	font-size: 14px;
        	background-color: green;
        	
        }
 		.dataTables_filter{
                     padding-left:-95px;//上移
                    }
        .dataTables_filter>label>input{
         width:300px;
        }
        #listHistory_paginate{
        	margin-top: 10px
        }
    </style>  


    <div id="wrapper">

    <nav class="navbar-default navbar-static-side" role="navigation" >
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                   
                    <div class="logo-element">
                        IN+
                    </div>
                </li>
                	<li><a href="index.jsp"><i class="fa fa-calendar-plus-o"></i>
							<span class="nav-label">客户/联系人合并</span> </a></li>
					<li><a href="listhistory.jsp"><i class="fa fa-list-alt"></i>
							<span class="nav-label">客户/联系人合并记录</span></a></li>

                </li>
               </ul>

        </div>
    </nav>
 	
        <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-exchange"></i> </a>
           
        </div>

        </nav>
        </div>
      
                  <div class="row wrapper border-bottom white-bg page-heading">
               		 <div class="col-lg-10">
                   		 <h2>客户/联系人合并历史记录</h2>
                	 </div>
          		 </div>
          		 
			<ul class="nav nav-tabs" id="myTab" role="tablist">
			  <li class="nav-item">
			    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true" onclick=' refreshCompanyTable()'>客户合并记录</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false" onclick=' refreshContactTable()'>联系人合并记录</a>
			  </li>
			</ul>
		
			
			
			
				
			<div class="tab-content" id="myTabContent">
          	<!-- 客户合并记录页 -->
          	<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
          			   <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="#" class="dropdown-item">选项 1</a>
                                </li>
                                <li><a href="#" class="dropdown-item">选项 2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">

                    <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover table-border dataTables-example" id="listHistory"  >
                    <thead>
                    <tr>
                        <th width=12% class="center">合并时间</th>
                        <th width=14% class="center">原客户（公司）ID</th>
                        <th width=27% class="center">原客户（公司）名称</th>
                        <th width=14% class="center">合并后客户（公司）ID</th>
                        <th width=27% class="center">合并后客户（公司）名称</th>
                        <th width=6%  class="center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
 
                    
                    </tbody>
                   
                    </table>
                        </div>

                    </div>
                </div>
            </div>
            </div>
        </div>
          			
          	
          	</div>
          	
          	<!-- 联系人合并记录页 -->
          	<div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
          	   <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="#" class="dropdown-item">选项 1</a>
                                </li>
                                <li><a href="#" class="dropdown-item">选项 2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">

                    <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover table-border dataTables-example" id="listContactHistory" >
                    <thead>
                    <tr>
                        <th width=11% class="center">合并时间</th>
                        <th width=10% class="center">原联系人ID</th>
                        <th width=15% class="center">原联系人名称</th>
                        <th width=17% class="center">原公司名称</th>
                        <th width=10% class="center">合并后联系人ID</th>
                        <th width=15% class="center">合并后联系人名称</th>
                        <th width=17% class="center">合并后公司名称</th>
                        <th width=5%  class="center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
 
                    
                    </tbody>
                   
                    </table>
                        </div>

                    </div>
                </div>
            </div>
            </div>
        </div>

          	
          	
          	</div>
          		
          	</div>	 
          		 
             <div class="footer">
           	 <div class="float-right">
                 <strong>Copyright </strong>  © 2015 Target molecule Corp.            </div>
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
    <script src="js/jquery.metisMenu.js"></script>
    <script src="js/jquery.slimscroll.min.js"></script>

    <script src="js/highlight1.js"></script>
    <script type="text/javascript" language="javascript" src="http://cdn.datatables.net/1.10-dev/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" language="javascript" src="http://cdn.datatables.net/plug-ins/28e7751dbec/integration/jqueryui/dataTables.jqueryui.js"></script>




<script type="text/javascript">
jQuery(function ($) {
	$(document).ready(function () {
 		
 var initTable=function(){
	 var dt=[];
	 var t ;
	 
	 var table= $('#listHistory').DataTable({
		  bDeferRender:false,
		  data:dt,
          pageLength: 25,
          responsive: false,
          dom: '<"html5buttons"B>lTfgitp',
          bAutoWidth:false,
          bLengthChange: true,
          bInfo: false,
          bFilter: true,
          stripeClasses: [ 'mystrip2', 'mystrip1'],
          language: {
              "sProcessing": "处理中...",
              "sLengthMenu": "每页 _MENU_ 条记录",
              "sZeroRecords": "没有匹配结果",
              "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
              "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
              "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
              "sInfoPostFix": "",
              "sSearch": "搜索:",
              "sUrl": "",
              "sEmptyTable": "表中数据为空",
              "sLoadingRecords": "载入中...",
              "sInfoThousands": ",",
              "oPaginate": {
                  "sFirst": "首页",
                  "sPrevious": "上页",
                  "sNext": "下页",
                  "sLast": "末页"
              },
              "oAria": {
                  "sSortAscending": ": 以升序排列此列",
                  "sSortDescending": ": 以降序排列此列"
              }
              

          },
          buttons: [
   
              {extend: 'print',
               customize: function (win){
                      $(win.document.body).addClass('white-bg');
                      $(win.document.body).css('font-size', '14px');
	                      $(win.document.body).find('table')
                              .addClass('compact')
                              .css('font-size', 'inherit');
              }
              }
          ]

         
      });
	
	 
	 var table1= $('#listContactHistory').DataTable({
		  bDeferRender:false,
		  data:dt,
         pageLength: 25,
         responsive: false,
         dom: '<"html5buttons"B>lTfgitp',
         bAutoWidth:false,
         bLengthChange: true,
         bInfo: false,
         bFilter: true,
         stripeClasses: [ 'mystrip2', 'mystrip1'],
         language: {
             "sProcessing": "处理中...",
             "sLengthMenu": "每页 _MENU_ 条记录",
             "sZeroRecords": "没有匹配结果",
             "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
             "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
             "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
             "sInfoPostFix": "",
             "sSearch": "搜索:",
             "sUrl": "",
             "sEmptyTable": "表中数据为空",
             "sLoadingRecords": "载入中...",
             "sInfoThousands": ",",
             "oPaginate": {
                 "sFirst": "首页",
                 "sPrevious": "上页",
                 "sNext": "下页",
                 "sLast": "末页"
             },
             "oAria": {
                 "sSortAscending": ": 以升序排列此列",
                 "sSortDescending": ": 以降序排列此列"
             }
             

         },
         buttons: [
  
             {extend: 'print',
              customize: function (win){
                     $(win.document.body).addClass('white-bg');
                     $(win.document.body).css('font-size', '14px');
	                      $(win.document.body).find('table')
                             .addClass('compact')
                             .css('font-size', 'inherit');
             }
             }
         ]

        
     });
	 table.on('draw', function () {
		
         var body = $(table.table().body());
         body.unhighlight();
         body.highlight(table.search());
     });
	 table1.on('draw', function () {
			
         var body = $(table1.table().body());
         body.unhighlight();
         body.highlight(table1.search());
     });
	 
 };
 
 
	  
	  refreshCompanyTable();
	  initTable();
});  
});
function refreshCompanyTable(){
	 var $listurl="${pageContext.request.contextPath}/listhistory.action";
	 var key =JSON.stringify({});

	 $.ajax({
	     	type: "post",
	         url:  $listurl,
	         contentType:"application/json;charset=utf-8",
	         data : key,
	         success: function(dg) {
	        	var data=dg['data'];

				 t = $('#listHistory').DataTable();
				 $('#listHistory').dataTable().fnClearTable(this); 				 
				for(var ii=0,rr=data.length;ii<rr;ii++){    
					  console.log(data[ii]);
					  t.row.add( [
								data[ii]['creatime'],
								data[ii]['companyID_Old'],
								data[ii]['companyName_Old'],
								data[ii]['companyID_New'],
								data[ii]['companyName_New'],
								'<button  class="btn btn-danger btn-xs center"  type="button" onclick="resumeCompany('+data[ii]['autoID']+')">还原</button>'
								
					        ] ).draw();
			
	           		
	        		 }
				
	         	
	             }

	         });
	
}

function refreshContactTable(){
	 var $listurl="${pageContext.request.contextPath}/listContacthistory.action";
	 var key =JSON.stringify({});

	 $.ajax({
	     	type: "post",
	         url:  $listurl,
	         contentType:"application/json;charset=utf-8",
	         data : key,
	         success: function(dg) {
	        	var data=dg['data'];

				 t = $('#listContactHistory').DataTable();
				 $('#listContactHistory').dataTable().fnClearTable(this); 				 
				for(var ii=0,rr=data.length;ii<rr;ii++){    
					  console.log(data[ii]);
					  t.row.add( [
								data[ii]['creatime'],
								data[ii]['contactID_Old'],
								data[ii]['contactName_Old'],
								data[ii]['companyName_Old'],
								data[ii]['contactID_New'],
								data[ii]['contactName_New'],
								data[ii]['companyName_New'],
								'<button  class="btn btn-danger btn-xs center"  type="button" onclick="resumeContact('+data[ii]['autoID']+')">还原</button>'
								
					        ] ).draw();
			
	           		
	        		 }
				
	         	
	             }

	         });
	
}
function resumeCompany(id,namefunction){
	 var $url="${pageContext.request.contextPath}/resumeCompany.action";
	 var $key=JSON.stringify({"autoID":id});
	  $.ajax({
     	type: "post",
         url:  $url,
         contentType:"application/json;charset=utf-8",
         dataType : 'json',
			data:$key,
         success: function(data) {
         		if(data["code"]==0){
             	   swal({
                        title:" ",
                        text: data['message'],
	                	   type: "success"
             	   }, function () {
             		   //window.location.reload()
             		 refreshCompanyTable();
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
                 text: e.printStackTrace,
             	   type: "error",
             	   closeOnConfirm: false
      	   });                 }
         
         });

}

function resumeContact(id,namefunction){
	 var $url="${pageContext.request.contextPath}/resumeContact.action";
	 var $key=JSON.stringify({"autoID":id});
	  $.ajax({
    	type: "post",
        url:  $url,
        contentType:"application/json;charset=utf-8",
        dataType : 'json',
			data:$key,
        success: function(data) {
        		if(data["code"]==0){
            	   swal({
                       title:" ",
                       text: data['message'],
	                	   type: "success"
            	   }, function () {
            		   //window.location.reload()
            		 refreshContactTable();
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
                text: e.printStackTrace,
            	   type: "error",
            	   closeOnConfirm: false
     	   });                 }
        
        });

}
</script>

</body>

</html>


