<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>
     <spring:url value="/emails" var="emails-url" />

	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>
            <br/>
            <a href="${emails-url}">Inbox <span class="badge">${unreadMsgs}</span></a>
            <hr/>
            <spring:url value="/last-emails" var="lastEmailsAction" />
            <br/>

            <div class="text-center-align">
                <label class="text-center text-bold text-size-19"> Write New Email Here...</label>
            </div>
            <br/>
            <div class="uploadcontainer">
            <form:form method="POST" modelAttribute="email" enctype="multipart/form-data" class="form-horizontal" action="/spring-mvc-form/send-email-new" >
                        <div class="row">
                                <div class="form-group col-md-12">
                                        <label class="col-md-3 control-lable" for="recipient">Recipient</label>
                                        <div class="col-md-7">
                                                <form:input type="text" path="recipient" id="recipient" class="form-control input-sm"/>
                                        </div>
                                </div>
                        </div>
                        <div class="row">
                                <div class="form-group col-md-12">
                                        <label class="col-md-3 control-lable" for="cc">CC</label>
                                        <div class="col-md-7">
                                                <form:input type="text" path="cc" id="cc" class="form-control input-sm"/>
                                        </div>
                                </div>
                        </div>
                        <div class="row">
                                <div class="form-group col-md-12">
                                        <label class="col-md-3 control-lable" for="subject">Subject</label>
                                        <div class="col-md-9">
                                                <form:input type="text" path="subject" id="subject" class="form-control input-sm"/>
                                        </div>
                                </div>
                        </div>
                        <div class="row">
                                <div class="form-group col-md-12">
                                        <label class="col-md-3 control-lable" for="body">Email text</label>
                                        <div class="col-md-9">
                                            <form:textarea rows="14" path="body" id="body" class="form-control input-sm"/>
                                        </div>
                                </div>
                        </div>
                        <div class="row">
                                <div class="form-group col-md-12">
                                        <label class="col-md-3 control-lable" for="file">Attach File</label>
                                        <div class="col-md-7">
                                                <form:input type="file" path="fileAttach" id="file" class="form-control input-sm"/>
                                                <div class="has-error">
                                                        <form:errors path="fileAttach" class="help-inline"/>
                                                </div>
                                        </div>
                                </div>
                        </div>
                        
                        <div class="row">
                                <div class="form-actions center-block">
                                        <input type="submit" name="sendParam" value="Send Email" class="btn btn-primary btn-sm">
                                        <input type="submit" name="sendParam" value="Save to Drafts" class="btn btn-warning btn-sm">
                                </div>
                        </div>

                </form:form>
                </div>    
        </div>
                  
	<jsp:include page="../fragments/footer.jsp" />
        

</body>
</html>