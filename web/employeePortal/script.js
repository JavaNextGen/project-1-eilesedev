const url = "http://localhost:3001/";

if (sessionStorage.getItem("role") == "FINANCE_MANAGER") {
    window.location.replace("file:///C:/Users/jenea/OneDrive/02-Revature%20Paid%20Internship/Projects-FOR%20SUBMISSION/Project%201/project-1-eilesedev/web/managerPortal/index.html");
}

document.getElementById("getEmpReimbursements").addEventListener("click", getReimbursements);

let currentUser = sessionStorage.getItem("userID");

document.getElementById("userName").innerHTML = sessionStorage.getItem("f_name");

document.getElementById("create").addEventListener("click", createReimbursement);

// Begin function to create reimbursement
async function createReimbursement() {
    let typeID = document.getElementById("inputGroupSelect01").value;
    // console.log(statusID); //works
    let amount = document.getElementById("amount").value;
    // console.log(amount); //of type number

    let created_reimb = {
        statusID: 1,
        author: currentUser,
        amount: amount,
        typeID: typeID
    }

    console.log(created_reimb);

    let response = await fetch(url + "reimbursements/create", {
        method: "POST",
        body: JSON.stringify(created_reimb),
        credentials: "include"
    });

    console.log(response.status);

    if (response.status === 201) {
        alert("Reimbursement successfully created!");
    } else {
        alert("Creating a reimbursement failed. Try again!");
    }
}
// End function to create reimbursement

//Begin function to get reimbursments specific to the user
async function getReimbursements() {

    let response = await fetch(url + "reimbursements/get/author" + currentUser, { credentials: "include" });

    console.log(response);

    if (response.status === 200) {

        let data = await response.json();

        console.log(data);

        for (let reimbursement of data) {

            let row = document.createElement("tr");

            let id = document.createElement("td");
            id.innerHTML = reimbursement.id;
            row.appendChild(id);

            let amount = document.createElement("td");
            amount.innerHTML = reimbursement.reimb_amount;
            row.appendChild(amount);

            let reimbStatus = document.createElement("td");
            reimbStatus.innerHTML = reimbursement.reimb_status_id;
            row.appendChild(reimbStatus);

            let reimbType = document.createElement("td");
            reimbType.innerHTML = reimbursement.reimb_type;
            row.appendChild(reimbType);


            // Check HTML and come back here
            document.getElementById("reimbBody").appendChild(row);
        }

    }


}
//End function to get reimbursments specific to the user


