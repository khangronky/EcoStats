const dataToSend = [
    1990,
    2000,
    "Hello",
    "World"
]
dataToSend.push("New Element");

fetch("http://localhost:7000/html/Test.html", {
    method: "POST",
    body: JSON.stringify(dataToSend)
})
.then((response) => response.json())
.then((data) => {
    console.log(data);
})
.catch((error) => {
    console.error("Error: " + error);
});