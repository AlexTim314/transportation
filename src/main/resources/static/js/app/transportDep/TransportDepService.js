'use strict';

App.factory('TransportDepService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchAllTransportDeps: function () {
                return $http.get('/transportation/transportDeps')
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while fetching transportDeps');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            getTransportDep: function () {
                return $http.get('/transportation/transportDeps/one')
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while fetching transportDeps');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchAllDrivers: function (transportDep) {
                return $http.post('/transportation/transportDeps/drivers',
                        JSON.stringify(transportDep), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while fetching Drivers in transportDep');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            createTransportDep: function (transportDep) {
                return $http.post('/transportation/transportDeps/create',
                        JSON.stringify(transportDep), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while creating transportDep');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            updateTransportDep: function (transportDep) {
                return $http.put('/transportation/transportDeps/update/',
                        JSON.stringify(transportDep), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating TransportDep');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            deleteTransportDep: function (transportDep) {
                console.log(transportDep);
                return $http({method: 'DELETE',
                    url: '/transportation/transportDeps/delete/',
                    data: JSON.stringify(transportDep),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting TransportDep');
                            return $q.reject(errResponse);
                        }
                );
            },
            createDriver: function (driver) {
                return $http.post('/transportation/transportDeps/drivers/create',
                        JSON.stringify(driver), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating TransportDep(createDriver)');
                                    return $q.reject(errResponse);
                                }
                        );

            },

            updateDriver: function (driver) {
                return $http.put('/transportation/transportDeps/drivers/update',
                        JSON.stringify(driver), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating Driver (TransportDep)');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            deleteDriver: function (driver) {
                return $http({method: 'DELETE',
                    url: '/transportation/transportDeps/drivers/delete/',
                    data: JSON.stringify(driver),
                    headers: self.headers,

                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting Driver (TransportDep)');
                            return $q.reject(errResponse);
                        }
                );
            },

            fetchAllVehicles: function (transportDep) {
                return $http.post('/transportation/transportDeps/vehicles',
                        JSON.stringify(transportDep), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while fetching Vehicles in transportDep');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            createVehicle: function (vehicle) {
                return $http.post('/transportation/transportDeps/vehicles/create',
                        JSON.stringify(vehicle), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating TransportDep (createVehicle)');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updateVehicle: function (vehicle) {
                return $http.put('/transportation/transportDeps/vehicles/update',
                        JSON.stringify(vehicle), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating Vehicle (TransportDep)');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            deleteVehicle: function (vehicle) {
                return $http({method: 'DELETE',
                    url: '/transportation/transportDeps/vehicles/delete/',
                    data: JSON.stringify(vehicle),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting Vehicle (TransportDep)');
                            return $q.reject(errResponse);
                        }
                );
            },

        };

    }]);
