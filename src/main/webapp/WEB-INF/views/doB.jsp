<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<ul id='testUL'>

</ul>

	<!-- jQuery 2.1.4 -->
	<script src="/controller/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	
	<script>
	$(document).ready(function(){
		
		var arr = ['123','456','789'];
		
		
		$('#testUL').on("click", "[data-rno]", function(){
			
			console.log("click", $(this).attr("data-rno"));
			
		});
		
		
		
		var str = "";
			
		for(var i = 0; i < arr.length; i++){
			
			 str += "<li data-rno='"+arr[i]+"'>" + arr[i];
			 str+=  "</li>";
			
		}
		
		$("#testUL").html(str);
		
	});
	
	
	
	</script>
	
</body>
</html>