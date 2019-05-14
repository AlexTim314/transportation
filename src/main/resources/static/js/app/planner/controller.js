'use strict';

App.controller('PlannerController', ['$scope', 'PlannerService',
    function ($scope, PlannerService) {
        var self = this;
        self.department = {id: null, shortname: '', fullname: '', address: '', phone: '', claims: []};
        self.specDepartment = {id: null, shortname: '', fullname: '', address: '', phone: ''};
        self.claim = {id: null, templateName: null, specialization: null, carBoss: null, purpose: null, creationDate: null, affirmationDate: null, actual: true, vehicleType: null, routeTasks: []};
        self.newClaim = {id: null, templateName: null, specialization: null, carBoss: null, purpose: null, creationDate: null, affirmationDate: null, actual: true, vehicleType: null, department: {}, records: [], routeTasks: [], };
        self.routeTemplate = {id: null, name: '', department: null, routeTasks: []};
        self.routeTask = {id: null, workName: '', orderNum: '', place: null, routeTemplate: null};
        self.temporaryRTask = {id: null, workName: '', orderNum: '', place: null, routeTemplate: null};
        self.place = {id: null, name: '', address: ''};
        self.record = {id: null, startDate: '', endDate: '', entranceDate: '', claim: {}, appointments: []};
        self.newRecord = {id: null, startDate: '', endDate: '', entranceDate: '', appointments: []};
        self.appointment = {id: null, creationDate: '', status: '', transportDep: {}, vehicleModel: {}, vehicle: {}, driver: {}};
        self.vehicleModel = {id: null, modelName: '', vehicleType: {}};
        self.vehicle = {id: null, number: ''};
        self.driver = {id: null, firstname: '', name: '', surname: '', phone: ''};
        self.vehicleType = {id: null, typeName: '', specialization: ''};
        self.clrec = {record: {}, claim: {}};
        self.compClRec = {record: {}, claim: {}, appointment: {}};
        self.carBoss = {id: null};
        self.otsInfo = {id: null, type1count: 0, type4count: 0, type2count: 0, drivercount: 0, type3count: 0, name: ''};
        self.compVMTDSpec = {shortname: '', modelname: '', vehiclemodelid: null, transportdepid: null, vehiclespecialization: ''};
        self.compVMTDSpecs = [];
        self.otsInfos = [];
        self.allCountDrivers = 0;
        self.carBosses = [];
        self.departments = [];
        self.specDepartments = [];
        self.headers = [];
        self.complHeaders = [];
        self.claims = [];
        self.newClaims = [];
        self.records = [];
        self.newRecords = [];
        self.appointments = [];
        self.transportDeps = [];
        self.vehicleModels = [];
        self.rTasks = [];
        self.complRTasks = [];
        self.places = [];
        self.today = false;
        self.week = false;
        self.all = false;
        self.archive = false;
        self.ctoday = false;
        self.cweek = false;
        self.call = false;
        self.carchive = false;
        self.bosses = [];
        self.onWeek = false;
        self.cancelNote = '';
        self.selectedIcon;
        self.type;
        self.date;
        self.inProgress;
        self.canceled;
        self.ready;
        self.workName = null;
        self.tIds = 0;
        self.checkedTskNumb = 0;
        var expandedHeaders = [];
        self.isOtherDay = false;
        self.isDelete = false;
        self.startDate;
        self.vehicles = [];
        self.filteredVehicleModels = [];
        self.selectedModel = {id: null, modelName: '', vehicleType: {}};
        self.permit = false;
        self.tempTransportDep = {};
        self.tempVehSpec = '';
        self.username;


        self.getPermit = function () {
            PlannerService.getPermit()
                    .then(
                            function (d) {
                                self.permit = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Permit');
                            }
                    );
        };

        self.getUserName = function () {
            PlannerService.getUserName()
                    .then(
                            function (d) {
                                self.username = d.username;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Username');
                            });
        };

        self.getPermit();
        self.getUserName();

        self.fetchAllSpecDepartments = function () {
            PlannerService.fetchAllDepartments()
                    .then(
                            function (d) {
                                self.specDepartments = d;
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

        self.selectSmallIcon = function (spec) {
            var bus = 'fas fa-bus-alt';
            var car = 'fas fa-car';
            var truck = 'fas fa-truck';
            var tractor = 'fas fa-tractor';
            switch (spec) {
                case 0:
                    return bus;
                case 1:
                    return car;
                case 2:
                    return truck;
                case 3:
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

        self.fetchMonthPlanRecords = function () {
            self.all = true;
            self.today = false;
            self.week = false;
            self.archive = false;
            formOpen('cover-trsp1');
            formOpen('preloader');
            PlannerService.fetchMonthPlanRecords()
                    .then(
                            function (d) {
                                self.headers = d;
                                expandHeaders();
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
            PlannerService.fetchMonthBeforePlanRecords()
                    .then(
                            function (d) {
                                self.headers = d;
                                expandHeaders();
                                formClose('cover-trsp1');
                                formClose('preloader');
                            },
                            function (errResponse) {
                                console.error('Error while fetching MonthBefRecords');
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
            PlannerService.fetchWeekPlanRecords()
                    .then(
                            function (d) {
                                self.headers = d;
                                expandHeaders();
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
            self.archive = false;
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


        self.fetchMonthCompletePlanRecords = function () {
            self.call = true;
            self.ctoday = false;
            self.cweek = false;
            self.carchive = false;
            formOpen('cover-trsp1');
            formOpen('preloader');
            PlannerService.fetchMonthCompletePlanRecords()
                    .then(
                            function (d) {
                                self.complHeaders = d;
                                formClose('cover-trsp1');
                                formClose('preloader');
                            },
                            function (errResponse) {
                                console.error('Error while fetching AllCompleteRecords');
                            }
                    );
        };

        self.fetchMonthBeforeCompleteRecords = function () {
            self.call = false;
            self.ctoday = false;
            self.cweek = false;
            self.carchive = true;
            formOpen('cover-trsp1');
            formOpen('preloader');
            PlannerService.fetchMonthBeforeCompletePlanRecords()
                    .then(
                            function (d) {
                                self.complHeaders = d;
                                formClose('cover-trsp1');
                                formClose('preloader');
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
            self.carchive = false;
            formOpen('cover-trsp1');
            formOpen('preloader');
            PlannerService.fetchWeekCompletePlanRecords()
                    .then(
                            function (d) {
                                self.complHeaders = d;
                                formClose('cover-trsp1');
                                formClose('preloader');
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
            self.carchive = false;
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
            self.carchive = false;
            self.changeDate();
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

        self.fetchCarBosses = function () {
            console.log(self.specDepartment);
            PlannerService.fetchCarBosses()
                    .then(
                            function (d) {
                                self.carBosses = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Bosses');
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

        self.fetchVehicles = function () {
            PlannerService.fetchVehicles()
                    .then(
                            function (d) {
                                self.vehicles = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Vehicles');
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

        self.fetchOtsInfo = function () {
            PlannerService.fetchOtsInfo()
                    .then(
                            function (d) {
                                self.otsInfos = d;
                                var k = 0;
                                for (var i = 0; i < self.otsInfos.length; i++) {
                                    k = k + self.otsInfos[i].otsInfo.drivercount;
                                }
                                self.allCountDrivers = k;
                            },
                            function (errResponse) {
                                console.error('Error while fetching OtsInfo');
                            }
                    );
        };


        self.fetchOtsVehModels = function () {
            PlannerService.fetchOtsVehModels()
                    .then(
                            function (d) {
                                self.compVMTDSpecs = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching compVMTDSpec');
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

        self.getDateFromServer = function () {
            PlannerService.fetchDateFromServer()
                    .then(
                            function (d) {
                                var date = new Date(d);
                                var today = self.getFormatedDate(date, "-", true);
                                document.getElementById('date-plan').value = today;
                                document.getElementById('startDate').value = today;
                                document.getElementById('startDate').min = today;
                                document.getElementById('entranceTime').value = "00:00";
                                document.getElementById('startTime').value = "00:00";
                                document.getElementById('endTime').value = "00:00";
                                self.startDate = new Date(today);
                                self.date = self.getFormatedDate(date, ".", false);

                                date.setDate(date.getDate() + 1);
                                var tomorrow = self.getFormatedDate(date, "-", true);
                                document.getElementById('compl-date-plan').value = tomorrow;
                            },
                            function (errResponse) {
                                console.error('Error while fetching NewClaims');
                            }
                    );
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

        /*  self.getToday = function () {
         var date = new Date();
         var today = self.getFormatedDate(date, "-", true);
         
         document.getElementById('date-plan').value = today;
         document.getElementById('startDate').value = today;
         document.getElementById('startDate').min = today;
         document.getElementById('entranceTime').value = "00:00";
         document.getElementById('startTime').value = "00:00";
         document.getElementById('endTime').value = "00:00";
         
         self.startDate = new Date(today);
         self.date = self.getFormatedDate(date, ".", false);
         
         date.setDate(date.getDate() + 1);
         var tomorrow = self.getFormatedDate(date, "-", true);
         document.getElementById('compl-date-plan').value = tomorrow;
         };
         */
        self.fetchVehicleTypes = function () {
            PlannerService.fetchVehicleTypes()
                    .then(
                            function (d) {
                                self.vehicleTypes = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching VehicleTypes');
                            }
                    );
        };


        self.fetchTomorrowPlanRecords();
        self.fetchTomorrowCompletePlanRecords();
        self.fetchTransportDeps();
        self.fetchAllVehicleModels();
        self.getDateFromServer();
        self.fetchCarBosses();
        self.fetchPlaces();
        self.fetchAllSpecDepartments();
        self.fetchVehicleTypes();
        self.fetchOtsInfo();
        self.fetchVehicles();
        self.fetchOtsVehModels();

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
                    if (appointment.status === 'CANCELED_BY_DISPATCHER' || appointment.transportDep !== null && appointment.id === null) {
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
                            },
                            function (errResponse) {
                                console.error('Error while creating Appointment.');
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

        self.clearBacklight = function (element) {
            var block = document.getElementById('bar-block1');
            var items = block.getElementsByClassName('info-bar-btn');
            var i;
            for (i = 0; i < items.length; i++) {
                if (element !== items[i]) {
                    items[i].classList.remove('subActive');
                }
            }
        };

        self.rowOtsInfoClick = function (ots, element) {
            self.clearBacklight(element);
            element.classList.toggle('subActive');
            for (var i = 0; i < self.otsInfos.length; i++) {
                if (self.otsInfos[i] !== ots) {
                    self.otsInfos[i].isVisible = false;
                }
            }
            if (ots.isVisible === undefined) {
                ots.isVisible = true;
            } else {
                ots.isVisible = !ots.isVisible;
            }
        };

        self.moreInfoOpen = function (clrec) {
            self.compClRec = clrec;
            formOpen('more-claim');
            formOpen('cover-trsp1');
        };

        self.closeInfo = function () {
            formClose('more-claim');
            formClose('cover-trsp1');
        };

        self.moreInfoAppointments = function (compClRec) {
            self.compClRec = compClRec;
            formOpen('cover-trsp1');
            formOpen('more-claim-status');

        };

        self.closeInfoAppointments = function () {
            formClose('more-claim-status')
            formClose('cover-trsp1');
        };

        self.changeDate = function () {
            var datePlan = new Date(document.getElementById('date-plan').value);
            var datePlan = new Date(document.getElementById('compl-date-plan').value);
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

        self.statusClass = function (clrec) {
            var status = clrec.appointment.status;
            return self.selectStatusIco(status) + ' ' + self.selectStatusColor(status);
        };

        self.personToString = function (person) {
            if (person === null) {
                return null;
            }
            var result = person.firstname + " " + person.name.charAt(0) + "." + (person.surname !== null && person.surname !== undefined ? person.surname.charAt(0) + "." : "");
            return result;
        };


        self.affirmatorToString = function (user) {
            if (user !== null) {
                var nameArr = user.fullName.split(' ');
                var result = nameArr[0];
                if (nameArr.length > 1) {
                    result += " " + nameArr[1].charAt(0) + ".";
                }
                if (nameArr.length > 2) {
                    result += nameArr[2].charAt(0) + ".";
                }
                return result;
            }
        };

        self.carBossToString = function (boss) {
            var result = boss.firstname + " " + boss.name.charAt(0) + "." + (boss.surname !== null && boss.surname !== null ? boss.surname.charAt(0) + "." : "") + " " + boss.post;
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
            PlannerService.updateRoute(self.claim)
                    .then(
                            function (d) {
                                var clm = d;
                                var l = -1;
                                var k = -1;
                                for (var i = 0; i < self.headers.length; i++) {
                                    for (var j = 0; j < self.headers[i].compositeClaimRecords.length; j++) {
                                        if (self.headers[i].compositeClaimRecords[j].claim.id === clm.id) {
                                            l = i;
                                            k = j;
                                            break;
                                        }
                                    }
                                    if (k >= 0) {
                                        break;
                                    }
                                }
                                self.headers[l].compositeClaimRecords[k].claim = d;
                            },
                            function (errResponse) {
                                console.error('Error while updating Claim.');
                            }
                    );
        };

        self.addTask = function (p) {
            for (var i = 0; i < self.claim.routeTasks.length; i++) {
                if (self.claim.routeTasks[i].orderNum > self.checkedTskNumb) {
                    self.claim.routeTasks[i].orderNum = self.claim.routeTasks[i].orderNum + 1;
                }
            }
            self.claim.routeTasks.push({id: null, place: p, workName: self.workName, orderNum: self.checkedTskNumb + 1});
            self.workName = null;
        };

        self.removeTask = function (tsk) {
            var k = -1;
            for (var i = 0; i < self.claim.routeTasks.length; i++) {
                if (tsk.orderNum === self.claim.routeTasks[i].orderNum) {
                    k = i;
                    break;
                }
            }
            self.claim.routeTasks.splice(k, 1);
        };

        self.submit = function () {
            for (var i = 0; i < self.claim.routeTasks.length; i++) {
                self.complRTasks.push(self.claim.routeTasks[i]);
                for (var j = 0; j < self.rTasks.length; j++) {
                    if (self.claim.routeTasks[i].orderNum === self.rTasks[j].orderNum) {
                        self.complRTasks.push(self.rTasks[j]);
                    }
                }
            }
            self.updateRoute();
            formClose('formRoute');
        };

        self.resetForm = function () {
            self.workName = null;
            self.routeTemplate = {id: null, name: null, routeTasks: []};
            formClose('formRoute');
        };

        self.checkTsk = function (tsk) {
            self.checkedTskNumb = tsk.orderNum;
            for (var i = 0; i < self.claim.routeTasks.length; i++) {
                if (self.claim.routeTasks[i].checked) {
                    self.claim.routeTasks[i].checked = false;
                    break;
                }
            }
            for (var j = 0; j < self.claim.routeTasks.length; j++) {
                if (self.claim.routeTasks[j] === tsk) {
                    self.claim.routeTasks[j].checked = true;
                    self.claim.routeTasks[j] = tsk;
                    break;
                }
            }
        };


        self.checkRecord = function (rec) {
            self.newRecord.checked = false;
            self.newRecord = rec;
            self.newRecord.checked = true;
        };


        self.addRec = function () {
            var sd = new Date(self.startDate);

            if (sd === null) {
                return;
            }
            var inc = self.isOtherDay ? 1 : 0;

            var entranceTime = new Date(self.newRecord.entranceDate);
            //    entranceTime.setUTCHours(entranceTime.getHours());
            var startTime = new Date(self.newRecord.startDate);
            //  startTime.setUTCHours(startTime.getHours());
            var endTime = new Date(self.newRecord.endDate);
            //endTime.setUTCHours(endTime.getHours());

            if (self.onWeek) {
                for (var i = 0; i < 5; i++) {
                    var rec = {id: null};
                    rec.startDate = self.frmtDate(sd, startTime);
                    rec.startDate.setDate(rec.startDate.getDate() + i);
                    rec.endDate = self.frmtDate(sd, endTime);
                    rec.endDate.setDate(rec.endDate.getDate() + i + inc);
                    rec.entranceDate = self.frmtDate(sd, entranceTime);
                    rec.entranceDate.setDate(rec.entranceDate.getDate() + i);
                    if (rec.startDate == 'Invalid Date' || rec.entranceDate == 'Invalid Date' || rec.endDate == 'Invalid Date') {
                        alert("Необходимо указать время подачи, выезда и возвращения!");
                        return;
                    }
                    self.newClaim.records.push(rec);
                }
            } else {
                var rec = {id: null};
                rec.startDate = self.frmtDate(sd, startTime);
                rec.endDate = self.frmtDate(sd, endTime);
                rec.endDate.setDate(rec.endDate.getDate() + inc);
                rec.entranceDate = self.frmtDate(sd, entranceTime);
                if (rec.startDate == 'Invalid Date' || rec.entranceDate == 'Invalid Date' || rec.endDate == 'Invalid Date') {
                    alert("Необходимо указать время подачи, выезда и возвращения!");
                    return;
                }
                self.newClaim.records.push(rec);
            }
        };

        self.frmtDate = function (date, time) {
            var result = new Date(new Date(date).getFullYear(), new Date(date).getMonth(), new Date(date).getDate(), new Date(time).getHours(), new Date(time).getMinutes(), new Date(time).getSeconds());
            return result;
        };

        self.removeRec = function (rec) {
            //удалить запись
            var k = -1;
            for (var i = 0; i < self.newClaim.records.length; i++) {
                if (rec === self.newClaim.records[i]) {
                    k = i;
                    break;
                }
            }
            self.newClaim.records.splice(k, 1);
        };

        self.validateForm = function () {
            if (self.specDepartment.id === null) {
                console.log('Department not selected!');
                return true;
            }
            if (self.newClaim.specialization === null) {
                console.log('Specialization not selected!');
                return true;
            }
            if (self.newClaim.records.length === 0) {
                console.log('No records in claim!');
                return true;
            }
            for (var i = 0; i < self.newClaim.records.length; i++) {
                var r = self.newClaim.records[i];
                if (r.startDate === null || r.startDate === undefined
                        || r.entranceDate === null || r.entranceDate === undefined
                        || r.endDate === null || r.endDate === undefined) {
                    console.log('Missing Date or Time record!');
                    return true;
                }
            }
            return false;
        };

        self.submitClaim = function () {
            if (self.validateForm()) {
                alert("Форма заполнена не полностью!");
                return;
            }
            if (self.newClaim.id === null) {
                //коррекция времени для хранения без часового пояса
                for (var i = 0; i < self.newClaim.records.length; i++) {
                    var rec = self.newClaim.records[i];
                    var sd = new Date(rec.startDate);                    
                    
                    var entranceTime = new Date(rec.entranceDate);
                    entranceTime.setUTCHours(entranceTime.getHours());
                    var startTime = new Date(rec.startDate);
                    startTime.setUTCHours(startTime.getHours());
                    var endTime = new Date(rec.endDate);
                    endTime.setUTCHours(endTime.getHours());
                    
                    self.newClaim.records[i].startDate = self.frmtDate(sd, startTime);
                    self.newClaim.records[i].entranceDate = self.frmtDate(sd, entranceTime);
                    self.newClaim.records[i].endDate = self.frmtDate(sd, endTime);
                }
                self.createClaim(self.newClaim);
//                formClose('plannerCarBoss');
//                formClose('formRoute');
            }

            self.resetClaimForm();
        };

        self.createClaim = function () {
            for (var i = 0; i < self.newClaim.routeTasks.length; i++) {
                self.newClaim.routeTasks[i].id = null;
            }
            formClose('form-add');
            formClose('cover-trsp1');
            self.newClaim.department = self.specDepartment;
            PlannerService.createClaim(self.newClaim)
                    .then(
                            function (d) {
                                //self.newClaims.push(d);
                                self.fetchTomorrowPlanRecords();
                                self.fetchTomorrowCompletePlanRecords();
                                self.resetClaimForm();
                            },
                            function (errResponse) {
                                console.error('Error while creating Claim.');
                            }
                    );
        };

        self.resetClaimForm = function () {
            self.isOtherDay = false;
            self.newClaim = {
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
            self.getDateFromServer();
            self.newRecord = {id: null, startDate: null, endDate: null, entranceDate: null};
            formClose('plannerCarBoss');
            formClose('formRoute');
            formClose('newFormTask');
            formClose('form-add');
            formClose('cover-trsp1');
        };

        self.submitRTask = function () {
            if (self.routeTask.id === null && self.routeTask.orderNum === '') {
                self.addRTask();
            } else {
                self.updateRTask();
            }
            self.resetRTaskForm();
        };

        self.addRTask = function () {
            var rt = {id: null};
            rt.orderNum = self.newClaim.routeTasks.length;
            rt.workName = self.routeTask.workName;
            rt.place = self.routeTask.place;
            rt.routeTemplate = self.routeTask.routeTemplate;
            self.newClaim.routeTasks.push(rt);
        };

        self.updateRTask = function () {
            self.removeRTask(self.temporaryRTask);
            var rt = {id: null};
            rt.orderNum = self.routeTask.orderNum;
            rt.workName = self.routeTask.workName;
            rt.place = self.routeTask.place;
            rt.routeTemplate = self.routeTask.routeTemplate;
            self.newClaim.routeTasks.push(rt);
        };

        self.resetRTaskForm = function () {
            self.routeTask = {id: null, workName: '', orderNum: '', place: null, routeTemplate: null};
        };

        self.removeRTask = function (routeTask) {
            var k = -1;
            for (var i = 0; i < self.newClaim.routeTasks.length; i++) {
                if (routeTask === self.newClaim.routeTasks[i]) {
                    k = i;
                    break;
                }
            }
            self.newClaim.routeTasks.splice(k, 1);
        };

        self.tryToUpdateRTask = function (routeTask) {
            self.routeTask.id = routeTask.id;
            self.routeTask.orderNum = routeTask.orderNum;
            self.routeTask.workName = routeTask.workName;
            self.routeTask.place = routeTask.place;
            self.routeTask.routeTemplate = routeTask.routeTemplate;
            self.temporaryRTask = routeTask;
        };


        self.createCarBoss = function () {
            self.carBoss.department = self.specDepartment;
            PlannerService.createCarBoss(self.carBoss)
                    .then(
                            function (d) {
                                self.fetchCarBosses();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while creating CarBoss.');
                            }
                    );
        };

        self.updateCarBoss = function () {
            PlannerService.updateCarBoss(self.carBoss)
                    .then(
                            function (d) {
                                self.fetchCarBosses();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while updating CarBoss.');
                            }
                    );
        };

        self.removeCB = function (carBoss) {
            PlannerService.deleteCarBoss(carBoss)
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

        self.submitCB = function () {
            if (self.specDepartment.id === null) {
                alert("Не выбрано подразделение!");
                return;
            }
            if (self.carBoss.id === null) {
                self.createCarBoss();
            } else {
                self.updateCarBoss();
            }
        };

        self.tryToCreate = function () {
            self.carBoss = {id: null};
            formOpen('formCarBoss');
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
            formOpen('formCarBoss');
        };

        self.tryToDelete = function (carBoss) {
            self.carBoss = carBoss;
            formOpen('del-car-boss-confirm');
        };

        self.resetCBForm = function () {
            self.carBoss = {id: null};
            formClose('formCarBoss');
        };

        self.checkAll = function (header) {
            for (var i = 0; i < header.compositeClaimRecords.length; i++) {
                header.compositeClaimRecords[i].record.checked = header.allChecked;
            }
        };

        self.getUniqueModels = function (arr) {
            var i = 0,
                    current,
                    length = arr.length,
                    unique = [];
            for (; i < length; i++) {
                current = arr[i];
                if (!~unique.indexOf(current)) {
                    unique.push(current);
                }
            }
            return unique;
        };

//        self.prepareData = function (td, spec) {
//            self.tempTransportDep = td;
//            self.tempVehSpec = spec;
//        };

//        self.filterVehicleModels = function (vm) {
//            for (var i = 0; i < self.vehicles.length; i++) {
//                if (self.vehicles[i].transportDep.id === self.tempTransportDep.id) {
//                    if (self.vehicles[i].model !== null && self.vehicles[i].model !== undefined) {
//                        if (self.vehicles[i].model.modelName === vm.modelName && self.vehicles[i].model.vehicleType.specialization === self.tempVehSpec) {
//                            return vm;
//                        }
//                    }
//                }
//            }
//        };


//        self.filterModelsByselectedTransportDep = function (td, spec) {
//
////            self.filteredVehicleModels = [];
////            for (var i = 0; i < self.vehicles.length; i++) {
////                if (self.vehicles[i].transportDep.id === td.id) {
////                    for (var j = 0; j < self.vehicleModels.length; j++) {
////                        if (self.vehicles[i].model !== null && self.vehicles[i].model !== undefined) {
////                            if (self.vehicles[i].model.modelName === self.vehicleModels[j].modelName && self.vehicles[i].model.vehicleType.specialization === spec) {
////                                self.filteredVehicleModels.push(self.vehicleModels[j]);
////                            }
////                        }
////                    }
////                }
////            }
////            self.filteredVehicleModels = self.getUniqueModels(self.filteredVehicleModels);
//            self.filteredVehicleModels = self.getUniqueModels(self.vehicleModels);
//        };

        self.changeCheckedTD = function (compositeClaimRecord) {
            if (compositeClaimRecord.record.checked) {
                for (var i = 0; i < self.headers.length; i++) {
                    for (var j = 0; j < self.headers[i].compositeClaimRecords.length; j++) {
                        if (self.headers[i].compositeClaimRecords[j].record.checked) {
                            self.headers[i].compositeClaimRecords[j].appointment.transportDep = compositeClaimRecord.appointment.transportDep;
                        }
                    }
                }
            }

            //   self.filterModelsByselectedTransportDep(compositeClaimRecord.appointment.transportDep, compositeClaimRecord.claim.specialization);

        };

        self.changeCheckedVM = function (compositeClaimRecord) {
            if (compositeClaimRecord.record.checked) {
                for (var i = 0; i < self.headers.length; i++) {
                    for (var j = 0; j < self.headers[i].compositeClaimRecords.length; j++) {
                        if (self.headers[i].compositeClaimRecords[j].record.checked) {
                            self.headers[i].compositeClaimRecords[j].appointment.vehicleModel = compositeClaimRecord.appointment.vehicleModel;
                        }
                    }
                }
            }
        };

        self.carBossToStringInfo = function (boss) {
            if (boss === null) {
                return null;
            }
            if (boss !== undefined) {
                var result = boss.firstname + " " + boss.name.charAt(0) + "." + (boss.surname !== null && boss.surname !== null ? boss.surname.charAt(0) + "." : "");
                return result;
            } else {
                return null;
            }
        };

        self.prepareToAddClaim = function () {
            formOpen('form-add');
            formOpen('cover-trsp1');
        };

    }]);


