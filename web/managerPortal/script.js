const url = "http://localhost:3002/"; 

document.getElementById("getEmpReimbursements").addEventListener("click", getReimbursements);

  //Session storage stores username and password
  let u = sessionStorage.getItem("u");
  let p = sessionStorage.getItem("p");

  let persisted_user = {
    username:u, 
    password:p
}

console.log(persisted_user);


async function getReimbursements() {

    //Trying to persist user login
    let reimb_login = await fetch(url + "auth/login", {
        method: "POST", 
        body: JSON.stringify(logged_in_user), 
        credentials: "include"
    });

    // if()

    //End - trying to persist user login


    let response = await fetch(url + "reimbursements/get/" + "Pending", {credentials: "include"});

    console.log(response);

    if(response.status === 200){

        let data = await response.json(); 

        console.log(data);

        for(let reimbursement of data){

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


