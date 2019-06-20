'use strict';

App.factory('PlannerService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            getPermit: function () {
                return $http.get('/transportation/planner/permit')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching permit');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            getUserName: function () {
                return $http.get('/transportation/planner/username')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching username');
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

//            fetchMonthPlanRecords: function () {
//                return $http.get('/transportation/planner/affirmedClaims/Month')
//                        .then(
//                                function (response) {
//                                    return response.data;
//                                },
//                                function (errResponse) {
//                                    console.error('Error while fetching monthRecs');
//                                    return $q.reject(errResponse);
//                                }
//                        );
//            },
            fetchMonthBeforePlanRecords: function () {
                return $http.get('/transportation/planner/claims/archive')
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

            fetchVehicleTypes: function () {
                return $http.get('/transportation/vehicleTypes')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching vehicleTypes');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchWeekPlanRecords: function () {
                return $http.get('/transportation/planner/claims/week')
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

            fetchInfo: function (ids) {
                return $http.post('/transportation/planner/info',
                        JSON.stringify(ids), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching information from Record');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchTomorrowPlanRecords: function () {
                return $http.get('/transportation/planner/claims/tomorrow')
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

//            fetchBosses: function () {
//                return $http.get('/transportation/user/carBosses')
//                        .then(
//                                function (response) {
//                                    return response.data;
//                                },
//                                function (errResponse) {
//                                    console.error('Error while fetching bosses');
//                                    return $q.reject(errResponse);
//                                }
//                        );
//            },

            fetchDatePlanRecords: function (date) {
                return $http.post('/transportation/planner/claims/day',
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

            /* printPlan: function (date) {
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
             */

            fetchMonthCompletePlanRecords: function () {
                return $http.get('/transportation/planner/plannedClaims/Month')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching monthRecs');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchMonthBeforeCompletePlanRecords: function () {
                return $http.get('/transportation/planner/plannedClaims/monthBefore')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching monthBefRecs');
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

            fetchVehicles: function () {
                return $http.get('/transportation/planner/vehicles')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching vehicles');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchDrivers: function () {
                return $http.get('/transportation/planner/drivers')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching drivers');
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
            fetchDateFromServer: function () {
                return $http.get('/transportation/getNow')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching date from server');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            fetchPlaces: function () {
                return $http.get('/transportation/places')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching places');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchCarBosses: function () {
                return $http.get('/transportation/planner/carBosses')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching bosses');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchOtsInfo: function () {
                return $http.get('/transportation/planner/ots_info')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching otsInfo');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchOtsVehModels: function () {
                return $http.get('/transportation/planner/transport_dep_models')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching otsVehModels');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchDepartments: function () {
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

            fetchRouteTasks: function () {
                return $http.get('/transportation/routeTasks')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching routeTasks');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchRouteTemplates: function (id) {
                return $http.post('/transportation/planner/routeTemplates',
                        JSON.stringify(id), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching routeTemplates');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            createCarBoss: function (carBoss) {
                return $http.post('/transportation/planner/carBoss_create',
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
                return $http.put('/transportation/planner/carBoss_update',
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
                    url: '/transportation/planner/carBoss_delete',
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

            createClaim: function (claim) {
                return $http.post('/transportation/planner/claim_create',
                        JSON.stringify(claim), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while creating claim');
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



