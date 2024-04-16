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

let date = new Date();
let sel_day = 7; //일자 조절
date.setDate(date.getDate() + sel_day );		

document.getElementById('start_date').min = date.toISOString().substring(0,10);
    



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