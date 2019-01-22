'use strict';

App.factory('FuelsManagementService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchAllFuels: function () {
                return $http.get('/transportation/management/fuels')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching fuels');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            createFuel: function (fuel) {
                return $http.post('/transportation/management/fuel_create',
                        JSON.stringify(fuel), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while creating fuel');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updateFuel: function (fuel) {
                return $http.put('/transportation/management/fuel_update',
                        JSON.stringify(fuel), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating fuel');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteFuel: function (fuel) {
                console.log(fuel);
                return $http({method: 'DELETE',
                    url: '/transportation/management/fuel_delete',
                    data: JSON.stringify(fuel),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting fuel');
                            return $q.reject(errResponse);
                        }
                );
            },
        };
    }]);


