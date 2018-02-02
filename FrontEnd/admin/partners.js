// Partners js with real get requests(placeholder urls) commented out 
// dummy get, post requests in place along with real ones commented out
// needs edit and remove post requests.
// no checks present, need to ensure form completion and condtition coverage

PartnerApp = angular.module('PartnerApp', ['PartnerApp.controllers', 'smart-table', 'ui.bootstrap']);
	
angular.module('PartnerApp.controllers', []).controller('testController',  ['$scope', '$http', '$uibModal', function($scope, $http, $modal) {
	$scope.loading = false;
	var modalInstance = null;

	// partner commented out for demo hardcoded data 
	// to be removed post connection with database
	var partner = []
	var partner = [
		{	"id" : "1",
			"partner" : "Alfreds Futterkiste",
			"contact" : "Pahal Singhania",
			"phone" : "1234567890",
			"email" : "pahalsinghania@yahoo.com"
		},
		{	"id" : "2",
			"partner" : "D'Amore-McKim",
			"contact" : "Matt",
			"phone" : "1234567890",
			"email" : "matt@mcKim.com"
		},
		{	"id" : "3",
			"partner" : "CCIS",
			"contact" : "sarah",
			"phone" : "1234567890",
			"email" : "matt@mcKim.com"
		},
		{	"id" : "4",
			"partner" : "Libero",
			"contact" : "Tom",
			"phone" : "1234567890",
			"email" : "libero@husky.neu.edu"
		}
	];		
	
	$scope.getData = function() {
		$scope.loading = true;

		// get request for partner with dummy url
		/*
		$http.get("Enter URL Here ")
		.then(function(response){
			$scope.partner = response.data;
			$scope.loading = false;
		});
		*/


		//dummy get request for Parnters
		$http.get("http://dummy.restapiexample.com/api/v1/employees")
		.then(function(response){
			$scope.loading = false;

			// hardcoded partners for demo
			$scope.partner = [
				{	"id" : "1",
					"partner" : "Alfreds Futterkiste",
					"contact" : "Pahal Singhania",
					"phone" : "1234567890",
					"email" : "pahalsinghania@yahoo.com"
				},
				{	"id" : "2",
					"partner" : "D'Amore-McKim",
					"contact" : "Matt",
					"phone" : "1234567890",
					"email" : "matt@mcKim.com"
				},
				{	"id" : "3",
					"partner" : "CCIS",
					"contact" : "sarah",
					"phone" : "1234567890",
					"email" : "matt@mcKim.com"
				},
				{	"id" : "4",
					"partner" : "Libero",
					"contact" : "Tom",
					"phone" : "1234567890",
					"email" : "libero@husky.neu.edu"
				}
			];		
		});
	}
	$scope.getData();

	// get data and real url commented out 
	$scope.addPartner = function(params) {
	
			if (!$scope.partnerName) {
				console.error("no params");
				return;
			}else {
			
			
			$http({
				method: 'POST',
				//url: "http://128.31.25.76:8088/nuaces-1.0/partner/add?userid="+5+"&partner="+$scope.partnerName+"&contact="+$scope.contact+"&phone="+$scope.phone+"&email="+$scope.email,
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
				$scope.partner.push({
					"id" : "5",
					"partner" : $scope.partnerName,
					"contact" : $scope.contact,
					"phone" : $scope.phone,
					"email" : $scope.email
				});
			}
		}

	// dummy remove request
	$scope.removeItem = function removeItem(row) {
        var index = $scope.rowCollection.indexOf(row);
        if (index !== -1) {
            $scope.rowCollection.splice(index, 1);
        }
    }
}]);