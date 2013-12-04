<%@include file="/WEB-INF/views/common/header.jsp" %>
<script>
function FoodController($scope) {
	  $scope.foods = [
	                  {'id': 'k1', 'name': 'pacal', 'price': 444},
	                  {'id': 'k2', 'name': 'json prokolt', 'price': 560},
	                  {'id': 'k3', 'name': 'palacsinta', 'price': 1200},
	                  {'id': 'k4', 'name': 'fokhagymas kave', 'price': 300},
	                  ];
}
</script>

<div ng-controller="FoodController">
name: <input type="text" ng-model="name"/>
<hr/>
<h3>welcome {{name}}</h3>

<ul ng-repeat="food in foods">
  <li>{{food.name}}</li>
</ul>

</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>
