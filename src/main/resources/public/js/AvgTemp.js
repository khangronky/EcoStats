document.addEventListener('DOMContentLoaded', function () {
    updateFilters();
    document.getElementById('view-by').addEventListener('change', updateFilters);
    document.getElementById('apply-filters').addEventListener('click', function () {
        if (handleErrors() === true) {
            changeSortList();
            applyFilters();
        }
    });
});

function updateFilters() {
    var viewBySelection = document.getElementById('view-by').value;
    var additionalFiltersCountries = document.getElementById('additional-filters-countries');
    var additionalFiltersCities = document.getElementById('additional-filters-cities');
    var additionalFiltersStates = document.getElementById('additional-filters-states');

    additionalFiltersCountries.style.display = 'none';
    additionalFiltersCities.style.display = 'none';
    additionalFiltersStates.style.display = 'none';

    if (viewBySelection === 'Countries') additionalFiltersCountries.style.display = 'block';
    if (viewBySelection === 'Cities') additionalFiltersCities.style.display = 'block';
    if (viewBySelection === 'States') additionalFiltersStates.style.display = 'block';
}

function handleErrors() {
    var startingYearsCheck = true;
    var timePeriodCheck = false;
    var error = true;

    var startingYearsElement = document.getElementById('starting-years');
    var timePeriodElement = document.getElementById('time-period');

    if (startingYearsElement.style.borderColor === 'red') startingYearsElement.style.borderColor = '';
    if (startingYearsElement.nextSibling) startingYearsElement.nextSibling.remove();

    if (timePeriodElement.style.borderColor === 'red') timePeriodElement.style.borderColor = '';
    if (timePeriodElement.nextSibling) timePeriodElement.nextSibling.remove();

    var startingYears = startingYearsElement.value.split(',').map(year => year.trim());
    for (let startingYear of startingYears) {
        if (startingYears.length > 5 || startingYear === '' || !Number.isInteger(Number(startingYear)) || startingYear < 1750 || startingYear > 2013) {
            startingYearsElement.style.borderColor = 'red';
            var errorMessage = document.createElement('p');
            errorMessage.innerHTML = 'You should type 5 maximum valid starting years from 1750 to 2013 separated by commas';
            errorMessage.style.color = 'red';
            startingYearsElement.after(errorMessage);
            startingYearsCheck = false;
            error = false;
            break;
        }
    }

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

    if (startingYearsCheck === true && timePeriodCheck === true) {
        for (let startingYear of startingYears) {
            if (Number(startingYear) + Number(timePeriodElement.value) > 2013) {
                timePeriodElement.style.borderColor = 'red';
                var errorMessage = document.createElement('p');
                errorMessage.innerHTML = 'One of the starting years plus the time period is greater than 2013';
                errorMessage.style.color = 'red';
                timePeriodElement.after(errorMessage);
                error = false;
                break;
            }
        }
    }
    return error;
}

function changeSortList() {
    var startingYearsElement = document.getElementById('starting-years');
    var newStartingYears = startingYearsElement.value.split(',').map(year => year.trim());
    var viewBySelection = document.getElementById('view-by').value;

    if (viewBySelection === 'Countries') {
        var countryOldStartingYearsString = sessionStorage.getItem('countryStartingYears');
        var countrySortCategoryElement = document.getElementById('country-sort-category');
        if (countryOldStartingYearsString !== newStartingYears.toString()) {
            sessionStorage.setItem('countryStartingYears', newStartingYears.toString());
            for (let i = countrySortCategoryElement.options.length - 1; i > 0; i--) {
                countrySortCategoryElement.remove(i);
            }
            for (let startingYear of newStartingYears) {
                var option = document.createElement('option');
                option.innerHTML = startingYear;
                countrySortCategoryElement.appendChild(option);
            }
        }
    }

    if (viewBySelection === 'Cities') {
        var cityOldStartingYearsString = sessionStorage.getItem('cityStartingYears');
        var citySortCategoryElement = document.getElementById('city-sort-category');
        if (cityOldStartingYearsString !== newStartingYears.toString()) {
            sessionStorage.setItem('cityStartingYears', newStartingYears.toString());
            for (let i = citySortCategoryElement.options.length - 1; i > 0; i--) {
                citySortCategoryElement.remove(i);
            }
            for (let startingYear of newStartingYears) {
                var option = document.createElement('option');
                option.innerHTML = startingYear;
                citySortCategoryElement.appendChild(option);
            }
        }
    }

    if (viewBySelection === 'States') {
        var stateOldStartingYearsString = sessionStorage.getItem('stateStartingYears');
        var stateSortCategoryElement = document.getElementById('state-sort-category');
        if (stateOldStartingYearsString !== newStartingYears.toString()) {
            sessionStorage.setItem('stateStartingYears', newStartingYears.toString());
            for (let i = stateSortCategoryElement.options.length - 1; i > 0; i--) {
                stateSortCategoryElement.remove(i);
            }
            for (let startingYear of newStartingYears) {
                var option = document.createElement('option');
                option.innerHTML = startingYear;
                stateSortCategoryElement.appendChild(option);
            }
        }
    }
}

function applyFilters() {

}