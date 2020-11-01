var check = function() {
	var password = document.getElementById("password_id").value;
	var confirm_password = document.getElementById("confirm_password_id").value;
		
	if (password == confirm_password) {
	    document.getElementById('error').style.color = 'green';
		document.getElementById('error').hidden = 
	    document.getElementById('error').innerHTML = 'matching';
	} else {
	    document.getElementById('error').style.color = 'red';
	    document.getElementById('error').innerHTML = 'not matching';
	}
}