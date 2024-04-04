// 모달 열기
function openModal() {
	var modal = document.getElementById('myModal');
	modal.style.display = 'block';
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
	   
      const filterMenuInit = () => {
          const filters = document.querySelectorAll('[data-filter-id]');
   
          filters.forEach(filter => {
              const filterBtns = [...filter.querySelectorAll('[data-filter]')].filter(el => el.className === 'filterbtn');
              const filterLists = [...filter.querySelectorAll('[data-filter]')].filter(el => el.className=== 'filterList');
   
              filterBtns.forEach(btn => {
                  btn.addEventListener('click', () => {
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




$("input[name='regionItem']:not('#all')").on('click', function(){
	if($("#all").is(':checked')){
		$("#all").prop("checked", false);			
	}
});

function allSelect(btn){
	console.log("전체 선택");	
	$("input[name='regionItem']:checked").each(function(e){
		$(this).prop("checked", false);
	});	
		
	if(!btn.checked){
		btn.checked = true;		
	}
	
}



	function filterPush(){
		var checkArr = [];     // 배열 초기화    
		
		$("input[name='regionItem']:checked").each(function(i){
	        checkArr.push($(this).val());     // 체크된 것만 값을 뽑아서 배열에 push    		
		});				
		
		$.ajax({
	        url: '/list/check',
	        type: 'get',
	        data: {
            	filterValueArr: checkArr        						
			},    
			success : function(data) {
				$("#location-tag").children().remove();
				$(".box-container").children().remove();
				
				checkArr.forEach(function(e){
					let tagStr = '<div class="tag"><p>'+ e +'</p></div>';	
					$("#location-tag").append(tagStr);				
				});
				
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
					closeModal();	
				});
				
			},
			error: function (data, status, err) {
			},
		});
	}