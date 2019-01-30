'use strict';

App.controller('DispatcherController', ['$scope', 'DispatcherService',
    function ($scope, DispatcherService) {
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
        self.clrec = {record: {}, claim: {}, appointment: {}};
        self.compClRec = {record: {}, claim: {}, appointment: {}};
        self.departments = [];
        self.headers = [];
        self.complHeaders = [];
        self.vehicles = [];
        self.drivers = [];
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
        self.tempAppoints = [];


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

        self.fetchAllPlanRecords = function () {
            self.all = true;
            self.today = false;
            self.week = false;
            DispatcherService.fetchAllPlanRecords()
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
            DispatcherService.fetchWeekPlanRecords()
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
            DispatcherService.fetchTomorrowPlanRecords()
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
            DispatcherService.fetchDatePlanRecords(datePlan)
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


        self.fetchAllVehicleModels = function () {
            DispatcherService.fetchAllVehicleModels()
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

        self.fetchDrivers = function () {
            DispatcherService.fetchDrivers()
                    .then(
                            function (d) {
                                self.drivers = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Drivers');
                            }
                    );
        };

        self.fetchVehicles = function () {
            DispatcherService.fetchVehicles()
                    .then(
                            function (d) {
                                self.vehicles = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Vehicles');
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
        self.fetchAllVehicleModels();
        self.fetchDrivers();
        self.fetchVehicles();
        self.getToday();
        self.fetchVehicles();
        self.fetchDrivers();

        self.departFromObj = function (obj) {
            self.departments = obj.departments;
            console.log(self.departments);
        };



//        self.createAppointment = function () {
//            var appoints = [];
//            var appointment = {};
//            console.log(self.headers.length);
//            console.log(self.headers[0].compositeClaimRecords.length);
//            for (var i = 0; i < self.headers.length; i++) {
//                if (self.headers[i].compositeClaimRecords.length > 0) {
//                    for (var j = 0; j < self.headers[i].compositeClaimRecords.length; j++) {
//                        appointment = {id: null, creationDate: '', status: '', transportDep: null, vehicleModel: null, vehicle: null, driver: null};
//                        if (self.headers[i].compositeClaimRecords[j].appointment !== undefined && self.headers[i].compositeClaimRecords[j].appointment.transportDep !== null) {
//                            appointment.vehicleModel = self.headers[i].compositeClaimRecords[j].appointment.vehicleModel;
//                            appointment.transportDep = self.headers[i].compositeClaimRecords[j].appointment.transportDep;
//                            appointment.status = 'IN_PROGRESS';
//                            appoints.push({
//                                recordId: self.headers[i].compositeClaimRecords[j].record.id,
//                                appointment: appointment
//                            });
//                        }
//                    }
//                }
//            }
//            console.log(appoints);
//            PlannerService.createAppointment(appoints)
//                    .then(
//                            function (d) {
//                                self.fetchAllCompletePlanRecords();
//                            },
//                            function (errResponse) {
//                                console.error('Error while creating Appointment.');
//                            }
//                    );
//        };

        self.updateAppointment = function () {
            console.log(self.tempAppoints);
            DispatcherService.updateAppointment(self.tempAppoints)
                    .then(
                            function (d) {
                                self.tempAppoints = [];
                                if (self.all) {
                                    self.fetchAllPlanRecords();
                                    return;
                                }
                                if (self.week) {
                                    self.fetchWeekPlanRecords();
                                    return;
                                }
                                if (self.today) {
                                    self.fetchTomorrowPlanRecords();
                                    return;
                                }
                                self.fetchDatePlanRecords();
                            },
                            function (errResponse) {
                                console.error('Error while updating Appointment.');
                            }
                    );
        };

        self.prepareToUpdateAppointment = function () {
            for (var i = 0; i < self.tempAppoints.length; i++) {
                if (self.tempAppoints.length > 0) {
                    if (self.tempAppoints[i].id === self.clrec.appointment.id) {
                        self.tempAppoints.splice(i, 1);
                        console.log('splice appointment');
                    }
                }
            }
            self.tempAppoints.push(self.clrec.appointment);
            formClose('formAppointment');
        };

        self.rowClick = function (dep) {
            if (dep.isVisible === undefined) {
                dep.isVisible = true;
            } else {
                dep.isVisible = !dep.isVisible;
            }
        };

        self.resetAppForm = function () {
            formClose('formAppointment');
        };

        self.appoint = function (clrec) {
            self.clrec = clrec;
            formOpen('formAppointment');
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
            var inProgress = 'fas fa-clock';
            var ready = 'fas fa-check';
            var completed = 'fas fa-check-double';
            var canceled = 'fas fa-ban';
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
            var inProgress = 'done_status';
            var ready = 'done_status';
            var completed = 'status_ready';
            var canceled = 'cancel_status';
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

        self.statusClass = function (appointment) {
            var status = appointment.status;
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

    }]);


