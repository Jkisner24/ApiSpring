document.addEventListener("DOMContentLoaded", function () {
  fetch("http://localhost:8080/api/v1/expenseCategory")
    .then(response => response.json())
    .then(data => {
      const expenseCategoryListContainer = document.getElementById("expenseCategoryList");
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

      data.forEach(expenseCategory => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${expenseCategory.id}</td>
          <td>${expenseCategory.name}</td>
        `;
        tbody.appendChild(row);
      });

      expenseCategoryListContainer.appendChild(table);
    })
    .catch(error => console.error("Something went wrong with Category in DB:", error));
});
