'use strict';

App.controller('ClaimsController', ['$scope', 'ClaimsService',
    function ($scope, ClaimsService) {
        var self = this;
        self.claim = {id: null, templateName: null, specialization: null, carBoss: null, purpose: null, creationDate: null, affirmationDate: null, actual: true, vehicleType: null, records: [], routeTasks: []};
        self.routeTemplate = {id: null, name: '', department: null, routeTasks: []};
        self.routeTask = {id: null, workName: '', orderNum: '', place: null, routeTemplate: null};
        self.place = {id: null, name: '', address: ''};
        self.newClaims = [];
        self.claimTemplates = [];
        self.routeTasks = [];
        self.vehicleTypes = [];
        self.routeTemplates = [];
        self.places = [];
        self.bosses = [];
        self.record = {id: null, startDate: "", endDate: "", entranceDate: ""};
        self.onWeek = false;
        self.isOtherDay = false;
        self.isDelete = false;
        var taskNum;


        self.fetchNewClaims = function () {
            ClaimsService.fetchNewClaims()
                    .then(
                            function (d) {
                                self.newClaims = d;
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching Claims');
                            }
                    );
        };
        
        self.fetchClaimTemplates = function () {
            ClaimsService.fetchСlaimTemplates()
                    .then(
                            function (d) {
                                self.claimTemplates = d;
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching ClaimTemplates');
                            }
                    );
        };        

        self.fetchVehicleTypes = function () {
            ClaimsService.fetchVehicleTypes()
                    .then(
                            function (d) {
                                self.vehicleTypes = d;
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
                            },
                            function (errResponse) {
                                console.error('Error while fetching Bosses');
                            }
                    );
        };

        self.fetchDepartment = function () {
            ClaimsService.fetchDepartment()
                    .then(
                            function (d) {
                                self.department = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Department');
                            }
                    );
        };

        self.fetchNewClaims();
        self.fetchClaimTemplates();
        self.fetchVehicleTypes();
        self.fetchRouteTemplates();
        self.fetchPlaces();
        self.fetchBosses();


        self.createClaim = function () {
            for (var i = 0; i < self.claim.routeTasks.length; i++) {
                self.claim.routeTasks[i].id = null;
            }
            menu_close();
            ClaimsService.createClaim(self.claim)
                    .then(
                            function (d) {
                                if (self.claim.templateName === null) {
                                    self.newClaims.push(d);
                                }
                                self.resetClaimForm();
                            },
                            function (errResponse) {
                                console.error('Error while creating Claim.');
                            }
                    );
        };

        self.updateClaim = function (claim) {
            for (var i = 0; i <claim.records.length; i++) {
                claim.records[i].id = null;
            }
            menu_close();
            ClaimsService.updateClaim(claim)
                    .then(
                            self.fetchNewClaims,
                            function (errResponse) {
                                console.error('Error while updating Claim.');
                            }
                    );
        };

        self.confirmClaims = function () {
            var confirmedClaims = [];
            for (var ind in self.newClaims) {
                var c = self.newClaims[ind];
                if (c.affirmation) {
                    confirmedClaims.push(c);
                    console.log(c);
                }
            }
            for (var i = 0; i < confirmedClaims.length; i++) {
                self.updateClaim(confirmedClaims[i]);
            }
        };

        self.deleteClaim = function () {
            var delIds = [];
            for (var i = 0; i < self.newClaims.length; i++) {
                if (self.newClaims[i].checked) {
                    delIds.push(self.newClaims[i].id);
                }
            }
            ClaimsService.deleteClaim(delIds)
                    .then(
                            self.fetchNewClaims,
                            function (errResponse) {
                                console.error('Error while deleting Claim.');
                            }
                    );
            formClose('del-claim-confirm');
        };

        self.deleteRecord = function (claim, record) {
            var recId = record.id;
            ClaimsService.deleteRecord(claim.id, recId)
                    .then(
                            function (d) {
                                var k = -1;
                                for (var i = 0; i < claim.records.length; i++) {
                                    if (recId === claim.records[i].id) {
                                        k = i;
                                        break;
                                    }
                                }
                                claim.records.splice(k, 1);
                            },
                            function (errResponse) {
                                console.error('Error while deleting Record.');
                            }
                    );
        };

        self.carBossToString = function (boss) {
            var result = boss.firstname + " " + boss.name.charAt(0) + "." + (boss.surname !== null && boss.surname !== null ? boss.surname.charAt(0) + "." : "") + " " + boss.post;
            return result;
        };

        self.addRTask = function () {
            var rt = {id: null};
            rt.orderNum = self.claim.routeTasks.length + 1;
            rt.workName = self.routeTask.workName;
            rt.place = self.routeTask.place;
            rt.routeTemplate = self.routeTask.routeTemplate;

            self.claim.routeTasks.push(rt);
        };

        self.frmtDate = function (date, time) {
            var result = new Date(new Date(date).getFullYear(), new Date(date).getMonth(), new Date(date).getDate(), new Date(time).getHours(), new Date(time).getMinutes(), new Date(time).getSeconds());
            return result;
        };

        self.addRec = function () {
            var sd = new Date(self.record.startDate);
            var inc = self.isOtherDay ? 1 : 0;
            if (self.onWeek) {
                for (var i = 0; i < 5; i++) {
                    var rec = {id: null};
                    rec.startDate = new Date(sd);
                    rec.startDate.setDate(rec.startDate.getDate() + i);
                    rec.endDate = self.frmtDate(sd, self.record.endDate);
                    rec.endDate.setDate(rec.endDate.getDate() + i + inc);
                    rec.entranceDate = self.frmtDate(sd, self.record.entranceDate);
                    rec.entranceDate.setDate(rec.entranceDate.getDate() + i);
                    self.claim.records.push(rec);
                }
            } else {
                var rec = {id: null};
                rec.startDate = new Date(sd);
                rec.endDate = self.frmtDate(sd, self.record.endDate);
                rec.endDate.setDate(rec.endDate.getDate() + inc);
                rec.entranceDate = self.frmtDate(sd, self.record.entranceDate);
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
                if (rec === self.claim.records[i]) {
                    k = i;
                    break;
                }
            }
            self.claim.records.splice(k, 1);
        };

        self.rowClick = function (clm) {
            if (clm.isVisible === undefined) {
                clm.isVisible = true;
            } else {
                clm.isVisible = !clm.isVisible;
            }
        };

        self.resetClaimForm = function () {
            self.isOtherDay = false;
            self.claim = {
                id: null,
                templateName: null,
                specialization: null,
                carBoss: null,
                purpose: null,
                creationDate: null,
                affirmationDate: null,
                actual: true,
                vehicleType: null,
                records: [],
                routeTasks: []
            };
            
            self.record = {id: null, startDate: null, endDate: null, entranceDate: null};
        };

        self.setClaimForTemp = function (claim) {
            self.claim = claim;
            self.claim.id = null;
            for (var i = 0; i < self.claim.records.length; i++) {
                self.claim.records[i].id = null;
            }
            formOpen('dialog-save');
        };

        self.checkAll = function () {
            for (var i = 0; i < self.newClaims.length; i++) {
                self.newClaims[i].checked = self.all;
            }
        };

        self.tryToUpdateClaim = function (claim) {
            self.claim.id = claim.id;
            self.claim.actual = claim.actual;
            self.claim.carBoss = claim.carBoss;
            self.claim.purpose = claim.purpose;
            self.claim.records = claim.records;
            self.claim.routeTasks = claim.routeTasks;
            self.claim.specialization = claim.specialization;
            self.claim.templateName = claim.templateName;
            self.claim.vehicleType = claim.vehicleType;
            self.claim.affirmationDate = claim.affirmationDate;
            self.claim.creationDate = new Date(claim.creationDate);
            self.record.startDate = new Date(claim.records[0].startDate);
            self.record.entranceDate = new Date(claim.records[0].entranceDate);
            self.record.endDate = new Date(claim.records[0].endDate);
            console.log(claim.records[0]);
            console.log(self.record);
            edit_open();
        };

        self.submitClaim = function () {
            if (self.claim.id === null) {
                self.createClaim(self.claim);
            } else {
                self.updateClaim(self.claim);
            }
            self.resetClaimForm();
        };

        self.pickCarBoss = function (cb) {
            self.claim.carBoss = cb;
        };

    }]);

