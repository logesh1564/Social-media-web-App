//// A game of 3x3
//const n = 3;
//const winCondition = 3;
//// Init a 2D array that manage the board, default value 0
//const gameBoard = Array.from({length: n}, () =>
//    Array.from({length: n}, () => 0)
//);
//// current player and AI status
//let player = 1;
//let AI = false;
//
//function restart() {
//    const cells = document.querySelectorAll('.cell');
//    for (let i = 0; i < cells.length; i++) {
//        cells[i].textContent = '';
//        cells[i].addEventListener('click', cellClick, false);
//    }
//    player = 1;
//    gameBoard.forEach(arr => arr.fill(0));
//    console.log(gameBoard)
//}
//
//function cellClick(cell) {
//    play(cell.target.id);
//    if (AI) {
//        // do sth to get the id
//        const aiResult = minmax(gameBoard, 0, player);
//        console.log(aiResult);
//        const cellId = `c${aiResult.row}${aiResult.col}`;
//        play(cellId);
//    }
//}
//
//function play(cellId) {
//    const cell = document.getElementById(cellId);
//    if (cell.textContent === '') {
//        const pattern = player === 1 ? 'O' : 'X';
//        console.log(`play ${pattern} on ${cellId}`);
//        cell.textContent = pattern;
//        const row = parseInt(cellId.charAt(1));
//        const col = parseInt(cellId.charAt(2));
//        writeBoard(row, col);
//        checkBoard(row, col);
//        switchTurn();
//    }
//
//}
//
//function writeBoard(row, col) {
//    console.log(`${row}:${col}`);
//    gameBoard[row][col] = player;
//}
//
//function switchTurn() {
//    if (player === 1) {
//        player = -1;
//    } else {
//        player = 1;
//    }
//}
//
//function checkBoard(row, col) {
//    let winner = null;
//    //check win
//    const state = gameState(gameBoard, player, row, col);
//    if (state) {
//        winner = player === 1 ? 'O' : 'X';
//        endGame(winner);
//    } else if (state === null) {
//        console.log(`TIE`);
//        endGame(null);
//    }
//}
//
//function gameState(board, player, row, col) {
//    let diag1 = 0;
//    let diag2 = 0;
//    // check all board if not pass in last turn play
//    if (row === undefined && col === undefined) {
//        for (let i = 0; i < n; i++) {
//            let ver = 0;
//            let hor = 0;
//            for (let j = 0; j < n; j++) {
//                ver += board[j][i];
//                hor += board[i][j];
//            }
//            if (ver === player * winCondition || hor === player * winCondition) {
//                return true;
//            }
//        }
//        for (let i = 0; i < n; i++) {
//            //check diagonal
//            diag1 += board[i][i];
//            diag2 += board[i][n - 1 - i];
//        }
//        if (diag1 === player * winCondition || diag2 === player * winCondition) {
//            return true;
//        } else if (board.every(arr => arr.every(n => n !== 0))) {
//            return null;
//        } else {
//            return false;
//        }
//    } else {
//        // check vertical
//        //check horizontal
//        let ver = 0;
//        let hor = 0;
//        for (let i = 0; i < n; i++) {
//            ver += board[i][col];
//            hor += board[row][i];
//            //check diagonal
//            diag1 += board[i][i];
//            diag2 += board[i][n - 1 - i];
//        }
//        if (ver === player * winCondition || hor === player * winCondition || diag1 === player * winCondition || diag2 === player * winCondition) {
//            return true;
//        } else if (board.every(arr => arr.every(n => n !== 0))) {
//            return null;
//        } else {
//            return false;
//        }
//    }
//}
//
//function endGame(winner) {
//    const cells = document.querySelectorAll('.cell');
//    for (let i = 0; i < cells.length; i++) {
//        cells[i].removeEventListener('click', cellClick);
//    }
//    if (winner !== null) {
//        setTimeout(() => alert(`Congratulations! ${winner} you Win`), 0);
//
//    } else {
//        setTimeout(() => alert(`It's a tie`), 0);
//    }
//}
//
//function minmax(board, depth, player) {
//    // check state of last move by last player, so we have to flip player
//    const state = gameState(board, player === 1 ? -1 : 1);
//    if (state) {
//        // game win go here
//        // if this turn player is -1 (AI), then last turn is 1 (Human)
//        return player === -1 ? depth - 10 : 10 - depth;
//    } else if (state === null) {
//        return 0;
//    } else {
//        let moves = [];
//        for (let i = 0; i < n; i++) {
//            for (let j = 0; j < n; j++) {
//                // clone the board
//                const calcBoard = board.map(arr => Array.from(arr));
//                if (calcBoard[i][j] === 0) {
//                    calcBoard[i][j] = player;
//                    const value = minmax(calcBoard, depth + 1, player === 1 ? -1 : 1);
//                    moves.push({
//                        cost: value,
//                        cell: {
//                            row: i,
//                            col: j
//                        }
//                    });
//                }
//            }
//        }
//        if (player === -1) {
//            const max = moves.reduce((a, b) => a.cost > b.cost ? a : b);
//            if (depth === 0) {
//                return max.cell;
//            } else {
//                return max.cost;
//            }
//        } else {
//            const min = moves.reduce((a, b) => a.cost < b.cost ? a : b);
//            if (depth === 0) {
//                return min.cell;
//            } else {
//                return min.cost;
//            }
//        }
//    }
//}
//
//// start game
//restart();
const ticTacToeGame = new TicTacToeGame();
ticTacToeGame.start();

