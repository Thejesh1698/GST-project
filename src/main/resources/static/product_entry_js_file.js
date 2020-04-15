function openForm() {
    document.getElementById("productForm").style.display = "block";
    document.getElementById("main_heading").style.opacity = 0.2;
    document.getElementById("buttons_division").style.opacity = 0.2;
    document.getElementById("main_division").style.opacity = 0.2;
}

function closeForm() {
    document.getElementById("productForm").style.display = "none";
    document.getElementById("main_heading").style.opacity = 1;
    document.getElementById("buttons_division").style.opacity = 1;
    document.getElementById("main_division").style.opacity = 1;
}

function openEditForm(ele1,ele2,ele3,ele4) {

    document.getElementById("editFormInput1").value=ele1;
    document.getElementById("editFormInput2").value=ele2;
    document.getElementById("editFormInput3").value=ele3;
    document.getElementById("editFormInput4").value=ele4;

    document.getElementById("editForm").style.display = "block";
    document.getElementById("main_heading").style.opacity = 0.2;
    document.getElementById("buttons_division").style.opacity = 0.2;
    document.getElementById("main_division").style.opacity = 0.2;
}

function closeEditForm() {
    document.getElementById("editForm").style.display = "none";
    document.getElementById("main_heading").style.opacity = 1;
    document.getElementById("buttons_division").style.opacity = 1;
    document.getElementById("main_division").style.opacity = 1;
}