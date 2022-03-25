
let user;


window.onload = async function(){
    let sessionResponse = await fetch("http://localhost:9001/session");

    let sessionResponseBody = await sessionResponse.json();

    if(!sessionResponseBody.success){
        window.location = "../";
    }
    
    user = sessionResponseBody.data;
    console.log(user)

    getAllReimbursements();
    
}

async function getAllReimbursements() {
    document.getElementById("reimbursements-container").innerHTML = "";

    let response = await fetch("http://localhost:9001/allreimbursements/");

    let responseBody = await response.json();

    console.log(responseBody)

    let reimbursements = responseBody.data;

    reimbursements.forEach(reimbursement => {
        createReimbursement(reimbursement)
        console.log(reimbursement)
    });


}
async function getAllReimbursementsPending() {
    document.getElementById("reimbursements-container").innerHTML = "";

    let response = await fetch("http://localhost:9001/allreimbursements/status/0");

    let responseBody = await response.json();

    console.log(responseBody)

    let reimbursements = responseBody.data;

    reimbursements.forEach(reimbursement => {
        createReimbursement(reimbursement)
        console.log(reimbursement)
    });


}
async function getAllReimbursementsApproved() {
    document.getElementById("reimbursements-container").innerHTML = "";

    let response = await fetch("http://localhost:9001/allreimbursements/status/1");

    let responseBody = await response.json();

    console.log(responseBody)

    let reimbursements = responseBody.data;

    reimbursements.forEach(reimbursement => {
        createReimbursement(reimbursement)
        console.log(reimbursement)
    });


}
async function getAllReimbursementsDenied() {
    document.getElementById("reimbursements-container").innerHTML = "";

    let response = await fetch("http://localhost:9001/allreimbursements/status/2");

    let responseBody = await response.json();

    console.log(responseBody)

    let reimbursements = responseBody.data;

    reimbursements.forEach(reimbursement => {
        createReimbursement(reimbursement)
        console.log(reimbursement)
    });


}





function createReimbursement(reimbursement) {
    let reimbContainerElem = document.getElementById("reimbursements-container");

    let reimbCardElem = document.createElement("div");
    reimbCardElem.className = "reimb-card";

    reimbCardElem.id = reimbursement.reimbId
    var submitDate = new Date(reimbursement.submitted).toLocaleDateString();
    var resolveDate = new Date(reimbursement.resolved).toLocaleDateString();
    let typeArray = new Array('Transportation','Food','Lodging','Medical','Other');
    var typeId = typeArray[reimbursement.typeId];
    let statusArray = new Array('Pending','Approved','Denied');
    var status = statusArray[reimbursement.status];

    if(reimbursement.resolved != null){
    reimbCardElem.innerHTML = `
    <div id="reimb-container">
          <div class="container-row">
            <div class="reimb-label-left">Reimbursement ID:
              <div>${reimbursement.reimbId}</div>
            </div>
            <div class="reimb-label-right">Author:
              <div>${reimbursement.author}</div>
            </div>
          </div>
          <div class="container-row">
            <div class="reimb-label-left">Type:
              <div>${typeId}</div>
            </div>
            <div class="reimb-label-right">Amount:
              <div>${reimbursement.amount}</div>
            </div>
          </div>
          <div class="container-row">
            <div class="reimb-label-left">Status:
              <div>${status}</div>
            </div>
            <div class="reimb-label-right" id="resolver-label">Resolver:
              <div>${reimbursement.resolver}</div>
            </div>
          </div>
          <div id="reimb-description">Description:
            <div>${reimbursement.description}</div>
          </div>
          <div class="container-row">
            <div class="reimb-label-left">Submitted:
              <div>${submitDate}</div>
            </div>
            <div class="reimb-label-right">Resolved:
              <div>${resolveDate}</div>
            </div>
          </div>
          
        </div>
        
    `
    }else{
        reimbCardElem.innerHTML = `
    <div id="reimb-container">
          <div class="container-row">
            <div class="reimb-label-left" >Reimbursement ID:
              <div>${reimbursement.reimbId}</div>
            </div>
            <div class="reimb-label-right">Author:
              <div>${reimbursement.author}</div>
            </div>
          </div>
          <div class="container-row">
            <div class="reimb-label-left">Type:
              <div>${typeId}</div>
            </div>
            <div class="reimb-label-right">Amount:
              <div>${reimbursement.amount}</div>
            </div>
          </div>
          <div class="container-row">
            <div class="reimb-label-left">Status:
              <div>${status}</div>
            </div>
          </div>
          <div id="reimb-description">Description:
            <div>${reimbursement.description}</div>
          </div>
          <div class="container-row">
            <div class="reimb-label-left">Submitted:
              <div>${submitDate}</div>
            </div>
            <div class="reimb-label-right">Resolved:
              <div>pending</div>
            </div>
          </div>
          <div class="container-row" id="approveDeny">
            <div>
            <select class= "form-input" id="resolve_${reimbursement.reimbId}" placeholder="Reimbursement Type" >
                <option value=0>Pending</option>
                <option value=1>Approve</option>
                <option value=2>Deny</option>

            </select>
            <button class="btn" onclick="resolve("resolve_${reimbursement.reimbId}")>Resolve</button> 
            </div>
          </div>
        </div>
        
    `
    }


    reimbContainerElem.appendChild(reimbCardElem);
}
async function approve(element){

    let sessionResponse = await fetch("http://localhost:9001/session");

    let sessionResponseBody = await sessionResponse.json();

    if(!sessionResponseBody.success){
        window.location = "../";
    }
    
    user = sessionResponseBody.data;


    let reimbursement = {
        status : 1,
        resolver : user.userId,
        reimbId : element.reimbId
    };
    console.log(reimbId);
    let response = await fetch("http://localhost:9001/reimbursement/"+element.reimbId, {
        method: "PATCH" 
    });

    getAllReimbursementsPending();

}
async function deny(element){
    let sessionResponse = await fetch("http://localhost:9001/session");

    let sessionResponseBody = await sessionResponse.json();

    if(!sessionResponseBody.success){
        window.location = "../";
    }
    
    user = sessionResponseBody.data;


    let reimbursement = {
        status : 2,
        resolver : user.userId,
        reimbId : element.reimbId
    };
    console.log(reimbId);
    let response = await fetch("http://localhost:9001/reimbursement/"+element.reimbId, {
        method: "PATCH"
    });

    getAllReimbursementsPending();


}
