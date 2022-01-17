const url = "http://localhost:3001/";

document.getElementById("getEmpReimbursements").addEventListener("click", getReimbursements);

document.getElementById("userName").innerHTML = sessionStorage.getItem("f_name");

// let reimbursement; 
const reimbArr = [];

//This gets reimbursements by status -- use this to filter reimbursements
async function getReimbursements() {

    let statusID = document.getElementById("inputGroupSelect01").value;
    

    let response = await fetch(url + "reimbursements/get/" + statusID, { credentials: "include" });

    console.log(response);

    if (response.status === 200) {

        let data = await response.json();

        console.log(data); //DONE TESTING



        for (reimbursement of data) {
            let reimb = {
                "id": reimbursement.id,
                "reimb_amount": reimbursement.reimb_amount,
                "first": reimbursement.reimb_author.f_Name,
                "last": reimbursement.reimb_author.l_Name,
                "reimb_status_id": reimbursement.reimb_status_id,
                "reimb_type": reimbursement.reimb_type

            }

            reimbArr.push(reimb);

            let row = document.createElement("tr");

            let id = document.createElement("td");
            id.innerHTML = reimb.id;
            row.appendChild(id);

            let employee = document.createElement("td");
            employee.innerHTML = reimb.first + " " + reimb.last;
            row.appendChild(employee);

            let amount = document.createElement("td");
            amount.innerHTML = reimb.reimb_amount;
            row.appendChild(amount);

            let reimbStatus = document.createElement("td");
            reimbStatus.innerHTML = reimb.reimb_status_id;
            row.appendChild(reimbStatus);

            let reimbType = document.createElement("td");
            reimbType.innerHTML = reimb.reimb_type;
            row.appendChild(reimbType);


            // Check HTML and come back here
            document.getElementById("reimbBody").appendChild(row);
        }

        console.log(reimbArr);

        document.getElementById("approveReimbursement").addEventListener("click", approveReimb);
        document.getElementById("denyReimbursement").addEventListener("click", denyReimb);

        //Access data in array and print it out on card with a button to approve/deny


        //Handle logic on backend

        //Handle issues on server




    } else {
        alert("Reimbursement Update failed Failed! Refresh the page and try again");
    }

}
//End getting reimbursements by status

// Get all reimbursements
async function getAllReimbursements() {

    let response = await fetch(url + "reimbursements/get/all", { credentials: "include" });

    console.log(response);

    if (response.status === 200) {

        let data = await response.json();

        console.log(data); //DONE TESTING



        for (reimbursement of data) {
            let reimb = {
                "id": reimbursement.id,
                "reimb_amount": reimbursement.reimb_amount,
                "first": reimbursement.reimb_author.f_Name,
                "last": reimbursement.reimb_author.l_Name,
                "reimb_status_id": reimbursement.reimb_status_id,
                "reimb_type": reimbursement.reimb_type

            }

            reimbArr.push(reimb);

            let row = document.createElement("tr");

            let id = document.createElement("td");
            id.innerHTML = reimb.id;
            row.appendChild(id);

            let employee = document.createElement("td");
            employee.innerHTML = reimb.first + " " + reimb.last;
            row.appendChild(employee);

            let amount = document.createElement("td");
            amount.innerHTML = reimb.reimb_amount;
            row.appendChild(amount);

            let reimbStatus = document.createElement("td");
            reimbStatus.innerHTML = reimb.reimb_status_id;
            row.appendChild(reimbStatus);

            let reimbType = document.createElement("td");
            reimbType.innerHTML = reimb.reimb_type;
            row.appendChild(reimbType);


            // Check HTML and come back here
            document.getElementById("reimbBody").appendChild(row);
        }

    } else {
        alert("Reimbursement Update failed Failed! Refresh the page and try again");
    }

}
// End getting all reimbursements


// let updatedRBody;
let rId; 
let resolverID =  sessionStorage.getItem("userID");
let statusID;


function approveReimb() {
    let rId = prompt("Enter the value of the Reimbursement ID you want to APPROVE here: ");

    console.log(rId);

    if (rId != null) {
        statusID = 2;
        updateReimbursements(); 
    } else {
        alert("You must enter a Reimbursement ID");
    }
}

function denyReimb() {
    rId = prompt("Enter the value of the Reimbursement ID you want to DENY here: ");

    console.log(rId);

    if (rId != null) {
        statusID = 3;
        updateReimbursements(); 
    } else {
        alert("You must enter a Reimbursement ID");
    }
}

async function updateReimbursements() {

    let processed_reimb = {
        resolverID:resolverID, 
        statusID:statusID, 
    }

    console.log(processed_reimb); 



    let response1 = await fetch(url + "reimbursements/update/" + rId, { 
        method: "PUT",
        body: JSON.stringify(processed_reimb), 
        credentials: "include" });

    console.log(response1);

  

    if (response1.status === 200) {

       alert("successfully updated reimbursement");
    } else{
        console.log(response1.status);
        alert("Reimbursement could not be updated");
    }
}

