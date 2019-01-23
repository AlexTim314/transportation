'use strict';

App.controller('FuelsManagementController', ['$scope', 'FuelsManagementService',
    function ($scope, FuelsManagementService) {
        var self = this;
        self.fuel = {id: null, mark: '', fuelType: ''};
        self.fuels = [];

        self.fetchAllFuels = function () {
            FuelsManagementService.fetchAllFuels()
                    .then(
                            function (d) {
                                self.fuels = d;
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching Fuels');
                            }
                    );
        };

        self.fetchAllFuels();

        self.createFuel = function (fuel) {
            FuelsManagementService.createFuel(fuel)
                    .then(
                            self.fetchAllFuels,
                            function (errResponse) {
                                console.error('Error while creating Fuel.');
                            }
                    );
        };

        self.updateFuel = function (fuel) {
            FuelsManagementService.updateFuel(fuel)
                    .then(
                            self.fetchAllFuels,
                            function (errResponse) {
                                console.error('Error while updating Fuel.');
                            }
                    );
        };

        self.deleteFuel = function (fuel) {
            FuelsManagementService.deleteFuel(fuel)
                    .then(
                            self.fetchAllFuels,
                            function (errResponse) {
                                console.error('Error while deleting Fuel.');
                            }
                    );
        };

        self.submitFuel = function () {
            if (self.fuel.id === null) {
                self.createFuel(self.fuel);
            } else {
                self.updateFuel(self.fuel);
            }
            self.resetFuel();
        };

        self.editFuel = function (fuel) {
            self.fuel.id = fuel.id;
            self.fuel.fuelType = fuel.fuelType;
            self.fuel.mark = fuel.mark;
        };

        self.resetFuel = function () {
            self.fuel = {id: null, mark: '', fuelType: ''};
        };

        //Функция передачи объекта по клику на строку таблицы
        self.setFuel = function (vehMark) {
            self.fuel = vehMark;
            self.fetchVehicleModelsByMark();
        };


    }]);
