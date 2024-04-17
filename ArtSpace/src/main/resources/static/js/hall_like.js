function likeHall(hall_id){
	const like = event.target;
	let status = like.dataset.like;												
	event.stopPropagation();
	
	// 반대로 바꿔주고 저장
	if(status == "N"){
		status = "Y";
	} else if(status == "Y"){
		status = "N";					
	}
	
  	$.ajax({
  		url:'/hall/like/' + hall_id,
  		type:'get',
		data:{ "status":status },
  		// 다른 페이지를 처리 후에 결과가 성공일 때
  		success:function(data) {
			if(data == "login"){
				alert("로그인이 필요 합니다.");
			} else {
				like.dataset.like = status;
				like.className = status;					
			}
  		},
	});
}