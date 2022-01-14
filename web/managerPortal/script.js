const url = "http://localhost:3002/";

document.getElementById("getEmpReimbursements").addEventListener("click", getReimbursements);

document.getElementById("userName").innerHTML = sessionStorage.getItem("f_name");

let reimbursement; 


async function getReimbursements() {

    let response = await fetch(url + "reimbursements/get/" + "Pending", { credentials: "include" });

    console.log(response);

    if (response.status === 200) {

        let data = await response.json();

        console.log(data);

        let idArr = new Array;




        for (reimbursement of data) {

            let row = document.createElement("tr");

            let id = document.createElement("td");
            id.innerHTML = reimbursement.id;
            idArr.push(reimbursement.id);
            row.appendChild(id);

            let name = document.createElement("td");
            name.innerHTML = reimbursement.reimb_author.f_Name + " " + reimbursement.reimb_author.l_Name;
            row.appendChild(name);

            let amount = document.createElement("td");
            amount.innerHTML = reimbursement.reimb_amount;
            row.appendChild(amount);

            let reimbStatus = document.createElement("td");
            reimbStatus.innerHTML = reimbursement.reimb_status_id;
            row.appendChild(reimbStatus);

            let reimbType = document.createElement("td");
            reimbType.innerHTML = reimbursement.reimb_type;
            row.appendChild(reimbType);

            row.addEventListener("click", updateReimbursements);

            document.getElementById("reimbBody").appendChild(row);
        }

        console.log(idArr);



        function updateReimbursements(data, idArr) {

            let rId = reimbursement.id; 
            console.log(rId); 

            let process = confirm("Press okay to confirm this request. To deny press cancel");

                    if (process == true) {
                        reimbStatus = "APPROVED";
                        console.log(reimbStatus);
                        return true;
                    } else {
                        reimbStatus = "DENIED";
                        console.log(reimbStatus);
                        return false;
                    }
            }

        }

    }










//then update table
// alert("this works!"); 
//on row click pull reimbursement ID

//update reimbursement status to whatever button is pressed (alert)

//change status in the table and reload it

//I can pass the User ID from session storage but that means that I have to find user by ID in controller to pass through to the DAO

async function updatedReimbursement() {



    console.log(updatedR);

    let response3 = await fetch(url + "reimbursements/update", {
        method: "PUT",
        body: JSON.stringify(updatedR),
        credentials: "include"
    });

    if (response3.status === 20) {

        document.getElementById("reimbBody").innerHTML = " ";

        //Refresh table
        for (let reimbursement of data) {

            let row = document.createElement("tr");

            let name = document.createElement("td");
            name.innerHTML = reimbursement.reimb_author.f_Name + " " + reimbursement.reimb_author.l_Name;
            row.appendChild(name);

            let amount = document.createElement("td");
            amount.innerHTML = reimbursement.reimb_amount;
            row.appendChild(amount);

            let reimbStatus = document.createElement("td");
            reimbStatus.innerHTML = reimbursement.reimb_status_id;
            row.appendChild(reimbStatus);

            let reimbType = document.createElement("td");
            reimbType.innerHTML = reimbursement.reimb_type;
            row.appendChild(reimbType);

            row.addEventListener("click", updateReimbursements);

            document.getElementById("reimbBody").appendChild(row);
        }

    }

}