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
            
            <br/>
            <strong>Email Details Page:</strong>
            <br/>

            <!-- Modal Email Details-->
            <form>
              <div class="form-group row">
                <label for="from" class="col-sm-2 col-form-label">Email From</label>
                <div class="col-sm-8">
                  <label for="from" class="col-sm-8 col-form-label">
                      <c:forEach var="address" items="${email.message.from}">
                           ${address.toString()}
                       </c:forEach>
                  </label>
                </div>
              </div>
              <div class="form-group row">
                <label for="subject" class="col-sm-2 col-form-label">Subject</label>
                <div class="col-sm-8">
                    <label for="subject" class="col-sm-8 col-form-label">
                        <fmt:formatDate value="${email.message.sentDate}" pattern="dd-MM-yyyy HH:mm" />
                    </label>
                </div>
              </div>
              <div class="form-group row">
                <label for="subject" class="col-sm-2 col-form-label">Subject</label>
                <div class="col-sm-10">
                    <label for="subject" class="col-sm-10 col-form-label">
                        ${email.message.subject}
                    </label>
                </div>
              </div>
              <div class="form-group row">
                <label for="subject" class="col-sm-2 col-form-label">Text</label>
                <div class="col-sm-10">
                    <c:out value="${email.text}" escapeXml="false" />
                  
                </div>
              </div>
            </form>
            
	</div>
                  
	<jsp:include page="../fragments/footer.jsp" />
        
</body>
</html>