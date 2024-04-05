// 비밀번호 수정 알림
function updatePw() {
	alert("비밀번호가 수정되었습니다.");
}

// 비밀번호 변경 유효성 검사
function pwCheck() {
	let pw = document.getElementById('pw').value;
	let pwChk = document.getElementById('pwChk').value;
	// 영문과 숫자를 포함한 8~16자
	const regExp = /^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z\d]{8,16}$/;

	if (!pw.match(regExp)) {
		document.getElementById('check').innerHTML = '영문과 숫자를 포함한 8~16자를 입력하세요';
		document.getElementById('check').style.color = 'red';
		document.getElementById('updatePwBtn').disabled = true;
	} else {
		if (pw == pwChk) {
			document.getElementById('check').innerHTML = '비밀번호가 일치합니다';
			document.getElementById('check').style.color = 'green';
			document.getElementById('updatePwBtn').disabled = false;
		} else {
			document.getElementById('check').innerHTML = '비밀번호가 일치하지 않습니다';
			document.getElementById('check').style.color = 'red';
			document.getElementById('updatePwBtn').disabled = true;
		}


	}
}

// 닉네임 수정 유효성 검사
function nicknameCheck() {
	let nickname = document.getElementById('nickname').value;
	// 한글, 영문, 숫자 2~6자
	const regExp = /^[가-힣a-zA-Z\d]{2,6}$/;

	if (!nickname.match(regExp)) {
		document.getElementById('nicknameCheck').innerHTML = '한글, 영문, 숫자를 이용해 2~6자를 입력하세요.';
		document.getElementById('nicknameCheck').style.color = 'red';
		document.getElementById('updateNicknameBtn').disabled = true;
	} else {
		document.getElementById('nicknameCheck').innerHTML = '올바른 닉네임입니다.'
		document.getElementById('nicknameCheck').style.color = 'green';
		document.getElementById('updateNicknameBtn').disabled = false;
	}
}
// 닉네임 수정 알림
function updateNickname() {
	alert("닉네임이 수정되었습니다.");
}

// 핸드폰 수정 알림
function updatePhone() {
	alert("핸드폰 번호가 수정되었습니다.");
}

// 핸드폰 유효성 검사
function phoneCheck() {
	var phone = document.getElementById('phone').value;
	const regExp = /^\d{3}-\d{4}-\d{4}$/;

	if (!phone.match(regExp)) {
		document.getElementById('phoneCheck').innerHTML = "010-xxxx-xxxx 형식으로 입력해주세요."
		document.getElementById('phoneCheck').style.color = 'red';
		document.getElementById('updatePhoneBtn').disabled = true;
	} else {
		document.getElementById('phoneCheck').innerHTML = "올바른 핸드폰 번호입니다."
		document.getElementById('phoneCheck').style.color = 'green';
		document.getElementById('updatePhoneBtn').disabled = false;
	}
}

// 회원 탈퇴 확인
function confirmLeave() {
	var result = confirm("정말 회원 탈퇴를 하시겠습니까?");
	if (result) {
		alert("회원 탈퇴가 완료되었습니다.");
		return true;
	} else {
		return false;
	}
}