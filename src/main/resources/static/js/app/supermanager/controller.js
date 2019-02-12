'use strict';

App.controller('SuperManagerController', ['$scope', 'SuperManagerService',
    function ($scope, SuperManagerService) {
        var self = this;
        self.department = {id: null, shortname: '', fullname: '', address: '', phone: '', claims: []};
        self.claim = {id: null, templateName: null, specialization: null, carBoss: null, purpose: null, creationDate: null, affirmationDate: null, actual: true, vehicleType: null, routeTasks: []};
        self.routeTemplate = {id: null, name: '', department: null, routeTasks: []};
        self.routeTask = {id: null, workName: '', orderNum: '', place: null, routeTemplate: null};
        self.place = {id: null, name: '', address: ''};
        self.record = {id: null, startDate: '', endDate: '', entranceDate: '', claim: {}, appointments: []};
        self.appointment = {id: null, creationDate: '', status: '', transportDep: {}, vehicleModel: {}, vehicle: {}, driver: {}};
        self.vehicleModel = {id: null, modelName: '', vehicleType: {}};
        self.vehicle = {id: null, number: ''};
        self.driver = {id: null, firstname: '', name: '', surname: '', phone: ''};
        self.vehicleType = {id: null, typeName: '', specialization: ''};
        self.clrec = {record: {}, claim: {}};
        self.compClRec = {record: {}, claim: {}, appointment: {}};
        self.compClRecs = [];
        self.headers = [];
        self.departments = [];
        self.claims = [];
        self.records = [];
        self.appointments = [];
        self.vehicleModels = [];
        var expandedHeaders = [];
        self.places = [];
        self.today = false;
        self.week = false;
        self.all = false;
        self.date;
        self.allChecked = false;



        self.fetchAllRecords = function () {
            self.all = true;
            self.today = false;
            self.week = false;
            SuperManagerService.fetchAllRecords()
                    .then(
                            function (d) {
                                self.headers = d;
                                expandHeaders();
                            },
                            function (errResponse) {
                                console.error('Error while fetching AllRecords');
                            }
                    );
        };

        self.fetchWeekRecords = function () {
            self.all = false;
            self.today = false;
            self.week = true;
            SuperManagerService.fetchWeekRecords()
                    .then(
                            function (d) {
                                self.headers = d;
                                expandHeaders();
                            },
                            function (errResponse) {
                                console.error('Error while fetching WeekRecords');
                            }
                    );
        };

        self.fetchTomorrowRecords = function () {
            self.all = false;
            self.today = true;
            self.week = false;
            SuperManagerService.fetchTomorrowRecords()
                    .then(
                            function (d) {
                                self.headers = d;
                                expandHeaders();
                            },
                            function (errResponse) {
                                console.error('Error while fetching TodayRecords');
                            }
                    );
        };

        self.fetchDateRecords = function () {
            self.all = false;
            self.today = false;
            self.week = false;
            self.changeDate();
            var datePlan = new Date(document.getElementById('date-plan').value);
            SuperManagerService.fetchDateRecords(datePlan)
                    .then(
                            function (d) {
                                self.headers = d;
                                expandHeaders();
                            },
                            function (errResponse) {
                                console.error('Error while fetching Records of Day');
                            }
                    );
        };

        self.fetchAllRecords();


        self.changeDate = function () {
            var datePlan = new Date(document.getElementById('date-plan').value);
            var day = datePlan.getDate();
            var month = datePlan.getMonth() + 1;
            var year = datePlan.getFullYear();
            if (month < 10)
                month = "0" + month;
            if (day < 10)
                day = "0" + day;
            self.date = day + "." + month + "." + year;
        };

        self.rowClick = function (dep) {
            if (dep.isVisible === undefined) {
                dep.isVisible = true;
            } else {
                dep.isVisible = !dep.isVisible;
            }
        };

        var expandHeaders = function () {
            for (var k in expandedHeaders) {
                self.headers[k].isVisible = expandedHeaders[k];
            }
        };

        self.personToString = function (person) {
            var result = person.firstname + " " + person.name.charAt(0) + "." + (person.surname !== null && person.surname !== undefined ? person.surname.charAt(0) + "." : "");
            return result;
        };

        self.checkPhone = function (appointment) {
            return appointment.driver !== null && appointment.driver !== undefined && appointment.driver.phone !== null && appointment.driver.phone !== undefined;
        };

        self.showDriver = function (appointment) {
            return appointment.driver === null || appointment.driver === undefined ? '-' : self.personToString(appointment.driver);
        };

        self.checkAll = function (compositeClaimRecords) {
            for (var i = 0; i < compositeClaimRecords.length; i++) {
                compositeClaimRecords[i].record.checked = self.allChecked;
            }
        };

        self.prepareToAffirm = function (clr) {
            self.compClRecs = clr;
            formOpen('dialog-signature');
        };

        self.affirmRecords = function () {
            var affIds = [];
            for (var i = 0; i < self.compClRecs.length; i++) {
                if (self.compClRecs[i].record.checked) {
                    affIds.push(self.compClRecs[i].record.id);
                }
            }
            SuperManagerService.affirmRecords(affIds)
                    .then(
                            function () {
//                                for (var i = 0; i < affIds.length; i++) {
//                                    for (var j = 0; j < self.newClaims.length; j++) {
//                                        if (self.newClaims[j].id === affIds[i]) {
//                                            self.newClaims.splice(j, 1);
//                                        }
//                                    }
//                                }
                                formClose('dialog-signature');
                                self.fetchAllRecords();
                                self.all = false;
                            },
//                            self.fetchNewClaims,
//                            self.fetchAffirmedClaims,
                            function (errResponse) {
                                console.error('Error while affirm Records.');
                            }
                    );
        };


        self.prepareToCancel = function (clrec) {
            self.compClRecs = clrec;
            formOpen('dialog-ban');
        };

        self.cancelRecords = function () {
            var Ids = [];
            for (var i = 0; i < self.compClRecs.length; i++) {
                if (self.compClRecs[i].record.checked) {
                    Ids.push(self.compClRecs[i].record.id);
                }
            }
            SuperManagerService.cancelRecords(Ids)
                    .then(
                            function () {
//                                for (var i = 0; i < affIds.length; i++) {
//                                    for (var j = 0; j < self.newClaims.length; j++) {
//                                        if (self.newClaims[j].id === affIds[i]) {
//                                            self.newClaims.splice(j, 1);
//                                        }
//                                    }
//                                }
                                formClose('dialog-ban');
                                self.fetchAllRecords();
                                self.all = false;
                            },
//                            self.fetchNewClaims,
//                            self.fetchAffirmedClaims,
                            function (errResponse) {
                                console.error('Error while cancel Records.');
                            }
                    );
        };

        self.amountReady = function (header) {
            var k = 0;
            for (var j = 0; j < header.compositeClaimRecords.length; j++) {
                if (header.compositeClaimRecords[j].record.affirmator !== null && header.compositeClaimRecords[j].appointment.status === 'IN_PROGRESS') {
                    k = k + 1;
                }
            }
            return k;
        };

        self.amountCanceled = function (header) {
            var k = 0;
            for (var j = 0; j < header.compositeClaimRecords.length; j++) {
                if (header.compositeClaimRecords[j].appointment.status === 'CANCELED_BY_USER' ||
                        header.compositeClaimRecords[j].appointment.status === 'CANCELED_BY_PLANNER' ||
                        header.compositeClaimRecords[j].appointment.status === 'CANCELED_BY_SUPERMANAGER' ||
                        header.compositeClaimRecords[j].appointment.status === 'CANCELED_BY_DISPATCHER') {
                    k = k + 1;
                }
            }
            return k;
        };

        self.selectStatusIco = function (stat) {
            var ready = 'fas fa-lg fa-clipboard-check';
            var canceled = 'fas fa-lg fa-ban';
            switch (stat) {
                case 'READY':
                    return ready;
                case 'CANCELED_BY_USER':
                    return canceled;
                case 'CANCELED_BY_PLANNER':
                    return canceled;
                case 'CANCELED_BY_DISPATCHER':
                    return canceled;
                case 'CANCELED_BY_SUPERMANAGER':
                    return canceled;
            }
        };

        self.selectStatusColor = function (stat) {
            var ready = 'status-ready';
            var canceled = 'cancel-status';
            switch (stat) {
                case 'READY':
                    return ready;
                case 'CANCELED_BY_USER':
                    return canceled;
                case 'CANCELED_BY_PLANNER':
                    return canceled;
                case 'CANCELED_BY_DISPATCHER':
                    return canceled;
                case 'CANCELED_BY_SUPERMANAGER':
                    return canceled;
            }
        };

        self.apptStatus = function (stat) {
            var inProgress = 'Обрабатывается';
            var ready = 'Готово';
            var completed = 'Завершено';
            var canceledByUser = 'Отменено пользователем';
            var canceledByPlanner = 'Отменено планировщиком';
            var canceledByDispatcher = 'Отменено диспетчером';
            var canceledBySuperManager = 'Отменено управлением';
            //var stat = self.appt.status;
            switch (stat) {
                case 'IN_PROGRESS':
                    return inProgress;
                case 'READY':
                    return ready;
                case 'COMPLETED':
                    return completed;
                case 'CANCELED_BY_USER':
                    return canceledByUser;
                case 'CANCELED_BY_PLANNER':
                    return canceledByPlanner;
                case 'CANCELED_BY_DISPATCHER':
                    return canceledByDispatcher;
                case 'CANCELED_BY_SUPERMANAGER':
                    return canceledBySuperManager;
            }
        };


    }]);

