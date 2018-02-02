// Profile js with real get requests(placeholder urls) commented out  
// dummy get requests in place 
// post requests made with dummy URls
// no checks present, need to ensure form completion and condtition coverage

ProfileApp = angular.module('ProfileApp', ['ProfileApp.controllers', 'smart-table', 'ui.bootstrap']);

angular.module('ProfileApp.controllers', []).controller('testController',  ['$scope', '$http', '$uibModal', function($scope, $http, $modal) {
$scope.loading = false;
var modalInstance = null;
$scope.profile= [
    {	
    "userid":"1",
    "firstName" : "Tom",
    "lastname" : "Katzman",
    "email" : "email@husky.neu.edu",
    "uname" : "tomkatzman",
    "location" : "Northeastern university",
    "partner" : "Nintendo",
    }
];

$http.defaults.headers.common['Access-Control-Allow-Origin'] = '*';

$scope.getData = function() {
    $scope.loading = true;

    // get request for partner activities 
    /*
    $http({
		url: "http://128.31.25.76:8088/nuaces-1.0/profile", 
		method: "GET",
		params: {userid:1}
	}).then(function(response){
    	$scope.profile = response.data;
		$scope.loading = false;
    });	
    */
}
$scope.getData();


$scope.updateProfile = function(params) {
        console.log($scope.partner);
    
        if (!$scope.activityname) {
            console.error("no params");
            return;
        }else {
        $http({
            method: 'POST',
            url: "http://128.31.25.76:8088/nuaces-1.0/profile/update ",
            headers: {
              'Access-Control-Allow-Origin' : ' * ',
              'crossorigin' : 'anonymous',
              'Access-Control-Allow-Methods' : 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
              'Access-Control-Allow-Headers' : 'Origin, Content-Type, X-Auth-Token',
              'Content-Type' : 'application/json'
            },
            data: {
                "userid":1,
            "firstName" : $scope.firstName,
            "lastname" : $scope.lastname,
            "email" : $scope.email,
            "uname" : $scope.uname,
            "location" : $scope.location,
            "partner" : $scope.partner,
            }
        
            }).then(function(response){
                console.log(response);
                $scope.getData();
            });
        }
    }

    $scope.uploadPic = function() {
        var f = document.getElementById('file').files[0],
            r = new FileReader();
    
        r.onloadend = function(e) {
          var data = e.target.result;
          //send your binary data via $http or $resource or do anything else with it
        }
    
        r.readAsBinaryString(f);

        $http({
            method: 'POST',
            url: "http://128.31.25.76:8088/nuaces-1.0/profile/upload ",
            headers: {
              'Access-Control-Allow-Origin' : ' * ',
              'crossorigin' : 'anonymous',
              'Access-Control-Allow-Methods' : 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
              'Access-Control-Allow-Headers' : 'Origin, Content-Type, X-Auth-Token',
              'Content-Type' : 'application/json'
            },
            data 
        
            }).then(function(response){
                console.log(response);
                $scope.getData();
            });
    }
    

}]);
