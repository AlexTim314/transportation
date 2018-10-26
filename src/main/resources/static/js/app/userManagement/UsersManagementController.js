'use strict';

App.controller('UsersManagementController', ['$scope', 'UsersManagementService',
    function ($scope, UsersManagementService) {
        var self = this;
        self.user = {userId: null, userName: '', department: {name: ""}, roles: []};
        self.role = {roleName: null};
        self.department = {name: null};
        self.users = [];
        self.roles = [];
        self.departments = [];
        self.curUserToDeleteName = '';

        self.fetchAllUsers = function () {
            UsersManagementService.fetchAllUsers()
                    .then(
                            function (d) {
                                self.users = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Users');
                                showAlert(errResponse);
                            }
                    );
        };

        self.fetchAllRoles = function () {
            UsersManagementService.fetchAllRoles()
                    .then(
                            function (d) {
                                self.roles = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Roles');
                                showAlert(errResponse);
                            }
                    );
        };

        self.fetchAllDepartments = function () {
            UsersManagementService.fetchAllDepartments()
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

        self.fetchAllUsers();
        self.fetchAllRoles();
        self.fetchAllDepartments();

        self.createUser = function (user) {
            UsersManagementService.createUser(user)
                    .then(
                            self.fetchAllUsers,
                            function (errResponse) {
                                console.error('Error while creating User.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.updateUser = function (user) {
//            OrganizationService.updateOrganization(self.currentOrganization, organization)
//                    .then(
//                            self.fetchAllOrganizations,
//                            function (errResponse) {
//                                console.error('Error while updating Organization.');
//                                showAlert(errResponse);
//                            }
//                    );
        };

        self.deleteUser = function (user) {
            UsersManagementService.deleteUser(user)
                    .then(
                            self.fetchAllUsers,
                            function (errResponse) {
                                console.error('Error while deleting User.');
                                showAlert(errResponse);
                            }
                    );
        };

        self.submit = function () {
            if (self.user.userId === null) {
                self.createUser(self.user);
            } else {
                self.updateUser(self.user);
            }
            self.reset();
        };

        self.editUser = function (user) {
            self.user.userId = user.userId;
            self.user.userName = user.userName;
            self.user.department = user.department;
            self.user.roles = user.roles.slice();
//            console.log('Organization name to be edited', orgName);
//            self.currentOrganization.name = orgName;
//            self.organization.name = orgName;
//            //$scope.myForm.$setDirty();
        };

        self.addRole = function () {
            self.user.roles.push(self.role);
        };

        self.removeRole = function () {
            self.user.roles.pop();
        };

//        self.showConfirmDialogThenDelete = function (orgName) {
//            self.curOrgToDeleteName = orgName;
//            $('#dialog-confirm').dialog('open');
//        }
//
//        self.removeCurOrg = function () {
//            console.log('org to be deleted', self.curOrgToDeleteName);
//            // if org is updated now
//            if (self.currentOrganization.name === self.curOrgToDeleteName) {
//                self.reset();
//            }
//            self.deleteOrganization({name: self.curOrgToDeleteName});
//        };

        self.reset = function () {
            self.user = {userId: null, userName: '', department: {name: ""}, roles: []};
            self.role = {roleName: null};
        };

    }]);