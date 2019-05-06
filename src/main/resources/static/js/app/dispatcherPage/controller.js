'use strict';

App.controller('DispatcherPageController', ['$scope', 'DispatcherPageService',
    function ($scope, DispatcherPageService) {
        var self = this;
        self.transportDep = {id: null};
        self.permit = false;
        
        self.getPermit = function () {
            DispatcherPageService.getPermit()
                    .then(
                            function (d) {
                                self.permit = d;
                                console.log(self.permit);
                            },
                            function (errResponse) {
                                console.error('Error while fetching Permit');
                            }
                    );
        };

       self.getUserName = function () {
            DispatcherPageService.getUserName()
                    .then(
                            function (d) {
                                self.username = d.username;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Username');
                            });
        };

        self.getPermit();
        self.getUserName();

        self.fetchTransportDep = function () {
            DispatcherPageService.fetchTransportDep()
                    .then(
                            function (d) {
                                self.transportDep = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching TransportDep');
                            }
                    );
        };

        self.fetchTransportDep();

    }]);

