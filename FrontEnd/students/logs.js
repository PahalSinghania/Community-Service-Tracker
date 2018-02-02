// Logs js with real get requests(placeholder urls) commented out  
// dummy get requests in place for get activities
// checkin functionality implemented
// no checks present , need to ensure form completion and condtition coverage

LogApp = angular.module('LogApp', ['LogApp.controllers', 'smart-table', 'ui.bootstrap']);

angular.module('LogApp.controllers', []).controller('testController',  ['$scope', '$http', '$uibModal', function($scope, $http, $modal) {
$scope.loading = false;
var modalInstance = null;
$scope.partner = {};
$scope.civic = {};
$scope.alliance = {};

$http.defaults.headers.common['Access-Control-Allow-Origin'] = '*';

$scope.getData = function() {
    $scope.loading = true;

    // get request for partner activities with dummy url
    /*
    $http({
		url: "http://128.31.25.76:8088/nuaces-1.0/getactivities", 
		method: "GET",
		params: {userid:1}
	}).then(function(response){
    	$scope.logs = response.data;
		$scope.loading = false;
    });	
    */
    
    //dummy get request for civic activities
    $http.get("http://dummy.restapiexample.com/api/v1/employees")
    .then(function(response){
        $scope.loading = false;

        // hardcoded parnter activities for demo
        $scope.partner= [
            {	"activityid" : "1",
                "activityname" : "volunteering",
                "date" : "01/04/17",
                "time" : "3:45 pm",
                "hours" : "2",
                "location" : "Northeastern University",
                "comments" : "",
            },
            {	"activityid" : "2",
                "activityname" : "volunteering",
                "date" : "01/11/17",
                "time" : "3:45 pm",
                "hours" : "2",
                "location" : "Northeastern University",
                "comments" : "",
            },   
            {	"activityid" : "3",
                "activityname" : "volunteering",
                "date" : "01/18/17",
                "time" : "3:45 pm",
                "hours" : "2",
                "location" : "Northeastern University",
                "comments" : "",
            },
            {	"activityid" : "4",
                "activityname" : "volunteering",
                "date" : "01/25/17",
                "time" : "3:45 pm",
                "hours" : "2",
                "location" : "Northeastern University",
                "comments" : "",
            },
            {	"activityid" : "5",
                "activityname" : "volunteering",
                "date" : "02/01/17",
                "time" : "3:45 pm",
                "hours" : "2",
                "location" : "Northeastern University",
                "comments" : "",
            },
            {	"activityid" : "6",
                "activityname" : "volunteering",
                "date" : "02/08/17",
                "time" : "3:45 pm",
                "hours" : "2",
                "location" : "Northeastern University",
                "comments" : "",
            },   
            {	"activityid" : "7",
                "activityname" : "volunteering",
                "date" : "02/15/17",
                "time" : "3:45 pm",
                "hours" : "2",
                "location" : "Northeastern University",
                "comments" : "",
            },
            {	"activityid" : "8",
                "activityname" : "volunteering",
                "date" : "02/22/17",
                "time" : "3:45 pm",
                "hours" : "2",
                "location" : "Northeastern University",
                "comments" : "",
            }
        ];
    });

    /* get request for civic activities with dummy url
    $http.get("Enter URL here")
    .then(function(response){
        $scope.civic = response.data;
        $scope.loading = false;
    });	
    */


    //dummy get request for civic activities
    $http.get("http://dummy.restapiexample.com/api/v1/employees")
    .then(function(response){
        $scope.loading = false;

        // hardcoded civic activities for demo
        $scope.civic = [
            {	"activityid" : "1",
                "action" : "Service Learning",
                "type" : "Voting",
                "date" : "02/21/17",
                "time" : "12:42 pm"
            },
            {	"activityid" : "2",
                "action" : "Community Service",
                "date" : "1/03/17",
                "time" : "7:11 am",
                "type" : "other"
            }
        ];
    });

    /* get request for alliance activities with dummy url
    $http.get("Enter URL here")
    .then(function(response){
        $scope.alliance = response.data;
        $scope.loading = false;
    });	
    */

    //dummy get request
    $http.get("http://dummy.restapiexample.com/api/v1/employees")
    .then(function(response){
        $scope.loading = false;

        // hardcoded alliance activities for demo
        $scope.alliance = [
            {	"activityid" : "1",
                "topic" : "Fundraising",
                "date" : "12/04/17",
                "time" : "12:42 pm"
            },
        ];
    });

    

}

$scope.getData();


$scope.addPartner = function(params) {
    
        $scope.partner.push({
            "activityid" : 9,
            "userid" : 1,
            "activityname" : $scope.activityname,
            "date" : $scope.date,
            "time" : $scope.time,
            "hours" : $scope.hours,
            'partner' : "hi",
            "location" : $scope.location,
            "comments" : $scope.comments,
        });

        var substringDate = $scope.date.toISOString().substring(0,10);
        var dateArray = substringDate.split("-");
        var formattedDate = dateArray[1] + "/" + dateArray[2] + "/" + dateArray[0];

        console.log($scope.time.toLocaleTimeString());
        var substringTime = $scope.time.toLocaleTimeString().substring(0,10);
        var timeArray = substringTime.split(" ");
        var time = timeArray[0].split(":");
        var formattedTime = time[0] + ":" + time[1];
        console.log(formattedTime);
        if (timeArray[1] == "PM"){
            var military = parseInt(time[0]) + 12;
            console.log(military);
            formattedTime = military.toString() + ":" + time [1];
        }

        if (formattedTime.length < 5) {
            formattedTime = "0" + formattedTime;
        }
        console.log(formattedTime);
    
        if (!$scope.activityname) {
            console.error("no params");
            return;
        }else {
        $http({
            method: 'POST',
            url: "http://128.31.25.76:8088/nuaces-1.0/checkin/partner?userid="+1+"&hours="+$scope.hours+"&date="+formattedDate+"&time="+formattedTime+"&activityname="+$scope.activityname+"&partner="+$scope.activityname+"&location="+$scope.location+"&comments="+$scope.comments,
            headers: {
              'Access-Control-Allow-Origin' : ' * ',
              'crossorigin' : 'anonymous',
              'Access-Control-Allow-Methods' : 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
              'Access-Control-Allow-Headers' : 'Origin, Content-Type, X-Auth-Token',
              'Content-Type' : 'application/json'
            },
        
            }).then(function(response){
                console.log(response);
                $scope.getData();
            });
        }
    }

    $scope.addCivic = function(params) {
        
            $scope.civic.push({
                "activityid" : 9,
                "userid" : 1,
                "action" : $scope.actionName,
                "date" : $scope.date,
                "time" : $scope.time,
                "type" : $scope.type
            });
    
            var substringDate = $scope.date.toISOString().substring(0,10);
            var dateArray = substringDate.split("-");
            var formattedDate = dateArray[1] + "/" + dateArray[2] + "/" + dateArray[0];
    
            console.log($scope.time.toLocaleTimeString());
            var substringTime = $scope.time.toLocaleTimeString().substring(0,10);
            var timeArray = substringTime.split(" ");
            var time = timeArray[0].split(":");
            var formattedTime = time[0] + ":" + time[1];
            console.log(formattedTime);
            if (timeArray[1] == "PM"){
                var military = parseInt(time[0]) + 12;
                console.log(military);
                formattedTime = military.toString() + ":" + time [1];
            }

            if (formattedTime.length < 5) {
                formattedTime = "0" + formattedTime;
            }
            console.log(formattedTime);
        
            if (!$scope.type) {
                console.error("no params");
                return;
            }else {
            $http({
                method: 'POST',
                url: "http://128.31.25.76:8088/nuaces-1.0/checkin/civicaction?userid="+1+"&date="+formattedDate+"&time="+formattedTime+"&activityname="+$scope.actionName,
                headers: {
                  'Access-Control-Allow-Origin' : ' * ',
                  'crossorigin' : 'anonymous',
                  'Access-Control-Allow-Methods' : 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
                  'Access-Control-Allow-Headers' : 'Origin, Content-Type, X-Auth-Token',
                  'Content-Type' : 'application/json'
                },
            
                }).then(function(response){
                    console.log(response);
                    $scope.getData();
                });
            }
        }

        $scope.addAlliance = function(params) {
            
                $scope.alliance.push({
                    "activityid" : 9,
                    "userid" : 1,
                    "meetingName" : $scope.meetingName,
                    "date" : $scope.date,
                    "time" : $scope.time
                });
        
                var substringDate = $scope.date.toISOString().substring(0,10);
                var dateArray = substringDate.split("-");
                var formattedDate = dateArray[1] + "/" + dateArray[2] + "/" + dateArray[0];
        
                console.log($scope.time.toLocaleTimeString());
                var substringTime = $scope.time.toLocaleTimeString().substring(0,10);
                var timeArray = substringTime.split(" ");
                var time = timeArray[0].split(":");
                var formattedTime = time[0] + ":" + time[1];
                console.log(formattedTime);
                if (timeArray[1] == "PM"){
                    var military = parseInt(time[0]) + 12;
                    console.log(military);
                    formattedTime = military.toString() + ":" + time [1];
                }

                if (formattedTime.length < 5) {
                    formattedTime = "0" + formattedTime;
                }
                console.log(formattedTime);
            
                if (!$scope.meetingName) {
                    console.error("no params");
                    return;
                }else {
                $http({
                    method: 'POST',
                    url: "http://128.31.25.76:8088/nuaces-1.0/checkin/alliancebuilding?userid="+1+"&date="+formattedDate+"&time="+formattedTime+"&activityname="+$scope.meetingName,
                    headers: {
                      'Access-Control-Allow-Origin' : ' * ',
                      'crossorigin' : 'anonymous',
                      'Access-Control-Allow-Methods' : 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
                      'Access-Control-Allow-Headers' : 'Origin, Content-Type, X-Auth-Token',
                      'Content-Type' : 'application/json'
                    },
                
                    }).then(function(response){
                        console.log(response);
                        $scope.getData();
                    });
                }
            }
    

}]);

LogApp.controller('empViewCtrl',  ['$scope', '$http', 'record', function($scope, $http, record) {
function init(){
    $scope.activity = record;
}
init();

}]);

LogApp.controller('addEmpCtrl',  ['$scope', '$http', 'record', function($scope, $http) {

}]);

LogApp.controller('updateEmpCtrl',  ['$scope', '$http', 'record', function($scope, $http, record) {
$scope.activity = {};
function init(){
    
}
init();

}]);