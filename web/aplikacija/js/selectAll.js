function checkAll(cb, name) {
   var checkBoxes = document.getElementsByName(String(name));
   for (var i = 0; i < checkBoxes.length; i++) {
      checkBoxes[i].checked = cb.checked;
   }
}