console.log("test.main: OK")

//All-Users
let tableUsers = []
const allUsers = document.querySelector("#adminTab")

fetch("http://localhost:8080/api/users").then(
    res => {
        res.json().then(
            data => {
                if (data.length > 0) {
                    data.forEach((user) => {
                        tableUsers.push(user)
                    })
                    console.log(tableUsers)
                    showUsers(tableUsers)
                }
            }
        )
    })

function showUsers(event) {
    let temp = ""
    console.log(event)

    event.forEach((user) => {
        let userRole = "";
        for (let i = 0; i < user.roles.length; i++) {
            userRole += " " + user.roles[i].name.substring(5);
        }
        temp += `<tr data-id="${user.id}" id="tr-user-${user.id}">
                            <td class="mainTabId" id="mainTabId${user.id}">${user.id}</td>
                            <td class="mainTabFirstname" id="mainTabFirstname-${user.id}">${user.firstname}</td>
                            <td class="mainTabLastname" id="mainTabLastname-${user.id}">${user.lastname}</td>
                            <td class="mainTabAge" id="mainTabAge-${user.id}">${user.age}</td>
                            <td class="mainTabEmail" id="mainTabEmail-${user.id}">${user.username}</td>
                            <td class="mainTabRole" id="mainTabRole-${user.id}">${userRole}</td>
                            <td>
                            <button type="button" class="btn btn-info eBtn" 
                            data-toggle="modal" data-target='#editModal' id="edit-btn-js">Edit
                            </button>
                            </td>
                            <td>
                            <button type="button" class="btn btn-danger " 
                            data-toggle="modal" data-target='#deleteModal' id="delete-btn-js">Delete
                            </button>
                            </td></tr>`

    })
    document.getElementById('allUsersData').innerHTML = temp;
}


//NewUser
document.getElementById('addNewUser').addEventListener('submit', submitFormNewUser)

function submitFormNewUser(event) {
    event.preventDefault();
    // let rol = document.querySelector('#newRoles').getElementsByTagName('option')

    let newUser = {
        firstname: $("#newFirstName").val(),
        lastname: $("#newLastName").val(),
        age: $("#newAge").val(),
        username: $("#newEmail").val(),
        password: $("#newPassword").val(),
        roles: $("#newRoles").val()
    }

    fetch("http://localhost:8080/api/users", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(newUser)
    })
        .then(
        res => {
            res.json().then(
                newUser => {
                    tableUsers.push(newUser)
                    showUsers(tableUsers)
                })
        })
    $('#myTab li:first-child a').tab('show')

}

//Edit-Delete-Modal-View
const modalEdit = document.getElementById('editModal')
const modalDelete = document.getElementById('deleteModal')
let selectUserId


allUsers.addEventListener('click', ev => {
    ev.preventDefault()

    let editButtonClick = ev.target.id === 'edit-btn-js'
    let deleteButtonClick = ev.target.id === 'delete-btn-js'

    const parent = ev.target.parentElement.parentElement

    selectUserId = parent.querySelector('.mainTabId').textContent

    let firstname = parent.querySelector('.mainTabFirstname').textContent
    let lastname = parent.querySelector('.mainTabLastname').textContent
    let age = parent.querySelector('.mainTabAge').textContent
    let email = parent.querySelector('.mainTabEmail').textContent
    let role = parent.querySelector('.mainTabRole').textContent


    if (deleteButtonClick) {
        console.log('ModalDelete: delete id=' + selectUserId)

        document.getElementById("delId").value = selectUserId
        document.getElementById("delFirstName").value = firstname
        document.getElementById("delLastName").value = lastname
        document.getElementById("delAge").value = age
        document.getElementById("delEmail").value = email
        document.getElementById("delRoles").value = role
    }

    if(editButtonClick){
        console.log('ModalEdit: edit id=' + selectUserId)

        document.getElementById("editId").value = selectUserId
        document.getElementById("editFirstName").value = firstname
        document.getElementById("editLastName").value = lastname
        document.getElementById("editAge").value = age
        document.getElementById("editEmail").value = email
        document.getElementById("editRoles").value = role
    }
})

modalDelete.addEventListener('click', runModalDelete)

function runModalDelete(e) {
    console.log("runModalDelete...")
    e.preventDefault()
    let modalDeleteButton = e.target.id === 'modal-delete-button'

    if (modalDeleteButton) {
        fetch(`http://localhost:8080/api/users/${selectUserId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }

        })
            .then(() => {
                document.getElementById('tr-user-' + selectUserId).innerHTML = ""
            })
    }
}

modalEdit.addEventListener('click',runModalEdit)

function runModalEdit(e){
    console.log("runModalEdit")
    e.preventDefault()
    let modalEditButton = e.target.id === 'modal-edit-button'

    if(modalEditButton){
        let editUser = {
            id:$("#editId").val(),
            firstname: $("#editFirstName").val(),
            lastname: $("#editLastName").val(),
            age: $("#editAge").val(),
            username: $("#editEmail").val(),
            password: $("#editPassword").val(),
            roles: $("#editRoles").val()
        }
        fetch(`http://localhost:8080/api/users/${selectUserId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            body: JSON.stringify(editUser)
        })
            .then(
                res => {
                    res.json().then(
                        editUser => {
                            tableUsers.push(editUser)
                            showUsers(tableUsers)
                        })
                })

    }

}