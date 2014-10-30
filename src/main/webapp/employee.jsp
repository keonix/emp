<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Servlet task</title>
<script src="js/jquery.min.js"></script>
<script src="js/script.js"></script>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<fieldset style="width: 575px;">
		<legend>
			<b>Enter you data:</b>
		</legend>
		<form action="index.html" method="post">
			<!-- Create fields for new employee -->
			<table cellspacing="0" cellpadding="2" border="0">
				<tr>
					<td>Enter you first name: ${empty fname ? '<font color="red">*</font>' : '*'}</td>
					<td><input type="text" value="${empty fname ? '' : fname}" name="fname" id="firstNameId"></td>
				</tr>
				<tr>
					<td>Enter you last name: ${empty lname ? '<font color="red">*</font>' : '*'}</td>
					<td><input type="text" value="${empty lname ? '' : lname}" name="lname" id="lastNameId"></td>
				</tr>
				<tr>
					<td>Enter you email: ${empty email ? '<font color="red">*</font>' : '*'}</td>
					<td><input type="text" value="${empty email ? '' : email}" name="email" id="emailId"></td>
				</tr>
				<tr>
					
				</tr>
				<tr>
					<td><input type="reset" value="sbros"></td>
					<td><input type="submit" value="submit" name="submitData" id="submitDataId"></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<div class="errMsg">
		<c:out value="${errMsg}" />
	</div>
	<div class="empTable">
		<table>
		<c:choose>
			<c:when test="${empty applicationScope.employees}">
				<!-- Inform user that there's no employee yet -->
					<tr>
						<td><span style="color: red; font-weight:bold;">There isn't exists employee yet</span></td>
					</tr>
			</c:when>
			<c:otherwise>
						<tr>
						<td>ID</td>
						<td>Name</td>
						<td>LastName</td>
						<td>Email</td>
					</tr>
					<c:forEach var="employee" items="${applicationScope.employees}">
					<tr>
						<td><c:out value="${employee.id}" /></td>
						<td><c:out value="${employee.fname}" /></td>
						<td><c:out value="${employee.lname}" /></td>
						<td><c:out value="${employee.email}" /></td>
						</tr>
					</c:forEach>
					</c:otherwise>
		</c:choose>
		</table>
	</div>
</body>
</html>
