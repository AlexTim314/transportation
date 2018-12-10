'use strict';

App.factory('ClaimService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchClaims: function (startDate, endDate) {
                return $http.get('/transportation/claims/byUser/' + startDate + '/' + endDate)
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while fetching claims');
                                    return $q.reject(decodeURI(errResponse));
                                }
                        );
            },

            fetchAllRecords: function (claim) {
                console.log(claim);
                return $http.post('/transportation/claims/byUser/records',
                        JSON.stringify(claim), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while fetching Records in Claim');
                                    return $q.reject(decodeURI(errResponse));
                                }
                        );
            },

        }
    }]);