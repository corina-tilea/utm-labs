<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Upload/Download/Delete Documents</title>
</head>

<body>
	<div class="generic-container">
		
		<div class="panel panel-default">
			
			<div class="panel-heading"><span class="lead">Upload New Document</span></div>
			<div class="uploadcontainer">
                            <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal" action="/spring-mvc-form/add-document-page   " >
			
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="file">Upload a document</label>
							<div class="col-md-7">
								<form:input type="file" path="file" id="file" class="form-control input-sm"/>
								<div class="has-error">
									<form:errors path="file" class="help-inline"/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="file">Description</label>
							<div class="col-md-7">
								<form:input type="text" path="description" id="description" class="form-control input-sm"/>
							</div>
							
						</div>
					</div>
			
					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Upload" class="btn btn-primary btn-sm">
						</div>
					</div>
	
				</form:form>
				</div>
		</div>
	 	
   	</div>
</body>
</html>