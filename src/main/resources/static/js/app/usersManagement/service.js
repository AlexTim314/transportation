'use strict';

App.factory('UsersManagementService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchAllUsers: function () {
                return $http.get('/transportation/management/users')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching users');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            fetchAllRoles: function () {
                return $http.get('/transportation/management/roles')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching roles');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            fetchAllDepartments: function () {
                return $http.get('/transportation/departments')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching departments');
                                    return $q.reject(errResponse);
                                }
                        );
            },
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
            createUser: function (user) {
                return $http.post('/transportation/management/user_create',
                        JSON.stringify(user), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while creating user');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            updateUser: function (user) {
                return $http.put('/transportation/management/user_update',
                        JSON.stringify(user), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating user');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            updateDepartments: function (departments) {
                return $http.put('/transportation/management/user_update_departments',
                        JSON.stringify(departments), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating departments');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            deleteUser: function (user) {
                return $http({method: 'DELETE',
                    url: '/transportation/management/user_delete',
                    data: JSON.stringify(user),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting user');
                            return $q.reject(errResponse);
                        }
                );
            }

        };

    }]);


