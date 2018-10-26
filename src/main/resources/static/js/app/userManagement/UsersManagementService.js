'use strict';

App.factory('UsersManagementService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchAllUsers: function () {
                return $http.get('/transportation/users')
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
                return $http.get('/transportation/roles')
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
            createUser: function (user) {
                return $http.post('/transportation/users/create',
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
                return $http.put('/transportation/users/update',
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
            deleteUser: function (user) {
                console.log(user);
                return $http({method: 'DELETE',
                    url: '/transportation/users/delete',
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


