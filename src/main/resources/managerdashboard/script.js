async function reimbIdLookup() {
    //get values of elements
    let reimbIdInputElem = document.getElementById("reimbId");
    //put values into json notation
    let reimbId = reimbIdInputElem.value;
        
    let session = await fetch("http://localhost:9001/session", { 
        method: "GET"
    });

    let sessionResponseBody = await session.json();
    //send http request
    let response = await fetch("http://localhost:9001/reimbursement/"+reimbIdInputElem.value); {
        method: "GET"
    }
    //awaiting response from server
    let responseBody = await response.json();

    
    if(responseBody.success == false) {
        let messageElem = document.getElementById("message")
        messageElem.innerText = responseBody.message
    }else{

        if(responseBody.data.USER_ROLE_ID == 0) {
            window.location = "./reimbursement/"+reimbIdInputElem.value
        }else{
            window.location = "../dashboard/"
            
        }
    }

}
async function myReimbursements() {
    let session = await fetch("http://localhost:9001/session", { 
        method: "GET"
    });

    let sessionResponseBody = await session.json();

    window.location = "../myreimbursements"
}


