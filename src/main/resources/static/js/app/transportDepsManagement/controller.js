'use strict';

App.controller('TransportDepsManagementController', ['$scope', 'TransportDepsManagementService',
    function ($scope, TransportDepsManagementService) {
        var self = this;
        self.transportDep = {id: null, shortname: '', fullname: '', address: '', phone: ''};
        self.transportDeps = [];

        self.fetchAllTransportDeps = function () {
            TransportDepsManagementService.fetchAllTransportDeps()
                    .then(
                            function (d) {
                                self.transportDeps = d;
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching TransportDeps');
                            }
                    );
        };
        
        self.fetchAllTransportDeps();
        
         self.createTransportDep = function (transportDep) {
            TransportDepsManagementService.createTransportDep(transportDep)
                    .then(
                            self.fetchAllTransportDeps,
                            function (errResponse) {
                                console.error('Error while creating TransportDep.');
                            }
                    );
        };

        self.updateTransportDep = function (transportDep) {
           TransportDepsManagementService.updateTransportDep(transportDep)
                    .then(
                            self.fetchAllTransportDeps,
                            function (errResponse) {
                                console.error('Error while updating TransportDep.');
                            }
                    );
        };

        self.deleteTransportDep = function (transportDep) {
            TransportDepsManagementService.deleteTransportDep(transportDep)
                    .then(
                            self.fetchAllTransportDeps,
                            function (errResponse) {
                                console.error('Error while deleting TransportDep.');
                            }
                    );
        };
                
         self.submit = function () {
            if (self.transportDep.id === null) {
                self.createTransportDep(self.transportDep);
            } else {
                self.updateTransportDep(self.transportDep);
            }
            self.reset();
        };

        self.editTransportDep = function (transportDep) {
            self.transportDep.id = transportDep.id;
            self.transportDep.shortname = transportDep.shortname;
            self.transportDep.fullname = transportDep.fullname;
            self.transportDep.address = transportDep.address;
            self.transportDep.phone = transportDep.phone;
        };
               
        self.reset = function () {
            self.transportDep = {id: null, shortname: '', fullname: '', address: '', phone: ''};
        };

    }]);