package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import database.dbconnect;

public class Appointment {
	//A common method to connect to the DB
	dbconnect obj = new dbconnect();
	
	
	public String insertAppointment(String docID, String AppDate, String AppTime, String PID) {
	
	String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into appointment(`docID`,`AppDate`,`AppTime`,`PID`)"+ "values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			//binding values
			preparedStmt.setInt(1, Integer.parseInt(docID));
			preparedStmt.setDate(2, Date.valueOf(AppDate));
			preparedStmt.setString(3, AppTime);
			preparedStmt.setInt(4, Integer.parseInt(PID));
			preparedStmt.execute();
			con.close();
			
			String newAppointment = readAppointment();
			output = "{\"status\":\"success\",\"data\":\""+ newAppointment + "\"}";
			System.out.println("Inserted successfully...................................");
			
		} catch (Exception e) {
			output = "{\"status\":\"error\",\"data\":\" Error while inserting the Hospitals.\"}";
			System.out.println("Error while inserting the Appointment........."+ e);
			System.err.println(e.getMessage());
		}
		return output;
	}
	//reading an item
			public String readAppointment() {
				String output = "";
				try {
					Connection con = obj.connect();
					if (con == null) {
						return "Error while connecting to the database for reading.";
					}
					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>Doctor ID</th><th>Appointment Date</th> <th>"
							+ "Appointment Time</th><th>Patient ID</th><th>Update</th><th>Remove</th></tr>";
					String query = "select * from appointment";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					// iterate through the rows in the result set
					while (rs.next()) {
						String AppID = Integer.toString(rs.getInt("AppID"));
						String docID = Integer.toString(rs.getInt("docID"));
						String AppDate = rs.getString("AppDate");
						String AppTime = rs.getString("AppTime");
						String PID = Integer.toString(rs.getInt("PID"));
						
					

						
						// Add into the html table
						output += "<tr><td><input id='aidAppointmentIDUpdate'name='aidAppointmentIDUpdate' type='hidden' value='" + AppID + "'>" + 
								docID + "</td>";
						output += "<td>" + AppDate + "</td>";
						output += "<td>" + AppTime + "</td>";
						output += "<td>" + PID + "</td>";
						// buttons
						output += "<td><input name='btnUpdate' type='button'"
								+ "value='Update'class='btnUpdate btn btn-secondary'></td>"
								+"<td><input name='btnRemove'type='button'"
								+" value='Remove'class='btnRemove btn btn-danger' data-appointmentid='"+ AppID + "'>" + "</td></tr>";
						//1233
					}
					con.close();
					// Complete the html table
					output += "</table>";
				} catch (Exception e) {
					output = "Error while reading the Appointment.";
					System.err.println(e.getMessage());
				}
				return output;
			}
			
		

	public String updateAppointment(String ID, String doctorID, String AppointmentDate, String AppointmentTime, String PatientID) {
		System.out.println("Update method...............................................................................");
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			//update
			// create a prepared statement
			String query = "UPDATE appointment SET docID=?,AppDate=?,AppTime=?,PID=? WHERE AppID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(doctorID));
			preparedStmt.setDate(2, Date.valueOf(AppointmentDate));
			preparedStmt.setString(3, AppointmentTime);
			preparedStmt.setInt(4, Integer.parseInt(PatientID));
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newAppointment = readAppointment();
			
			output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\",\"data\":\" Error while updating the item \"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteAppointment(String AppID) {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from appointment where AppID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(AppID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newAppointment = readAppointment();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \" Error while deleting the appointment.\"}";
			System.err.println(e.getMessage());
	  }
		return output;
	}

}
