// 선택된 메뉴에 class="open" 달아주는 함수
document.addEventListener('DOMContentLoaded', function() {

    var currentPath = window.location.pathname;
    var menuItems = document.querySelectorAll('.left-menu a li');

    menuItems.forEach(function(item) {
        item.classList.remove('open');
    });

    menuItems.forEach(function(item) {
        var link = item.parentElement;
        if (link.getAttribute('href') === currentPath) {
            item.classList.add('open');
        }
    });
});