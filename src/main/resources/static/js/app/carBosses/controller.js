'use strict';

App.controller('CarBossesController', ['$scope', 'CarBossesService',
    function ($scope, CarBossesService) {
        var self = this;

        self.department = {id: null};

        self.carBoss = {id: null};
        self.carBosses = [];

        self.fetchDepartment = function () {
            CarBossesService.fetchDepartment()
                    .then(
                            function (d) {
                                self.department = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Department');
                            }
                    );
        };

        self.fetchCarBosses = function () {
            CarBossesService.fetchCarBosses()
                    .then(
                            function (d) {
                                self.carBosses = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Bosses');
                            }
                    );
        };

        self.fetchDepartment();
        self.fetchCarBosses();

        self.createCarBoss = function () {
            self.carBoss.department = self.department;
            CarBossesService.createCarBoss(self.carBoss)
                    .then(
                            function (d) {
//                                self.carBosses.push(d);
                                self.fetchCarBosses();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while creating CarBoss.');
                            }
                    );
        };

        self.updateCarBoss = function (carBoss) {
            CarBossesService.updateCarBoss(carBoss)
                    .then(
                            function (d) {
//                                self.carBosses.push(d);
                                self.fetchCarBosses();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while updating CarBoss.');
                            }
                    );
        };

        self.deleteCarBoss = function () {
            CarBossesService.deleteCarBoss(self.carBoss)
                    .then(
                            function (d) {
                                self.carBoss = {id: null};
                                formClose('del-car-boss-confirm');
                                self.fetchCarBosses();
                            },
                            function (errResponse) {
                                console.error('Error while deleting CarBoss.');
                            }
                    );
            formClose('del-car-boss-confirm');
        };

        self.tryToCreate = function () {
            self.carBoss = {id: null};
            formOpen('formCarBoss');
        };

        self.tryToUpdate = function (carBoss) {
            self.carBoss.id = carBoss.id;
            self.carBoss.firstname = carBoss.firstname;
            self.carBoss.name = carBoss.name;
            self.carBoss.surname = carBoss.surname;
            self.carBoss.post = carBoss.post;
            self.carBoss.birthday = carBoss.birthday;
            self.carBoss.address = carBoss.address;
            self.carBoss.phone = carBoss.phone;
            self.carBoss.department = carBoss.department;
            formOpen('formCarBoss');
        };

        self.tryToDelete = function (carBoss) {
            self.carBoss = carBoss;
            formOpen('del-car-boss-confirm');
        };

        self.resetForm = function () {
            self.carBoss = {id: null};
            formClose('formCarBoss');
        };

    }]);

