
'use strict';

App.factory('ClaimsPageService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            
            getPermit: function () {
                return $http.get('/transportation/user/permit')
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
                return $http.get('/transportation/user/username')
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
            }
        };
    }]);