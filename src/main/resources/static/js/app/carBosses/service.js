
'use strict';

App.factory('CarBossesService', ['$http', '$q', '$document', function ($http, $q, $document) {

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
                                    console.log(response.data)
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching department');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchCarBosses: function () {
                return $http.get('/transportation/user/carBosses')
                        .then(
                                function (response) {
                                    console.log(response.data)
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching bosses');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            createCarBoss: function (carBoss) {
                return $http.post('/transportation/user/carBoss_create',
                        JSON.stringify(carBoss), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while creating carBoss');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updateCarBoss: function (carBoss) {
                return $http.put('/transportation/user/carBoss_update',
                        JSON.stringify(carBoss), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating carBoss');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteCarBoss: function (carBoss) {
                return $http({method: 'DELETE',
                    url: '/transportation/user/carBoss_delete',
                    data: JSON.stringify(carBoss),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting carBoss');
                            return $q.reject(errResponse);
                        }
                );
            }

        };
    }]);