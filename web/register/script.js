const url = "http://localhost:3002/"; 

document.getElementById("register").addEventListener("click", registerUser);

async function registerUser() {

    let fname = document.getElementById("fname").value; 
    let lname = document.getElementById("lname").value;

    let unm = document.getElementById("username").value; 
    let pswd = document.getElementById("password").value; 
    let email = document.getElementById("email").value; 

    let role; 

    if(document.getElementById("finance_manager").checked){
        // alert("finance manager checked!"); FOR TESTING FINANCE MANAGER CHECKED
        role = 2; 
    } else {
        // alert("user is an employee"); FOR TESTING FINANCE MANAGER NOT CHECKED
        role = 1; 
    }

    let registered_user = {
        username:unm, 
        password:pswd, 
        fName:fname, 
        lName:lname,
        email:email, 
        role:role 
    }

    console.log(registered_user); 

    let response = await fetch(url + "auth/register", {
        method: "POST", 
        body: JSON.stringify(registered_user), 
        credentials: "include"
    });

    console.log(response.status); 

    if(response.status === 201){
        document.getElementById("login_row").innerText = `Hey ${fname} press the button below to enter your new ERS System!`; 
        let enter = document.getElementById("enter"); 
        enter.hidden = !enter.hidden; 
    } else{
        document.getElementById("login_row").innerText = "Login Failed! Refresh the page and try again"
    }


}


