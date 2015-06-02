<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <script src="js/datePicker.js"></script> -->
</head>
<body>
<jsp:include page="menuHeader.jsp" />
<form:form action="saveTests" modelAttribute="labTests" method="POST">
<jsp:include page="showLabTestTableCommon.jsp" />
<p></p>
<input type="submit" class="button" name="commit" value="Save">
</form:form>
</body>
</html>

