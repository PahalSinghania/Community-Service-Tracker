// settings js with real get requests(placeholder urls) commented out  
// dummy get requests in place 
// no checks present, need to ensure form completion and condtition coverage
// implement response display funtionality

SettingsApp = angular.module('LogApp', ['LogApp.controllers', 'smart-table', 'ui.bootstrap']);

angular.module('LogApp.controllers', []).controller('testController',  ['$scope', '$http', '$uibModal', function($scope, $http, $modal) {
$scope.loading = false;
var modalInstance = null;
$scope.passwords = {};

// reset pass form post request with dummy url present 
$scope.resetPass = function(params) {
    
    if (!$scope.lastname) {
        console.error("no params");
        return;
    }else {
        $http({
            method: 'POST',
            
			/* ----- Uncomment post demo and remove dummy url ---*/
            //url: "http://128.31.25.76:8088/nuaces-1.0/admin/resetPass?userid="+1+"&oldpswd="+scope.oldpswd+"&newpswd="+scope.newpswd+"&repswd="+repswd,
            url: "http://dummy.restapiexample.com/api/v1/employees",
            headers: {
              'Access-Control-Allow-Origin' : ' * ',
              'crossorigin' : 'anonymous',
              'Access-Control-Allow-Methods' : 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
              'Access-Control-Allow-Headers' : 'Origin, Content-Type, X-Auth-Token',
              'Content-Type' : 'application/json'
            },
            }).then(function(response){
                console.log(response);
            });
            }
        }

}]);