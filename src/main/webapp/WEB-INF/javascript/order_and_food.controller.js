function OrderAndFoodController($scope, $http) {
	$scope.jsonResult;
	$scope.nextId = 100;
	$scope.newFood = {};
	$scope.foods = {};
	$scope.order = {};
	$scope.newOrderItem = {};
	$scope.cartId = 0;
	$scope.total;

	$scope.refresh = function() {
		console.log("refresh ...");
		$http({
			method : 'POST',
			url : '/mvc/order/cart'
		}).success(function(data, status, headers, config) {
			console.log("cartId received: " + angular.fromJson(data).id);
			$scope.cartId = angular.fromJson(data).id;
		}).error(function(data, status, headers, config) {
			$scope.jsonResult = "ERROR " + data;
		});

		$http({
			method : 'GET',
			url : '/mvc/food'
		}).success(function(data, status, headers, config) {
			$scope.jsonResult = "OK " + data;
			console.log("num of foods: " + data.length);
			data.forEach(function(food) {
				$scope.foods[food.id] = food;
			});
		}).error(function(data, status, headers, config) {
			$scope.jsonResult = "ERROR " + data;
		});
		
		$http({
			method : 'GET',
			url : '/mvc/order/cart/' + $scope.cartId
		}).success(function(data, status, headers, config) {
			console.log("cartcontent received: " + angular.fromJson(data));
			data.forEach(function(orderItem) {
				$scope.order[orderItem.id] = orderItem;
			});
		}).error(function(data, status, headers, config) {
			$scope.jsonResult = "ERROR " + data;
		});
		$scope.newFood = new Object();
	};

	$scope.refresh();
	
	$scope.remove = function(foodId) {
		$http({
			method : 'DELETE',
			url : '/mvc/food/' + foodId
		}).success(function(data, status, headers, config) {
			$scope.jsonResult = "OK " + data;
			delete $scope.foods[foodId];
		}).error(function(data, status, headers, config) {
			$scope.jsonResult = "ERROR " + data;
		});
	};

	$scope.addFood = function() {
		console.log("debug1:" + $scope.newFood);

		$http({
			method : 'POST',
			url : '/mvc/food',
			data : $scope.newFood
		}).success(function(data, status, headers, config) {
			console.log("debug2:");
			$scope.jsonResult = "OK " + data;
			console.log("ADD ok");
			$scope.refresh();
		}).error(function(data, status, headers, config) {
			console.log("debug3");
			$scope.jsonResult = "ERROR " + data;
		});
		console.log("debug4");
	};

	$scope.addOrderItem = function(foodId) {
		$scope.newOrderItem.foodId = foodId;
		console.log("debug6:" + $scope.newOrderItem.foodId);
		console.log("debug7:" + $scope.newOrderItem.quantity);
		console.log("debug8:" + angular.toJson($scope.newOrderItem));

		$http({
			method : 'PUT',
			url : '/mvc/order/cart/' + $scope.cartId,
			data : angular.toJson($scope.newOrderItem)
		}).success(function(data, status, headers, config) {
			console.log("ADD orderItem ok");
			$scope.refresh();
		}).error(function(data, status, headers, config) {
			console.log("error adding orderItem");
		});
	};

	$scope.removeFoodFromOrder = function(orderItemId) {
		$http({
			method : 'DELETE',
			url : '/mvc/order/cart/' + $scope.cartId + '/' + orderItemId
		}).success(function(data, status, headers, config) {
			$scope.jsonResult = "OK " + data;
			delete $scope.order[orderItemId];
		}).error(function(data, status, headers, config) {
			$scope.jsonResult = "ERROR " + data;
		});
	};

	$scope.checkOut = function() {
		$http({
			method : 'POST',
			url : '/mvc/order/cart/' + $scope.cartId
		}).success(function(data, status, headers, config) {
			$scope.jsonResult = "OK " + data;
		}).error(function(data, status, headers, config) {
			$scope.jsonResult = "ERROR " + data;
		});
		$scope.order = {};
		$scope.refresh();
	};
	
}