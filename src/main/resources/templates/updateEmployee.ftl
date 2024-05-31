<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2 class="mt-4">Update Employee</h2>
    <form action="${contextPath}/updateEmployee" method="post">
        <input type="hidden" name="employeeId" value="${employee.employeeId}">
        <div class="form-group">
            <label for="employeeName">Employee Name:</label>
            <input type="text" class="form-control" id="employeeName" name="employeeName" value="${employee.employeeName}" required>
        </div>

        <div class="form-group">
            <label for="departmentId">Department:</label>
            <select class="form-control" id="departmentId" name="departmentId" required>
                <#list departments as department>
                    <option value="${department.departmentId}"
                            <#if department.departmentName == employee.departmentName>selected</#if>>${department.departmentName}</option>
                </#list>
            </select>
        </div>

        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Update Employee">
            <a href="${contextPath}/employee"><button type="button" class="btn btn-secondary">Cancel</button></a>
        </div>
    </form>
</div>
</body>
</html>
