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
        self.allday = false;
        self.claimFromTemplateDate = '';

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
            self.allday = true;
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
            self.allday = false;
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
            self.allday = false;
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
                                self.routeTemplates.push({id: null, name: 'пользовательский', routeTasks: []});
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

        self.getToday = function () {
            var date = new Date();
            var day = date.getDate();
            var month = date.getMonth() + 1;
            var year = date.getFullYear();
            if (month < 10)
                month = "0" + month;
            if (day < 10)
                day = "0" + day;
            var today = year + "-" + month + "-" + day;
            document.getElementById('startDate').value = today;
        };

        self.fetchNewClaims();
        self.fetchAffirmedClaimsTomorrow();
        self.fetchClaimTemplates();
        self.fetchVehicleTypes();
        self.fetchRouteTemplates();
        self.fetchPlaces();
        self.fetchBosses();
        self.getToday();

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
            for (var i = 0; i < self.newClaims.length; i++) {
                if (self.newClaims[i].checked) {
                    affIds.push(self.newClaims[i].id);
                }
            }
            ClaimsService.affirmClaims(affIds)
                    .then(
                            function () {
                                for (var i = 0; i < affIds.length; i++) {
                                    for (var j = 0; j < self.newClaims.length; j++) {
                                        if (self.newClaims[j].id === affIds[i]) {
                                            self.newClaims.splice(j, 1);
                                        }
                                    }
                                }
                                self.fetchAffirmedClaims();
                                self.all = false;
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
            self.all = false;
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
            self.all = false;
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
            if (sd === null) {
                return;
            }
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

        self.checkRec = function (rec) {
            self.record.checked = false;
            self.record = rec;
            self.record.checked = true;
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
                routeTasks: [],
                claimFromTemplateDate: null
            };
            self.getToday();
            self.record = {id: null, startDate: null, endDate: null, entranceDate: null};
            self.affirmedClaim = {id: null, templateName: null, specialization: null, carBoss: null, purpose: null, creationDate: null, affirmationDate: null, actual: true, vehicleType: null, records: [], routeTasks: [], affirmator: {id: null}};
        };
        self.resetRTaskForm = function () {
            self.routeTask = {id: null, workName: '', orderNum: '', place: null, routeTemplate: null};
        };
        self.setClaimForTemp = function (claim) {
            self.copyClaimProperties(claim);
            self.claim.id = null;
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
        self.copyClaimProperties = function (claim) {
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
            if (claim !== null) {
                self.copyClaimProperties(claim);
                self.claim.id = null;
                self.claim.templateName = null;
                if (self.claimFromTemplateDate !== '') {
                    Date.prototype.addDays = function (days) {
                        var date = new Date(this.valueOf());
                        date.setDate(date.getDate() + days);
                        return date;
                    }

                    //поиск более ранней записи                    
                    var index = 0;
                    for (var i = 1; i < claim.records.length; i++) {
                        if (claim.records[0].startDate > claim.records[i].startDate) {
                            index = i;
                        }
                    }
                    var date = new Date(claim.records[index].startDate);
                    var datesDiff = self.claimFromTemplateDate.getDate() - date.getDate();
                    self.record.startDate = date.addDays(datesDiff);
                    self.record.entranceDate = new Date(claim.records[index].entranceDate).addDays(datesDiff);
                    self.record.endDate = new Date(claim.records[index].endDate).addDays(datesDiff);

                    for (var i = 0; i < claim.records.length; i++) {
                        date = new Date(claim.records[i].startDate);
                        self.claim.records[i].startDate = self.frmtDate(date.addDays(datesDiff), date);
                        date = new Date(claim.records[i].entranceDate);
                        self.claim.records[i].entranceDate = self.frmtDate(date.addDays(datesDiff), date);
                        date = new Date(claim.records[i].endDate);
                        self.claim.records[i].endDate = self.frmtDate(date.addDays(datesDiff), date);
                    }
                }
            }
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
            if (self.validateForm()) {
                return;
            }
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

        self.selectStatusIco = function (stat) {
            var inProgress = 'fas fa-lg fa-clock';
            var ready = 'fas fa-lg fa-check';
            var completed = 'fas fa-lg fa-check-double';
            var canceled = 'fas fa-lg fa-ban';
            switch (stat) {
                case 'IN_PROGRESS':
                    return inProgress;
                case 'READY':
                    return ready;
                case 'COMPLETED':
                    return completed;
                case 'CANCELED':
                    return canceled;
            }
        };

        self.selectStatus = function (record) {
            if (record.appointments.length === 0) {
                return '-';
            }
            var inProgress = 'Обрабатывается';
            var ready = 'Готово';
            var completed = 'Завершено';
            var canceled = 'Отменено';
            var stat = record.appointments[0].status;
            var id = record.appointments[0].id;
            for (var i = 1; i < record.appointments.length; i++) {
                if (id < record.appointments[i].id) {
                    id = record.appointments[i].id;
                    stat = record.appointments[i].status;
                }
            }
            switch (stat) {
                case 'IN_PROGRESS':
                    return inProgress;
                case 'READY':
                    return ready;
                case 'COMPLETED':
                    return completed;
                case 'CANCELED':
                    return canceled;
            }
        };

        self.selectStatusColor = function (stat) {
            var inProgress = 'done-status';
            var ready = 'status-ready';
            var completed = 'status-ready';
            var canceled = 'cancel-status';
            switch (stat) {
                case 'IN_PROGRESS':
                    return inProgress;
                case 'READY':
                    return ready;
                case 'COMPLETED':
                    return completed;
                case 'CANCELED':
                    return canceled;
            }
        };

        self.statusClass = function (record) {
            if (record.appointments.length === 0) {
                return '';
            }
            var stat = record.appointments[0].status;
            var id = record.appointments[0].id;
            for (var i = 1; i < record.appointments.length; i++) {
                if (id < record.appointments[i].id) {
                    id = record.appointments[i].id;
                    stat = record.appointments[i].status;
                }
            }
            return self.selectStatusIco(stat) + ' ' + self.selectStatusColor(stat);
        };

        self.appt = {};

        self.apptStatus = function () {
            var inProgress = 'Обрабатывается';
            var ready = 'Готово';
            var completed = 'Завершено';
            var canceled = 'Отменено';
            var stat = self.appt.status;
            switch (stat) {
                case 'IN_PROGRESS':
                    return inProgress;
                case 'READY':
                    return ready;
                case 'COMPLETED':
                    return completed;
                case 'CANCELED':
                    return canceled;
            }
        };

        self.showNote = function (record) {
            if (record.appointments.length === 0) {
                return;
            }
            var appt = record.appointments[0];
            var id = record.appointments[0].id;
            for (var i = 1; i < record.appointments.length; i++) {
                if (id < record.appointments[i].id) {
                    id = record.appointments[i].id;
                    appt = record.appointments[i];
                }
            }
            if (appt.note === null || appt.note === undefined || appt.note.length < 3) {
                return;
            }
            self.appt = appt;
            formOpen('appt-note')
        };

        self.personToString = function (person) {
            var result = person.firstname + " " + person.name.charAt(0) + "." + (person.surname !== null && person.surname !== undefined ? person.surname.charAt(0) + "." : "");
            return result;
        };

        self.checkPhone = function (appointment) {
            if (appointment === null || appointment === undefined) {
                return false;
            }
            return appointment.driver !== null && appointment.driver !== undefined && appointment.driver.phone !== null && appointment.driver.phone !== undefined;
        };

        self.showDriver = function (appointment) {
            if (appointment === null || appointment === undefined) {
                return '-';
            }
            return appointment.driver === null || appointment.driver === undefined ? '-' : self.personToString(appointment.driver);
        };

        self.validateForm = function () {
            if (self.claim.specialization === null) {
                return true;
            }
            if (self.claim.records.length === 0) {
                return true;
            }
            for (var i = 0; i < self.claim.records.length; i++) {
                var r = self.claim.records[i];
                if (r.startDate === null || r.startDate === undefined
                        || r.entranceDate === null || r.entranceDate === undefined
                        || r.endDate === null || r.endDate === undefined) {
                    return true;
                }
            }
            return false;
        };

    }]);

