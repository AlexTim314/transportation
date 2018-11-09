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

            fetchAllDrivers: function (id) {
                return $http.get('/transportation/transportDeps/'+id+'/drivers')
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
            deleteTransportDep: function (id) {
                console.log(id);
                return $http({method: 'DELETE',
                    url: '/transportation/transportDeps/delete/' + id,
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
            createDriver: function (idTransportDep, driver) {
                console.log(idTransportDep + ' ' + driver)
                return $http.post('/transportation/transportDeps/' + idTransportDep + '/drivers/create',
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

        };

    }]);