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
        if (isNaN(value) || (1) > value || value > 4) {
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
    const x = document.querySelector(".x");
    const y = document.querySelector('.y');
    const r = document.querySelector('.r');

    console.log(x, y, r);
    createError("");

    return validate(x, y, r)
        .then(() => true)
        .catch((e) => {
            createError(e);
            return false;
        });
}

function validateAndSend(x, y, r) {
    validate(x, y, r)
        .then(() => {
            sendData(x, y, r);
        }).catch((error) => {
        createError(error);
    });
}

function sendData(x, y, r) {
    document.getElementById('inputs-form:x-hidden').value = x;
    document.getElementById('inputs-form:y-hidden').value = y;
    document.getElementById('inputs-form:submit-button').click();

    console.log("click sended")
}

// отрисовка
function drawDot(x, y, r, status) {
    const canvas = document.getElementById('canvas');
    const ctx = canvas.getContext('2d');
    const formula = (coord, radius) => (200 + (4 * coord * 40) / radius);
    ctx.beginPath();
    ctx.fillStyle = status ? '#52cf41' : '#EE204D';
    ctx.moveTo(200, 200);
    ctx.arc(formula(x, r), formula(-y, r), 5, 0, 2 * Math.PI);
    ctx.fill();
    ctx.closePath();
    console.log("dot has been drawn");
}

// отправка по нажатию
function byClick(event, canvas) {
    const r = document.querySelector('.r');

    if (!r) {
        createError("r не определен");
        return;
    }

    const rect = canvas.getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;
    const R = 50 * r.value;

    const canvasX = x - canvas.width / 2;
    const canvasY = canvas.height / 2 - y;

    const xValue = (canvasX / R * r.value).toFixed(4);
    const yValue = (canvasY / R * r.value).toFixed(4);

    console.log(`Данные нажатия: (${xValue}, ${yValue}, ${r.value})`);

    validateAndSend(xValue, yValue, r.value);
}

function handleSliderChange() {
    let sliderValue = ice.ace.instance('inputs-form:slider').getValue();
    sliderValue = Math.round(sliderValue / 0.5) * 0.5;

    document.getElementById('inputs-form:r').value = sliderValue;
    ice.ace.instance('inputs-form:slider').setValue(sliderValue);

    graphInit();
}
