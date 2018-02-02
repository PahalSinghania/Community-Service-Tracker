// Students js with real get requests(placeholder urls) commented out 
// dummy get , post requests in place along with real ones commented out
// needs view, edit, remove post requests.
// no checks present, need to ensure form completion and condtition coverage

StudentApp = angular.module('StudentApp', ['StudentApp.controllers', 'smart-table', 'ui.bootstrap']);

angular.module('StudentApp.controllers', []).controller('testController',  ['$scope', '$http', '$uibModal', function($scope, $http, $modal) {
    $scope.loading = false;
    var modalInstance = null;
    $scope.student = [];

       // hardcoded partners for demo
       $scope.student = [
        {	
            "firstname" : "Pahal",
            "lastname" : "Singhania",
            "huskyid" : "1234567890",
            "email" : "pahalsinghania@yahoo.com",
            "college" : "CCIS",
            "peerleader" : "Sarah",
            "partner" : "Boston Public schools"
        },
        {	
            "firstname" : "Alfreds",
            "lastname" : "Anders",
            "huskyid" : "1234567890",
            "email" : "andres@yahoo.com",
            "college" : "CAMD",
            "peerleader" : "Sarah",
            "partner" : "Boston Public schools"
        },
        {	
            "firstname" : "Matt",
            "lastname" : "Thanos",
            "huskyid" : "1234567890",
            "email" : "thanos.lo@husky.com",
            "college" : "D'Amore-McKim",
            "peerleader" : "Sarah",
            "partner" : "MBTA"
        },
        {	
            "firstname" : "Brielle",
            "lastname" : "Williamson",
            "huskyid" : "1234567890",
            "email" : "williamson@yahoo.com",
            "college" : "School of Law",
            "peerleader" : "Mike",
            "partner" : "CS 4500"
        }

    ];	

    $scope.getData = function() {
        $scope.loading = true;

        // get request for students with dummy url
        /*
        $http.get("Enter URL Here ")
        .then(function(response){
            $scope.student = response.data;
            $scope.loading = false;
        });
        */


        //dummy get request for Parnters
        $http.get("http://dummy.restapiexample.com/api/v1/employees")
        .then(function(response){
            $scope.loading = false;
               // hardcoded partners for demo
               $scope.student = [
                {	
                    "firstname" : "Pahal",
                    "lastname" : "Singhania",
                    "huskyid" : "1234567890",
                    "email" : "pahalsinghania@yahoo.com",
                    "college" : "CCIS",
                    "peerleader" : "Sarah",
                    "partner" : "Boston Public schools"
                },
                {	
                    "firstname" : "Alfreds",
                    "lastname" : "Anders",
                    "huskyid" : "1234567890",
                    "email" : "andres@yahoo.com",
                    "college" : "CAMD",
                    "peerleader" : "Sarah",
                    "partner" : "Boston Public schools"
                },
                {	
                    "firstname" : "Matt",
                    "lastname" : "Thanos",
                    "huskyid" : "1234567890",
                    "email" : "thanos.lo@husky.com",
                    "college" : "D'Amore-McKim",
                    "peerleader" : "Sarah",
                    "partner" : "MBTA"
                },
                {	
                    "firstname" : "Brielle",
                    "lastname" : "Williamson",
                    "huskyid" : "1234567890",
                    "email" : "williamson@yahoo.com",
                    "college" : "School of Law",
                    "peerleader" : "Mike",
                    "partner" : "CS 4500"
                }

            ];	
        });
    }

    $scope.getData();

    
	// adds students dummy url present pushes data manually
	$scope.addStudent = function(params) {
        
        if (!$scope.lastname) {
            console.error("no params");
            return;
        }else {
            $http({
                method: 'POST',
                url: "http://128.31.25.76:8088/nuaces-1.0/checkin/partner?userid="+1+"&hours=2&date=01/01/2017&time=13:01&activityname=hi&partner=hi&location=nu&comments=lol",			
                headers: {
                  'Access-Control-Allow-Origin' : ' * ',
                  'crossorigin' : 'anonymous',
                  'Access-Control-Allow-Methods' : 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
                  'Access-Control-Allow-Headers' : 'Origin, Content-Type, X-Auth-Token',
                  'Content-Type' : 'application/json'
                },
                }).then(function(response){
                    console.log(response);
                	/* ----- Uncomment post demo ---*/
                    //$scope.getData();
                });
    
                    // for demo
                    $scope.student.push({
                        "lastname" : $scope.lastname,
                        "firstname" : $scope.firstname,
                        "huskyid" : $scope.id,
                        "college" : $scope.college,
                        "email" : $scope.email,
                        "peerleader" : $scope.leader,
                        "partner" : $scope.parnter
                    });
                }
            }

        //dummy remove student
        $scope.removeItem = function removeItem(row) {
            var index = $scope.rowCollection.indexOf(row);
            if (index !== -1) {
            $scope.rowCollection.splice(index, 1);
        }
    }
}]);

