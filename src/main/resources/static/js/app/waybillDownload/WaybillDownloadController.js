'use strict';

App.controller('WaybillDownloadController', ['$scope', 'WaybillDownloadService',
    function ($scope, WaybillDownloadService) {
        var self = this;
        
        self.downloadWaybill = function (appointment) {
            WaybillDownloadService.downloadWaybill(appointment)
                    .then(                          
                            function (errResponse) {
                                console.error('Error while downloading waybill. Controller.');
                                alert(errResponse);
                            }
                    );
        };
    }]);
