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

            fetchAllPlanRecords: function () {
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

            fetchWeekPlanRecords: function () {
                return $http.get('/transportation/planner/affirmedClaims/Week')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching weekRecs');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchTomorrowPlanRecords: function () {
                return $http.get('/transportation/planner/affirmedClaims/Tomorrow')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching tomorrowRecs');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchDatePlanRecords: function (date) {
                return $http.post('/transportation/planner/affirmedClaims/Date',
                        JSON.stringify(date), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while fetching Recs of date');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            printPlan: function (date) {
                return $http.post('/transportation/planner/plandownload',
                        JSON.stringify(date), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while fetching Recs of date');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchAllCompletePlanRecords: function () {
                return $http.get('/transportation/planner/plannedClaims')
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

            fetchWeekCompletePlanRecords: function () {
                return $http.get('/transportation/planner/plannedClaims/Week')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching weekRecs');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchTomorrowCompletePlanRecords: function () {
                return $http.get('/transportation/planner/plannedClaims/Tomorrow')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching tomorrowRecs');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchDateCompletePlanRecords: function (date) {
                return $http.post('/transportation/planner/plannedClaims/Date',
                        JSON.stringify(date), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while fetching Recs of date');
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

            createAppointments: function (appointments) {
                return $http.post('/transportation/planner/appointments_create',
                        JSON.stringify(appointments), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while creating appointments');
                                    return $q.reject(errResponse);
                                }
                        );
            },

//            updateAppointment: function (appointment) {
//                return $http.put('/transportation/planner/appointment_update',
//                        JSON.stringify(appointment), {headers: self.headers})
//                        .then(
//                                function (response) {
//                                    return response.data;
//                                },
//                                function (errResponse) {
//                                    console.error('Error while updating appointment');
//                                    return $q.reject(errResponse);
//                                }
//                        );
//            },

            cancelRecord: function (cmpRec) {
                console.log(cmpRec);
                return $http.put('/transportation/planner/recordCancel',
                        JSON.stringify(cmpRec), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while canceled record');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            
            updateRoute: function (claim) {
                return $http.put('/transportation/planner/route_update',
                        JSON.stringify(claim), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating route');
                                    return $q.reject(errResponse);
                                }
                        );
            }, 
            
            updateTime: function (record) {
                return $http.put('/transportation/planner/time_update',
                        JSON.stringify(record), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating time');
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



