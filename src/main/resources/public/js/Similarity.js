document.addEventListener("DOMContentLoaded", function () {
    updateFilters();
    document.getElementById("view-by").addEventListener("change", updateFilters);
    document.getElementById("apply-filters").addEventListener("click", function () {
        if (handleErrors() == true) {
            applyFilters();
        }
    });
});

function updateFilters() {
    var startingYear = 0;
    var endingYear = 0;
    var viewByValue = "";
    var countryName = "";
    var cityName = "";
    var stateName = "";
    var simCategory = "";
    var numResults = 0;
    var input = "";

    viewByValue = document.getElementById("view-by").value;
    var additionalFiltersCountries = document.getElementById("additional-filters-countries");
    var additionalFiltersCities = document.getElementById("additional-filters-cities");
    var additionalFiltersStates = document.getElementById("additional-filters-states");

    additionalFiltersCountries.style.display = "none";
    additionalFiltersCities.style.display = "none";
    additionalFiltersStates.style.display = "none";

    if (viewByValue === "Countries") {
        additionalFiltersCountries.style.display = "flex";
        var countryNameElement = document.getElementById("country-name");
        input = startingYear + ", " + endingYear + ", " + viewByValue + ", " + countryName + ", " + cityName + ", " + stateName + ", " + simCategory + ", " + numResults;
        fetch("../html/Similarity.html", {
            method: "POST",
            body: input
        })
            .then((response) => response.text())
            .then((output) => {
                var outputArray = output.split("\n");
                for (let i = countryNameElement.options.length - 1; i >= 0; i--) {
                    countryNameElement.remove(i);
                }
                for (let i = 1; i < outputArray.length - 1; i++) {
                    var option = document.createElement("option");
                    option.innerHTML = outputArray[i];
                    countryNameElement.add(option);
                }
            })
            .catch((error) => {
                console.error("Error: " + error);
            });
    }

    if (viewByValue === "Cities") {
        additionalFiltersCities.style.display = "flex";
        var countryNameElement = document.getElementById("country-name-cities")
        input = startingYear + ", " + endingYear + ", " + viewByValue + ", " + countryName + ", " + cityName + ", " + stateName + ", " + simCategory + ", " + numResults;
        fetch("../html/Similarity.html", {
            method: "POST",
            body: input
        })
            .then((response) => response.text())
            .then((output) => {
                var outputArray = output.split("\n");
                for (let i = countryNameElement.options.length - 1; i >= 0; i--) {
                    countryNameElement.remove(i);
                }
                for (let i = 1; i < outputArray.length - 1; i++) {
                    var option = document.createElement("option");
                    option.innerHTML = outputArray[i];
                    countryNameElement.add(option);
                }
                updateCities();
            })
            .catch((error) => {
                console.error("Error: " + error);
            });
        document.getElementById("country-name-cities").addEventListener("change", updateCities);
        function updateCities() {
            countryName = countryNameElement.value;
            var cityNameElement = document.getElementById("city-name");
            input = startingYear + ", " + endingYear + ", " + viewByValue + ", " + countryName + ", " + cityName + ", " + stateName + ", " + simCategory + ", " + numResults;
            fetch("../html/Similarity.html", {
                method: "POST",
                body: input
            })
                .then((response) => response.text())
                .then((output) => {
                    var outputArray = output.split("\n");
                    for (let i = cityNameElement.options.length - 1; i >= 0; i--) {
                        cityNameElement.remove(i);
                    }
                    for (let i = 1; i < outputArray.length - 1; i++) {
                        var option = document.createElement("option");
                        option.innerHTML = outputArray[i];
                        cityNameElement.add(option);
                    }
                })
                .catch((error) => {
                    console.error("Error: " + error);
                });
        }
    }

    if (viewByValue === "States") {
        additionalFiltersStates.style.display = "flex";
        var countryNameElement = document.getElementById("country-name-states")
        input = startingYear + ", " + endingYear + ", " + viewByValue + ", " + countryName + ", " + cityName + ", " + stateName + ", " + simCategory + ", " + numResults;
        fetch("../html/Similarity.html", {
            method: "POST",
            body: input
        })
            .then((response) => response.text())
            .then((output) => {
                var outputArray = output.split("\n");
                for (let i = countryNameElement.options.length - 1; i >= 0; i--) {
                    countryNameElement.remove(i);
                }
                for (let i = 1; i < outputArray.length - 1; i++) {
                    var option = document.createElement("option");
                    option.innerHTML = outputArray[i];
                    countryNameElement.add(option);
                }
                updateStates();
            })
            .catch((error) => {
                console.error("Error: " + error);
            });
        document.getElementById("country-name-states").addEventListener("change", updateStates);
        function updateStates() {
            countryName = countryNameElement.value;
            var stateNameElement = document.getElementById("state-name");
            input = startingYear + ", " + endingYear + ", " + viewByValue + ", " + countryName + ", " + cityName + ", " + stateName + ", " + simCategory + ", " + numResults;
            fetch("../html/Similarity.html", {
                method: "POST",
                body: input
            })
                .then((response) => response.text())
                .then((output) => {
                    var outputArray = output.split("\n");
                    for (let i = stateNameElement.options.length - 1; i >= 0; i--) {
                        stateNameElement.remove(i);
                    }
                    for (let i = 1; i < outputArray.length - 1; i++) {
                        var option = document.createElement("option");
                        option.innerHTML = outputArray[i];
                        stateNameElement.add(option);
                    }
                })
                .catch((error) => {
                    console.error("Error: " + error);
                });
        }
    }
}

