'use strict';

App.factory('TransportDepService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchAllTransportDeps: function () {
                return $http.get('/transportation/transportDeps')
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

            fetchAllDrivers: function (id) {
                return $http.get('/transportation/transportDeps/'+id+'/drivers')
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
                return $http.post('/transportation/transportDeps/create',
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
                return $http.put('/transportation/transportDeps/update/',
                        JSON.stringify(transportDep), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating TransportDep');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            deleteTransportDep: function (id) {
               // console.log(id);
                return $http({method: 'DELETE',
                    url: '/transportation/transportDeps/delete/' + id,
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting TransportDep');
                            return $q.reject(errResponse);
                        }
                );
            },
            createDriver: function (idTransportDep, driver) {
              //  console.log(idTransportDep + ' ' + driver)
                return $http.post('/transportation/transportDeps/' + idTransportDep + '/drivers/create',
                        JSON.stringify(driver), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating TransportDep(createDriver)');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            
             updateDriver: function (idTransportDep, driver) {
                return $http.put('/transportation/transportDeps/' + idTransportDep + '/drivers/' + driver.id + '/update',
                        JSON.stringify(driver), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating Driver (TransportDep)');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            deleteDriver: function (idDriver,idTransportDep) {
                console.log('id Driver = '+idDriver);
                return $http({method: 'DELETE',
                    url: '/transportation/transportDeps/'+idTransportDep+'/drivers/delete/' + idDriver,
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting Driver (TransportDep)');
                            return $q.reject(errResponse);
                        }
                );
            },
            
            fetchAllVechicles: function (id) {
                return $http.get('/transportation/transportDeps/'+id+'/vechicles')
                        .then(
                                function (response) {
                                    return response.data;

                                },
                                function (errResponse) {
                                    console.error('Error while fetching Vechicles in transportDep');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            createVechicle: function (idTransportDep, vechicle) {
                return $http.post('/transportation/transportDeps/' + idTransportDep + '/vechicles/create',
                        JSON.stringify(vechicle), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating TransportDep (createVechicle)');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            
             updateVechicle: function (idTransportDep, vechicle) {
                return $http.put('/transportation/transportDeps/' + idTransportDep + '/vechicles/' + vechicle.id + '/update',
                        JSON.stringify(vechicle), {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating Vechicle (TransportDep)');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            deleteVechicle: function (idVechicle, idTransportDep) {
                console.log('id Vechicle = '+idVechicle);
                return $http({method: 'DELETE',
                    url: '/transportation/transportDeps/'+idTransportDep+'/vechicles/delete/' + idVechicle,
                    headers: self.headers
                }).then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while deleting Vechicle (TransportDep)');
                            return $q.reject(errResponse);
                        }
                );
            },

        };

    }]);