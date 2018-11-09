'use strict';

App.controller('TransportDepController', ['$scope', 'TransportDepService',
    function ($scope, TransportDepService) {
        var self = this;
        self.transportDep = {id: null, name: '', addres: '', phone: ''};
        self.transportDeps = [];
        self.drivers = [];
        self.driver = {id: null, firstname: '', name: '', surname: '', birthday: '', addres: '', phone: '', note: '',transportDep: {name: ""}};


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
        
         self.addRowHandlers = function() {
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
        //self.fetchAllDrivers(5);
        
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
            var idTransportDep = 6;
            TransportDepService.createDriver(idTransportDep, driver)
                    .then(
                            self.fetchAllDrivers(idTransportDep),
                            function (errResponse) {
                                console.error('Error while creating Driver in TransportDep.');
                                showAlert(errResponse);
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

        self.submit = function () {
            if (self.transportDep.transportDepId === null) {
                self.createTransportDep(self.transportDep);
            } else {
                self.updateTransportDep(self.transportDep);
            }
            self.reset();
        };

        self.editTransportDep = function (transportDep) {
            self.transportDep.id = transportDep.id;
            self.transportDep.name = transportDep.name;
            self.transportDep.addres = transportDep.addres;
            self.transportDep.phone = transportDep.phone;
        };



        self.reset = function () {
            self.transportDep = {id: null, name: '', addres: '', phone: ''};
        };

    }]);