function handleErrors() {
    const startingYearElement = document.getElementById("starting-year");
    const timePeriodElement = document.getElementById("time-period");
    const numberResultsElement = document.getElementById("number-results");

    var startingYearCheck = true;
    var timePeriodCheck = false;
    var error = true;

    if (startingYearElement.style.borderColor = "red") startingYearElement.style.borderColor = "";
    if (startingYearElement.nextSibling) startingYearElement.nextSibling.remove();

    if (timePeriodElement.style.borderColor = "red") timePeriodElement.style.borderColor = "";
    if (timePeriodElement.nextSibling) timePeriodElement.nextSibling.remove();

    if (numberResultsElement.style.borderColor = "red") numberResultsElement.style.borderColor = "";
    if (numberResultsElement.nextSibling) numberResultsElement.nextSibling.remove();

    if (startingYearElement.value === "") {
        startingYearElement.style.borderColor = "red";
        const errorMessage = document.createElement("p");
        errorMessage.innerHTML = "Please enter the starting year";
        errorMessage.style.color = "red";
        startingYearElement.after(errorMessage);
        error = false;
    }
    else if (!Number.isInteger(Number(startingYearElement.value))) {
        startingYearElement.style.borderColor = "red";
        const errorMessage = document.createElement("p");
        errorMessage.innerHTML = "Invalid starting year";
        errorMessage.style.color = "red";
        startingYearElement.after(errorMessage);
        error = false;
    }
    else if (startingYearElement.value < 1750 || startingYearElement.value > 2013) {
        startingYearElement.style.borderColor = "red";
        const errorMessage = document.createElement("p");
        errorMessage.innerHTML = "Starting year must be from 1750 to 2013";
        errorMessage.style.color = "red";
        startingYearElement.after(errorMessage);
        error = false;
    }
    else startingYearCheck = true;

    if (timePeriodElement.value === "") {
        timePeriodElement.style.borderColor = "red";
        const errorMessage = document.createElement("p");
        errorMessage.innerHTML = "Please enter the time period";
        errorMessage.style.color = "red";
        timePeriodElement.after(errorMessage);
        error = false;
    }
    else if (!Number.isInteger(Number(timePeriodElement.value))) {
        timePeriodElement.style.borderColor = "red";
        const errorMessage = document.createElement("p");
        errorMessage.innerHTML = "Invalid time period";
        errorMessage.style.color = "red";
        timePeriodElement.after(errorMessage);
        error = false;
    }
    else if (timePeriodElement.value < 0) {
        timePeriodElement.style.borderColor = "red";
        const errorMessage = document.createElement("p");
        errorMessage.innerHTML = "The time period can not be negative";
        errorMessage.style.color = "red";
        timePeriodElement.after(errorMessage);
        error = false;
    }
    else timePeriodCheck = true;

    if (startingYearCheck == true && timePeriodCheck == true) {
        if (Number(startingYearElement.value) + Number(timePeriodElement.value) > 2013) {
            timePeriodElement.style.borderColor = "red";
            const errorMessage = document.createElement("p");
            errorMessage.innerHTML = "The starting year plus the time period is greater than 2013";
            errorMessage.style.color = "red";
            timePeriodElement.after(errorMessage);
            error = false;
        }
    }

    if (numberResultsElement.value === "") {
        numberResultsElement.style.borderColor = "red";
        const errorMessage = document.createElement("p");
        errorMessage.innerHTML = "Please enter the number of results";
        errorMessage.style.color = "red";
        numberResultsElement.after(errorMessage);
        error = false;
    }
    else if (!Number.isInteger(Number(numberResultsElement.value))) {
        numberResultsElement.style.borderColor = "red";
        const errorMessage = document.createElement("p");
        errorMessage.innerHTML = "Invalid number of results";
        errorMessage.style.color = "red";
        numberResultsElement.after(errorMessage);
        error = false;
    }
    else if (numberResultsElement.value <= 0) {
        numberResultsElement.style.borderColor = "red";
        const errorMessage = document.createElement("p");
        errorMessage.innerHTML = "The number of results must be greater than 0";
        errorMessage.style.color = "red";
        numberResultsElement.after(errorMessage);
        error = false;
    }
    return error;
}

function applyFilters() {
    var startingYear = 0;
    var endingYear = 0;
    var viewByValue = "";
    var countryName = "";
    var cityName = "";
    var stateName = "";
    var simCategory = "";
    var numResults = 0;
    var input = "";
    startingYear = document.getElementById("starting-year").value;
    endingYear = document.getElementById("time-period").value;
    viewByValue = document.getElementById("view-by").value;
    if (viewByValue === "Countries") {
        countryName = document.getElementById("country-name").value;
        simCategory = document.getElementById("similarity-category").value;
    }
    if (viewByValue === "Cities") {
        countryName = document.getElementById("country-name-cities").value;
        cityName = document.getElementById("city-name").value
    }
    if (viewByValue === "States") {
        countryName = document.getElementById("country-name-states").value;
        stateName = document.getElementById("state-name").value
    }
    numResults = document.getElementById("number-results").value;

    input = startingYear + ", " + endingYear + ", " + viewByValue + ", " + countryName + ", " + cityName + ", " + stateName + ", " + simCategory + ", " + numResults;
    fetch("../html/Similarity.html", {
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