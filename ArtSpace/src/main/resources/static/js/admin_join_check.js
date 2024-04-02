//아이디 유효성검사
$(document).ready(function() {
	
	let patternEmail = /([^a-zA-Z0-9])/i;
	let a = [0,0,0];

	
	$("#email").on("propertychange change keyup paste input", function(){
		let	engLow = $.trim($("#email").val()).search(/[a-z]/ig);
		
		if(patternEmail.test($.trim($("#email").val()))){
			$('#emailchk').html("아이디를23 입력해주십시오.").css('color','red');
			a[0] = 0;
		} else {
			var email = $('#email').val();
			$.ajax({
				url: '/yjh-console/emailCheck',
				method: 'post',
				data : {
						"email" : email
				},
				dataType : 'text',
				
				success : function(data) {
					if (data == "redundancy") {
						$('#emailchk').html("중복된 아이디입니다").css('color', 'red');
						a[0] = 0;
					} else if(engLow < 0 || $('#email').val().length < 3 || $('#email').val().length > 10 ){
						$('#emailchk').html("아이디는 영어어를 포함한 3~10 자리 사용해야 입니다.").css('color', 'red');
						a[0] = 0;
					} else if (data == "noredundancy") {
						$('#emailchk').html("사용가능한 아이디입니다").css('color', 'green');
						a[0] = 1;
					;
					} 
				},
			});
		}
		;
		isSubmit();
	});
	
	
	// 비밀번호
	$("#password").on("propertychange change keyup paste input", function(){
		let pw = $('#password').val();
		let num = pw.search(/[0-9]/g);
		let	engLow = pw.search(/[a-z]/ig);

		//빈값		
		if(!$('#password').val()){
			$('#pwchk').html("영문과 숫자를 포함한 6~16자").css('color','red');		
			a[1] = 0;
		// 자릿수 부족
		} else if($('#password').val().length < 6 || $('#password').val().length > 16){
			$('#pwchk').html("영문과 숫자를 포함한 6~16자").css('color','red');		
			a[1] = 0;
		} else {
			// 조건 부족
			if(num < 0 || engLow < 0 ){
				$('#pwchk').html("영문과 숫자를 포함한 6~16자").css('color','red');	
				a[1] = 0;
			} else {
				// 성공
				$('#pwchk').html("사용가능한 아이디입니다").css('color', 'green');
				a[1] = 1;
			}
		}
		isSubmit();
	});
	
		// 비밀번호 확인
	$("#password2").on("propertychange change keyup paste input", function(){
		let pw = $('#password').val();
		//빈값		
		if(!$('#password2').val()){
			$('#pw2chk').html("비밀번호가 일치하지 않습니다.").css('color','red');		
			a[2] = 0;
		} else {
			// 조건 부족
			if(pw != $('#password2').val()){
				$('#pw2chk').html("비밀번호가 일치하지 않습니다.").css('color','red');		
				a[2] = 0;
			} else {
				// 성공
				$('#pw2chk').html("비밀번호가 일치 합니다.").css('color', 'green');
				a[2] = 1;
			}
		}
		isSubmit();
	});



	
// 유효성 검사를 모두 통과하면 해당 함수 실행할 것
function isSubmit(){
	let ok = 0;
	
	a.forEach(function(n){
		if(n==0){
			ok = 1;
			return;
		} 
	});
	

	if(ok == 0 ){
		$('.btn1').attr("disabled", false);		
	} else {
		$('.btn1').attr("disabled", true);				
	}

}	
	
	
	
	
})