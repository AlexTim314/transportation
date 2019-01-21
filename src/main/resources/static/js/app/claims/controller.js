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
        self.routeTemplate = {id: null, name: '', department: {shortname: ''}};
        self.routeTask = {id: null, workName: '', orderNum: '', place: {name: ''}, routeTemplate: {name: ''}};
        self.place = {id: null, name: '', address: ''};
        self.departments = [];
        self.claims = [];
        self.records = [];
        self.vehicleTypes = [];
        self.routeTemplates = [];
        self.routeTasks = [];
        self.places = [];


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
        
        self.fetchVehicleTypes = function () {
            ClaimsService.fetchVehicleTypes()
                    .then(
                            function (d) {
                                self.vehicleTypes = d;
                                console.log('vehicleTypes = ' + d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching VehicleTypes');
                            }
                    );
        };
        
        self.fetchRouteTemplates = function () {
            ClaimsService.fetchRouteTemplates()
                    .then(
                            function (d) {
                                self.routeTemplates = d;
                                console.log('routeTemplates = ' + d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching RouteTemplates');
                            }
                    );
        };
        
        self.fetchPlaces = function () {
            ClaimsService.fetchPlaces()
                    .then(
                            function (d) {
                                self.places = d;
                                console.log('Places = ' + d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching Places');
                            }
                    );
        };
        
        self.fetchRouteTasks = function () {
            ClaimsService.fetchRouteTasks()
                    .then(
                            function (d) {
                                self.routeTasks = d;
                                console.log('RouteTasks = ' + d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching RouteTasks');
                            }
                    );
        };


        self.fetchClaims();
        self.fetchVehicleTypes();
        self.fetchRouteTemplates();
        self.fetchPlaces();


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

