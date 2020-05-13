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
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
	
	  <!-- Toastr style
    <link href="css/toastr.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/jquery-ui.min.css">
    <link href="css/sweetalert.css" rel="stylesheet"> -->
	
	 <style>
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
                    <h2>合并历史记录</h2>
                    <ol class="breadcrumb">
                      
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
         <div class="row">
                <div class="col-lg-12">
                    <div class="ibox ">
                        <div class="ibox-title">
                            <h5>简单Foo表格与分页,排序和过滤</h5>

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
                            <input type="text" class="form-control form-control-sm m-b-xs" id="filter"
                                   placeholder="搜索表">

                            <table class="footable table table-stripped" data-page-size="8" data-filter=#filter id="listHistory">
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
                                <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <ul class="pagination float-right"></ul>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
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
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.metisMenu.js"></script>
    <script src="js/jquery.slimscroll.min.js"></script>
    <script src="js/footable.all.min.js"></script>
    <script src="js/inspinia.js"></script>
    <script src="js/pace.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    
    <!--
    <script src="js/jquery.min.js"></script>
     Sweet alert 
    <script src="js/sweetalert.min.js"></script>-->





<script type="text/javascript">
jQuery(function ($) {
 var $listurl="${pageContext.request.contextPath}/listhistory.action";
 var key =JSON.stringify({});
  $(document).ready(function () {
	  var dt=[];
	  var t ;
	  
	 $.ajax({
     	type: "post",
         url:  $listurl,
         contentType:"application/json;charset=utf-8",
         data : key,
         success: function(dg) {
        	var data=dg['data'];

			 t = $('#listHistory').DataTable();
			for(var ii=0,rr=data.length;ii<rr;ii++){    
				  console.log(data[ii]);

				t.row.add( [
							data[ii]['creatime'],
							data[ii]['companyID_Old'],
							data[ii]['companyName_Old'],
							data[ii]['companyID_New'],
							data[ii]['companyName_New'],
							'<button  class="btn btn-danger btn-xs center"  type="button" onclick=" resumeCompany('+data[ii]['autoID']+')">还原</button>'
							
				        ] ).draw();
				
				
           		
        		 }
         	
             }

         });
	 
	 var table= $('#listHistory').DataTable({
		  data:dt,
          pageLength: 25,
          responsive: false,
          dom: '<"html5buttons"B>lTfgitp',
          bAutoWidth:false,
          bLengthChange: true,
          bInfo: false,
          bFilter: true,
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
   	
});  
});

function  resumeCompany(id,name){
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
                  text: e.printStackTrace,
              	   type: "error",
              	   closeOnConfirm: false
       	   });                 }
          
          });

}
</script>
<!-- Page-Level Scripts -->
    <script>
        $(document).ready(function() {

            $('#listHistory').footable({
            	
            });
        
        });

    </script>
</body>

</html>


