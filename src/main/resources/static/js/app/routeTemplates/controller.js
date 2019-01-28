'use strict';

App.controller('RouteTemplatesController', ['$scope', 'RouteTemplatesService',
    function ($scope, RouteTemplatesService) {
        var self = this;

        self.department = {id: null};
        self.workName = null;
        self.tIds = 0;
        self.places = [];

        self.routeTemplates = [];

        self.routeTemplate = {id: null, name: null, routeTasks: []};

        self.all = false;

        var idsArr = [];

        self.fetchDepartment = function () {
            RouteTemplatesService.fetchDepartment()
                    .then(
                            function (d) {
                                self.department = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Department');
                            }
                    );
        };

        self.fetchPlaces = function () {
            RouteTemplatesService.fetchPlaces()
                    .then(
                            function (d) {
                                self.places = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Places');
                            }
                    );
        };

        self.fetchRouteTemplates = function () {
            RouteTemplatesService.fetchRouteTemplates()
                    .then(
                            function (d) {
                                self.routeTemplates = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Bosses');
                            }
                    );
        };

        self.fetchDepartment();
        self.fetchPlaces();
        self.fetchRouteTemplates();

        self.createRouteTemplate = function () {
            self.routeTemplate.department = self.department;
            self.prepareRoute();
            RouteTemplatesService.createRouteTemplate(self.routeTemplate)
                    .then(
                            function (d) {
//                                self.routeTemplates.push(d);
                                self.fetchRouteTemplates();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while creating RouteTemplate.');
                            }
                    );
        };

        self.updateRouteTemplate = function () {
            self.prepareRoute();
            RouteTemplatesService.updateRouteTemplate(self.routeTemplate)
                    .then(
                            function (d) {
//                                self.routeTemplates.push(d);
                                self.fetchRouteTemplates();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while updating RouteTemplate.');
                            }
                    );
        };

        self.deleteRouteTemplates = function () {
            RouteTemplatesService.deleteRouteTemplates(idsArr)
                    .then(
                            function (d) {
                                formClose('del-route-templ-confirm');
                                self.fetchRouteTemplates();
                            },
                            function (errResponse) {
                                console.error('Error while deleting RouteTemplates.');
                            }
                    );
            formClose('del-route-templ-confirm');
        };

        self.submit = function () {
            if (self.routeTemplate.id === null) {
                self.createRouteTemplate();
            } else {
                self.updateRouteTemplate();
            }
        };

        self.addTask = function (p) {
            self.routeTemplate.routeTasks.push({id: null, place: p, workName: self.workName, tIds: self.tIds});
            self.tIds++;
            self.workName = null;
        };

        self.removeTask = function (tsk) {
            var k = -1;
            for (var i = 0; i < self.routeTemplate.routeTasks.length; i++) {
                if (tsk.tIds === self.routeTemplate.routeTasks[i].tIds) {
                    k = i;
                    break;
                }
            }
            self.routeTemplate.routeTasks.splice(k, 1);
        };

        self.prepareRoute = function () {
            for (var i = 0; i < self.routeTemplate.routeTasks.length; i++) {
                self.routeTemplate.routeTasks[i].id = null;
                self.routeTemplate.routeTasks[i].orderNum = i;
            }
        };

        self.tryToCreate = function () {
            self.tIds = 0;
            self.routeTemplate = {id: null, name: null, routeTasks: []};
            formOpen('formRouteTemplate');
        };

        self.tryToUpdate = function (routeTemplate) {
            self.tIds = 0;
            self.routeTemplate.id = routeTemplate.id;
            self.routeTemplate.name = routeTemplate.name;
            self.routeTemplate.department = routeTemplate.department;
            for (var i = 0; i < routeTemplate.routeTasks.length; i++) {
                var tsk = {
                    tIds: self.tIds,
                    id: null,
                    place: routeTemplate.routeTasks[i].place,
                    workName: routeTemplate.routeTasks[i].workName,
                    orderNum: routeTemplate.routeTasks[i].orderNum
                };
                self.tIds++;
                self.routeTemplate.routeTasks.push(tsk);
            }
            formOpen('formRouteTemplate');
        };

        self.tryToDelete = function () {
            idsArr = [];
            for (var i = 0; i < self.routeTemplates.length; i++) {
                if (self.routeTemplates[i].checked) {
                    idsArr.push(self.routeTemplates[i].id);
                }
            }
            formOpen('del-route-templ-confirm');
        };

        self.resetForm = function () {
            self.workName = null;
            self.routeTemplate = {id: null, name: null, routeTasks: []};
            formClose('formRouteTemplate');
        };

        self.checkAll = function () {
            for (var i = 0; i < self.routeTemplates.length; i++) {
                self.routeTemplates[i].checked = self.all;
            }
        };

    }]);

