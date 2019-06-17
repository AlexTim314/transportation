'use strict';
App.controller('DispatcherSidebarController', ['$scope', 'DispatcherSidebarService',
    function ($scope, DispatcherSidebarService) {
        var self = this;

        self.driversInfo;
        self.tdInfo;
        self.numVehicles;


        self.fetchDriversInfo = function () {
            DispatcherSidebarService.fetchDriversInfo()
                    .then(
                            function (d) {
                                self.driversInfo = d[0];
                            },
                            function (errResponse) {
                                console.error('Error while fetching Deps from server');
                            }
                    );
        };
        self.fetchVehiclesInfo = function () {
            DispatcherSidebarService.fetchVehiclesInfo()
                    .then(
                            function (d) {
                                self.tdInfo = d[0];
                                self.numVehicles = 0;
                                for (var i = 0; i <self.tdInfo.vehicleModelInfos.length; i++) {
                                    self.numVehicles = self.numVehicles +  self.tdInfo.vehicleModelInfos[i].count;
                                }
                            },
                            function (errResponse) {
                                console.error('Error while fetching Deps from server');
                            }
                    );
        };

        self.fetchDriversInfo();
        self.fetchVehiclesInfo();

        self.selectSmallIcon = function (spec) {
            var bus = 'fas fa-bus-alt';
            var car = 'fas fa-car';
            var truck = 'fas fa-truck';
            var tractor = 'fas fa-tractor';
            switch (spec) {
                case 0:
                    return bus;
                case 1:
                    return car;
                case 2:
                    return truck;
                case 3:
                    return tractor;
            }
        };

    }]);
