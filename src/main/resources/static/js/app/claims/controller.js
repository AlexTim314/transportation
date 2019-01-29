'use strict';

App.controller('ClaimsController', ['$scope', 'ClaimsService',
    function ($scope, ClaimsService) {
        var self = this;
        self.claim = {id: null, templateName: null, specialization: null, carBoss: null, purpose: null, creationDate: null, affirmationDate: null, actual: true, vehicleType: null, records: [], routeTasks: []};
        self.affirmedClaim = {id: null, templateName: null, specialization: null, carBoss: null, purpose: null, creationDate: null, affirmationDate: null, actual: true, vehicleType: null, records: [], routeTasks: [], affirmator: {id: null}};
        self.routeTemplate = {id: null, name: '', department: null, routeTasks: []};
        self.routeTask = {id: null, workName: '', orderNum: '', place: null, routeTemplate: null};
        self.temporaryRTask = {id: null, workName: '', orderNum: '', place: null, routeTemplate: null};
        self.place = {id: null, name: '', address: ''};
        self.record = {id: null, startDate: '', endDate: '', entranceDate: '', appointments: []};
        self.appointment = {id: null, creationDate: '', status: '', vehicleModel: {}, vehicle: {}, driver: {}};
        self.vehicleModel = {id: null, modelName: ''};
        self.vehicle = {id: null, number: ''};
        self.driver = {id: null, firstname: '', name: '', surname: '', phone: ''};
        self.affirmator = {id: null, username: '', fullName: ''};
        self.newClaims = [];
        self.affirmedClaims = [];
        self.claimTemplates = [];
        self.appointments = [];
        self.routeTasks = [];
        self.vehicleTypes = [];
        self.routeTemplates = [];
        self.places = [];
        self.bosses = [];
        self.onWeek = false;
        self.isOtherDay = false;
        self.isDelete = false;
        self.today = false;
        self.week = false;
        self.all = false;
        
        self.claimFromTemplateDate='';
        self.fetchNewClaims = function () {
            ClaimsService.fetchNewClaims()
                    .then(
                            function (d) {
                                self.newClaims = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching NewClaims');
                            }
                    );
        };
        self.fetchAffirmedClaims = function () {
            self.all = true;
            self.today = false;
            self.week = false;
            ClaimsService.fetchAffirmedClaims()
                    .then(
                            function (d) {
                                self.affirmedClaims = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching AffirmedClaims');
                            }
                    );
        };
        self.fetchAffirmedClaimsWeek = function () {
            self.all = false;
            self.today = false;
            self.week = true;
            self.affirmedClaims = [];
            ClaimsService.fetchAffirmedClaimsWeek()
                    .then(
                            function (d) {
                                self.affirmedClaims = d;
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching Affirmed Claims Week');
                            }
                    );
        };
        self.fetchAffirmedClaimsTomorrow = function () {
            self.all = false;
            self.today = true;
            self.week = false;
            ClaimsService.fetchAffirmedClaimsTomorrow()
                    .then(
                            function (d) {
                                self.affirmedClaims = d;
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching Affirmed Claims Tomorrow');
                            }
                    );
        };
        self.fetchClaimTemplates = function () {
            ClaimsService.fetchСlaimTemplates()
                    .then(
                            function (d) {
                                self.claimTemplates = d;
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
        self.fetchAffirmedClaims();
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
                                } else {
                                    self.claimTemplates.push(d);
                                }
                                self.resetClaimForm();
                                //self.fetchNewClaims;
                            },
                            function (errResponse) {
                                console.error('Error while creating Claim.');
                            }
                    );
        };
        self.saveClaimTemplate = function () {
            if (self.claim.templateName === null) {
                alert('Имя шаблона не может быть пустым!');
            } else {
                self.createClaim();
            }
        };
        self.updateClaim = function (claim) {
            for (var i = 0; i < claim.routeTasks.length; i++) {
                claim.routeTasks[i].id = null;
            }
            menu_close();
            ClaimsService.updateClaim(claim)
                    .then(
                            function () {
                                if (claim.templateName === null) {
                                    self.fetchNewClaims();
                                } else {
                                    self.fetchClaimTemplates();
                                }

                            },
                            function (errResponse) {
                                console.error('Error while updating Claim.');
                            }
                    );
        };

        self.updateAffirmClaim = function (affClaim) {
            ClaimsService.updateClaim(affClaim)
                    .then(
                            self.fetchNewClaims,
                            function (errResponse) {
                                console.error('Error while updating affClaim.');
                            }
                    );
        };

        self.affirmClaims = function () {
            var affIds = [];
            var affClm = [];
            for (var i = 0; i < self.newClaims.length; i++) {
                if (self.newClaims[i].checked) {
                    affIds.push(self.newClaims[i].id);
                    affClm.push(self.newClaims[i]);
                }
            }
            ClaimsService.affirmClaims(affIds)
                    .then(
                            function () {
                                for (var i = 0; i < affClm.length; i++) {
                                    self.affirmedClaims.push(affClm[i]);
                                    for (var j = 0; j < self.newClaims.length; j++) {
                                        if (self.newClaims[j] === affClm[i]) {
                                            self.newClaims.splice(j, 1);
                                        }
                                    }
                                }
                            },
//                            self.fetchNewClaims,
//                            self.fetchAffirmedClaims,
                            function (errResponse) {
                                console.error('Error while affirm Claim.');
                            }
                    );
        };
        self.deleteClaims = function () {
            var delIds = [];
            for (var i = 0; i < self.newClaims.length; i++) {
                if (self.newClaims[i].checked) {
                    delIds.push(self.newClaims[i].id);
                }
            }
            ClaimsService.deleteClaims(delIds)
                    .then(
                            self.fetchNewClaims,
                            function (errResponse) {
                                console.error('Error while deleting Claim.');
                            }
                    );
            formClose('del-claim-confirm');
        };

        self.deleteClaimTemplates = function () {
            var delIds = [];
            for (var i = 0; i < self.claimTemplates.length; i++) {
                if (self.claimTemplates[i].checked) {
                    delIds.push(self.claimTemplates[i].id);
                }
            }
            ClaimsService.deleteClaims(delIds)
                    .then(
                            self.fetchClaimTemplates,
                            function (errResponse) {
                                console.error('Error while deleting ClaimTemplates.');
                            }
                    );
            formClose('del-claim-template-confirm');
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

        self.cancelAffRecord = function (rec) {
            var maxDateApp = {};
            var maxDate = rec.appointments[0].creationDate;
            for (var i = 0; i < rec.appointments.length; i++) {
                if (rec.appointments[i].creationDate > maxDate) {
                    maxDate = rec.appointments[i].creationDate;
                    maxDateApp = rec.appointments[i];
                }
            }
            maxDateApp.status = 'CANCELED';
            console.log('Отмененное назначение:');
            console.log(maxDateApp);
        };

        self.carBossToString = function (boss) {
            var result = boss.firstname + " " + boss.name.charAt(0) + "." + (boss.surname !== null && boss.surname !== null ? boss.surname.charAt(0) + "." : "") + " " + boss.post;
            return result;
        };
        self.addRTask = function () {
            var rt = {id: null};
            rt.orderNum = self.claim.routeTasks.length;
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
        self.infoClick = function (clm) {
            formOpen('more-claim');
            self.claim = clm;
        };
        self.infoAffClick = function (clm) {
            formOpen('more-claim-confirm');
            self.affirmedClaim = clm;
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
            self.affirmedClaim = {id: null, templateName: null, specialization: null, carBoss: null, purpose: null, creationDate: null, affirmationDate: null, actual: true, vehicleType: null, records: [], routeTasks: [], affirmator: {id: null}};
        };
        self.resetRTaskForm = function () {
            self.routeTask = {id: null, workName: '', orderNum: '', place: null, routeTemplate: null};
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
        self.checkAllTemplates = function () {
            for (var i = 0; i < self.claimTemplates.length; i++) {
                self.claimTemplates[i].checked = self.all;
            }
        };
        self.copyClaimProperties =  function (claim) {
            self.claim.id = claim.id;
            self.claim.actual = claim.actual;
            self.claim.carBoss = claim.carBoss;
            self.claim.purpose = claim.purpose;
            self.claim.records = [];
            for (var i = 0; i < claim.records.length; i++) {
                var rec = {
                    id: null,
                    startDate: claim.records[i].startDate,
                    entranceDate: claim.records[i].entranceDate,
                    endDate: claim.records[i].endDate
                };
                self.claim.records.push(rec);
            }
            self.claim.routeTasks = claim.routeTasks;
            self.claim.specialization = claim.specialization;
            self.claim.templateName = claim.templateName;
            self.claim.vehicleType = claim.vehicleType;
            self.claim.affirmationDate = claim.affirmationDate;
            self.claim.creationDate = new Date(claim.creationDate);
            self.record.startDate = new Date(claim.records[0].startDate);
            self.record.entranceDate = new Date(claim.records[0].entranceDate);
            self.record.endDate = new Date(claim.records[0].endDate);
        };
        self.prepearClaimFromTemplate = function (claim) {
            self.copyClaimProperties(claim);
            self.claim.id = null;  
            self.claim.templateName = null;            
        };
        self.tryToUpdateClaim = function (claim) {
            self.copyClaimProperties(claim);
            if (self.claim.templateName === null) {
                edit_open();
            } else {
                templateEditOpen();
            }
        };
        self.tryToUpdateRTask = function (routeTask) {
            self.routeTask.id = routeTask.id;
            self.routeTask.orderNum = routeTask.orderNum;
            self.routeTask.workName = routeTask.workName;
            self.routeTask.place = routeTask.place;
            self.routeTask.routeTemplate = routeTask.routeTemplate;
            self.temporaryRTask = routeTask;
        };
        self.updateRTask = function () {
            self.removeRTask(self.temporaryRTask);
            var rt = {id: null};
            rt.orderNum = self.routeTask.orderNum;
            rt.workName = self.routeTask.workName;
            rt.place = self.routeTask.place;
            rt.routeTemplate = self.routeTask.routeTemplate;
            self.claim.routeTasks.push(rt);
        };
        self.submitRTask = function () {
            if (self.routeTask.id === null && self.routeTask.orderNum === '') {
                self.addRTask();
            } else {
                self.updateRTask();
            }
            self.resetRTaskForm();
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

