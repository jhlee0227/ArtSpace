// 모달 열기
function openModal(){
	let modal = document.getElementById('filterModal');
	modal.style.display = 'block';
}

// 모달 닫기
function closeModal() {
	let modal = document.getElementById('filterModal');
	modal.style.display = 'none';
}

// 모달 닫기
window.onclick = function(event) {
	let modal = document.getElementById('filterModal');
	if (event.target == modal) {
		modal.style.display = "none";
	}
}

/* =================================== */


/* === 견적 내기 부분 === */
    
let estimate = 0;		// 최종 견적가
let rental_timeList = [];	// 대여할 날짜 리스트
var equipList = [];			// 대여할 장비 리스트


// 예약 가능일자 (오늘 기준 일주일 후 부터 가능)
let date = new Date();
let sel_day = 7; //일자 조절
date.setDate(date.getDate() + sel_day );		
//document.getElementById('rental_date').min = date.toISOString().substring(0,10);

// 날짜, 시간 선택 > 가격 표시
document.getElementById('rental_date').onchange = function(){
	let price = document.getElementById('rental_time').value;
	
	if(document.getElementById('rental_time').value != "")	{
		$('#day_price').text(price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	}
}

document.getElementById('rental_time').onchange = function(){
	let price = document.getElementById('rental_time').value;
	
	if(document.getElementById('rental_date').value != "")	{
		$('#day_price').text(price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	}
}
/* ========== 날짜 끝 =========== */ 
    
    

// 날짜 시간 담기 버튼 누르면
function date_setting(){
	let rental_date = document.getElementById('rental_date');
	let rental_time = document.getElementById('rental_time');
	
	if(rental_date.value == "" || rental_time.value == ""){
		return;
	} else {
		// 제대로 된 값일때
		let day = document.getElementById('rental_date').value;
		let time = $("#rental_time option:checked").text();
		let price = document.getElementById('rental_time').value;
		
		let temp = {
			"reserve_date":day,
			"reserve_time":time,
			"price":price
		};
		
		let ok = 0;
		// 이미 설정된 값 제외
		rental_timeList.forEach(function(e){
			if(e.reserve_date == day && e.reserve_time == time){
				$('#datechk').html("이미 추가된 날짜와 시간 입니다.").css('color', 'red');
				ok = 1;
			} else if(time == "하루" && e.reserve_date == day){
				$('#datechk').html("중복 된 날짜는 추가 할 수 없습니다.").css('color', 'red');
				ok = 1;
			} else {
				$('#datechk').html("");				
			}	
		});
		
		if(ok == 0){
			// 배열로 담아줌
			rental_timeList.push(temp);
			showdate();
		}		
	}
}

// 담은 날짜 취소하면
function deleteDate(index){
	rental_timeList.splice(index, 1);
	showdate();
}

// 담겨진 날짜를 유저 화면에 보여줌
function showdate(){
	let i = 0;
	$(".contained-date").children().remove();
	rental_timeList.forEach(function(e){
		// 화면에 표시해주기
		let str="<li>- " + (i+1) + " 공연 : " + e.reserve_date + " " + e.reserve_time + " " + e.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원 ";
		str += "<span class='deleteBtn' onclick='deleteDate("+i+")'>취소</span></li>"	
		$(".contained-date").append(str);
		i++;
	});
	
	total();
}

// == 장비 == //

// 장비 체크 상태 변화 있을 때 재계산
$("input[name='equip']").change(function(){
	equipList = [];     // 배열 초기화
		
	// 체크 된 input 장비만
	$("input[name='equip']:checked").each(function(i){
		let strArr = $(this).val().split("+");		
		strArr.push($("select[name='equip_num_" + strArr[0] +"']").val());
		
		if(strArr[4] < 1){
			return;
		}
		
		let equip = {
			"equip_type":strArr[1],
			"equip_name":strArr[2],
			"equip_price":strArr[3],
			"equip_num":strArr[4]
		};			
		equipList.push(equip);     // 체크된 것만 값을 뽑아서 배열에 push  
	});
	
	// 견적가 계산하기
	total();
});


// 마이크 개수 선택 달라져도 재계산
$(".equip_num").change(function(){
	equipList = [];     // 배열 초기화
		
	// 체크 된 input 장비만
	$("input[name='equip']:checked").each(function(i){
		let strArr = $(this).val().split("+");		
		strArr.push($("select[name='equip_num_" + strArr[0] +"']").val());
		
		if(strArr[4] < 1){
			return;
		}
		
		let equip = {
			"equip_type":strArr[1],
			"equip_name":strArr[2],
			"equip_price":strArr[3],
			"equip_num":strArr[4]
		};			
		equipList.push(equip);     // 체크된 것만 값을 뽑아서 배열에 push  
	});
	
	// 견적가 계산하기
	total();
});


// 총 견적가 계산
function total(){
	estimate = 0;
	
	// 날짜값 계산
	rental_timeList.forEach(function(e){
		estimate += JSON.parse(e.price);
	});
	
	// 장비값 계산
	equipList.forEach(function(e){
		if(e.equip_type == 'mic'){
			estimate += (JSON.parse(e.equip_price) * JSON.parse(e.equip_num) * rental_timeList.length);		
		} else {
			estimate += (JSON.parse(e.equip_price) * rental_timeList.length);					
		}
	});

	$('#amount').text(estimate.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
}

// 예약확인 버튼 눌러서 제출!
function reservation_submit(){
	
	if(rental_timeList.length < 1){
		alert("예약 날짜를 1개 이상 선택해야 합니다.");
		return;
	}
	
	// 기타 목록 가져오기
	let food = $('input[name=food]:checked').val();
	let ac = $('input[name=ac]:checked').val();
	let hall_id =  $('input[name=hall_id]').val();

	var reservationDTO = {
		hall_id: hall_id,
		estimate: estimate,
		food: food,
	    ac: ac
	}

	var jsonList = {
   		"reservation":JSON.stringify(reservationDTO),
   		"timeList":JSON.stringify(rental_timeList),
   		"equipList":JSON.stringify(equipList)
	}
	
	$.ajax({
        url: '/reservation/insert',
        method: 'post',
        data:jsonList,
		success : function(data) {
			if(data == "login"){
				alert("로그인이 필요 합니다.");
				location.href="http://localhost:1105/login";
			} else if(data == "success") {
				alert("예약이 완료되었습니다.");
				location.href="http://localhost:1105/mypage/reserve";
			} else {
				alert(data);
			}
		},
  		beforeSend: function () {
			var width = 0;
			var height = 0;
			var left = 0;
			var top = 0;
            width = 50;
			height = 50;
            top = ( $(window).height() - height ) / 2 + $(window).scrollTop();
            left = ( $(window).width() - width ) / 2 + $(window).scrollLeft();
 
            if($("#div_ajax_load_image").length != 0) {
				$("#div_ajax_load_image").css({
					"top": top+"px",
					"left": left+"px"
				});                     
				$("#div_ajax_load_image").show();
			} else { 
				$('body').append('<div id="div_ajax_load_image" style="position:absolute; top:' + top + 'px; left:' + left + 'px; width:' + width + 'px; height:' + height + 'px; z-index:9999; background:#f0f0f0; filter:alpha(opacity=50); opacity:alpha*0.5; margin:auto; padding:0; "><img src="/img/ajax_loader.gif" style="width:50px; height:50px;"></div>');
			}
       	}, 
		complete: function () {
			$("#div_ajax_load_image").hide();
		},
  		error:function(request, status, error) { // 오류가 발생했을 때 호출된다.
  		},
	});
}

/* === 견적 내기 부분 END ===*/





/*$(document).ready(function() {
    // Load questions on page load
    //loadQuestions();

    // Submit question form
    $('#questionForm').submit(function(event) {
        event.preventDefault();
        var content = $('#questionContent').val();
        saveQuestion(content);
    });
});

function loadQuestions() {
    $.ajax({
        url: '/questions',
        type: 'GET',
        success: function(data) {
            // Display questions in the DOM
            // Handle pagination if needed
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function saveQuestion(content) {
    $.ajax({
        url: '/questions',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ content: content }),
        success: function(data) {
            // Reload questions after successful submission
            loadQuestions();
            $('#questionContent').val(''); // Clear textarea
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}*/