document.addEventListener('DOMContentLoaded', function () {
    updateFilters();
    document.getElementById('view-by').addEventListener('change', updateFilters);
    document.getElementById('apply-filters').addEventListener('click', function () {
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
    var dataToSend = "";

    viewByValue = document.getElementById('view-by').value;
    var additionalFilterCountries = document.getElementById('additional-filter-countries');
    var additionalFilterCities = document.getElementById('additional-filter-cities');
    var additionalFilterStates = document.getElementById('additional-filter-states');

    additionalFilterCountries.style.display = 'none';
    additionalFilterCities.style.display = 'none';
    additionalFilterStates.style.display = 'none';

    if (viewByValue === 'Countries') {
        additionalFilterCountries.style.display = 'block';
    }
    if (viewByValue === 'Cities') {
        additionalFilterCities.style.display = 'block';
        dataToSend = startingYear + ',' + endingYear + ',' + viewByValue + ',' + countryName + ',' + sortCategory + ',' + sortOrder;
        fetch("http://localhost:7001/html/Change.html", {
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
        additionalFilterStates.style.display = 'block';
        dataToSend = startingYear + ',' + endingYear + ',' + viewByValue + ',' + countryName + ',' + sortCategory + ',' + sortOrder;
        fetch("http://localhost:7001/html/Change.html", {
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
    var startingYearCheck = false;
    var endingYearCheck = false;
    var error = true;

    var startingYearElement = document.getElementById('starting-year');
    var endingYearElement = document.getElementById('ending-year');

    if (startingYearElement.style.borderColor === 'red') startingYearElement.style.borderColor = '';
    if (startingYearElement.nextSibling) startingYearElement.nextSibling.remove();

    if (endingYearElement.style.borderColor === 'red') endingYearElement.style.borderColor = '';
    if (endingYearElement.nextSibling) endingYearElement.nextSibling.remove();

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
        errorMessage.innerHTML = 'The starting year must be from 1750 to 2013';
        errorMessage.style.color = 'red';
        startingYearElement.after(errorMessage);
        error = false;
    }
    else startingYearCheck = true;

    if (endingYearElement.value === '') {
        endingYearElement.style.borderColor = 'red';
        var errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Please enter the ending year';
        errorMessage.style.color = 'red';
        endingYearElement.after(errorMessage);
        error = false;
    }
    else if (!Number.isInteger(Number(endingYearElement.value))) {
        endingYearElement.style.borderColor = 'red';
        var errorMessage = document.createElement('p');
        errorMessage.innerHTML = 'Invalid ending year';
        errorMessage.style.color = 'red';
        endingYearElement.after(errorMessage);
        error = false;
    }
    else if (endingYearElement.value < 1750 || endingYearElement.value > 2013) {
        endingYearElement.style.borderColor = 'red';
        var errorMessage = document.createElement('p');
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
            var errorMessage = document.createElement('p');
            errorMessage.innerHTML = 'The starting year can not be greater than the ending year';
            errorMessage.style.color = 'red';
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
    var dataToSend = "";

    startingYear = document.getElementById('starting-year').value;
    endingYear = document.getElementById('ending-year').value;
    viewByValue = document.getElementById('view-by').value;
    if (viewByValue === 'Countries') {
        sortCategory = document.getElementById('country-sort-category').value;
        sortOrder = document.getElementById('country-sort-order').value;
    }
    if (viewByValue === 'Cities') countryName = document.getElementById('country-name-cities').value;
    if (viewByValue === 'States') countryName = document.getElementById('country-name-states').value;
    dataToSend = startingYear + ',' + endingYear + ',' + viewByValue + ',' + countryName + ',' + sortCategory + ',' + sortOrder;
    alert(dataToSend);
}