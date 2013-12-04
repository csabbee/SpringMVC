<%@include file="/WEB-INF/views/common/header.jsp" %>
<h4>v0.9</h4>
<script>
function FoodController($scope) {
	  $scope.nextId = 100;
      $scope.newFood = {};	
	  $scope.foods = {
	                  'k1': {'id': 'k1', 'name': 'pacal1', 'price': 444},
	                  'k2' : {'id': 'k2', 'name': 'json prokolt', 'price': 560},
	                  'k3' : {'id': 'k3', 'name': 'palacsinta', 'price': 1200},
	                  'k4' : {'id': 'k4', 'name': 'fokhagymas kave', 'price': 300},
	  };
	  
	  $scope.remove = function(foodId) {
		  delete $scope.foods[foodId];
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

<ul ng-repeat="food in foods">
  <li >[{{food.id}}] {{food.name}}
  <button class="btn btn-danger" ng-click="remove(food.id)">
    <i class="icon icon-trash icon-white" ></i>
    </button>    
  </li>
</ul>

<hr/>
<form class="form-inline">
   <input type="text" class="input-small" placeholder="food" ng-model="newFood.name">
   <input type="text" class="input-small" placeholder="price" ng-model="newFood.price">
   <button type="submit" class="btn" ng-click="addFood()">Add</button>
</form>

</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>
