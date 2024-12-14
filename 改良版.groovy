// script.js
let studyTime = 25 * 60; // 25分（秒単位）
let breakTime = 5 * 60; // 5分（休憩時間）
let timeRemaining = studyTime;
let level = 1;
let popcornCount = 0;
let isRunning = false;
let intervalId;

const timeDisplay = document.getElementById('time');
const popcornDisplay = document.getElementById('popcorn-count');
const levelDisplay = document.getElementById('level');
const startButton = document.getElementById('start-button');
const resetButton = document.getElementById('reset-button');

function updateDisplay() {
    const minutes = Math.floor(timeRemaining / 60);
    const seconds = timeRemaining % 60;
    timeDisplay.textContent = `残り時間: ${minutes}:${seconds.toString().padStart(2, '0')}`;
    popcornDisplay.textContent = `ポップコーン: ${popcornCount} 粒`;
    levelDisplay.textContent = `レベル: ${level}`;
}

function startTimer() {
    if (isRunning) return;
    isRunning = true;

    intervalId = setInterval(() => {
        if (timeRemaining > 0) {
            timeRemaining--;
            popcornCount++;
            updateDisplay();
        } else {
            clearInterval(intervalId);
            if (timeRemaining === 0 && studyTime > 0) {
                alert(`レベル${level}完了！5分間の休憩を始めます！`);
                startBreak();
            }
        }
    }, 1000);
}

function startBreak() {
    timeRemaining = breakTime;
    updateDisplay();

    intervalId = setInterval(() => {
        if (timeRemaining > 0) {
            timeRemaining--;
            updateDisplay();
        } else {
            clearInterval(intervalId);
            level++;
            timeRemaining = studyTime;
            isRunning = false;
            alert("休憩終了！次のレベルに進みましょう！");
            updateDisplay();
        }
    }, 1000);
}

function resetTimer() {
    clearInterval(intervalId);
    isRunning = false;
    timeRemaining = studyTime;
    level = 1;
    popcornCount = 0;
    updateDisplay();
}

startButton.addEventListener('click', startTimer);
resetButton.addEventListener('click', resetTimer);

updateDisplay();
