function displaySnackbar(message) {
    var snackbar = document.getElementById("snackbar");
    snackbar.innerText = message
    snackbar.className = "show";
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}