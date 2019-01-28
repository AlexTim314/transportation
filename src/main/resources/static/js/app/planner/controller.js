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
        self.vehicleModel = {id: null, modelName: ''};
        self.vehicle = {id: null, number: ''};
        self.driver = {id: null, firstname: '', name: '', surname: '', phone: ''};
        self.departments = [];
        self.claims = [];
        self.records = [];
        self.appointments = [];
        
        self.fetchAllDepartments = function () {
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

        self.fetchAllDepartments();
        self.fetchAllAppointments();


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
            if (dep.isVisible === undefined) {
                dep.isVisible = true;
            } else {
                dep.isVisible = !dep.isVisible;
            }
        };

    }]);
