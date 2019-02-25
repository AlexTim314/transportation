menu_open = function () {
    var str = document.getElementById("form-add");
    str.style.display = "block";
    var str1 = document.getElementById("btn-add-edit");
    str1.innerHTML = "Добавить";
    var sec = document.getElementsByClassName('section-template');
    sec[0].style.display = "none";
    var sec = document.getElementsByClassName('section-template');
    sec[1].style.display = "none";
}
menu_close = function () {
    var str = document.getElementById("form-add");
    str.style.display = "none";
    formClose('formTask');
}
more_open = function () {
    var str = document.getElementById("more-id");
    str.style.display = "block";
}
more_close = function () {
    var str = document.getElementById("more-id");
    str.style.display = "none";
}

edit_open = function () {
    var str = document.getElementById("form-add");
    str.style.display = "block";
    var str1 = document.getElementById("btn-add-edit");
    str1.innerHTML = "Изменить";
    var sec = document.getElementsByClassName('section-template');
    sec[0].style.display = "none";
    var sec = document.getElementsByClassName('section-template');
    sec[1].style.display = "none";
}

formRecord_close = function () {
    var str = document.getElementById("formRecord-id");
    str.style.display = "none";
}
formRecord_open = function () {
    var str = document.getElementById("formRecord-id");
    str.style.display = "block";

}
formLogin_close = function () {
    var str = document.getElementById("formLogin");
    str.style.display = "none";
}
formLogin_open = function () {
    var str = document.getElementById("formLogin");
    str.style.display = "block";
}
formOpen = function (name) {
    var str = document.getElementById(name);
    str.style.display = "block";
}
formClose = function (name) {
    var str = document.getElementById(name);
    str.style.display = "none";
}
formEditOpen = function (name) {
    var str = document.getElementById(name);
    str.style.display = "block";
    str.getElementsByTagName('button')[1].innerHTML = "Изменить";
}
selectToggle = function (down) {
    var down1 = document.getElementById(down);
    if (down1.style.display == 'block') {
        down1.style.display = 'none';
    } else {
        down1.style.display = 'block';
    }
}
templateOpen = function () {
    menu_open();
    var sec = document.getElementsByClassName('section-template');
    sec[0].style.display = "block";
}
templateAddOpen = function () {
    menu_open();
    var sec = document.getElementsByClassName('section-template');
    sec[1].style.display = "block";
}
templateEditOpen = function () {
    edit_open();
    var sec = document.getElementsByClassName('section-template');
    sec[1].style.display = "block";
}
open_tab1 = function (tabName, btnName, iEnd) {
    for (var i = 1; i <= iEnd; i++) {
        var str = document.getElementById("tab-list" + i);
        var str_btn = document.getElementById("tab-btn" + i);
        if (str_btn.classList.contains("active")) {
            str_btn.classList.remove("active");
        }
        str.style.display = "none";
    }
    var str = document.getElementById(tabName);
    var str_btn = document.getElementById(btnName);
    str.style.display = "block";
    str_btn.classList.add("active");
    var filterHidden = document.getElementsByClassName('filter-container');
    for (var i = 0; i < filterHidden.length - 1; i++) {
        filterHidden[i].style.display = 'none';
    }
    if (tabName == 'tab-list1') {
        var filterShow = document.getElementsByClassName('tab1');
        for (var i = 0; i < filterShow.length; i++) {
            filterShow[i].style.display = 'block';
        }
    }
    if (tabName == 'tab-list2') {
        var filterShow = document.getElementsByClassName('tab2');
        for (var i = 0; i < filterShow.length; i++) {
            filterShow[i].style.display = 'block';
        }
    }
    if (tabName == 'tab-list3') {
        var filterShow = document.getElementsByClassName('tab3');
        for (var i = 0; i < filterShow.length; i++) {
            filterShow[i].style.display = 'block';
        }
    }
    if (tabName == 'tab-list4') {
        var filterShow = document.getElementsByClassName('tab4');
        for (var i = 0; i < filterShow.length; i++) {
            filterShow[i].style.display = 'block';
        }
    }

}
radioRoute = function (route1, route2) {
    var r1 = document.getElementById(route1);
    var r2 = document.getElementById(route2);
    r1.style.display = "none"
    r2.style.display = "block";
}
checkShow = function (checkParam, checkbox) {
    var check1 = document.getElementById(checkParam);
    var checkbox1 = document.getElementById(checkbox);
    if (checkbox1.checked == true) {
        check1.style.display = "block";
    } else {
        check1.style.display = "none";
    }
}
checkHidden = function () {
    var checkbox1 = document.getElementById('check-hidden1');
    var elements = document.getElementsByClassName('check-hidden');
    if (checkbox1.checked == true) {
        for (var i = 0; i < elements.length; i++) {
            elements[i].style.display = 'none';
        }
    } else {
        for (var i = 0; i < elements.length; i++) {
            elements[i].style.display = 'block';
        }
    }
}
open_sidenav = function () {
    var str = document.getElementById("sidenav-id");
    var str1 = document.getElementById("claim-create");
    var str2 = document.getElementById("sb-v-id");
    str.style.left = '0';
    str1.style.width = '82%';
    str2.style.left = "-2%";
    // setTimeout('str2.style.left="-2%"', 900);

}
close_sidenav = function () {
    var str = document.getElementById("sidenav-id");
    var str1 = document.getElementById("claim-create");
    var str2 = document.getElementById("sb-v-id");
    str.style.left = '-17%';
    str1.style.width = '98%';
    str2.style.left = "0";
    // setTimeout('', 900);

}
turn_tabs1 = function () {
    var str = document.getElementById("tbl1");
    var str1 = document.getElementById("week-additional");
    var str2 = document.getElementById("week1");
    var str3 = document.getElementById("week2");
    str3.classList.remove('week-active');
    str2.classList.add('week-active');
    str.style.display = "table";
    str1.style.display = "none";
}
turn_tabs2 = function () {
    var str1 = document.getElementById("tbl1");
    var str = document.getElementById("week-additional");
    var str2 = document.getElementById("week2");
    var str3 = document.getElementById("week1");
    str2.classList.add('week-active');

    str3.classList.remove('week-active');
    str.style.display = "table";
    str1.style.display = "none";
}
//----------inputs masks---------------
window.addEventListener("DOMContentLoaded", function () {
    function setCursorPosition(pos, elem) {
        elem.focus();
        if (elem.setSelectionRange)
            elem.setSelectionRange(pos, pos);
        else if (elem.createTextRange) {
            var range = elem.createTextRange();
            range.collapse(true);
            range.moveEnd("character", pos);
            range.moveStart("character", pos);
            range.select();
        }
    }

    function mask(event) {
        var matrix = "+7 (___) ___ ____",
                i = 0,
                def = matrix.replace(/\D/g, ""),
                val = this.value.replace(/\D/g, "");
        if (def.length >= val.length)
            val = def;
        this.value = matrix.replace(/./g, function (a) {
            return /[_\d]/.test(a) && i < val.length ? val.charAt(i++) : i >= val.length ? "" : a;
        });
        if (event.type === "blur") {
            if (this.value.length === 2)
                this.value = "";
        } else
            setCursorPosition(this.value.length, this);
    }

    function russ(event) {
        this.value = this.value.replace(/[^а-яёА-ЯЁ]/ig, "");
        if (event.type === "blur") {
            if (this.value.length === 2)
                this.value = "";
        } else
            setCursorPosition(this.value.length, this);
    }

    function fueling(event) {
        var matrix = "___.__",
                i = 0,
                def = matrix.replace(/\D/g, ""),
                val = this.value.replace(/\D/g, "");
        if (def.length >= val.length)
            val = def;
        this.value = matrix.replace(/./g, function (a) {
            return /[_\d]/.test(a) && i < val.length ? val.charAt(i++) : i >= val.length ? "" : a;
        });
        if (event.type === "blur") {
            if (this.value.length === 2)
                this.value = "";
        } else
            setCursorPosition(this.value.length, this);
    }

    function odometr(event) {
        var matrix = "_________.__",
                i = 0,
                def = matrix.replace(/\D/g, ""),
                val = this.value.replace(/\D/g, "");
        if (def.length >= val.length)
            val = def;
        this.value = matrix.replace(/./g, function (a) {
            return /[_\d]/.test(a) && i < val.length ? val.charAt(i++) : i >= val.length ? "" : a;
        });
        if (event.type === "blur") {
            if (this.value.length === 2)
                this.value = "";
        } else
            setCursorPosition(this.value.length, this);
    }

    function eHours(event) {
        var matrix = "_______.__",
                i = 0,
                def = matrix.replace(/\D/g, ""),
                val = this.value.replace(/\D/g, "");
        if (def.length >= val.length)
            val = def;
        this.value = matrix.replace(/./g, function (a) {
            return /[_\d]/.test(a) && i < val.length ? val.charAt(i++) : i >= val.length ? "" : a;
        });
        if (event.type === "blur") {
            if (this.value.length === 2)
                this.value = "";
        } else
            setCursorPosition(this.value.length, this);
    }

    var input = document.querySelector("[type='tel']");
    var surname = document.getElementsByClassName("letter-mask");
    var fuel = document.getElementsByClassName("fuel");
    var odom = document.getElementsByClassName("odometr");
    var engineHours = document.getElementsByClassName("engineHours");

    input.addEventListener("input", mask, false);
    input.addEventListener("focus", mask, false);
    input.addEventListener("blur", mask, false);

    for (var i = 0; i < surname.length; i++) {
        surname[i].addEventListener("input", russ, false);
        surname[i].addEventListener("focus", russ, false);
        surname[i].addEventListener("blur", russ, false);
    }
    for (var i = 0; i < fuel.length; i++) {
        fuel[i].addEventListener("input", fueling, false);
        fuel[i].addEventListener("focus", fueling, false);
        fuel[i].addEventListener("blur", fueling, false);
    }
    for (var i = 0; i < odom.length; i++) {
        odom[i].addEventListener("input", odometr, false);
        odom[i].addEventListener("focus", odometr, false);
        odom[i].addEventListener("blur", odometr, false);
    }
    for (var i = 0; i < engineHours.length; i++) {
        engineHours[i].addEventListener("input", eHours, false);
        engineHours[i].addEventListener("focus", eHours, false);
        engineHours[i].addEventListener("blur", eHours, false);
    }
});
//----------------------------------------------------

open_sidetab = function (tabName, btnName, iEnd) {
    for (var i = 1; i <= iEnd; i++) {
        var str = document.getElementById("bar-block" + i);
        var str_btn = document.getElementById("side-btn" + i);
        if (str_btn.classList.contains("active")) {
            str_btn.classList.remove("active");
        }
        str.style.display = "none";

    }
    var str = document.getElementById(tabName);
    var str_btn = document.getElementById(btnName);
    str.style.display = "block";
    str_btn.classList.add("active");
}




fasttab = function () {
    var fast_element = getElementsByClassName('fast-filter');
    var elements = fast_element.getElementsByClassName('filter-elem');
    for (i = 0; i < elements.length; i++) {
        elements[i].addEventListener("click", function () {

            var panel1 = this.nextElementSibling;
            if (panel1.classList.contains("hiddenRow")) {
                panel1.classList.toggle("collapseRow");
                this.classList.toggle("activeRow");
            }

        });

    }
}



//  var block= document.getElementById('bar-block1');
//  var items= block.getElementsByClassName('info-bar-btn');
//  var i;
//    for (i = 0; i < items.length; i++){
//    items[i].addEventListener("click", function() {
//    this.classList.toggle('subActive');
//  });
//
//}




