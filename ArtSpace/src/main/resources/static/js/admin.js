/* 일반 */
function checkSelectAll() {
	// 전체 체크박스
	const checkboxes = document.querySelectorAll('input[name="check"]');
	// 선택된 체크박스
	const checked = document.querySelectorAll('input[name="check"]:checked');
	// select all 체크박스
	const selectAll = document.querySelector('input[name="selectAll"]');

	if (checkboxes.length === checked.length) {
		selectAll.checked = true;
	} else {
		selectAll.checked = false;
	}
}

document.querySelectorAll('input[name="check"]').forEach(function(checkbox) {
    checkbox.addEventListener('click', function() {
        checkSelectAll(); // 개별 체크박스를 클릭할 때마다 전체 체크박스 상태 업데이트
    });
});

// 전체선택 체크박스 체크하면 개별 체크박스 전부 체크
document.querySelectorAll('input[name="selectAll"]').forEach(function(checkbox) {
	checkbox.addEventListener('click', function() {
		const checkboxes = document.getElementsByName('check');
		checkboxes.forEach((checkbox) => {
			checkbox.checked = this.checked;
		});
		checkSelectAll();
	});
});

/* 탭 1번 */
function checkSelectAll1() {
	// 전체 체크박스
	const checkboxes1 = document.querySelectorAll('input[name="check1"]');
	// 선택된 체크박스
	const checked1 = document.querySelectorAll('input[name="check1"]:checked');
	// select all 체크박스
	const selectAll1 = document.querySelector('input[name="selectAll1"]');

	if (checkboxes1.length === checked1.length) {
		selectAll1.checked = true;
	} else {
		selectAll1.checked = false;
	}
}

document.querySelectorAll('input[name="check1"]').forEach(function(checkbox) {
    checkbox.addEventListener('click', function() {
        checkSelectAll1(); // 개별 체크박스를 클릭할 때마다 전체 체크박스 상태 업데이트
    });
});

// 전체선택 체크박스 체크하면 개별 체크박스 전부 체크
document.querySelectorAll('input[name="selectAll1"]').forEach(function(checkbox) {
	checkbox.addEventListener('click', function() {
		const checkboxes1 = document.getElementsByName('check1');
		checkboxes1.forEach((checkbox) => {
			checkbox.checked = this.checked;
		});
		checkSelectAll1();
	});
});

/* 탭 2번 */
function checkSelectAll2() {
	// 전체 체크박스
	const checkboxes2 = document.querySelectorAll('input[name="check2"]');
	// 선택된 체크박스
	const checked2 = document.querySelectorAll('input[name="check2"]:checked');
	// select all 체크박스
	const selectAll2 = document.querySelector('input[name="selectAll2"]');

	if (checkboxes2.length === checked2.length) {
		selectAll2.checked = true;
	} else {
		selectAll2.checked = false;
	}
}

document.querySelectorAll('input[name="check2"]').forEach(function(checkbox) {
    checkbox.addEventListener('click', function() {
        checkSelectAll2(); // 개별 체크박스를 클릭할 때마다 전체 체크박스 상태 업데이트
    });
});

// 전체선택 체크박스 체크하면 개별 체크박스 전부 체크
document.querySelectorAll('input[name="selectAll2"]').forEach(function(checkbox) {
	checkbox.addEventListener('click', function() {
		const checkboxes2 = document.getElementsByName('check2');
		checkboxes2.forEach((checkbox) => {
			checkbox.checked = this.checked;
		});
		checkSelectAll2();
	});
});


/* 탭 3번 */
function checkSelectAll3() {
	// 전체 체크박스
	const checkboxes3 = document.querySelectorAll('input[name="check3"]');
	// 선택된 체크박스
	const checked3 = document.querySelectorAll('input[name="check3"]:checked');
	// select all 체크박스
	const selectAll3 = document.querySelector('input[name="selectAll3"]');

	if (checkboxes3.length === checked3.length) {
		selectAll3.checked = true;
	} else {
		selectAll3.checked = false;
	}
}

document.querySelectorAll('input[name="check3"]').forEach(function(checkbox) {
    checkbox.addEventListener('click', function() {
        checkSelectAll3(); // 개별 체크박스를 클릭할 때마다 전체 체크박스 상태 업데이트
    });
});

// 전체선택 체크박스 체크하면 개별 체크박스 전부 체크
document.querySelectorAll('input[name="selectAll3"]').forEach(function(checkbox) {
	checkbox.addEventListener('click', function() {
		const checkboxes3 = document.getElementsByName('check3');
		checkboxes3.forEach((checkbox) => {
			checkbox.checked = this.checked;
		});
		checkSelectAll3();
	});
});

// tab을 클릭했을 때 다른 tab의 체크박스 체크 해제
function checkboxClear() {
	// tab들의 ID값
	var tabIds = ['all','notLeave','leave'];

	var selectTabId = document.querySelector('input[name="tab_item"]:checked').id;

	tabIds.forEach(function(tabId) {
		if (tabId !== selectTabId) {
			var checkboxes = document.querySelectorAll('#' + tabId + '_content input[type="checkbox"]');
			checkboxes.forEach(function(checkbox) {
				checkbox.checked = false;
			})
		}
	})
}
// 체크박스 해제 적용
var tabItems = document.querySelectorAll('input[name="tab_item"]');
tabItems.forEach(function(tabItem) {
	tabItem.addEventListener('change', checkboxClear);
})


function checkLeave() {
	var result = confirm("선택된 회원을 탈퇴시키시겠습니까?");
	if (result) {
		alert("회원 탈퇴가 완료되었습니다.");
		return true;
	} else {
		return false;
	}
}

function checkResign() {
	var result = confirm("선택된 회원을 재가입시키시겠습니까?");
	if (result) {
		alert("회원 재가입이 완료되었습니다.")
		return true;
	} else {
		return false;
	}
}