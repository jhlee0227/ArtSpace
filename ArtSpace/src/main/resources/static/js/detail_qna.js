
	$(document).ready(function() {
    // Load questions on page load
    loadQuestions();

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
}