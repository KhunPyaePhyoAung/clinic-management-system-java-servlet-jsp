document.addEventListener("DOMContentLoaded", () => {
	listenAlertCloseButton();
	listenFormSubmitAndRemoveEmptyParameter();
});

const listenAlertCloseButton = () => {
	let alerts = document.getElementsByClassName("alertMessage");
	for (let i = 0; i < alerts.length; i++) {
		let alert = alerts[i];
		let btnClose = alert.querySelector(".btn-alert-close");
		btnClose.addEventListener("click", () => {
			alert.remove();
		});
		setTimeout(() => {
			alert.remove();
		}, 10000);
	}
};

const listenFormSubmitAndRemoveEmptyParameter = () => {
	let forms = document.querySelectorAll("form");
	forms.forEach(form => {
        form.addEventListener("formdata", event => {
			let formData = event.formData;
            for (let [name, value] of Array.from(formData.entries())) {
	            if (value === '') {
					formData.delete(name);
				}
	         }
        });
    });
};

// function show(id) {
// 	document.getElementById(id).classList.remove('d-none');
// 	document.getElementById(id).classList.add('d-block');
// }

// function hide(id) {
// 	document.getElementById(id).classList.remove('d-block');
// 	document.getElementById(id).classList.add('d-none');
// }

// function hideMedicineForm() {
// 	var form = document.getElementById('medicine-form');
// 	var inputs = form.getElementsByTagName('input');
// 	for (var i = 0; i < inputs.length; i++) {
// 		inputs[i].value = '';
// 	}
// 	form.classList.remove('d-block');
// 	form.classList.add('d-none');
// }

// function hideDiseaseForm() {
// 	var form = document.getElementById('disease-form');
// 	var inputs = form.getElementsByTagName('input');
// 	for (var i = 0; i < inputs.length; i++) {
// 		inputs[i].value = '';
// 	}
// 	form.classList.remove('d-block');
// 	form.classList.add('d-none');
// }
