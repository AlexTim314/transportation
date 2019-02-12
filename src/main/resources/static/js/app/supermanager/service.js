'use strict';

App.factory('SuperManagerService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {

            fetchAllRecords: function () {
                return $http.get('/transportation/supermanager/records')
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

            fetchWeekRecords: function () {
                return $http.get('/transportation/supermanager/records/Week')
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

            fetchTomorrowRecords: function () {
                return $http.get('/transportation/supermanager/records/Tomorrow')
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

            fetchDateRecords: function (date) {
                return $http.post('/transportation/supermanager/records/Date',
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
            
            affirmRecords: function (affIds) {
                return $http.put('/transportation/supermanager/records_affirm',
                        JSON.stringify(affIds), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating records');
                                    return $q.reject(errResponse);
                                }
                        );
            }

        };
    }]);
        