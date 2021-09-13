//Show One User
const oneUserElement = document.getElementById("infoAboutUserTable")
let oneUserTemp = ""
fetch("http://localhost:8080/api/users/im")
    .then(res => res.json())
    .then(data => userTable(data))

const userTable = (user) => {
    console.log("show auth")
    let userRole = "";
    console.log(user.roles)

    user.roles.forEach((u) => {
        console.log(u.name)
        userRole = u.name.substring(5)
        oneUserTemp += `<tr>
                            <td>${user.id}</td>
                            <td>${user.firstname}</td>
                            <td>${user.lastname}</td>
                            <td>${user.age}</td>
                            <td>${user.username}</td>
                            <td>${userRole}</td>
                           </tr>`
    })
    oneUserElement.innerHTML = oneUserTemp;
}

