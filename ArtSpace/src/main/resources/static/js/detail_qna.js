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




/* === 견적 내기 부분 === */
// 예약 가능일자 (오늘 기준 일주일 후 부터 가능)
let date = new Date();
let sel_day = 7; //일자 조절
date.setDate(date.getDate() + sel_day );		
document.getElementById('rental_date').min = date.toISOString().substring(0,10);

/*document.getElementById('start_date').onchange = function(){
	document.getElementById('end_date').min = this.value;
    
}*/

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
 //
 
    

let rental_timeList = [];

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
			"rental_date":day,
			"time":time,
			"price":price
		};
		
		let ok = 0;
		// 이미 설정된 값 제외
		rental_timeList.forEach(function(e){
			if(e.rental_date == day && e.time == time){
				alert('이미 추가된 날짜와 시간 입니다.');
				ok = 1;
				return;
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
	console.log(rental_timeList);
	let i = 0;
	$(".contained-date").children().remove();
	rental_timeList.forEach(function(e){
		// 화면에 표시해주기
		let str="<li>- " + (i+1) + " 공연 : " + e.rental_date + " " + e.time + " " + e.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원 ";
		str += "<span class='deleteBtn' onclick='deleteDate("+i+")'>취소</span></li>"	
		$(".contained-date").append(str);
		i++;
	});
	
	total();
}

// == 장비 == //




// 총 견적가 계산
function total(){
	let total_price = 0;
	
	rental_timeList.forEach(function(e){
		total_price += JSON.parse(e.price);
	});

	$('#amount').text(total_price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
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