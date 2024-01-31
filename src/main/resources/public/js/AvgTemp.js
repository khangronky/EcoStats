document.addEventListener('DOMContentLoaded', function () {
    updateFilters();
    document.getElementById('view-by').addEventListener('change', updateFilters);
    document.getElementById('apply-filters').addEventListener('click', function () {
        if (handleErrors() === true) alert('Filters applied successfully');
    });
});

function updateFilters() {
    var startingYears = [0, 0, 0, 0, 0];
    var period = 0;
    var viewByValue = "";
    var countryName = "";
    var sortCategory = "";
    var sortOrder = "";
    var dataToSend = "";

    viewByValue = document.getElementById('view-by').value;
    var additionalFiltersCountries = document.getElementById('additional-filters-countries');
    var additionalFiltersCities = document.getElementById('additional-filters-cities');
    var additionalFiltersStates = document.getElementById('additional-filters-states');

    additionalFiltersCountries.style.display = 'none';
    additionalFiltersCities.style.display = 'none';
    additionalFiltersStates.style.display = 'none';

    if (viewByValue === 'Countries') {
        additionalFiltersCountries.style.display = 'block';
    }

    if (viewByValue === 'Cities') {
        additionalFiltersCities.style.display = 'block';
        dataToSend = startingYears[0] + ',' + startingYears[1] + ',' + startingYears[2] + ',' + startingYears[3] + ',' + startingYears[4] + ',' +
        period + ',' + viewByValue + ',' + countryName + ',' + sortCategory + ',' + sortOrder;
        fetch("http://localhost:7001/html/AvgTemp.html", {
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
        additionalFiltersStates.style.display = 'block';
        dataToSend = startingYears[0] + ',' + startingYears[1] + ',' + startingYears[2] + ',' + startingYears[3] + ',' + startingYears[4] + ',' +
        period + ',' + viewByValue + ',' + countryName + ',' + sortCategory + ',' + sortOrder;
        fetch("http://localhost:7001/html/AvgTemp.html", {
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
                    option.text = dataArray[i];
                    countryNameElement.add(option);
                }
            })
            .catch((error) => {
                console.error("Error: " + error);
            });
    }
}

function handleErrors() {
    const startingYearsElement = document.getElementById('starting-years');
    const timePeriodElement = document.getElementById('time-period');

    var startingYearsCheck = true;
    var timePeriodCheck = false;
    var error = true;

    if (startingYearsElement.style.borderColor = 'red') startingYearsElement.style.borderColor = '';
    if (startingYearsElement.nextSibling) startingYearsElement.nextSibling.remove();

    if (timePeriodElement.style.borderColor = 'red') timePeriodElement.style.borderColor = '';
    if (timePeriodElement.nextSibling) timePeriodElement.nextSibling.remove();

    const startingYears = startingYearsElement.value.split(',').map(year => year.trim());
    for (let startingYear of startingYears) {
        if (startingYears.length > 5 || startingYear === '' || !Number.isInteger(Number(startingYear)) || startingYear < 1750 || startingYear > 2013) {
            startingYearsElement.style.borderColor = 'red';
            const errorMessage = document.createElement('p');
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

    if (startingYearsCheck === true && timePeriodCheck === true) {
        for (let startingYear of startingYears) {
            if (Number(startingYear) + Number(timePeriodElement.value) > 2013) {
                timePeriodElement.style.borderColor = 'red';
                const errorMessage = document.createElement('p');
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
    var viewByValue = document.getElementById('view-by').value;

    if (viewByValue === 'Countries') {
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

    if (viewByValue === 'Cities') {
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

    if (viewByValue === 'States') {
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
    var startingYears = [0, 0, 0, 0, 0];
    var period = 0;
    var viewByValue = "";
    var countryName = "";
    var sortCategory = "";
    var sortOrder = "";
    var dataToSend = "";

    var startingYearsArray = document.getElementById('starting-years').value.split(',').map(year => year.trim());
    for (let i = 0; i < startingYearsArray.length; i++) {
        startingYears[i] = startingYearsArray[i];
    }
    period = document.getElementById('time-period').value;
    viewByValue = document.getElementById('view-by').value;

    if (viewByValue === 'Countries') {
        sortCategory = document.getElementById('country-sort-category').value;
        sortOrder = document.getElementById('country-sort-order').value;
    }
    if (viewByValue === 'Cities') {
        countryName = document.getElementById('country-name-cities').value;
        sortCategory = document.getElementById('city-sort-category').value;
        sortOrder = document.getElementById('city-sort-order').value;
    }
    if (viewByValue === 'States') {
        countryName = document.getElementById('country-name-states').value;
        sortCategory = document.getElementById('state-sort-category').value;
        sortOrder = document.getElementById('state-sort-order').value;
    }
    dataToSend = startingYears[0] + ',' + startingYears[1] + ',' + startingYears[2] + ',' + startingYears[3] + ',' + startingYears[4] + ',' +
    period + ',' + viewByValue + ',' + countryName + ',' + sortCategory + ',' + sortOrder;
    alert(dataToSend);
}
