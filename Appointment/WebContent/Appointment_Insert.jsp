<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.Appointment" %>
     <%@ page import="com.AppointmentAPI" %>
   

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointment Insert</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="./Components/Appointment.js"></script>
</head>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 90%;
  margin-left: auto;
  margin-right: auto;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
.card{
	padding: 20px;
	border: 1px solid black;
	border-radius: 10px;
	margin-bottom: 20px;
	font-size: 15px;
	margin-top: 15px;
}
#btnSave{
	margin-top: 15px;
	font-size: px;
	width: 100%;
}
.alert{
	width: 80%;
	margin-left: auto;
	margin-right: auto;
	padding: 15px;
	text-align: center;
}

  </style>
<body>
	<div class="jumbotron text-center" style="padding: 10px;;">
		<h1>Appointment</h1>
	
	</div>
	<div class="container">
		<div class="row"><div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="card">
	<form id="formAppointment" name="formAppointment" method="post" action="Appointment_Insert.jsp">
	    Doctor ID:
	     <input id="docID" name="docID" type="text" 
			class="form-control form-control-sm"required > <br> 
		Appointment Date: 
		<input id="AppDate" name="AppDate" type="date"
			class="form-control form-control-sm" required> <br> 
		Appointment Time:
		 <input id="AppTime" name="AppTime" type="time"  
			class="form-control form-control-sm" required> <br> 
		Patient ID:
		 <input id="PID" name="PID" type="text"  
			class="form-control form-control-sm" required> <br> 
		<input id="btnSave" name="btnSave" type="button" value="Save" 
			class="btn btn-primary"  > 
		<input type="hidden" id="aidAppointmentIDSave" name="aidAppointmentIDSave" value="">
	</form>
	</div>
</div>
</div>
</div>

	<div id="alertSuccess" class="alert alert-success">
	
	</div>
	<div id="alertError" class="alert alert-danger"></div>
		
	 <div id="divAppointmentGrid">
	
	<%
	Appointment a1 = new Appointment();
	out.print(a1.readAppointment());
	%>
		</div>

		
</body>
</html>