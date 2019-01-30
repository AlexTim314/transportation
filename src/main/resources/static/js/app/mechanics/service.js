
'use strict';

App.factory('MechanicsService', ['$http', '$q', '$document', function ($http, $q, $document) {

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

            fetchMechanics: function () {
                return $http.get('/transportation/dispatcher/mechanics')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching Mechanics');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            createMechanic: function (mechanic) {
                return $http.post('/transportation/dispatcher/mechanic_create',
                        JSON.stringify(mechanic), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while creating mechanic');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updateMechanic: function (mechanic) {
                return $http.put('/transportation/dispatcher/mechanic_update',
                        JSON.stringify(mechanic), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating mechanic');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteMechanic: function (mechanic) {
                return $http({method: 'DELETE',
                    url: '/transportation/dispatcher/mechanic_delete',
                    data: JSON.stringify(mechanic),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting mechanic');
                            return $q.reject(errResponse);
                        }
                );
            }

        };
    }]);