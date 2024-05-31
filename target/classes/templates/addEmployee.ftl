<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Employee</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2 class="mt-4">Add Employee</h2>
    <form action="${contextPath}/addEmployee" method="post">
        <div class="form-group">
            <label for="employeeName">Employee Name:</label>
            <input type="text" class="form-control" id="employeeName" name="employeeName" required>
        </div>

        <div class="form-group">
            <label for="departmentName">Department Name:</label>
            <select class="form-control" id="departmentName" name="departmentName" required>
                <#list departments as department>
                    <option value="${department.departmentName}">${department.departmentName}</option>
                </#list>
            </select>
        </div>

        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Add Employee">
            <input type="button" class="btn btn-secondary" value="Cancel" onclick="window.location.href='${contextPath}/employee';">
            <button type="button" class="btn btn-info" onclick="window.location.href='${contextPath}/department';">View Departments</button>
        </div>
    </form>
</div>
</body>
</html>
