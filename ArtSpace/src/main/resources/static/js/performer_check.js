// 필수입력 요소 확인
function infoCheck() {
	var group_name = document.getElementById('group_name').value;
	var leader_name = document.getElementById('leader_name').value;
	var leader_phone = document.getElementById('leader_phone').value;
	var member_num = document.getElementById('member_num').value;


	if (group_name !== '' && leader_name !== '' && leader_phone !== '' && member_num !== '') {
		document.getElementById('perfoBtn').disabled = false;
	} else if (group_name == '' || leader_name == '' || leader_phone == '' || member_num == '') {
		document.getElementById('perfoBtn').disabled = true;
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