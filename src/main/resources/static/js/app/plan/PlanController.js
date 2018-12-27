'use strict';

App.controller('PlanController', ['$scope', 'PlanService',
    function ($scope, PlanService) {
        var self = this;
        self.department = {id: null, name: '', addres: ''};
        self.vehicleType = {type: '', specialization: ''};
        self.claim = {id: null, clDate: '', affirmation: '', clType: '', department: {name: ''}};
        self.record = {id: null, weekHash: '', type: '', datetime: '', departureDate: '', returnDate: '', timeDelivery: '', departureTime: '', returnTime: '',
            purpose: '', serviceField: '', templateName: '', carBoss: '', status: '', description: '', claim: {clType: ''}, vehicleType: {specialization: ''}, waypoints: []};
        self.waypoint = {name: '', latitude: '', longitude: ''};
        self.driver = {id: null, firstname: '', name: '', surname: '', birthday: '', address: '', phone: '', note: ''};
        self.vehicle = {id: null, number: '', fuelRemnant: '', odometr: '', note: '', vehicleModel: {modelName: ''}};
        self.vehicleModel = {id: null, modelName: '', vehicleType: {specialization: ''}};
        self.appointment = {id: null, appDateTime: '', vehicleModel: {modelName: ''}};
        self.appointmentGroup = {id: null, note: '', record: {timeDelivery: ''}, appointment: {appDateTime: ''}};
        self.departments = [];
        self.unapprovedClaims = [];
        self.approvedClaims = [];
        self.records = [];
        self.waypoints = [];
        self.vehicleTypes = [];
        self.drivers = [];
        self.vehicles = [];
        self.vehicleModels = [];
        self.appointments = [];
        self.appointmentGroups = [];


        self.fetchRecords = function () {
            var status = 'record_status_created';
            var dd = new Date().getDate();
            var mm = new Date().getMonth() + 1;
            var yyyy = new Date().getFullYear();
            var date = yyyy + '-' + mm + '-' + dd;
            PlanService.fetchRecords(date,status)
                    .then(
                            function (d) {
                                self.records = d;
                                self.prepareLists();
                            },
                            function (errResponse) {
                                console.error('Error while fetching Records');
                                alert(errResponse);
                            }
                    );
        };
        
        self.fetchRecords();

        self.addRowHandlers = function (dep) {
            if (dep.isVisible === undefined) {
                dep.isVisible = true;
            } else {
                dep.isVisible = !dep.isVisible;
            }
        };

        self.prepareLists = function(){
            self.departments = [];
            for(var i in self.records){
                var rec = self.records[i];
                var dep = rec.claim.department;
                
                var isContain = false;
                for(var j in self.departments){
                    if(dep.id===self.departments[j].id){
                        self.departments[j].records.push(rec);
                        isContain = true;
                        break;
                    }
                }
                if(!isContain){
                    dep.records = [];
                    dep.records.push(rec);
                    self.departments.push(dep);
                }
            }
        };
        
    }]);