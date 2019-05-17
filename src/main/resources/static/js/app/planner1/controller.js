'use strict';

App.controller('PlannerController1', ['$scope', 'PlannerService1',
    function ($scope, PlannerService1) {
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
        self.pageCount;
        self.numRecordsPerPage = 10;
        self.pager = {};
        self.data = [];
        self.cmpsts = [];
        self.vehicleTypes = [];
        self.drivers = [];
        self.routeTasks = [];
        // self.clrecs = [];


        self.getPermit = function () {
            PlannerService1.getPermit()
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
            PlannerService1.getUserName()
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
            PlannerService1.fetchAllDepartments()
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
            PlannerService1.fetchAllAppointments()
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
            PlannerService1.fetchAllClaims()
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
            PlannerService1.fetchMonthPlanRecords()
                    .then(
                            function (d) {
                                self.cmpsts = d;
                                self.createHeaders();
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
            PlannerService1.fetchMonthBeforePlanRecords()
                    .then(
                            function (d) {
                                self.cmpsts = d;
                                console.log(self.cmpsts)
                                self.createHeaders();
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
            PlannerService1.fetchWeekPlanRecords()
                    .then(
                            function (d) {
                                self.cmpsts = d;
                                self.createHeaders();
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
            PlannerService1.fetchTomorrowPlanRecords()
                    .then(
                            function (d) {
                                self.cmpsts = d;
                                self.createHeaders();
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
            PlannerService1.fetchDatePlanRecords(datePlan)
                    .then(
                            function (d) {
                                self.cmpsts = d;
                                self.createHeaders();
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
            PlannerService1.fetchMonthCompletePlanRecords()
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
            PlannerService1.fetchMonthBeforeCompletePlanRecords()
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
            PlannerService1.fetchWeekCompletePlanRecords()
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
            PlannerService1.fetchTomorrowCompletePlanRecords()
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
            PlannerService1.fetchDateCompletePlanRecords(datePlan)
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
            PlannerService1.fetchCarBosses()
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
            PlannerService1.fetchTransportDeps()
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
            PlannerService1.fetchVehicles()
                    .then(
                            function (d) {
                                self.vehicles = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Vehicles');
                            }
                    );
        };

        self.fetchDrivers = function () {
            PlannerService1.fetchDrivers()
                    .then(
                            function (d) {
                                self.drivers = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Drivers');
                            }
                    );
        };

        self.fetchAllVehicleModels = function () {
            PlannerService1.fetchAllVehicleModels()
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
            PlannerService1.fetchPlaces()
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
            PlannerService1.fetchOtsInfo()
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
            PlannerService1.fetchOtsVehModels()
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
            PlannerService1.fetchDateFromServer()
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
            PlannerService1.fetchVehicleTypes()
                    .then(
                            function (d) {
                                self.vehicleTypes = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching VehicleTypes');
                            }
                    );
        };

        self.fetchDepartments = function () {
            PlannerService1.fetchDepartments()
                    .then(
                            function (d) {
                                self.departments = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Departments');
                            }
                    );
        };

        self.fetchRouteTasks = function () {
            PlannerService1.fetchRouteTasks()
                    .then(
                            function (d) {
                                self.routeTasks = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching RouteTasks');
                            }
                    );
        };

        self.fetchDepartments();
        self.fetchDrivers();
        self.fetchVehicles();
        self.fetchVehicleTypes();
        self.fetchTransportDeps();
        self.fetchAllVehicleModels();
        self.fetchRouteTasks();
        self.fetchTomorrowPlanRecords();
        self.fetchTomorrowCompletePlanRecords();
        self.getDateFromServer();
        self.fetchCarBosses();
        self.fetchPlaces();
        self.fetchAllSpecDepartments();
        self.fetchOtsInfo();
        self.fetchOtsVehModels();


        self.createAppointments = function () {
            var appoints = [];
            var appointment = {};
            expandedHeaders = [];
            for (var i = 0; i < self.headers.length; i++) {
                if (self.headers[i].isVisible) {
                    expandedHeaders[i] = true;
                }
                for (var j = 0; j < self.headers[i].composite.length; j++) {
                    var appointment = self.headers[i].composite[j].appointment;
                    var recId = self.headers[i].composite[j].record.id;
                    if (appointment.status === '3' || appointment.status === '4') {
                        continue;
                    }
                    if (appointment.status === '5' || appointment.transportDep !== null && appointment.id === null) {
                        appoints.push({
                            recordId: recId,
                            appointment: appointment
                        });
                    }
                }
            }
            PlannerService1.createAppointments(appoints)
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
            PlannerService1.deleteAppointment(appointment)
                    .then(
                            self.fetchAllAppointments,
                            function (errResponse) {
                                console.error('Error while deleting Appointment.');
                            }
                    );
        };

        self.rowClick = function (dep) {
            if (dep.isVisible) {
                dep.isVisible = !dep.isVisible;
            } else {
                for (var i = 0; i < self.headers.length; i++) {
                    self.headers[i].isVisible = false;
                }
                //if (dep.isVisible === undefined) {
                //     dep.isVisible = true;
                // } else {
                dep.isVisible = !dep.isVisible;
                // }
                self.showRecords(dep.composite);
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
                case '0':
                    return inProgress;
                case '1':
                    return ready;
                case '2':
                    return completed;
                case '3':
                    return canceledByUser;
                case '4':
                    return canceledByPlanner;
                case '5':
                    return canceledByDispatcher;
                case '6':
                    return canceledBySupermanager;
            }
        };


        self.selectStatusIco = function (stat) {
            var inProgress = 'fas fa-lg fa-clock';
            var ready = 'fas fa-lg fa-check';
            var completed = 'fas fa-lg fa-check-double';
            var canceled = 'fas fa-lg fa-ban';
            switch (stat) {
                case '0':
                    return inProgress;
                case '1':
                    return ready;
                case '2':
                    return completed;
                case '3':
                    return canceled;
                case '4':
                    return canceled;
                case '5':
                    return canceled;
                case '6':
                    return canceled;
            }
        };


        self.selectStatusColor = function (stat) {
            var inProgress = 'done-status';
            var ready = 'status-ready';
            var completed = 'status-ready';
            var canceled = 'cancel-status';
            switch (stat) {
                case '0':
                    return inProgress;
                case '1':
                    return ready;
                case '2':
                    return completed;
                case '3':
                    return canceled;
                case '4':
                    return canceled;
                case '5':
                    return canceled;
                case '6':
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
            if (user !== null && user !== undefined) {
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
            if (boss !== null && boss !== undefined) {
                var result = boss.firstname + " " + boss.name.charAt(0) + "." + (boss.surname !== null && boss.surname !== null ? boss.surname.charAt(0) + "." : "") + " " + boss.post;
                return result;
            }
        };

        self.checkPhone = function (appointment) {
            return appointment.driver !== null && appointment.driver !== undefined && appointment.driver.phone !== null && appointment.driver.phone !== undefined;
        };

        self.showDriver = function (appointment) {
            return appointment.driver === null || appointment.driver === undefined ? '-' : self.personToString(appointment.driver);
        };

        self.disable = function (clrec) {
            var appointment = clrec.appointment;
            if (appointment.status !== '5' && appointment.id !== null) {
                return true;
            }
        };

        var expandHeaders = function () {
            for (var k in expandedHeaders) {
                if (self.headers.length > 0)
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
                canceledApp.status = '4';
                canceledApp.note = self.cancelNote;
            } else {
                canceledApp = {id: null, creationDate: '', status: '', note: ''};
                canceledApp.status = '4';
                canceledApp.note = self.cancelNote;
            }
            PlannerService1.cancelRecord({recordId: self.record.id, appointment: canceledApp})
                    .then(
                            function (d) {
                                var rec = d;
                                var l = -1;
                                var k = -1;
                                for (var i = 0; i < self.headers.length; i++) {
                                    for (var j = 0; j < self.headers[i].composite.length; j++) {
                                        if (self.headers[i].composite[j].record.id === rec.id) {
                                            l = i;
                                            k = j;
                                            break;
                                        }
                                    }
                                    if (k >= 0) {
                                        break;
                                    }
                                }
                                self.headers[l].composite[k].record = d;
                            },
                            function (errResponse) {
                                console.error('Error while canceled Record.');
                            }
                    );
        };

        self.amountReady = function (header) {
            var k = 0;
            if (header.composite !== undefined) {
                for (var j = 0; j < header.composite.length; j++) {
                    if (header.composite[j].appointment.status === '1') {
                        k = k + 1;
                    }
                }
            }
            return k;
        };

        self.amountInProgress = function (header) {
            var k = 0;
            if (header.composite !== undefined) {
                for (var j = 0; j < header.composite.length; j++) {
                    if (header.composite[j].appointment.status === '0') {
                        k = k + 1;
                    }
                }
            }
            return k;
        };

        self.amountCanceled = function (header) {
            var k = 0;
            if (header.composite !== undefined) {
                for (var j = 0; j < header.composite.length; j++) {
                    if (header.composite[j].appointment.status === '3' ||
                            header.composite[j].appointment.status === '4' ||
                            header.composite[j].appointment.status === '5') {
                        k = k + 1;
                    }
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
            PlannerService1.updateTime(self.record)
                    .then(
                            function (d) {
                                var rec = d;
                                var l = -1;
                                var k = -1;
                                for (var i = 0; i < self.headers.length; i++) {
                                    for (var j = 0; j < self.headers[i].composite.length; j++) {
                                        if (self.headers[i].composite[j].record.id === rec.id) {
                                            l = i;
                                            k = j;
                                            break;
                                        }
                                    }
                                    if (k >= 0) {
                                        break;
                                    }
                                }
                                self.headers[l].composite[k].record = d;
                            },
                            function (errResponse) {
                                console.error('Error while updating Time');
                            }
                    );
        };

        self.updateRoute = function () {
            PlannerService1.updateRoute(self.claim)
                    .then(
                            function (d) {
                                var clm = d;
                                var l = -1;
                                var k = -1;
                                for (var i = 0; i < self.headers.length; i++) {
                                    for (var j = 0; j < self.headers[i].composite.length; j++) {
                                        if (self.headers[i].composite[j].claim.id === clm.id) {
                                            l = i;
                                            k = j;
                                            break;
                                        }
                                    }
                                    if (k >= 0) {
                                        break;
                                    }
                                }
                                self.headers[l].composite[k].claim = d;
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
            if (self.onWeek) {
                for (var i = 0; i < 5; i++) {
                    var rec = {id: null};
                    rec.startDate = self.frmtDate(sd, self.newRecord.startDate);
                    rec.startDate.setDate(rec.startDate.getDate() + i);
                    rec.endDate = self.frmtDate(sd, self.newRecord.endDate);
                    rec.endDate.setDate(rec.endDate.getDate() + i + inc);
                    rec.entranceDate = self.frmtDate(sd, self.newRecord.entranceDate);
                    rec.entranceDate.setDate(rec.entranceDate.getDate() + i);
                    if (rec.startDate == 'Invalid Date' || rec.entranceDate == 'Invalid Date' || rec.endDate == 'Invalid Date') {
                        alert("Необходимо указать время подачи, выезда и возвращения!");
                        return;
                    }
                    self.newClaim.records.push(rec);
                }
            } else {
                var rec = {id: null};
                rec.startDate = self.frmtDate(sd, self.newRecord.startDate);
                rec.endDate = self.frmtDate(sd, self.newRecord.endDate);
                rec.endDate.setDate(rec.endDate.getDate() + inc);
                rec.entranceDate = self.frmtDate(sd, self.newRecord.entranceDate);
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
            PlannerService1.createClaim(self.newClaim)
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
            PlannerService1.createCarBoss(self.carBoss)
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
            PlannerService1.updateCarBoss(self.carBoss)
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
            PlannerService1.deleteCarBoss(carBoss)
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
            for (var i = 0; i < header.composite.length; i++) {
                header.composite[i].record.checked = header.allChecked;
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
                    for (var j = 0; j < self.headers[i].composite.length; j++) {
                        if (self.headers[i].composite[j].record.checked) {
                            self.headers[i].composite[j].appointment.transportDep = compositeClaimRecord.appointment.transportDep;
                        }
                    }
                }
            }

            //   self.filterModelsByselectedTransportDep(compositeClaimRecord.appointment.transportDep, compositeClaimRecord.claim.specialization);

        };

        self.changeCheckedVM = function (compositeClaimRecord) {
            if (compositeClaimRecord.record.checked) {
                for (var i = 0; i < self.headers.length; i++) {
                    for (var j = 0; j < self.headers[i].composite.length; j++) {
                        if (self.headers[i].composite[j].record.checked) {
                            self.headers[i].composite[j].appointment.vehicleModel = compositeClaimRecord.appointment.vehicleModel;
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

        self.showRecords = function (clrecs) {
            self.clrec = [];
            self.pageCount = Math.ceil(clrecs.length / self.numRecordsPerPage);
            console.log('page count =' + self.pageCount);
            self.clrecs = clrecs;
            self.setPage(1);
        };

//=================pagination===========
        self.setPage = function (page) {
            if (page < 1 || page > self.pageCount) {
                self.pager = {};
                self.data = [];
                return;
            }
            // get pager object from service
            self.pager = self.getPager(self.clrecs.length, page, self.numRecordsPerPage);
            // get current page of items
            // self.data = self.clrecs.slice(self.pager.startIndex, self.pager.endIndex + 1);
            console.log(self.data);
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

        self.createHeaders = function () {
            var t1 = Date.now();
            self.headers = [];
            var deps = [];
            var k = 0;
            deps[k] = self.cmpsts[0].departmentid;
            for (var i = 1; i < self.cmpsts.length; i++) {
                if (self.cmpsts[i].departmentid !== deps[k]) {
                    k++;
                    deps[k] = self.cmpsts[i].departmentid;
                }
            }
            for (var i = 0; i < deps.length; i++) {
                for (var j = 0; j < self.departments.length; j++) {
                    if (deps[i] === self.departments[j].id) {
                        self.headers[i] = {
                            department: {
                                id: self.departments[j].id,
                                shortname: self.departments[j].shortname,
                                fullname: self.departments[j].fullname},
                            composite: []
                        };

                    }
                }
            }
            for (var i = 0; i < self.headers.length; i++) {
                for (var j = 0; j < self.cmpsts.length; j++) {
                    if (self.headers[i].department.id === self.cmpsts[j].departmentid) {
                        for (var l = 0; l < self.transportDeps.length; l++) {
                            if (self.cmpsts[j].transportdepid === self.transportDeps[l].id) {
                                var transportDep = self.transportDeps[l];
                            }
                        }
                        for (var l = 0; l < self.vehicleModels.length; l++) {
                            if (self.cmpsts[j].modelid === self.vehicleModels[l].id) {
                                var vehicleModelId = self.vehicleModels[l].id;
                                var vehicleModelModelName = self.vehicleModels[l].modelName;
                                var vehicleModelVehicleType = self.vehicleModels[l].vehicleType;
                            }
                        }
                        for (var l = 0; l < self.drivers.length; l++) {
                            if (self.cmpsts[j].driverid === self.drivers[l].id) {
                                var driverId = self.drivers[l].id;
                                var driverFirstname = self.drivers[l].firstname;
                                var driverName = self.drivers[l].name;
                                var driverSurname = self.drivers[l].surname;
                                var driverPhone = self.drivers[l].phone;
                            }
                        }
                        for (var l = 0; l < self.vehicles.length; l++) {
                            if (self.cmpsts[j].vehicleid === self.vehicles[l].id) {
                                var vehicle = self.vehicles[l];
                            }
                        }

                        self.headers[i].composite.push({
                            claim: {
                                id: self.cmpsts[j].claimid,
                                specialization: self.cmpsts[j].claimspecialization,
                                purpose: self.cmpsts[j].claimpurpose,
                                vehicleType: {typeName: self.cmpsts[j].vehicletypename},
                                routeTasks: {}
                            },
                            record: {
                                id: self.cmpsts[j].recordid,
                                startDate: self.cmpsts[j].startdate,
                                entranceDate: self.cmpsts[j].entrancedate,
                                endDate: self.cmpsts[j].enddate
                            },
                            appointment: {
                                id: self.cmpsts[j].appointmentid,
                                status: self.cmpsts[j].appointmentstatus,
                                note: self.cmpsts[j].appointmentnote,
                                transportDep: transportDep,
                                vehicleModel: {
                                    id: vehicleModelId,
                                    modelName: vehicleModelModelName,
                                    vehicleType: vehicleModelVehicleType
                                },
                                vehicle: vehicle,
                                driver: {
                                    id: driverId,
                                    firstname: driverFirstname,
                                    name: driverName,
                                    surname: driverSurname,
                                    phone: driverPhone
                                },
                                creator: {
                                    id: null,
                                    fullName: null,
                                    post: null
                                },
                                modificator: {
                                    id: null,
                                    fullName: null,
                                    post: null
                                }
                            }
                        });

                    }
                }
            }

            var t2 = Date.now();
            console.log(t1);
            console.log(t2);
            console.log(t2 - t1);
            console.log(self.headers);
        };


    }]);


