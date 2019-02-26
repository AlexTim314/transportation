'use strict';

App.controller('VehiclesController', ['$scope', 'VehiclesService',
    function ($scope, VehiclesService) {
        var self = this;

        self.transportDep = {id: null};
        self.vehicleTypes = [];
        self.models = [];
        self.fuels = [];

        self.fuel = {id: null};

        self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}, fuels: []};
        self.vehicles = [];
        self.vehicleInfo = {id: null};
        self.refueling = {id: null, refuelingDate: '', volume: '', fuel: null, vehicle: null}
        
        self.history = [];

        self.all = false;

        var idsArr = [];

        self.fetchTransportDep = function () {
            VehiclesService.fetchTransportDep()
                    .then(
                            function (d) {
                                self.transportDep = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching TransportDep');
                            }
                    );
        };

        self.fetchVehicleTypes = function () {
            VehiclesService.fetchVehicleTypes()
                    .then(
                            function (d) {
                                self.vehicleTypes = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching VehicleTypes');
                            }
                    );
        };

        self.fetchVehicleModels = function () {
            VehiclesService.fetchVehicleModels()
                    .then(
                            function (d) {
                                self.models = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Models');
                            }
                    );
        };

        self.fetchFuels = function () {
            VehiclesService.fetchFuels()
                    .then(
                            function (d) {
                                self.fuels = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Fuels');
                            }
                    );
        };

        self.fetchVehicles = function () {
            VehiclesService.fetchVehicles()
                    .then(
                            function (d) {
                                self.vehicles = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Vehicles');
                            }
                    );
        };
        
        self.getVehicleHistory = function (vehicle) {
            self.vehicle = vehicle;
            VehiclesService.fetchVehicleHistory(vehicle)
                    .then(
                            function (d) {
                                self.history = d;
                                formOpen('history-transport');
                            },
                            function (errResponse) {
                                console.error('Error while fetching Vehicle History');
                            }
                    );
        };

        self.fetchTransportDep();
        self.fetchVehicleTypes();
        self.fetchVehicleModels();
        self.fetchFuels();
        self.fetchVehicles();

        self.createVehicle = function () {
            self.vehicle.transportDep = self.transportDep;
            VehiclesService.createVehicle(self.vehicle)
                    .then(
                            function (d) {
                                self.fetchVehicles();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while creating Vehicle.');
                            }
                    );
        };

        self.createVehicleInfo = function () {
            self.vehicleInfo.vehicle = self.vehicle;
            VehiclesService.createVehicleInfo(self.vehicleInfo)
                    .then(
                            function (d) {
                                self.fetchVehicles();
                                self.closeStateForm();
                                self.closeStatusForm();
                                self.vehicle.status = d.status;
                                self.vehicle.ododmetr = d.ododmetr;
                                self.vehicle.fuel = d.fuel;
                                self.vehicle.motohours = d.motohours;
                            },
                            function (errResponse) {
                                console.error('Error while creating Vehicle.');
                            }
                    );
        };

        self.updateVehicle = function () {
            VehiclesService.updateVehicle(self.vehicle)
                    .then(
                            function (d) {
                                self.fetchVehicles();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while updating Vehicle.');
                            }
                    );
        };

        self.deleteVehicles = function () {
            VehiclesService.deleteVehicles(idsArr)
                    .then(
                            function (d) {
                                formClose('del-vehicle-confirm');
                                self.fetchVehicles();
                            },
                            function (errResponse) {
                                console.error('Error while deleting Vehicle.');
                            }
                    );
        };

        self.submit = function () {
            if (self.vehicle.id === null) {
                self.createVehicle();
            } else {
                self.updateVehicle()();
            }
        };

        self.tryToCreate = function () {
            self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}};
            formOpen('formTransport');
        };

        self.tryToUpdate = function (vehicle) {
            self.vehicle.id = vehicle.id;
            self.vehicle.model = vehicle.model;
            self.vehicle.number = vehicle.number;
            self.vehicle.fuels = vehicle.fuels;
            self.vehicle.fuel = vehicle.fuel;
            self.vehicle.ododmetr = vehicle.ododmetr;
            self.vehicle.motohours = vehicle.motohours;
            self.vehicle.status = vehicle.status;
            self.vehicle.note = vehicle.note;
            self.vehicle.transportDep = vehicle.transportDep;
            formOpen('formTransport');
        };

        self.tryToDelete = function () {
            idsArr = [];
            for (var i = 0; i < self.vehicles.length; i++) {
                if (self.vehicles[i].checked) {
                    idsArr.push(self.vehicles[i].id);
                }
            }
            formOpen('del-vehicle-confirm');
        };

        self.tryToUpdateVehicleState = function (vehicle) {
            self.vehicle = vehicle;
            self.vehicleInfo.status = vehicle.status;
            self.vehicleInfo.fuel = vehicle.fuel;
            self.vehicleInfo.odometr = vehicle.odometr;
            self.vehicleInfo.motohours = vehicle.motohours;
            formOpen('formCurrentParametr');
        };

        self.tryToUpdateVehicleStatus = function (vehicle) {
            self.vehicle = vehicle;
            self.vehicleInfo.status = vehicle.status;
            self.vehicleInfo.fuel = vehicle.fuel;
            self.vehicleInfo.odometr = vehicle.odometr;
            self.vehicleInfo.motohours = vehicle.motohours;
            self.vehicleInfo.note = null;
            formOpen('formChangeVehicleStatus')
        };

        self.resetForm = function () {
            self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}, fuels: []};
            formClose('formTransport');
        };

        self.checkAll = function () {
            for (var i = 0; i < self.vehicles.length; i++) {
                self.vehicles[i].checked = self.all;
            }
        };

        self.addFuel = function () {
            self.vehicle.fuels.push(self.fuel);
            self.fuel = {id: null};
        };

        self.removeFuel = function (fuel) {
            var k = -1;
            for (var i = 0; i < self.vehicles.length; i++) {
                if (self.vehicles[i].id === fuel.id) {
                    k = i;
                    break;
                }
            }
            self.vehicle.fuels.splice(k, 1);
            self.fuel = {id: null};
        };

        self.openInfoForm = function (vehicle) {
            self.vehicle = vehicle;
            formOpen('more-transport');
        };

        self.closeInfoForm = function () {
            self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}, fuels: []};
            formClose('more-transport');
        };

        self.closeStateForm = function () {
            self.vehicleInfo = {id: null};
            formClose('formCurrentParametr');
        };

        self.closeStatusForm = function () {
            self.vehicleInfo = {id: null};
            formClose('formChangeVehicleStatus')
        };
        
        self.closeVehicleHistoryForm = function () {
            self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}, fuels: []};
            formClose('history-transport')
        };
        
        self.shortFullName = function (user) {
            var nameArr = user.fullName.split(' ');
            var result = nameArr[0];
            if (nameArr.length > 1) {
                result += " " + nameArr[1].charAt(0) + ".";
            }
            if (nameArr.length > 2) {
                result += nameArr[2].charAt(0) + "."
            }
            return result;
        };
        
        self.tryToRefuelingVehicle = function (vehicle) {
            self.refueling.vehicle = vehicle;
            formOpen('formRefueling');
        };
        
        self.refuelingVehicle = function () {
            console.log(self.refueling);
            VehiclesService.refulingVehicle(self.refueling)
                    .then(
                            function (d) {
                                console.log('Refueling Sucsessible');
                                formClose('formRefueling');
                            },
                            function (errResponse) {
                                console.error('Error while creating Refueling.');
                            }
                    );
        };
        
        self.cancelRefueling = function () {
            self.refueling = {id: null, refuelingDate: '', volume: '', fuel: null, vehicle: null};
            formClose('formRefueling');
        };

    }]);

