'use strict';

App.controller('DepartmentController', ['$scope', 'DepartmentService',
    function ($scope, DepartmentService) {
        var self = this;
        self.department = {departmentId: null, departmentName: '', departmentAddres: ''};
        self.departments = [];


        self.fetchAllDepartments = function () {
            DepartmentService.fetchAllDepartments()
                    .then(
                            function (d) {
                                self.departments = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Departments');
                                showAlert(errResponse);
                            }
                    );
        };

        self.fetchAllDepartments();

        self.createDepartment = function (department) {
            DepartmentService.createDepartment(department)
                    .then(
                            self.fetchAllDepartments,
                            function (errResponse) {
                                console.error('Error while creating Department.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.updateDepartment = function (department) {
            DepartmentService.updateUser(department)
                    .then(
                            self.fetchAllDepartments,
                            function (errResponse) {
                                console.error('Error while updating Department.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.deleteDepartment = function (department) {
            DepartmentService.deleteDepartment(department)
                    .then(
                            self.fetchAllDepartments,
                            function (errResponse) {
                                console.error('Error while deleting Department.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.submit = function () {
            if (self.department.departmentId === null) {
                self.createDepartment(self.department);
            } else {
                self.updateDepartment(self.department);
            }
            self.reset();
        };

        self.editDepartment = function (department) {
            self.department.departmentId = department.departmentId;
            self.department.departmentName = department.departmentName;
            self.department.departmentAddres = department.departmentAddres;
        };



        self.reset = function () {
            self.department = {departmentId: null, departmentName: '', departmentAddres: ''};
        };

    }]);