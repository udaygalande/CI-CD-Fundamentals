<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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

	<div>
		<form action="/updateCart" modelAttribute="cart" method="post">
		
				<%-- <form:hidden path="cartId" value="1"></form:hidden>
			<form:hidden path="user.id" value="1"></form:hidden> --%>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Product Name</th>
						<th>Product Category</th>
						<th>Quantity</th>
						<th>Price</th>
					</tr>
				</thead>
				<%-- ${cart.products} --%>
				<%-- ${cart.products} --%>
				<c:forEach var="product" items="${cart.products}"
					varStatus="productStatus">
			
					<%-- <label id="id">${product.productId}</label> --%>
					<tr>

						<%--< form:hidden
							path="products[${productStatus.index}].productName"></form:hidden> --%>
						<td>${product.productName}</td>
						<c:choose>

							<c:when test="${product.productName == 'book' }">
								<%-- <form:hidden path="products[${productStatus.index}]."></form:hidden> --%>
								<td><label> ${product.genere} </label>
								</td>
							</c:when>
							<c:when test="${product.productName == 'apparal'}">
								<td><label> ${product.type} </label>
								</td>
							</c:when>
						</c:choose>
						<td>
							<div class="col-lg-2">
								<div class="input-group">
									<%-- <span class="input-group-btn" >
										<button type="button" 
											class="quantity-left-minus btn btn-danger btn-number"
											data-type="minus" data-field="quantity${product.productId}">
											<span class="glyphicon glyphicon-minus"></span>
										</button>
									</span> --%>

									<label
										 min="1"
										id="quantity${product.productId}" size="7px">
									${product.quantity}</label>

									<%-- <span class="input-group-btn">
										<button type="button"
											class="quantity-right-plus btn btn-success btn-number"
											data-type="plus" data-field="quantity${product.productId}">
											<span class="glyphicon glyphicon-plus"></span>
										</button>
									</span> --%>
								</div>
							</div> <%-- <form:input path="products[${productStatus.index}].quantity" value = "${product.quantity}" /> --%>
						</td>
						<td><label id="totalPrice">${product.price*product.quantity}</label>
						</td>
						<%-- <td>${product.price}*${product.quantity}</td> --%>

					</tr>
				</c:forEach>

				<tr>
					<td>
						<a class="btn btn-primary btn btn-dark" href="/deleteAll?id=${cart.cartId}"  data-toggle="tooltip" title="Remove All the products from the Cart" 
						role="button">Remove Cart</a>
					</td>
				</tr>
			</table>
		</form>

	</div>

	<script type="text/javascript">
	
	$(document).ready(function(){
		  $('[data-toggle="tooltip"]').tooltip();   
		});
	
	$('.btn-number').click(function(e){
		e.preventDefault();
		id = $(this).attr('data-field');
		var quantitiy=0;
		 type    = $(this).attr('data-type');
		 input = $("input[id = '"+id+"']");
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
		            if(currentValue>1){
						input.val(currentValue - 1).change();
		            }
		    /* });  */
			}
		     
		});
	</script>
</body>
</html>