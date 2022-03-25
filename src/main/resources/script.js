document.getElementById("login-form").addEventListener("submit", async function (event) {
    event.preventDefault();
    //get values of elements
    let usernameInputElem = document.getElementById("username");
    let passwordInputElem = document.getElementById("password");
    //put values into json notation
    let user = {
        username: usernameInputElem.value,
        password: passwordInputElem.value
    }
    //send http request
    let response = await fetch("http://localhost:9001/session", {
        method: "POST",
        body: JSON.stringify(user) //passing json user
    })
    //awaiting response from server
    let responseBody = await response.json();

    
    if(responseBody.success == false) {
        let messageElem = document.getElementById("message")
        messageElem.innerText = responseBody.message
    }else{
        //window.location = "./dashboard?userId="+responseBody.data.ers_users_id

        if(responseBody.data.role == 0){
            window.location = "./managerdashboard/?userId="+responseBody.data.userId
        }else{
            window.location = "./dashboard/?userId="+responseBody.data.userId
            console.log("User ID: "+responseBody.data.userId)
            console.log("User Role: "+responseBody.data.role)
        }
    }

/*     console.log(usernameInputElem.value, passwordInputElem.value);
 */
})
