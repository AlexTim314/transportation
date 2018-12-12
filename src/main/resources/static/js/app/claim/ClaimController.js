'use strict';

App.controller('ClaimController', ['$scope', 'ClaimService',
    function ($scope, ClaimService) {
        var self = this;
        self.department = {id: null, name: '', addres: ''};
        self.vehicleType = {type: '', specialization: ''};
        self.claim = {id: null, clDate: '', affirmation: '', tip: '', department: {name: ''}};
        self.record = {id: null, weekHash: '', type: '', datetime: '', dapartureDate: '', returnDate: '', timeDelivery: '', departureTime: '', returnTime: '',
            purpose: '', serviceField: '', templateName: '', carBoss: '', status: '', description: '', claim: {clDate: ''}, vehicleType: {specialization: ''}, waypoints: []};
        self.waypoint = {name: '', latitude: '', longitude: ''}; 
        self.departments = [];
        self.claims = [];
        self.records = [];
        self.waypoints = [];
        self.vehicleTypes = [];


        self.fetchClaims = function () {
//           var dateRange = '{ "StartDate": "2018-10-20", "EndDate": "2018-10-24" }';  
//           dateRange = JSON.parse(dateRange);
           var dd = new Date().getDate();
           var mm = new Date().getMonth();
           var yyyy = new Date().getFullYear();
            var sD = '2018-10-20';
            var eD = yyyy+'-'+mm+'-'+dd;
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

            if(clm.records === undefined){
                self.fetchAllRecords(clm);
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
        
        self.fetchAllVehicleTypes = function () {

            ClaimService.fetchVehicleTypes()
                    .then(
                            function (d) {
                                self.vehicleTypes = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching VehicleType');
                                alert(errResponse);
                            }
                    );
        };
        
        
        self.fetchClaims();
        self.fetchAllVehicleTypes();
        
//        self.fetchVehicleByTypes = function (vehType) {
//
//            ClaimService.fetchVehicleTypes(vehType)
//                    .then(
//                            function (d) {
//                                self.vehicleTypes = d;
//                            },
//                            function (errResponse) {
//                                console.error('Error while fetching VehicleType');
//                                alert(errResponse);
//                            }
//                    );
//        };

        
        self.createClaim = function (claim) {
           var dd = new Date().getDate();
           var mm = new Date().getMonth();
           var yyyy = new Date().getFullYear();
            claim.clDate = yyyy+'-'+mm+'-'+dd;           
            ClaimService.createClaim(claim)
                    .then(
                            self.fetchClaims,
                            function (errResponse) {
                                console.error('Error while creating Claim.');
                                showAlert(errResponse);
                            }
                    );
        };
        
        self.createRecord = function (record) {         
            ClaimService.createRecord(record)
                    .then(
                            self.fetchRecords,
                            function (errResponse) {
                                console.error('Error while creating Record.');
                                showAlert(errResponse);
                            }
                    );
        };


//        self.updateClaim = function (claim) {
//            ClaimService.updateClaim(claim)
//                    .then(
//                            self.fetchAllClaims,
//                            function (errResponse) {
//                                console.error('Error while updating Claim.');
//                                showAlert(errResponse);
//                            }
//                    );
//        };

        self.updateRecord = function (record) {
            ClaimService.updateRecord(record)
                    .then(
                            self.fetchAllRecords,
                            function (errResponse) {
                                console.error('Error while updating Record.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.deleteClaim = function (claim) {
            ClaimService.deleteClaim(claim)
                    .then(
                            self.fetchAllClaims,
                            function (errResponse) {
                                console.error('Error while deleting Claim.');
                                showAlert(errResponse);
                            }
                    );
        };
        
        self.deleteRecord = function (record) {
            ClaimService.deleteRecord(record)
                    .then(
                            self.fetchAllRecords,
                            function (errResponse) {
                                console.error('Error while deleting Record.');
                                showAlert(errResponse);
                            }
                    );
        };
        
        self.submit = function () {
//            if (self.claim.claimId === null) {
//                self.createClaim(self.claim);
//            } else {
//                self.updateClaim(self.claim);
//            }
console.log('sumbit')
            self.reset();
        };


    }]);
