/* ===== 이미지 파일 관련 ===== */

// 이미지 정보 담을 배열
var sel_files = new Array();
let hall_id = document.querySelector("input[name='hall_id']").value;

$(document).ready(function(){
    $('#imgfile').on('change', handleImgFileSelect);
	
  	$.ajax({
  		url:'/hall/form/getFile/' + hall_id,
  		type:'post',
  		success:function(data) {				
		  	console.log(data);				
			data.forEach(function(img){	
				// 파일을 읽기 위한 FileReader 객체 생성
				sel_files.push(img);
			});
			console.log(sel_files);
  		},
  		error:function(request, status, error)
  		{ // 오류가 발생했을 때 호출된다.
  			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
  		},
	
	});
	
});

let imgList = document.querySelectorAll('.selProductFile');
let deleteImg = [];

/*imgList.forEach(function(img){
	console.log(new Blob(img.src, { type: "image/png" }));
	let f = new File(img.src, img.src);
	console.log(f);
});
*/


  
function fileUploadAction() {
  $('#imgfile').trigger('click');
}

function handleImgFileSelect(e) {
    var maxFileCnt = 10;   // 첨부파일 최대 개수
    var attFileCnt = document.querySelectorAll('.imgbtn').length;    // 기존 추가된 첨부파일 개수
    var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
    var curFileCnt = document.querySelector('#imgfile').files.length;  // 현재 선택된 첨부파일 개수
	
    // 첨부파일 개수 확인
    if (curFileCnt > remainFileCnt) {
        alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
    }
    
	let files = e.target.files;
	let filesArr = Array.prototype.slice.call(files);
	console.log(filesArr);
		
	for (var i = 0; i < Math.min(curFileCnt, remainFileCnt); i++) {
		let f = filesArr[i];
		if(validation(f)){
			sel_files.push(f);
	    	let reader = new FileReader();
	    	reader.onload = function(e) {
			var html = "<a href=\"javascript:void(0);\" onclick=\"deleteImageAction("+i+");\" class=\"imgbtn\" id=\"img_id_"+i+"\">";
				html += "<img src=\"" + e.target.result + "\" data-file='"+f.name+"' class='selProductFile' title='Click to remove'></a>";
	      		$('.img-input-wrap').append(html);
	    	}
		    reader.readAsDataURL(f);
		}
	}
    // 초기화
    document.querySelector("input[type=file]").value = "";
    console.log(sel_files);
}


 // 첨부파일 검증 
function validation(obj){
    const fileTypes = ['image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif', 'application/haansofthwp', 'application/x-hwp'];
    if (obj.name.length > 100) {
        alert("파일명이 100자 이상인 파일은 제외되었습니다.");
        return false;
    } else if (obj.size > (50 * 1024 * 1024)) {
        alert("최대 파일 용량인 50MB를 초과한 파일은 제외되었습니다.");
        return false;
    } else if (obj.name.lastIndexOf('.') == -1) {
        alert("확장자가 없는 파일은 제외되었습니다.");
        return false;
    } else if (!fileTypes.includes(obj.type)) {
        alert("첨부가 불가능한 파일은 제외되었습니다.");
        return false;
    } else {
        return true;
    }
}

// 파일 삭제
function deleteImageAction(index) {            
  console.log("index : "+index);
  sel_files.splice(index, 1);

  var img_id = "#img_id_"+index;
  $(img_id).remove();

}   



/* ===== 이미지 관련 End =====*/


// ======== 우편번호 검색 ===========//

function execDaumPostcode(){
	new daum.Postcode( {
	  oncomplete: function( data ) {
	    document.getElementById('zip_code').value = data.zonecode;
	    document.getElementById('address1').value = data.address;
	  }
	} ).open();
}





