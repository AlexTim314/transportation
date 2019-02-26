'use strict';

App.controller('UsersManagementController', ['$scope', 'UsersManagementService',
    function ($scope, UsersManagementService) {
        var self = this;
        self.user = {id: null, username: '', fullName: '', post: '', department: null, transportDep: null, roles: [], departments: []};
        self.role = {roleName: null};
        self.department = {name: null};
        self.subDep = {name: null};
        self.departments = [];
        self.users = [];
        self.roles = [];
        self.transportDeps = [];

        self.fetchAllUsers = function () {
            self.isUserFetching = true;
            UsersManagementService.fetchAllUsers()
                    .then(
                            function (d) {
                                self.users = d;
                                self.fetchAllDepartments();
                            },
                            function (errResponse) {
                                console.error('Error while fetching Users');
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
                            }
                    );
        };

        self.fetchAllDepartments = function () {
            UsersManagementService.fetchAllDepartments()
                    .then(
                            function (d) {
                                self.departments = d;
                                for (var i = 0; i < self.departments.length; i++) {
                                    var dpt = self.departments[i];
                                    for (var j = 0; j < self.users.length; j++) {
                                        var u = self.users[j];
                                        if (u.departments === undefined) {
                                            u.departments = [];
                                        }
                                        if (dpt.superManager !== null && dpt.superManager !== undefined && dpt.superManager.id === u.id) {
                                            u.departments.push(dpt);
                                        }
                                    }
                                }
                            },
                            function (errResponse) {
                                console.error('Error while fetching Departments');
                            }
                    );
        };

        self.fetchAllTransportDeps = function () {
            UsersManagementService.fetchAllTransportDeps()
                    .then(
                            function (d) {
                                self.transportDeps = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching TransportDeps');
                            }
                    );
        };

        self.fetchAllUsers();
        self.fetchAllRoles();
        self.fetchAllTransportDeps();

        self.createUser = function (user) {
            var depArr = self.user.departments;
            self.user.departments = null;
            UsersManagementService.createUser(user)
                    .then(
                            function (d) {
                                for (var i = 0; i < depArr.length; i++) {
                                    depArr[i].superManager = d;
                                }
                                self.fetchAllUsers();
                                self.updateDepartments(depArr);
                                self.reset();
                            },
                            function (errResponse) {
                                console.error('Error while creating User.');
                            }
                    );
        };

        self.updateUser = function (user) {
            var depArr = self.user.departments;
            self.user.departments = null;
            UsersManagementService.updateUser(user)
                    .then(
                            function (d) {
                                for (var i = 0; i < depArr.length; i++) {
                                    depArr[i].superManager = d;
                                }
                                self.fetchAllUsers();
                                self.updateDepartments(depArr);
                                self.reset();
                            },
                            function (errResponse) {
                                console.error('Error while updating User.');
                            }
                    );
        };

        self.updateDepartments = function (departments) {
            UsersManagementService.updateDepartments(departments)
                    .then(
                            function (d) {
                            },
                            function (errResponse) {
                                console.error('Error while updating Departments.');
                            }
                    );
        };

        self.deleteUser = function (user) {
            UsersManagementService.deleteUser(user)
                    .then(
                            self.fetchAllUsers,
                            function (errResponse) {
                                console.error('Error while deleting User.');
                            }
                    );
        };

        self.submit = function () {
            if (self.user.id === null) {
                self.createUser(self.user);
            } else {
                self.updateUser(self.user);
            }
        };

        self.editUser = function (user) {
            self.user.id = user.id;
            self.user.username = user.username;
            self.user.fullName = user.fullName;
            self.user.post = user.post;
            self.user.department = user.department;
            self.user.transportDep = user.transportDep;
            self.user.roles = user.roles.slice();
            self.user.departments = user.departments.slice();
        };

        self.addRole = function () {
            self.user.roles.push(self.role);
        };

        self.removeRole = function (role) {
            var k = -1;
            for (var i = 0; i < self.user.roles.length; i++) {
                var r = self.user.roles[i];
                if (r.id === role.id) {
                    k = i;
                    break;
                }
            }
            self.user.roles.splice(k, 1);
        };

        self.addDep = function () {
            self.subDep.superManager = self.user;
            self.user.departments.push(self.subDep);
        };

        self.removeDep = function (dep) {
            var k = -1;
            for (var i = 0; i < self.user.departments.length; i++) {
                var dp = self.user.departments[i];
                if (dp.id === dep.id) {
                    k = i;
                    break;
                }
            }
            self.user.departments.splice(k, 1);
        };

        self.reset = function () {
            self.user = {id: null, username: '', fullName: '', post: '', department: null, transportDep: null, roles: [], departments: []};
            self.role = {roleName: null};
        };

        self.shortFullName = function (user) {
            var nameArr = user.fullName.split(' ');
            var result = nameArr[0];
            if (nameArr.length > 1) {
                result += " " + nameArr[1].charAt(0) + ".";
            }
            if (nameArr.length > 2) {
                result += nameArr[2].charAt(0) + "."
            }
            return result;
        };

    }]);
