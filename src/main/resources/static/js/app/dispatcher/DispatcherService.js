'use strict';

App.factory('DispatcherService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchAllVehicleTypes: function () {
                return $http.get('/transportation/vehicleTypes')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching types');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            fetchAllModels: function () {
                return $http.get('/transportation/vehicleModels')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching models');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            fetchDrivers: function () {
                return $http.get('/transportation/dispatcher/drivers')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching drivers');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            fetchVehicles: function () {
                return $http.get('/transportation/dispatcher/vehicles')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching vehicles');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            saveDriver: function (driver) {
                return $http.post('/transportation/dispatcher/save_driver',
                        JSON.stringify(driver), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while saving driver');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            saveVehicle: function (vehicle) {
                return $http.post('/transportation/dispatcher/save_vehicle',
                        JSON.stringify(vehicle), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while saving vehicle');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            deleteDriver: function (driver) {
                return $http({method: 'DELETE',
                    url: '/transportation/dispatcher/delete_driver',
                    data: JSON.stringify(driver),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting driver');
                            return $q.reject(errResponse);
                        }
                );
            },
            deleteVehicle: function (vehicle) {
                return $http({method: 'DELETE',
                    url: '/transportation/dispatcher/delete_vehicle',
                    data: JSON.stringify(vehicle),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting vehicle');
                            return $q.reject(errResponse);
                        }
                );
            }

        };

    }]);


