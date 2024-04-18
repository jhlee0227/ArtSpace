$(document).ready(function() {
	$("#searchButton").click(function() {
		var searchType = $("#searchType").val();
		var searchInput = $("#searchInput").val();
		var data = {
			type: searchType,
			keyword: searchInput
		};

		$.ajax({
			url: "/admin/hall/search",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(data), // 서버에서 반환되는 데이터 타입을 JSON으로 지정
			success: function(searchHallList) {
				var html = ""; // HTML 코드
				for (var i = 0; i < searchHallList.length; i++) {
					html += "<tr>";
					html += "<td class='box'>" + "<input type='checkbox' value='" + searchHallList[i].hall_id + "'class='chkbox' name='check' onclick='checkSelectAll()'>" + "</td>";
					html += "<td><a href='#'>" + searchHallList[i].hall_name + "</a></td>";
					html += "<td>" + searchHallList[i].name + "</td>";
					html += "<td>" + searchHallList[i].address1 + ' ' + searchHallList[i].address2 + "</td>";
					html += "<td>" + (searchHallList[i].visibility === 'Y' ? 'X' : 'O') + "</td>";
					html += "</tr>"; // 행 종료
				}
				$("#searchResult").html(html); // 테이블의 tbody에 HTML 코드 추가
				// 검색 후에는 전체선택 checkbox 체크 해제
				$(".table thead input[name='selectAll']").prop('checked', false);
			},
			error: function(data, status, error) {
			}
		});
	});
});