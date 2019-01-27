'use strict';

App.controller('ClaimsPageController', ['$scope', 'ClaimsPageService',
    function ($scope, ClaimsPageService) {
        var self = this;
        self.department = {id: null};

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

