// 탭 1번 검색
$(document).ready(function() {
	$("#searchButton1").click(function() {
		var searchType = $("#searchType1").val();
		var searchInput = $("#searchInput1").val();
		var data = {
			type: searchType,
			keyword: searchInput
		};

		$.ajax({
			url: "/admin/search",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(data), // 서버에서 반환되는 데이터 타입을 JSON으로 지정
			success: function(userList) {
				var html = ""; // HTML 코드를 생성할 변수
				for (var i = 0; i < userList.length; i++) {
					html += "<tr>"; // 새로운 행 시작
					html += "<td><input type='checkbox' value='" + userList[i].user_id + "' class='chkbox' name='check1' onclick='checkSelectAll1()'></td>";
					html += "<td>" + userList[i].email + "</td>";
					html += "<td>" + userList[i].nickname + "</td>";
					html += "<td>";
					if (userList[i].authority === 'SU') {
						html += "일반";
					} else if (userList[i].authority === 'SA') {
						html += "관리자";
					} else if (userList[i].authority === 'SCN') {
						html += "승인되지 않은 법인";
					} else if (userList[i].authority === 'SCY') {
						html += "승인된 법인";
					}
					html += "</td>";
					html += "<td>" + (userList[i].leave_status === 'Y' ? '탈퇴' : '탈퇴X') + "</td>";
					html += "</tr>"; // 행 종료
				}
				$("#searchResults1").html(html); // 테이블의 tbody에 HTML 코드 추가
				// 검색 후에는 전체선택 checkbox 체크 해제
				$(".table thead input[name='selectAll1']").prop('checked', false);
			},
			error: function(data, status, error) {
			}
		});
	});
});

// 탭 2번 검색
$(document).ready(function() {
	$("#searchButton2").click(function() {
		var searchType = $("#searchType2").val();
		var searchInput = $("#searchInput2").val();
		var data = {
			type: searchType,
			keyword: searchInput
		};

		$.ajax({
			url: "/admin/search",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(data), // 서버에서 반환되는 데이터 타입을 JSON으로 지정
			success: function(userList) {
				var html = ""; // HTML 코드를 생성할 변수
				for (var i = 0; i < userList.length; i++) {
					if (userList[i].leave_status === 'N') {
						html += "<tr>"; // 새로운 행 시작
						html += "<td><input type='checkbox' value='" + userList[i].user_id + "' class='chkbox' name='check2' onclick='checkSelectAll2()'></td>";
						html += "<td>" + userList[i].email + "</td>";
						html += "<td>" + userList[i].nickname + "</td>";
						html += "<td>";
						if (userList[i].authority === 'SU') {
							html += "일반";
						} else if (userList[i].authority === 'SA') {
							html += "관리자";
						} else if (userList[i].authority === 'SCN') {
							html += "승인되지 않은 법인";
						} else if (userList[i].authority === 'SCY') {
							html += "승인된 법인";
						}
						html += "</td>";
						html += "<td>" + (userList[i].leave_status === 'Y' ? '탈퇴' : '탈퇴X') + "</td>";
						html += "</tr>"; // 행 종료
					}

				}
				$("#searchResults2").html(html); // 테이블의 tbody에 HTML 코드 추가
				// 검색 후에는 전체선택 checkbox 체크 해제

			},
			error: function(data, status, error) {
			}
		});
	});
});

// 탭 3번 검색
$(document).ready(function() {
	$("#searchButton3").click(function() {
		var searchType = $("#searchType3").val();
		var searchInput = $("#searchInput3").val();
		var data = {
			type: searchType,
			keyword: searchInput
		};

		$.ajax({
			url: "/admin/search",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(data), // 서버에서 반환되는 데이터 타입을 JSON으로 지정
			success: function(userList) {
				var html = ""; // HTML 코드를 생성할 변수
				for (var i = 0; i < userList.length; i++) {
					if (userList[i].leave_status === 'Y') {
						html += "<tr>"; // 새로운 행 시작
						html += "<td><input type='checkbox' value='" + userList[i].user_id + "' class='chkbox' name='check3' onclick='checkSelectAll3()'></td>";
						html += "<td>" + userList[i].email + "</td>";
						html += "<td>" + userList[i].nickname + "</td>";
						html += "<td>";
						if (userList[i].authority === 'SU') {
							html += "일반";
						} else if (userList[i].authority === 'SA') {
							html += "관리자";
						} else if (userList[i].authority === 'SCN') {
							html += "승인되지 않은 법인";
						} else if (userList[i].authority === 'SCY') {
							html += "승인된 법인";
						}
						html += "</td>";
						html += "<td>" + (userList[i].leave_status === 'Y' ? '탈퇴' : '탈퇴X') + "</td>";
						html += "</tr>"; // 행 종료
					}
				}
				$("#searchResults3").html(html); // 테이블의 tbody에 HTML 코드 추가
				// 검색 후에는 전체선택 checkbox 체크 해제
				$(".table thead input[name='selectAll3']").prop('checked', false);
			},
			error: function(data, status, error) {
			}
		});
	});
});