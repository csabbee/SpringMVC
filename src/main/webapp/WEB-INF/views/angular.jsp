<%@include file="/WEB-INF/views/common/header.jsp" %>
<h4>v1.00</h4>

<div ng-controller="OrderAndFoodController">

<button class="btn btn-success" ng-click="refresh()">refresh</button>

<table>
	<tr>
		<td>
		  <div class="span6">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>id</th>
						<th>food</th>
						<th>price</th>
						<th>
						  <div align="center">actions</div>
						</th>
					</tr>
				</thead>
				<tr ng-repeat="food in foods">
					<td>{{food.id}}</td>
					<td>{{food.name}}</td>
					<td>{{food.price}}</td>
					<td>
					 <div align="center">
						<button class="btn btn-danger" ng-click="remove(food.id)">
							<i class="icon icon-trash icon-white"></i>
						</button>
						<button class="btn btn-success" ng-click="addOrderItem(food.id, 1)">
              <i class="icon icon-plus-sign icon-white"></i>
            </button>
           </div>
					</td>
				</tr>
			</table> <!-- <h3>result: {{jsonResult}}</h3>-->
			</div>
			<hr />
			<form class="form-inline">
				<input type="text" class="input-small" placeholder="food" ng-model="newFood.name"> 
				<input type="text" class="input-small" placeholder="price" ng-model="newFood.price">
				<button type="submit" class="btn btn-succes" ng-click="addFood()">Add</button>
			</form>
		<td>
		<td>
      <table>
        <thead>
          <tr>
            <th>food</th>
            <th>quantity</th>
          </tr>
        </thead>
        <tr ng-repeat="orderitem in order">
          <td>{{orderitem.id}}</td>
          <td>{{orderitem.quantity}}</td>
          <td>
            <button class="btn btn-danger" ng-click="removeFoodFromOrder(orderitem.id)">
              <i class="icon icon-trash icon-white"></i>
            </button>
          </td>
        </tr>
      </table>
    </td>
	</tr>
</table>

</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>