//============================================/
// 시간대 체크에 따라 가격 input 활성화 여부
$("#morning").click(function(){
	if(this.checked){
		$("#m_price").prop("disabled", false);
	} else {
		$("#m_price").prop("disabled", true);			
	}
});
$("#after").click(function(){
	if(this.checked){
		$("#a_price").prop("disabled", false);
	} else {
		$("#a_price").prop("disabled", true);
	}
});
$("#eve").click(function(){
	if(this.checked){
		$("#e_price").prop("disabled", false);
	} else {
		$("#e_price").prop("disabled", true);				
	}
});
$("#full").click(function(){
	if(this.checked){
		$("#f_price").prop("disabled", false);
	}else {
		$("#f_price").prop("disabled", true);				
	}
});			
/*======== 유형성 검사 ==========*/

		// 넘어온 값이 빈값인지 체크합니다.
		// !value 하면 생기는 논리적 오류를 제거하기 위해
		// 명시적으로 value == 사용
		// [], {} 도 빈값으로 처리
		
function checkValue(){
		
	let value = $('#hall_name').val().trim();
	
	if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
		$("#hall_name").focus();
		alert("모든 값을 입력 해주십시오.");
		return;
	}
	
	value = $('#zip_code').val().trim();
	if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
		$("#zip_code").focus();
		alert("모든 값을 입력 해주십시오.");
		return;
	}

	value = $('#area').val().trim();
	if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
		$("#area").focus();
		alert("모든 값을 입력 해주십시오.");
		return;
	}
	value = $('#width').val().trim();
	if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
		$("#width").focus();
		alert("모든 값을 입력 해주십시오.");
		return;
	}
	value = $('#length').val().trim();
	if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
		$("#length").focus();
		alert("모든 값을 입력 해주십시오.");
		return;
	}
	value = $('#height').val().trim();
	if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
		$("#height").focus();
		alert("모든 값을 입력 해주십시오.");
		return;
	}									
	value = $('#maximum').val().trim();
	if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
		$("#maximum").focus();
		alert("모든 값을 입력 해주십시오.");
		return;
	}
	
	
	// 시간선택
	if(!$("#morning").is(':checked') && !$("#after").is(':checked') && !$("#eve").is(':checked') && !$("#full").is(':checked') ){
		$("#morning").focus();
		alert("시간은 최소 1개는 선택해야 합니다.");
		return;				
	}		
	
	// 선택했는데 값을 안쓰면 빠꾸
	if($("#morning").is(':checked') ){
		value = $('#m_price').val().trim();
		if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
			$("#m_price").focus();
			return;		
		}		
	}
	if($("#after").is(':checked') ){
		value = $('#a_price').val().trim();
		if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
			$("#a_price").focus();
			return;		
		}		
	}
	if($("#eve").is(':checked') ){
		value = $('#e_price').val().trim();
		if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
			$("#e_price").focus();
			return;		
		}		
	}
	if($("#full").is(':checked') ){
		value = $('#f_price').val().trim();
		if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
			$("#f_price").focus();
			return;		
		}		
	}
								
	value = $('#hall_description').val().trim();
	if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
		$("#hall_description").focus();
		alert("모든 값을 입력 해주십시오.");
		return;
	}								
	value = $('#warning').val().trim();
	if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
		$("#warning").focus();
		alert("모든 값을 입력 해주십시오.");
		return;
	}
	
	// 이미지 전송 관련 폼 데이터
    var form = document.querySelector("form[name='hall_info_form']");
    var formData = new FormData(form);
    
    for (var i = 0; i < sel_files.length; i++) {
        formData.append("files", sel_files[i]);
    }
    
	
	
	if(hall_id == ""){
	  	$.ajax({
	  		url:'/hall/form/insert',
	  		type:'post',
	  		data: formData,
	  		processData:false,
	  		contentType: false,
	  		// 다른 페이지를 처리 후에 결과가 성공일 때
	  		success:function(data) {
				document.hall_info_form.action=data;
				document.hall_info_form.submit();
	  		},
	  		error:function(request, status, error)
	  		{ // 오류가 발생했을 때 호출된다.
	  			/*console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);*/
	  		},
  		
  		});		
	}else {
	  	$.ajax({
	  		url:'/hall/form/update/' + hall_id,
	  		type:'post',
	  		data: formData,
	  		processData:false,
	  		contentType: false,
	  		// 다른 페이지를 처리 후에 결과가 성공일 때
	  		success:function(data) {				
				window.location.href = 'http://localhost:1105/hall/form/equipment/'+hall_id;
	  		},
	  		error:function(request, status, error)
	  		{ // 오류가 발생했을 때 호출된다.
	  			/*console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);*/
	  		},
  		
  		});
	}
	


	
}

