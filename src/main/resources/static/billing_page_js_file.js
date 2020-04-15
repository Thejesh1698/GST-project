

function clearSearch(){
    document.getElementById('search_space').value="";
}

function addSearchWord(){
    var c=document.getElementById('search_space').value;
    if(c==""){
        document.getElementById('search_space').value="Search...";
    }
}


function changeTotalCost(ele){
    var id=ele.id;
    var cost=ele.name;
    var val=ele.value;

    if(val<0){
        document.getElementById("2"+id).textContent="negative values not allowed";
        changeCartCost();
    }
    else if(val.indexOf(".")!=-1){
        document.getElementById("2"+id).textContent="fractional values not allowed";
        changeCartCost();
    }
    else{
        document.getElementById("2"+id).textContent=(cost*val).toFixed(2);
        changeCartCost();
    }


}


function changeCartCost(){

    var total=0.0;
    var lis=document.getElementsByClassName("total_row_cost");

    for(var i=0;i<lis.length;i++){
        var temp=lis[i].textContent;
        if(temp!="negative values not allowed"&&temp!="fractional values not allowed") {
            total += parseFloat(temp);
        }
    }

    document.getElementById("cart_cost").textContent=total.toFixed(2);
}

window.onload = changeCartCost();
