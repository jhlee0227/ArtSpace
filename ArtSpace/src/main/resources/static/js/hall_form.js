
// 이미지 정보 담을 배열
let sel_files = [];

$(document).ready(function(){
  $('#imgfile').on('change', handleImgFileSelect);

  

});

function fileUploadAction() {
  console.log('fileUploadAction');
  $('#imgfile').trigger('click');
}

function handleImgFileSelect(e) {
  // 이미지 정보 초기화
  sel_files = [];
  $('.img-input-wrap').empty();

  let files = e.target.files;
  let filesArr = Array.prototype.slice.call(files);

  let index = 0;
  filesArr.forEach(function(f) {
    if(!f.type.match('image.*')){
      alert('이미지 파일만 업로드 할 수 있습니다.');
      return;
    }
    
    sel_files.push(f);
    
    let reader = new FileReader();
    reader.onload = function(e) {
      var html = "<a href=\"javascript:void(0);\" onclick=\"deleteImageAction("+index+");\" class=\"imgbtn\" id=\"img_id_"+index+"\"><img src=\"" + e.target.result + "\" data-file='"+f.name+"' class='selProductFile' title='Click to remove'></a>";
      $('.img-input-wrap').append(html);
      index++;
    }
    reader.readAsDataURL(f);
  });
}


function deleteImageAction(index) {            
  console.log("index : "+index);
  sel_files.splice(index, 1);

  var img_id = "#img_id_"+index;
  $(img_id).remove();

  console.log(sel_files);
}   

function submitAction() {            
  var data = new FormData();

  for(var i=0, len=sel_files.length; i<len; i++) {
      var name = "image_"+i;
      data.append(name, sel_files[i]);
  }
  data.append("image_count", sel_files.length);
  

  var xhr = new XMLHttpRequest();
  xhr.open("POST","./study01_af.php");
  xhr.onload = function(e) {
      if(this.status == 200) {
          console.log("Result : "+e.currentTarget.responseText);
      }
  }
  xhr.send(data);
}


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

/*======== 유형성 검사 ==========*/

		// 넘어온 값이 빈값인지 체크합니다.
		// !value 하면 생기는 논리적 오류를 제거하기 위해
		// 명시적으로 value == 사용
		// [], {} 도 빈값으로 처리
		
		function checkValue(){
			
			let value = $('#hall_name').val();
			
			if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
				$("#hall_name").focus();
				return;
			}
			
			value = $('#zip_code').val();
			if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
				$("#zip_code").focus();
				return;
			}

			value = $('#area').val();
			if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
				$("#area").focus();
				return;
			}
			value = $('#width').val();
			if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
				$("#width").focus();
				return;
			}
			value = $('#length').val();
			if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
				$("#length").focus();
				return;
			}
			value = $('#height').val();
			if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
				$("#height").focus();
				return;
			}									
			value = $('#maximum').val();
			if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
				$("#maximum").focus();
				return;
			}		
										
			value = $('#hall_description').val();
			if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
				$("#hall_description").focus();
				return;
			}								
			value = $('#warning').val();
			if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
				$("#warning").focus();
				return;
			}			
			document.hall_info_form.submit();
		}

