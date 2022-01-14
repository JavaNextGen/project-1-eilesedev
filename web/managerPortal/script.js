const url = "http://localhost:3002/";

document.getElementById("getEmpReimbursements").addEventListener("click", getReimbursements);

document.getElementById("userName").innerHTML = sessionStorage.getItem("f_name");

// let reimbursement; 
const reimbArr = [];


async function getReimbursements() {

    let response = await fetch(url + "reimbursements/get/" + "Pending", { credentials: "include" });

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


// let updatedRBody;
let rId; 
let resolver =  sessionStorage.getItem("userID");
let reimb_status_id;


function approveReimb() {
    let rId = prompt("Enter the value of the Reimbursement ID you want to APPROVE here: ");

    console.log(rId);

    if (rId != null) {
        reimb_status_id = "APPROVED"
        updateReimbursements(); 
    } else {
        alert("You must enter a Reimbursement ID");
    }
}

function denyReimb() {
    rId = prompt("Enter the value of the Reimbursement ID you want to DENY here: ");

    console.log(rId);

    if (rId != null) {
        reimb_status_id = "DENIED"
        updateReimbursements(); 
    } else {
        alert("You must enter a Reimbursement ID");
    }
}

async function updateReimbursements() {

    let response1 = await fetch(url + "reimbursements/update/" + rId + "/" + resolver + "/" +   reimb_status_id, { 
        method: "PUT",
        credentials: "include" });

    console.log(response1);

  

    if (response1.status === 200) {

       alert("successfully updated reimbursement");
    } else{
        console.log(response1.status);
        alert("Reimbursement could not be updated");
    }
}