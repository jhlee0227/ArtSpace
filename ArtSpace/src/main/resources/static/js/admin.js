/* 일반 */
function checkSelectAll() {
    // 전체 체크박스
    const checkboxes = document.querySelectorAll('input[name="check"]');
    // 선택된 체크박스
    const checked = document.querySelectorAll('input[name="check"]:checked');
    // select all 체크박스
    const selectAll = document.querySelector('input[name="selectAll"]');

    if (checkboxes.length === checked.length) {
        selectAll.checked = true;
    } else {
        selectAll.checked = false;
    }
}

function selectAll(selectAll) {
    const checkboxes = document.getElementsByName('check');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked
    })
}

/* 탭 1번 */
function checkSelectAll1() {
    // 전체 체크박스
    const checkboxes1 = document.querySelectorAll('input[name="check1"]');
    // 선택된 체크박스
    const checked1 = document.querySelectorAll('input[name="check1"]:checked');
    // select all 체크박스
    const selectAll1 = document.querySelector('input[name="selectAll1"]');

    if (checkboxes1.length === checked1.length) {
        selectAll1.checked = true;
    } else {
        selectAll1.checked = false;
    }
}

function selectAll1(selectAll1) {
    const checkboxes1 = document.getElementsByName('check1');

    checkboxes1.forEach((checkbox) => {
        checkbox.checked = selectAll1.checked
    })
}

/* 탭 2번 */
function checkSelectAll2() {
    // 전체 체크박스
    const checkboxes2 = document.querySelectorAll('input[name="check2"]');
    // 선택된 체크박스
    const checked2 = document.querySelectorAll('input[name="check2"]:checked');
    // select all 체크박스
    const selectAll2 = document.querySelector('input[name="selectAll2"]');

    if (checkboxes2.length === checked2.length) {
        selectAll2.checked = true;
    } else {
        selectAll2.checked = false;
    }
}

function selectAll2(selectAll2) {
    const checkboxes2 = document.getElementsByName('check2');

    checkboxes2.forEach((checkbox) => {
        checkbox.checked = selectAll2.checked
    })
}