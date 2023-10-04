// 	main JS 

function toggleForm(e) {
	
	const PageComponents = document.querySelectorAll(".pageComponent"); 			// PageComponents is every component of the page excluding modals. (i.e. header, main, footer, etc. ) 
	const FormToggler = e.target; 													// `FormToggler`s are CTAs. 
	const ValidNames = [new RegExp("formOpen", "i"), new RegExp("formClose", "i")]; // `formOpen` for `"Login"` & `"Sign-Up"` CTAs, `formClose` for modal cancel and bootstrap close btns. 
	
	if(ValidNames[0].test(FormToggler.className)) {
		
		const ID = FormToggler.classList[3]; 										/**/ console.log(FormToggler.classList[3]); /**/ 
		const CorrespondingForm = document.querySelector("#" + ID); 				// `CorrespondingForm` is either the signup or login modal 
		
		[...PageComponents].forEach(i => {
			
			i.style.display = "none"; 
			
		}); 
		CorrespondingForm.style.display = "block"; 
		
	} 
	else if(ValidNames[1].test(FormToggler.className)) {
		
		const ID = FormToggler.classList[2]; 
		const CorrespondingForm = document.querySelector("#" + ID); 
		
		[...PageComponents].forEach(i => { 
			
			i.style.display = "block"; 
			
		}); 
		CorrespondingForm.style.display = "none"; 
		
	}
	
} 

const Togglers = [...document.querySelectorAll(".formOpen"), ...document.querySelectorAll(".formClose")]; 
Togglers.forEach(i => i.addEventListener("click", toggleForm)); 

const loginForm_signupCTA = document.querySelector("#loginForm_signupCTA"); 
loginForm_signupCTA.addEventListener("click", function() {
	
	document.querySelector("#loginForm").style.display = "none"; 
	
}); 