<%@ page isErrorPage="true" contentType="text/html" %>
<%@ include file="taglibraries.jspf" %>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<html>
	<head>
		<title>Error 500</title>
		<link type='text/css' rel='stylesheet' href='/registration-webapp/stylesheet/reset.css'></link>
		<link type='text/css' rel='stylesheet' href='/registration-webapp/stylesheet/styling.css'></link>
	</head>
	<body>
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<table align="center" height="100%" width="100%" >
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td align="center" ID="h1blue">
					Hmmm, something went wrong, see the error below
				</td>
			</tr>
			<tr height="400">
				<td>
					<table height="100%" width="100%" ID="bgimage">
						<tr style="min-height:350px;">
							<td align="center">
								<textarea wrap="off" rows="80" cols="120">Request URI: ${pageContext.errorData.requestURI}
									Servlet Name: ${pageContext.errorData.servletName}
									Status Code: ${pageContext.errorData.statusCode}
									Exception: ${pageContext.errorData.throwable}
									Message: ${pageContext.errorData.throwable.message}
									Stack Trace: 
									<% 
									List<Throwable> errorList = new ArrayList<Throwable>();
									Throwable error = pageContext.getErrorData().getThrowable();
									
									while (error != null) {
										errorList.add(error);
										error = error.getCause();
									}
									pageContext.setAttribute("errorList", errorList);
									%>
									<c:forEach var="throwable" items="${errorList}" varStatus="varStatus">
									<c:if test="${varStatus.index > 0}">
									Caused By:
									Exception: ${throwable}
									Message: ${throwable.message}
									
									</c:if>
										<c:forEach var="st" items="${throwable.stackTrace}">${st}
										</c:forEach>
									</c:forEach>
				
								</textarea>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
