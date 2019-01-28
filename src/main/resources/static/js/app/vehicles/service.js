
'use strict';

App.factory('VehiclesService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchDepartment: function () {
                return $http.get('/transportation/department')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching department');
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
                                    console.error('Error while fetching bosses');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            createVehicle: function (vehicle) {
                return $http.post('/transportation/dispatcher/vehicle_create',
                        JSON.stringify(vehicle), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while creating vehicle');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updateVehicle: function (vehicle) {
                return $http.put('/transportation/dispatcher/vehicle_update',
                        JSON.stringify(vehicle), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating vehicle');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteVehicle: function (vehicle) {
                return $http({method: 'DELETE',
                    url: '/transportation/dispatcher/vehicle_delete',
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