'use strict';

App.controller('PlanController', ['$scope', 'PlanService',
    function ($scope, PlanService) {
        var self = this;

        self.records = [];
        self.transportDeps = [];
        self.models = [];

        self.appointments = [];
        self.appointmentGroups = [];

        self.fetchAllTransportDeps = function () {
            PlanService.fetchAllTransportDeps()
                    .then(
                            function (d) {
                                self.transportDeps = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching TransportDeps');
                                showAlert(errResponse);
                            }
                    );
        };

        self.fetchAllModels = function () {
            PlanService.fetchAllModels()
                    .then(
                            function (d) {
                                self.models = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Models');
                                showAlert(errResponse);
                            }
                    );
        };

        self.fetchRecords = function () {
            var status = 'record_status_created';
            var dd = new Date().getDate();
            var mm = new Date().getMonth() + 1;
            var yyyy = new Date().getFullYear();
            var date = yyyy + '-' + mm + '-' + dd;
            PlanService.fetchRecords(date, status)
                    .then(
                            function (d) {
                                self.records = d;
                                self.prepareLists();
                            },
                            function (errResponse) {
                                console.error('Error while fetching Records');
                                alert(errResponse);
                            }
                    );
        };

        self.fetchAllModels();
        self.fetchAllTransportDeps();
        self.fetchRecords();

        self.addRowHandlers = function (dep) {
            if (dep.isVisible === undefined) {
                dep.isVisible = true;
            } else {
                dep.isVisible = !dep.isVisible;
            }
        };

        self.prepareLists = function () {
            self.departments = [];
            for (var i in self.records) {
                var rec = self.records[i];
                var dep = rec.claim.department;

                var isContain = false;
                for (var j in self.departments) {
                    if (dep.id === self.departments[j].id) {
                        self.departments[j].records.push(rec);
                        isContain = true;
                        break;
                    }
                }
                if (!isContain) {
                    dep.records = [];
                    dep.records.push(rec);
                    self.departments.push(dep);
                }
            }
        };

    }]);