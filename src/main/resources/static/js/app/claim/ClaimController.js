'use strict';

App.controller('ClaimController', ['$scope', 'ClaimService',
    function ($scope, ClaimService) {
        var self = this;
        self.department = {id: null, name: '', addres: ''};
        self.claim = {id: null, clDate: '', affirmation: '', tip: '', department: {name: ''}};
        self.record = {id: null, weekHash: '', type: '', datetime: '', dapartureDate: '', returnDate: '', timeDelivery: '', departureTime: '', returnTime: '',
            purpose: '', serviceField: '', templateName: '', carBoss: '', status: '', description: '', claim: {clDate: ''}};
        self.departments = [];
        self.claims = [];
        self.records = [];


        self.fetchClaims = function () {
//           var dateRange = '{ "StartDate": "2018-10-20", "EndDate": "2018-10-24" }';  
//           dateRange = JSON.parse(dateRange);
            var sD = '2018-10-20';
            var eD = '2018-10-24';
            ClaimService.fetchClaims(sD, eD)
                    .then(
                            function (d) {
                                self.claims = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Claims');
                                alert(errResponse);
                            }
                    );
        };

        self.addRowHandlers = function (clm) {
            if(clm.isVisible === undefined){
                clm.isVisible = true;
            }else{
                clm.isVisible = !clm.isVisible;
            }
            //self.claim = clm;
            console.log('2 zapisi vniz');
            console.log(clm);
            console.log(clm.records);
            console.log('===============');
            
            if(clm.records === undefined){
                self.fetchAllRecords(clm);
//                clm.records = self.records;
            }
        };
        
        self.fetchAllRecords = function (claim) {

            ClaimService.fetchAllRecords(claim)
                    .then(
                            function (d) {
                                claim.records = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Records');
                                alert(errResponse);
                            }
                    );
        };

        self.fetchClaims();
      //  self.fetchAllRecords();


    }]);