function TicTacToeGame() {
  const board = new Board();
  const humanPlayer = new HumanPlayer(board);
  const computerPlayer = new ComputerPlayer(board);
  let turn = 0;

  this.start = function() {
    const config = { childList: true };
    const observer = new MutationObserver(() => takeTurn());
    board.positions.forEach((el) => observer.observe(el, config));
    takeTurn();
  }

  function takeTurn() {
    if (board.checkForWinner()) {
      return;
    }

    if (turn % 2 === 0) {
      humanPlayer.takeTurn();
    } else {
      computerPlayer.takeTurn();
    }

    turn++;
  };
}

function Board() {
  this.positions = Array.from(document.querySelectorAll('.col'));

  this.checkForWinner = function() {
    let winner = false;

    const winningCombinations = [
        [0,1,2],
        [3,4,5],
        [6,7,8],
        [0,4,8],
        [2,4,6],
        [0,3,6],
        [1,4,7],
        [2,5,8]
    ];

    const positions = this.positions;
    winningCombinations.forEach((winningCombo) => {
      const pos0InnerText = positions[winningCombo[0]].innerText;
      const pos1InnerText = positions[winningCombo[1]].innerText;
      const pos2InnerText = positions[winningCombo[2]].innerText;
      const isWinningCombo = pos0InnerText !== '' &&
        pos0InnerText === pos1InnerText && pos1InnerText === pos2InnerText;
      if (isWinningCombo) {
          winner = true;
          winningCombo.forEach((index) => {
            positions[index].className += ' winner';
          })
          alert('winner');
      }
    });
    
    return winner;
    
  }
  
}

function ComputerPlayer(board) {
  this.takeTurn = function() {
    let availablePositions = board.positions.filter((p) => p.innerText === '');
    const move = Math.floor(Math.random() * (availablePositions.length - 0));
    availablePositions[move].innerText = 'O';
  }
}

function HumanPlayer(board) {
  this.takeTurn = function() {
    board.positions.forEach(el =>
      el.addEventListener('click', handleTurnTaken));
  }

  function handleTurnTaken(event) {
    event.target.innerText = 'X';
    board.positions
      .forEach(el => el.removeEventListener('click', handleTurnTaken));
  }
}

function myFunction() {
    document.getElementById("rst").reset();
  }