'use strict';

App.factory('VehicleTypesManagementService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchAllVehicleTypes: function () {
                return $http.get('/transportation/management/vehicleTypes')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching vehicleTypes');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchAllVehicleModels: function () {
                return $http.get('/transportation/management/vehicleModels')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching vehicleModels');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            
            fetchVehicleModelsByType: function (vehicleType){
                 return $http.post('/transportation/management/vehicleModelsByVehicleType',
                        JSON.stringify(vehicleType), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching vehicleModels by Type');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            createVehicleType: function (vehicleType) {
                return $http.post('/transportation/management/vehicleType_create',
                        JSON.stringify(vehicleType), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while creating vehicleType');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            createVehicleModel: function (vehicleModel) {
                return $http.post('/transportation/management/vehicleModel_create',
                        JSON.stringify(vehicleModel), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while creating vehicleModel');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updateVehicleType: function (vehicleType) {
                return $http.put('/transportation/management/vehicleType_update',
                        JSON.stringify(vehicleType), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating vehicleType');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updateVehicleModel: function (vehicleModel) {
                return $http.put('/transportation/management/vehicleModel_update',
                        JSON.stringify(vehicleModel), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating vehicleModel');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteVehicleType: function (vehicleType) {
                console.log(vehicleType);
                return $http({method: 'DELETE',
                    url: '/transportation/management/vehicleType_delete',
                    data: JSON.stringify(vehicleType),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting vehicleType');
                            return $q.reject(errResponse);
                        }
                );
            },

            deleteVehicleModel: function (vehicleModel) {
                console.log(vehicleModel);
                return $http({method: 'DELETE',
                    url: '/transportation/management/vehicleModel_delete',
                    data: JSON.stringify(vehicleModel),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting vehicleModel');
                            return $q.reject(errResponse);
                        }
                );
            }

        };
    }]);


