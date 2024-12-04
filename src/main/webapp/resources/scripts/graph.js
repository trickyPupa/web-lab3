const fillColor = "lightpink";

function drawAxis(canvas, context) {
    context.beginPath();
    context.moveTo(0, canvas.height / 2);
    context.lineTo(canvas.width, canvas.height / 2);
    context.strokeStyle = 'black';
    context.lineWidth = 2;
    context.stroke();

    context.beginPath();
    context.moveTo(canvas.width / 2, 0);
    context.lineTo(canvas.width / 2, canvas.height);
    context.strokeStyle = 'black';
    context.lineWidth = 2;
    context.stroke();
}

function drawGrid(canvas, context) {
    context.beginPath();
    const step = 50;

    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;
    const gap = 30;

    context.fillStyle = 'black';
    context.font = '1.25em Montserrat, sans-serif';
    context.textAlign = 'center';
    context.textBaseline = 'bottom';

    for (let x = 0; x <= canvas.width / step; x += 1) {
        context.moveTo(x * step, 0);
        context.lineTo(x * step, canvas.height);
        context.strokeStyle = 'lightgray';
        context.lineWidth = 1;
        context.stroke();

        // context.fillText(x.toString(), centerX + step * x, centerY + gap);
        // context.beginPath();
        // context.moveTo(centerX + step * x, centerY - 5);
        // context.lineTo(centerX + step * x, centerY + 5);
        // context.strokeStyle = 'black';
        // context.closePath();
        // context.stroke();
    }
    for (let y = 0; y < canvas.height / step; y += 1) {
        context.moveTo(0, y * step);
        context.lineTo(canvas.width, y * step);
        context.strokeStyle = 'lightgray';
        context.lineWidth = 1;
        context.stroke();

        // context.fillText(y.toString(), centerX + gap, centerY + step * y + 10);
        // context.beginPath();
        // context.moveTo(centerX - 5, centerY + step * y);
        // context.lineTo(centerX + 5, centerY + step * y);
        // context.strokeStyle = 'black';
        // context.closePath();
        // context.stroke();
    }
}

function drawCircle(context, x, y, r, startAngle, endAngle, counterclockwise=false) {
    context.beginPath();
    context.moveTo(x, y);
    context.fillStyle = fillColor;
    context.arc(x, y, r, startAngle, endAngle, counterclockwise);
    context.closePath();
    context.fill();
}

function drawRect(context, x, y, w, h) {
    context.beginPath();
    context.moveTo(x, y);
    context.fillStyle = fillColor;
    context.rect(x, y, w, h);
    context.closePath();
    context.fill();
}

function drawTriangle(context, x1, y1, x2, y2, x3, y3) {
    context.beginPath();
    context.moveTo(x1, y1);
    context.fillStyle = fillColor;
    context.lineTo(x2, y2);
    context.lineTo(x3, y3);
    context.closePath();
    context.fill();
}

function drawCoords(canvas, context, R) {
	const centerX = canvas.width / 2;
	const centerY = canvas.height / 2;
	const gap = 30;
	
    context.fillStyle = 'black';
    context.font = '1.25em Montserrat, sans-serif';
    context.textAlign = 'center';
    context.textBaseline = 'bottom';
	
    context.fillText('R/2', centerX + R/2, centerY + gap);
    context.beginPath();
    context.moveTo(centerX + R, centerY - 5);
    context.lineTo(centerX + R, centerY + 5);
    context.strokeStyle = 'black';
    context.closePath();
    context.stroke();
    context.fillText('R', centerX + R, centerY + gap);
    context.beginPath();
    context.moveTo(centerX + R, centerY - 5);
    context.lineTo(centerX + R, centerY + 5);
    context.strokeStyle = 'black';
    context.stroke();
    context.closePath();
    context.fillText('-R/2', centerX + gap, centerY + R/2 + 10);
    context.beginPath();
    context.moveTo(centerX - 5, centerY + R/2);
    context.lineTo(centerX + 5, centerY + R/2);
    context.strokeStyle = 'black';
    context.closePath();
    context.stroke();
    context.fillText('-R', centerX + gap - 0, centerY + R + 10);
    context.beginPath();
    context.moveTo(centerX - 5, centerY + R);
    context.lineTo(centerX + 5, centerY + R);
    context.strokeStyle = 'black';
    context.closePath();
    context.stroke();
    context.beginPath();
    context.fillText('-R/2', centerX - R/2, centerY + gap);
    context.moveTo(centerX - R/2, centerY - 5);
    context.lineTo(centerX - R/2, centerY + 5);
    context.strokeStyle = 'black';
    context.closePath();
    context.stroke();
    context.beginPath();
    context.fillText('-R', centerX - R, centerY + gap);
    context.moveTo(centerX - R, centerY - 5);
    context.lineTo(centerX - R, centerY + 5);
    context.strokeStyle = 'black';
    context.closePath();
    context.stroke();
    context.beginPath();
    context.fillText('R/2', centerX + gap, centerY - R/2 + 10);
    context.moveTo(centerX - 5, centerY - R/2);
    context.lineTo(centerX + 5, centerY - R/2);
    context.strokeStyle = 'black';
    context.closePath();
    context.stroke();
    context.beginPath();
    context.fillText('R', centerX + gap, centerY - R + 10);
    context.moveTo(centerX - 5, centerY - R);
    context.lineTo(centerX + 5, centerY - R);
    context.strokeStyle = 'black';
    context.closePath();
    context.stroke();

}

function drawCoords2(canvas, context, R) {
    const step = 50;

    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;
    const gap = 30;

    context.fillStyle = 'black';
    context.font = '1.25em Montserrat, sans-serif';
    context.textAlign = 'center';
    context.textBaseline = 'bottom';

    for (let x = -canvas.width / 2 / step + 1; x <= canvas.width / 2 /step - 1; x += 1) {
        context.fillText(x.toString(), centerX + step * x, centerY + gap);
        context.beginPath();
        context.moveTo(centerX + step * x, centerY - 5);
        context.lineTo(centerX + step * x, centerY + 5);
        context.strokeStyle = 'black';
        context.closePath();
        context.stroke();
    }
    for (let y = -canvas.height / 2 / step + 1; y < canvas.height / 2 / step - 1; y += 1) {
        if (y === 0)
            continue;
        context.fillText((-y).toString(), centerX + gap, centerY + step * y + 10);
        context.beginPath();
        context.moveTo(centerX - 5, centerY + step * y);
        context.lineTo(centerX + 5, centerY + step * y);
        context.strokeStyle = 'black';
        context.closePath();
        context.stroke();
    }
}

function graphInit(){
    const canvas = document.getElementById('canvas');
    const context = canvas.getContext('2d');

    drawGraph(canvas, context);

    canvas.addEventListener('click', (event) => byClick(event, canvas));
}

function drawGraph(canvas, context) {
    const rValue = document.querySelector('.r').value
    if (!rValue){
        return;
    }
    const R = 50 * rValue;

    canvas.width = 500;
    canvas.height = 500;

    drawGrid(canvas, context);
    drawCircle(context, canvas.width / 2, canvas.height / 2, R / 2, 0, Math.PI / 2, false);
    drawRect(context, canvas.width / 2, canvas.height / 2, -R, R / 2);
    drawTriangle(context, canvas.width / 2, canvas.height / 2, canvas.width / 2, canvas.height / 2 - R, canvas.width / 2 + R, canvas.height / 2);
    drawAxis(canvas, context);
    drawCoords2(canvas, context, R);
}

document.addEventListener('DOMContentLoaded', function() {
    graphInit();
})
// window.onload = function () {
//     graphInit();
// };