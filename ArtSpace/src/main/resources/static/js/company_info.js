// 확인 취소
function check() {
	var result = confirm("사업자 정보를 저장하시겠습니까?");
	if (result) {
		alert("사업자 정보가 저장되었습니다.");
		return true;
	} else {
		return false;
	}
}

// 대표명 유효성 검사
// 영어나 한글로 이루어진 2~15자
function nameCheck() {
	let owner_name = document.getElementById('owner_name').value;
	const regExp = /^[가-힣a-zA-Z]{2,15}$/;
	let owner_check = document.getElementById('owner_check');

	if (!owner_name.match(regExp)) {
		owner_check.innerHTML = '영어나 한글로 이루어진 2~15자를 입력해주세요.'
		owner_check.style.color = 'red';
		return false;
	} else {
		owner_check.innerHTML = '올바른 닉네임입니다.'
		owner_check.style.color = 'green';
		return true;
	}
}

// 대표이메일 유효성 검사
function emailCheck() {
	let owner_email = document.getElementById('owner_email').value;
	const regExp = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9]+\.[a-zA-Z]{2,}$/;
	let email_check = document.getElementById('email_check');

	if (!owner_email.match(regExp)) {
		email_check.innerHTML = '올바른 이메일을 입력해주세요.'
		email_check.style.color = 'red';
		return false;
	} else {
		email_check.innerHTML = '올바른 이메일입니다.'
		email_check.style.color = 'green';
		return true;
	}
}

// 핸드폰 유효성 검사
function phoneCheck() {
	let phone = document.getElementById('owner_phone').value;
	const regExp = /^\d{3}-\d{4}-\d{4}$/;
	let phone_check = document.getElementById('phone_check');

	if (!phone.match(regExp)) {
		phone_check.innerHTML = "010-xxxx-xxxx 형식으로 입력해주세요."
		phone_check.style.color = 'red';
		return false;
	} else {
		phone_check.innerHTML = "올바른 핸드폰 번호입니다."
		phone_check.style.color = 'green';
		return true;
	}
}

function activate() {
	let nameValid = nameCheck();
	let emailValid = emailCheck();
	let phoneValid = phoneCheck();

	if (nameValid && emailValid && phoneValid) {
		document.getElementById('btn').disabled = false;
	} else {
		document.getElementById('btn').disabled = true;
	}
}

function checkAllInputs() {
	let nameValid = nameCheck();
	let emailValid = emailCheck();
	let phoneValid = phoneCheck();

	let fileInputs = document.querySelectorAll('input[type=file]');
	let allFile = true;

	for (let i = 0; i < fileInputs.length; i++) {
		if (fileInputs[i].value === '') {
			allFile = false;
			break;
		}
	}

	return nameValid && emailValid && phoneValid && allFile;
}

function activateBtn() {
	let paperBtn = document.getElementById('paperBtn');
	paperBtn.disabled = !checkAllInputs();
}

document.addEventListener('DOMContentLoaded', function() {
	let fileInputs = document.querySelectorAll('input[type=file]');
	let paperBtn = document.getElementById('paperBtn');

	fileInputs.forEach(function(fileInput) {
		fileInput.addEventListener('change', activateBtn);
	});

	document.getElementById('owner_name').addEventListener('input', activateBtn);
	document.getElementById('owner_email').addEventListener('input', activateBtn);
	document.getElementById('owner_phone').addEventListener('input', activateBtn);
});

function submitCheck() {
	var result = confirm("서류를 제출 하시겠습니까?\n(*제출한 후에는 수정이 불가능합니다)");
	if (result) {
		alert("서류 제출이 완료되었습니다.\n(*관리자 승인 후 공연장 등록을 할 수 있습니다)");
		return true;
	} else {
		return false;
	}
}