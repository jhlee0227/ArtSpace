let plusBtn = document.querySelector("#plus-btn");
let index = (document.querySelectorAll(".item-row").length) - 1;

plusBtn.addEventListener("click", function () {
  index++;
  const itemRow = document.querySelector(".item-row"); //복사할 노드
  const newNode = itemRow.cloneNode(true); // 노드 복사


  newNode.lastElementChild.lastElementChild.value = null;
  newNode.lastElementChild.lastElementChild.name = "equiList[" + index + "].equip_price";

  newNode.lastElementChild.lastElementChild.previousElementSibling.value = null;
  newNode.lastElementChild.lastElementChild.previousElementSibling.name = "equiList[" + index + "].equip_num";

  newNode.lastElementChild.lastElementChild.previousElementSibling.previousElementSibling.value = null;
  newNode.lastElementChild.lastElementChild.previousElementSibling.previousElementSibling.name = "equiList[" + index + "].equip_name";

  newNode.lastElementChild.lastElementChild.previousElementSibling.previousElementSibling.previousElementSibling.value = "none";
  newNode.lastElementChild.lastElementChild.previousElementSibling.previousElementSibling.previousElementSibling.name = "equiList[" + index + "].equip_type";

  document.querySelector(".item-container").appendChild(newNode);
});

function removeRow(e) {
index--;
if(index < 0) index = 0;

  if(e.parentNode.parentNode.childElementCount > 1){
    e.parentNode.remove();
	  let rows = document.querySelectorAll(".input-con");

	  for(let i=0; i<[...rows].length; i++){
  	rows[i].firstElementChild.name = "equiList["+ i +"].equip_type";	
    	rows[i].firstElementChild.nextElementSibling.name = "equiList["+ i +"].equip_name";
    rows[i].firstElementChild.nextElementSibling.nextElementSibling.name = "equiList["+ i +"].equip_num";
    rows[i].firstElementChild.nextElementSibling.nextElementSibling.nextElementSibling.name = "equiList["+ i +"].equip_price";	    
  }
  } else {
    e.nextElementSibling.lastElementChild.value = null;
    e.nextElementSibling.lastElementChild.name = "equiList[0].equip_price";

    e.nextElementSibling.lastElementChild.previousElementSibling.value = null;
    e.nextElementSibling.lastElementChild.previousElementSibling.name = "equiList[0].equip_num";

    e.nextElementSibling.lastElementChild.previousElementSibling.previousElementSibling.value = null;
    e.nextElementSibling.lastElementChild.previousElementSibling.previousElementSibling.name = "equiList[0].equip_name";
    
    e.nextElementSibling.lastElementChild.previousElementSibling.previousElementSibling.previousElementSibling.value = "none";
    e.nextElementSibling.lastElementChild.previousElementSibling.previousElementSibling.previousElementSibling.name = "equiList[0].equip_type";            
  }
}
