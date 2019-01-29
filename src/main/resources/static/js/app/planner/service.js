'use strict';

App.factory('PlannerService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchAllDepartments: function () {
                return $http.get('/transportation/planner/departments')
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
            
            fetchAllClaims: function () {
                return $http.get('/transportation/planner/claims')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching claims');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            
            fetchAllAppointments: function () {
                return $http.get('/transportation/planner/appointments')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching appointments');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            
            fetchAllDepsRecords: function () {
                return $http.get('/transportation/planner/affirmedClaims')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching allRecs');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            
            fetchTransportDeps: function () {
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
            
            fetchAllVehicleModels: function () {
                return $http.get('/transportation/vehicleModels')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching vehicleModels');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            createAppointment: function (appointment) {
                return $http.post('/transportation/planner/appointment_create',
                        JSON.stringify(appointment), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                    
                                },
                                function (errResponse) {
                                    console.error('Error while creating appointment');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updateAppointment: function (appointment) {
                return $http.put('/transportation/planner/appointment_update',
                        JSON.stringify(appointment), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating appointment');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteAppointment: function (appointment) {
                console.log(appointment);
                return $http({method: 'DELETE',
                    url: '/transportation/planner/appointment_delete',
                    data: JSON.stringify(appointment),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting appointment');
                            return $q.reject(errResponse);
                        }
                );
            }

        };
    }]);



