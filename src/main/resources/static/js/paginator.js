// initialize
var pageElem = 'pagination';
var total = 150;
var max = 5;
var pag = document.getElementById(pageElem);
var items = pag.getElementsByTagName('div');
var currentPage = 1;

//        recieveData=function(arg1,arg2){
//            total = arg1;
//            max = arg2;
//        }


createPage = function (elem, cur) {
    var obj = GetPager(total, cur, max);
    for (var i = 1; i <= obj.endPage; i++) {
        var div = document.createElement('div');
        div.className = "pages";
        if (i == cur) {
            div.className = "pages page-active"
        }
        div.innerHTML = i;
        elem.insertBefore(div, elem.children[i]);
    }
}
previousPageClick = function () {
    if (currentPage !== 1) {
        currentPage -= 1;
        var obj = GetPager(total, currentPage, max);
        for (var i = 1; i < items.length - 1; i++) {
            var arrPage = createArr(obj.startPage, obj.endPage);
            changePage(pag, arrPage, currentPage);
        }
    }
}
nextPageClick = function () {
    var obj = GetPager(total, currentPage, max);
    if (currentPage !== obj.totalPages) {
        currentPage += 1;
        for (var i = 1; i < items.length - 1; i++) {
            var arrPage = createArr(obj.startPage, obj.endPage);
            changePage(pag, arrPage, currentPage);
        }
    }
}
//	catchClick=function(){	
//		for (var i = 1; i < items.length-1; i++){
//    		items[i].addEventListener("click", function() {
//    		currentPage=this.innerHTML*1; 
//    		var obj=GetPager(total,currentPage,max);
//			var arrPage=createArr(obj.startPage,obj.endPage);
//			changePage(pag,arrPage,currentPage);			
//  		});    	
//	}	
//}

changePage = function (elem, arr, cur) {
    var items = elem.getElementsByTagName('div');
    var j = -1;
    for (var i = 1; i < items.length - 1; i++) {
        items[i].innerHTML = arr[j + i];
        if (arr[j + i] == cur) {
            items[i].classList.add('page-active');
        } else {
            items[i].classList.remove('page-active');
        }
    }
}

createArr = function (start, end) {
    var arr = [];
    for (var i = start; i <= end; i++) {
        arr.push(i);
    }
    return arr;
}


function GetPager(totalItems, currentPage, pageSize) {
    currentPage = currentPage || 1;
    pageSize = pageSize || 10;
    var totalPages = Math.ceil(totalItems / pageSize);
    var startPage, endPage;
    if (totalPages <= 10) {
        startPage = 1;
        endPage = totalPages;
    } else {
        if (currentPage <= 6) {
            startPage = 1;
            endPage = 10;
        } else if (currentPage + 4 >= totalPages) {
            startPage = totalPages - 9;
            endPage = totalPages;
        } else {
            startPage = currentPage - 5;
            endPage = currentPage + 4;
        }
    }
    return {
        // totalItems: totalItems,
        currentPage: currentPage,
        pageSize: pageSize,
        totalPages: totalPages,
        startPage: startPage,
        endPage: endPage
    };
}
//        createPage(pag,currentPage);
//        catchClick();