<html>

<head>
    <link rel="stylesheet" href="billing_page_style.css">
</head>

<body>
<form id="back" action="/" method="post" onclick="animate()"><input type="submit" value="<|back"></form>
<h1 id="H1">Billing Page</h1>

<form id="search_division" method="post" action="search">
    <table>
        <tr>
            <td id="search_icon">&nbsp;&nbsp;%%</td><td><input onblur="addSearchWord()" onclick="clearSearch()" id="search_space" type="text" name="search_word" value="Search..."></td><td id="id1"><input id="search_button" type="submit" name="go" value="GO">
        </td>
        </tr>
    </table>
</form>

<div id="search_result">

    <table id="search_result_table">
        ${search_result}
    </table>
</div>

<div id="error">${error}</div>

<div id="products_list">
    <table id="products_list_table">
        <tr>
            <th  colspan="4"><h2 id="cart_heading">YOUR CART</h2></th>
        </tr>
        <tr>
            <th>Product Code</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Total Cost(with GST)</th>
        </tr>
        ${cart}

        <tr class="no_border white"><td colspan="4"class="white" ></td></tr>
        <tr class="no_border"><td colspan="3">Total Cost Of Your Cart</td><td id="cart_cost">0.00</td></tr>
    </table>

</div>

<script type="text/javascript" src="billing_page_js_file.js"></script>
</body>

</html>