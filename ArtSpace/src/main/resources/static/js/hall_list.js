// 모달 열기
function openModal(str){
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
	   
$('div.tap-menu h2').click(function(){
	var tab_id = $(this).attr('data-tab');

    $('div.tap-menu h2').removeClass('current');
    $('.tab-content').removeClass('current');
    console.log(tab_id);
	

    $(this).addClass('current');
    $("#"+tab_id).addClass('current');
})	   
	   
	   
	   
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

// 필터 적용시키기
function filterPush(){
	var checkArr = [];     // 배열 초기화    
	
	$("input[name='regionItem']:checked").each(function(i){
		console.log($(this).val());
        checkArr.push($(this).val());     // 체크된 것만 값을 뽑아서 배열에 push    		
	});				
		
	$.ajax({
        url: '/list/check',
        type: 'get',
        data: {
			filterValueArr: checkArr        						
		},    
		success : function(data) {
			closeModal();	
			$("#location-tag").children().remove();
			$(".box-container").children().remove();
			$(".no-data").remove();
			
			checkArr.forEach(function(e){
				let tagStr = '<div class="tag"><p>'+ e +'</p></div>';	
				$("#location-tag").append(tagStr);				
			});
				
			if(data.length < 1){
				let str = '<div class="no-data"><h1>결과가 없습니다.</h1></div>'
				$(".con").append(str);
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
}