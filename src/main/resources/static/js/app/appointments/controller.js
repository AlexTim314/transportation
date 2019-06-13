'use strict';
App.controller('DispatcherController', ['$scope', 'DispatcherService',
    function ($scope, DispatcherService) {
        var self = this;
        self.department = {id: null, shortname: '', fullname: '', address: '', phone: ''};
        self.claim = {id: null, templateName: null, specialization: null, carBoss: null, purpose: null, creationDate: null, affirmationDate: null, actual: true, vehicleType: null, routeTasks: []};
        self.routeTemplate = {id: null, name: '', department: null, routeTasks: []};
        self.addDispatcherClaim = {id: null, vehicleId: null, driverId: null, carBossId: null, purpose: '', dates: [], routeTasks: []};
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
        self.rec = {id: null, appointment: {}, carBoss: {}, purpose: '', routeTasks: [], startDate: '', endDate: '', entranceDate: ''};
        self.carBoss = {id: null};
        self.newRecord = {startDate: '', endDate: '', entranceDate: ''};
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
        self.numRecordsPerPage = 10;
        self.data = [];
        self.pager = {};
        self.startDate;
        self.vehicleTypes;
        self.places;
        self.routeTasks;
        self.cmpsts = [];
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
                                self.pageCount = Math.ceil(d.length / self.numRecordsPerPage);
                                self.setPage(1);
                                formClose('cover-trsp1');
                                formClose('preloader');
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
                                self.cmpsts = d;
                                self.pageCount = Math.ceil(d.length / self.numRecordsPerPage);
                                self.setPage(1);
                                formClose('cover-trsp1');
                                formClose('preloader');
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
                                self.pageCount = Math.ceil(d.length / self.numRecordsPerPage);
                                self.setPage(1);
                                formClose('cover-trsp1');
                                formClose('preloader');
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
            formOpen('cover-trsp1');
            formOpen('preloader');
            DispatcherService.fetchTomorrowPlanRecords()
                    .then(
                            function (d) {
                                self.data = d;
                                self.pageCount = Math.ceil(d.length / self.numRecordsPerPage);
                                self.setPage(1);
                                formClose('cover-trsp1');
                                formClose('preloader');
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
            formOpen('cover-trsp1');
            formOpen('preloader');
            DispatcherService.fetchDatePlanRecords(datePlan)
                    .then(
                            function (d) {
                                self.data = d;
                                formClose('cover-trsp1');
                                formClose('preloader');
                                self.pageCount = Math.ceil(d.length / self.numRecordsPerPage);
                                self.setPage(1);
                            },
                            function (errResponse) {
                                console.error('Error while fetching Records of Day');
                            }
                    );
        };
        self.fetchCarBosses = function () {
            DispatcherService.fetchCarBosses()
                    .then(
                            function (d) {
                                self.carBosses = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Bosses');
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
//=================pagination===========
        self.setPage = function (page) {
            if (page < 1 || page > self.pageCount) {
                //self.pager = {};
                self.headers = [];
                return;
            }
            // get pager object from service
            self.pager = self.getPager(self.data.length, page, self.numRecordsPerPage);
            // get current page of items
            // self.headers = self.data.slice(self.pager.startIndex, self.pager.endIndex + 1);
        };
        self.getPager = function (totalItems, currentPage, pageSize) {
            var totalPages = self.pageCount;
            var startPage, endPage;
            if (totalPages <= 10) {
                // less than 10 total pages so show all
                startPage = 1;
                endPage = totalPages;
            } else {
                // more than 10 total pages so calculate start and end pages
                if (currentPage <= 6) {
                    startPage = 1;
                    endPage = 10;
                } else if (currentPage + 4 >= totalPages) {
                    startPage = totalPages - 9;
                    endPage = totalPages;
                } else {
                    startPage = currentPage - 5;
                    endPage = currentPage + 4;
                }
            }
            // calculate start and end item indexes
            var startIndex = (currentPage - 1) * pageSize;
            var endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);
            var pages = [];
            var k = 0;
            // create an array of pages to ng-repeat in the pager control
            for (var i = startPage; i < endPage + 1; i++) {
                pages[k] = i;
                k++;
            }
            // return object with all pager properties required by the view
            return {
                totalItems: totalItems,
                currentPage: currentPage,
                pageSize: pageSize,
                totalPages: totalPages,
                startPage: startPage,
                endPage: endPage,
                startIndex: startIndex,
                endIndex: endIndex,
                pages: pages
            };
        };
//=====================
        self.fetchVehicleTypes = function () {
            DispatcherService.fetchVehicleTypes()
                    .then(
                            function (d) {
                                self.vehicleTypes = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching VehicleTypes');
                            }
                    );
        };
        self.fetchPlaces = function () {
            DispatcherService.fetchPlaces()
                    .then(
                            function (d) {
                                self.places = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Places');
                            }
                    );
        };
        self.fetchRouteTasks = function () {
            DispatcherService.fetchRouteTasks()
                    .then(
                            function (d) {
                                self.routeTasks = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching RouteTasks');
                            }
                    );
        };
        self.fetchDepartment = function () {
            DispatcherService.fetchDepartment()
                    .then(
                            function (d) {
                                self.department = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Department');
                            }
                    );
        };
        self.fetchRouteTemplates = function () {
            DispatcherService.fetchRouteTemplates()
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
        self.getDateFromServer = function () {
            DispatcherService.fetchDateFromServer()
                    .then(
                            function (d) {
                                var date = new Date(d);
                                var today = self.getFormatedDate(date, "-", true);
                                document.getElementById('date-plan').value = today;
                                document.getElementById('startDate').value = today;
                                document.getElementById('startDate').min = today;
                                // document.getElementById('entranceTime').value = "00:00";
                                document.getElementById('startTime').value = "00:00";
                                document.getElementById('endTime').value = "00:00";
                                self.startDate = new Date(today);
                                //   self.date = self.getFormatedDate(date, ".", false);

                                date.setDate(date.getDate() + 1);
                                // var tomorrow = self.getFormatedDate(date, "-", true);
                                // document.getElementById('compl-date-plan').value = tomorrow;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Date');
                            }
                    );
        };
        self.fetchTomorrowPlanRecords();
        self.fetchAllVehicleModels();
        self.fetchDrivers();
        self.fetchVehicles();
        self.getToday();
        self.fetchVehicleTypes();
        self.fetchCarBosses();
        self.fetchPlaces();
        self.fetchRouteTasks();
        self.fetchDepartment();
        self.getDateFromServer();
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
                console.log('В заявке не указан тип автотранспорта');
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
        self.submitClaim = function () {

            if (self.validateForm()) {
                alert("Форма заполнена не полностью!");
                return;
            } else {
                for (var i = 0; i < self.addDispatcherClaim.dates.length; i++) {
                    var rec = self.addDispatcherClaim.dates[i];
                    var sd = new Date(rec.startDate);
                    var ed = new Date(rec.endDate);
                    ed = rec.isOtherDay ? ed.setDate(sd.getDate() + 1) : ed.setDate(sd.getDate());
                    var entranceTime = new Date(rec.startDate.getTime() + 1800000);
                    entranceTime.setUTCHours(entranceTime.getHours());
                    var startTime = new Date(rec.startDate);
                    startTime.setUTCHours(startTime.getHours());
                    var endTime = new Date(rec.endDate);
                    endTime.setUTCHours(endTime.getHours());
                    self.addDispatcherClaim.dates[i].startDate = self.frmtDate(sd, startTime);
                    self.addDispatcherClaim.dates[i].entranceDate = self.frmtDate(sd, entranceTime);
                    self.addDispatcherClaim.dates[i].endDate = self.frmtDate(ed, endTime);
                }

                //   if (self.addDispatcherClaim.dates.id === null) {
                self.createClaim();
            }
            self.resetClaimForm();
        };
        self.validateForm = function () {
//            if (self.rec.startDate === null || self.rec.startDate === undefined
//                    || self.rec.endDate === null || self.rec.endDate === undefined) {
//                console.log('Missing Date or Time record!');
//                return true;
//            }

            if (self.addDispatcherClaim.dates.length === 0) {
                console.log('No records in claim!');
                return true;
            }
            for (var i = 0; i < self.addDispatcherClaim.dates.length; i++) {
                var r = self.addDispatcherClaim.dates[i];
                if (r.entranceDate === null || r.entranceDate === undefined
                        || r.endDate === null || r.endDate === undefined) {
                    console.log('Missing Date or Time record!');
                    return true;
                }
            }


            return false;
        };
        self.createClaim = function () {
            for (var i = 0; i < self.addDispatcherClaim.routeTasks.length; i++) {
                self.addDispatcherClaim.routeTasks[i].id = null;
                delete self.addDispatcherClaim.appointment;
                delete self.addDispatcherClaim.id;
            }
            formClose('form-add');
            formClose('cover-trsp1');
            DispatcherService.createClaim(self.addDispatcherClaim)
                    .then(
                            function (d) {
                                //self.addDispatcherClaims.push(d);
                                self.resetClaimForm();
                            },
                            function (errResponse) {
                                console.error('Error while creating Claim.');
                            }
                    );
        };
        self.resetClaimForm = function () {
            self.isOtherDay = false;
            self.addDispatcherClaim = {
                id: null,
                vehicleId: null,
                driverId: null,
                carBossId: null,
                purpose: '',
                dates: [],
                routeTasks: []};
            self.getDateFromServer();
            formClose('dispatcherCarBoss');
            formClose('formRoute');
            formClose('dispFormTask');
            formClose('form-add');
            formClose('cover-trsp1');
        };
        self.resetRTaskForm = function () {
            self.routeTask = {id: null, workName: '', orderNum: '', place: null, routeTemplate: null};
        };
        self.submitRTask = function () {
            if (self.routeTask.id === null && self.routeTask.orderNum === '') {
                self.addRTask();
            } else {
                self.updateRTask();
            }
            self.resetRTaskForm();
        };
        self.removeRTask = function (routeTask) {
            var k = -1;
            for (var i = 0; i < self.addDispatcherClaim.routeTasks.length; i++) {
                if (routeTask === self.addDispatcherClaim.routeTasks[i]) {
                    k = i;
                    break;
                }
            }
            self.addDispatcherClaim.routeTasks.splice(k, 1);
        };
        self.tryToUpdateRTask = function (routeTask) {
            self.routeTask.id = routeTask.id;
            self.routeTask.orderNum = routeTask.orderNum;
            self.routeTask.workName = routeTask.workName;
            self.routeTask.place = routeTask.place;
            self.routeTask.routeTemplate = routeTask.routeTemplate;
            self.temporaryRTask = routeTask;
        };
        self.addRTask = function () {
            var rt = {id: null};
            rt.orderNum = self.addDispatcherClaim.routeTasks.length;
            rt.workName = self.routeTask.workName;
            rt.place = self.routeTask.place;
            rt.routeTemplate = self.routeTask.routeTemplate;
            self.addDispatcherClaim.routeTasks.push(rt);
        };
        self.updateRTask = function () {
            self.removeRTask(self.temporaryRTask);
            var rt = {id: null};
            rt.orderNum = self.routeTask.orderNum;
            rt.workName = self.routeTask.workName;
            rt.place = self.routeTask.place;
            rt.routeTemplate = self.routeTask.routeTemplate;
            self.rec.routeTasks.push(rt);
        };
        self.resetCBForm = function () {
            self.carBoss = {id: null};
            formClose('dispatcherCarBoss');
        };
        self.submitCB = function () {
//            if (self.specDepartment.id === null) {
//                alert("Не выбрано подразделение!");
//                return;
//            }
            if (self.carBoss.id === null) {
                self.createCarBoss();
            } else {
                self.updateCarBoss();
            }
        };
        self.removeCB = function (carBoss) {
            DispatcherService.deleteCarBoss(carBoss)
                    .then(
                            function (d) {
                                var k = -1;
                                for (var i = 0; i < self.carBosses.length; i++) {
                                    if (carBoss === self.carBosses[i]) {
                                        k = i;
                                        break;
                                    }
                                }
                                self.carBosses.splice(k, 1);
                            },
                            function (errResponse) {
                                console.error('Error while deleting CarBoss.');
                            }
                    );
        };
        self.tryToUpdateCB = function (carBoss) {
            self.carBoss.id = carBoss.id;
            self.carBoss.firstname = carBoss.firstname;
            self.carBoss.name = carBoss.name;
            self.carBoss.surname = carBoss.surname;
            self.carBoss.post = carBoss.post;
            self.carBoss.birthday = new Date(carBoss.birthday);
            self.carBoss.address = carBoss.address;
            self.carBoss.phone = carBoss.phone;
            self.carBoss.department = carBoss.department;
            formOpen('dispatcherCarBoss');
        };
        self.createCarBoss = function () {
            self.carBoss.department = self.department;
            DispatcherService.createCarBoss(self.carBoss)
                    .then(
                            function (d) {
                                self.fetchCarBosses();
                                self.resetCBForm();
                            },
                            function (errResponse) {
                                console.error('Error while creating CarBoss.');
                            }
                    );
        };
        self.updateCarBoss = function () {
            DispatcherService.updateCarBoss(self.carBoss)
                    .then(
                            function (d) {
                                self.fetchCarBosses();
                                self.resetCBForm();
                            },
                            function (errResponse) {
                                console.error('Error while updating CarBoss.');
                            }
                    );
        };
        self.resetForm = function () {
            self.workName = null;
            self.routeTemplate = {id: null, name: null, routeTasks: []};
            formClose('dispFormTask');
        };
        self.carBossToString = function (boss) {
            if (boss !== null && boss !== undefined) {
                var result = boss.firstname + " " + boss.name.charAt(0) + "." + (boss.surname !== null && boss.surname !== null ? boss.surname.charAt(0) + "." : "") + " " + boss.post;
                return result;
            }
        };
        self.addRec = function () {
            var sd = new Date(self.startDate);
            if (sd === null) {
                return;
            }
            if (self.onWeek) {
                for (var i = 0; i < 5; i++) {
                    var rec = {};
                    rec.isOtherDay = self.newRecord.isOtherDay;
                    rec.startDate = self.frmtDate(sd, self.newRecord.startDate);
                    rec.startDate.setDate(rec.startDate.getDate() + i);
                    rec.endDate = self.frmtDate(sd, self.newRecord.endDate);
                    rec.endDate.setDate(rec.endDate.getDate() + i);
                    rec.entranceDate = self.frmtDate(sd, new Date(self.newRecord.startDate.getTime() + 1800000));
                    rec.entranceDate.setDate(rec.entranceDate.getDate() + i);
                    if (rec.startDate == 'Invalid Date' || rec.entranceDate == 'Invalid Date' || rec.endDate == 'Invalid Date') {
                        alert("Необходимо указать время подачи, выезда и возвращения!");
                        return;
                    }
                    self.addDispatcherClaim.dates.push(rec);
                }
            } else {
                var rec = {};
                rec.isOtherDay = self.newRecord.isOtherDay;
                rec.startDate = self.frmtDate(sd, self.newRecord.startDate);
                rec.endDate = self.frmtDate(sd, self.newRecord.endDate);
                rec.endDate.setDate(rec.endDate.getDate());
                rec.entranceDate = self.frmtDate(sd, new Date(self.newRecord.startDate.getTime() + 1800000));
                if (rec.startDate == 'Invalid Date' || rec.entranceDate == 'Invalid Date' || rec.endDate == 'Invalid Date') {
                    alert("Необходимо указать время подачи, выезда и возвращения!");
                    return;
                }
                self.addDispatcherClaim.dates.push(rec);
            }
//            self.rec.startDate = rec.startDate;
//            self.rec.endDate = rec.endDate;
//            self.rec.entranceDate = rec.entranceDate;
            //console.log(self.addDispatcherClaim.dates);
        };
//        self.removeRec = function () {
//            self.rec.splice(0, 1);
//        };

        self.removeRec = function (rec) {
            //удалить запись
            var k = -1;
            for (var i = 0; i < self.addDispatcherClaim.dates.length; i++) {
                if (rec === self.addDispatcherClaim.dates[i]) {
                    k = i;
                    break;
                }
            }
            self.addDispatcherClaim.dates.splice(k, 1);
        };
        self.frmtDate = function (date, time) {
            var result = new Date(new Date(date).getFullYear(), new Date(date).getMonth(), new Date(date).getDate(), new Date(time).getHours(), new Date(time).getMinutes(), new Date(time).getSeconds());
            return result;
        };
        self.getFormatedDate = function (date, separator, yearAtBeginning) {
            var day = date.getDate();
            var month = date.getMonth() + 1;
            var year = date.getFullYear();
            if (month < 10)
                month = "0" + month;
            if (day < 10)
                day = "0" + day;
            if (yearAtBeginning) {
                return year + separator + month + separator + day;
            }
            return day + separator + month + separator + year;
        };
        self.openAddClaimForm = function () {
            formOpen('form-add');
            formOpen('cover-trsp1');
            self.fetchRouteTemplates();
        };
        self.checkRecord = function (rec) {
            self.newRecord.checked = false;
            self.newRecord = rec;
            self.newRecord.checked = true;
        };
        self.closeAddingClaimForm = function () {
            menu_close();
            formClose('dispatcherCarBoss');
            formClose('formRoute');
            formClose('dispFormTask');
            formClose('form-add');
            formClose('cover-trsp1');
        };
        self.createDataComposite = function () {
            if (self.cmpsts.length > 0) {
                self.data = [];

                for (var i = 0; i < self.cmpsts.length; i++) {

                    for (var j = 0; j < self.vehicleTypes.length; j++) {
                        if (self.cmpsts[i].vehicleTypeId === self.vehicleTypes[j].id) {
                            var vehicleType = self.vehicleTypes[j];
                        }
                    }

                    for (var j = 0; j < self.vehicleModels.length; j++) {
                        if (self.cmpsts[i].vehicleModelId === self.vehicleModels[j].id) {
                            var vehicleModel = self.vehicleModels[j];
                        }
                    }
                    
                    for (var j = 0; j < self.drivers.length; j++) {
                        if (self.cmpsts[i].driverid === self.drivers[j].id){
                            var driver = self.drivers[j];
                        }
                    }
                    
                    for (var j = 0; j < self.drivers.length; j++) {
                        if (self.cmpsts[i].vehicleid === self.vehicles[j].id){
                            var vehicle = self.vehicles[j];
                        }
                    }
                    
                    self.data.push({
                        claim: {
                            id: self.cmpsts[i].claimid,
                            // templateName: self.cmpsts[i].claim,
                            specialization: self.cmpsts[i].vehicleTypeSpecialization,
                            purpose: self.cmpsts[i].purpose,
                            //creationDate: self.cmpsts[i].claim,
                            //affirmationDate: self.cmpsts[i].claim,
                            //actual: self.cmpsts[i].claim,
                            department: self.cmpsts[i].depshortname,
                            vehicleType: vehicleType,
                            carBoss: {
                                firstname: self.cmpsts[i].carbossfirstname,
                                name: self.cmpsts[i].carbossname,
                                surname: self.cmpsts[i].carbosssurname,
                                phone: self.cmpsts[i].carbossphone
                            }
                            //creator:	{},
                            //affirmator:	{},
                            //routeTasks:	route,
                        },
                        record: {
                            id: self.cmpsts[i].recordid,
                            startDate: self.cmpsts[i].startdate,
                            entranceDate: self.cmpsts[i].entrancedate,
                            endDate: self.cmpsts[i].enddate,
                            affirmator: {
                                id: null,
                                fullName: 'null'
                            },
                            tasks: self.cmpsts[i].route
                        },
                        appointment: {
                            id: self.cmpsts[i].appointmentid,
                            status: self.cmpsts[i].appstatus,
                            note: self.cmpsts[i].appnote,
                            //transportDep: {},
                            vehicleModel: vehicleModel,
                            vehicle: vehicle,
                            driver: driver,
                            creator: {},
                            modificator: {},
                            // creationDate:
                        }
                    });
                }
            } else {
                self.data = [];
            }
        };
    }]);


