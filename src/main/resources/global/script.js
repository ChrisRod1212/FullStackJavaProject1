let domain = "http://localhost:9001"

async function toDashboard() {
    let backResponse = await fetch("http://localhost:9001/session", {
        method: "GET",
    })
    let backResponseBody = await backResponse.json();

    
    if(backResponseBody.success == false) {
        let messageElem = document.getElementById("message")
        messageElem.innerText = backResponseBody.message
    }else{

        if(backResponseBody.data.role == 0){
            window.location = "../managerdashboard/"
            console.log("User ID: "+backResponseBody.data.userId)
            console.log("User Role: "+backResponseBody.data.role)
        }else{
            window.location = "../dashboard/"
            console.log("User ID: "+backResponseBody.data.userId)
            console.log("User Role: "+backResponseBody.data.role)
        }
    }

}