function startGame() {
    const player1 = document.getElementById('player1').value || "Player 1";
    const player2 = document.getElementById('player2').value || "Player 2";

    // ส่งข้อมูลผู้เล่นไปยัง backend
    fetch('http://localhost:8080/api/start', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ player1, player2 })
    })
        .then(response => response.json())
        .then(data => {
            alert(`Game started!\n${data.firstPlayer} will start first!`);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to start the game!');
        });
}