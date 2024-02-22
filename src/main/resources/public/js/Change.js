document.addEventListener("DOMContentLoaded", function () {
    updateFilters();
    document.getElementById("view-by").addEventListener("change", updateFilters);
    document.getElementById("apply-filters").addEventListener("click", function () {
        if (handleErrors() === true) {
            applyFilters();
        }
    });
});

function updateFilters() {
    var startingYear = 0;
    var endingYear = 0;
    var viewByValue = "";
    var countryName = "";
    var sortCategory = "";
    var sortOrder = "";
    var input = "";

    viewByValue = document.getElementById("view-by").value;
    var additionalFilterCountries = document.getElementById("additional-filter-countries");
    var additionalFilterCities = document.getElementById("additional-filter-cities");
    var additionalFilterStates = document.getElementById("additional-filter-states");

    additionalFilterCountries.style.display = "none";
    additionalFilterCities.style.display = "none";
    additionalFilterStates.style.display = "none";

    if (viewByValue === "Countries") {
        additionalFilterCountries.style.display = "block";
    }
    if (viewByValue === "Cities") {
        additionalFilterCities.style.display = "block";
        var countryNameElement = document.getElementById("country-name-cities");
        input = startingYear + ", " + endingYear + ", " + viewByValue + ", " + countryName + ", " + sortCategory + ", " + sortOrder;
        fetch("../html/Change.html", {
            method: "POST",
            body: input
        })
            .then((response) => response.text())
            .then((output) => {
                var rows = output.split("\n");
                for (let i = countryNameElement.options.length - 1; i >= 0; i--) {
                    countryNameElement.remove(i);
                }
                for (let i = 1; i < rows.length - 1; i++) {
                    var option = document.createElement("option");
                    option.text = rows[i];
                    countryNameElement.add(option);
                }
            })
            .catch((error) => {
                console.error("Error: " + error);
            });
    }
    if (viewByValue === "States") {
        additionalFilterStates.style.display = "block";
        var countryNameElement = document.getElementById("country-name-states");
        input = startingYear + ", " + endingYear + ", " + viewByValue + ", " + countryName + ", " + sortCategory + ", " + sortOrder;
        fetch("../html/Change.html", {
            method: "POST",
            body: input
        })
            .then((response) => response.text())
            .then((output) => {
                var rows = output.split("\n");
                for (let i = countryNameElement.options.length - 1; i >= 0; i--) {
                    countryNameElement.remove(i);
                }
                for (let i = 1; i < rows.length - 1; i++) {
                    var option = document.createElement("option");
                    option.innerHTML = rows[i];
                    countryNameElement.add(option);
                }
            })
            .catch((error) => {
                console.error("Error: " + error);
            });
    }
}

