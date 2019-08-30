'use strict';

App.controller('VehiclesController', ['$scope', 'DispatcherService',
    function ($scope, DispatcherService) {
        var self = this;

        self.transportDep = {id: null};
        self.vehicleTypes = [];
        self.models = [];
        self.fuels = [];

        self.fuel = {id: null};

        self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}, fuels: []};
        self.vehicles = [];
        self.vehicleInfo = {id: null};
        self.refueling = {id: null, refuelingDate: '', volume: '', fuel: null, vehicle: null};
        self.vehWaybill = {};

        self.history = [];

        self.cmpsts = [];
        self.data = [];
        self.drivers = [];
        self.driversMap = {};
        self.vehicleModels = [];
        self.vehicleModelsMap = {};
        self.vehicleTypes = [];
        self.vehicleTypesMap = {};
        self.vehiclesMap = {};

        self.all = false;

        var idsArr = [];

        self.fetchTransportDep = function () {
            DispatcherService.fetchTransportDep()
                    .then(
                            function (d) {
                                self.transportDep = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching TransportDep');
                            }
                    );
        };

        self.fetchVehicleTypes = function () {
            DispatcherService.fetchVehicleTypes()
                    .then(
                            function (d) {
                                self.vehicleTypes = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching VehicleTypes');
                            }
                    );
        };

        self.fetchVehicleModels = function () {
            DispatcherService.fetchVehicleModels()
                    .then(
                            function (d) {
                                self.models = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Models');
                            }
                    );
        };
        
//        self.newWaybill = function () {
//            DispatcherService.newWaybill()
//                    .then(
//                            function (d) {
//                                console.log(d);
//                            },
//                            function (errResponse) {
//                                console.error('Error while creating waybill');
//                            }
//                    );
//        };

        self.fetchFuels = function () {
            DispatcherService.fetchFuels()
                    .then(
                            function (d) {
                                self.fuels = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Fuels');
                            }
                    );
        };

        self.fetchVehicles = function () {
            DispatcherService.fetchVehicles()
                    .then(
                            function (d) {
                                self.vehicles = d;
                                for (var i = 0; i < d.length; i++) {
                                    self.vehiclesMap[d[i].id] = i;
                                    if (self.vehicles[i].status === 'исправно') {
                                        self.vehicles[i].stat_id = 0;
                                    }
                                    if (self.vehicles[i].status === 'ремонт') {
                                        self.vehicles[i].stat_id = 1;
                                    }
                                    if (self.vehicles[i].status === 'другое') {
                                        self.vehicles[i].stat_id = 2;
                                    }
                                    if (self.vehicles[i].status === 'неработоспособно') {
                                        self.vehicles[i].stat_id = 3;
                                    }
                                    if (self.vehicles[i].status === 'списано') {
                                        self.vehicles[i].stat_id = 4;
                                    }
                                }
                            },
                            function (errResponse) {
                                console.error('Error while fetching Vehicles');
                            }
                    );
        };

        self.getAppointmentsByVehicle = function (vehicle) {
            DispatcherService.fetchAppointmentsByVehicle(vehicle)
                    .then(
                            function (d) {
                                self.cmpsts = d;
                                self.createDataComposite();
                            },
                            function (errResponse) {
                                console.error('Error while fetching appointments by vehicle');
                                console.error(errResponse);
                            }
                    );
        };

        self.getVehicleHistory = function (vehicle) {
            self.vehicle = vehicle;
            DispatcherService.fetchVehicleHistory(vehicle)
                    .then(
                            function (d) {
                                self.history = d;
                                formOpen('history-transport');
                                formOpen('cover-trsp1');
                            },
                            function (errResponse) {
                                console.error('Error while fetching Vehicle History');
                            }
                    );
        };

        self.fetchTransportDep();
        self.fetchVehicleTypes();
        self.fetchVehicleModels();
        self.fetchFuels();
        self.fetchVehicles();

        self.createVehicle = function () {
            self.vehicle.transportDep = self.transportDep;
            DispatcherService.createVehicle(self.vehicle)
                    .then(
                            function (d) {
                                self.fetchVehicles();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while creating Vehicle.');
                            }
                    );
        };

        self.createVehicleInfo = function () {
            self.vehicleInfo.vehicle = self.vehicle;
            DispatcherService.createVehicleInfo(self.vehicleInfo)
                    .then(
                            function (d) {
                                self.fetchVehicles();
                                self.closeStateForm();
                                self.closeStatusForm();
                                self.vehicle.status = d.status;
                                self.vehicle.ododmetr = d.ododmetr;
                                self.vehicle.fuel = d.fuel;
                                self.vehicle.motohours = d.motohours;
                            },
                            function (errResponse) {
                                console.error('Error while creating Vehicle.');
                            }
                    );
        };

        self.updateVehicle = function () {
            DispatcherService.updateVehicle(self.vehicle)
                    .then(
                            function (d) {
                                self.fetchVehicles();
                                self.resetForm();
                            },
                            function (errResponse) {
                                console.error('Error while updating Vehicle.');
                            }
                    );
        };

        self.deleteVehicles = function () {
            DispatcherService.deleteVehicles(idsArr)
                    .then(
                            function (d) {
                                self.closeDeleteForm();
                                self.fetchVehicles();
                            },
                            function (errResponse) {
                                console.error('Error while deleting Vehicle.');
                            }
                    );
        };
        self.closeDeleteForm = function () {
            formClose('del-vehicle-confirm');
            formClose('cover-trsp1');
        };

        self.submit = function () {
            if (self.vehicle.id === null) {
                self.createVehicle();
            } else {
                self.updateVehicle()();
            }
        };

        self.tryToCreate = function () {
            self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}};
            formOpen('formTransport');
        };

        self.tryToUpdate = function (vehicle) {
            self.vehicle.id = vehicle.id;
            self.vehicle.model = vehicle.model;
            self.vehicle.number = vehicle.number;
            self.vehicle.fuels = vehicle.fuels;
            self.vehicle.fuel = vehicle.fuel;
            self.vehicle.ododmetr = vehicle.ododmetr;
            self.vehicle.motohours = vehicle.motohours;
            self.vehicle.status = vehicle.status;
            self.vehicle.note = vehicle.note;
            self.vehicle.transportDep = vehicle.transportDep;
            formOpen('cover-trsp1');
            formOpen('formTransport');
        };

        self.addVehicle = function () {
            formOpen('cover-trsp1');
            formOpen('formTransport');
        };

        self.tryToDelete = function () {
            idsArr = [];
            for (var i = 0; i < self.vehicles.length; i++) {
                if (self.vehicles[i].checked) {
                    idsArr.push(self.vehicles[i].id);
                }
            }
            formOpen('del-vehicle-confirm');
            formOpen('cover-trsp1');
        };

        self.tryToUpdateVehicleState = function (vehicle) {
            self.vehicle = vehicle;
            self.vehicleInfo.status = vehicle.status;
            self.vehicleInfo.fuel = vehicle.fuel;
            self.vehicleInfo.odometr = vehicle.odometr;
            self.vehicleInfo.motohours = vehicle.motohours;
            formOpen('formCurrentParametr');
            formOpen('cover-trsp1');
        };

        self.tryToUpdateVehicleStatus = function (vehicle) {
            self.vehicle = vehicle;
            self.vehicleInfo.status = vehicle.status;
            self.vehicleInfo.fuel = vehicle.fuel;
            self.vehicleInfo.odometr = vehicle.odometr;
            self.vehicleInfo.motohours = vehicle.motohours;
            self.vehicleInfo.note = null;
            formOpen('formChangeVehicleStatus');
            formOpen('cover-trsp1');
        };

        self.resetForm = function () {
            self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}, fuels: []};
            formClose('formTransport');
            formClose('cover-trsp1');
        };

        self.checkAll = function () {
            for (var i = 0; i < self.vehicles.length; i++) {
                self.vehicles[i].checked = self.all;
            }
        };

        self.addFuel = function () {
            self.vehicle.fuels.push(self.fuel);
            self.fuel = {id: null};
        };

        self.removeFuel = function (fuel) {
            var k = -1;
            for (var i = 0; i < self.vehicles.length; i++) {
                if (self.vehicles[i].id === fuel.id) {
                    k = i;
                    break;
                }
            }
            self.vehicle.fuels.splice(k, 1);
            self.fuel = {id: null};
        };

        self.openInfoForm = function (vehicle) {
            self.vehicle = vehicle;
            formOpen('more-transport');
            formOpen('cover-trsp1');
            //self.newWaybill();
        };

        self.closeInfoForm = function () {
            self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}, fuels: []};
            formClose('more-transport');
            formClose('cover-trsp1');
        };

        self.closeStateForm = function () {
            self.vehicleInfo = {id: null};
            formClose('formCurrentParametr');
            formClose('cover-trsp1');
        };

        self.closeStatusForm = function () {
            self.vehicleInfo = {id: null};
            formClose('formChangeVehicleStatus')
            formClose('cover-trsp1');
        };

        self.closeVehicleHistoryForm = function () {
            self.vehicle = {id: null, model: {id: null, vehicleType: {id: null}}, fuels: []};
            formClose('history-transport');
            formClose('cover-trsp1');
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

        self.tryToRefuelingVehicle = function (vehicle) {
            self.refueling.vehicle = vehicle;
            formOpen('formRefueling');
            formOpen('cover-trsp1');
        };

        self.refuelingVehicle = function () {
            console.log(self.refueling);
            DispatcherService.refulingVehicle(self.refueling)
                    .then(
                            function (d) {
                                console.log('Refueling Sucsessible');
                                formClose('formRefueling');
                            },
                            function (errResponse) {
                                console.error('Error while creating Refueling.');
                            }
                    );
        };

        self.cancelRefueling = function () {
            self.refueling = {id: null, refuelingDate: '', volume: '', fuel: null, vehicle: null};
            formClose('formRefueling');
            formClose('cover-trsp1');
        };

        self.openWaybillSidebar = function (v) {
            self.getAppointmentsByVehicle(v);
            fastOpen();
            self.vehWaybill = v;
            self.drivers = DispatcherService.getCommonDrivers();
            self.driversMap = DispatcherService.getCommonDriversMap();
            self.vehicleModels = DispatcherService.getCommonVehicleModels();
            self.vehicleModelsMap = DispatcherService.getCommonVehicleModelsMap();
            self.vehicleTypes = DispatcherService.getCommonVehicleTypes();
            self.vehicleTypesMap = DispatcherService.getCommonVehicleTypesMap();
        };

        self.closeWaybillSidebar = function () {
            fastClose();
            self.vehWaybill = null;
        };
        self.createDataComposite = function () {
            if (self.cmpsts.length > 0) {
                self.data = [];
                var driver = {};
                var vehicle = {};
                var vehicleType = {};
                var vehicleModel = {};
                for (var i = 0; i < self.cmpsts.length; i++) {
                    driver = null;
                    vehicle = null;
                    vehicleType = null;
                    vehicleModel = null;

                    var dr_id = self.cmpsts[i].appClaim.driverid;
                    if (dr_id !== null) {
                        var arrId = self.driversMap[dr_id];
                        driver = self.drivers[arrId];
                    }

                    var vh_id = self.cmpsts[i].appClaim.vehicleid;
                    if (vh_id !== null) {
                        var arrId = self.vehiclesMap[vh_id];
                        vehicle = self.vehicles[arrId];
                    }

                    var vt_id = self.cmpsts[i].appClaim.vehicleTypeId;
                    if (vt_id !== null) {
                        var arrId = self.vehicleTypesMap[vt_id];
                        vehicleType = self.vehicleTypes[arrId];
                    }

                    var vm_id = self.cmpsts[i].appClaim.vehicleModelId;
                    if (vm_id !== null) {
                        var arrId = self.vehicleModelsMap[vm_id];
                        vehicleModel = self.vehicleModels[arrId];
                    }

                    self.data.push({
                        claim: {
                            id: self.cmpsts[i].appClaim.claimid,
                            specialization: self.cmpsts[i].appClaim.vehicleTypeSpecialization,
                            purpose: self.cmpsts[i].appClaim.purpose,
                            department: self.cmpsts[i].appClaim.depshortname,
                            vehicleType: vehicleType,
                            carBoss: {
                                firstname: self.cmpsts[i].appClaim.carbossfirstname,
                                name: self.cmpsts[i].appClaim.carbossname,
                                surname: self.cmpsts[i].appClaim.carbosssurname,
                                phone: self.cmpsts[i].appClaim.carbossphone
                            }
                        },
                        record: {
                            id: self.cmpsts[i].appClaim.recordid,
                            startDate: self.cmpsts[i].appClaim.startdate,
                            entranceDate: self.cmpsts[i].appClaim.entrancedate,
                            endDate: self.cmpsts[i].appClaim.enddate,
                            affirmator: {
                                id: null,
                                fullName: null
                            },
                            tasks: self.cmpsts[i].routeTasks
                        },
                        appointment: {
                            id: self.cmpsts[i].appClaim.appointmentid,
                            status: self.cmpsts[i].appClaim.appstatus,
                            note: self.cmpsts[i].appClaim.appnote,
                            vehicleModel: vehicleModel,
                            vehicle: vehicle,
                            driver: driver,
                            creationDate: self.cmpsts[i].appClaim.appcrdate,
                            creator: {id: self.cmpsts[i].appClaim.creatorid}
                        }
                    });
                }
            } else {
                self.data = [];
            }
            console.log(self.data);
        };

        self.rowClick = function (data) {
//            if (data.isVisible) {
//                data.isVisible = !data.isVisible;
//                //self.showRecords([]);
//            } else {
//                for (var i = 0; i < self.data.length; i++) {
//                    self.data[i].isVisible = false;
//                }
            //if (dep.isVisible === undefined) {
            //     dep.isVisible = true;
            // } else {
            data.isVisible = !data.isVisible;
            // }
            //self.showRecords(data.composite);
            //}
        };
        self.personToString = function (person) {
            var result = person.firstname + " " + person.name.charAt(0) + "." + (person.surname !== null && person.surname !== undefined ? person.surname.charAt(0) + "." : "");
            return result;
        };
        self.showDriver = function (appointment) {
            return appointment.driver === null || appointment.driver === undefined ? '-' : self.personToString(appointment.driver);
        };
        self.statusClass = function (appointment) {
            var status = appointment.status;
            return self.selectStatusColor(status);
        };
        self.selectStatusColor = function (stat) {
            var inProgress = 'done-status';
            var ready = 'status-ready';
            var completed = 'status-ready';
            var canceled = 'cancel-status';
            switch (stat) {
                case '0':
                    return inProgress;
                case '1':
                    return ready;
                case '2':
                    return completed;
                case '3':
                    return canceled;
                case '4':
                    return canceled;
                case '5':
                    return canceled;
                case '6':
                    return canceled;
            }
        };
        self.selectStatus = function (stat) {
            var inProgress = 'Обрабатывается';
            var ready = 'Готово';
            var completed = 'Завершено';
            var canceledByUser = 'Отменено пользователем';
            var canceledByPlanner = 'Отменено планировщиком';
            var canceledByDispatcher = 'Отменено диспетчером';
            var canceledBySupermanager = 'Отменено управлением';
            switch (stat) {
                case '0':
                    return inProgress;
                case '1':
                    return ready;
                case '2':
                    return completed;
                case '3':
                    return canceledByUser;
                case '4':
                    return canceledByPlanner;
                case '5':
                    return canceledByDispatcher;
                case '6':
                    return canceledBySupermanager;
            }
        };
        self.setStatusColor = function (stat) {
            switch (stat) {
                case 0:
                    return 'status-ready';
                case 1:
                    return 'cancel-status';
                case 2:
                    return 'done-status';
                case 3:
                    return 'cancel-status';
                case 4:
                    return 'cancel-status';
            }
        };

    }]);

