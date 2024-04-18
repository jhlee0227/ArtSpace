// 모달 열기 + reserve_id전송 후 data 받아오기
function openModal(button) {
	// button 근처 input이 갖고 있는 reserve_id값 받아옴
	var reserve_id = $(button).siblings('.reserveId').val();
	var modal = document.getElementById('myModal');
	modal.style.display = 'block';
	console.log(reserve_id);
	$.ajax({
		type: 'POST',
		url: '/mypage/reserve',
		data: { reserve_id: reserve_id },
		success: function(response) {
			if (response.success) {
				var reservationDetail = response.reservationDetail;
				var reservationEquipments = response.reservationEquipments;
				var reserveDate = response.reserveDate;
				// 모달에 정보를 채워넣는 코드 작성
				$('.modal-con.hall-name').text(reservationDetail.hall_name);
				$('.modal-con.hall-address').text(reservationDetail.address1 + ' ' + reservationDetail.address2);
				$('.modal-con.estimate').text(Number(reservationDetail.estimate).toLocaleString() + '원');

				// 예약 날짜, 시간
				var datesHtml = '';
				reserveDate.forEach(function(date, index) {
					var formattedDate = new Date(date.reserve_date).toISOString().split('T')[0];
					// HTML에 추가
					datesHtml += formattedDate + ' ' + date.reserve_time;
					if (index !== reserveDate.length - 1) {
						datesHtml += '<br><br>';
					}
				})
				$('.modal-con.reservation-time').html(datesHtml)
				// 장비 이름 + 장비 개수
				var equipmentsHtml = '';
				if (reservationEquipments.length > 0) {
					reservationEquipments.forEach(function(equip, index) {
						equipmentsHtml += equip.equip_name + ' : ' + equip.equip_num + '개';
						if (index !== reservationEquipments.length - 1) {
							equipmentsHtml += '<br>' + '<br>';
						}
					});
				} else {
					equipmentsHtml = '없음';
				}
				$('.modal-con.equipments').html(equipmentsHtml);

				$('.modal-con.username').text(reservationDetail.name);
				$('.modal-con.email').text(reservationDetail.email);
				$('.modal-con.phone').text(reservationDetail.phone);
				$('.modal-con.ac').text(reservationDetail.ac);
				$('.modal-con.food').text(reservationDetail.food);
			} else {
				alert('예약 정보를 불러오는 데 실패했습니다.');
			}
		},
		error: function(data, status, error) {
			console.log(response.message);
			alert('예약 정보를 불러오는 데 실패했습니다.');
		}
	});
}

// 모달 닫기
function closeModal() {
	var modal = document.getElementById('myModal');
	modal.style.display = 'none';
}

window.onclick = function(event) {
	var modal = document.getElementById('myModal');
	if (event.target == modal) {
		modal.style.display = "none";
	}
}
