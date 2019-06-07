'use strict';

App.factory('DispatcherService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {

            fetchMonthPlanRecords: function () {
                return $http.get('/transportation/dispatcher/appointments/Month')
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

            fetchMonthBeforeRecords: function () {
                return $http.get('/transportation/dispatcher/appointments/monthBefore')
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

            fetchWeekPlanRecords: function () {
                return $http.get('/transportation/dispatcher/appointments/Week')
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
                return $http.get('/transportation/dispatcher/appointments/Tomorrow')
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
                return $http.post('/transportation/dispatcher/appointments/Date',
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

            fetchAllVehicleModels: function () {
                return $http.get('/transportation/dispatcher/vehicleModels')
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

            fetchDrivers: function () {
                return $http.get('/transportation/dispatcher/drivers')
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

            fetchVacantDrivers: function (app) {
                return $http.post('/transportation/dispatcher/vacantDrivers',
                        JSON.stringify(app), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while fetching vacant drivers');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchVehicles: function () {
                return $http.get('/transportation/dispatcher/vehicles')
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

            fetchVacantVehicles: function (app) {
                return $http.post('/transportation/dispatcher/vacantVehicles',
                        JSON.stringify(app), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while fetching vacant Vehicle');
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

            fetchCarBosses: function () {
                return $http.get('/transportation/dispatcher/carBosses')
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
            fetchDepartment: function () {
                return $http.get('/transportation/department')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching department');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchRouteTemplates: function () {
                return $http.get('/transportation/dispatcher/routeTemplates')
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

//            createAppointment: function (appointments) {
//                return $http.post('/transportation/planner/appointments_create',
//                        JSON.stringify(appointments), {headers: self.headers})
//                        .then(
//                                function (response) {
//                                    return response.data;
//                                    
//                                },
//                                function (errResponse) {
//                                    console.error('Error while creating appointments');
//                                    return $q.reject(errResponse);
//                                }
//                        );
//            },

            downloadWaybill: function (appointment) {
                return $http.get('/transportation/dispatcher/waybilldownload/' + appointment.id)
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while download waybill');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updateAppointments: function (appointments) {
                return $http.put('/transportation/dispatcher/appointments_update',
                        JSON.stringify(appointments), {headers: self.headers})
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

            updateStatusAppointment: function (appointment) {
                return $http.put('/transportation/dispatcher/appointment_update',
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

            createCarBoss: function (carBoss) {
                return $http.post('/transportation/dispatcher/carBoss_create',
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
                return $http.put('/transportation/dispatcher/carBoss_update',
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
                    url: '/transportation/dispatcher/carBoss_delete',
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

