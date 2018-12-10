 'use strict';
 App.controller('routeTagController', function() {
    var routeList = this;
    routeList.tags = [
      {text:'',done:false}];
   
    routeList.addTag = function() {
      routeList.tags.push({text:routeList.routeText,done:true});
        routeList.routeText = '';
    }; 
  });