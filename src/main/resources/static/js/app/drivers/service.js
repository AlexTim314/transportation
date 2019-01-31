
'use strict';

App.factory('DriversService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchTransportDep: function () {
                return $http.get('/transportation/transportDep')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching transportDep');
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

            createDriver: function (driver) {
                return $http.post('/transportation/dispatcher/driver_create',
                        JSON.stringify(driver), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while creating driver');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            
            createDriverInfo: function (driverInfo) {
                return $http.post('/transportation/dispatcher/driver_updateState',
                        JSON.stringify(driverInfo), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while creating driver');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updateDriver: function (driver) {
                return $http.put('/transportation/dispatcher/driver_update',
                        JSON.stringify(driver), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating driver');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteDriver: function (driver) {
                return $http({method: 'DELETE',
                    url: '/transportation/dispatcher/driver_delete',
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
            }

        };
    }]);