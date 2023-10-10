// 	userLogin JS 

$(document).ready(function() {
	
	$("#log_in").on("submit", function(event) {
		
		event.preventDefault(); 
		
		let loginData = {
			loginForm_username: $("input[name='loginForm_username']").val(), 
			login_password: $("input[name='login_password']").val()
		}; 
		
		let csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content"); 
		let csrfHeaderName = document.querySelector("meta[name='_csrf_header']").getAttribute("content"); 
		
		console.log(loginData); 
		
		$.ajax({
			type: "POST", 
			url: "/index/login", 
			data: loginData, 														// above defined `loginData` object 
			contentType: "application/x-www-form-urlencoded; charset=UTF-8", 		// opposed to a JSON payload, send URL encoded parameters 
			headers: { [csrfHeaderName]: csrfToken }, 
			success: function(response) {
				
				if(response.success) {
					
					window.location.href = "/index/myprofile"; 						// Redirect to profile page (myprofile.html) 
					
				} 
				else {
					
					let failString = "Invalid credentials: Either the userID and or password failed authentication"; 
					console.log(failString); 
					alert(failString); 
					
				}
				
			}, 
			error: function(xhr, status, error) {
				
				 let jsonResponse = JSON.parse(xhr.responseText); 
				 alert(jsonResponse.message); 
				
			} 
			
		}); 
		
	}); 
	
	$("#openUsernamePasswordResetModal").click(function() {
		
		$("#resetModal").modal("show"); 
		
	}); 
	
	$(".closeModalButton").click(function() {
		
		$("#resetModal").modal("hide"); 
		
	}); 
	
}); 