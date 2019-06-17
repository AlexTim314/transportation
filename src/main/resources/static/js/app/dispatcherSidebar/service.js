'use strict';

App.factory('DispatcherSidebarService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            
            fetchDriversInfo: function () {
                return $http.get('/transportation/dispatcher/driverInfo')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching drivers info from server');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            
            fetchVehiclesInfo: function () {
                return $http.get('/transportation/dispatcher/ots_veh_info')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching vehicles info from server');
                                    return $q.reject(errResponse);
                                }
                        );
            }
            
        };
    }]);


