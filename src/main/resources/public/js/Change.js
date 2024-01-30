document.addEventListener('DOMContentLoaded', function () {
    updateFilters();
    document.getElementById('view-by').addEventListener('change', updateFilters);
    document.getElementById('apply-filters').addEventListener('click', function () {
        if (handleErrors() === true) alert('Filters applied successfully');
    });
});

function updateFilters() {
    const viewByValue = document.getElementById('view-by').value;
    const additionalFilterCountries = document.getElementById('additional-filter-countries');
    const additionalFilterCities = document.getElementById('additional-filter-cities');
    const additionalFilterStates = document.getElementById('additional-filter-states');

    additionalFilterCountries.style.display = 'none';
    additionalFilterCities.style.display = 'none';
    additionalFilterStates.style.display = 'none';

    if (viewByValue === 'Countries') additionalFilterCountries.style.display = 'block';
    if (viewByValue === 'Cities') additionalFilterCities.style.display = 'block';
    if (viewByValue === 'States') additionalFilterStates.style.display = 'block'
}

function handleErrors() {
    const startingYearElement = document.getElementById('starting-year');
    const endingYearElement = document.getElementById('ending-year');

    var startingYearCheck = false;
    var endingYearCheck = false;
    var error = true;

    if (startingYearElement.style.borderColor = 'red') startingYearElement.style.borderColor = '';
    if (startingYearElement.nextSibling) startingYearElement.nextSibling.remove();

    if (endingYearElement.style.borderColor = 'red') endingYearElement.style.borderColor = '';
    if (endingYearElement.nextSibling) endingYearElement.nextSibling.remove();

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
        errorMessage.innerHTML = 'The starting year must be from 1750 to 2013';
        errorMessage.style.color = 'red';
        startingYearElement.after(errorMessage);
        error = false;
    }
    else startingYearCheck = true;

    if (endingYearElement.value === '') {
        endingYearElement.style.borderColor = 'red';
        const errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Please enter the ending year';
        errorMessage.style.color = 'red';
        endingYearElement.after(errorMessage);
        error = false;
    }
    else if (!Number.isInteger(Number(endingYearElement.value))) {
        endingYearElement.style.borderColor = 'red';
        const errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Invalid ending year';
        errorMessage.style.color = 'red';
        endingYearElement.after(errorMessage);
        error = false;
    }
    else if (endingYearElement.value < 1750 || endingYearElement.value > 2013) {
        endingYearElement.style.borderColor = 'red';
        const errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'The ending year must be from 1750 to 2013';
        errorMessage.style.color = 'red';
        endingYearElement.after(errorMessage);
        error = false;
    }
    else endingYearCheck = true;

    if (startingYearCheck === true && endingYearCheck === true) {
        if (startingYearElement.value > endingYearElement.value) {
            startingYearElement.style.borderColor = 'red';
            endingYearElement.style.borderColor = 'red';
            const errorMessage = document.createElement('p');
            errorMessage.innerHTML = 'The starting year can not be greater than the ending year';
            errorMessage.style.color = 'red';
            endingYearElement.after(errorMessage);
            error = false;
        }
    }
    return error;
}