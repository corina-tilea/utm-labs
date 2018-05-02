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

	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>
            <a href="#">Inbox <span class="badge">${unreadMsgs}</span></a>
            
            <spring:url value="/view-email-details" var="emailDetails" />
            <spring:url value="/last-emails" var="lastEmailsAction" />
            <spring:url value="/new-email" var="newEmailAction" />
            <!--Load last n emails-->
            <form style="display: block;" class="form-inline" id="form-load-lastn-emails" method="post" action="${lastEmailsAction}">
                  <div class="form-group mb-2">
                      <label >Number of last email to load</label>
                   </div>    
                  <div class="form-group mb-2">
                       <input type="text"  placeholder="50"  name="nrOfEmails"   class="findText" />
                 </div>  
                <a class="btn btn-primary mb-2" onclick="document.getElementById('form-load-lastn-emails').submit()" >Load last emails</a>  
            </form> 
            <br/>
            
            <!-- Button trigger modal -->
            <a  class="btn btn-primary" href="${newEmailAction}" >
              Write new Email
            </a>
            <!--Emails table-->
                <table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>From</th>
					<th>Subject</th>
					<th>Date</th>
					<th>NrOfFiles</th>
					<th>Actions</th>
				</tr>
			</thead>

                        <c:forEach var="emailWrapper" items="${emails}" varStatus="indexStatus" >
				<tr>
					<td>
                                            ${indexStatus.index+1}
					</td>
                                        <td><c:forEach var="address" items="${emailWrapper.message.from}">
                                                ${address.toString()}
                                            </c:forEach>
                                        </td>
					<td class="width-500" >
                                            <div class="text-bold">${emailWrapper.message.subject}</div>
                                                ${emailWrapper.beginText}
                                        </td>
					<td>
                                            <fmt:formatDate value="${emailWrapper.message.sentDate}" pattern="dd-MM-yyyy HH:mm" />
                                        </td>
					<td>${emailWrapper.attachedFiles.size()}</td>
					<td>
                                            <a class="btn btn-info view-email-details"  href="${emailDetails}/${indexStatus.index}" >Details</a>
                                        </td>
                                </tr>
			</c:forEach>
		</table>
	</div>
                  
	<jsp:include page="../fragments/footer.jsp" />
        
<!--        <script>
            $('button.view-email-details').on('click', function (event) {
                var selected = $(this).attr("itemid");
                $.ajax({
                    type: 'GET',
                    url: "/spring-mvc-form/view-email-details/" + selected,
                    success: function (data) {
                        console.log('success', data, selected);
                        $("#exampleModal").dialog({modal:true}).dialog('open');
                    },
                    error: function (exception) {
                        console.log('Exeption:' + exception);
                    }
                });
                e.preventDefault();
            });
        </script>-->

</body>
</html>