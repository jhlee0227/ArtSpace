$(document).ready(function() {
	// 초기 상태에서 버튼 비활성화
	updateButtonState("#all_content");
	updateButtonState("#notLeave_content");
	updateButtonState("#leave_content");

	// 전체 회원 탭 클릭 이벤트
	$("#all").on("click", function() {
		updateButtonState("#all_content");
	});

	// 가입 중인 회원 탭 클릭 이벤트
	$("#notLeave").on("click", function() {
		updateButtonState("#notLeave_content");
	});

	// 탈퇴 회원 탭 클릭 이벤트
	$("#leave").on("click", function() {
		updateButtonState("#leave_content");
	});

	// 전체 회원 탭 체크박스 처리
	$("#all_content").on("change", ".chkbox", function() {
		updateButtonState("#all_content");
	});

	// 가입 중인 회원 탭 체크박스 처리
	$("#notLeave_content").on("change", ".chkbox", function() {
		updateButtonState("#notLeave_content");
	});

	// 탈퇴 회원 탭 체크박스 처리
	$("#leave_content").on("change", ".chkbox", function() {
		updateButtonState("#leave_content");
	});

	// 탭 내의 체크박스 상태에 따라 버튼 상태 업데이트 함수
	function updateButtonState(tabId) {
		var checked = $(tabId + " .chkbox:checked").length;
		if (checked > 0) {
			$(tabId + " .leave-btn, " + tabId + " .resign-btn").prop("disabled", false);
		} else {
			$(tabId + " .leave-btn, " + tabId + " .resign-btn").prop("disabled", true);
		}
	}
});