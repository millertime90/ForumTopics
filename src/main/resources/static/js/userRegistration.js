// 	userRegistration JS 

$(document).ready(function() {
	
	// When the form is submitted 
	$("#signup").on("submit", function(event) {
		
		console.log("I'm working outside of if statement./nFollowing are password values:"); 
		console.log("Password: " + $("input[name='tentative_password']").val()); 
		console.log("Confirm Password: " + $("input[name='password_hash']").val()); 
		
		// Prevent the form from submitting the traditional way 
		event.preventDefault(); 
		
		if($("input[name='tentative_password']").val() == $("input[name='password_hash']").val()) {
			
			console.log("I'm working in if statement"); 
			
			// Gather form data 
			let formData = {
				firstName: $("input[name='firstName']").val(), 
				lastName: $("input[name='lastName']").val(), 
				email: $("input[name='email']").val(), 
				username: $("input[name='username']").val(), 
				tentative_password: $("input[name='tentative_password']").val(), 
				password_hash: $("input[name='password_hash']").val()
			}; 
			
			let csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content"); 
			let csrfHeaderName = document.querySelector("meta[name='_csrf_header']").getAttribute("content"); 
		
			console.log(formData); 
			
			// Send the data via AJAX 
			$.ajax({
				type: "POST", 
				url: "/index/register", 
				contentType: "application/json", 
				headers: { 
					Accept: "application/json", 
					[csrfHeaderName]: csrfToken 
				}, 
				data: JSON.stringify(formData), 
				success: function(response) { 
					
					let showLoginModal = function() {
						
						// Clear UI form data 
						let UIformData = {
							firstName: $("input[name='firstName']"), 
							lastName: $("input[name='lastName']"), 
							email: $("input[name='email']"), 
							username: $("input[name='username']"), 
							password_hash: $("input[name='password_hash']") 
						}; 
						
						for(let i in UIformData) { UIformData[i].val(""); } 							// clears all but 1 UI data field, `#password`
						$("#password").val(""); 														// clears `#password` value field 
						$("#confirmPasswordIndicator").html(""); 										// clears self HTML (rids of this particular `fa-check-circle`) 
						[...document.querySelectorAll(".requirement")].forEach(i => i.innerHTML = ""); 	// rids of remaining `fa-check-circle`s 
						$("#strength-bar").css("width", "0%"); 											// update `#strength-bar` indicator 
						$("#strength-text").text("Password Required"); 
						
						// Show redirect modal 
						Togglers[5].click(); 																		// hide all `.formModal`s 
						[...document.querySelectorAll(".pageComponent")].forEach(i => i.style.display = "none"); 	// hide all `.pageComponent`s 
						$("#registerSuccessModal").modal("show"); 													// show `.registerSuccessModal` 
						
						// redirect to login modal 
						let toLogin = document.querySelector("#toLogin"); 
						toLogin.addEventListener("click", function() {
							
							Togglers[1].click(); 												// Show login form. `Togglers` defined in `main.js` 
							$("#registerSuccessModal").modal("hide");  							// hide `.registerSuccessModal` 
							
						}); 
						
						
					}
				
					console.log("Registration successful!", response); 	// You can redirect or show a message based on the response 
					
					// Show redirect modal and redirect to login modal 
					showLoginModal(); 
				
				}, 
				error: function(error) {
					
					console.log("Error during registration:", error.responseJSON || error.responseText || error); 
					
					if(error.responseJSON && error.responseJSON.message) {
						
						alert(error.responseJSON.message); 
						
					} 
					else if(error.responseText) {
						
						alert(error.responseText); 
						
					} 
					else {
						
						console.log("An unexpected error incurred", error); 
						alert("An unexpected error incurred."); 
						
					}
					
				} 
				
			}); 
			
		} 
		else { return; } 
		
	}); 
	
}); 