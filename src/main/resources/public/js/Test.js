const dataToSend = {
    name: "John Doe",
    age: 25,
};

fetch("http://localhost:7000/html/Test.html", {
    method: "POST",
    body: JSON.stringify(dataToSend)
})
.then((response) => response.json())
.then((data) => {
    console.log(Object.keys(data[1])[1]);
})
.catch((error) => {
    console.error("Error: " + error);
});