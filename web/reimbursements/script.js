const url = "http://localhost:3002/"; 


document.getElementById("submit_reimb").addEventListener("click", submitReimb);

async function submitReimb() {

    let reimb_type = document.getElementById("reimb_type_select").value; 

    let reimb_amount = document.getElementById("amount").value; 
    let status_id = 1; 

    let submitted_reimb = {
        reimb_type_id:reimb_type, 
        reimb_amount:reimb_amount, 
        reimb_status_id:status_id
    }

    console.log(submitted_reimb); 

    let response = await fetch(url + "reimbursements/create", {
        method: "POST", 
        body: JSON.stringify(submitted_reimb), 
        //include header here?
        credentials: "include"
    });

    console.log(response.status); 

    if(response.status === 201){
        let textnode = document.createTextNode("Reimbursement Successfully Added!");
        document.getElementById("reimb_container").appendChild(textnode);  
    } else{
        let textnode = document.createTextNode("Reimbursement Could Not be added. Refresh and try again");
        document.getElementById("reimb_container").appendChild(textnode);  
    }


}