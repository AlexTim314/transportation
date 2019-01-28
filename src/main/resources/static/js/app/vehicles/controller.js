'use strict';

App.controller('VehiclesController', ['$scope', 'VehiclesService',
    function ($scope, VehiclesService) {
        var self = this;

        self.department = {id: null};

        self.vehicle = {id: null};
        self.vehicles = [];

        self.fetchDepartment = function () {
            VehiclesService.fetchDepartment()
                    .then(
                            function (d) {
                                self.department = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Department');
                            }
                    );
        };

        self.fetchVehicles = function () {
            VehiclesService.fetchVehicles()
                    .then(
                            function (d) {
                                self.vehicles = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Bosses');
                            }
                    );
        };

        self.fetchDepartment();
        self.fetchVehicles();

        self.createVehicle = function () {
            self.vehicle.department = self.department;
            VehiclesService.createVehicle(self.vehicle)
                    .then(
                            function (d) {
//                                self.vehicles.push(d);
                                self.fetchVehicles();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while creating Vehicle.');
                            }
                    );
        };

        self.updateVehicle = function () {
            VehiclesService.updateVehicle(self.vehicle)
                    .then(
                            function (d) {
//                                self.vehicles.push(d);
                                self.fetchVehicles();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while updating Vehicle.');
                            }
                    );
        };

        self.deleteVehicle = function () {
            VehiclesService.deleteVehicle(self.vehicle)
                    .then(
                            function (d) {
                                self.vehicle = {id: null};
                                formClose('del-car-boss-confirm');
                                self.fetchVehicles();
                            },
                            function (errResponse) {
                                console.error('Error while deleting Vehicle.');
                            }
                    );
            formClose('del-car-boss-confirm');
        };

        self.submit = function () {
            if (self.vehicle.id === null) {
                self.createVehicle();
            } else {
                self.updateVehicle()();
            }
        };

        self.tryToCreate = function () {
            self.vehicle = {id: null};
            formOpen('formVehicle');
        };

        self.tryToUpdate = function (vehicle) {
            self.vehicle.id = vehicle.id;
            self.vehicle.firstname = vehicle.firstname;
            self.vehicle.name = vehicle.name;
            self.vehicle.surname = vehicle.surname;
            self.vehicle.post = vehicle.post;
            self.vehicle.birthday = vehicle.birthday;
            self.vehicle.address = vehicle.address;
            self.vehicle.phone = vehicle.phone;
            self.vehicle.department = vehicle.department;
            formOpen('formVehicle');
        };

        self.tryToDelete = function (vehicle) {
            self.vehicle = vehicle;
            formOpen('del-car-boss-confirm');
        };

        self.resetForm = function () {
            self.vehicle = {id: null};
            formClose('formVehicle');
        };

    }]);

