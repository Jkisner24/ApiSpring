document.addEventListener("DOMContentLoaded", function () {

  fetch("http://localhost:8080/api/v1/expense")
    .then(response => response.json())
    .then(data => {
      const expenseListContainer = document.getElementById("expenseList");

      const table = document.createElement("table");
      table.classList.add("table", "table-responsive");
      table.innerHTML = `
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Date</th>
            <th>Category</th>
            <th>Owner</th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      `;

      const tbody = table.querySelector("tbody");

      data.forEach(expense => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${expense.id}</td>
          <td>${expense.name}</td>
          <td>${expense.price}</td>
          <td>${expense.expenseDate}</td>
          <td>${expense.category.name}</td>
          <td>${expense.owner.name}</td>
        `;
        tbody.appendChild(row);
      });

      expenseListContainer.innerHTML = '';
      expenseListContainer.appendChild(table);
    })
    .catch(error => {
      console.error("Somenthing went wrong in Backend:", error);
    });
});

