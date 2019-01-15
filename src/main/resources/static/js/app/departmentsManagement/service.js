'use strict';

App.factory('DepartmentsManagementService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchAllDepartments: function () {
                return $http.get('/transportation/management/departments')
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
                return $http.post('/transportation/management/department_create',
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
                return $http.put('/transportation/management/department_update',
                        JSON.stringify(department), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating department');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteDepartment: function (department) {
                console.log(department);
                return $http({method: 'DELETE',
                    url: '/transportation/management/department_delete',
                    data: JSON.stringify(department),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting department');
                            return $q.reject(errResponse);
                        }
                );
            }

        };
    }]);