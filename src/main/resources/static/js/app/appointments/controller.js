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
        self.vacantDrivers = [];
        self.vacantVehicles = [];
        self.claims = [];
        self.records = [];
        self.appointments = [];
        self.transportDeps = [];
        self.vehicleModels = [];
        self.today = false;
        self.week = false;
        self.all = false;
        self.archive = false;
        self.ctoday = false;
        self.cweek = false;
        self.call = false;
        self.selectedIcon;
        self.type;
        self.date;
        self.tempAppoints = [];
        self.filteredVacantVehicles = [];
        self.filteredVehicleModels = [];
        self.tempStatus;
        self.tempNote;
        self.tempVehicle;
        self.tempDriver;
        self.tempVehicleModel;
        self.pageCount;
        self.numRecordsPerPage = 15;
        self.data;
        self.listRecordsPerPage = [];
        self.n;
        self.n1;


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

        self.fetchMonthPlanRecords = function () {
            self.all = true;
            self.today = false;
            self.week = false;
            self.archive = false;
            formOpen('cover-trsp1');
            formOpen('preloader');
            DispatcherService.fetchMonthPlanRecords()
                    .then(
                            function (d) {
                                self.data = d;
                                self.headers = [];
                                formClose('cover-trsp1');
                                formClose('preloader');
                                self.pageCount = Math.ceil(d.length / self.numRecordsPerPage);
                                self.createListPages();
                                self.showRecordsPage(1);
                            },
                            function (errResponse) {
                                console.error('Error while fetching MonthRecords');
                            }
                    );
        };

        self.fetchMonthBeforePlanRecords = function () {
            self.all = false;
            self.today = false;
            self.week = false;
            self.archive = true;
            formOpen('cover-trsp1');
            formOpen('preloader');
            DispatcherService.fetchMonthBeforeRecords()
                    .then(
                            function (d) {
                                self.data = d;
                                self.headers = [];
                                formClose('cover-trsp1');
                                formClose('preloader');
                                self.pageCount = Math.ceil(d.length / self.numRecordsPerPage);
                                self.createListPages();
                                self.showRecordsPage(1);
                                // self.records = d.records;
                            },
                            function (errResponse) {
                                console.error('Error while fetching MonthBeforeRecords');
                            }
                    );
        };

        self.fetchWeekPlanRecords = function () {
            self.all = false;
            self.today = false;
            self.week = true;
            self.archive = false;
            formOpen('cover-trsp1');
            formOpen('preloader');
            DispatcherService.fetchWeekPlanRecords()
                    .then(
                            function (d) {
                                self.data = d;
                                self.headers = [];
                                formClose('cover-trsp1');
                                formClose('preloader');
                                self.pageCount = Math.ceil(d.length / self.numRecordsPerPage);
                                self.createListPages();
                                self.showRecordsPage(1);
                                // self.records = d.records;
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
            self.archive = false;
            DispatcherService.fetchTomorrowPlanRecords()
                    .then(
                            function (d) {
                                self.data = d;
                                self.headers = [];
                                self.pageCount = Math.ceil(d.length / self.numRecordsPerPage);
                                self.createListPages();
                                self.showRecordsPage(1);
                                // self.records = d.records;
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
            self.archive = false;
            self.changeDate();
            var datePlan = new Date(document.getElementById('date-plan').value);
            console.log(datePlan);
            DispatcherService.fetchDatePlanRecords(datePlan)
                    .then(
                            function (d) {
                                self.data = d;
                                self.headers = [];
                                self.pageCount = Math.ceil(d.length / self.numRecordsPerPage);
                                self.createListPages();
                                self.showRecordsPage(1);
                                // self.records = d.records;
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

        self.fetchVacantDrivers = function (app) {
            DispatcherService.fetchVacantDrivers(app)
                    .then(
                            function (d) {
                                self.vacantDrivers = d;
//                                if (self.tempDrivers !== null) {
//                                    self.vacantDrivers = d;
//                                    if (self.vacantDrivers !== null) {
//                                        for (var i = 0; i < self.tempDrivers.length; i++) {
//                                            if (self.tempDrivers[i] in d) {
//                                                self.vacantDrivers.splice(i, 1);
//                                            }
//                                        }
//                                    }
//                                } else {
//                                    self.vacantDrivers = d;
//                                }
                            },
                            function (errResponse) {
                                console.error('Error while fetching Vacant Drivers');
                            }
                    );
        };

        self.fetchVehicles = function () {
            DispatcherService.fetchVehicles()
                    .then(
                            function (d) {
                                self.vehicles = d;
                                self.filteringVehicles(self.clrec.appointment.vehicleModel);
                            },
                            function (errResponse) {
                                console.error('Error while fetching Vehicles');
                            }
                    );
        };

        self.fetchVacantVehicles = function (app) {
            DispatcherService.fetchVacantVehicles(app)
                    .then(
                            function (d) {
                                self.vacantVehicles = d;
//                                 if (self.tempVehicles !== null) {
//                                    self.vacantVehicles = d;
//                                    if (self.vacantVehicles !== null) {
//                                        for (var i = 0; i < self.tempVehicles.length; i++) {
//                                            if (self.tempVehicles[i] in d) {
//                                                self.vacantVehicles.splice(i, 1);
//                                            }
//                                        }
//                                    }
//                                } else {
//                                    self.vacantVehicles = d;
//                                }

                                self.filteringVehicles(self.clrec.appointment.vehicleModel);
                            },
                            function (errResponse) {
                                console.error('Error while fetching Vacant Vehicles');
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

        self.createListPages = function () {
            for (var i = 1; i < self.pageCount + 1; i++) {
                self.listRecordsPerPage[i - 1] = i;
            }
        };

        self.fetchTomorrowPlanRecords();
        self.fetchAllVehicleModels();
        self.fetchDrivers();
        self.fetchVehicles();
        self.getToday();
        self.fetchVehicles();
        self.fetchDrivers();
        self.createListPages();


        self.departFromObj = function (obj) {
            self.departments = obj.departments;
        };

        self.updateAppointments = function () {
            DispatcherService.updateAppointments(self.tempAppoints)
                    .then(
                            function (d) {
                                self.tempAppoints = [];
                                if (self.all) {
                                    self.fetchMonthPlanRecords();
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
                                if (self.archive) {
                                    self.fetchMonthBeforePlanRecords();
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
//            self.tempDrivers.push(self.clrec.appointment.driver);
//            self.tempVehicle.push(self.clrec.appointment.vehicle);
            if ((self.clrec.appointment.vehicle === null) || (self.clrec.appointment.driver === null)) {
                alert('Не заполнены данные');
            } else {
                self.tempVehicleModel = null;
                self.tempDriver = null;
                self.tempVehicle = null;
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
                formClose('cover-trsp1');
            }
        };

        self.prepareToChangeStatus = function (clrec) {
            self.clrec = clrec;
            //temporary save the current status of the claim to return it after canceling changing.
            self.tempStatus = clrec.appointment.status;
            self.tempNote = clrec.appointment.note;
            formOpen('cover-trsp1');
            formOpen('formChangeStatus');
        };

        self.changeAppStatus = function () {
            self.tempStatus = null;
            self.tempNote = null;
            formClose('formChangeStatus');
            formClose('cover-trsp1');
            DispatcherService.updateStatusAppointment(self.clrec.appointment)
                    .then(
                            function (d) {
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
                                if (self.archive) {
                                    self.fetchMonthBeforePlanRecords();
                                    return;
                                }
                                self.fetchDatePlanRecords();
                            },
                            function (errResponse) {
                                console.error('Error while changing status Appointment.');
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

        self.resetAppForm = function () {
            //returning the driver and vehicle of the appointment to its original state
            self.clrec.appointment.vehicleModel = self.tempVehicleModel;
            self.clrec.appointment.driver = self.tempDriver;
            self.clrec.appointment.vehicle = self.tempVehicle;
            formClose('formAppointment');
            formClose('cover-trsp1');
        };

        self.appoint = function (clrec) {
            self.clrec = clrec;
            //temporary save the current driver and vihicle of the appointment to return it after canceling changing.
            self.tempVehicleModel = clrec.appointment.vehicleModel;
            self.tempDriver = clrec.appointment.driver;
            self.tempVehicle = clrec.appointment.vehicle;

            self.fetchVacantDrivers(self.clrec.appointment);
            self.fetchVacantVehicles(self.clrec.appointment);
            self.filteringVehicleModels(self.clrec.claim.vehicleType);
            formOpen('formAppointment');
            formOpen('cover-trsp1');
        };

        self.downloadWaybill = function (appointment) {

            window.open("/transportation/dispatcher/waybilldownload/" + appointment.id, "_self");
            //self.appointment = appointment;
            /*  DispatcherService.downloadWaybill(appointment)
             .then(
             function (response) {
             return response.data;
             },
             function (errResponse) {
             console.error('Error while download waybill');
             }
             );*/
        };

        self.moreInfoOpen = function (clrec) {
            self.clrec = clrec;
            formOpen('more-claim1');
            formOpen('cover-trsp1');
        };

        self.closeInfo = function () {
            formClose('more-claim1');
            formClose('cover-trsp1');
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
            var canceledBySupermanager = 'Отменено управлением';
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
                    return canceledBySupermanager;
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
                case 'CANCELED_BY_SUPERMANAGER':
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
                case 'CANCELED_BY_SUPERMANAGER':
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

        self.filteringVehicles = function (model) {
            self.filteredVacantVehicles = [];
            for (var i = 0; i < self.vacantVehicles.length; i++) {
                if (self.vacantVehicles[i].model !== null && self.vacantVehicles[i].model !== undefined && model !== undefined && model !== null) {
                    if (self.vacantVehicles[i].model.modelName === model.modelName) {
                        self.filteredVacantVehicles.push(self.vacantVehicles[i]);
                    }
                }
            }
        };

        self.filteringVehicleModels = function (vehicleType) {
            self.filteredVehicleModels = [];
            if (vehicleType !== undefined && vehicleType !== null) {
                for (var i = 0; i < self.vehicleModels.length; i++) {
                    if (self.vehicleModels[i].vehicleType !== null && self.vehicleModels[i].vehicleType !== undefined) {
                        if (self.vehicleModels[i].vehicleType.specialization === vehicleType.specialization) {
                            self.filteredVehicleModels.push(self.vehicleModels[i]);
                        }
                    }
                }
            } else {
                console.log('В заявке не указан тип автотранспорта')
                self.filteredVehicleModels = self.vehicleModels;
            }
        };

        self.clearChangeStatusForm = function () {
            //returning the status of the claim to its original state
            self.clrec.appointment.status = self.tempStatus;
            self.clrec.appointment.note = self.tempNote;
            formClose('formChangeStatus');
            formClose('cover-trsp1');
        };

        self.showRecordsPage = function (page) {
            if ((page > 0) && (page < (self.pageCount + 1))) {
                self.headers = [];
                var k = 0;
                self.n = self.pageCount;
                self.n1 = page;
                for (var i = (page * self.numRecordsPerPage) - (self.numRecordsPerPage); i < (page * self.numRecordsPerPage); i++) {
                    if (self.data[i] !== undefined) {
                        self.headers[k] = self.data[i];
                        k++;
                    }
                }
            }
        };




    }]);


