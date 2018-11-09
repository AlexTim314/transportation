'use strict';

App.controller('TransportDepController', ['$scope', 'TransportDepService',
    function ($scope, TransportDepService) {
        var self = this;
        var idTransportDep;
        self.transportDep = {id: null, name: '', addres: '', phone: ''};
        self.transportDeps = [];
        self.drivers = [];
        self.driver = {id: null, firstname: '', name: '', surname: '', birthday: '', addres: '', phone: '', note: '', transportDep: {name: ""}};


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
                                idTransportDep = id;
                                self.fetchAllDrivers(id);
                                // alert("id:" + id);
                            };
                        };

                currentRow.onclick = createClickHandler(currentRow);
            }
        }

        self.fetchAllDrivers = function (id) {
            TransportDepService.fetchAllDrivers(id)
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



        self.fetchAllTransportDeps();
       // self.fetchAllDrivers(idTransportDep);

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
            TransportDepService.createDriver(idTransportDep, driver)
                    .then(
                            self.fetchAllDrivers(idTransportDep),
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
            TransportDepService.deleteDriver(idDriver)
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

        self.deleteTransportDep = function (id) {
            TransportDepService.deleteTransportDep(id)
                    .then(
                            self.fetchAllTransportDeps,
                            function (errResponse) {
                                console.error('Error while deleting TransportDep.');
                                showAlert(errResponse);
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



        self.resetTransportDep = function () {
            self.transportDep = {id: null, name: '', addres: '', phone: ''};
        };
        
        self.resetDriver = function () {
            self.driver = {id: null, firstname: '', name: '', surname: '', birthday: '', addres: '', phone: '', note: '', transportDep: {name: ""}};
        };

    }]);