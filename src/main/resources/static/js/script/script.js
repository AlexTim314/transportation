menu_open=function() {
 var str = document.getElementById("form-add");
 str.style.display="block";
 var str1 = document.getElementById("btn-add-edit");
 str1.innerHTML="Добавить";
 document.getElementById('no-active1').style.display = 'block';
}
menu_close=function() {
 var str = document.getElementById("form-add");
 str.style.display="none";
  document.getElementById('no-active1').style.display = 'none';
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
  document.getElementById('no-active1').style.display = 'block';
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
formOpen=function(name){
    var str = document.getElementById(name);
    str.style.display="block";
}
formClose=function(name){
    var str = document.getElementById(name);
    str.style.display="none";
}
formEditOpen=function(name){
    var str = document.getElementById(name);
    str.style.display="block";
    str.getElementsByTagName('button')[1].innerHTML="Изменить";
}
open_tab1=function(tabName,btnName,iEnd){
   for (var i = 1; i<=iEnd; i++) {
     var str = document.getElementById("tab-list"+i);
     var str_btn=document.getElementById("tab-btn"+i);
     if (str_btn.classList.contains("active")){
      str_btn.classList.remove("active");}
    str.style.display="none";  
    } 
  var str = document.getElementById(tabName);
  var str_btn=document.getElementById(btnName);
  str.style.display="block";
  str_btn.classList.add("active");
  var filterHidden=document.getElementsByClassName('filter-container');
  for (var i=0;i<filterHidden.length-1;i++){
      filterHidden[i].style.display = 'none';
  }
  if (tabName=='tab-list1') {  
    var filterShow=document.getElementsByClassName('tab1');
    for (var i=0;i<filterShow.length;i++){
      filterShow[i].style.display = 'block';
    }
  }
  if (tabName=='tab-list2') {  
    var filterShow=document.getElementsByClassName('tab2');
    for (var i=0;i<filterShow.length;i++){
      filterShow[i].style.display = 'block';
    }
  }
  if (tabName=='tab-list3') {  
    var filterShow=document.getElementsByClassName('tab3');
    for (var i=0;i<filterShow.length;i++){
      filterShow[i].style.display = 'block';
    }
  }
    if (tabName=='tab-list4') {  
    var filterShow=document.getElementsByClassName('tab4');
    for (var i=0;i<filterShow.length;i++){
      filterShow[i].style.display = 'block';
    }
  }
  /******filter****/
  // var fltr1 = document.getElementById("f-empls");
  // var fltr2 = document.getElementById("f-trnsp");
  // var fltr3 = document.getElementById("f-sts");
  // fltr1.style.display = 'block';
  // fltr2.style.display = 'none';
  // fltr3.style.display = 'none';
}
radioRoute=function(route1,route2) {
   var r1=document.getElementById(route1);
   var r2=document.getElementById(route2);
   r1.style.display="none"
   r2.style.display = "block";
}

open_sidenav=function(){
    var str=document.getElementById("sidenav-id");
    var str1=document.getElementById("claim-create");
    var str2=document.getElementById("sb-v-id");
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
turn_tabs1=function() {
    var str=document.getElementById("tbl1");
    var str1=document.getElementById("week-additional");
    var str2=document.getElementById("week1");
    var str3=document.getElementById("week2");
    str3.classList.remove('week-active');
    str2.classList.add('week-active');
    str.style.display = "table";
    str1.style.display = "none";
}
turn_tabs2=function() {
    var str1=document.getElementById("tbl1");
    var str=document.getElementById("week-additional");
     var str2=document.getElementById("week2");
      var str3=document.getElementById("week1");
    str2.classList.add('week-active');

    str3.classList.remove('week-active');
    str.style.display = "table";
    str1.style.display = "none";
}

// open_tab=function(){
//   var tab_btn=document.getElementsByClassName("nav-horizontal");
//   var btn=tab_btn.getElementsByTagName("div");
//   var i;
//   for (var i = btn.length; i++) {
//     btn[i].addEventListener("click", function () {
//       this.classList.add("active");
//     });
//   }
// }
// open_tab();

var tab = document.getElementById("tbl1");
var elements = tab.getElementsByTagName('tr');
var i;

for (i = 1; i < elements.length; i++) {
  elements[i].addEventListener("click", function() {
  
    var panel = this.nextElementSibling;
    if (panel.classList.contains("hiddenRow")){
       panel.classList.toggle("collapseRow");
      this.classList.toggle("activeRow");   
      } 
   
  });
}

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
