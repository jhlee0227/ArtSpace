
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
