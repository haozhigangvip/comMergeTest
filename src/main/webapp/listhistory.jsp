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
                    <h2>合并历史记录</h2>
                    <ol class="breadcrumb">
                      
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
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
                    <table class="table table-striped table-bordered table-hover dataTables-example" id="listHistory" >
                    <thead>
                    <tr>
                        <th>合并时间</th>
                        <th>原客户（公司）ID</th>
                        <th>原客户（公司）名称</th>
                        <th>合并后客户（公司）ID</th>
                        <th>合并后客户（公司）名称</th>
                        <th>操作</th>
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
        <div class="footer">
            <div class="float-right">
                 <strong>2.9.2 inspinia</strong>
            </div>
            <div>
                <strong>Copyright</strong> inspinia 2.9.2 &copy; 2014-2018
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
    <script src="js/jquery.metisMenu.js"></script>
    <script src="js/jquery.slimscroll.min.js"></script>
    <script src="js/datatables.min.js"></script>
    <script src="js/dataTables.bootstrap4.min.js"></script>
    




<script type="text/javascript">
jQuery(function ($) {
 var $listurl="${pageContext.request.contextPath}/listhistory.action";
 var key=null
  $(document).ready(function () {
	 $.ajax({
     	type: "post",
         url:  $listurl,
         processData: false,
         contentType:"application/json;charset=utf-8",
         data : key,
         cache: false,
         success: function(data) {
        	 va

        	 for(var item in data){
        		 alert(item[1] );
        		 var trHTML = "<tr><td class='gradeX'>"+item.creatime+"</td><td  class='center'>"
        		 +item.CompanyID_Old+"</td> <td  class='center'>"
        		 +item[0]+"</td><td  class='center'>"
        		 +item[2]+"</td><td  class='center'>"
        		 +item[0]+"</td><td  class='center'><button  class='btn btn-danger btn-xs'>恢复</button></td></tr>";
        	 	$("#listHistory").append(trHTML)}
         	
             }

         });
		
	  $('.dataTables-example').DataTable({
          pageLength: 25,
          responsive: true,
          dom: '<"html5buttons"B>lTfgitp',
          buttons: [
   
              {extend: 'print',
               customize: function (win){
                      $(win.document.body).addClass('white-bg');
                      $(win.document.body).css('font-size', '10px');

                      $(win.document.body).find('table')
                              .addClass('compact')
                              .css('font-size', 'inherit');
              }
              }
          ]

      });
   
});  
});
</script>

</body>

</html>


