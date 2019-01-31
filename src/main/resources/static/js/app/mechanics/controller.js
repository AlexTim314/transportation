'use strict';

App.controller('MechanicsController', ['$scope', 'MechanicsService',
    function ($scope, MechanicsService) {
        var self = this;

        self.transportDep = {id: null};

        self.mechanic = {id: null};
        self.mechanics = [];

        self.fetchTransportDep = function () {
            MechanicsService.fetchTransportDep()
                    .then(
                            function (d) {
                                self.transportDep = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching TransportDep');
                            }
                    );
        };

        self.fetchMechanics = function () {
            MechanicsService.fetchMechanics()
                    .then(
                            function (d) {
                                self.mechanics = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Bosses');
                            }
                    );
        };

        self.fetchTransportDep();
        self.fetchMechanics();

        self.createMechanic = function () {
            self.mechanic.transportDep = self.transportDep;
            MechanicsService.createMechanic(self.mechanic)
                    .then(
                            function (d) {
//                                self.mechanics.push(d);
                                self.fetchMechanics();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while creating Mechanic.');
                            }
                    );
        };

        self.updateMechanic = function () {
            MechanicsService.updateMechanic(self.mechanic)
                    .then(
                            function (d) {
//                                self.mechanics.push(d);
                                self.fetchMechanics();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while updating Mechanic.');
                            }
                    );
        };

        self.deleteMechanic = function () {
            MechanicsService.deleteMechanic(self.mechanic)
                    .then(
                            function (d) {
                                self.mechanic = {id: null};
                                formClose('del-mechanic-confirm');
                                self.fetchMechanics();
                            },
                            function (errResponse) {
                                console.error('Error while deleting Mechanic.');
                            }
                    );
        };

        self.submit = function () {
            if (self.mechanic.id === null) {
                self.createMechanic();
            } else {
                self.updateMechanic();
            }
        };

        self.tryToCreate = function () {
            self.mechanic = {id: null};
            formOpen('formMechanic');
        };

        self.tryToUpdate = function (mechanic) {
            self.mechanic.id = mechanic.id;
            self.mechanic.firstname = mechanic.firstname;
            self.mechanic.name = mechanic.name;
            self.mechanic.surname = mechanic.surname;
            self.mechanic.workmanship = mechanic.workmanship;
            self.mechanic.birthday = new Date(mechanic.birthday);
            self.mechanic.address = mechanic.address;
            self.mechanic.phone = mechanic.phone;
            self.mechanic.transportDep = mechanic.transportDep;
            formOpen('formMechanic');
        };

        self.tryToDelete = function (mechanic) {
            self.mechanic.id = mechanic.id;
            formOpen('del-mechanic-confirm');
        };

        self.resetForm = function () {
            self.mechanic = {id: null};
            formClose('formMechanic');
        };

    }]);

