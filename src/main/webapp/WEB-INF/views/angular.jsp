<%@include file="/WEB-INF/views/common/header.jsp" %>
<h4>v0.93</h4>
<script>
function FoodController($scope, $http) {
	  $scope.jsonResult;
	  $scope.nextId = 100;
      $scope.newFood = {};	
      $scope.foods={};

	  $scope.refresh = function(foodId) {
		  $http({method: 'GET', url: '/mvc/food'}).
		  success(function(data, status, headers, config) {
			  $scope.jsonResult = "OK " + data;
			  $scope.foods=data;
		  }).
		  error(function(data, status, headers, config) {
			  $scope.jsonResult = "ERROR " + data;
		  });
	  }
      
	  $scope.remove = function(foodId) {
		  //delete $scope.foods[foodId];
		  
		  $http({method: 'GET', url: '/mvc/food/k1'}).
		  success(function(data, status, headers, config) {
			  $scope.jsonResult = "OK " + data;
			  $scope.foods[data.id]=data;
		  }).
		  error(function(data, status, headers, config) {
			  $scope.jsonResult = "ERROR " + data;
		  });
	  }
	  
	  $scope.addFood = function() {
		  console.log($scope.newFood);
		  $scope.newFood.id = 'x' + $scope.nextId++;
		  $scope.foods[$scope.newFood.id] = $scope.newFood;
		  $scope.newFood = new Object();
	  }
}
</script>

<div ng-controller="FoodController">
name: <input type="text" ng-model="name"/>
<hr/>
<h3>welcome {{name}}</h3>

<button class="btn btn-success" ng-click="refresh()">refresh</button>
<ul ng-repeat="food in foods">
  <li >[{{food.id}}] {{food.name}}
  <button class="btn btn-danger" ng-click="remove(food.id)">
    <i class="icon icon-trash icon-white" ></i>
    </button>    
  </li>
</ul>

<h3>result: {{jsonResult}}</h3>
<hr/>
<form class="form-inline">
   <input type="text" class="input-small" placeholder="food" ng-model="newFood.name">
   <input type="text" class="input-small" placeholder="price" ng-model="newFood.price">
   <button type="submit" class="btn" ng-click="addFood()">Add</button>
</form>

</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>
