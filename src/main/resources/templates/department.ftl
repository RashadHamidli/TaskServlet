<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>DEPARTMENT</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mt-4">DEPARTMENT</h1>
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th hidden="hidden">Department ID</th>
            <th>Department Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <#list departments as department>
            <tr>
                <td hidden="hidden">${department.departmentId}</td>
                <td>${department.departmentName}</td>
                <td>
                    <form action="${contextPath}/deleteDepartment" method="post" style="display:inline;">
                        <input type="hidden" name="departmentId" value="${department.departmentId}">
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this department?')">Delete</button>
                    </form>
                    <form action="${contextPath}/updateDepartment" method="get" style="display:inline;">
                        <input type="hidden" name="departmentId" value="${department.departmentId}">
                        <button type="submit" class="btn btn-warning btn-sm" onclick="return confirm('Are you sure you want to update this department?')">Update</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <form action="${contextPath}/addDepartment" method="get" style="display:inline;">
        <button type="submit" class="btn btn-success">Add Department</button>
    </form>
    <button type="button" class="btn btn-info" onclick="window.location.href='${contextPath}/employee';">View Employee</button>
</div>
</body>
</html>
