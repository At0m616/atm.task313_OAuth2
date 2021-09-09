//Show One User
console.log("test.userInfo: OK")
const oneUser = document.getElementById("infoAboutUserTable")
let temp = ""
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
        temp += `<tr>
                            <td>${user.id}</td>
                            <td>${user.firstname}</td>
                            <td>${user.lastname}</td>
                            <td>${user.age}</td>
                            <td>${user.username}</td>
                            <td>${userRole}</td>
                           </tr>`
    })
    oneUser.innerHTML = temp;
}