function handleErrors() {
    var startingYearCheck = false;
    var endingYearCheck = false;
    var error = true;

    var startingYearElement = document.getElementById("starting-year");
    var endingYearElement = document.getElementById("ending-year");

    if (startingYearElement.style.borderColor === "red") startingYearElement.style.borderColor = "";
    if (startingYearElement.nextSibling) startingYearElement.nextSibling.remove();

    if (endingYearElement.style.borderColor === "red") endingYearElement.style.borderColor = "";
    if (endingYearElement.nextSibling) endingYearElement.nextSibling.remove();

    if (startingYearElement.value === "") {
        startingYearElement.style.borderColor = "red";
        var errorMessage = document.createElement("p");
        errorMessage.innerHTML = "Please enter the starting year";
        errorMessage.style.color = "red";
        startingYearElement.after(errorMessage);
        error = false;
    }
    else if (!Number.isInteger(Number(startingYearElement.value))) {
        startingYearElement.style.borderColor = "red";
        var errorMessage = document.createElement("p");
        errorMessage.innerHTML = "Invalid starting year";
        errorMessage.style.color = "red";
        startingYearElement.after(errorMessage);
        error = false;
    }
    else if (startingYearElement.value < 1750 || startingYearElement.value > 2013) {
        startingYearElement.style.borderColor = "red";
        var errorMessage = document.createElement("p");
        errorMessage.innerHTML = "The starting year must be from 1750 to 2013";
        errorMessage.style.color = "red";
        startingYearElement.after(errorMessage);
        error = false;
    }
    else startingYearCheck = true;

    if (endingYearElement.value === "") {
        endingYearElement.style.borderColor = "red";
        var errorMessage = document.createElement("p");
        errorMessage.innerHTML = "Please enter the ending year";
        errorMessage.style.color = "red";
        endingYearElement.after(errorMessage);
        error = false;
    }
    else if (!Number.isInteger(Number(endingYearElement.value))) {
        endingYearElement.style.borderColor = "red";
        var errorMessage = document.createElement("p");
        errorMessage.innerHTML = "Invalid ending year";
        errorMessage.style.color = "red";
        endingYearElement.after(errorMessage);
        error = false;
    }
    else if (endingYearElement.value < 1750 || endingYearElement.value > 2013) {
        endingYearElement.style.borderColor = "red";
        var errorMessage = document.createElement("p");
        errorMessage.innerHTML = "The ending year must be from 1750 to 2013";
        errorMessage.style.color = "red";
        endingYearElement.after(errorMessage);
        error = false;
    }
    else endingYearCheck = true;

    if (startingYearCheck === true && endingYearCheck === true) {
        if (startingYearElement.value > endingYearElement.value) {
            startingYearElement.style.borderColor = "red";
            endingYearElement.style.borderColor = "red";
            var errorMessage = document.createElement("p");
            errorMessage.innerHTML = "The starting year can not be greater than the ending year";
            errorMessage.style.color = "red";
            endingYearElement.after(errorMessage);
            error = false;
        }
    }
    return error;
}

function applyFilters() {
    var startingYear = 0;
    var endingYear = 0;
    var viewByValue = "";
    var countryName = "";
    var sortCategory = "";
    var sortOrder = "";
    var input = "";
    
    startingYear = document.getElementById("starting-year").value;
    endingYear = document.getElementById("ending-year").value;
    viewByValue = document.getElementById("view-by").value;
    if (viewByValue === "Countries") {
        sortCategory = document.getElementById("country-sort-category").value;
        sortOrder = document.getElementById("country-sort-order").value;
    }
    if (viewByValue === "Cities") countryName = document.getElementById("country-name-cities").value;
    if (viewByValue === "States") countryName = document.getElementById("country-name-states").value;

    input = startingYear + ", " + endingYear + ", " + viewByValue + ", " + countryName + ", " + sortCategory + ", " + sortOrder;
    fetch("../html/Change.html", {
        method: "POST",
        body: input
    })
        .then((response) => response.text())
        .then((output) => {
            var table = document.getElementById("table");
            if (table) table.remove();

            var table = document.createElement("table");
            table.id = "table";
            table.className = "min-w-full leading-normal";

            var rows = output.split("\n");
            for (let i = 0; i < rows.length - 1; i++) {
                var tr = document.createElement("tr");
                var columns = rows[i].split(",");
                if (i === 0) {
                    for (let j = 0; j < columns.length; j++) {
                        var th = document.createElement("th");
                        th.className = "px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 tracking-wider";
                        th.innerHTML = columns[j];
                        tr.appendChild(th);
                    }
                    table.appendChild(tr);
                }
                else {
                    for (let j = 0; j < columns.length; j++) {
                        var td = document.createElement("td");
                        td.className = "px-5 py-5 border-b border-gray-200 bg-white text-sm";
                        var p = document.createElement("p");
                        p.className = "text-gray-900 whitespace-no-wrap";
                        p.innerHTML = columns[j];
                        td.appendChild(p);
                        tr.appendChild(td);
                    }
                    table.appendChild(tr);
                }
            }
            document.getElementById("content").appendChild(table);
        })
        .catch((error) => {
            console.error("Error: " + error);
        });
}