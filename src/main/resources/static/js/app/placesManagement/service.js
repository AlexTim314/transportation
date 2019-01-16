'use strict';

App.factory('PlacesManagementService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchAllPlaces: function () {
                return $http.get('/transportation/management/places')
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
            
            createPlace: function (place) {
                return $http.post('/transportation/management/place_create',
                        JSON.stringify(place), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while creating place');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updatePlace: function (place) {
                return $http.put('/transportation/management/place_update',
                        JSON.stringify(place), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating place');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deletePlace: function (place) {
                console.log(place);
                return $http({method: 'DELETE',
                    url: '/transportation/management/place_delete',
                    data: JSON.stringify(place),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting place');
                            return $q.reject(errResponse);
                        }
                );
            }
            
};
    }]);
