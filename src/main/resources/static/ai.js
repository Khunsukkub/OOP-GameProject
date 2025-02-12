//เมื่อเลือกโหมด Player vs Bot หรือ Bot vs Bot ให้เรียกใช้ฟังก์ชัน AI
function botMove() {
    const actions = ['Attack', 'Defend', 'Move'];
    const randomAction = actions[Math.floor(Math.random() * actions.length)];
    return randomAction;
}
if (mode === 'PVB') {
    const botAction = botMove();
    alert(`Bot chose to: ${botAction}`);
}