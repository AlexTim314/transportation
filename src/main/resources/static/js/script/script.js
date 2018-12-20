menu_open=function() {
 var str = document.getElementById("form-add");
 str.style.display="block";
 var str1 = document.getElementById("btn-add-edit");
 str1.innerHTML="Добавить";
}
menu_close=function() {
 var str = document.getElementById("form-add");
 str.style.display="none";
}
more_open=function() {
 var str = document.getElementById("more-id");  
 str.style.display="block";
}
more_close=function() {
 var str = document.getElementById("more-id");
 str.style.display="none";
}

edit_open=function() {
 var str = document.getElementById("form-add");
 str.style.display="block";
 var str1 = document.getElementById("btn-add-edit");
 str1.innerHTML="Изменить";
}

formRecord_close=function(){
  var str = document.getElementById("formRecord-id");
  str.style.display="none"; 
}
formRecord_open=function(){
  var str = document.getElementById("formRecord-id");
  str.style.display="block";

}
formLogin_close=function(){
  var str = document.getElementById("formLogin");
  str.style.display="none";
}
formLogin_open=function(){
  var str = document.getElementById("formLogin");
  str.style.display="block";
}
open_tab1=function(){
  var str = document.getElementById("tab-list1");
  var str_btn=document.getElementById("tab-btn1");
  str.style.display="block";
  str_btn.classList.add("active");
/* var str_h=document.getElementById("tbl-h");
  str_h.style.width = "95%";*/

  var str = document.getElementById("tab-list2");
  var str_btn=document.getElementById("tab-btn2");
  str_btn.classList.remove("active");
  str.style.display="none";

  /******filter****/
  var fltr1 = document.getElementById("f-empls");
  var fltr2 = document.getElementById("f-trnsp");
  var fltr3 = document.getElementById("f-sts");
  fltr1.style.display = 'block';
  fltr2.style.display = 'none';
  fltr3.style.display = 'none';
}
open_tab2=function(){
  var str = document.getElementById("tab-list2");
  var str_btn=document.getElementById("tab-btn2");
  str.style.display="block";
  str_btn.classList.add("active");
 /* var str_h=document.getElementById("tbl-h");
  str_h.style.width = "60%";*/
 

  var str = document.getElementById("tab-list1");
  var str_btn=document.getElementById("tab-btn1");
  str_btn.classList.remove("active");
  str.style.display="none";
  /******filter****/
  var fltr1 = document.getElementById("f-empls");
  var fltr2 = document.getElementById("f-trnsp");
  var fltr3 = document.getElementById("f-sts");
  fltr1.style.display = 'none';
  fltr2.style.display = 'block';
  fltr3.style.display = 'block';
}

open_sidenav=function(){
    var str=document.getElementById("sidenav-id");
    var str1=document.getElementById("claim-create");
  //  var str2=document.getElementById("sb-v-id");
    str.style.width = '17%';
    str1.style.width = '82%';
    
    setTimeout('str2.style.display="none"', 300);

}
close_sidenav=function(){
    var str=document.getElementById("sidenav-id");
    var str1=document.getElementById("claim-create");
    var str2=document.getElementById("sb-v-id");
    str.style.width = '0';
    str1.style.width = '98%';
    setTimeout('str2.style.display="block"', 300);

}





//var tab = document.getElementById("tbl1");
//var elements = tab.getElementsByTagName('tr');
//var i;
//
//for (i = 1; i < elements.length; i++) {
//  elements[i].addEventListener("click", function() {
//  
//    var panel = this.nextElementSibling;
//    if (panel.classList.contains("hiddenRow")){
//       panel.classList.toggle("collapseRow");
//      this.classList.toggle("activeRow");   
//      } 
//   
//  });
//}

var tab1 = document.getElementById("tbl2");
var elements1 = tab1.getElementsByTagName('tr');
var i;

for (i = 1; i < elements1.length; i++) {
  elements1[i].addEventListener("click", function() {
  
    var panel1 = this.nextElementSibling;
    if (panel1.classList.contains("hiddenRow")){
       panel1.classList.toggle("collapseRow");
      this.classList.toggle("activeRow");   
      } 
   
  });
}
