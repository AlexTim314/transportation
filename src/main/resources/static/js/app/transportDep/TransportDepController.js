'use strict';

App.controller('TransportDepController', ['$scope', 'TransportDepService',
    function ($scope, TransportDepService) {
        var self = this;
        var idTransportDep;
        var transpDep;
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

        self.addRowHandlers = function () {
            var table = document.getElementById("transportDep-table");
            var rows = table.getElementsByTagName("tr");
            for (var i = 0; i < rows.length; i++) {
                var currentRow = table.rows[i];
                var createClickHandler =
                        function (row)
                        {
                            return function () {
                                var cell = row.getElementsByTagName("td")[0];
                                var id = cell.innerHTML;
                                cell = row.getElementsByTagName("td")[1];
                                var name = cell.innerHTML;
                                cell = row.getElementsByTagName("td")[2];
                                var addres = cell.innerHTML;
                                cell = row.getElementsByTagName("td")[3];
                                var phone = cell.innerHTML;
                                transpDep = '{"id": ' + id + ', "name": "'+name+'", "addres": "'+addres+'", "phone": "'+phone+'"}';
                                transpDep = JSON.parse(transpDep);
                                console.log(transpDep);
                                idTransportDep = id;
                                self.fetchAllDrivers(transpDep);
                                self.fetchAllVechicles(transpDep);
                            };
                        };

                currentRow.onclick = createClickHandler(currentRow);
            }
        };

        self.fetchAllDrivers = function (transportDep) {
            TransportDepService.fetchAllDrivers(transportDep)
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
           driver.transportDep = transpDep; 
//           console.log(driver.transportDep.id);
//           console.log(driver.transportDep);
           console.log(driver);
            TransportDepService.createDriver(driver,transpDep)
                    .then(
                            self.fetchAllDrivers(transpDep),
                            function (errResponse) {
                                console.error('Error while creating Driver in TransportDep with id = ' + idTransportDep);
                                showAlert(errResponse);
                            }
                    );
        };

        self.updateDriver = function (idTransportDep, driver) {
            TransportDepService.updateDriver(idTransportDep, driver)
                    .then(
                            self.fetchAllDrivers(idTransportDep),
                            function (errResponse) {
                                console.error('Error while updating Driver in TransportDep with id = ' + idTransportDep);
                                showAlert(errResponse);
                            }
                    );
        };

        self.deleteDriver = function (idDriver) {
            TransportDepService.deleteDriver(idDriver, idTransportDep)
                    .then(
                            self.fetchAllDrivers(idTransportDep),
                            function (errResponse) {
                                console.error('Error while deleting Driver in TransportDep with id = ' + idTransportDep);
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
            TransportDepService.createVechicle(idTransportDep, vechicle)
                    .then(
                            self.fetchAllVechicles(idTransportDep),
                            function (errResponse) {
                                console.error('Error while creating Vechicle in TransportDep with id = ' + idTransportDep);
                                showAlert(errResponse);
                            }
                    );
        };

        self.updateVechicle = function (idTransportDep, vechicle) {
            TransportDepService.updateVechicle(idTransportDep, vechicle)
                    .then(
                            self.fetchAllVechicles(idTransportDep),
                            function (errResponse) {
                                console.error('Error while updating Vechicle in TransportDep with id = ' + idTransportDep);
                                showAlert(errResponse);
                            }
                    );
        };

        self.deleteVechicle = function (idVechicle) {
            TransportDepService.deleteVechicle(idVechicle, idTransportDep)
                    .then(
                            self.fetchAllVechicles(idTransportDep),
                            function (errResponse) {
                                console.error('Error while deleting Vechicle in TransportDep with id = ' + idTransportDep);
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
                self.updateDriver(idTransportDep, self.driver);
            }
            self.resetDriver();
        };

        self.submitVechicle = function () {
            if (self.Vechicle.vechicleId === null) {
                self.createVechicle(self.vechicle);
            } else {
                self.updateVechicle(idTransportDep, self.vechicle);
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