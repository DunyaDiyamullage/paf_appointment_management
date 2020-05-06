$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateAppointmentForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var method = ($("#aidAppointmentIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "AppointmentAPI",
		type : method,
		data : $("#formAppointment").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onAppointmentSaveComplete(response.responseText, status);
		}
	});
	//$("#formAppointment").submit();
});

//UPDATE==========================================
$(document).on("click",".btnUpdate",function(event) {
					$("#aidAppointmentIDSave").val($(this).closest("tr").find('#aidAppointmentIDUpdate').val());
					$("#docID").val($(this).closest("tr").find('td:eq(0)').text());
					$("#AppDate").val($(this).closest("tr").find('td:eq(1)').text());
					$("#AppTime").val($(this).closest("tr").find('td:eq(2)').text());
					$("#PID").val($(this).closest("tr").find('td:eq(3)').text());
		});
function onAppointmentSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divAppointmentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#aidAppointmentIDSave").val("");
	$("#formAppointment")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "AppointmentAPI",
		type : "DELETE",
		data : "AppID=" + $(this).data("appointmentid"),
		dataType : "text",
		complete : function(response, status) {
			onAppointmentDeleteComplete(response.responseText, status);
		}
	});
});

function onAppointmentDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divAppointmentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}



// CLIENTMODEL=========================================================================
function validateAppointmentForm() {
	// CODE
//	if ($("#AppID").val().trim() == "") {
//		return "Insert Appointment ID.";
//	}
	
	if ($("#docID").val().trim() == "") {
		return "Insert Doctor ID.";
	}
	
	if ($("#AppDate").val().trim() == "") {
		return "Insert Appointment Date.";
	}
	
	if ($("#AppTime").val().trim() == "") {
		return "Insert Appointment Time.";
	}
	if ($("#PID").val().trim() == "") {
		return "Insert Patient ID.";
	}
	return true;
}

