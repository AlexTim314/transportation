'use strict';

App.controller('ClaimController', ['$scope', 'ClaimService',
    function ($scope, ClaimService) {
        var self = this;
        self.department = {id: null, name: '', addres: ''};
        self.vehicleType = {type: '', specialization: ''};
        self.claim = {id: null, clDate: '', affirmation: '', clType: '', department: {name: ''}};
        self.record = {id: null, weekHash: '', type: '', datetime: '', departureDate: '', returnDate: '', timeDelivery: '', departureTime: '', returnTime: '',
            purpose: '', serviceField: '', templateName: '', carBoss: '', status: '', description: '', claim: {clType: ''}, vehicleType: {specialization: ''}, waypoints: []};
        self.waypoint = {name: '', latitude: '', longitude: ''};
        self.departments = [];
        self.claims = [];
        self.records = [];
        self.waypoints = [];
        self.vehicleTypes = [];
        var recObj;


        self.fetchClaims = function () {

            var dd = new Date().getDate();
            var mm = new Date().getMonth();
            var yyyy = new Date().getFullYear();
            var sD = '2018-10-20';
            var eD = yyyy + '-' + mm + '-' + dd;
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
            self.claim = clm;
            if (clm.isVisible === undefined) {
                clm.isVisible = true;
            } else {
                clm.isVisible = !clm.isVisible;
            }

            if (clm.records === undefined) {
                self.fetchAllRecords(clm);
            }
        };

        self.addRowHandlersRecords = function (rec) {
            self.record = rec;
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

        self.fetchAllWaypoints = function () {

            ClaimService.fetchWaypoints()
                    .then(
                            function (d) {
                                self.waypoints = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Waypoints');
                                alert(errResponse);
                            }
                    );
        };


        self.fetchClaims();
        self.fetchAllVehicleTypes();
        self.fetchAllWaypoints();

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
            claim.clDate = yyyy + '-' + mm + '-' + dd;
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
            if (record.id === null) {
                record.claim = self.claim;
                record.waypoints = null;
                record.status = 'record_status_created';
                console.log('created');
            }
            record.datetime = new Date();
            record.departureDate = new Date(record.departureDate);
            record.departureTime = new Date(record.departureTime);
            record.returnDate = new Date(record.returnDate);
            record.returnTime = new Date(record.returnTime);
            record.timeDelivery = new Date(record.timeDelivery);
            ClaimService.createRecord(record)
                    .then(
                            self.fetchAllRecords(self.claim),
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
                            self.fetchClaims,
                            self.fetchAllRecords,
                            function (errResponse) {
                                console.error('Error while deleting Record.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.submit = function () {
            console.log('sumbit')
            self.reset();
        };

        self.editRecord = function (record) {
            self.record.id = record.id;
            self.record.weekHash = record.weekHash;
            self.record.type = record.type;
            self.record.datetime = new Date(record.datetime);
            self.record.departureDate = new Date(record.departureDate);
            self.record.returnDate = new Date(record.returnDate);
            self.record.timeDelivery = new Date(record.timeDelivery);
            self.record.departureTime = new Date(record.departureTime);
            self.record.returnTime = new Date(record.returnTime);
            self.record.purpose = record.purpose;
            self.record.serviceField = record.serviceField;
            self.record.templateName = record.templateName;
            self.record.carBoss = record.carBoss;
            self.record.status = record.status;
            self.record.description = record.description;
            self.record.claim = record.claim;
            self.record.vehicleType = record.vehicleType;
            self.record.waypoints = record.waypoints;
        };

        self.approvalClaims = function () {
            var table = document.getElementById("tbl1");
            var elem = table.getElementsByTagName("input");
            var k = 1;
            for (i = 0; i < elem.length; i++) {
                console.log(i);
                console.log(table.rows[k].cells[1].innerHTML);
                k = k + 2;
                // if (elem[i].checked === true){
                //    console.log(table.rows[i+1].cells[1].innerHTML);
                //     console.log('верно строка'+i);
                // } else {
                //     console.log('Неверно строка'+i);
                // }

            }

        };

        self.recieveObject = function (obj) {
            recObj = obj;
            if (recObj.hasOwnProperty('clType')) {
                console.log('принят объект Claim');
                console.log(recObj);
            }
            if (recObj.hasOwnProperty('weekHash')) {
                console.log('принят объект Record');
                console.log(recObj);
            }
        };

        self.delRecObject = function () {
            if (recObj.hasOwnProperty('clType')) {
                self.deleteClaim(recObj);
                console.log("Claim deleted!");
                recObj = null;
            }
            if (recObj.hasOwnProperty('weekHash')) {
                self.deleteRecord(recObj);
                console.log("Record deleted!");
                recObj = null;
            }           
        };

    }]);
