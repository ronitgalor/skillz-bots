/**
 * This is an example bot.
 */

/**
 * Makes the bot run a single turn.
 *
 * @param  {PirateGame} game - The current game state.
 */
let toIDF = true;
function doTurn(game) {
	// Get one of my pirates.
	const ups = game.getAllMyPirates()[6];
	const upsidf1 = game.getAllMyPirates()[4];
	const upsidf2 = game.getAllMyPirates()[7];
	const idf1 = game.getAllMyPirates()[3];
	const idf2 = game.getAllMyPirates()[1];
	const idfIsis = game.getAllMyPirates()[5];
	const isis1 = game.getAllMyPirates()[0];
	const isis2 = game.getAllMyPirates()[2];

	if (ups.isAlive()) {
		upsTurn(ups, game);
	}
	upsIdfTurn(upsidf1, game, ups);

	if (ups.isAlive()) {
		upsIdfTurn(upsidf2, game, ups);
		upsIdfTurn(idfIsis, game, ups);
	} else {
		upsIdfTurn(upsidf2, game, upsidf1);
		upsIdfTurn(idfIsis, game, upsidf1);
	}

	idfTurn(idf1, game);
	idfTurn(idf2, game);

	isisTurn(isis1, game);
	isisTurn(isis2, game);
}

function idfIsisTurn(idfIsis, game) {
	if (idfIsis.isAlive()) {
		if (toIDF) {
			idfIsis.sail(game.getEnemyMothership());
		} else {
			idfIsis.sail(game.getEnemyCapsule());
		}
		if (idfIsis.getLocation().col === game.getEnemyCapsule().getLocation().col && idfIsis.getLocation().row === game.getEnemyCapsule().getLocation().row) {
			toIDF = true;
		}
		if (idfIsis.getLocation().col === game.getEnemyMothership().getLocation().col && idfIsis.getLocation().row === game.getEnemyMothership().getLocation().row) {
			toIDF = false;
		}
		console.log(toIDF);
	}
}

function upsTurn(ups, game) {
	ups.sail(!ups.hasCapsule() ? game.getMyCapsule() : game.getMyMothership());
}

function upsIdfTurn(upsIdf, game, ups) {
	if (upsIdf.isAlive()) {
		if (!tryPush(upsIdf, game)) {
			if (ups.isAlive()) {
				upsIdf.sail(ups);
			} else {
				upsTurn(upsIdf, game);
			}
		}
	}
}

function isisTurn(isis, game) {
	if (isis.isAlive) {
		if (!tryPush(isis, game)) {
			isis.sail(game.getEnemyCapsule());
		}
	}
}

function idfTurn(idf, game) {
	if (idf.isAlive()) {
		if (!tryPush(idf, game)) {
			let enemy = null;
			for (const e of game.getEnemyLivingPirates()) {
				if (e.getLocation().distance(idf.getLocation()) < (idf.pushRange * 2)) {
					enemy = e;
					break;
				}
			}
			if (!enemy || idf.getLocation().distance(game.getEnemyMothership().getLocation()) > idf.pushRange) {
				idf.sail(game.getEnemyMothership());
			} else {
				idf.sail(enemy);
			}
		}
	}
}

/**
 * Makes the pirate try to push an enemy pirate. Returns true if it did.
 *
 * @param {Pirate} pirate - The pushing pirate.
 * @param {PirateGame} game - The current game state.
 * @return {boolean} - true if the pirate pushed.
 */
/**
 * This is an example bot.
 */

/**
 * Makes the bot run a single turn.
 *
 * @param  {PirateGame} game - The current game state.
 */

/**
 * Makes the pirate try to push an enemy pirate. Returns true if it did.
 *
 * @param {Pirate} pirate - The pushing pirate.
 * @param {PirateGame} game - The current game state.
 * @return {boolean} - true if the pirate pushed.
 */
function tryPush(pirate, game) {
	// Go over all enemies.
	for (const enemy of game.getEnemyLivingPirates()) {
		// Check if the pirate can push the enemy.
		if (pirate.canPush(enemy)) {
			const cols = enemy.getLocation().col;
			const rows = enemy.getLocation().row;
			const min = Math.min(rows, cols, 6400 - rows, 6400 - cols);
			console.log(min);
			let x = 0;
			let y = 0;
			// If (min === cols) {
			//     x = rows;
			//     y = -1;
			// } else if (min === rows) {
			//     x = -1;
			//     y = cols;
			// } else if (min === 6400 - rows) {
			//     x = 6401;
			//     y = cols;
			// } else if (min === 6400 - cols) {
			//     x = rows;
			//     y = 6401;
			// } else {
			//     game.debugger.print("beep");
			// }
			switch (min) {
				case cols:
					x = rows;
					y = -1;
					break;
				case rows:
					x = -1;
					y = cols;
					break;
				case (6400 - rows):
					x = 6401;
					y = cols;
					break;
				case (6400 - cols):
					x = rows;
					y = 6401;
					break;
			}
			pirate.push(enemy, new Location(x, y));
			// If(enemy.getLocation().col<3200)
			// {
			//     x = -100;
			// }

			// if(enemy.getLocation().row<3200)
			// {
			//     y = -100;
			// }

			// if(Math.abs(3200 - enemy.getLocation().col) > Math.abs(3200 - enemy.getLocation().row))
			// {
			//     y = enemy.getLocation().row;
			// }
			// else
			// {
			//     x = enemy.getLocation().col;
			// }
			// Push enemy!

			// Print a message.
			console.log('pirate ' + pirate + ' pushes ' + enemy + ' towards ' + enemy.initialLocation);

			// Did push.
			return true;
		}
	}

	// Didn't push.
	return false;
}