document.addEventListener("DOMContentLoaded", function () {

  fetch("http://localhost:8080/api/v1/owner")
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
        `;
        tbody.appendChild(row);
      });

      ownerListContainer.appendChild(table);
    })
    .catch(error => console.error("Something went wrong with Owner in DB:", error));
});
