document.addEventListener("DOMContentLoaded", function () {

    fetch("https://apispring-13nc.onrender.com/api/v1/expense")
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
                    <td>
                        <button class="btn modifyExpenseBtn" data-expense-id="${expense.id}">
                            <i class="bi bi-pencil-square"></i>
                            Modify
                        </button>
                        <button class="btn deleteExpenseBtn" data-expense-id="${expense.id}">
                            <i class="bi bi-trash"></i>
                            delete
                        </button>
                    </td>
                `;
                tbody.appendChild(row);
                const deleteButtons = document.querySelectorAll(".deleteExpenseBtn");
                deleteButtons.forEach(button => {
                     button.addEventListener("click", handleDeleteExpense);
                });

            });

                expenseListContainer.appendChild(table);

        })
        .catch(error => console.error("Something went wrong with Category in DB:", error));

        const addExpenseForm = document.getElementById("addExpenseForm");

        addExpenseForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const name = document.getElementById("expenseName").value;
        const price = document.getElementById("expensePrice").value;
        const expenseDate = document.getElementById("expenseDate").value;
        const categoryId = document.getElementById("categoryId").value;
        const ownerId = document.getElementById("ownerId").value;

        const body = JSON.stringify({
            name: name,
            price: price,
            expenseDate: expenseDate,
            category: {
                id: categoryId,
            },
            owner: {
                id: ownerId
            }
        });
            fetch("https://apispring-13nc.onrender.com/api/v1/expense/save", {
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

    function handleDeleteExpense(event) {
        const expenseId = event.target.getAttribute("data-expense-id");
            console.log(expenseId)

        fetch(`https://apispring-13nc.onrender.com/api/v1/expense/delete/${expenseId}`, {
            method: "DELETE"
        })
            .then(response => {
                if (response.ok) {
                    fetchExpenses();
                } else {
                    console.error("Error with delete expense:", response.statusText);
                }
            })
            .catch(error => {
                console.error("Error with delete expense:", error);
            });
    }

    function fetchExpenses() {
        fetch("https://apispring-13nc.onrender.com/api/v1/expense")
            .then(response => response.json())
            .then(data => {
            })
            .catch(error => {
                console.error("Something went wrong in Backend:", error);
            });
    }
});

