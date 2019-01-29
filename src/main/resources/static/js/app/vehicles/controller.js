'use strict';

App.controller('VehiclesController', ['$scope', 'VehiclesService',
    function ($scope, VehiclesService) {
        var self = this;

        self.transportDep = {id: null};
        self.vehicleTypes = [];
        self.models = [];

        self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}};
        self.vehicles = [];

        self.fetchTransportDep = function () {
            VehiclesService.fetchTransportDep()
                    .then(
                            function (d) {
                                self.transportDep = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Department');
                            }
                    );
        };

        self.fetchVehicleTypes = function () {
            VehiclesService.fetchVehicleTypes()
                    .then(
                            function (d) {
                                self.vehicleTypes = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Department');
                            }
                    );
        };

        self.fetchVehicleModels = function () {
            VehiclesService.fetchVehicleModels()
                    .then(
                            function (d) {
                                self.models = d;
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

        self.fetchTransportDep();
        self.fetchVehicleTypes();
        self.fetchVehicleModels();
        self.fetchVehicles();

        self.createVehicle = function () {
            self.vehicle.transportDep = self.transportDep;
            VehiclesService.createVehicle(self.vehicle)
                    .then(
                            function (d) {
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
                                self.fetchVehicles();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while updating Vehicle.');
                            }
                    );
        };

        self.deleteVehicles = function () {
            VehiclesService.deleteVehicles(self.vehicle)
                    .then(
                            function (d) {
                                formClose('del-vehicle-confirm');
                                self.fetchVehicles();
                            },
                            function (errResponse) {
                                console.error('Error while deleting Vehicle.');
                            }
                    );
        };

        self.submit = function () {
            if (self.vehicle.id === null) {
                self.createVehicle();
            } else {
                self.updateVehicle()();
            }
        };

        self.tryToCreate = function () {
            self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}};
            formOpen('formTransport');
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
            formOpen('formTransport');
        };

        self.tryToDelete = function () {
            self.vehicle = vehicle;
            formOpen('del-vehicle-confirm');
        };

        self.resetForm = function () {
            self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}};
            formClose('formTransport');
        };

    }]);

