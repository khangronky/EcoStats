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
        <label for="country-name-to-view-cities" class="block text-gray-700 text-sm mb-2">Country name to view cities</label>
        <select id="country-name-to-view-cities" name="country-name-to-view-cities" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
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
        <label for="country-name-to-view-states" class="block text-gray-700 text-sm mb-2">Country name to view states</label>
        <select id="country-name-to-view-states" name="country-name-to-view-states" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
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
    var startingYearCheck = true;
    var timePeriodCheck = false;
    var error = true;

    var startingYearElement = document.getElementById('starting-year');
    var timePeriodElement = document.getElementById('time-period');
    var numberResultsElement = document.getElementById('number-results');

    if (startingYearElement.style.borderColor === 'red') startingYearElement.style.borderColor = '';
    if (startingYearElement.nextSibling) startingYearElement.nextSibling.remove();

    if (timePeriodElement.style.borderColor === 'red') timePeriodElement.style.borderColor = '';
    if (timePeriodElement.nextSibling) timePeriodElement.nextSibling.remove();

    if (numberResultsElement.style.borderColor === 'red') numberResultsElement.style.borderColor = '';
    if (numberResultsElement.nextSibling) numberResultsElement.nextSibling.remove();

    if (startingYearElement.value === '') {
        startingYearElement.style.borderColor = 'red';
        var errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Please enter the starting year';
        errorMessage.style.color = 'red';
        startingYearElement.after(errorMessage);
        error = false;
    }
    else if (!Number.isInteger(Number(startingYearElement.value))) {
        startingYearElement.style.borderColor = 'red';
        var errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Invalid starting year';
        errorMessage.style.color = 'red';
        startingYearElement.after(errorMessage);
        error = false;
    }
    else if (startingYearElement.value < 1750 || startingYearElement.value > 2013) {
        startingYearElement.style.borderColor = 'red';
        var errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Starting year must be from 1750 to 2013';
        errorMessage.style.color = 'red';
        startingYearElement.after(errorMessage);
        error = false;
    }
    else startingYearCheck = true;

    if (timePeriodElement.value === '') {
        timePeriodElement.style.borderColor = 'red';
        var errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Please enter the time period';
        errorMessage.style.color = 'red';
        timePeriodElement.after(errorMessage);
        error = false;
    }
    else if (!Number.isInteger(Number(timePeriodElement.value))) {
        timePeriodElement.style.borderColor = 'red';
        var errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Invalid time period';
        errorMessage.style.color = 'red';
        timePeriodElement.after(errorMessage);
        error = false;
    }
    else if (timePeriodElement.value < 0) {
        timePeriodElement.style.borderColor = 'red';
        var errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'The time period can not be negative';
        errorMessage.style.color = 'red';
        timePeriodElement.after(errorMessage);
        error = false;
    }
    else timePeriodCheck = true;

    if (startingYearCheck == true && timePeriodCheck == true) {
        if (Number(startingYearElement.value) + Number(timePeriodElement.value) > 2013) {
            timePeriodElement.style.borderColor = 'red';
            var errorMessage = document.createElement('p');
            errorMessage.innerHTML = 'The starting year plus the time period is greater than 2013';
            errorMessage.style.color = 'red';
            timePeriodElement.after(errorMessage);
            error = false;
        }
    }

    if (numberResultsElement.value === '') {
        numberResultsElement.style.borderColor = 'red';
        var errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Please enter the number of results';
        errorMessage.style.color = 'red';
        numberResultsElement.after(errorMessage);
        error = false;
    }
    else if (!Number.isInteger(Number(numberResultsElement.value))) {
        numberResultsElement.style.borderColor = 'red';
        var errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Invalid number of results';
        errorMessage.style.color = 'red';
        numberResultsElement.after(errorMessage);
        error = false;
    }
    else if (numberResultsElement.value < 1) {
        numberResultsElement.style.borderColor = 'red';
        var errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'The number of results can not be less than 1';
        errorMessage.style.color = 'red';
        numberResultsElement.after(errorMessage);
        error = false;
    }
    return error;
}

function applyFilters() {
    
}