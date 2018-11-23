'use strict';

App.controller('ClaimController', ['$scope', 'ClaimService',
    function ($scope, ClaimService) {
        var self = this;
        self.department = {id: null, name: '', addres: ''};
        self.claim = {id: null, clDate: '', affirmation: '', tip: '', department: {name: ''}};
        self.record = {id: null, weekHash: '', datetime: '', dapartureDate: '', returnDate: '',  timeDelivery: '', departureTime: '', returnTime: '',
            purpose: '', serviceField: '', templateName: '', carBoss: '', status: '', description: '', claim: {clDate: ''}};

        
        self.departments = [];
        self.claims = [];
        self.records = [];
        
        
         self.fetchAllClaims = function () {
            ClaimService.fetchAllClaims()
                    .then(
                            function (d) {
                                self.claims = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Claims');
                                alert(errResponse);
                            }
                    );
        };
        
        self.fetchAllRecords = function () {
            ClaimService.fetchAllRecords()
                    .then(
                            function (d) {
                                self.records = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Records');
                                alert(errResponse);
                            }
                    );
        };
        self.fetchAllClaims();
        self.fetchAllRecords();


    }]);