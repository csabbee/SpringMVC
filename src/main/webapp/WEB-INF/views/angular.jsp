<%@include file="/WEB-INF/views/common/header.jsp"%>
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
									<table>
										<tr>
											<button class="btn btn-danger" ng-click="remove(food.id)">
												<i class="icon icon-trash icon-white"></i>
											</button>
											<form class="form-inline">
												<input type="hidden" ng-init="newOrderItem.quantity='1'" ng-model="newOrderItem.quantity">
												<button type="submit" class="btn btn-success" ng-click="addOrderItem(food.id)">
													<i class="icon icon-plus-sign icon-white"></i>
												</button>
											</form>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<!-- <h3>result: {{jsonResult}}</h3>-->
					<h3>cartId: {{cartId}}</h3>
				</div>
				<hr />
				<form class="form-inline">
					<input type="text" class="input-small" placeholder="food" ng-model="newFood.name"> 
					<input type="text" class="input-small" placeholder="price" ng-model="newFood.price">
					<button type="submit" class="btn btn-succes" ng-click="addFood()">Add</button>
				</form>
			<td>
			<td>
				<div class="span6">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>food</th>
								<th>quantity</th>
							</tr>
						</thead>
						<tr ng-repeat="orderItem in order">
							<td>{{orderItem.id}}</td>
							<td>{{orderItem.quantity}}</td>
							<td>
								<button class="btn btn-danger" ng-click="removeFoodFromOrder(orderItem.id)">
									<i class="icon icon-trash icon-white"></i>
								</button>
							</td>
						</tr>
						<tfoot>
						<tr>
              <td>
               <div align="center">
                <button class="btn btn-success btn-large" ng-click="checkOut()">
                  <span class="glyphicon icon-shopping-cart icon-white" style="font-size:30px"></span>
                </button>
               </div>
              </td>
            </tr>
						</tfoot>
					</table>
				</div>
			</td>
		</tr>
	</table>

</div>
<%@include file="/WEB-INF/views/common/footer.jsp"%>
