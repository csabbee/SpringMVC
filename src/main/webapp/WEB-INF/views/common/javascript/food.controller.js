function FoodController($scope, $http) {
	  $scope.jsonResult;
	  $scope.nextId = 100;
      $scope.newFood = {};	
      $scope.foods={};
      
	  $scope.refresh = function() {
		  console.log("refresh ...");

		  $http({method: 'GET', url: '/mvc/food'}).
		  success(function(data, status, headers, config) {
			  $scope.jsonResult = "OK " + data;
			  console.log("num of foods: " + data.length);
			  data.forEach(function(food) {
				  $scope.foods[food.id] = food;
			  });
		  }).
		  error(function(data, status, headers, config) {
			  $scope.jsonResult = "ERROR " + data;
		  });
		  $scope.newFood = new Object();
	  };
	  
	  $scope.refresh();
      
	  $scope.remove = function(foodId) {		  
		  $http({method: 'DELETE', url: '/mvc/food/' + foodId}).
		  success(function(data, status, headers, config) {
			  $scope.jsonResult = "OK " + data;
			  delete $scope.foods[foodId];
		  }).
		  error(function(data, status, headers, config) {
			  $scope.jsonResult = "ERROR " + data;
		  });
	  };
	  
	  $scope.addFood = function() {
		  console.log("debug1:" + $scope.newFood);
		  
		  $http({method: 'POST', url: '/mvc/food', data: $scope.newFood}).
		  success(function(data, status, headers, config) {
			  console.log("debug2:");
			  $scope.jsonResult = "OK " + data;
			  console.log("ADD ok");
			  $scope.refresh();
		  }).
		  error(function(data, status, headers, config) {
			  console.log("debug3");
			  $scope.jsonResult = "ERROR " + data;
		  });
		  console.log("debug4");
	  };
	  
	  $scope.orderFood = function(foodId) {
		  $http({method: 'POST', url: '/mvc/food/order' + foodId}).
		  succes(function(data, status, headers, config) {
			  $scope.jsonResult = "OK " + data;
		  }).
		  error(function(data, status, headers, config) {
			  $scope.jsonResult = "ERROR" + data;
		  });
	  };
}