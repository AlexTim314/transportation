'use strict';

App.factory('TransportDepsManagementService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchAllTransportDeps: function () {
                return $http.get('/transportation/management/transportDeps')
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
            
            createTransportDep: function (transportDep) {
                return $http.post('/transportation/management/transportDep_create',
                        JSON.stringify(transportDep), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while creating transportDep');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updateTransportDep: function (transportDep) {
                return $http.put('/transportation/management/transportDep_update',
                        JSON.stringify(transportDep), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating transportDep');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteTransportDep: function (transportDep) {
                console.log(transportDep);
                return $http({method: 'DELETE',
                    url: '/transportation/management/transportDep_delete',
                    data: JSON.stringify(transportDep),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting transportDep');
                            return $q.reject(errResponse);
                        }
                );
            }
            
};
    }]);
