<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>Shopping Cart Application</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Shopping Cart</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="/">Home</a></li>
				<li><a href="/productList">Product List</a></li>
				<li><a href="/editCart">Cart</a></li>
			</ul>
		</div>
	</nav>
	${userMessage }
	<c:choose>
		<c:when test="${mode == 'HOME' }">
			<div class="container">
				<h3>Welcome to your Shopping Cart Application</h3>
				<p>Shopping cart helps you to add the product/s to your cart
					while shopping</p>
			</div>
		</c:when>
		<c:when test="${mode == 'SHOW_PRODUCTS' }">
			<div class="container">
				<p>Type something in the input field to search the table for
					product names, category</p>
				<input class="form-control" id="myInput" type="text"
					placeholder="Search.."> <br>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Product Name</th>
							<th>Product Category</th>
							 <th>Quantity already in the cart</th>
							<th>Price/Item</th>
							<th>Add to Cart</th>
							<th>Show Details</th>
						</tr>
					</thead>
					<tbody id="myTable">
						<c:forEach var="product" items="${products}">

							<tr>
								<td>${product.productName}</td>
								<c:choose>
									<c:when test="${product.productName == 'book' }">
										<td>${product.genere}</td>
									</c:when>
									<c:when test="${product.productName == 'apparal'}">
										<td>${product.type}</td>
									</c:when>
								</c:choose>
								<td>${product.quantity}</td>
								<td>${product.price}</td>

								<td><a href="addToCart?id=${product.productId}"><span
										class="glyphicon glyphicon-plus"></span></a></td>

								<td><a href="showDetails?id=${product.productId}"
									class="btn btn-info btn-lg"> <span
										class="glyphicon glyphicon-info-sign"></span></a></td>
							</tr>
						</c:forEach>
						<!-- <tr>
							<td>John</td>
							<td>Doe</td>
							<td>john@example.com</td>
						</tr>
						<tr>
							<td>Mary</td>
							<td>Moe</td>
							<td>mary@example.com</td>
						</tr>
						<tr>
							<td>July</td>
							<td>Dooley</td>
							<td>july@example.com</td>
						</tr> -->
					</tbody>
				</table>
				
			</div>
			<label>${userMessage}</label>
		</c:when>
		<c:when test="${mode == 'PRODUCT_DETAILS' }">
			<table class="table table-condensed">
				<thead>
					<tr>
						<th>Product Name</th>
						<th>Type</th>
						<th>Quantity</th>
						<th>Total Price</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${product.productName}</td>
						<c:choose>
							<c:when test="${product.productName == 'book' }">
								<td>${product.genere}</td>
							</c:when>
							<c:when test="${product.productName == 'apparal'}">
								<td>${product.type}</td>
							</c:when>
						</c:choose>

						<td>
							<div class="col-lg-2">
								<div class="input-group">
									<span class="input-group-btn" >
										<button type="button" 
											class="quantity-left-minus btn btn-danger btn-number"
											data-type="minus" data-field="quantity${product.productId}">
											<span class="glyphicon glyphicon-minus"></span>
										</button>
									</span>
									
									<input  id="quantity" size="7px" name = "quantity${product.productId}" disabled="disabled"
										value="${product.quantity}" />
										
									<span class="input-group-btn">
										<button type="button"
											class="quantity-right-plus btn btn-success btn-number"
											data-type="plus" data-field="quantity${product.productId}">
											<span class="glyphicon glyphicon-plus"></span>
										</button>
									</span>
								</div>
							</div> <%-- <form:input path="products[${productStatus.index}].quantity" value = "${product.quantity}" /> --%>
						</td>
						<label hidden="true" id="price">${product.price}</label>
						<td><label id="totalPrice">${product.price*product.quantity}</label>  </td>
						
					</tr>
					<tr/><tr/>
					<tr><td/>
						<td><%-- <a href="updateProduct?id=${product.productId}&quantity="
									class="btn btn-info btn-lg"> <span	class="glyphicon glyphicon-info-sign"></span>
							</a> --%>
							
							<a  class="btn btn-primary btn btn-dark" href='' onclick="this.href='updateProduct?id=${product.productId}&quantity='+document.getElementById('quantity').value">
							update</a>
							
						</td>
					<td><!-- <button type="button" formaction="/productList"
											class="btn btn-dark"> Back
										</button> -->
										
								<a class="btn btn-primary btn btn-dark" href="/productList" role="button">Back</a>			
							</td></tr>
					
					
				</tbody>
			</table>
			
			<label>Updating the record with 0 quantity delete from the cart</label>
		</c:when>
	</c:choose>

	<script>
		$(document).ready(function(){
  			$("#myInput").on("keyup", function() {
    		var value = $(this).val().toLowerCase();
    		$("#myTable tr").filter(function() {
      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    			});
  			});
  			
		});
	
		$('.btn-number').click(function(e){
			e.preventDefault();
			id = $(this).attr('data-field');
			price = parseInt(($("label[id = 'price']")).text());
			totalPrice = $("label[id = 'totalPrice']");
			var quantitiy=0;
			 type    = $(this).attr('data-type');
			 input = $("input[name = '"+id+"']");
			   /* $('.quantity-right-plus').click(function(e){ */
				   if(type== 'plus')
				   {
					var id =
			        // Stop acting like a button
			        
			        // Get the field name
			       
			        // If is not undefined
			        currentValue =  parseInt(input.val());
			            if(currentValue <10)
			            	input.val(currentValue + 1).change();

			          
			            // Increment
			        
			    /* }); */
			   }else if(type =='minus'){
				   
			   
			    /*  $('.quantity-left-minus').click(function(e){ */
			        // Stop acting like a button
			        e.preventDefault();
			        // Get the field name
			       currentValue =  parseInt(input.val());
			        // If is not undefined
			      
			            // Increment
			            if(currentValue>0){
							input.val(currentValue - 1).change();
			            }
			    /* });  */
				}
			   totalPrice.text(parseInt(input.val())*price).change();
			});
	
	</script>
</body>
</html>

