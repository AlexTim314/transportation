'use strict';

App.controller('WaypointController', ['$scope', 'WaypointService',
    function ($scope, WaypointService) {
        var self = this;
        self.waypoint = {id: null, name: '', latitude: '', longitude: ''};
        self.distance = {id: null, dist: '', end_point_id: null, start_point_id: null};
        self.waypoints = [];
        self.distances = [];


        self.fetchAllWaypoints = function () {
            WaypointService.fetchAllWaypoints()
                    .then(
                            function (d) {
                                self.waypoints = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Waypoints');
                                showAlert(errResponse);
                            }
                    );
        };
        
        self.fetchAllDistances = function () {
            WaypointService.fetchAllDistances()
                    .then(
                            function (d) {
                                self.distances = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Distances');
                                showAlert(errResponse);
                            }
                    );
        };

        self.fetchAllWaypoints();
        self.fetchAllDistances();
  
        self.createWaypoint = function (waypoint) {
            WaypointService.createWaypoint(waypoint)
                    .then(
                            self.fetchAllWaypoints,
                            function (errResponse) {
                                console.error('Error while creating Waypoint.');
                                showAlert(errResponse);
                            }
                    );
        };
        
        self.createDistance = function (distance) {
            WaypointService.createDistance(distance)
                    .then(
                            self.fetchAllDistances,
                            function (errResponse) {
                                console.error('Error while creating Distance.');
                                showAlert(errResponse);
                            }
                    );
        };
        
        self.updateWaypoint = function (waypoint) {
            WaypointService.updateWaypoint(waypoint)
                    .then(
                            self.fetchAllWaypoints,
                            function (errResponse) {
                                console.error('Error while updating Waypoint.');
                                showAlert(errResponse);
                            }
                    );
        };
        
        self.updateDistance = function (distance) {
            WaypointService.updateDistance(distance)
                    .then(
                            self.fetchAllDistances,
                            function (errResponse) {
                                console.error('Error while updating Distance.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.deleteWaypoint = function (waypoint) {
            WaypointService.deleteWaypoint(waypoint)
                    .then(
                            self.fetchAllWaypoints,
                            function (errResponse) {
                                console.error('Error while deleting Waypoint.');
                                showAlert(errResponse);
                            }
                    );
        };
        
        self.deleteDistance = function (distance) {
            WaypointService.deleteDistance(distance)
                    .then(
                            self.fetchAllDistances,
                            function (errResponse) {
                                console.error('Error while deleting Distance.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.submitWaypoint = function () {
            if (self.waypoint.waypointId === null) {
                self.createWaypoint(self.waypoint);
            } else {
                self.updateWaypoint(self.waypoint);
            }
            self.reset();
        };
        
        self.submitDistance = function () {
            if (self.distance.distanceId === null) {
                self.createDistance(self.distance);
            } else {
                self.updateDistance(self.distance);
            }
            self.reset();
        };

        self.editWaypoint = function (waypoint) {
            self.waypoint.id = waypoint.id;
            self.waypoint.name = waypoint.name;
            self.waypoint.latitude = waypoint.latitude;
            self.waypoint.longitude = waypoint.longitude;
        };

        self.resetWaypoint = function () {
            self.waypoint = {id: null, name: '', latitude: '', longitude: ''};
        };
        
        self.resetDistance = function () {
            self.distance = {id: null, dist: '', end_point_id: null, start_point_id: null};
        };


    }]);