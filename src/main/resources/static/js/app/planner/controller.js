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
        self.departments = [];
        self.headers = [];
        self.claims = [];
        self.records = [];
        self.appointments = [];
        self.transportDeps = [];
        self.vehicleModels = [];
        self.selectedIcon;
        self.type;

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
            PlannerService.fetchAllDepsRecords()
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


        self.fetchAllPlanRecords();
        self.fetchTransportDeps();
        self.fetchAllVehicleModels();
        //self.fetchAllAppointments();


        self.departFromObj = function (obj) {
            self.departments = obj.departments;
            console.log(self.departments);
        };

        self.createAppointment = function (appointment) {
            PlannerService.createAppointment(appointment)
                    .then(
                            function (d) {
                                self.appointments.push(d);
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
            // self.type = dep.compositeClaimRecords[0].claim.specialization;
            //  console.log(self.type);
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

//        self.resetForm = function (){
//            self.clrec = {};
//        };



    }]);