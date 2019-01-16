'use strict';

App.controller('VehicleTypesManagementController', ['$scope', 'VehicleTypesManagementService',
    function ($scope, VehicleTypesManagementService) {
        var self = this;
        self.vehicleType = {id: null, type: '', vehicleModels: []};
        self.vehicleModel = {id: null, modelName: '', vehicleType: ''};
        self.vehicleTypes = [];
        self.vehicleModels = [];

        self.fetchAllVehicleTypes = function () {
            VehicleTypesManagementService.fetchAllVehicleTypes()
                    .then(
                            function (d) {
                                self.vehicleTypes = d;
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching VehicleTypes');
                            }
                    );
        };
        
        self.fetchAllVehicleModels = function () {
            VehicleTypesManagementService.fetchAllVehicleModels()
                    .then(
                            function (d) {
                                self.vehicleModels = d;
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching VehicleModels');
                            }
                    );
        };
        
        self.fetchAllVehicleTypes();
        self.fetchAllVehicleModels();
        
        
        self.createVehicleType = function (vehicleType) {
            VehicleTypesManagementService.createVehicleType(vehicleType)
                    .then(
                            self.fetchAllVehicleTypes,
                            function (errResponse) {
                                console.error('Error while creating VehicleType.');
                            }
                    );
        };
        
        self.createVehicleModel = function (vehicleModel) {
            VehicleTypesManagementService.createVehicleModel(vehicleModel)
                    .then(
                            self.fetchAllVehicleModels,
                            function (errResponse) {
                                console.error('Error while creating VehicleModel.');
                            }
                    );
        };

        self.updateVehicleType = function (vehicleType) {
           VehicleTypesManagementService.updateVehicleType(vehicleType)
                    .then(
                            self.fetchAllVehicleTypes,
                            function (errResponse) {
                                console.error('Error while updating VehicleType.');
                            }
                    );
        };
        
        self.updateVehicleModel = function (vehicleModel) {
           VehicleTypesManagementService.updateVehicleModel(vehicleModel)
                    .then(
                            self.fetchAllVehicleModels,
                            function (errResponse) {
                                console.error('Error while updating VehicleModel.');
                            }
                    );
        };

        self.deleteVehicleType = function (vehicleType) {
            VehicleTypesManagementService.deleteVehicleType(vehicleType)
                    .then(
                            self.fetchAllVehicleTypes,
                            function (errResponse) {
                                console.error('Error while deleting VehicleType.');
                            }
                    );
        };
        
        self.deleteVehicleModel = function (vehicleModel) {
            VehicleTypesManagementService.deleteVehicleModel(vehicleModel)
                    .then(
                            self.fetchAllVehicleModels,
                            function (errResponse) {
                                console.error('Error while deleting VehicleModel.');
                            }
                    );
        };
                
        self.submitVehicleType = function () {
            if (self.vehicleType.id === null) {
                self.createVehicleType(self.vehicleType);
            } else {
                self.updateVehicleType(self.vehicleType);
            }
            self.reset();
        };
        
        self.submitVehicleType = function () {
            if (self.vehicleType.id === null) {
                self.createVehicleType(self.vehicleType);
            } else {
                self.updateVehicleType(self.vehicleType);
            }
            self.reset();
        };

        self.editVehicleType = function (vehicleType) {
            self.vehicleType.id = vehicleType.id;

        };
               
        self.resetVehicleType = function () {
            self.vehicleType = {id: null, type: '', vehicleModels: []};
        };

    }]);
