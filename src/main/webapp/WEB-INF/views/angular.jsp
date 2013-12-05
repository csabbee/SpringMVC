<%@include file="/WEB-INF/views/common/header.jsp" %>
<h4>v1.00</h4>

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
