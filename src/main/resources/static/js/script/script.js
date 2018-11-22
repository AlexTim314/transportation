menu_open=function() {
 var str = document.getElementById("form-add");
 str.style.display="block";
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
formLogin_open=function(){
  var str = document.getElementById("formLogin");
  str.style.display="block";

}
formLogin_close=function(){
  var str = document.getElementById("formLogin");
  str.style.display="none";
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

