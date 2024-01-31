const dataToSend = `
abc, def, ghi
jkl, mno, pqr
`;

fetch("http://localhost:7001/html/Test.html", {
    method: "POST",
    body: dataToSend
})
    .then((response) => response.text())
    .then((data) => {
        const a = data.substring(0, 3);
        console.log(a);
    })
    .catch((error) => {
        console.error("Error: " + error);
    });