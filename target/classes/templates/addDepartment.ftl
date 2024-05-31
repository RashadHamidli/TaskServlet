<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Department</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2 class="mt-4">Add Department</h2>
    <form action="${contextPath}/addDepartment" method="post">
        <div class="form-group">
            <label for="departmentName">Department Name:</label>
            <input type="text" class="form-control" id="departmentName" name="departmentName" required>
        </div>
        <button type="submit" class="btn btn-primary">Add Department</button>
        <button type="button" class="btn btn-secondary" onclick="window.location.href='${contextPath}/department';">Cancel</button>
    </form>
</div>
</body>
</html>
