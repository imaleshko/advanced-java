const add = () => {
    const box = document.createElement("div");
    box.innerHTML = `
        <hr/>
        Розмірність:
        <input type="number" name="r" value="3" min="1" max="15" oninput="this.nextElementSibling.value = Number(this.value) + 1; grid(this.parentElement)"> x
        <input type="number" name="c" value="4" min="2" max="16" oninput="this.previousElementSibling.value = Number(this.value) -1; grid(this.parentElement)">
        <div></div>
    `;
    document.getElementById("list").appendChild(box);
    grid(box);
}

const grid = (box) => {
    const [rInput, cInput] = box.querySelectorAll("input");
    const r = Number(rInput.value);
    const c = Number(cInput.value);
    const g = box.querySelector("div");
    if (r < 1 || c < 2 || r > 15 || c > 16) {
        g.innerHTML = "";
        return;
    }

    let html = "<table><tr>";
    for (let j = 0; j < c; j++) {
        const label = (c === r + 1 && j === c - 1) ? "b" : `x${j + 1}`;
        html += `<th>${label}</th>`;
    }
    html += "</tr>";

    for (let i = 0; i < r; i++) {
        html += "<tr>";
        for (let j = 0; j < c; j++) {
            html += '<td><input type="number" step="any" name="cell" required></td>';
        }
        html += "</tr>";
    }
    html += "</table>";
    g.innerHTML = html;
}

add();
