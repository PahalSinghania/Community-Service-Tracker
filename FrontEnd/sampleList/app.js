// sample app controller with edit delete, view functionality implemented
// reference code, not relevant to actual website implementation



TestApp = angular.module('TestApp', ['TestApp.controllers', 'smart-table', 'ui.bootstrap']);
	
angular.module('TestApp.controllers', []).controller('testController',  ['$scope', '$http', '$uibModal', function($scope, $http, $modal) {
	$scope.loading = false;
	var modalInstance = null;
	
	$scope.getData = function() {
		$scope.loading = true;
		$http.get("http://dummy.restapiexample.com/api/v1/employees")
		.then(function(response){
			$scope.employees = response.data;
			$scope.loading = false;
		});
	}
	
	$scope.viewRecord = function(id){
		   if(id > 0) {
			  $http.get("http://dummy.restapiexample.com/api/v1/employee/"+id)
				.then(function(response){
					modalInstance = $modal.open({
					  animation: false,
					  templateUrl: 'view/view_record.html',
					  controller: 'empViewCtrl',
					  scope: $scope,
					  size: '',
					  resolve: {
						  record: function () {
							  return response.data;
						  }
					  }
				   });
				});
			 
		   }
			   

	}
	
	$scope.addRecord = function(){
		modalInstance = $modal.open({
		  animation: false,
		  templateUrl: 'view/add_record.html',
		  controller: 'addEmpCtrl',
		  scope: $scope,
		  size: '',
		  resolve: {
		  }
	   });

	}
	
	$scope.editRecord = function(id){
		   if(id > 0) {
			  $http.get("http://dummy.restapiexample.com/api/v1/employee/")
				.then(function(response){
					modalInstance = $modal.open({
					  animation: false,
					  templateUrl: 'view/update_record.html',
					  controller: 'updateEmpCtrl',
					  scope: $scope,
					  size: '',
					  resolve: {
						  record: function () {
							  return response.data;
						  }
					  }
				   });
				});
		   }

	}

	$scope.cancelModal = function(){
	 modalInstance.dismiss('cancel');
	}
	
	$scope.saveRecord = function(params) {
		console.log(params);
		$http.post("http://dummy.restapiexample.com/api/v1/create", params)
			.then(function(response){
				console.log(response);
				$scope.getData();
			});
	}
	
	$scope.updateRecord = function(params) {
		$http.put("http://dummy.restapiexample.com/api/v1/update/"+params.id, params)
			.then(function(response){
			  console.log(response);
			  $scope.getData();
			});
	}
	$scope.deletRecord = function(id) {
		if (confirm('Are you sure you want to delete this?')) {
			 $http.delete("http://dummy.restapiexample.com/api/v1/delete/"+id)
			.then(function(response){
				console.log(response);
			});
		}
		
	}
	$scope.getData();
}]);

TestApp.controller('empViewCtrl',  ['$scope', '$http', 'record', function($scope, $http, record) {
	function init(){
        $scope.employee = record;
    }
	init();
	
}]);

TestApp.controller('addEmpCtrl',  ['$scope', '$http', 'record', function($scope, $http) {
	$scope.saveEmp = function () {
            $scope.datas = {};

            if(!angular.isDefined($scope.employee_name) || $scope.employee_name === '') {
                alert('employee name is empty');
                return;
            }
            else if(!angular.isDefined($scope.employee_age) || $scope.employee_age === '') {
                alert('employee age is empty');
                return;
            }else if(!angular.isDefined($scope.employee.salary) || $scope.employee.salary === '') {
                alert('employee salary is empty');
                return;
            } else {
				$scope.datas.name = $scope.employee_name;
				$scope.datas.age = $scope.employee_age;
				$scope.datas.salary = $scope.employee_salary;
				console.log($scope.datas);
			}
            $scope.cancelModal();
            $scope.saveRecord($scope.datas);
        };
	
}]);

TestApp.controller('updateEmpCtrl',  ['$scope', '$http', 'record', function($scope, $http, record) {
	$scope.employee = {};
	function init(){
		$scope.employee.name = record.employee_name;
		$scope.employee.age = parseInt(record.employee_age);
		$scope.employee.salary = parseInt(record.employee_salary);
		$scope.employee.id = parseInt(record.id);
    }
	$scope.updateEmp = function () {
		$scope.cancelModal();
		if(!angular.isDefined($scope.employee.name) || $scope.employee.name === '') {
                alert('employee name is empty');
                return;
            }
            else if(!angular.isDefined($scope.employee.age) || $scope.employee.age === '') {
                alert('employee age is empty');
                return;
            }else if(!angular.isDefined($scope.employee.salary) || $scope.employee.salary === '') {
                alert('employee salary is empty');
                return;
            }
		$scope.updateRecord($scope.employee);
	}
	init();
	
}]);