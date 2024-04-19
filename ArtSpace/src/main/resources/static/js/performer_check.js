// 필수입력 요소 확인
function infoCheck() {
	var group_name = document.getElementById('group_name').value;
	var leader_name = document.getElementById('leader_name').value;
	var leader_phone = document.getElementById('leader_phone').value;
	var member_num = document.getElementById('member_num').value;

	var isGroupNameValid = groupNameCheck();  // 그룹 이름 유효성 검사
	var isLeaderNameValid = leaderNameCheck();  // 리더 이름 유효성 검사
	var isLeaderPhoneValid = phoneCheck();  // 핸드폰 번호 유효성 검사
	var isMemberNumValid = memberNumCheck(); // 멤버 수 유효성 검사

	if (group_name !== '' && leader_name !== '' && leader_phone !== '' && member_num !== '' 
	&& isGroupNameValid && isLeaderNameValid && isLeaderPhoneValid && isMemberNumValid) {
		document.getElementById('perfoBtn').disabled = false;
	} else {
		document.getElementById('perfoBtn').disabled = true;
	}
}

// 핸드폰 번호 체크
function phoneCheck() {
	var phone = document.getElementById('leader_phone').value;
	const regExp = /^\d{3}-\d{4}-\d{4}$/;

	if (!phone.match(regExp)) {
		document.getElementById('phoneCheck').innerHTML = "010-xxxx-xxxx 형식으로 입력해주세요."
		document.getElementById('phoneCheck').style.color = 'red';
		return false;
	} else {
		document.getElementById('phoneCheck').innerHTML = "올바른 핸드폰 번호입니다."
		document.getElementById('phoneCheck').style.color = 'green';
		return true;
	}
}

function leaderNameCheck() {
	let leaderName = document.getElementById('leader_name').value;
	// 한글, 영문 2~15자
	const regExp = /^[가-힣a-zA-Z]{2,15}$/;

	if (!leaderName.match(regExp)) {
		document.getElementById('leaderNameCheck').innerHTML = '한글, 영문을 이용해 2~15자를 입력하세요.';
		document.getElementById('leaderNameCheck').style.color = 'red';
		return false;
	} else {
		document.getElementById('leaderNameCheck').innerHTML = '올바른 이름입니다.'
		document.getElementById('leaderNameCheck').style.color = 'green';
		return true;
	}
}

function groupNameCheck() {
	let name = document.getElementById('group_name').value;
	// 한글, 영문, 숫자
	const regExp = /^[가-힣a-zA-Z\d]+$/;

	if (!name.match(regExp)) {
		document.getElementById('groupNameCheck').innerHTML = '한글, 영문, 숫자를 이용해 단체명을 입력하세요.';
		document.getElementById('groupNameCheck').style.color = 'red';
		return false;
	} else {
		document.getElementById('groupNameCheck').innerHTML = '올바른 단체명입니다.'
		document.getElementById('groupNameCheck').style.color = 'green';
		return true;
	}
}

// 멤버 수 유효성 검사 함수
function memberNumCheck() {
	// 멤버 수 입력 필드 값 가져오기
	var member_num = document.getElementById('member_num').value;

	// 멤버 수가 1에서 1000 사이인지 확인
	if (member_num >= 1 && member_num <= 1000) {
		// 유효할 경우 메시지와 색상 설정
		document.getElementById('memberNumCheck').innerHTML = "유효한 멤버 수입니다.";
		document.getElementById('memberNumCheck').style.color = 'green';
		return true;
	} else {
		// 유효하지 않을 경우 메시지와 색상 설정
		document.getElementById('memberNumCheck').innerHTML = "멤버 수는 1에서 1000 사이여야 합니다.";
		document.getElementById('memberNumCheck').style.color = 'red';
		return false;
	}
}

// 수정완료 확인/취소
function confirmUpdate() {
	var result = confirm("공연자 정보를 수정하시겠습니까?");
	if (result) {
		alert("정보 수정이 완료되었습니다.");
		return true;
	} else {
		return false;
	}
}

function confirmInsert() {
	var result = confirm("공연자 정보를 등록하시겠습니까?");
	if (result) {
		alert("정보 등록이 완료되었습니다.");
		return true;
	} else {
		return false;
	}
}