'use strict';

App.controller('DispatcherController', ['$scope', 'DispatcherService',
    function ($scope, DispatcherService) {
        var self = this;

        self.vehicleTypes = [];
        self.models = [];

        self.driver = {id: null, vacant: true};
        self.vehicle = {id: null, vacant: true, odometr: 0, fuelRemnant: 10};
        self.drivers = [];
        self.vehicles = [];

        self.fetchAllVehicleTypes = function () {
            DispatcherService.fetchAllVehicleTypes()
                    .then(
                            function (d) {
                                self.vehicleTypes = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching VehicleTypes');
                                showAlert(errResponse);
                            }
                    );
        };

        self.fetchAllModels = function () {
            DispatcherService.fetchAllModels()
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

        self.fetchDrivers = function () {
            DispatcherService.fetchDrivers()
                    .then(
                            function (d) {
                                self.drivers = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Drivers');
                                showAlert(errResponse);
                            }
                    );
        };

        self.fetchVehicles = function () {
            DispatcherService.fetchVehicles()
                    .then(
                            function (d) {
                                self.vehicles = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Vehicles');
                                showAlert(errResponse);
                            }
                    );
        };

        self.fetchAllVehicleTypes();
        self.fetchAllModels();
        self.fetchDrivers();
        self.fetchVehicles();

        self.saveDriver = function (driver) {
            DispatcherService.saveDriver(driver)
                    .then(
                            self.fetchDrivers,
                            function (errResponse) {
                                console.error('Error while saving Driver.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.saveVehicle = function (vehicle) {
            DispatcherService.saveVehicle(vehicle)
                    .then(
                            self.fetchVehicles,
                            function (errResponse) {
                                console.error('Error while saving Vehicle.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.deleteDriver = function (driver) {
            DispatcherService.deleteDriver(driver)
                    .then(
                            self.fetchDrivers,
                            function (errResponse) {
                                console.error('Error while deleting Driver.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.deleteVehicle = function (vehicle) {
            DispatcherService.deleteVehicle(vehicle)
                    .then(
                            self.fetchVehicles,
                            function (errResponse) {
                                console.error('Error while deleting Vehicle.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.tryToRemoveDriver = function (driver) {
            self.driver.id = driver.id;
            self.driver.name = driver.name;
            self.driver.firstname = driver.firstname;
            self.driver.surname = driver.surname;
            self.driver.driverLicense = driver.driverLicense;
            self.driver.phone = driver.phone;
            self.driver.vacant = driver.vacant;
            formOpen('dialog-window');
        };

        self.tryToRemoveVehicle = function (vehicle) {
            self.vehicle.id = vehicle.id;
            self.vehicle.number = vehicle.number;
            self.vehicle.odometr = vehicle.odometr;
            self.vehicle.fuelRemnant = vehicle.fuelRemnant;
            self.vehicle.vehicleModel = vehicle.vehicleModel;
            self.vehicle.vacant = vehicle.vacant;
            formOpen('dialog-window');
        };

        self.submitRemoving = function () {
            if (self.driver.id !== null) {
                self.deleteDriver(self.driver);
                self.resetDriverForm();
            }
            if (self.vehicle.id !== null) {
                self.deleteVehicle(self.vehicle);
                self.resetVehicleForm();
            }
            formClose('dialog-window');
        };

        self.changeDriverVacant = function (driver) {
            self.driver.id = driver.id;
            self.driver.name = driver.name;
            self.driver.firstname = driver.firstname;
            self.driver.surname = driver.surname;
            self.driver.driverLicense = driver.driverLicense;
            self.driver.phone = driver.phone;
            self.driver.vacant = !driver.vacant;
            self.saveDriver(self.driver);
            self.resetDriverForm();
        };

        self.changeVehicleVacant = function (vehicle) {
            self.vehicle.id = vehicle.id;
            self.vehicle.number = vehicle.number;
            self.vehicle.odometr = vehicle.odometr;
            self.vehicle.fuelRemnant = vehicle.fuelRemnant;
            self.vehicle.vehicleModel = vehicle.vehicleModel;
            self.vehicle.vacant = !vehicle.vacant;
            self.saveVehicle(self.vehicle);
            self.resetVehicleForm();
        };

        self.submitDriverForm = function () {
            self.saveDriver(self.driver);
            self.resetDriverForm();
        };

        self.submitVehicleForm = function () {
            self.saveVehicle(self.vehicle);
            self.resetVehicleForm();
        };

        self.editDriver = function (driver) {
            self.driver.id = driver.id;
            self.driver.name = driver.name;
            self.driver.firstname = driver.firstname;
            self.driver.surname = driver.surname;
            self.driver.driverLicense = driver.driverLicense;
            self.driver.phone = driver.phone;
            self.driver.vacant = driver.vacant;
            formEditOpen('formDriver');
        };

        self.editVehicle = function (vehicle) {
            self.vehicle.id = vehicle.id;
            self.vehicle.number = vehicle.number;
            self.vehicle.odometr = vehicle.odometr;
            self.vehicle.fuelRemnant = vehicle.fuelRemnant;
            self.vehicle.vehicleModel = vehicle.vehicleModel;
            self.vehicle.vacant = vehicle.vacant;
            formEditOpen('formTransport');
        };

        self.resetDriverForm = function () {
            self.driver = {id: null, vacant: true};
            formClose('formDriver');
        };

        self.resetVehicleForm = function () {
            self.vehicle = {id: null, vacant: true, odometr: 0, fuelRemnant: 10};
            formClose('formTransport')
        };

    }]);
