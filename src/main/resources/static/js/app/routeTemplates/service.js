
'use strict';

App.factory('RouteTemplatesService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchDepartment: function () {
                return $http.get('/transportation/department')
                        .then(
                                function (response) {
                                    console.log(response.data)
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching department');
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

            fetchRouteTemplates: function () {
                return $http.get('/transportation/user/routeTemplates')
                        .then(
                                function (response) {
                                    console.log(response.data)
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching bosses');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            createRouteTemplate: function (routeTemplate) {
                return $http.post('/transportation/user/routeTemplate_create',
                        JSON.stringify(routeTemplate), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while creating routeTemplate');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updateRouteTemplate: function (routeTemplate) {
                return $http.put('/transportation/user/routeTemplate_update',
                        JSON.stringify(routeTemplate), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating routeTemplate');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteRouteTemplates: function (idsArr) {
                return $http({method: 'DELETE',
                    url: '/transportation/user/routeTemplates_delete',
                    data: JSON.stringify(idsArr),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting routeTemplates');
                            return $q.reject(errResponse);
                        }
                );
            }

        };
    }]);