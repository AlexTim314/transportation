'use strict';

App.factory('DispatcherService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            
            
            fetchAllPlanRecords: function () {
                return $http.get('/transportation/dispatcher/appointments')
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
            }

        };
    }]);

