async function register() {
    //get values of elements
    let usernameInputElem = document.getElementById("username");
    let passwordInputElem = document.getElementById("password");
    let firstnameInputElem = document.getElementById("firstname");
    let lastnameInputElem = document.getElementById("lastname");
    let emailInputElem = document.getElementById("email");
    let phoneInputElem = document.getElementById("phone");
    let stateInputElem = document.getElementById("state");
    let zipInputElem = document.getElementById("zip");
    let addressInputElem = document.getElementById("address");
    //put values into json notation
    let user = {
        username: usernameInputElem.value,
        password: passwordInputElem.value,
        role: '1',
        firstname: firstnameInputElem.value,
        lastname: lastnameInputElem.value,
        email: emailInputElem.value,
        phone: phoneInputElem.value,
        state: stateInputElem.value,
        zip: zipInputElem.value,
        address: addressInputElem.value
    }
    //send http request
    let response = await fetch("http://localhost:9001/register", {
        method: "POST",
        body: JSON.stringify(user) //passing json user
    })
    //awaiting response from server
    let responseBody = await response.json();

    if(responseBody.success == false) {
        let messageElem = document.getElementById("message")
        messageElem.innerText = responseBody.message
    }else{
        console.log("user created");
        window.location = "../index.html"
    }

/*     console.log(usernameInputElem.value, passwordInputElem.value);
 */
}