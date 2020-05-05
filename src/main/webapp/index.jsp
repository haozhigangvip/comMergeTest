<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <!-- 导入 css-->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <!--导入bootstrap.js-->
     <script src="https://code.jquery.com/jquery-1.10.2.js"></script>  
 	 <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>  

	
    <style type="text/css">
      .ui-com {
        font-size: 14px;
        max-height: 100px;
        max-width: 172px;
        overflow-y: auto;
        /* 防止水平滚动条 */
        overflow-x: hidden;
      }
    </style>  


</head>




<body>

<div class="input-group input-group-lg">
<div class="div class="input-group-prepend">
    <span class="input-group-addon" id="sizing-addon1">公司名称</span>
    <input type="text" class="form-control" placeholder="CompanyName" aria-describedby="sizing-addon1" id="Commpany1">

<div class="dropdown-menu" id="listdiv">
</div>
</div>
</div>


  
</head>
<body>
<!-- input id="search_kw" type="text" name="myname" class="ui-autocomplete"></input>  -->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid"> 
    <div class="navbar-header">
        <a class="navbar-brand" href="#">搜索自动提示</a>
    </div>
    <div class="navform">
        <form class="navbar-form navbar-left" role="search">       
            <input id="search_kw" type="text" name="myname" class="form-control ui-com"  placeholder="请输入基金关键字">
        </form>
    </div>
    </div>
</nav>
</body>
<script type="text/javascript">
jQuery(function ($) {
$(document).ready(function () {  
    (function(){  
        var insertOptions = function(data, id) {  
            
        	var result = new Array();  
        	 for(var ii=0,rr=data.length;ii<rr;ii++){
        		 result.push(data[ii]['companyname']);
        		 
        		 } 

           
            console.log(result.toString());
            
            $('#search_kw').autocomplete({  
                source: result  
            });  
        }  

        $('#search_kw').keyup(function(){  
            var right_id = "search_kw";  
            var $url="${pageContext.request.contextPath}/searchCompany.action";
            
            var skeyword =JSON.stringify({'companyname': $("#search_kw").val().trim()});
            $.ajax({
            	type: "post",
                url:  $url,
                processData: false,
                contentType:"application/json;charset=utf-8",
                dataType : 'json',
                data : skeyword,
                cache: false,
                success: function(data) {
                        insertOptions(data['listCompany'], right_id);
                    }

                });
        });  

    })();  
});  
});
</script>


<script type="text/javascript">
$(function(){
	$('.dropdown-toggle').dropdown(function(){
		alert("111");
	});
	$('#basic').prev().find("input").keyup(function (){
      
        $value=$(this).val().trim();

       if($value!=null && $value!=""){
    	   $value=JSON.stringify({'companyname': $value});
           $url="${pageContext.request.contextPath}/searchCompany.action";
           $.ajax({
               type: "post",
               url:  $url,
               processData: false,
               contentType:"application/json;charset=utf-8",
               dataType : 'json',
               data : $value,
               cache: false,
               success: function(data) {
            	   $("#listdiv").html("");
            	  
                  if(data['listCompany'].length>0){
					
                	 for(var ii=0,rr=data['listCompany'].length;ii<rr;ii++){
                		  $("#listdiv").append($('<li><a class="dropdown-item">'+data['listCompany'][ii]['comID']+"-"+data['listCompany'][ii]['companyname']+'</a></li>'));
                		  $("#basic").append($('<option value="'+data['listCompany'][ii]['comID']+'" >'+data['listCompany'][ii]['companyname']+'</option>'));
                	 } 
 					
                	
                	  $("#listdiv").show();
                	  $(".selectpicker" ).selectpicker('refresh');
                  }else{
                
                	  $("#listdiv").hide();
                  }
                	  
                	  
               },
               error: function(e) {
                  alert(e)
               }
           })
           return false;
       }else{

    	   $("#listdiv").hide();
    	   return false;
       }



    });
})



</script>
  <script charset="gbk" src="http://www.baidu.com/js/opensug.js"></script>
        <!--JQuery JS依赖-->
        <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
        <!--Bootstrap JS依赖-->
        <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>
