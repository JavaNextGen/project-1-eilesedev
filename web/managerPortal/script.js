const url = "http://localhost:3002/";

document.getElementById("getEmpReimbursements").addEventListener("click", getReimbursements);


async function getReimbursements() {

    let response = await fetch(url + "reimbursements/get/" + "Pending", { credentials: "include" });

    console.log(response);

    if (response.status === 200) {

        let data = await response.json();

        console.log(data);


        // <input type="button" value="Click me" onclick="alert('You clicked the button!')">
    //     element.setAttribute("type", type);
	// element.setAttribute("value", type);
	// element.setAttribute("name", type);


        // Buttons for approval/ denial
        let btnA = document.createElement("input");
        btnA.setAttribute("type", "button");
        btnA.setAttribute("value", "APPROVE")
        btnA.className = "btn btn-success";
        // btnA.innerHTML = "APPROVE";
        // btn.onclick = (function(entry) {return function() {chooseUser(entry);}})(entry);
        // td.appendChild(btn);

        let btnD = document.createElement("input");
        btnD.setAttribute("type", "button");
        btnD.setAttribute("value", "DENY")
        btnD.className = "btn btn-danger";
        // btnD.innerHTML = "DENY";

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

            document.getElementById("reimbBody").appendChild(row);
        }

        

         // reimbType.insertAdjacentElement('afterend', btnA);
            // btnA.insertAdjacentElement('afterend', btnD);

            let approval = row.insertCell(4); 
            approval.appendChild(btnA);

            let approvalBtn = document.createElement("td"); 
            approvalBtn.appendChild(btnA); 
            row.appendChild(approvalBtn); 
            // approvalBtn.insertAdjacentElement('afterbegin', btnA);
            // row.insertCell(approvalBtn); 

            let denyBtn = document.createElement("td"); 
            denyBtn.insertAdjacentElement('afterbegin', btnD);
            row.appendChild(denyBtn); 

    }


}

