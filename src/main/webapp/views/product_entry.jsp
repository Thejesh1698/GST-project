<html>

<head>
    <link rel="stylesheet" href="product_entry_style.css">
</head>

<body>
<h1 id="main_heading">
    Product Entry & Listing page
</h1>

<div id="error">${error}</div>

<div id="buttons_division">
    <button id="button1" onclick="openForm()">+ Click here to add a new product</button>

    <form id="form1" method="post" action="billing_page"><input id="button2" type="submit" value="Billing Page"></form>
</div>

<div id="productForm">
    <div class="close" onclick="closeForm()" readonly>&nbsp;x&nbsp;</div>
    <form action="add_product" method="post">
        <h2>Add Product Details</h2>
        <table>
            <tr>
                <td>Product Code:</td><td><input type="text" name="product_code" pattern="[p][c].{1,}" title="example: pc1,pc2,pc3"></td>
            </tr>
            <tr>
                <td>Product Name:</td><td><input type="text" name="product_name"></td>
            </tr>
            <tr>
                <td>Product Price:</td><td><input type="number" name="product_price" step="0.01"></td>
            </tr>
            <tr>
                <td>Product Gst:</td><td><input type="number" name="product_gst" step="0.01" min="0.00" max="100.00"></td>
            </tr>

        </table>
        <input type="submit" class="btn" value="Add">
        <input type="reset" class="btn" value="Clear">

    </form>
</div>

<div id="editForm">
    <div class="close" onclick="closeEditForm()" readonly>&nbsp;x&nbsp;</div>
    <form action="edit_product" method="post">
        <h2>Change Product Details</h2>
        <table>
            <tr>
                <td>Product Code:(cannot be changed)</td><td><input id="editFormInput1" type="text" name="product_code" pattern="[p][c].{1,}" title="example: pc1,pc2,pc3" readonly></td>
            </tr>
            <tr>
                <td>Product Name:</td><td><input id="editFormInput2" type="text" name="product_name"></td>
            </tr>
            <tr>
                <td>Product Price:</td><td><input id="editFormInput3" type="number" name="product_price" step="0.01"></td>
            </tr>
            <tr>
                <td>Product Gst:</td><td><input id="editFormInput4" type="number" name="product_gst" step="0.01" min="0.00" max="100.00"></td>
            </tr>

        </table>
        <input type="submit" name="submit" class="btn" value="Edit">
        <input type="submit" name="submit" class="btn" value="Delete">
    </form>
</div>

<div id="main_division">

    <table>
        <tr>
            <th>Product Code</th>
            <th>Product Name</th>
            <th>Price (per item)</th>
            <th>GST percentage</th>
            <th>Edit</th>
        </tr>
        ${info}
    </table>
</div>

<script type="text/javascript" src="product_entry_js_file.js"></script>
</body>

</html>