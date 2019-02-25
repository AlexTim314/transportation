'use strict';

App.controller('ClaimsPageController', ['$scope', 'ClaimsPageService',
    function ($scope, ClaimsPageService) {
        var self = this;
        self.department = {id: null};
        self.permit = false;

        self.getPermit = function () {
            ClaimsPageService.getPermit()
                    .then(
                            function (d) {
                                self.permit = d;
                                console.log(self.permit);
                            },
                            function (errResponse) {
                                console.error('Error while fetching Permit');
                            }
                    );
        };

        self.getPermit();

        self.fetchDepartment = function () {
            ClaimsPageService.fetchDepartment()
                    .then(
                            function (d) {
                                self.department = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Department');
                            }
                    );
        };

        self.fetchDepartment();

    }]);

