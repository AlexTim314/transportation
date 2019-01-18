'use strict';

App.controller('ClaimsController', ['$scope', 'ClaimsService',
    function ($scope, ClaimsService) {
        var self = this;
        self.claim = {id: null, templateName: '', specialization: '', carBoss: '', purpose: '', creationDate: '', affirmationDate: '', actual: '', department: {shortname: ''}, vehicleType: {typeName: ''}, creator: {fullName: ''}, affirmator: {fullName: ''}, records: []};
        self.record = {id: null, entranceDate: '', startDate: '', endDate: '', status: '', note: '', appointmentGroup: {}};
        self.vehicleType = {id: null, typeName: '', specialization: ''};
        self.creator = {id: null, username: '', fullName: '', department: {shortname: ''}};
        self.affirmator = {id: null, username: '', fullName: '', department: {shortname: ''}};
        self.department = {id: null, shortname: '', fullname: '', address: '', phone: ''};
        self.departments = [];
        self.claims = [];
        self.records = [];
        self.vehicleTypes = [];


        self.fetchClaims = function () {
            ClaimsService.fetchClaims()
                    .then(
                            function (d) {
                                self.claims = d;
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching Claims');
                            }
                    );
        };


        self.fetchClaims();


        self.createClaim = function (claim) {
            ClaimsService.createClaim(claim)
                    .then(
                            function (d) {
                                self.claims.push(d);
                            },
                            function (errResponse) {
                                console.error('Error while creating Claim.');
                            }
                    );
        };

        self.updateClaim = function (claim) {
            ClaimsService.updateClaim(claim)
                    .then(
                            
                                self.fetchClaims,
                            
                            function (errResponse) {
                                console.error('Error while updating Claim.');
                            }
                    );
        };

        self.deleteClaim = function (claim) {
            ClaimsService.deleteClaim(claim)
                    .then(
                            self.fetchClaims,
                            function (errResponse) {
                                console.error('Error while deleting Claim.');
                            }
                    );
        };
        
        self.fetchVehicleTypes = function(){
            ClaimsService.fetchVehicleTypes()
                .then(
                            function (d) {
                                self.vehicleTypes = d;
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching VehicleTypes');
                            }
                    );
        }

//        self.submit = function () {
//            if (self.department.id === null) {
//                self.createDepartment(self.department);
//            } else {
//                self.updateDepartment(self.department);
//            }
//            self.reset();
//        };
//
//        self.editDepartment = function (department) {
//            self.department.id = department.id;
//            self.department.fullname = department.fullname;
//            self.department.shortname = department.shortname;
//            self.department.address = department.address;
//            self.department.phone = department.phone;
//        };
//
//        self.reset = function () {
//            self.department = {id: null, shortname: '', fullname: '', address: '', phone: ''};
//        };

    }]);

