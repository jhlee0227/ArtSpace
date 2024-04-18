// 모달 열기
function openModal(id, reId) {
	var modal = document.getElementById('myModal');
	modal.style.display = 'block';
	hall_id = id;
	reserve_id = reId;
}
// 모달 닫기
function closeModal() {
	var modal = document.getElementById('myModal');
	modal.style.display = 'none';
}

// 별점
$('.star-rating i').click(function() {
	$(this).addClass('selected');
	$(this).prevAll().addClass('selected');
	$(this).nextAll().removeClass('selected');
	var rating = $(this).parent().find('.selected').length;
	$('#ratingInput').val(rating); // 별점 값을 hidden input에 설정
});

var reserve_id = 0;
var hall_id = 0;
// 리뷰 저장하기
$('#saveReview').click(function() {
	var rating = parseInt($('#ratingInput').val(), 10);
	var reviewContent = $('#reviewContentInput').val();

	var data = {
		rating: rating,
		review_content: reviewContent,
		hall_id: hall_id,
		reserve_id: reserve_id
	};

	$.ajax({
		type: 'POST',
		url: '/mypage/saveReview',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function(response) {
			if (response) {
				updateReviewStatus(reserve_id);
				closeModal();
				alert('리뷰가 저장되었습니다.');

			} else {
				alert('리뷰 저장에 실패했습니다.');
			}
		},
		error: function(data, status, error) {
			alert('에러 발생');
		}
	});
});

// 리뷰작성 상태 변경
function updateReviewStatus(reserve_id) {
	
	$.ajax({
		type: 'POST',
		url: '/mypage/updateReviewStatus',
		data: { reserve_id: reserve_id },
		success: function(response) {
			if (response) {
				location.reload();
			} else {

			}
		},
		error: function(data, status, error) {

		}
	})
}