<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.aniket.factory.StudentServiceFactory" %>
<%@ page import="com.aniket.service.StudentService" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student | Records System</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            margin-top: 50px;
        }
        .header {
            text-align: center;
            padding: 20px;
            background-color: #343a40;
            color: white;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        .filter-section {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
        .filter-heading {
            font-size: 18px;
            font-weight: bold;
            color: #495057;
        }
        .pagination {
            justify-content: center;
        }
        .pagination a {
            color: #343a40;
        }
        .filter-btns {
            display: flex;
            justify-content: space-between;
            margin-top: 15px;
        }
    </style>
</head>
<body>

<div class="container">
    <!-- Header -->
    <div class="header">
        <h1>Student Records System</h1>
    </div>

    <!-- Filter Section -->
    <div class="filter-section">
        <p class="filter-heading">Filter Options</p>
        <form action="./StudentFilterServlet" method="GET">
        <div class="row">
            <!-- City Filter -->
            <div class="col-md-6 mb-3">
                <label for="cityFilter">Sort by City</label>
                <select class="form-control" id="cityFilter" name="cityFilter">
                    <option value="">Select City</option>
                    <% 
                    	StudentService studentService = StudentServiceFactory.getInstanceStudentService();
                		List<String> listCities = studentService.filterStudentCities();
                		if(listCities != null){
                			for(String row:listCities){
                    %>
                    <option value="<%=row%>"><%=row%></option>
                    
                    <%
                									}
                		}
                    %>
                </select>
            </div>
            <!-- Percentage Filter -->
            <div class="col-md-6 mb-3">
                <label for="percentageFilter">Percentage</label>
                <select class="form-control" id="percentageFilter" name="perFilter">
                    <option value="">Select Percentage</option>
                    <option value="60.00">First Class</option>
                    <option value="75.00">Distinction</option>
                    <option value="90.00">Above 90%</option>
                </select>
            </div>
        </div>
        <!-- Filter Buttons -->
        <div class="filter-btns">
            <button name="action" value="Filter" class="btn btn-primary">Apply Filters</button>
            <button name="action" value="All" class="btn btn-secondary">Display All</button>
        </div>
        </form>
    </div>

    <!-- Student Records Table (Placeholder for now) -->
    <div class="table-responsive">
        <table id="studentTable" class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>R.no</th>
                    <th>Name</th>
                    <th>City</th>
                    <th>Percentage</th>
                    <th>Bday</th>
                </tr>
            </thead>
            <tbody id="studentTableBody">
                <c:forEach var="student" items="${students}">
                <tr>
                    <td>${student.rno}</td>
                    <td>${student.name}</td>
                    <td>${student.addr}</td>
                    <td>${student.per}</td>
                    <td>${student.bday}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- Pagination -->
    <nav aria-label="Page navigation">
        <ul class="pagination">
        	<c:if test="${currentPage > 1}">
            <li class="page-item">
                <a class="page-link" href="StudentFilterServlet?action=All&page=${currentPage - 1}">«</a>
            </li>
            </c:if>
            <li class="page-item">
                <a class="page-link" href="#">${currentPage}</a>
            </li>
            <c:if test="${currentPage < totalPages}">
            <li class="page-item">
                <a class="page-link" href="StudentFilterServlet?action=All&page=${currentPage + 1}">»</a>
            </li>
            </c:if>
        </ul>
    </nav>
</div>

<!-- Bootstrap JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- <script>
        function applyFilter() {
            const city = $('#cityFilter').val();
            const percentage = $('#percentageFilter').val();
            $.ajax({
                url: 'StudentFilterServlet',
                type: 'GET',
                data: { city: city, percentage: percentage },
                success: function(data) {
                    $('#studentTable tbody').html(data); // Update table with filtered data
                }
            });
        }
        
        function displayAllData() {
            $.ajax({
                url: 'StudentFilterServlet',
                type: 'GET',
                data: { city: "", percentage: "" }, // No filter parameters to fetch all data
                success: function(data) {
                    $('#studentTable tbody').html(data); // Update table with all data
                }
            });
        }
    </script> -->

</body>
</html>
