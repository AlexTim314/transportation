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

            createClaim: function (claim) {
                var str = document.getElementById("formRecord-id");
                str.style.display = "none";
                return $http.post('/transportation/claims/byUser/create',
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

            createRecord: function (record) {
                return $http.post('/transportation/claims/byUser/records/create',
                        JSON.stringify(record), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while creating record');
                                    return $q.reject(errResponse);
                                }
                        );
            },
//            updateClaim: function (claim) {
//                return $http.put('/transportation/claims/byUser/update/',
//                        JSON.stringify(claim), {headers: self.headers})
//                        .then(
//                                function (response) {
//                                    return response.data;
//                                },
//                                function (errResponse) {
//                                    console.error('Error while updating Claim');
//                                    return $q.reject(errResponse);
//                                }
//                        );
//            },

            updateRecord: function (record) {
                return $http.put('/transportation/claims/byUser/records/update/',
                        JSON.stringify(record), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating Recod');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteClaim: function (claim) {
                return $http({method: 'DELETE',
                    url: '/transportation/claims/byUser/delete/',
                    data: JSON.stringify(claim),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting Claim');
                            return $q.reject(errResponse);
                        }
                );
            },

            deleteRecord: function (record) {
                return $http({method: 'DELETE',
                    url: '/transportation/claims/byUser/records/delete/',
                    data: JSON.stringify(record),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting Record');
                            return $q.reject(errResponse);
                        }
                );
            }

        }
    }]);
