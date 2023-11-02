function generateUniqueNumber() {
    return Math.floor(1000 + Math.random() * 9000);
}

function register() {
    var fullName = document.getElementById("registration-fullname").value;
    var username = document.getElementById("registration-username").value;
    var contactNumber = document.getElementById("registration-contact").value;
    var email = document.getElementById("registration-email").value;
    var password = document.getElementById("registration-password").value;
    var confirmPassword = document.getElementById("registration-confirm-password").value;

    if (password !== confirmPassword) {
        alert("Passwords do not match. Please try again.");
        return;
    }

    var uniqueNumber = generateUniqueNumber();
    document.getElementById("unique-number").innerText = "Your unique number: " + uniqueNumber;

  }



   