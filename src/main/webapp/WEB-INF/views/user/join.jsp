<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>


<style>
.fileDrop {
  width: 80%;
  height: 100px;
  border: 1px dotted gray;
  background-color: lightslategrey;
  margin: auto;
  
}
</style>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">REGISTER BOARD ${login }</h3>
				</div>
				<!-- /.box-header -->

<form id='registerForm' role="form" method="post">
	<div class="box-body">
		<div class="form-group">
			<label for="userid">USER ID</label> <input type="text"
				name='uid' class="form-control" placeholder="Enter Title" value='user'>
		</div>
		<div class="form-group">
			<label for="userid">PASSWORD</label> <input type="password"
				name='upw' class="form-control" placeholder="Enter Title" value='1111'>
		</div>
		<div class="form-group">
			<label for="userid">USER NAME:</label> <input type="text"
				name='uname' class="form-control" placeholder="Enter Title" value='테스터'>
		</div>
			</div>

	<!-- /.box-body -->

	<div class="box-footer">
		<div>
			<hr>
		</div>

		
		<button type="submit" class="btn btn-primary">Submit</button>

	</div>
</form>


			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

 

<%@include file="../include/footer.jsp"%>
