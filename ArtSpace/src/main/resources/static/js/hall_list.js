// 모달 열기
function openModal(tab_name){
	let modal = document.getElementById('filterModal');
	modal.style.display = 'block';
	
    $('div.tap-menu h2').removeClass('current');
    $('.tab-content').removeClass('current');

    $($("h2[data-tab='"+ tab_name +"']")).addClass('current');
    $("#"+tab_name).addClass('current');
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
	   
// 탭 바꾸기	   
$('div.tap-menu h2').click(function(){
	var tab_id = $(this).attr('data-tab');

    $('div.tap-menu h2').removeClass('current');
    $('.tab-content').removeClass('current');

    $(this).addClass('current');
    $("#"+tab_id).addClass('current');
})	   
	   
	   
// 지역 선택 메뉴들 보이는거	   
const filterMenuInit = () => {
	const filters = document.querySelectorAll('[data-filter-id]');
   
    filters.forEach(filter => {
		const filterBtns = [...filter.querySelectorAll('[data-filter]')].filter(el => el.className === 'filterbtn');
        const filterLists = [...filter.querySelectorAll('[data-filter]')].filter(el => el.className=== 'filterList');
   
        filterBtns.forEach(btn => {
			btn.addEventListener('click', () => {
				allUnChecked();
                const filterType = btn.getAttribute('data-filter');
   
                filterBtns.forEach(btn => btn.classList.remove('active'));
                btn.classList.add('active');
   
                filterLists.forEach(list => {
                	if (filterType === 'all'){
						list.style.display = 'flex';
                        return;
                    }
                          
                    list.style.display = list.getAttribute('data-filter') === filterType ? 'flex' : 'none';
                });
            });
        });
    });
};
filterMenuInit();	

// 선택된거 전체 취소
function allUnChecked(){
	$("input[name='regionItem']:checked").each(function(e){
		$(this).prop("checked", false);
	});		
}

// 전체 선택이 체크 되어있을 때 다른거 누르면 체크 해제
$("input[name='regionItem']:not('.allSelect')").on('click', function(){
	if($(".allSelect").is(':checked')){
		$(".allSelect").prop("checked", false);			
	}
});

// xx 전체 체크 했을 때 해당걸 제외하고 모두 해제
function allSelect(btn){
	$("input[name='regionItem']:checked").each(function(e){
		$(this).prop("checked", false);
	});	
		
	if(!btn.checked){
		btn.checked = true;		
	}
}

// 가격 범위 (최소값)
$(document).on("blur", "#input-min-price", function(){	
	if(!$(this).val()){
		$(this).val(0);
	 }
	if($(this).val() < 0){
		$(this).val(0);
	} else if($(this).val() > $('#input-max-price').val()){
		$(this).val($('#input-max-price').val());
	}
});
	
// 가격 범위 (최대값)	
$(document).on("blur", "#input-max-price", function(){	
	if(!$(this).val()){
		$(this).val(0);
	 } else {
		if($(this).val() < 0){
			$(this).val(0);
	    } 
	    if($(this).val() > 3000) {
			$(this).val(3000);
		}
	    
		if($('#input-min-price').val()){
			// 최대 값이 비어있지 않고 최소 값도 비어있지 않을 때
			if($(this).val() < $('#input-min-price').val()){
				console.log('최대가 더 작음');
				return;
	  		}	
	  	} else {
			console.log("최대 값이 비어있지 않고 최소 값은 비어있을 때");
		}
	 }
	  console.log('정상');
});

// 필터 적용시키기
function filterPush(){
	$("#location-tag").children().remove();
	$(".no-data").remove();
			
	var checkArr = [];     // 배열 초기화    
	
	$("input[name='regionItem']:checked").each(function(i){
		console.log($(this).val());
        checkArr.push($(this).val());     // 체크된 것만 값을 뽑아서 배열에 push    		
	});				
	
	let min_price = document.getElementById('input-min-price').value;
	let max_price = document.getElementById('input-max-price').value;
	let max_people = document.getElementById('input-max-people').value;
	console.log("최소" + min_price);	
	console.log("최대" + max_price);
/*	
	if(min_price != "" && max_price != ""){
		console.log("값 있음");
		if(min_price != "" && max_price == ""){
			alert("범위를 확인해 주십시오.");		
			return;
		}
		if(min_price == ""){
			min_price = 0;

		}	
		console.log("최소" + min_price);	
		console.log("최대" + max_price);
		if(min_price >= max_price){
			alert("최소값은 최대 값보다 작아야 합니다.");
			return;
		}	
		console.log('통과');
	}	*/
	
	return;
	
	var param= {
		local: JSON.stringify(checkArr),
	    "min_price": min_price,
	    "max_price": max_price,
	    "max_people": max_people
	}
	
	$.ajax({
        url: '/list/check',
        type: 'get',
		contentType: 'application/json; charset=UTF-8',
        data: {
			value: param     
		},    
		success : function(data) {
			$(".box-container").children().remove();
			
			checkArr.forEach(function(e){
				let tagStr = '<div class="tag"><p>'+ e +'</p></div>';	
				$("#location-tag").append(tagStr);				
			});
				
			if(data.length < 1){
				let str = '<div class="no-data"><h1>결과가 없습니다.</h1></div>'
				$("#main-con").append(str);
			} else {
				data.forEach(function(hall){
					let str='<div class="box" onclick="location.href=\'/hall/detail/' + hall.hall_id + '\'">';
					str += '<div class="imgbox">';
					str += '<img src="" alt="">';								
					str += '<i id="like-btn" class="fa fa-star fa-4x" aria-hidden="true"></i>\n</div>';
				  	str += '<div class="content">';
					str += '<p class="address-text">'+ hall.address1.substring(0, 6) +'</p>';
					str += '<p class="title-text">'+ hall.hall_name +'</p>';
					str += '<p class="price-text"><span class="point">'+ hall.minPrice + '</span>원~</p>'
					str += '<div class="time-text">';								
					let index = 0;
					hall.hallTimeList.forEach(function(time){
						str += '<p>' + time.type + '</p>';
						if(index+1 < hall.hallTimeList.length){
							str += '<span> | </span>';
						}
						index++;
					});							  
					str += '</div>\n</div>\n</div>'
								
					$(".box-container").append(str);
				});
			}
		},
		error: function (data, status, err) {
		},
	});
	closeModal();	
}