'use strict';

App.factory('PlanService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            
            fetchRecords: function (date,status) {

                return $http.get('/transportation/plan/records/'+date+'/'+status)
                        .then(
                                function (response) {
                                    console.log('fetching records');
                                    console.log(response.data);
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while fetching Records');
                                    return $q.reject(decodeURI(errResponse));
                                }
                        );
            },
            
        };
        
    }]);