'use strict';

App.controller('TransportDepController', ['$scope', 'TransportDepService',
    function ($scope, TransportDepService) {
        var self = this;
        self.transportDep = {id: null, name: '', addres: '', phone: ''};
        self.driver = {id: null, firstname: '', name: '', surname: '', birthday: '', addres: '', phone: '', note: '', transportDep: {name: ""}};
        self.vehicle = {id: null, number: '', fuelRemnant: null, odometr: null, note: '', transportDep: {name: ""}};
        self.transportDeps = [];
        self.drivers = [];
        self.vehicles = [];



        self.fetchAllTransportDeps = function () {
            TransportDepService.fetchAllTransportDeps()
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

        //Функция передачи объекта по клику на строку таблицы
        self.addRowHandlers = function (tDep) {
            self.transportDep = tDep;
            self.fetchAllDrivers(tDep);
            self.fetchAllVehicles(tDep);
        };

        self.fetchAllDrivers = function (transportDep) {
            TransportDepService.fetchAllDrivers(transportDep)
                    .then(
                            function (d) {
                                self.drivers = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Drivers');
                                alert(errResponse);
                            }
                    );
        };
        self.fetchAllVehicles = function (transportDep) {
            TransportDepService.fetchAllVehicles(transportDep)
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


        self.fetchAllTransportDeps();

        self.createTransportDep = function (transportDep) {
            TransportDepService.createTransportDep(transportDep)
                    .then(
                            self.fetchAllTransportDeps,
                            function (errResponse) {
                                console.error('Error while creating TransportDep.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.createDriver = function (driver) {
            driver.transportDep = self.transportDep;
            TransportDepService.createDriver(driver)
                    .then(
                            self.fetchAllDrivers(self.transportDep),
                            function (errResponse) {
                                console.error('Error while creating Driver in TransportDep with id = ' + self.transportDep.id);
                                alert(errResponse);
                            }
                    );
        };

        self.updateDriver = function (driver) {
            TransportDepService.updateDriver(driver)
                    .then(
                            self.fetchAllDrivers(self.transportDep),
                            function (errResponse) {
                                console.error('Error while updating Driver in TransportDep with id = ' + self.transportDep.id);
                                alert(errResponse);
                            }
                    );
        };

        self.deleteDriver = function (driver) {
            TransportDepService.deleteDriver(driver)
                    .then(
                            self.fetchAllDrivers(self.transportDep),
                            function (errResponse) {
                                console.error('Error while deleting Driver in TransportDep with id = ' + self.transportDep.id);
                                alert(errResponse);
                            }
                    );
        };

        self.updateTransportDep = function (transportDep) {
            TransportDepService.updateTransportDep(transportDep)
                    .then(
                            self.fetchAllTransportDeps,
                            function (errResponse) {
                                console.error('Error while updating TransportDep.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.deleteTransportDep = function (transportDep) {
            TransportDepService.deleteTransportDep(transportDep)
                    .then(
                            self.fetchAllTransportDeps,
                            function (errResponse) {
                                console.error('Error while deleting TransportDep.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.createVehicle = function (vehicle) {
            vehicle.transportDep = self.transportDep;
            TransportDepService.createVehicle(vehicle)
                    .then(
                            self.fetchAllVehicles(self.transportDep),
                            function (errResponse) {
                                console.error('Error while creating Vehicle in TransportDep with id = ' + self.transportDep.id);
                                alert(errResponse);
                            }
                    );
        };

        self.updateVehicle = function (vehicle) {
            TransportDepService.updateVehicle(vehicle)
                    .then(
                            self.fetchAllVehicles(self.transportDep),
                            function (errResponse) {
                                console.error('Error while updating Vehicle in TransportDep with id = ' + self.transportDep.id);
                                alert(errResponse);
                            }
                    );
        };

        self.deleteVehicle = function (vehicle) {
            TransportDepService.deleteVehicle(vehicle)
                    .then(
                            self.fetchAllVehicles(self.transportDep),
                            function (errResponse) {
                                console.error('Error while deleting Vehicle in TransportDep with id = ' + self.transportDep.id);
                                alert(errResponse);
                            }
                    );
        };

        self.submitTransportDep = function () {
            if (self.transportDep.transportDepId === null) {
                self.createTransportDep(self.transportDep);
            } else {
                self.updateTransportDep(self.transportDep);
            }
            self.resetTransportDep();
        };

        self.submitDriver = function () {
            if (self.Driver.driverId === null) {
                self.createDriver(self.driver);
            } else {
                self.updateDriver(self.driver);
            }
            self.resetDriver();
        };

        self.submitVehicle = function () {
            if (self.Vehicle.vehicleId === null) {
                self.createVehicle(self.vehicle);
            } else {
                self.updateVehicle(self.vehicle);
            }
            self.resetVehicle();
        };

        self.editTransportDep = function (transportDep) {
            self.transportDep.id = transportDep.id;
            self.transportDep.name = transportDep.name;
            self.transportDep.addres = transportDep.addres;
            self.transportDep.phone = transportDep.phone;
        };

        self.editDriver = function (driver) {
            self.driver.id = driver.id;
            self.driver.firstname = driver.firstname;
            self.driver.name = driver.name;
            self.driver.surname = driver.surname;
            self.driver.birthday = driver.birthday;
            self.driver.addres = driver.addres;
            self.driver.phone = driver.phone;
            self.driver.note = driver.note;
            self.driver.transportDep = driver.transportDep;

        };
        self.editVehicle = function (vehicle) {
            self.vehicle.id = vehicle.id;
            self.vehicle.number = vehicle.number;
            self.vehicle.fuelRemnant = vehicle.fuelRemnant;
            self.vehicle.odometr = vehicle.odometr;
            self.vehicle.note = vehicle.note;
            self.vehicle.transportDep = vehicle.transportDep;
        };


        self.resetTransportDep = function () {
            self.transportDep = {id: null, name: '', addres: '', phone: ''};
        };

        self.resetDriver = function () {
            self.driver = {id: null, firstname: '', name: '', surname: '', birthday: '', addres: '', phone: '', note: '', transportDep: {name: ""}};
        };

        self.resetVehicle = function () {
            self.vehicle = {id: null, number: '', fuelRemnant: null, odometr: null, note: '', transportDep: {name: ""}};
        };
    }]);