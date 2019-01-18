
'use strict';

App.factory('ClaimsService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchClaims: function () {
                return $http.get('/transportation/user/claims')
                        .then(
                                function (response) {console.log(response.data)
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching claims');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            createClaim: function (claim) {
                return $http.post('/transportation/user/claim_create',
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

            updateClaim: function (claim) {
                return $http.put('/transportation/user/claim_update',
                        JSON.stringify(claim), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating claim');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteClaim: function (claim) {
                console.log(claim);
                return $http({method: 'DELETE',
                    url: '/transportation/user/claim_delete',
                    data: JSON.stringify(claim),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting claim');
                            return $q.reject(errResponse);
                        }
                );
            },
            
            fetchVehicleTypes: function () {
                return $http.get('/transportation/vehicleTypes')
                        .then(
                                function (response) {console.log(response.data)
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching vehicleTypes');
                                    return $q.reject(errResponse);
                                }
                        );
            }

        };
    }]);