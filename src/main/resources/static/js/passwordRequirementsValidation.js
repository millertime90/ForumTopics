// 	passwordRequirementsValidation JS 

$(document).ready(function() { 
	
	const passwordStrengthIndicator = $(".password-strength-indicator"); 
	const signupBtn = $("#signup_btn"); 
	signupBtn.prop("disabled", true); 
	
	function areAllRequirementsMet(password, confirmPassword) {
		
		return password.length >= 8 && 
			   password.match(/[!@#$%^&*()_+\-=[\]{};':"\\/,.<>\/?]/) && 
			   password.match(/[0-9]/) && 
			   password == confirmPassword; 
		
	} 
	
	function checkAndUpdateButtonStatus() {
		
		const password = $("#password").val(); 
		const confirmPassword = $("#confirmPassword").val(); 
		
		if(areAllRequirementsMet(password, confirmPassword)) {
			
			signupBtn.prop("disabled", false); 
			
		} 
		else { signupBtn.prop("disabled", true); } 
		
	} 
	
	$("#password").on("input", function() {
		
		const password = $(this).val(); 
		const strength = calculatePasswordStrength(password); 
		updatePasswordStrengthIndicator(strength); 
		updatePasswordRequirements(password);
		
		// Show/hide password strength indicator based on password length 
		passwordStrengthIndicator.css("visibility", password.length > 0 ? "visible" : "hidden"); 
		
	}); 
	
	$("#confirmPassword").on("input", function() { 
		
		confirmPassword(); 
		checkAndUpdateButtonStatus(); 
		
	}); 
	
	function calculatePasswordStrength(password) {
		
		// Logic here to calculate password strength. 
		// Basic example with three levels for simplicity 
		
		let strength = 0; 
		
		if(password.length >= 0) {
			
			strength += 33; 
			
		} 
		if(password.match(/[!@#$%^&*()_+\-=[\]{};':"\\/,.<>\/?]/)) {
			
			strength += 33; 
			
		} 
		if(password.match(/[0-9]/)) {
			
			strength += 34; 
			
		} 
		
		return strength; 
		
	} 
	
	function updatePasswordStrengthIndicator(strength) {
		
		const strengthBar = $("#strength-bar"); 
		const strengthText = $("#strength-text"); 
		
		strengthBar.css("width", strength + "%"); 
		
		if(strength < 25) {
			
			strengthBar.removeClass("bg-warning bg-info bg-success").addClass("bg-danger"); 
			strengthText.text("Weak password"); 
			
		} 
		else if(strength < 50) {
			
			strengthBar.removeClass("bg-danger bg-info bg-success").addClass("bg-warning"); 
			strengthText.text("Fair password"); 
			
		} 
		else if(strength < 75) {
			
			strengthBar.removeClass("bg-danger bg-warning bg-success").addClass("bg-info"); 
			strengthText.text("Good password"); 
			
		} 
		else {
			
			strengthBar.removeClass("bg-danger bg-warning bg-info").addClass("bg-success"); 
			strengthText.text("Strong password"); 
			
		}
		
	} 
	
	function updatePasswordRequirements(password) {
		
		const requirements = [
			password.length >= 8, 
			password.match(/[!@#$%^&*()_+\-=[\]{};':"\\/,.<>\/?]/), 
			password.match(/[0-9]/) 
		]; 
		
		const passwordRequirements = $("#password-requirements"); 
		const requirementElements = passwordRequirements.find(".requirement"); 
		
		if(password === "") {
			
        	// Password is blank, restore all requirement indicators to asterisks
        	requirementElements.text("*");
        	return;
        	
    	}

    	for(let i = 0; i < requirements.length; i++) { 
        	
        	const iconClass = requirements[i] ? "fas fa-check-circle" : "fas fa-times-circle"; 
        	const iconColor = requirements[i] ? "green" : "red"; 
        	const requirementElement = requirementElements.eq(i); 
        	
        	if(requirements[i]) { 
            
            	// If requirement is met, replace the text with the appropriate icon 
            	requirementElement.html(`<i class="${iconClass}" style="color:${iconColor};"></i>`); 
        
        	} 
        	else { 
            
            	// If requirement is not met, replace the text with the red asterisk
            	requirementElement.html('<i class="fas fa-times-circle" style="color:red;"></i>'); 
        
        	} 
        	
    	} 
		
	} 
	
	function confirmPassword() { 
		
		const password = $("#password"); 
		const confirmPassword = $("#confirmPassword"); 
		const confirmPasswordIndicator = $("#confirmPasswordIndicator"); 
		
		if(confirmPassword.val() == "") { confirmPasswordIndicator.html(""); } 
		else { 
			
			confirmPasswordIndicator.html(password.val() == confirmPassword.val() ? 
			'<i class="fas fa-check-circle" style="color:green;"></i>' : 
			'<i class="fas fa-times-circle" style="color:red;"></i>'); 
			
		} 
		
	} 
	
}); 