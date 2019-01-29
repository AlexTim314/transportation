'use strict';

App.controller('VehiclesController', ['$scope', 'VehiclesService',
    function ($scope, VehiclesService) {
        var self = this;

        self.transportDep = {id: null};
        self.vehicleTypes = [];
        self.models = [];
        self.fuels = [];

        self.fuel = {id: null};

        self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}, fuels: []};
        self.vehicles = [];
        self.vehicleInfo = {id: null};

        self.all = false;

        var idsArr = [];

        self.fetchTransportDep = function () {
            VehiclesService.fetchTransportDep()
                    .then(
                            function (d) {
                                self.transportDep = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching TransportDep');
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
                                console.error('Error while fetching VehicleTypes');
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
                                console.error('Error while fetching Models');
                            }
                    );
        };

        self.fetchFuels = function () {
            VehiclesService.fetchFuels()
                    .then(
                            function (d) {
                                self.fuels = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Fuels');
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
                                console.error('Error while fetching Vehicles');
                            }
                    );
        };

        self.fetchTransportDep();
        self.fetchVehicleTypes();
        self.fetchVehicleModels();
        self.fetchFuels();
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

        self.createVehicleInfo = function () {
            self.vehicleInfo.vehicle = self.vehicle;
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
            VehiclesService.deleteVehicles(idsArr)
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
            formOpen('formTransport');
        };

        self.tryToDelete = function () {
            idsArr = [];
            for (var i = 0; i < self.vehicles.length; i++) {
                if (self.vehicles[i].checked) {
                    idsArr.push(self.vehicles[i].id);
                }
            }
            formOpen('del-vehicle-confirm');
        };

        self.tryToUpdateVehicleState = function (vehicle) {
            self.vehicle = vehicle;
            formOpen('formCurrentParametr');
        };

        self.resetForm = function () {
            self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}, fuels: []};
            formClose('formTransport');
        };

        self.checkAll = function () {
            for (var i = 0; i < self.vehicles.length; i++) {
                self.vehicles[i].checked = self.all;
            }
        };

        self.addFuel = function () {
            self.vehicle.fuels.push(self.fuel);
            self.fuel = {id: null};
        };

        self.removeFuel = function (fuel) {
            var k = -1;
            for (var i = 0; i < self.vehicles.length; i++) {
                if (self.vehicles[i].id === fuel.id) {
                    k = i;
                    break;
                }
            }
            self.vehicle.fuels.splice(k, 1);
            self.fuel = {id: null};
        };

        self.openInfoForm = function (vehicle) {
            self.vehicle = vehicle;
            formOpen('more-transport');
        };

        self.closeInfoForm = function () {
            self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}, fuels: []};
            formClose('more-transport');
        };

        self.closeStateForm = function () {
            self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}, fuels: []};
            self.vehicleInfo = {id: null};
            formClose('formCurrentParametr');
        };

    }]);

