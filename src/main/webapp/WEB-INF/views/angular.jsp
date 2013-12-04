<%@include file="/WEB-INF/views/common/header.jsp" %>
<script>
function FoodController($scope) {
	  $scope.foods = ["pacal", "krumpli", "bableves"];
}
</script>

<div ng-controller="FoodController">
name: <input type="text" ng-model="name"/>
<hr/>
<h3>welcome {{name}}</h3>

<ul ng-repeat="food in foods">
  <li>{{food}}</li>
</ul>

</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>
