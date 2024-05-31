<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>employee</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mt-4">EMPLOYEE</h1>
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th hidden="hidden">Employee ID</th>
            <th>Employee Name</th>
            <th>Department Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <#list employees as employee>
            <tr>
                <td hidden="hidden">${employee.employeeId}</td>
                <td>${employee.employeeName}</td>
                <td>${employee.departmentName}</td>
                <td>
                    <form action="${contextPath}/deleteEmployee" method="post" style="display:inline;">
                        <input type="hidden" name="employeeId" value="${employee.employeeId}">
                        <button type="submit" class="btn btn-danger btn-sm"
                                onclick="return confirm('Are you sure you want to delete this employee?')">Delete
                        </button>
                    </form>
                    <form action="${contextPath}/updateEmployee" method="get" style="display:inline;">
                        <input type="hidden" name="employeeId" value="${employee.employeeId}">
                        <button type="submit" class="btn btn-warning btn-sm"
                                onclick="return confirm('Are you sure you want to update this employee?')">Update
                        </button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <form action="${contextPath}/addEmployee" method="get" style="display:inline;">
        <button type="submit" class="btn btn-success">Add Employee</button>
    </form>
    <button type="button" class="btn btn-info" onclick="window.location.href='${contextPath}/department';">View
        Departments
    </button>
</div>
</body>
</html>
