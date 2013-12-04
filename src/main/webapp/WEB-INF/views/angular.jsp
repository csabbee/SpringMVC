<%@include file="/WEB-INF/views/common/header.jsp" %>
<h4>v1.00</h4>
<script>
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
			  console.log("num of foods: " + data.length)
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
	  }
	  
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

	  }
}
</script>
<div ng-controller="FoodController">

<button class="btn btn-success" ng-click="refresh()">refresh</button>

<table class="table table-striped" >
<thead>
    <tr>
      <th>id</th>
      <th>food</th>
      <th>price</th>
      <th>action</th>
    </tr>
  </thead>
  <tr ng-repeat="food in foods">
  	<td>{{food.id}}</td>
  	<td>{{food.name}}</td>
  	<td>{{food.price}}</td>
  	<td>
  	<button class="btn btn-danger" ng-click="remove(food.id)">
    <i class="icon icon-trash icon-white" ></i>
    </button>    
  	</td>
  </tr>
</table>

<h3>result: {{jsonResult}}</h3>
<hr/>
<form class="form-inline">
   <input type="text" class="input-small" placeholder="food" ng-model="newFood.name">
   <input type="text" class="input-small" placeholder="price" ng-model="newFood.price">
   <button type="submit" class="btn" ng-click="addFood()">Add</button>
</form>

</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>
