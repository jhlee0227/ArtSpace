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
	let btns = document.querySelectorAll("input[name='regionItem']:checked");
	btns.forEach(function(e){
		if(e != btn){		
			e.checked = false;
		}
	});	
}

// 필터 적용시키기
function filterPush(){
	$(".tags").children().remove();
	$(".no-data").remove();
			
	var checkArr = [];     // 배열 초기화
	
	$("input[name='regionItem']:checked").each(function(i){
        checkArr.push($(this).val());     // 체크된 것만 값을 뽑아서 배열에 push    		
	});
	
	var min_price = document.getElementById('input-min-price').value;
	var max_price = document.getElementById('input-max-price').value;
	var max_people = document.getElementById('input-max-people').value;
	var sortStr = $("#sort-list option:selected").val();

	if(checkArr == ""){
		checkArr = null;
	}
	
/*	if(min_price > max_price){
		alert('최소값이 최대값보다 클 수 없습니다.');
		return;
	}*/
	
	const url = new URL(window.location.href);
	const urlParams = url.searchParams.get('content');
	
	
	//JSON.stringify(checkArr),
	var hallFilterDTO = {
		localList: checkArr,
		content: urlParams,
	    minPrice: min_price*10000,
	    maxPrice: max_price*10000,
	    maxPeople: max_people,
	    sort:sortStr
	}
	
	$.ajax({
        url: '/list/check',
        method: 'post',
		contentType: "application/json",
        data: JSON.stringify(hallFilterDTO),    
		success : function(data) {
			$(".box-container").children().remove();
			
			if(checkArr != null){
				checkArr.forEach(function(e){
					let tagStr = '<div class="tag"><p>'+ e +'</p></div>';	
					$("#location-tag").append(tagStr);				
				});	
			}
			
			if(max_price > 0){
				let tagStr = '<div class="tag"><p>'+ min_price + '~' + max_price +' 만원</p></div>';	
				$("#price-tag").append(tagStr);				
			}
			
			if(max_people > 0){
				let tagStr = '<div class="tag"><p>'+ max_people +'명 이상</p></div>';	
				$("#people-tag").append(tagStr);				
			}
			
				
			if(data.length < 1){
				let str = '<div class="no-data"><h1>결과가 없습니다.</h1></div>'
				$("#main-con").append(str);
			} else {
				data.forEach(function(hall){
					let str='<div class="box" onclick="location.href=\'/hall/detail/' + hall.hall_id + '\'">';
					str += '<div class="imgbox">';
					if(hall.mainImage != null){
						str += '<img src="'+ hall.mainImage.path +'" data-file="'+ hall.mainImage.org_file_name +'"alt="이미지">';
					}				    						
					str += '</div>';
					
					str += '<div class="likeBtn" id="likeBtn" onclick="likeHall('+ hall.hall_id +');">';
					str += '<img data-like="'+ hall.likeStatus +'"src="img/heart_black.png" class="'+ hall.likeStatus +'"></div>'
				  	str += '<div class="content">';
				  	str += '<div class="content-top">';
					str += '<p class="address-text">'+ hall.address1.substring(0, 6) +'</p>';
				  	str += '<div class="rating-list"><i class="fa fa-star" aria-hidden="true"></i>'
					str += '<p>('+ hall.rating +')</p>'
					str += '<i id="like_icon" class="fa-regular fa-heart" aria-hidden="true"></i>'
					str += '<p>('+hall.likeNum+')</p>\n</div>\n</div>'	
					str += '<p class="title-text">'+ hall.hall_name +'</p>';
					str += '<p class="price-text"><span class="point">'+ hall.minPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</span>원~</p>'
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


// 리스트 정렬(최신순, 인기순...)
function sortList(){
	filterPush();
}