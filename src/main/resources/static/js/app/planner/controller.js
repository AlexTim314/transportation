'use strict';

App.controller('PlannerController', ['$scope', 'PlannerService',
    function ($scope, PlannerService) {
        var self = this;
        self.department = {id: null, shortname: '', fullname: '', address: '', phone: '', claims: []};
        self.claim = {id: null, templateName: null, specialization: null, carBoss: null, purpose: null, creationDate: null, affirmationDate: null, actual: true, vehicleType: null, routeTasks: []};
        self.routeTemplate = {id: null, name: '', department: null, routeTasks: []};
        self.routeTask = {id: null, workName: '', orderNum: '', place: null, routeTemplate: null};
        self.temporaryRTask = {id: null, workName: '', orderNum: '', place: null, routeTemplate: null};
        self.place = {id: null, name: '', address: ''};
        self.record = {id: null, startDate: '', endDate: '', entranceDate: '', claim: {}, appointments: []};
        self.appointment = {id: null, creationDate: '', status: '', transportDep: {}, vehicleModel: {}, vehicle: {}, driver: {}};
        self.vehicleModel = {id: null, modelName: '', vehicleType: {}};
        self.vehicle = {id: null, number: ''};
        self.driver = {id: null, firstname: '', name: '', surname: '', phone: ''};
        self.vehicleType = {id: null, typeName: '', specialization: ''};
        self.clrec = {record: {}, claim: {}};
        self.compClRec = {record: {}, claim: {}, appointment: {}};
        self.departments = [];
        self.headers = [];
        self.complHeaders = [];
        self.claims = [];
        self.records = [];
        self.appointments = [];
        self.transportDeps = [];
        self.vehicleModels = [];
        self.places = [];
        self.today = false;
        self.week = false;
        self.all = false;
        self.ctoday = false;
        self.cweek = false;
        self.call = false;
        self.cancelNote = '';
        self.selectedIcon;
        self.type;
        self.date;
        self.inProgress;
        self.canceled;
        self.ready;
        self.workName = null;
        self.tIds = 0;
        var expandedHeaders = [];

        self.fetchAllDepartments = function () {
            console.log('fetchDep');
            PlannerService.fetchAllDepartments()
                    .then(
                            function (d) {
                                self.departments = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Departments');
                            }
                    );
        };

        self.selectIcon = function (spec) {
            var bus = 'fas fa-lg fa-bus-alt';
            var car = 'fas fa-lg fa-car';
            var truck = 'fas fa-lg fa-truck';
            var tractor = 'fas fa-lg fa-tractor';
            switch (spec) {
                case "пассажирский":
                    return bus;
                case "легковой":
                    return car;
                case "грузовой":
                    return truck;
                case "спецтехника":
                    return tractor;
            }
        };

        self.fetchAllAppointments = function () {
            PlannerService.fetchAllAppointments()
                    .then(
                            function (d) {
                                self.appointments = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Appointments');
                            }
                    );
        };

        self.fetchAllClaims = function () {
            PlannerService.fetchAllClaims()
                    .then(
                            function (d) {
                                self.claims = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Claims');
                            }
                    );
        };

        self.fetchAllPlanRecords = function () {
            self.all = true;
            self.today = false;
            self.week = false;
            PlannerService.fetchAllPlanRecords()
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

        self.fetchWeekPlanRecords = function () {
            self.all = false;
            self.today = false;
            self.week = true;
            PlannerService.fetchWeekPlanRecords()
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

        self.fetchTomorrowPlanRecords = function () {
            self.all = false;
            self.today = true;
            self.week = false;
            PlannerService.fetchTomorrowPlanRecords()
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

        self.fetchDatePlanRecords = function () {
            self.all = false;
            self.today = false;
            self.week = false;
            self.changeDate();
            var datePlan = new Date(document.getElementById('date-plan').value);
            PlannerService.fetchDatePlanRecords(datePlan)
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


        self.fetchAllCompletePlanRecords = function () {
            self.call = true;
            self.ctoday = false;
            self.cweek = false;
            PlannerService.fetchAllCompletePlanRecords()
                    .then(
                            function (d) {
                                self.complHeaders = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching AllCompleteRecords');
                            }
                    );
        };

        self.fetchWeekCompletePlanRecords = function () {
            self.call = false;
            self.ctoday = false;
            self.cweek = true;
            PlannerService.fetchWeekCompletePlanRecords()
                    .then(
                            function (d) {
                                self.complHeaders = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching CompleteWeekRecords');
                            }
                    );
        };

        self.fetchTomorrowCompletePlanRecords = function () {
            self.call = false;
            self.ctoday = true;
            self.cweek = false;
            PlannerService.fetchTomorrowCompletePlanRecords()
                    .then(
                            function (d) {
                                self.complHeaders = d;
                                // self.records = d.records;
                            },
                            function (errResponse) {
                                console.error('Error while fetching CompleteTodayRecords');
                            }
                    );
        };

        self.fetchDateCompletePlanRecords = function () {
            self.call = false;
            self.ctoday = false;
            self.cweek = false;
            var datePlan = new Date(document.getElementById('compl-date-plan').value);
            PlannerService.fetchDateCompletePlanRecords(datePlan)
                    .then(
                            function (d) {
                                self.complHeaders = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching CompleteRecords of Day');
                            }
                    );
        };

        self.fetchTransportDeps = function () {
            PlannerService.fetchTransportDeps()
                    .then(
                            function (d) {
                                self.transportDeps = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching TransportDeps');
                            }
                    );
        };

        self.fetchAllVehicleModels = function () {
            PlannerService.fetchAllVehicleModels()
                    .then(
                            function (d) {
                                self.vehicleModels = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching VehicleModels');
                            }
                    );
        };

        self.fetchPlaces = function () {
            PlannerService.fetchPlaces()
                    .then(
                            function (d) {
                                self.places = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Places');
                            }
                    );
        };

        self.downloadPlan = function () {
            var datePlan = new Date(document.getElementById('compl-date-plan').value);            
            var day = datePlan.getDate();
            var month = datePlan.getMonth() + 1;
            var year = datePlan.getFullYear();
            
            if (month < 10)
                month = "0" + month;
            if (day < 10)
                day = "0" + day;
            /*var hour = datePlan.getHours();
            var minute = datePlan.getMinutes();            
            var seconds = datePlan.getSeconds();
            if (hour < 10)
                hour = "0" + hour;
            if (minute < 10)
                minute = "0" + minute;
            if (seconds < 10)
                seconds = "0" + seconds;
            var strDate = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + seconds;
            */
            var strDate = year + "" + month + "" + day;
            window.open("/transportation/planner/plandownload/" + strDate, "_self");
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
            document.getElementById('date-plan').value = today;
            document.getElementById('compl-date-plan').value = today;
            self.date = day + "." + month + "." + year;
        };



        self.fetchTomorrowPlanRecords();
        self.fetchTomorrowCompletePlanRecords();
        self.fetchTransportDeps();
        self.fetchAllVehicleModels();
        self.getToday();

        self.departFromObj = function (obj) {
            self.departments = obj.departments;
        };

        self.createAppointments = function () {
            var appoints = [];
            var appointment = {};
            expandedHeaders = [];
            for (var i = 0; i < self.headers.length; i++) {
                if (self.headers[i].isVisible) {
                    expandedHeaders[i] = true;
                }
                for (var j = 0; j < self.headers[i].compositeClaimRecords.length; j++) {
                    var appointment = self.headers[i].compositeClaimRecords[j].appointment;
                    var recId = self.headers[i].compositeClaimRecords[j].record.id;
                    if (appointment.status === 'CANCELED_BY_USER' || appointment.status === 'CANCELED_BY_PLANNER') {
                        continue;
                    }
                    if (appointment.status === 'CANCELED_BY_DISPATCHER' || appointment.vehicleModel !== null && appointment.transportDep !== null && appointment.id === null) {
                        appoints.push({
                            recordId: recId,
                            appointment: appointment
                        });
                    }
                }
            }
            PlannerService.createAppointments(appoints)
                    .then(
                            function (d) {
                                if (self.all) {
                                    self.fetchAllPlanRecords();
                                    self.fetchAllCompletePlanRecords();
                                    return;
                                }
                                if (self.week) {
                                    self.fetchWeekPlanRecords();
                                    self.fetchWeekCompletePlanRecords();
                                    return;
                                }
                                if (self.today) {
                                    self.fetchTomorrowPlanRecords();
                                    self.fetchTomorrowCompletePlanRecords();
                                    return;
                                }
                                self.fetchDatePlanRecords();
                                self.fetchDateCompletePlanRecords();
//                                open_tab1('tab-list2', 'tab-btn2', 2);
                            },
                            function (errResponse) {
                                console.error('Error while creating Appointment.');
                            }
                    );
        };

//        self.updateAppointment = function (appointment) {
//            PlannerService.updateAppointment(appointment)
//                    .then(
//                            self.fetchAllAppointments,
//                            function (errResponse) {
//                                console.error('Error while updating Appointment.');
//                            }
//                    );
//        };

        self.deleteAppointment = function (appointment) {
            PlannerService.deleteAppointment(appointment)
                    .then(
                            self.fetchAllAppointments,
                            function (errResponse) {
                                console.error('Error while deleting Appointment.');
                            }
                    );
        };

        self.rowClick = function (dep) {
            if (dep.isVisible === undefined) {
                dep.isVisible = true;
            } else {
                dep.isVisible = !dep.isVisible;
            }
        };

        self.rowCompleteClick = function (dep) {
            if (dep.isVisible === undefined) {
                dep.isVisible = true;
            } else {
                dep.isVisible = !dep.isVisible;
            }
        };

        self.moreInfoOpen = function (clrec) {
            self.compClRec = clrec;
            formOpen('more-claim');
        };

        self.moreInfoAppointments = function (compClRec) {
            self.compClRec = compClRec;
            console.log(self.compClRec);
            formOpen('more-claim-status');
        };

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

        self.getFilter = function (clrec) {
            var vType = clrec.claim.vehicleType;
            var filter = {};
            if (vType !== null && vType !== undefined) {
                filter.vehicleType = {typeName: vType.typeName};
            } else {
                filter.vehicleType = {specialization: clrec.claim.specialization};
            }
            return filter;
        };

        self.selectStatus = function (stat) {
            var inProgress = 'Обрабатывается';
            var ready = 'Готово';
            var completed = 'Завершено';
            var canceledByUser = 'Отменено пользователем';
            var canceledByPlanner = 'Отменено планировщиком';
            var canceledByDispatcher = 'Отменено диспетчером';
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
            }
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
                case 'CANCELED_BY_USER':
                    return canceled;
                case 'CANCELED_BY_PLANNER':
                    return canceled;
                case 'CANCELED_BY_DISPATCHER':
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
                case 'CANCELED_BY_USER':
                    return canceled;
                case 'CANCELED_BY_PLANNER':
                    return canceled;
                case 'CANCELED_BY_DISPATCHER':
                    return canceled;
            }
        };

        self.statusClass = function (clrec) {
            var status = clrec.appointment.status;
            return self.selectStatusIco(status) + ' ' + self.selectStatusColor(status);
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

        self.disable = function (clrec) {
            var appointment = clrec.appointment;
            if (appointment.status !== 'CANCELED_BY_DISPATCHER' && appointment.id !== null) {
                return true;
            }
        };

        var expandHeaders = function () {
            for (var k in expandedHeaders) {
                self.headers[k].isVisible = expandedHeaders[k];
            }
        };

        self.appt = {};

        self.checkRec = function (clrec) {
            self.appt = clrec.appointment;
            formOpen('appt-note');
        };

        self.apptStatus = function () {
            var status = self.appt.status;
            return self.selectStatus(status);
        };

        self.prepareToCancel = function (rec) {
            self.record.id = rec.id;
            self.record.startDate = new Date(rec.startDate);
            self.record.entranceDate = new Date(rec.entranceDate);
            self.record.endDate = new Date(rec.endDate);
            self.record.appointments = rec.appointments;

            console.log(self.record);
            formOpen('formCancel');
        };

        self.cancelRecord = function () {
            var canceledApp = {};
            if (self.record.appointments.length !== 0) {
                var id = self.record.appointments[0].id;
                canceledApp = self.record.appointments[0];
                for (var i = 1; i < self.record.appointments.length; i++) {
                    if (id < self.record.appointments[i].id) {
                        id = self.record.appointments[i].id;
                        canceledApp = self.record.appointments[i];
                    }
                }
                canceledApp.status = 'CANCELED_BY_PLANNER';
                canceledApp.note = self.cancelNote;
            } else {
                canceledApp = {id: null, creationDate: '', status: '', note: ''};
                canceledApp.status = 'CANCELED_BY_PLANNER';
                canceledApp.note = self.cancelNote;
            }
            console.log(canceledApp);
            PlannerService.cancelRecord({recordId: self.record.id, appointment: canceledApp})
                    .then(
                            function (d) {
                                var rec = d;
                                var l = -1;
                                var k = -1;
                                for (var i = 0; i < self.headers.length; i++) {
                                    for (var j = 0; j < self.headers[i].compositeClaimRecords.length; j++) {
                                        if (self.headers[i].compositeClaimRecords[j].record.id === rec.id) {
                                            l = i;
                                            k = j;
                                            break;
                                        }
                                    }
                                    if (k >= 0) {
                                        break;
                                    }
                                }
                                self.headers[l].compositeClaimRecords[k].record = d;
                            },
                            function (errResponse) {
                                console.error('Error while canceled Record.');
                            }
                    );
        };

        self.amountReady = function (header) {
            var k = 0;
            for (var j = 0; j < header.compositeClaimRecords.length; j++) {
                if (header.compositeClaimRecords[j].appointment.status === 'READY') {
                    k = k + 1;
                }
            }
            return k;
        };

        self.amountInProgress = function (header) {
            var k = 0;
            for (var j = 0; j < header.compositeClaimRecords.length; j++) {
                if (header.compositeClaimRecords[j].appointment.status === 'IN_PROGRESS') {
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
                        header.compositeClaimRecords[j].appointment.status === 'CANCELED_BY_DISPATCHER') {
                    k = k + 1;
                }
            }
            return k;
        };

        self.correctingTime = function (rec) {
            console.log(rec);
            self.record = rec;
            self.record.startDate = new Date(rec.startDate);
            self.record.entranceDate = new Date(rec.entranceDate);
            self.record.endDate = new Date(rec.endDate);
            formOpen('correctTime');
        };

        self.correctingRoute = function (clm) {
            self.claim = clm;
            formOpen('formRoute');

        };

        self.updateTime = function () {
            console.log(self.record)
            PlannerService.updateTime(self.record)
                    .then(
                            function (d) {
                                var rec = d;
                                var l = -1;
                                var k = -1;
                                for (var i = 0; i < self.headers.length; i++) {
                                    for (var j = 0; j < self.headers[i].compositeClaimRecords.length; j++) {
                                        if (self.headers[i].compositeClaimRecords[j].record.id === rec.id) {
                                            l = i;
                                            k = j;
                                            break;
                                        }
                                    }
                                    if (k >= 0) {
                                        break;
                                    }
                                }
                                self.headers[l].compositeClaimRecords[k].record = d;
                            },
                            function (errResponse) {
                                console.error('Error while updating Time');
                            }
                    );
        };

        self.updateRoute = function () {
            PlannerService.updateRoute(clm)
                    .then(
                            function (errResponse) {
                                console.error('Error while updating Route.');
                            }
                    );
        };

//        self.resetRTaskForm = function () {
//            self.routeTask = {id: null, workName: '', orderNum: '', place: null, routeTemplate: null};
//        };
//        
//        self.submitRTask = function () {
//            if (self.routeTask.id === null && self.routeTask.orderNum === '') {
//                self.addRTask();
//            } else {
//                self.updateRTask();
//            }
//            self.resetRTaskForm();
//        };
//        
//        self.removeRTask = function (routeTask) {
//            var k = -1;
//            for (var i = 0; i < self.claim.routeTasks.length; i++) {
//                if (routeTask === self.claim.routeTasks[i]) {
//                    k = i;
//                    break;
//                }
//            }
//            self.claim.routeTasks.splice(k, 1);
//        };
//        
//        self.tryToUpdateRTask = function (routeTask) {
//            self.routeTask.id = routeTask.id;
//            self.routeTask.orderNum = routeTask.orderNum;
//            self.routeTask.workName = routeTask.workName;
//            self.routeTask.place = routeTask.place;
//            self.routeTask.routeTemplate = routeTask.routeTemplate;
//            self.temporaryRTask = routeTask;
//        };
//        
//        self.addRTask = function () {
//            var rt = {id: null};
//            rt.orderNum = self.claim.routeTasks.length;
//            rt.workName = self.routeTask.workName;
//            rt.place = self.routeTask.place;
//            rt.routeTemplate = self.routeTask.routeTemplate;
//            self.claim.routeTasks.push(rt);
//        };
//        
//        self.updateRTask = function () {
//            self.removeRTask(self.temporaryRTask);
//            var rt = {id: null};
//            rt.orderNum = self.routeTask.orderNum;
//            rt.workName = self.routeTask.workName;
//            rt.place = self.routeTask.place;
//            rt.routeTemplate = self.routeTask.routeTemplate;
//            self.claim.routeTasks.push(rt);
//        };

        self.addTask = function (p) {
            self.claim.routeTasks.push({id: null, place: p, workName: self.workName, tIds: self.tIds});
            self.tIds++;
            self.workName = null;
        };
        
        self.removeTask = function (tsk) {
            var k = -1;
            for (var i = 0; i < self.claim.routeTasks.length; i++) {
                if (tsk.tIds === self.claim.routeTasks[i].tIds) {
                    k = i;
                    break;
                }
            }
            self.claim.routeTasks.splice(k, 1);
        };
        
        self.submit = function () {
            if (self.routeTemplate.id === null) {
                self.createRouteTemplate();
            } else {
                self.updateRouteTemplate();
            }
        };
        
        self.resetForm = function () {
            self.workName = null;
            self.routeTemplate = {id: null, name: null, routeTasks: []};
            formClose('formRouteTemplate');
        };

    }]);
