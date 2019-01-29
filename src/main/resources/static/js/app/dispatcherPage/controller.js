'use strict';

App.controller('DispatcherPageController', ['$scope', 'DispatcherPageService',
    function ($scope, DispatcherPageService) {
        var self = this;
        self.transportDep = {id: null};

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

