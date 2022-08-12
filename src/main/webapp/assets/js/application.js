function toggleShowProfilePictureMenu() {
	var menu  = document.getElementById("profile-picture-menu");
	menu.classList.toggle("d-block");
}

window.onclick = function(event) {
	console.log("Hello JavaScript");
	if (!event.target.matches(".dropdown-btn")) {
		var dropdowns = document.getElementsByClassName("dropdown-content");
		
		for (var i = 0; i < dropdowns.length; i++) {
			var currentDropdown = dropdowns[i];
			if (currentDropdown.classList.contains("d-block")) {
				currentDropdown.classList.remove("d-block");
			}
		}
	}
}

function show(id) {
	document.getElementById(id).classList.remove("d-none");
	document.getElementById(id).classList.add("d-block");
}

function hide(id) {
	document.getElementById(id).classList.remove("d-block");
	document.getElementById(id).classList.add("d-none");
}

function hideDoctorSpecialistForm() {
	var form = document.getElementById('specialist-form');
	var inputs = form.getElementsByTagName('input');
	for (var i = 0; i < inputs.length; i++) {
		inputs[i].value = "";
	}
	form.classList.remove("d-block");
	form.classList.add("d-none");
}

function hideMedicineForm() {
	var form = document.getElementById('medicine-form');
	var inputs = form.getElementsByTagName('input');
	for (var i = 0; i < inputs.length; i++) {
		inputs[i].value = "";
	}
	form.classList.remove("d-block");
	form.classList.add("d-none");
}

function hideDiseaseForm() {
	var form = document.getElementById('disease-form');
	var inputs = form.getElementsByTagName('input');
	for (var i = 0; i < inputs.length; i++) {
		inputs[i].value = "";
	}
	form.classList.remove("d-block");
	form.classList.add("d-none");
}


