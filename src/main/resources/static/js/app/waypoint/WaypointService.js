'use strict';

App.factory('WaypointService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchAllWaypoints: function () {
                return $http.get('/transportation/waypoints')
                        .then(
                                function (response) {
                                    
                                    
                                    
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while fetching Waypoints');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetchAllDistances: function () {
                return $http.get('/transportation/distances')
                        .then(
                                function (response) {

                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while fetching Distances');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            createWaypoint: function (waypoint) {
                waypoint.latitude = document.getElementById("latitude").value;
                waypoint.longitude = document.getElementById("longitude").value;
                return $http.post('/transportation/waypoints/create',
                        JSON.stringify(waypoint), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while creating waypoint');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            createDistance: function (distance) {
                return $http.post('/transportation/distances/create',
                        JSON.stringify(distance), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while creating distance');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updateWaypoint: function (waypoint) {
                return $http.put('/transportation/waypoints/update/',
                        JSON.stringify(waypoint), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating waypoint');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updateDistance: function (distance) {
                return $http.put('/transportation/distances/update/',
                        JSON.stringify(distance), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating distance');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteWaypoint: function (waypoint) {
                console.log(waypoint);
                return $http({method: 'DELETE',
                    url: '/transportation/waypoints/delete/',
                    data: JSON.stringify(waypoint),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting waypoint');
                            return $q.reject(errResponse);
                        }
                );
            },

            deleteDistance: function (distance) {
                console.log(distance);
                return $http({method: 'DELETE',
                    url: '/transportation/distances/delete/',
                    data: JSON.stringify(distance),
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting distance');
                            return $q.reject(errResponse);
                        }
                );
            },

        };

    }]);
