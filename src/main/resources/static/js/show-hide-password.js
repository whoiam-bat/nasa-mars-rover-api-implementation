let check = document.getElementById("show-hide");

check.addEventListener("click", function () {
    let inpt = document.getElementById("password");
    if (inpt.type === "password") {
        inpt.type = "text";
    } else {
        inpt.type = "password";
    }
})

