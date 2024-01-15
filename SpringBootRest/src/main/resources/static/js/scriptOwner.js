document.addEventListener("DOMContentLoaded", function () {

  fetch("https://apispring-13nc.onrender.com/api/v1/owner")
    .then(response => response.json())
    .then(data => {
      const ownerListContainer = document.getElementById("OwnerList");
      const table = document.createElement("table");
      table.classList.add("table", "table-responsive");
      table.innerHTML = `
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      `;

      const tbody = table.querySelector("tbody");

      data.forEach(owner => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${owner.id}</td>
          <td>${owner.name}</td>
          <button class="btn modifyOwnerBtn" data-owner-id="${owner.id}">
            <i class="bi bi-pencil-square"></i>
            Modify
          </button>
          <button class="btn deleteOwnerBtn" data-owner-id="${owner.id}">
            <i class="bi bi-trash"></i>
            delete
          </button>
        `;
        tbody.appendChild(row);
        const deleteButton = row.querySelector(".deleteOwnerBtn");
        deleteButton.addEventListener("click", handleDeleteOwner);

      });

      ownerListContainer.appendChild(table);
    })
    .catch(error => console.error("Something went wrong with Owner in DB:", error));

    const addOwnerForm = document.getElementById("addOwnerForm");

    addOwnerForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const name = document.getElementById("ownerName").value;

        const body = JSON.stringify({
            name: name,
        });

        fetch("https://apispring-13nc.onrender.com/api/v1/owner/save", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: body,
        })
        .then(response => response.json())
        .then(data => {
               console.log("Response of server:", data);
        })
        .catch(error => {
               console.error("Something went wrong in Backend:", error);
        });
    });
    function handleDeleteOwner(event) {
        const OwnerId = event.target.getAttribute("data-owner-id");
        console.log(OwnerId)

        fetch(`https://apispring-13nc.onrender.com/api/v1/owner/delete/${OwnerId}`, {
            method: "DELETE"
        })
            .then(response => {
                if (response.ok) {
                    fetchOwner();
                } else {
                    console.error("Error with delete category of expense:", response.statusText);
                }
            })
            .catch(error => {
                console.error("Error with delete category of expense:", error);
            });
    }

    function fetchOwner() {
        fetch("https://apispring-13nc.onrender.com/api/v1/owner")
            .then(response => response.json())
            .then(data => {
            })
            .catch(error => {
                console.error("Something went wrong in Backend:", error);
            });
    }

});
