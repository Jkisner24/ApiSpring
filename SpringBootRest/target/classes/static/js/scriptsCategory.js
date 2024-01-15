document.addEventListener("DOMContentLoaded", function () {

  fetch("https://apispring-13nc.onrender.com/api/v1/expenseCategory")
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
          <td>
          <button class="btn modifyExpenseCategoryBtn" data-expenseCategory-id="${expenseCategory.id}">
            <i class="bi bi-pencil-square"></i>
            Modify
          </button>
          <button class="btn deleteExpenseCategoryBtn" data-expenseCategory-id="${expenseCategory.id}">
            <i class="bi bi-trash"></i>
            delete
          </button>
          </td>
        `;
        tbody.appendChild(row);
        const deleteButton = row.querySelector(".deleteExpenseCategoryBtn");
        deleteButton.addEventListener("click", handleDeleteExpenseCategory);
        });

      expenseCategoryListContainer.appendChild(table);
    })
    .catch(error => console.error("Something went wrong with Category in DB:", error));


    const addExpenseCategoryForm = document.getElementById("addExpenseCategoryForm");

    addExpenseCategoryForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const name = document.getElementById("expenseCategoryName").value;

        const body = JSON.stringify({
            name: name
        });

        fetch("https://apispring-13nc.onrender.com/api/v1/expenseCategory/save", {
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

    function handleDeleteExpenseCategory(event) {
        const expenseCategoryId = event.target.getAttribute("data-expenseCategory-id");
        console.log(expenseCategoryId)

        fetch(`https://apispring-13nc.onrender.com/api/v1/expenseCategory/delete/${expenseCategoryId}`, {
            method: "DELETE"
        })
            .then(response => {
                if (response.ok) {
                    fetchExpensesCategory();
                } else {
                    console.error("Error with delete category of expense:", response.statusText);
                }
            })
            .catch(error => {
                console.error("Error with delete category of expense:", error);
            });
    }

    function fetchExpensesCategory() {
        fetch("https://apispring-13nc.onrender.com/api/v1/expenseCategory")
            .then(response => response.json())
            .then(data => {
            })
            .catch(error => {
                console.error("Something went wrong in Backend:", error);
            });
    }

});
