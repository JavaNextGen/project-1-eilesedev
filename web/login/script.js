const url = "http://localhost:3002/"; 

function redirectReg(){
    location.replace("file:///C:/Users/jenea/OneDrive/02-Revature%20Paid%20Internship/Projects-FOR%20SUBMISSION/Project%201/project-1-eilesedev/web/register/index.html  ");
}





document.getElementById("login").addEventListener("click", loginUser);

async function loginUser() {

    let unm = document.getElementById("username").value; 
    let pswd = document.getElementById("password").value; 


    //Session storage stores username and password
    sessionStorage.setItem("u", unm);
    sessionStorage.setItem("p", pswd);

    let logged_in_user = {
        username:unm, 
        password:pswd
    }

    console.log(logged_in_user); 

    let response = await fetch(url + "auth/login", {
        method: "POST", 
        body: JSON.stringify(logged_in_user), 
        credentials: "include"
    });

    

    console.log(response.status); 

    if(response.status === 202){
        sessionStorage.setItem("userObj", response.credentials);
        document.getElementById("login_row").innerText = `Welcome back! press the button below to enter your VocFinTech ERS System!`; 
        let enter = document.getElementById("enter"); 
        enter.hidden = !enter.hidden; 
        document.getElementById("auth_buttons").remove(); 
    } else{
        document.getElementById("login_row").innerText = "Login Failed! Refresh the page and try again"
    }


}