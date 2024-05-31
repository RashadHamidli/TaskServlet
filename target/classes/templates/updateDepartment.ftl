<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Department</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2 class="mt-4">Update Department</h2>
    <form action="${contextPath}/updateDepartment" method="post">
        <input type="hidden" name="departmentId" value="${department.departmentId}">
        <div class="form-group">
            <label for="departmentName">Department Name:</label>
            <input type="text" class="form-control" id="departmentName" name="departmentName" value="${department.departmentName}" required>
        </div>
        <button type="submit" class="btn btn-primary">Update Department</button>
        <a href="${contextPath}/department" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>
