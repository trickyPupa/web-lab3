function createError(message) {
    console.log(message);
    const error = document.getElementById("text-error");
    error.textContent = message;
}


// валидация и отправка
const checkX = (value) => {
    return new Promise((resolve, reject) => {
        if (isNaN(value) || (-5) > value || value > 5) {
            reject("значение x некорректно");
            console.log(value);
        } else {
            resolve();
        }
    });
}

const checkY = (value) => {
    return new Promise((resolve, reject) => {
        if (isNaN(value) || (-5) > value || value > 5) {
            reject("значение y некорректно");
            console.log(value);
        } else {
            resolve();
        }
    });
}

const checkR = (value) => {
    return new Promise((resolve, reject) => {
        if (isNaN(value) || (1) > value || value > 5) {
            reject("значение r некорректно");
            console.log(value);
        } else {
            resolve();
        }
    });
}

function validate(x, y, r) {
    return Promise.all([
        checkX(x),
        checkY(y),
        checkR(r)
    ]);
}

function submitForm() {
    // event.preventDefault();
    const x = parseFloat(document.querySelector(".x"));
    const y = document.querySelector('.y');
    const r = document.querySelector('.r');

    console.log(x, y, r);

    onSliderMove();
    // createError("");

    // return validate(x, y, r)
    //     .then(() => true)
    //     .catch((e) => {
    //         createError(e);
    //         return false;
    //     });
}

function validateAndSend(x, y, r) {
    validate(x, y, r)
        .then(() => {
            sendData(x, y, r);
            setTimeout(() => updatePointsFromTable(), 100);
        }).catch((error) => {
        createError(error);
    });
}

function sendData(x, y, r) {
    document.getElementById('hidden-form:x-hidden').value = x;
    document.getElementById('hidden-form:y-hidden').value = y;
    document.getElementById('hidden-form:r-hidden').value = r;

    document.getElementById('hidden-form:hidden-submit-button').click();

    console.log("click sended")
}

function byClick(event, canvas) {
    const sliderInstance = ice.ace.instance('inputs-form:slider');
    const r = parseFloat(sliderInstance.getValue());

    const rect = canvas.getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;
    const R = STEP * r;

    const canvasX = x - canvas.width / 2;
    const canvasY = canvas.height / 2 - y;

    const xValue = (canvasX / R * r).toFixed(4);
    const yValue = (canvasY / R * r).toFixed(4);

    console.log(`Данные нажатия: (${xValue}, ${yValue}, ${r})`);

    validateAndSend(xValue, yValue, r);
}

function onSliderMove(event) {
    const sliderInstance = ice.ace.instance('inputs-form:slider');
    const r = sliderInstance.getValue();
    document.getElementById('inputs-form:r-text').value = r;

    createError("");

    graphInit();
    updatePointsFromTable();
}

const updatePointsFromTable = () => {
    const table = document.getElementById("inputs-form:records-table");
    const rows = table.querySelectorAll(".records-table tbody tr");
    const points = [];

    rows.forEach(row => {
        const cells = row.querySelectorAll(".records-table td");
        const x = parseFloat(cells[0].textContent.trim());
        const y = parseFloat(cells[1].textContent.trim());
        const r = parseFloat(cells[2].textContent.trim());
        const status = cells[3].textContent.trim().toLowerCase() === 'true';
        points.push({ x, y, r, status });
    });

    sessionStorage.setItem('points', JSON.stringify(points));
    console.log("Updated points from table:", points);

    const sliderInstance = ice.ace.instance('inputs-form:slider');
    const currentR = parseFloat(sliderInstance.getValue());
    drawPoints(currentR);

    return points;
};

function drawPoints(r) {
    const pointsData = sessionStorage.getItem('points');
    if (!pointsData) {
        console.log("No points to redraw");
        return;
    }

    const points = JSON.parse(pointsData);
    const canvas = document.getElementById('canvas');
    const ctx = canvas.getContext('2d');

    points.forEach(point => {
        const x = canvas.width / 2 + (point.x * STEP / point.r) * r;
        const y = canvas.height / 2 - (point.y * STEP / point.r) * r;

        ctx.fillStyle = point.status ? '#5cdc5f' : '#e44242';
        ctx.beginPath();
        ctx.arc(x, y, 5, 0, 2 * Math.PI);
        ctx.fill();
        ctx.closePath();
    });

    console.log(`Redrawn points for R=${r}`);
}

function clearButtonClick() {
    setTimeout(() => {
        graphInit();
        updatePointsFromTable();
    }, 100);
}