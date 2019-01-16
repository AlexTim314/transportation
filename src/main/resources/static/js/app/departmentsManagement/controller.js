'use strict';

App.controller('DepartmentsManagementController', ['$scope', 'DepartmentsManagementService',
    function ($scope, DepartmentsManagementService) {
        var self = this;
        self.department = {id: null, shortname: '', fullname: '', address: '', phone: ''};
        self.departments = [];

        self.fetchAllDepartments = function () {
            DepartmentsManagementService.fetchAllDepartments()
                    .then(
                            function (d) {
                                self.departments = d;
                                console.log(d);
                            },
                            function (errResponse) {
                                console.error('Error while fetching Departments');
                            }
                    );
        };


        self.fetchAllDepartments();


        self.createDepartment = function (department) {
            DepartmentsManagementService.createDepartment(department)
                    .then(
                            self.fetchAllDepartments,
                            function (errResponse) {
                                console.error('Error while creating Department.');
                            }
                    );
        };

        self.updateDepartment = function (department) {
            DepartmentsManagementService.updateDepartment(department)
                    .then(
                            self.fetchAllDepartments,
                            function (errResponse) {
                                console.error('Error while updating Department.');
                            }
                    );
        };

        self.deleteDepartment = function (department) {
            DepartmentsManagementService.deleteDepartment(department)
                    .then(
                            self.fetchAllDepartments,
                            function (errResponse) {
                                console.error('Error while deleting Department.');
                            }
                    );
        };

        self.submit = function () {
            if (self.department.id === null) {
                self.createDepartment(self.department);
            } else {
                self.updateDepartment(self.department);
            }
            self.reset();
        };

        self.editDepartment = function (department) {
            self.department.id = department.id;
            self.department.fullname = department.fullname;
            self.department.shortname = department.shortname;
            self.department.address = department.address;
            self.department.phone = department.phone;
        };

        self.reset = function () {
            self.department = {id: null, shortname: '', fullname: '', address: '', phone: ''};
        };

    }]);


