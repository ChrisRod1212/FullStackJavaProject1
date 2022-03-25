async function myReimbursements() {
    let session = await fetch("http://localhost:9001/session", { 
        method: "GET"
    });

    let sessionResponseBody = await session.json();

    window.location = "../myreimbursements"
}

async function getAllReimbursements(){

    
    let response = await fetch(`${domain}/reimbursement?userId=${user.id}`);

    let responseBody = await response.json();

    console.log(responseBody)

    let reimbursements = responseBody.data;


    reimbursements.forEach(reimbursement => {
        createList(reimbursement)
    });


}

function viewList(event){

    let viewBtn = event.target;

    let listId = viewBtn.id.substring("view-btn-".length);


    window.location = `../grocerylist?listId=${listId}`

}