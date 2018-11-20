'use strict';

App.factory('DepartmentService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
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

            createDepartment: function (department) {
                return $http.post('/transportation/departments/create',
                        JSON.stringify(department), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while creating department');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            updateDepartment: function (department) {
                return $http.put('/transportation/departments/update/',
                        JSON.stringify(department), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating Department');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            deleteDepartment: function (department) {
                return $http({method: 'DELETE',
                    url: '/transportation/departments/delete/',
                    data: JSON.stringify(department),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting Department');
                            return $q.reject(errResponse);
                        }
                );
            }

        };

    }]);
