// Logs js with real get requests(placeholder urls) commented out  
// dummy get requests in place 
// no checks present , need to ensure form completion and condtition coverage

LogApp = angular.module('LogApp', ['LogApp.controllers', 'smart-table', 'ui.bootstrap']);
	
angular.module('LogApp.controllers', []).controller('testController',  ['$scope', '$http', '$uibModal', function($scope, $http, $modal) {
	$scope.loading = false;
	var modalInstance = null;
	$scope.partner = {};
	$scope.civic = {};
	$scope.alliance = {};

	$scope.getData = function() {
		$scope.loading = true;

		/* get request for partner activities with dummy url
		$http.get("Enter URL herre")
		.then(function(response){
			$scope.partner = response.data;
			$scope.loading = false;	
		});	
		*/


		//dummy get request for civic activities
		$http.get("http://dummy.restapiexample.com/api/v1/employees")
		.then(function(response){
			$scope.loading = false;

			// hardcoded parnter activities for demo
			$scope.partner= [
				{	"id" : "1",
					"student" : "Sarah",
					"activityname" : "volunteering",
					"date" : "12/04/17",
					"time" : "3:45 pm",
					"hours" : "2",
					"location" : "Northeastern University",
					"comments" : "",
				},
				{	"id" : "2",
					"student" : "Matt",
					"activityname" : "community service",
					"date" : "12/03/17",
					"time" : "3:41 am",
					"hours" : "1",
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
				{	"id" : "1",
					"student" : "Pahal",
					"action" : "Service Learning",
					"type" : "Voting",
					"date" : "12/04/17",
					"time" : "12:42 pm"
				},
				{	"id" : "2",
					"student" : "Tom",
					"action" : "community service",
					"date" : "1/03/17",
					"time" : "7:11 am",
					"type" : "other"
				},
				{	"id" : "3",
					"student" : "Sarah",
					"action" : "Service Learning",
					"type" : "Voting",
					"date" : "01/04/17",
					"time" : "12:42 pm"
				},
				{	"id" : "4",
					"student" : "Matt",
					"action" : "community service",
					"date" : "02/03/17",
					"time" : "7:11 am",
					"type" : "other"
					},
					{	"id" : "5",
					"student" : "Matt",
					"action" : "Service Learning",
					"type" : "Voting",
					"date" : "12/04/17",
					"time" : "12:42 pm"
				},
				{	"id" : "6",
					"student" : "Tom",
					"action" : "community service",
					"date" : "1/03/17",
					"time" : "7:11 am",
					"type" : "other"
				},
				{	"id" : "7",
					"student" : "Pahal",
					"action" : "Service Learning",
					"type" : "Voting",
					"date" : "12/04/17",
					"time" : "12:42 pm"
				},
				{	"id" : "8",
					"student" : "Tom",
					"action" : "community service",
					"date" : "1/03/17",
					"time" : "7:11 am",
					"type" : "other"
				},
				{	"id" : "9",
					"student" : "Sarah",
					"action" : "Service Learning",
					"type" : "Voting",
					"date" : "12/04/17",
					"time" : "12:42 pm"
				},
				{	"id" : "10",
					"student" : "Sarah",
					"action" : "community service",
					"date" : "01/04/17",
					"time" : "7:11 am",
					"type" : "other"
					},
					{	"id" : "11",
					"student" : "Pahal",
					"action" : "Service Learning",
					"type" : "Voting",
					"date" : "12/04/17",
					"time" : "12:42 pm"
				},
				{	"id" : "12",
					"student" : "Pahal",
					"action" : "community service",
					"date" : "01/31/17",
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
				{	"id" : "1",
					"student" : "Pahal",
					"topic" : "Fundraising",
					"date" : "12/04/17",
					"time" : "12:42 pm"
				},
				{	"id" : "2",
					"student" : "Sarah",
					"topic" : "community service",
					"time" : "3:45 am",
					"date" : "12/04/17",
				},
			];
		});

	}
	$scope.getData();
}]);