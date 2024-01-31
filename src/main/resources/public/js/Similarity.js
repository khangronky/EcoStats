document.addEventListener('DOMContentLoaded', function () {
    updateFilters();
    document.getElementById('view-by').addEventListener('change', updateFilters);
    document.getElementById('apply-filters').addEventListener('click', function () {
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
    var dataToSend = "";

    viewByValue = document.getElementById('view-by').value;
    var dynamicFilterContainer = document.getElementById('dynamic-filter-container');

    if (viewByValue === 'Countries') {
        dynamicFilterContainer.innerHTML = `
        <div class="flex-1">
            <label for="country-name" class="block text-gray-700 text-sm mb-2">Country name</label>
            <select id="country-name" name="country-name" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
            </select>
        </div>
        <div class="flex-1">
            <label for="similarity-category" class="block text-gray-700 text-sm mb-2">Similarity category</label>
            <select id="similarity-category" name="similarity-category" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                <option value="Temperature">Temperature</option>
                <option value="Population">Population</option>
                <option value="Both">Both</option>
            </select>
        </div>
        `;
        simCategory = document.getElementById('similarity-category').value;
        dataToSend = startingYear + ',' + endingYear + ',' + viewByValue + ',' + countryName + ',' + cityName + ',' + stateName + ',' + simCategory + ',' + numResults;
        fetch("http://localhost:7001/html/Similarity.html", {
            method: "POST",
            body: dataToSend
        })
            .then((response) => response.text())
            .then((data) => {
                var dataArray = data.split('\n');
                var countryNameElement = document.getElementById('country-name');
                for (let i = countryNameElement.options.length - 1; i >= 0; i--) {
                    countryNameElement.remove(i);
                }
                for (let i = 1; i < dataArray.length - 1; i++) {
                    var option = document.createElement('option');
                    option.text = dataArray[i];
                    countryNameElement.add(option);
                }
            })
            .catch((error) => {
                console.error("Error: " + error);
            });
    }

    if (viewByValue === 'Cities') {
        dynamicFilterContainer.innerHTML = `
        <div class="flex-1">
            <label for="country-name-cities" class="block text-gray-700 text-sm mb-2">Country name to view cities</label>
            <select id="country-name-cities" name="country-name-cities" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
            </select>
        </div>
        <div class="flex-1">
            <label for="city-name" class="block text-gray-700 text-sm mb-2">City name</label>
            <select id="city-name" name="city-name" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">  
            </select>
        </div>
        `;
        simCategory = "Temperature";
        dataToSend = startingYear + ',' + endingYear + ',' + viewByValue + ',' + countryName + ',' + cityName + ',' + stateName + ',' + simCategory + ',' + numResults;
        fetch("http://localhost:7001/html/Similarity.html", {
            method: "POST",
            body: dataToSend
        })
            .then((response) => response.text())
            .then((data) => {
                var dataArray = data.split('\n');
                var countryNameElement = document.getElementById('country-name-cities');
                for (let i = countryNameElement.options.length - 1; i >= 0; i--) {
                    countryNameElement.remove(i);
                }
                for (let i = 1; i < dataArray.length - 1; i++) {
                    var option = document.createElement('option');
                    option.text = dataArray[i];
                    countryNameElement.add(option);
                }
            })
            .catch((error) => {
                console.error("Error: " + error);
            });
        
    }

    if (viewByValue === 'States') {
        dynamicFilterContainer.innerHTML = `
        <div class="flex-1">
            <label for="country-name-states" class="block text-gray-700 text-sm mb-2">Country name to view states</label>
            <select id="country-name-states" name="country-name-states" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
            </select>
        </div>
        <div class="flex-1">
            <label for="state-name" class="block text-gray-700 text-sm mb-2">State name</label>
            <select id="state-name" name="state-name" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
            </select>
        </div>
        `;
        simCategory = "Temperature";
        dataToSend = startingYear + ',' + endingYear + ',' + viewByValue + ',' + countryName + ',' + cityName + ',' + stateName + ',' + simCategory + ',' + numResults;
        fetch("http://localhost:7001/html/Similarity.html", {
            method: "POST",
            body: dataToSend
        })
            .then((response) => response.text())
            .then((data) => {
                var dataArray = data.split('\n');
                var countryNameElement = document.getElementById('country-name-states');
                for (let i = countryNameElement.options.length - 1; i >= 0; i--) {
                    countryNameElement.remove(i);
                }
                for (let i = 1; i < dataArray.length - 1; i++) {
                    var option = document.createElement('option');
                    option.innerHTML = dataArray[i];
                    countryNameElement.add(option);
                }
            })
            .catch((error) => {
                console.error("Error: " + error);
            });
    }
}

function handleErrors() {
    const startingYearElement = document.getElementById('starting-year');
    const timePeriodElement = document.getElementById('time-period');
    const numberResultsElement = document.getElementById('number-results');

    var startingYearCheck = true;
    var timePeriodCheck = false;
    var error = true;

    if (startingYearElement.style.borderColor = 'red') startingYearElement.style.borderColor = '';
    if (startingYearElement.nextSibling) startingYearElement.nextSibling.remove();

    if (timePeriodElement.style.borderColor = 'red') timePeriodElement.style.borderColor = '';
    if (timePeriodElement.nextSibling) timePeriodElement.nextSibling.remove();

    if (numberResultsElement.style.borderColor = 'red') numberResultsElement.style.borderColor = '';
    if (numberResultsElement.nextSibling) numberResultsElement.nextSibling.remove();

    if (startingYearElement.value === '') {
        startingYearElement.style.borderColor = 'red';
        const errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Please enter the starting year';
        errorMessage.style.color = 'red';
        startingYearElement.after(errorMessage);
        error = false;
    }
    else if (!Number.isInteger(Number(startingYearElement.value))) {
        startingYearElement.style.borderColor = 'red';
        const errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Invalid starting year';
        errorMessage.style.color = 'red';
        startingYearElement.after(errorMessage);
        error = false;
    }
    else if (startingYearElement.value < 1750 || startingYearElement.value > 2013) {
        startingYearElement.style.borderColor = 'red';
        const errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Starting year must be from 1750 to 2013';
        errorMessage.style.color = 'red';
        startingYearElement.after(errorMessage);
        error = false;
    }
    else startingYearCheck = true;

    if (timePeriodElement.value === '') {
        timePeriodElement.style.borderColor = 'red';
        const errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Please enter the time period';
        errorMessage.style.color = 'red';
        timePeriodElement.after(errorMessage);
        error = false;
    }
    else if (!Number.isInteger(Number(timePeriodElement.value))) {
        timePeriodElement.style.borderColor = 'red';
        const errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Invalid time period';
        errorMessage.style.color = 'red';
        timePeriodElement.after(errorMessage);
        error = false;
    }
    else if (timePeriodElement.value < 0) {
        timePeriodElement.style.borderColor = 'red';
        const errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'The time period can not be negative';
        errorMessage.style.color = 'red';
        timePeriodElement.after(errorMessage);
        error = false;
    }
    else timePeriodCheck = true;

    if (startingYearCheck == true && timePeriodCheck == true) {
        if (Number(startingYearElement.value) + Number(timePeriodElement.value) > 2013) {
            timePeriodElement.style.borderColor = 'red';
            const errorMessage = document.createElement('p');
            errorMessage.innerHTML = 'The starting year plus the time period is greater than 2013';
            errorMessage.style.color = 'red';
            timePeriodElement.after(errorMessage);
            error = false;
        }
    }

    if (numberResultsElement.value === '') {
        numberResultsElement.style.borderColor = 'red';
        const errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Please enter the number of results';
        errorMessage.style.color = 'red';
        numberResultsElement.after(errorMessage);
        error = false;
    }
    else if (!Number.isInteger(Number(numberResultsElement.value))) {
        numberResultsElement.style.borderColor = 'red';
        const errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Invalid number of results';
        errorMessage.style.color = 'red';
        numberResultsElement.after(errorMessage);
        error = false;
    }
    else if (numberResultsElement.value <= 0) {
        numberResultsElement.style.borderColor = 'red';
        const errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'The number of results must be greater than 0';
        errorMessage.style.color = 'red';
        numberResultsElement.after(errorMessage);
        error = false;
    }
    return error;
}

function applyFilters() {
    
}

function populateCountryDropdown() {
    fetch('http://localhost:7001/html/Similarity.html') // Replace with your actual endpoint for fetching countries
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        updateCountryDropdown(data);
    })
    .catch(error => {
        console.error('Error fetching countries:', error);
    });
}


// This function updates the dropdown with the received list of countries
function updateCountryDropdown(countries) {
    var countrySelect = document.getElementById('country-name');
    countrySelect.innerHTML = '';  // Clear any existing options

    countries.forEach(country => {
        var option = document.createElement('option');
        option.value = country.code;  // Use the appropriate property for country code
        option.textContent = country.name;  // Use the appropriate property for country name
        countrySelect.appendChild(option);
    });
}

function updateTable(data) {
    var table = document.getElementById('similarity-table');
    var tbody = table.querySelector('tbody');
    tbody.innerHTML = ''; // Clear existing table rows

    // Assuming 'data' is an array of objects, where each object represents a row
    data.forEach(item => {
        var tr = document.createElement('tr');
        Object.values(item).forEach(val => {
            var td = document.createElement('td');
            td.textContent = val;
            tr.appendChild(td);
        });
        tbody.appendChild(tr);
    });
}