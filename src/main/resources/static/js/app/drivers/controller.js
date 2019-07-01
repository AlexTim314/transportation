'use strict';

App.controller('DriversController', ['$scope', 'DriversService',
    function ($scope, DriversService) {
        var self = this;

        self.transportDep = {id: null};

        self.driver = {id: null};
        self.driverInfo = {id: null};
        self.drivers = [];
        self.today;

        self.fetchTransportDep = function () {
            DriversService.fetchTransportDep()
                    .then(
                            function (d) {
                                self.transportDep = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching TransportDep');
                            }
                    );
        };

        self.fetchDrivers = function () {
            DriversService.fetchDrivers()
                    .then(
                            function (d) {
                                self.drivers = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Drivers');
                            }
                    );
        };

        self.getDateFromServer = function () {
            DriversService.fetchDateFromServer()
                    .then(
                            function (d) {
                                self.today = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching NewClaims');
                            }
                    );
        };

//        self.setBirthday = function () {
//            var date = new Date(self.today);
//            var day = date.getDate();
//            var month = date.getMonth() + 1;
//            var year = date.getFullYear() - 18;
//            if (month < 10)
//                month = "0" + month;
//            if (day < 10)
//                day = "0" + day;
//            var maxDay = year + "-" + month + "-" + day;
//            document.getElementById('birthday').value = maxDay;
//            document.getElementById('birthday').max = maxDay;
//        };

        self.fetchTransportDep();
        self.fetchDrivers();

        self.createDriver = function () {
            self.driver.transportDep = self.transportDep;
            DriversService.createDriver(self.driver)
                    .then(
                            function (d) {
//                                self.drivers.push(d);
                                self.fetchDrivers();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while creating Driver.');
                            }
                    );
        };

        self.createDriverInfo = function () {
            self.driverInfo.driver = self.driver;
            DriversService.createDriverInfo(self.driverInfo)
                    .then(
                            function (d) {
//                                self.drivers.push(d);
                                self.fetchDrivers();
                                self.closeStatusForm();
                            },
                            function (errResponse) {
                                console.error('Error while creating DriverInfo.');
                            }
                    );
        };

        self.updateDriver = function () {
            DriversService.updateDriver(self.driver)
                    .then(
                            function (d) {
//                                self.drivers.push(d);
                                self.fetchDrivers();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while updating Driver.');
                            }
                    );
        };

        self.deleteDriver = function () {
            DriversService.deleteDriver(self.driver)
                    .then(
                            function (d) {
                                self.driver = {id: null};
                                self.closeDeleteForm();
                                self.fetchDrivers();
                            },
                            function (errResponse) {
                                console.error('Error while deleting Driver.');
                            }
                    );
        };
        
        self.closeDeleteForm = function() {
            formClose('del-driver-confirm');
            formClose('cover-trsp1');
        };

        self.submit = function () {
            if (self.driver.id === null) {
                self.createDriver();
            } else {
                self.updateDriver();
            }
        };

        self.tryToCreate = function () {
            self.driver = {id: null};
            formOpen('cover-trsp1');
            formOpen('formDriver');
           // self.setBirthday();
        };

        self.tryToUpdate = function (driver) {
            self.driver.id = driver.id;
            self.driver.firstname = driver.firstname;
            self.driver.name = driver.name;
            self.driver.surname = driver.surname;
            self.driver.birthday = null;
            self.driver.address = '';
            self.driver.phone = driver.phone;
            self.driver.driverLicense = driver.driverLicense;
            self.driver.driverClass = driver.driverClass;
            self.driver.transportDep = driver.transportDep;
            self.driver.status = driver.status;
            self.driver.note = driver.note;
            formOpen('cover-trsp1');
            formOpen('formDriver');
        };

        self.tryToDelete = function (driver) {
            self.driver.id = driver.id;
            formOpen('cover-trsp1');
            formOpen('del-driver-confirm');
        };

        self.tryToUpdateStatus = function (driver) {
            self.driver.id = driver.id;
            self.driver.firstname = driver.firstname;
            self.driver.name = driver.name;
            self.driver.surname = driver.surname;
            self.driver.birthday = null;
            self.driver.address = ''
            self.driver.phone = driver.phone;
            self.driver.driverLicense = driver.driverLicense;
            self.driver.driverClass = driver.driverClass;
            self.driver.transportDep = driver.transportDep;
            self.driverInfo.status = driver.status;
            formOpen('cover-trsp1');
            formOpen('formChangeDriverStatus');
        };

        self.resetForm = function () {
            self.driver = {id: null};
            formClose('formDriver');
            formClose('cover-trsp1');
        };

        self.closeStatusForm = function () {
            self.driver = {id: null};
            self.driverInfo = {id: null};
            formClose('formChangeDriverStatus');
            formClose('cover-trsp1');
        };

    }]);

