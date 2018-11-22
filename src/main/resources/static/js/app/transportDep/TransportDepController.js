'use strict';

App.controller('TransportDepController', ['$scope', 'TransportDepService',
    function ($scope, TransportDepService) {
        var self = this;
        self.transportDep = {id: null, name: '', addres: '', phone: ''};
        self.driver = {id: null, firstname: '', name: '', surname: '', birthday: '', addres: '', phone: '', note: '', transportDep: {name: ""}};
        self.vechicle = {id: null, number: '', fuelRemnant: null, odometr: null, note: '', transportDep: {name: ""}};
        self.transportDeps = [];
        self.drivers = [];
        self.vechicles = [];



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
            self.fetchAllVechicles(tDep);
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
        self.fetchAllVechicles = function (transportDep) {
            TransportDepService.fetchAllVechicles(transportDep)
                    .then(
                            function (d) {
                                self.vechicles = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Vechicles');
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

        self.createVechicle = function (vechicle) {
            vechicle.transportDep = self.transportDep;
            TransportDepService.createVechicle(vechicle)
                    .then(
                            self.fetchAllVechicles(self.transportDep),
                            function (errResponse) {
                                console.error('Error while creating Vechicle in TransportDep with id = ' + self.transportDep.id);
                                alert(errResponse);
                            }
                    );
        };

        self.updateVechicle = function (vechicle) {
            TransportDepService.updateVechicle(vechicle)
                    .then(
                            self.fetchAllVechicles(self.transportDep),
                            function (errResponse) {
                                console.error('Error while updating Vechicle in TransportDep with id = ' + self.transportDep.id);
                                alert(errResponse);
                            }
                    );
        };

        self.deleteVechicle = function (vechicle) {
            TransportDepService.deleteVechicle(vechicle)
                    .then(
                            self.fetchAllVechicles(self.transportDep),
                            function (errResponse) {
                                console.error('Error while deleting Vechicle in TransportDep with id = ' + self.transportDep.id);
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

        self.submitVechicle = function () {
            if (self.Vechicle.vechicleId === null) {
                self.createVechicle(self.vechicle);
            } else {
                self.updateVechicle(self.vechicle);
            }
            self.resetVechicle();
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
        self.editVechicle = function (vechicle) {
            self.vechicle.id = vechicle.id;
            self.vechicle.number = vechicle.number;
            self.vechicle.fuelRemnant = vechicle.fuelRemnant;
            self.vechicle.odometr = vechicle.odometr;
            self.vechicle.note = vechicle.note;
            self.vechicle.transportDep = vechicle.transportDep;
        };


        self.resetTransportDep = function () {
            self.transportDep = {id: null, name: '', addres: '', phone: ''};
        };

        self.resetDriver = function () {
            self.driver = {id: null, firstname: '', name: '', surname: '', birthday: '', addres: '', phone: '', note: '', transportDep: {name: ""}};
        };

        self.resetVechicle = function () {
            self.vechicle = {id: null, number: '', fuelRemnant: null, odometr: null, note: '', transportDep: {name: ""}};
        };
    }]);