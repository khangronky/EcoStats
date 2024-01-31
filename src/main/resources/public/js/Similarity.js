document.addEventListener('DOMContentLoaded', function () {
    updateFilters();
    document.getElementById('view-by').addEventListener('change', updateFilters);
    document.getElementById('apply-filters').addEventListener('click', function () {
        if (handleErrors() == true) alert('Filters applied successfully');
    });
    populateCountryDropdown();
});

function updateFilters() {
    var viewBySelection = document.getElementById('view-by').value;
    var dynamicFilterContainer = document.getElementById('dynamic-filter-container');

    if (viewBySelection === 'Countries') dynamicFilterContainer.innerHTML = `
    <div class="flex-1">
        <label for="country-name" class="block text-gray-700 text-sm mb-2">Country name</label>
        <select id="country-name" name="country-name" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
            <option>Option 1</option>
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

    if (viewBySelection === 'Cities') dynamicFilterContainer.innerHTML = `
    <div class="flex-1">
        <label for="country-name-cities" class="block text-gray-700 text-sm mb-2">Country name to view cities</label>
        <select id="country-name-cities" name="country-name-cities" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
            <option>Option 1</option>
        </select>
    </div>
    <div class="flex-1">
        <label for="city-name" class="block text-gray-700 text-sm mb-2">City name</label>
        <select id="city-name" name="city-name" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
            <option>Option 1</option>
        </select>
    </div>
    `;

    if (viewBySelection === 'States') dynamicFilterContainer.innerHTML = `
    <div class="flex-1">
        <label for="country-name-states" class="block text-gray-700 text-sm mb-2">Country name to view states</label>
        <select id="country-name-states" name="country-name-states" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
            <option>Option 1</option>
        </select>
    </div>
    <div class="flex-1">
        <label for="state-name" class="block text-gray-700 text-sm mb-2">State name</label>
        <select id="state-name" name="state-name" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
            <option>Option 1</option>
        </select>
    </div>
    `;
}

function handleErrors() {
    const startingYearElement = document.getElementById('starting-year');
    const timePeriodElement = document.getElementById('time-period');

    var startingYearCheck = true;
    var timePeriodCheck = false;
    var error = true;

    if (startingYearElement.style.borderColor = 'red') startingYearElement.style.borderColor = '';
    if (startingYearElement.nextSibling) startingYearElement.nextSibling.remove();

    if (timePeriodElement.style.borderColor = 'red') timePeriodElement.style.borderColor = '';
    if (timePeriodElement.nextSibling) timePeriodElement.nextSibling.remove();

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
    return error;
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

function applyFilters() {
    var viewBySelection = document.getElementById('view-by').value;
    // Construct other input values as needed...

    var requestData = {
        viewBy: viewBySelection,
        // Include other input values here
    };

    fetch('http://localhost:7000/api/similarity', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestData),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        updateTable(data);
    })
    .catch(error => {
        console.error('Error:', error);
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