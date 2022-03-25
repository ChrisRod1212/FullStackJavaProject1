
async function submitRequest() {
    //get values of elements
    let reimbTypeInputElem = document.getElementById("reimbType");
    let reimbAmntInputElem = document.getElementById("reimbAmnt");
    let descriptionInputElem = document.getElementById("description");
    
    let session = await fetch("http://localhost:9001/session", { 
        method: "GET"
    });

    let sessionResponseBody = await session.json();

    
    //put values into json notation
    let reimbursement = {
        author : sessionResponseBody.data.userId,
        typeId : reimbTypeInputElem.value,
        description : descriptionInputElem.value,
        amount : reimbAmntInputElem.value
    }
    console.log(reimbursement)
    
    //send http request
    let response = await fetch("http://localhost:9001/newreimbursement/", {
        method: "POST",
        body: JSON.stringify(reimbursement)  //passing json reimbursement
    })
    //awaiting response from server
    let responseBody = await response.json();

    if(responseBody.success == false) {
        let messageElem = document.getElementById("message")
        messageElem.innerText = responseBody.message
    }else{
        console.log("reimbursement request submitted");
        createElement 
    }

toDashboard();
}

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
            window.location = "./managerdashboard/?userId="+backResponseBody.data.userId
            console.log("User ID: "+backResponseBody.data.userId)
            console.log("User Role: "+backResponseBody.data.role)
        }else{
            window.location = "./dashboard/?userId="+backResponseBody.data.userId
            console.log("User ID: "+backResponseBody.data.userId)
            console.log("User Role: "+backResponseBody.data.role)
        }
    }

}