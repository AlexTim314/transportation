'use strict';

App.factory('WaybillDownloadService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {           

            downloadWaybill: function (appointment) {
                return $http.post('/transportation/waybilldownload',
                        JSON.stringify(appointment), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while downloading waybill. Service.');
                                    return $q.reject(decodeURI(errResponse));
                                }
                        );
            }
        }
    }]);
