'use strict';

App.controller('ClaimsController', ['$scope', 'ClaimsService',
    function ($scope, ClaimsService) {
        var self = this;
        self.claim = {id: null, templateName: null, specialization: '', carBoss: null, purpose: '', creationDate: '', affirmationDate: null, actual: true, vehicleType: {typeName: ''}, records: [], routeTasks: []};
        self.routeTemplate = {id: null, name: '', department: null, routeTasks: []};
        self.routeTask = {id: null, workName: '', orderNum: '', place: null, routeTemplate: null};
        self.place = {id: null, name: '', address: ''};
        self.claims = [];
        self.routeTasks = [];
        self.vehicleTypes = [];
        self.routeTemplates = [];
        self.places = [];
        self.bosses = [];
        self.record = {id: null, startDate: "", endDate: "", entranceDate: ""};
        self.onWeek = false;

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
                                console.log('vehicleTypes =>');
                                console.log(d);
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
                                console.log('routeTemplates =>');
                                console.log(d);
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
                                console.log('Places =>');
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching Places');
                            }
                    );
        };

        self.fetchBosses = function () {
            ClaimsService.fetchBosses()
                    .then(
                            function (d) {
                                self.bosses = d;
                                console.log('Bosses =>');
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching Bosses');
                            }
                    );
        };

        self.fetchClaims();
        self.fetchVehicleTypes();
        self.fetchRouteTemplates();
        self.fetchPlaces();
        self.fetchBosses();


        self.createClaim = function (claim) {
            console.log(claim);
            for (var i = 0; i < self.claim.routeTasks.length; i++) {
                self.claim.routeTasks[i].id = null;
            }
            console.log(claim);
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

        self.carBossToString = function (boss) {
            var result = boss.firstname + " " + boss.name.charAt(0) + "." + (boss.surname !== null && boss.surname !== null ? boss.surname.charAt(0) + "." : "") + " " + boss.post;
            return result;
        };

        self.addRTask = function (routeTask) {
            self.claim.routeTasks.push(routeTask);
        };

        self.addRec = function () {
            var d = self.record.startDate;
            if (self.onWeek) {
                for (var i = 0; i < 7; i++) {
                    var rec = {id: null};
                    rec.startDate = new Date(d);
                    rec.startDate.setDate(rec.startDate.getDate() + i);

                    rec.endDate = new Date(d);
                    rec.endDate.setDate(rec.endDate.getDate() + i);

                    rec.entranceDate = new Date(d);
                    rec.entranceDate.setDate(rec.entranceDate.getDate() + i);

                    self.claim.records.push(rec);

                    console.log(rec);
                }
            } else {
                var rec = {id: null};
                rec.startDate = new Date(d);
                rec.endDate = new Date(d);
                rec.entranceDate = new Date(d);
                self.claim.records.push(rec);
            }
        };

        self.removeRTask = function (routeTask) {
            var k = -1;
            for (var i = 0; i < self.claim.routeTasks.length; i++) {
                if (routeTask === self.claim.routeTasks[i]) {
                    k = i;
                    break;
                }
            }
            self.claim.routeTasks.splice(k, 1);
        };

        self.removeRec = function (rec) {
            //удалить запись
            var k = -1;
            for (var i = 0; i < self.claim.records.length; i++) {
//                if(rec.startDate === self.claim.records[i].startDate){
//                    k = i;
//                    break;
//                }
                if (rec === self.claim.records[i]) {
                    k = i;
                    break;
                }
            }
            self.claim.records.splice(k, 1);
        };



    }]);

