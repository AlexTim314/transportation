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
        self.today = false;
        self.week = false;
        self.all = false;
        self.ctoday = false;
        self.cweek = false;
        self.call = false;
        self.selectedIcon;
        self.type;
        self.date;

        self.fetchAllDepartments = function () {
            console.log('fetchDep');
            PlannerService.fetchAllDepartments()
                    .then(
                            function (d) {
                                self.departments = d;
                                console.log(d);
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
                                console.log(d);
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
                                console.log(d);
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
                                // self.records = d.records;
                                console.log(self.headers);
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
                                // self.records = d.records;
                                console.log(self.headers);
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
                                // self.records = d.records;
                                console.log(self.headers);
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
            console.log(datePlan);
            PlannerService.fetchDatePlanRecords(datePlan)
                    .then(
                            function (d) {
                                self.headers = d;
                                // self.records = d.records;
                                console.log(self.headers);
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
                                console.log(self.complHeaders);
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

                                console.log(self.complHeaders);
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
                                console.log(self.complHeaders);
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
                                console.log(self.complHeaders);
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
                                console.log(d);
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
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching VehicleModels');
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
            document.getElementById('date-plan').value = today;
            self.date = day + "." + month + "." + year;
        };



        self.fetchAllPlanRecords();
        self.fetchAllCompletePlanRecords();
        self.fetchTransportDeps();
        self.fetchAllVehicleModels();
        self.getToday();

        self.departFromObj = function (obj) {
            self.departments = obj.departments;
            console.log(self.departments);
        };



        self.createAppointments = function () {
            var appoints = [];
            var appointment = {};
            console.log(self.headers.length);
            console.log(self.headers[0].compositeClaimRecords.length);
            for (var i = 0; i < self.headers.length; i++) {
                if (self.headers[i].compositeClaimRecords.length > 0) {
                    for (var j = 0; j < self.headers[i].compositeClaimRecords.length; j++) {
                        appointment = {id: null, creationDate: '', status: '', transportDep: null, vehicleModel: null, vehicle: null, driver: null};
                        if (self.headers[i].compositeClaimRecords[j].appointment !== undefined && self.headers[i].compositeClaimRecords[j].appointment.transportDep !== null) {
                            appointment.vehicleModel = self.headers[i].compositeClaimRecords[j].appointment.vehicleModel;
                            appointment.transportDep = self.headers[i].compositeClaimRecords[j].appointment.transportDep;
                            appointment.status = 'IN_PROGRESS';
                            appoints.push({
                                recordId: self.headers[i].compositeClaimRecords[j].record.id,
                                appointment: appointment
                            });
                        }
                    }
                }
            }
            console.log(appoints);
            PlannerService.createAppointments(appoints)
                    .then(
                            function (d) {
                                self.fetchAllPlanRecords();
                                self.fetchAllCompletePlanRecords();
//                                open_tab1('tab-list2', 'tab-btn2', 2);
                            },
                            function (errResponse) {
                                console.error('Error while creating Appointment.');
                            }
                    );
        };

        self.updateAppointment = function (appointment) {
            PlannerService.updateAppointment(appointment)
                    .then(
                            self.fetchAllAppointments,
                            function (errResponse) {
                                console.error('Error while updating Appointment.');
                            }
                    );
        };

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
            self.clrec = clrec;
            console.log(clrec);
            console.log(self.clrec);
            formOpen('more-claim');
        };

        self.moreInfoAppointments = function (compClRec) {
            self.compClRec = compClRec;
            console.log(compClRec);
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
            var canceled = 'Отменено';
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

        self.statusClass = function (clrec) {
            var status = clrec.appointment.status;
            return self.selectStatusIco(status) + ' ' + self.selectStatusColor(status);
        };

    }]);