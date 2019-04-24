
'use strict';

App.factory('DispatcherPageService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            getPermit: function () {
                return $http.get('/transportation/dispatcher/permit')
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
                return $http.get('/transportation/dispatcher/username')
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
            fetchTransportDep: function () {
                return $http.get('/transportation/transportDep')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching transportDep');
                                    return $q.reject(errResponse);
                                }
                        );
            }
        };
    }]);