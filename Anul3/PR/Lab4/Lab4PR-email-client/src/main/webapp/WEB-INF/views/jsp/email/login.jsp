<%@ page session="false"%>
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
                <spring:url value="/login" var="userActionUrl" />
                <form:form class="form-signin" method="post" modelAttribute="userForm" action="${userActionUrl}">

	
                    <h2 class="form-signin-heading">Please sign in</h2><br/>
                    <spring:bind path="email">
                        <label for="inputEmail" class="sr-only">Email address</label>
                        <form:input path="email" class="form-control" id="email" placeholder="Email address" />
                        <form:errors path="email" class="control-label" />
                    </spring:bind>
                        
                    <spring:bind path="password">    
                        <label for="inputPassword" class="sr-only">Password</label>
                        <form:password path="password" class="form-control" id="password" placeholder="Password" />
                        <form:errors path="password" class="control-label" />
                    </spring:bind>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="remember-me"> Remember me
                        </label>
                    </div>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                </form:form>
                
<!--                <form class="form-signin">
                    <h2 class="form-signin-heading">Please sign in</h2><br/>
                    <label for="inputEmail" class="sr-only">Email address</label>
                    <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus> <br/>
                    <label for="inputPassword" class="sr-only">Password</label>
                    <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="remember-me"> Remember me
                        </label>
                    </div>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                </form>-->

	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>