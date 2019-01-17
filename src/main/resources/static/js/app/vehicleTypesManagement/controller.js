'use strict';

App.controller('VehicleTypesManagementController', ['$scope', 'VehicleTypesManagementService',
    function ($scope, VehicleTypesManagementService) {
        var self = this;
        self.vehicleType = {id: null, type: '', specialization: ''};
        self.vehicleModel = {id: null, modelName: '', vehicleType: {type: ""}};
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
        
        self.fetchVehicleModelsByType = function () {
            VehicleTypesManagementService.fetchVehicleModelsByType(self.vehicleType)
                    .then(
                            function (d) {
                                self.vehicleModels = d;
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching VehicleModels by Type');
                            }
                    );
        };

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
            vehicleModel.vehicleType = self.vehicleType;
            VehicleTypesManagementService.createVehicleModel(vehicleModel)
                    .then(
                            self.fetchVehicleModelsByType,
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
                            self.fetchVehicleModelsByType,
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
                            self.fetchVehicleModelsByType,
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
            self.resetVehicleType();
        };

        self.submitVehicleModel = function () {
            if (self.vehicleModel.id === null) {
                self.createVehicleModel(self.vehicleModel);
            } else {
                self.updateVehicleModel(self.vehicleModel);
            }
            self.resetVehicleModel();
        };

        self.editVehicleType = function (vehicleType) {
            self.vehicleType.id = vehicleType.id;
            self.vehicleType.specialization = vehicleType.specialization;
            self.vehicleType.type = vehicleType.type;

        };

        self.editVehicleModel = function (vehicleModel) {
            self.vehicleModel.id = vehicleModel.id;
            self.vehicleModel.modelName = vehicleModel.modelName;
            self.vehicleModel.vehicleType = vehicleModel.vehicleType;

        };

        self.resetVehicleType = function () {
            self.vehicleType = {id: null, type: '', specialization: ''};
        };

        self.resetVehicleModel = function () {
            self.vehicleModel = {id: null, modelName: '', vehicleType: {type: ""}};
        };
        
        //Функция передачи объекта по клику на строку таблицы
        self.setVehicleType = function (vehType) {
            self.vehicleType = vehType;
            self.fetchVehicleModelsByType();

            
           
            
        };
        

    }]);
