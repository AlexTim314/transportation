
'use strict';

App.factory('ClaimsService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchNewClaims: function () {
                return $http.get('/transportation/user/newClaims')
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

            fetchAffirmedClaims: function () {
                return $http.get('/transportation/user/affirmedClaims')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching affirmedClaims');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchAffirmedClaimsTomorrow: function () {
                return $http.get('/transportation/user/affirmedClaims/Tomorrow')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching AffirmedClaimsTomorrow');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchAffirmedClaimsWeek: function () {
                return $http.get('/transportation/user/affirmedClaims/Week')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching AffirmedClaimsWeek');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchСlaimTemplates: function () {
                return $http.get('/transportation/user/claimTemplates')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching claimTemplates');
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

            fetchRouteTemplates: function () {
                return $http.get('/transportation/user/routeTemplates')
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

            fetchBosses: function () {
                return $http.get('/transportation/user/carBosses')
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
                return $http.get('/transportation/user/department')
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

            affirmClaims: function (affIds) {
                return $http.put('/transportation/user/claims_affirm',
                        JSON.stringify(affIds), {headers: self.headers})
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

            deleteClaims: function (dc) {
                return $http({method: 'DELETE',
                    url: '/transportation/user/claims_delete',
                    data: JSON.stringify(dc),
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

            deleteRecord: function (clmId, recId) {
                return $http({method: 'DELETE',
                    url: '/transportation/user/record_delete',
                    data: JSON.stringify([clmId, recId]),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting record');
                            return $q.reject(errResponse);
                        }
                );
            }

        };
    }]);
