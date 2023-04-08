let roverTypes = document.querySelectorAll("button[id*='type']");

roverTypes.forEach(button => button.addEventListener("click", function () {
    const buttonId = this.id;

    let roverType = buttonId.split("type")[1];
    let hiddenMarsRoverType = document.getElementById("hiddenMarsRoverType");
    hiddenMarsRoverType.value = roverType;

    let cameras = document.querySelectorAll("input[id*='camera']");
    cameras.forEach(camera => {
        if(camera.disabled === true && camera.checked === true) camera.checked = false
    });

    document.getElementById("roverTypeFrom").submit();
}))

let roverType = document.getElementById("hiddenMarsRoverType").value;

if(roverType === "") roverType = "Curiosity";

document.getElementById("type" + roverType).classList.remove("btn-secondary");
document.getElementById("type" + roverType).classList.add("btn-primary");
